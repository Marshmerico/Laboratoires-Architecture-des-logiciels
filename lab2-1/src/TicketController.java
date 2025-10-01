import java.util.*;

public class TicketController {
    private List<Ticket> tickets;
    private int ticketCounter;

    public TicketController(int ticketCounter) {
        this.tickets = new ArrayList<>();
        this.ticketCounter = ticketCounter;
    }

    // ================= MÉTHODES DE GESTION DES TICKETS =================

    public void createTicket(String title, String description, String priority, int creatorID) {
        Ticket ticket = new Ticket(ticketCounter++, title, description, "OUVERT", priority, creatorID);
        tickets.add(ticket);

        Main.getUserByID(creatorID).ifPresent(user -> user.addTicketID(ticket.getTicketID()));
        System.out.println("Ticket créé avec ID " + ticket.getTicketID());

    }

    public boolean assignTicket(int ticketID, int adminID) {
        Optional<Ticket> ticket = findTicket(ticketID);
        Optional<Admin> admin = Main.findAdmin(adminID);

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
        return false;
    }

    public boolean closeTicket(int ticketID) {
        return updateTicketStatus(ticketID, "TERMINE");
    }

    public boolean addCommentToTicket(int ticketID, String comment, int userID) {
        Optional<Ticket> ticket = findTicket(ticketID);
        Optional<User> user = Main.getUserByID(userID);

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

    // ================= MÉTHODES DE RAPPORT =================

    public void getUserTickets(int userID) {
        List<Ticket> userTickets = tickets.stream()
                .filter(t -> t.getCreatorID() == userID)
                .toList();

        if (userTickets.isEmpty()) {
            System.out.println("Aucun ticket trouvé.");
        } else {
            userTickets.forEach(System.out::println);
        }
    }

    public void getAdminAssignedTickets(int adminID) {
        List<Ticket> assignedTickets = tickets.stream()
                .filter(t -> t.getAssignedAdminID() == adminID)
                .toList();
        if (assignedTickets.isEmpty()) {
            System.out.println("Aucun ticket assigné.");
        } else {
            assignedTickets.forEach(System.out::println);
        }
    }

    public void getAllTickets() {

        if (tickets.isEmpty()) {
            System.out.println("Aucun ticket dans le système.");
        } else {
            tickets.forEach(System.out::println);
        }
    }

    public int getTicketCounter() {
        return ticketCounter;
    }
}