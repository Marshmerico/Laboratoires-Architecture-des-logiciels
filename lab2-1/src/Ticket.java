import java.io.File;
import java.util.*;

public class Ticket {
    private int ticketID;
    private String title;
    private String description;
    private File fichdescription;
    private String status;
    private String priority;
    private Date creationDate;
    private Date updateDate;
    private int creatorID;
    private int assignedAdminID;

    public Ticket(int ticketID, String title, String description, File fichdescription, String status, String priority, int creatorID) {
        this.ticketID = ticketID;
        this.title = title;
        this.description = description;
        this.fichdescription = fichdescription;
        this.status = status;
        this.priority = priority;
        this.creationDate = new Date();
        this.updateDate = new Date();
        this.creatorID = creatorID;
        this.assignedAdminID = 0;
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

    private boolean isStatusUpdateLegal(String status){
        switch (this.status){
            case "OUVERT" -> {
                return "ASSIGNE".equalsIgnoreCase(status);
            }
            case "ASSIGNE" -> {
                return "VALIDATION".equalsIgnoreCase(status);
            }
            case "VALIDATION" -> {
                return "TERMINE".equalsIgnoreCase(status);
            }
            case "TERMINE" -> {
                return false;
            }
        }
        return false;
    }

    public boolean updateStatus(String status) {
        if (isStatusUpdateLegal(status)) {
            this.status = status;
            this.updateDate = new Date();

            return true;
        }
        return false;
    }

    public void addComment(String comment, int userID, File fichdescription) {
        this.description = this.description + " [Commentaire user#" + userID + " : " + comment + "]";
        this.updateDate = new Date();
        this.fichdescription = fichdescription;
    }

    // AJOUT des getters pour le contrôleur
    public String getStatus() { return status; }
    public int getCreatorID() { return creatorID; }
    public int getAssignedAdminID() { return assignedAdminID; }

    @Override
    public String toString() {
        String creatorName = Main.users.stream()
                .filter(u -> u.getUserID() == creatorID)
                .map(User::getName)
                .findFirst()
                .orElse("inconnu");

        String adminName = (assignedAdminID != 0)
                ? Main.admins.stream()
                .filter(a -> a.getAdminID() == assignedAdminID)
                .map(Admin::getName)
                .findFirst()
                .orElse("inconnu")
                : "aucun";

        String txtFichier = (Objects.nonNull(fichdescription)) ? fichdescription.getName() : "";

        return "\nID : " + ticketID +
                "\nTitre : " + title +
                "\nDescription : " + description +
                "\nFichier : " + txtFichier +
                "\nStatut : " + status +
                "\nPriorité : " + priority +
                "\nCréé par : " + creatorName +
                "\nAssigné à : " + adminName +
                "\nDate de création : " + creationDate +
                "\nDernière mise à jour : " + updateDate;
    }

    public int getTicketID() { return ticketID; }
}