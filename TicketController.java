import java.util.*;
import java.util.stream.Collectors;

public class TicketController {
    private List<Ticket> tickets;
    private int ticketCounter;
    private List<User> users;
    private List<Admin> admins;

    public TicketController(int ticketCounter, List<User> users, List<Admin> admins) {
        this.tickets = new ArrayList<>();
        this.ticketCounter = ticketCounter;
        this.users = users;
        this.admins = admins;
    }

    // ================= MÉTHODES DE GESTION DES TICKETS =================

    public void createTicket(String title, String description, String priority, int creatorID) {
        Ticket ticket = new Ticket(ticketCounter++, title, description, "OUVERT", priority, creatorID);
        tickets.add(ticket);

        getUserByID(creatorID).ifPresent(user -> user.addTicketID(ticket.getTicketID()));
        System.out.println("Ticket créé avec ID " + ticket.getTicketID());
    }

    public boolean assignTicket(int ticketID, int adminID) {
        Optional<Ticket> ticket = findTicket(ticketID);
        Optional<Admin> admin = getAdminByID(adminID);

        if (ticket.isPresent() && admin.isPresent()) {
            if ("OUVERT".equalsIgnoreCase(ticket.get().getStatus())) {
                ticket.get().assignTo(adminID);
                admin.get().addTicketID(ticketID);
                ticket.get().updateStatus("ASSIGNE");
                return true;
            }
        }
        return false;
    }

    public boolean updateTicketStatus(int ticketID, String newStatus) {
        Optional<Ticket> ticket = findTicket(ticketID);
        if (ticket.isPresent()) {
            return ticket.get().updateStatus(newStatus);
        }
        return false;
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

    public Optional<Admin> getAdminByID(int adminID) {
        return admins.stream()
                .filter(a -> a.getAdminID() == adminID)
                .findFirst();
    }

    // ================= MÉTHODES DE RAPPORT =================

    public List<Ticket> getUserTickets(int userID) {
        return tickets.stream()
                .filter(t -> t.getCreatorID() == userID)
                .collect(Collectors.toList());
    }

    public List<Ticket> getAdminAssignedTickets(int adminID) {
        return tickets.stream()
                .filter(t -> t.getAssignedAdminID() == adminID)
                .collect(Collectors.toList());
    }

    public List<Ticket> getAllTickets() {
        return new ArrayList<>(tickets);
    }

    public int getTicketCounter() {
        return ticketCounter;
    }
}