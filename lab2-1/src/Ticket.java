import java.util.*;
import java.io.File;


public class Ticket
{

int ticketID;

String title;
String description;
File mediaFile;
String status;
String priority;

Date creationDate;
Date updateDate;

private Admin admin;




public Ticket(int ticketID , String title, String description, String status, String priority, Date creationDate, Date updateDate)
{
creationDate = new Date();
}


void assignTo(Admin admin)
{
    if (status.compareTo("OUVERT")==0)
    {
        admin = this.admin;
    }
    else
    {
    System.out.println("Le ticket doit être ouvert pour être assigné");
    }

}

void updateStatus(String status)
{
    this.status = status;
}

void addComment(String comment, User user)
{
     void addComment(String comment, File mediaFile)
    
    this.description = " Commentaire : " + comment;
    this.mediaFile = mediaFile;
    updateTicket(this)
}

@Override
public String toString()
{
return "ID : " + ticketID  + "\n" + " Titre :" + title + "\n" + " Description : " + description  + "\n" + " Statut " + status + "\n" + " Priorité : " + priority  + "\n" + " Date de création : " + creationDate + "\n" + " Dernière mise a jour : " + updateDate;   
}
public int getTicketID() {
    return ticketID;
}
public void setTicketID(int ticketID) {
    this.ticketID = ticketID;
}
public String getTitle() {
    return title;
}
public void setTitle(String title) {
    this.title = title;
}
public String getDescription() {
    return description;
}
public void setDescription(String description) {
    this.description = description;
}
public String getStatus() {
    return status;
}
public void setStatus(String status) {
    this.status = status;
}
public String getPriority() {
    return priority;
}
public void setPriority(String priority) {
    this.priority = priority;
}
public Date getCreationDate() {
    return creationDate;
}
public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
}
public Date getUpdateDate() {
    return updateDate;
}
public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
}
}
