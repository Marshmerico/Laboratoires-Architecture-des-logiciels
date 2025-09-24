import java.util.*;

public class Ticket {
    private int ticketID;
    private String title;
    private String description;
    private String status;
    private String priority;
    private Date creationDate;
    private Date updateDate;

    private int creatorID;        // ID du User qui a créé le ticket
    private int assignedAdminID;  // ID de l’Admin assigné (0 = aucun)

    public Ticket(int ticketID, String title, String description, String status, String priority, int creatorID) {
        this.ticketID = ticketID;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.creationDate = new Date();
        this.updateDate = new Date();
        this.creatorID = creatorID;
        this.assignedAdminID = 0; // pas encore assigné
    }

    public void assignTo(int adminID) {
        if ("OUVERT".equalsIgnoreCase(status)) {
            this.assignedAdminID = adminID;
            this.status = "ASSIGNE";
            this.updateDate = new Date();
        } else {
            System.out.println("⚠️ Le ticket doit être OUVERT pour être assigné.");
        }
    }

    public void updateStatus(String status) {
        this.status = status;
        this.updateDate = new Date();
    }

    public void addComment(String comment, int userID) {
        this.description = this.description + " [Commentaire user#" + userID + " : " + comment + "]";
        this.updateDate = new Date();
    }

    @Override
    public String toString() {
        // Chercher le nom du créateur
        String creatorName = Main.users.stream()
                .filter(u -> u.getUserID() == creatorID)
                .map(User::getName)
                .findFirst()
                .orElse("inconnu");

        // Chercher le nom de l’admin assigné
        String adminName = (assignedAdminID != 0)
                ? Main.admins.stream()
                .filter(a -> a.getAdminID() == assignedAdminID)
                .map(Admin::getName)
                .findFirst()
                .orElse("inconnu")
                : "aucun";

        return "\nID : " + ticketID +
                "\nTitre : " + title +
                "\nDescription : " + description +
                "\nStatut : " + status +
                "\nPriorité : " + priority +
                "\nCréé par : " + creatorName +
                "\nAssigné à : " + adminName +
                "\nDate de création : " + creationDate +
                "\nDernière mise à jour : " + updateDate;
    }

    // Getters
    public int getTicketID() { return ticketID; }
    public int getCreatorID() { return creatorID; }
    public int getAssignedAdminID() { return assignedAdminID; }
    public String getStatus() { return status; }
}
