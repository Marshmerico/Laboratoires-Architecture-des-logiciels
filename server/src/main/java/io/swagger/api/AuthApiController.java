package io.swagger.api;

import io.swagger.model.ErrorResponse;
import io.swagger.model.LoginRequest;
import io.swagger.model.LoginResponse;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
//import java.time.OffsetDateTime;


import java.util.ArrayList;
import java.util.List;

import io.swagger.User;
import io.swagger.Admin;
import io.swagger.Ticket;

@Controller
public class AuthApiController implements AuthApi {

    // Mock data - in real app, inject these via service/repository
    private static List<User> users = new ArrayList<User>();
    private static List<Admin> admins = new ArrayList<Admin>();

    static {
        // Initialize with test data
        users.add(new User(1, "Alice", "alice@mail.com", "DEV"));
        users.add(new User(2, "Bob", "bob@mail.com", "DEV"));
        users.add(new User(3, "Charlie", "charlie@mail.com", "DEV"));
        admins.add(new Admin(1, "Admin1", "admin1@mail.com"));
    }

    @Override
    public ResponseEntity<LoginResponse> login(@ApiParam(value = "Informations de connexion", required = true)
                                               @Valid @RequestBody LoginRequest body) {

        String email = body.getEmail();
        LoginRequest.UserTypeEnum userType = body.getUserType();

        if (userType == LoginRequest.UserTypeEnum.USER) {
            User foundUser = null;
            for (User u : users) {
                if (u.getEmail().equalsIgnoreCase(email)) {
                    foundUser = u;
                    break;
                }
            }

            if (foundUser != null) {
                LoginResponse response = new LoginResponse();
                response.setId(foundUser.getUserID());
                response.setName(foundUser.getName());
                response.setEmail(foundUser.getEmail());
                response.setUserType(LoginResponse.UserTypeEnum.USER);
                response.setRole(foundUser.getRole());
                return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
            }
        } else if (userType == LoginRequest.UserTypeEnum.ADMIN) {
            Admin foundAdmin = null;
            for (Admin a : admins) {
                if (a.getEmail().equalsIgnoreCase(email)) {
                    foundAdmin = a;
                    break;
                }
            }

            if (foundAdmin != null) {
                LoginResponse response = new LoginResponse();
                response.setId(foundAdmin.getAdminID());
                response.setName(foundAdmin.getName());
                response.setEmail(foundAdmin.getEmail());
                response.setUserType(LoginResponse.UserTypeEnum.ADMIN);
                return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
            }
        }

        // User not found
        ErrorResponse error = new ErrorResponse();
        //error.setTimestamp(OffsetDateTime.now());
        error.setStatus(401);
        error.setError("Unauthorized");
        error.setMessage("Email invalide ou utilisateur introuvable");
        error.setPath("/api/auth/login");
        return new ResponseEntity(error, HttpStatus.UNAUTHORIZED);
    }

    // Getters for accessing data from other controllers
    public static List<User> getUsers() {
        return users;
    }

    public static List<Admin> getAdmins() {
        return admins;
    }
}
/*
package io.swagger.api;

import io.swagger.model.ErrorResponse;
import io.swagger.model.LoginRequest;
import io.swagger.model.LoginResponse;
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
public class AuthApiController implements AuthApi {

    private static final Logger log = LoggerFactory.getLogger(AuthApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public AuthApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<LoginResponse> login(@ApiParam(value = "Informations de connexion" ,required=true )  @Valid @RequestBody LoginRequest body) {
        LoginResponse response = new LoginResponse();
        String email = body.getEmail();
        LoginRequest.UserTypeEnum userType = body.getUserType();

        // Mock user database
        if (userType == LoginRequest.UserTypeEnum.USER) {

            if ("alice@mail.com".equals(email)) {
                response.setId(1);
                response.setName("Alice");
                response.setEmail("alice@mail.com");
                response.setUserType(LoginResponse.UserTypeEnum.USER);
                response.setRole("DEV");
                return ResponseEntity.ok(response);
            } else if ("bob@mail.com".equals(email)) {
                response.setId(2);
                response.setName("Bob");
                response.setEmail("bob@mail.com");
                response.setUserType(LoginResponse.UserTypeEnum.USER);
                response.setRole("DEV");
                return ResponseEntity.ok(response);
            }
        }
        else if (userType == LoginRequest.UserTypeEnum.ADMIN) {

            if ("admin1@mail.com".equals(email)) {
                response.setId(1);
                response.setName("Admin1");
                response.setEmail("admin1@mail.com");
                response.setUserType(LoginResponse.UserTypeEnum.ADMIN);
                return ResponseEntity.ok(response);
            }
        }

        // User not found - return 401
        return new ResponseEntity<LoginResponse>(HttpStatus.UNAUTHORIZED);
    }
    /*
    public ResponseEntity<LoginResponse> login(@ApiParam(value = "Informations de connexion" ,required=true )  @Valid @RequestBody LoginRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<LoginResponse>(objectMapper.readValue("{\"empty\": false}", LoginResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<LoginResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<LoginResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}*/
