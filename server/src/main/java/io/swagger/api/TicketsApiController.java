package io.swagger.api;

import io.swagger.model.*;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.*;

import io.swagger.User;
import io.swagger.Admin;
import io.swagger.Ticket;

@Controller
public class TicketsApiController implements TicketsApi {

    private static List<Ticket> tickets = new ArrayList<Ticket>();
    private static int ticketCounter = 1;

    @Override
    public ResponseEntity<List<TicketResponse>> getAllTickets() {
        List<TicketResponse> responses = new ArrayList<TicketResponse>();
        for (Ticket ticket : tickets) {
            responses.add(toTicketResponse(ticket));
        }
        return new ResponseEntity<List<TicketResponse>>(responses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TicketResponse> createTicket(@ApiParam(value = "Données du nouveau ticket", required = true)
                                                       @Valid @RequestBody CreateTicketRequest body) {
        // Validate creator exists
        User creator = null;
        for (User u : AuthApiController.getUsers()) {
            if (u.getUserID() == body.getCreatorId()) {
                creator = u;
                break;
            }
        }

        if (creator == null) {
            return new ResponseEntity(createError(404, "Not Found",
                    "Créateur introuvable", "/api/tickets"), HttpStatus.NOT_FOUND);
        }

        Ticket ticket = new Ticket(
                ticketCounter++,
                body.getTitle(),
                body.getDescription(),
                "OUVERT",
                body.getPriority().getValue(),
                body.getCreatorId()
        );

        tickets.add(ticket);
        creator.addTicketID(ticket.getTicketID());

        return new ResponseEntity<TicketResponse>(toTicketResponse(ticket), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<TicketResponse>> getUserTickets(@ApiParam(value = "ID de l'utilisateur", required = true)
                                                               @PathVariable("userId") Integer userId) {
        User user = null;
        for (User u : AuthApiController.getUsers()) {
            if (u.getUserID() == userId) {
                user = u;
                break;
            }
        }

        if (user == null) {
            return new ResponseEntity(createError(404, "Not Found",
                    "Utilisateur introuvable", "/api/tickets/user/" + userId), HttpStatus.NOT_FOUND);
        }

        List<TicketResponse> userTickets = new ArrayList<TicketResponse>();
        for (Ticket t : tickets) {
            if (t.getCreatorID() == userId) {
                userTickets.add(toTicketResponse(t));
            }
        }

        return new ResponseEntity<List<TicketResponse>>(userTickets, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TicketResponse> getTicketById(@ApiParam(value = "ID du ticket", required = true)
                                                        @PathVariable("ticketId") Integer ticketId) {
        Ticket ticket = findTicket(ticketId);

        if (ticket == null) {
            return new ResponseEntity(createError(404, "Not Found",
                    "Ticket introuvable", "/api/tickets/" + ticketId), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<TicketResponse>(toTicketResponse(ticket), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TicketResponse> assignTicket(@ApiParam(value = "ID du ticket", required = true)
                                                       @PathVariable("ticketId") Integer ticketId,
                                                       @ApiParam(value = "ID de l'admin à assigner", required = true)
                                                       @Valid @RequestBody AssignTicketRequest body) {
        Ticket ticket = findTicket(ticketId);

        if (ticket == null) {
            return new ResponseEntity(createError(404, "Not Found",
                    "Ticket introuvable", "/api/tickets/" + ticketId + "/assign"), HttpStatus.NOT_FOUND);
        }

        Admin admin = null;
        for (Admin a : AuthApiController.getAdmins()) {
            if (a.getAdminID() == body.getAdminId()) {
                admin = a;
                break;
            }
        }

        if (admin == null) {
            return new ResponseEntity(createError(404, "Not Found",
                    "Admin introuvable", "/api/tickets/" + ticketId + "/assign"), HttpStatus.NOT_FOUND);
        }

        if (!"OUVERT".equalsIgnoreCase(ticket.getStatus())) {
            return new ResponseEntity(createError(400, "Bad Request",
                    "Le ticket doit être OUVERT pour être assigné",
                    "/api/tickets/" + ticketId + "/assign"), HttpStatus.BAD_REQUEST);
        }

        ticket.assignTo(body.getAdminId());
        admin.addTicketID(ticketId);

        return new ResponseEntity<TicketResponse>(toTicketResponse(ticket), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TicketResponse> updateTicketStatus(@ApiParam(value = "ID du ticket", required = true)
                                                             @PathVariable("ticketId") Integer ticketId,
                                                             @ApiParam(value = "Nouveau statut", required = true)
                                                             @Valid @RequestBody UpdateStatusRequest body) {
        Ticket ticket = findTicket(ticketId);

        if (ticket == null) {
            return new ResponseEntity(createError(404, "Not Found",
                    "Ticket introuvable", "/api/tickets/" + ticketId + "/status"), HttpStatus.NOT_FOUND);
        }

        boolean success = ticket.updateStatus(body.getStatus().getValue());

        if (!success) {
            return new ResponseEntity(createError(400, "Bad Request",
                    "Transition de statut invalide", "/api/tickets/" + ticketId + "/status"),
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<TicketResponse>(toTicketResponse(ticket), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TicketResponse> closeTicket(@ApiParam(value = "ID du ticket", required = true)
                                                      @PathVariable("ticketId") Integer ticketId) {
        Ticket ticket = findTicket(ticketId);

        if (ticket == null) {
            return new ResponseEntity(createError(404, "Not Found",
                    "Ticket introuvable", "/api/tickets/" + ticketId + "/close"), HttpStatus.NOT_FOUND);
        }

        boolean success = ticket.updateStatus("TERMINE");

        if (!success) {
            return new ResponseEntity(createError(400, "Bad Request",
                    "Impossible de fermer le ticket", "/api/tickets/" + ticketId + "/close"),
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<TicketResponse>(toTicketResponse(ticket), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TicketResponse> addComment(@ApiParam(value = "ID du ticket", required = true)
                                                     @PathVariable("ticketId") Integer ticketId,
                                                     @ApiParam(value = "Commentaire à ajouter", required = true)
                                                     @Valid @RequestBody AddCommentRequest body) {
        Ticket ticket = findTicket(ticketId);

        if (ticket == null) {
            return new ResponseEntity(createError(404, "Not Found",
                    "Ticket introuvable", "/api/tickets/" + ticketId + "/comments"), HttpStatus.NOT_FOUND);
        }

        // Check if user is authorized (creator of the ticket)
        if (ticket.getCreatorID() != body.getUserId()) {
            return new ResponseEntity(createError(403, "Forbidden",
                    "Utilisateur non autorisé à commenter ce ticket",
                    "/api/tickets/" + ticketId + "/comments"), HttpStatus.FORBIDDEN);
        }

        ticket.addComment(body.getComment(), body.getUserId());

        return new ResponseEntity<TicketResponse>(toTicketResponse(ticket), HttpStatus.OK);
    }

    // Helper methods
    private Ticket findTicket(int ticketId) {
        for (Ticket t : tickets) {
            if (t.getTicketID() == ticketId) {
                return t;
            }
        }
        return null;
    }

    private TicketResponse toTicketResponse(Ticket ticket) {
        TicketResponse response = new TicketResponse();
        response.setTicketId(ticket.getTicketID());
        response.setTitle(ticket.getTitle());
        response.setDescription(ticket.getDescription());
        response.setStatus(TicketResponse.StatusEnum.fromValue(ticket.getStatus()));
        response.setPriority(TicketResponse.PriorityEnum.fromValue(ticket.getPriority()));
        //response.setCreationDate(OffsetDateTime.now()); // Should use actual date from ticket
        //response.setUpdateDate(OffsetDateTime.now());
        response.setCreatorId(ticket.getCreatorID());
        response.setAssignedAdminId(ticket.getAssignedAdminID());
        return response;
    }

    private ErrorResponse createError(int status, String error, String message, String path) {
        ErrorResponse errorResponse = new ErrorResponse();
        //errorResponse.setTimestamp(OffsetDateTime.now());
        errorResponse.setStatus(status);
        errorResponse.setError(error);
        errorResponse.setMessage(message);
        errorResponse.setPath(path);
        return errorResponse;
    }

    public static List<Ticket> getTickets() {
        return tickets;
    }
}

/*
package io.swagger.api;

import io.swagger.model.AddCommentRequest;
import io.swagger.model.AssignTicketRequest;
import io.swagger.model.CreateTicketRequest;
import io.swagger.model.ErrorResponse;
import io.swagger.model.TicketResponse;
import io.swagger.model.UpdateStatusRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-11-01T21:15:30.678-04:00")

@Controller
public class TicketsApiController implements TicketsApi {

    private static final Logger log = LoggerFactory.getLogger(TicketsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public TicketsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<TicketResponse> addComment(@ApiParam(value = "ID du ticket",required=true) @PathVariable("ticketId") Integer ticketId,@ApiParam(value = "Commentaire à ajouter" ,required=true )  @Valid @RequestBody AddCommentRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TicketResponse>(objectMapper.readValue("{\"empty\": false}", TicketResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TicketResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TicketResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TicketResponse> assignTicket(@ApiParam(value = "ID du ticket",required=true) @PathVariable("ticketId") Integer ticketId,@ApiParam(value = "ID de l'admin à assigner" ,required=true )  @Valid @RequestBody AssignTicketRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TicketResponse>(objectMapper.readValue("{\"empty\": false}", TicketResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TicketResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TicketResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TicketResponse> closeTicket(@ApiParam(value = "ID du ticket",required=true) @PathVariable("ticketId") Integer ticketId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TicketResponse>(objectMapper.readValue("{\"empty\": false}", TicketResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TicketResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TicketResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TicketResponse> createTicket(@ApiParam(value = "Données du nouveau ticket" ,required=true )  @Valid @RequestBody CreateTicketRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TicketResponse>(objectMapper.readValue("{\"empty\": false}", TicketResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TicketResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TicketResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<TicketResponse>> getAllTickets() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<TicketResponse>>(objectMapper.readValue("{}", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<TicketResponse>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<TicketResponse>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TicketResponse> getTicketById(@ApiParam(value = "ID du ticket",required=true) @PathVariable("ticketId") Integer ticketId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TicketResponse>(objectMapper.readValue("{\"empty\": false}", TicketResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TicketResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TicketResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<TicketResponse>> getUserTickets(@ApiParam(value = "ID de l'utilisateur",required=true) @PathVariable("userId") Integer userId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<TicketResponse>>(objectMapper.readValue("{}", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<TicketResponse>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<TicketResponse>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TicketResponse> updateTicketStatus(@ApiParam(value = "ID du ticket",required=true) @PathVariable("ticketId") Integer ticketId,@ApiParam(value = "Nouveau statut" ,required=true )  @Valid @RequestBody UpdateStatusRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TicketResponse>(objectMapper.readValue("{\"empty\": false}", TicketResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TicketResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TicketResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}*/
