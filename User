import java.util.*;

public class User {
	private int userID;
	private String name;
	private String email;
	private String role;
	
	
	public User(int userID, String name, String email, String role) {
		this.userID = userID;
		this.email = email;
		this.name = name;
		this.role = role;
	}

	public void createTicket(Ticket ticket) {
		ticket.setStatus("open");
		ticket.setCreationDate(new Date());
		ticket.setUpdateDate(new Date());
	}
	
	public void viewTicket(Ticket ticket) {
		 if (Main.tickets.contains(ticket)) {
	            System.out.println("📄 Ticket ID: " + ticket.getTicketID());
	            System.out.println("Titre: " + ticket.getTitle());
	            System.out.println("Description: " + ticket.getDescription());
	            System.out.println("Statut: " + ticket.getStatus());
	            System.out.println("Priorité: " + ticket.getPriority());
	            System.out.println("Créé le: " + ticket.getCreationDate());
	            System.out.println("Mis à jour le: " + ticket.getUpdateDate());
	        } else {
	            System.out.println(" Ce ticket ne fait pas partie des tickets.");
	        }
	}
	 
	public static void updateTicket(Ticket ticket) {
		if (Main.tickets.contains(ticket)) {
		ticket.setUpdateDate(new Date());
		System.out.println(" Ce ticket" + ticket + " a ete uptade");
		 } else {
	            System.out.println(" Ce ticket ne fait pas partie des tickets.");
	        }
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public static void main(String[] args) {
		System.out.println(" Ce ticket ne fait pas partie des tickets.");
	}

	

	
}
