import java.util.*;

public class Admin{
    private int adminID;
    private String name;
    private String email;
    private List<int> tickets;

    public Admin(int adminID, String name, String email){
        this.adminID = adminID;
        this.name = name;
        this.email = email;
    }

    public void assignTicket(Ticket ticket, Admin admin){
        ticket.assignTo(admin);
    }

    public void closeTicket(Ticket ticket){
        ticket.updateStatus("TERMINE")
    }

    //public List<Ticket> viewAllTickets(){
    public void viewAllTickets(){
        System.out.prinln("Visualisation de tous les tickets");
        for (int ticket: tickets) {
            System.out.println(ticketsComplets[ticket].toString());
        }
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket.getTicketID());
    }



}