import java.util.ArrayList;
import java.util.List;

public class Admin {
    private int adminID;
    private String name;
    private String email;
    private List<Integer> ticketsID;

    public Admin(int adminID, String name, String email) {
        this.adminID = adminID;
        this.name = name;
        this.email = email;
        this.ticketsID = new ArrayList<Integer>();
    }

    // SUPPRIMER assignTicket() et closeTicket() - maintenant gérés par le contrôleur

    public int getAdminID() { return adminID; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public List<Integer> getTicketsID() {
        return ticketsID;
    }

    public void addTicketID(int id){ticketsID.add(id);}
}