import java.util.ArrayList;
import java.util.List;

public class User {
    private int userID;
    private String name;
    private String email;
    private String role;
    private List<Integer> ticketsID;

    public User(int userID, String name, String email, String role) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.role = role;
        this.ticketsID = new ArrayList<Integer>();
    }

    public void createTicket(Ticket ticket) {
        Main.tickets.add(ticket);
    }

    public int getUserID() { return userID; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getRole() { return role; }

    public List<Integer> getTicketsID() {
        return ticketsID;
    }

    public void addTicketID(int id){ticketsID.add(id);}
}
