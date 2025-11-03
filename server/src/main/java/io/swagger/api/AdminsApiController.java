package io.swagger.api;

import io.swagger.model.*;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.OffsetDateTime;
import java.util.*;

import io.swagger.User;
import io.swagger.Admin;
import io.swagger.Ticket;

@Controller
public class AdminsApiController implements AdminsApi {

    @Override
    public ResponseEntity<List<AdminResponse>> getAllAdmins() {
        List<AdminResponse> adminResponses = new ArrayList<AdminResponse>();
        for (Admin admin : AuthApiController.getAdmins()) {
            adminResponses.add(toAdminResponse(admin));
        }

        return new ResponseEntity<List<AdminResponse>>(adminResponses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TicketResponse>> getAdminAssignedTickets(
            @ApiParam(value = "ID de l'administrateur", required = true)
            @PathVariable("adminId") Integer adminId) {

        Admin admin = null;
        for (Admin a : AuthApiController.getAdmins()) {
            if (a.getAdminID() == adminId) {
                admin = a;
                break;
            }
        }

        if (admin == null) {
            ErrorResponse error = createError(404, "Not Found",
                    "Admin introuvable", "/api/admins/" + adminId + "/tickets");
            return new ResponseEntity(error, HttpStatus.NOT_FOUND);
        }

        List<TicketResponse> assignedTickets = new ArrayList<TicketResponse>();
        for (Ticket t : TicketsApiController.getTickets()) {
            if (t.getAssignedAdminID() == adminId) {
                assignedTickets.add(toTicketResponse(t));
            }
        }

        return new ResponseEntity<List<TicketResponse>>(assignedTickets, HttpStatus.OK);
    }

    // Helper methods
    private AdminResponse toAdminResponse(Admin admin) {
        AdminResponse response = new AdminResponse();
        response.setAdminId(admin.getAdminID());
        response.setName(admin.getName());
        response.setEmail(admin.getEmail());
        response.setTicketsId(admin.getTicketsID());
        return response;
    }

    private TicketResponse toTicketResponse(Ticket ticket) {
        TicketResponse response = new TicketResponse();
        response.setTicketId(ticket.getTicketID());
        response.setTitle(ticket.getTitle());
        response.setDescription(ticket.getDescription());
        response.setStatus(TicketResponse.StatusEnum.fromValue(ticket.getStatus()));
        response.setPriority(TicketResponse.PriorityEnum.fromValue(ticket.getPriority()));
        //response.setCreationDate(OffsetDateTime.now());
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
}
/*
package io.swagger.api;

import io.swagger.model.AdminResponse;
import io.swagger.model.ErrorResponse;
import io.swagger.model.TicketResponse;
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
public class AdminsApiController implements AdminsApi {

    private static final Logger log = LoggerFactory.getLogger(AdminsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public AdminsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<TicketResponse>> getAdminAssignedTickets(@ApiParam(value = "ID de l'administrateur",required=true) @PathVariable("adminId") Integer adminId) {
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

    public ResponseEntity<List<AdminResponse>> getAllAdmins() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<AdminResponse>>(objectMapper.readValue("{}", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<AdminResponse>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<AdminResponse>>(HttpStatus.NOT_IMPLEMENTED);
    }

}*/
