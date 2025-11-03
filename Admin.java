import java.util.*;

public class Admin{
    private int adminID;
    private String name;
    private String email;
    //private List<Ticket> tickets = new ArrayList<>();
    
    public Admin(int adminID, String name, String email){
        this.adminID = adminID;
        this.name = name;
        this.email = email;
    }

    public void assignTicket(Ticket ticket, Admin admin){
        ticket.assignTo(admin);
    }

    public void closeTicket(Ticket ticket){
        ticket.updateStatus("TERMINE");
    }

    //public List<Ticket> viewAllTickets(){
    public void viewAllTickets(){
        System.out.println("Visualisation de tous les tickets");
        for (Ticket ticket: Main.tickets) {
            System.out.println(ticket.toString());
        }
    }

    public void addTicket(Ticket ticket) {
        Main.tickets.add(ticket);
    }



}
