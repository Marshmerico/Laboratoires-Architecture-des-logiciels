import java.util.*;

public class Ticket
{

int ticketID;

String title;
String description;
String status;
String priority;

Date creationDate;
Date updateDate;

public Ticket(int ticketID , String title, String description, String status, String priority, Date creationDate, Date updateDate)
{
creationDate = new Date();
}


void assignTo(Admin admin)
{
    if (status.compareTo("OUVERT")==0)
    {
        this.admin = admin;
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

void addComment(String comment)
{
    this.description = description + " Commentaire : " + comment;
    updateTicket(this)
}

@Override
public String toString()
{
return "ID : " + ticketID  + "\n" + " Titre :" + title + "\n" + " Description : " + description  + "\n" + " Statut " + status + "\n" + " Priorité : " + priority  + "\n" + " Date de création : " + creationDate + "\n" + " Dernière mise a jour : " + updateDate;   
}
}
