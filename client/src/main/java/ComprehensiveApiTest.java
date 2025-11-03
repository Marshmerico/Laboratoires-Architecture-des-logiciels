import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.api.*;
import io.swagger.client.model.*;

import java.util.List;

/**
 * Comprehensive test client for Ticket Management System API
 * Tests all endpoints and demonstrates the complete workflow
 */
public class ComprehensiveApiTest {

    private static final String BASE_PATH = "http://localhost:8080/api";
    private static ApiClient apiClient;
    private static AuthenticationApi authApi;
    private static TicketsApi ticketsApi;
    private static UsersApi usersApi;
    private static AdminsApi adminsApi;

    public static void main(String[] args) {
        setupApiClient();

        System.out.println("=================================================");
        System.out.println("TICKET MANAGEMENT SYSTEM - API TEST SUITE");
        System.out.println("=================================================\n");

        try {
            // Test sequence
            testAuthentication();
            testUserManagement();
            testAdminManagement();
            testTicketLifecycle();
            testTicketAssignment();
            testTicketComments();
            testTicketStatusTransitions();
            testErrorHandling();

            System.out.println("\n=================================================");
            System.out.println("ALL TESTS COMPLETED SUCCESSFULLY!");
            System.out.println("=================================================");

        } catch (Exception e) {
            System.err.println("\n❌ TEST SUITE FAILED!");
            e.printStackTrace();
        }
    }

    private static void setupApiClient() {
        apiClient = new ApiClient();
        apiClient.setBasePath(BASE_PATH);
        apiClient.addDefaultHeader("Content-Type", "application/json");
        apiClient.addDefaultHeader("Accept", "application/json");

        authApi = new AuthenticationApi(apiClient);
        ticketsApi = new TicketsApi(apiClient);
        usersApi = new UsersApi(apiClient);
        adminsApi = new AdminsApi(apiClient);
    }

    // ==================== AUTHENTICATION TESTS ====================

    private static void testAuthentication() throws ApiException {
        printTestHeader("AUTHENTICATION TESTS");

        // Test 1: User login
        System.out.println("Test 1: User Login (Alice)");
        LoginRequest userLogin = new LoginRequest();
        userLogin.setEmail("alice@mail.com");
        userLogin.setUserType(LoginRequest.UserTypeEnum.USER);

        LoginResponse userResponse = authApi.login(userLogin);
        printSuccess("User logged in successfully");
        System.out.println("  → ID: " + userResponse.getId());
        System.out.println("  → Name: " + userResponse.getName());
        System.out.println("  → Role: " + userResponse.getRole());
        System.out.println();

        // Test 2: Admin login
        System.out.println("Test 2: Admin Login (Admin1)");
        LoginRequest adminLogin = new LoginRequest();
        adminLogin.setEmail("admin1@mail.com");
        adminLogin.setUserType(LoginRequest.UserTypeEnum.ADMIN);

        LoginResponse adminResponse = authApi.login(adminLogin);
        printSuccess("Admin logged in successfully");
        System.out.println("  → ID: " + adminResponse.getId());
        System.out.println("  → Name: " + adminResponse.getName());
        System.out.println();

        // Test 3: Invalid login
        System.out.println("Test 3: Invalid Login (should fail)");
        try {
            LoginRequest invalidLogin = new LoginRequest();
            invalidLogin.setEmail("nonexistent@mail.com");
            invalidLogin.setUserType(LoginRequest.UserTypeEnum.USER);
            authApi.login(invalidLogin);
            printError("Should have failed but didn't!");
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                printSuccess("Correctly rejected invalid login (401)");
            } else {
                throw e;
            }
        }
        System.out.println();
    }

    // ==================== USER MANAGEMENT TESTS ====================

    private static void testUserManagement() throws ApiException {
        printTestHeader("USER MANAGEMENT TESTS");

        // Test 1: Get all users
        System.out.println("Test 1: Get All Users");
        List<UserResponse> users = usersApi.getAllUsers();
        printSuccess("Retrieved " + users.size() + " users");
        for (UserResponse user : users) {
            System.out.println("  → " + user.getName() + " (" + user.getEmail() + ") - Role: " + user.getRole());
        }
        System.out.println();
    }

    // ==================== ADMIN MANAGEMENT TESTS ====================

    private static void testAdminManagement() throws ApiException {
        printTestHeader("ADMIN MANAGEMENT TESTS");

        // Test 1: Get all admins
        System.out.println("Test 1: Get All Admins");
        List<AdminResponse> admins = adminsApi.getAllAdmins();
        printSuccess("Retrieved " + admins.size() + " admins");
        for (AdminResponse admin : admins) {
            System.out.println("  → " + admin.getName() + " (" + admin.getEmail() + ")");
        }
        System.out.println();
    }

    // ==================== TICKET LIFECYCLE TESTS ====================

    private static int createdTicketId;

    private static void testTicketLifecycle() throws ApiException {
        printTestHeader("TICKET LIFECYCLE TESTS");

        // Test 1: Create a ticket
        System.out.println("Test 1: Create New Ticket");
        CreateTicketRequest newTicket = new CreateTicketRequest();
        newTicket.setTitle("Bug dans l'application");
        newTicket.setDescription("L'application crash au démarrage");
        newTicket.setPriority(CreateTicketRequest.PriorityEnum.HAUTE);
        newTicket.setCreatorId(1); // Alice

        TicketResponse createdTicket = ticketsApi.createTicket(newTicket);
        createdTicketId = createdTicket.getTicketId();
        printSuccess("Ticket created with ID: " + createdTicketId);
        System.out.println("  → Title: " + createdTicket.getTitle());
        System.out.println("  → Status: " + createdTicket.getStatus());
        System.out.println("  → Priority: " + createdTicket.getPriority());
        System.out.println();

        // Test 2: Get ticket by ID
        System.out.println("Test 2: Get Ticket by ID");
        TicketResponse retrievedTicket = ticketsApi.getTicketById(createdTicketId);
        printSuccess("Retrieved ticket: " + retrievedTicket.getTitle());
        System.out.println();

        // Test 3: Get user's tickets
        System.out.println("Test 3: Get User's Tickets (Alice - ID: 1)");
        List<TicketResponse> userTickets = ticketsApi.getUserTickets(1);
        printSuccess("User has " + userTickets.size() + " ticket(s)");
        for (TicketResponse ticket : userTickets) {
            System.out.println("  → Ticket #" + ticket.getTicketId() + ": " + ticket.getTitle() +
                    " [" + ticket.getStatus() + "]");
        }
        System.out.println();

        // Test 4: Get all tickets
        System.out.println("Test 4: Get All Tickets (Admin view)");
        List<TicketResponse> allTickets = ticketsApi.getAllTickets();
        printSuccess("Total tickets in system: " + allTickets.size());
        System.out.println();
    }

    // ==================== TICKET ASSIGNMENT TESTS ====================

    private static void testTicketAssignment() throws ApiException {
        printTestHeader("TICKET ASSIGNMENT TESTS");

        // Test 1: Assign ticket to admin
        System.out.println("Test 1: Assign Ticket to Admin");
        AssignTicketRequest assignRequest = new AssignTicketRequest();
        assignRequest.setAdminId(1); // Admin1

        TicketResponse assignedTicket = ticketsApi.assignTicket(createdTicketId, assignRequest);
        printSuccess("Ticket assigned to Admin1");
        System.out.println("  → New Status: " + assignedTicket.getStatus());
        System.out.println("  → Assigned Admin ID: " + assignedTicket.getAssignedAdminId());
        System.out.println();

        // Test 2: Get admin's assigned tickets
        System.out.println("Test 2: Get Admin's Assigned Tickets");
        List<TicketResponse> adminTickets = adminsApi.getAdminAssignedTickets(1);
        printSuccess("Admin has " + adminTickets.size() + " assigned ticket(s)");
        for (TicketResponse ticket : adminTickets) {
            System.out.println("  → Ticket #" + ticket.getTicketId() + ": " + ticket.getTitle() +
                    " [" + ticket.getStatus() + "]");
        }
        System.out.println();

        // Test 3: Try to reassign already assigned ticket (should fail)
        System.out.println("Test 3: Try to Reassign Already Assigned Ticket (should fail)");
        try {
            ticketsApi.assignTicket(createdTicketId, assignRequest);
            printError("Should have failed but didn't!");
        } catch (ApiException e) {
            if (e.getCode() == 400) {
                printSuccess("Correctly rejected reassignment (400)");
            } else {
                throw e;
            }
        }
        System.out.println();
    }

    // ==================== TICKET COMMENTS TESTS ====================

    private static void testTicketComments() throws ApiException {
        printTestHeader("TICKET COMMENTS TESTS");

        // Test 1: Add comment to ticket
        System.out.println("Test 1: Add Comment to Ticket");
        AddCommentRequest commentRequest = new AddCommentRequest();
        commentRequest.setComment("J'ai trouvé la source du problème");
        commentRequest.setUserId(1); // Alice (creator)

        TicketResponse commentedTicket = ticketsApi.addComment(createdTicketId, commentRequest);
        printSuccess("Comment added successfully");
        System.out.println("  → Updated description: " + commentedTicket.getDescription());
        System.out.println();

        // Test 2: Try to comment on someone else's ticket (should fail)
        System.out.println("Test 2: Unauthorized Comment (should fail)");
        CreateTicketRequest bobTicket = new CreateTicketRequest();
        bobTicket.setTitle("Bob's ticket");
        bobTicket.setDescription("Bob's issue");
        bobTicket.setPriority(CreateTicketRequest.PriorityEnum.MOYENNE);
        bobTicket.setCreatorId(2); // Bob

        TicketResponse bobsTicket = ticketsApi.createTicket(bobTicket);

        try {
            AddCommentRequest unauthorizedComment = new AddCommentRequest();
            unauthorizedComment.setComment("Alice trying to comment on Bob's ticket");
            unauthorizedComment.setUserId(1); // Alice
            ticketsApi.addComment(bobsTicket.getTicketId(), unauthorizedComment);
            printError("Should have failed but didn't!");
        } catch (ApiException e) {
            if (e.getCode() == 403) {
                printSuccess("Correctly rejected unauthorized comment (403)");
            } else {
                throw e;
            }
        }
        System.out.println();
    }

    // ==================== STATUS TRANSITION TESTS ====================

    private static void testTicketStatusTransitions() throws ApiException {
        printTestHeader("TICKET STATUS TRANSITION TESTS");

        // Create a new ticket for status tests
        CreateTicketRequest statusTestTicket = new CreateTicketRequest();
        statusTestTicket.setTitle("Status Transition Test Ticket");
        statusTestTicket.setDescription("Testing status transitions");
        statusTestTicket.setPriority(CreateTicketRequest.PriorityEnum.BASSE);
        statusTestTicket.setCreatorId(1);

        TicketResponse testTicket = ticketsApi.createTicket(statusTestTicket);
        int testTicketId = testTicket.getTicketId();

        // Test 1: OUVERT → ASSIGNE
        System.out.println("Test 1: Transition OUVERT → ASSIGNE");
        AssignTicketRequest assign = new AssignTicketRequest();
        assign.setAdminId(1);
        testTicket = ticketsApi.assignTicket(testTicketId, assign);
        printSuccess("Status: " + testTicket.getStatus());
        System.out.println();

        // Test 2: ASSIGNE → VALIDATION
        System.out.println("Test 2: Transition ASSIGNE → VALIDATION");
        UpdateStatusRequest toValidation = new UpdateStatusRequest();
        toValidation.setStatus(UpdateStatusRequest.StatusEnum.VALIDATION);
        testTicket = ticketsApi.updateTicketStatus(testTicketId, toValidation);
        printSuccess("Status: " + testTicket.getStatus());
        System.out.println();

        // Test 3: VALIDATION → TERMINE
        System.out.println("Test 3: Transition VALIDATION → TERMINE");
        UpdateStatusRequest toTermine = new UpdateStatusRequest();
        toTermine.setStatus(UpdateStatusRequest.StatusEnum.TERMINE);
        testTicket = ticketsApi.updateTicketStatus(testTicketId, toTermine);
        printSuccess("Status: " + testTicket.getStatus());
        System.out.println();

        // Test 4: Invalid transition (should fail)
        System.out.println("Test 4: Invalid Status Transition (should fail)");
        CreateTicketRequest invalidTransTicket = new CreateTicketRequest();
        invalidTransTicket.setTitle("Invalid Transition Test");
        invalidTransTicket.setDescription("Testing invalid transition");
        invalidTransTicket.setPriority(CreateTicketRequest.PriorityEnum.HAUTE);
        invalidTransTicket.setCreatorId(1);

        TicketResponse invalidTicket = ticketsApi.createTicket(invalidTransTicket);

        try {
            UpdateStatusRequest invalidStatus = new UpdateStatusRequest();
            invalidStatus.setStatus(UpdateStatusRequest.StatusEnum.TERMINE);
            ticketsApi.updateTicketStatus(invalidTicket.getTicketId(), invalidStatus);
            printError("Should have failed but didn't!");
        } catch (ApiException e) {
            if (e.getCode() == 400) {
                printSuccess("Correctly rejected invalid transition (400)");
            } else {
                throw e;
            }
        }
        System.out.println();

        // Test 5: Close ticket directly
        System.out.println("Test 5: Close Ticket Endpoint");
        CreateTicketRequest closeTestTicket = new CreateTicketRequest();
        closeTestTicket.setTitle("Close Test Ticket");
        closeTestTicket.setDescription("Testing close endpoint");
        closeTestTicket.setPriority(CreateTicketRequest.PriorityEnum.MOYENNE);
        closeTestTicket.setCreatorId(1);

        TicketResponse closeTicket = ticketsApi.createTicket(closeTestTicket);

        // Assign it first
        AssignTicketRequest assignForClose = new AssignTicketRequest();
        assignForClose.setAdminId(1);
        closeTicket = ticketsApi.assignTicket(closeTicket.getTicketId(), assignForClose);

        // Move to VALIDATION
        UpdateStatusRequest toVal = new UpdateStatusRequest();
        toVal.setStatus(UpdateStatusRequest.StatusEnum.VALIDATION);
        closeTicket = ticketsApi.updateTicketStatus(closeTicket.getTicketId(), toVal);

        // Now close it
        closeTicket = ticketsApi.closeTicket(closeTicket.getTicketId());
        printSuccess("Ticket closed: " + closeTicket.getStatus());
        System.out.println();
    }

    // ==================== ERROR HANDLING TESTS ====================

    private static void testErrorHandling() throws ApiException {
        printTestHeader("ERROR HANDLING TESTS");

        // Test 1: Get non-existent ticket
        System.out.println("Test 1: Get Non-existent Ticket");
        try {
            ticketsApi.getTicketById(99999);
            printError("Should have returned 404!");
        } catch (ApiException e) {
            if (e.getCode() == 404) {
                printSuccess("Correctly returned 404 for non-existent ticket");
            } else {
                throw e;
            }
        }
        System.out.println();

        // Test 2: Get tickets for non-existent user
        System.out.println("Test 2: Get Tickets for Non-existent User");
        try {
            ticketsApi.getUserTickets(99999);
            printError("Should have returned 404!");
        } catch (ApiException e) {
            if (e.getCode() == 404) {
                printSuccess("Correctly returned 404 for non-existent user");
            } else {
                throw e;
            }
        }
        System.out.println();

        // Test 3: Assign to non-existent admin
        System.out.println("Test 3: Assign to Non-existent Admin");
        CreateTicketRequest errorTicket = new CreateTicketRequest();
        errorTicket.setTitle("Error Test Ticket");
        errorTicket.setDescription("For error testing");
        errorTicket.setPriority(CreateTicketRequest.PriorityEnum.BASSE);
        errorTicket.setCreatorId(1);

        TicketResponse errorTestTicket = ticketsApi.createTicket(errorTicket);

        try {
            AssignTicketRequest badAssign = new AssignTicketRequest();
            badAssign.setAdminId(99999);
            ticketsApi.assignTicket(errorTestTicket.getTicketId(), badAssign);
            printError("Should have returned 404!");
        } catch (ApiException e) {
            if (e.getCode() == 404) {
                printSuccess("Correctly returned 404 for non-existent admin");
            } else {
                throw e;
            }
        }
        System.out.println();
    }

    // ==================== UTILITY METHODS ====================

    private static void printTestHeader(String title) {
        System.out.println("\n" + "=============================================================");
        System.out.println(title);
        System.out.println("============================================================" + "\n");
    }

    private static void printSuccess(String message) {
        System.out.println("✅ " + message);
    }

    private static void printError(String message) {
        System.err.println("❌ " + message);
    }
}