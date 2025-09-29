import java.util.*;

public class TicketController {
    private List<User> users;
    private List<Admin> admins;
    private List<Ticket> tickets;
    private int ticketCounter;

    public TicketController(List<User> users, List<Admin> admins, List<Ticket> tickets, int ticketCounter) {
        this.users = users;
        this.admins = admins;
        this.tickets = tickets;
        this.ticketCounter = ticketCounter;
    }

    // ================= MÉTHODES DE GESTION DES TICKETS =================

    public Ticket createTicket(String title, String description, String priority, int creatorID) {
        Ticket ticket = new Ticket(ticketCounter++, title, description, "OUVERT", priority, creatorID);
        tickets.add(ticket);

        getUserByID(creatorID).ifPresent(user -> user.addTicketID(ticket.getTicketID()));

        return ticket;
    }

    public boolean assignTicket(int ticketID, int adminID) {
        Optional<Ticket> ticket = findTicket(ticketID);
        Optional<Admin> admin = findAdmin(adminID);

        if (ticket.isPresent() && admin.isPresent()) {
            if ("OUVERT".equalsIgnoreCase(ticket.get().getStatus())) {
                ticket.get().assignTo(adminID);
                admin.get().addTicketID(ticketID);
                return true;
            }
        }
        return false;
    }

    public boolean updateTicketStatus(int ticketID, String newStatus) {
        Optional<Ticket> ticket = findTicket(ticketID);
        if (ticket.isPresent()) {
            return ticket.get().updateStatus(newStatus);
            //return true;
        }
        //return false;
    }

    public boolean closeTicket(int ticketID) {
        return updateTicketStatus(ticketID, "TERMINE");
    }

    public boolean addCommentToTicket(int ticketID, String comment, int userID) {
        Optional<Ticket> ticket = findTicket(ticketID);
        Optional<User> user = getUserByID(userID);

        if (ticket.isPresent() && user.isPresent() &&
                user.get().getTicketsID().contains(ticketID)) {
            ticket.get().addComment(comment, userID);
            return true;
        }
        return false;
    }

    // ================= MÉTHODES DE RECHERCHE =================

    public Optional<Ticket> findTicket(int ticketID) {
        return tickets.stream()
                .filter(t -> t.getTicketID() == ticketID)
                .findFirst();
    }

    public Optional<User> getUserByID(int userID) {
        return users.stream()
                .filter(u -> u.getUserID() == userID)
                .findFirst();
    }

    public Optional<Admin> findAdmin(int adminID) {
        return admins.stream()
                .filter(a -> a.getAdminID() == adminID)
                .findFirst();
    }

    public Optional<User> loginUser(String email) {
        return users.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public Optional<Admin> loginAdmin(String email) {
        return admins.stream()
                .filter(a -> a.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    // ================= MÉTHODES DE RAPPORT =================

    public List<Ticket> getUserTickets(int userID) {
        return tickets.stream()
                .filter(t -> t.getCreatorID() == userID)
                .toList();
    }

    public List<Ticket> getAdminAssignedTickets(int adminID) {
        return tickets.stream()
                .filter(t -> t.getAssignedAdminID() == adminID)
                .toList();
    }

    public List<Ticket> getAllTickets() {
        return new ArrayList<>(tickets);
    }

    public int getTicketCounter() {
        return ticketCounter;
    }
}