import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<User> users = new ArrayList<>();
    static List<Admin> admins = new ArrayList<>();
    static int ticketCounter = 1;
    static TicketController ticketController;

    public static void main(String[] args) {
        // Initialisation des données
        initializeData();

        // Création du contrôleur
        ticketController = new TicketController(ticketCounter);

        System.out.println("===== Système de Gestion de Tickets =====");

        while (true) {
            System.out.println("\nConnexion :");
            System.out.println("1. Utilisateur");
            System.out.println("2. Administrateur");
            System.out.println("3. Quitter");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> {
                    Optional<User> user = loginUser();
                    if (user.isPresent()) {
                        userMenu(user.get());
                    }
                }
                case 2 -> {
                    Optional<Admin> admin = loginAdmin();
                    if (admin.isPresent()) {
                        adminMenu(admin.get());
                    }
                }
                case 3 -> {
                    System.out.println("Fin du programme.");
                    return;
                }
                default -> System.out.println("Choix invalide.");
            }
        }
    }

    private static void initializeData() {
        users.add(new User(1, "Alice", "alice@mail.com", "DEV"));
        users.add(new User(2, "Bob", "bob@mail.com", "DEV"));
        users.add(new User(3, "Charlie", "charlie@mail.com", "DEV"));

        admins.add(new Admin(1, "Admin1", "admin1@mail.com"));
        admins.add(new Admin(2, "Admin2", "admin2@mail.com"));
    }

    public static Optional<User> getUserByID(int userID) {
        return users.stream()
                .filter(u -> u.getUserID() == userID)
                .findFirst();
    }

    public static Optional<Admin> findAdmin(int adminID) {
        return admins.stream()
                .filter(a -> a.getAdminID() == adminID)
                .findFirst();
    }

    // ================= CONNEXION UTILISATEUR =================
    private static Optional<User> loginUser() {
        System.out.print("Entrez votre email utilisateur : ");
        String email = scanner.nextLine();

        Optional<User> user = users.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst();

        if (user.isPresent()) {
            System.out.println("Bienvenue " + user.get().getName() + " !");
        } else {
            System.out.println("Utilisateur introuvable.");
        }
        return user;
    }

    // ================= CONNEXION ADMIN =================
    private static Optional<Admin> loginAdmin() {
        System.out.print("Entrez votre email administrateur : ");
        String email = scanner.nextLine();

        Optional<Admin> admin = admins.stream()
                .filter(a -> a.getEmail().equalsIgnoreCase(email))
                .findFirst();

        if (admin.isPresent()) {
            System.out.println("Bienvenue " + admin.get().getName() + " !");
        } else {
            System.out.println("Administrateur introuvable.");
        }
        return admin;
    }

    // ================= MENU UTILISATEUR =================
    private static void userMenu(User user) {
        while (true) {
            System.out.println("\n--- Menu Utilisateur (" + user.getName() + ") ---");
            System.out.println("1. Créer un ticket");
            System.out.println("2. Voir mes tickets");
            System.out.println("3. Ajouter un commentaire");
            System.out.println("4. Déconnexion");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> {
                    System.out.print("Titre : ");
                    String titre = scanner.nextLine();
                    System.out.print("Description : ");
                    String desc = scanner.nextLine();
                    System.out.print("Priorité (Haute/Moyenne/Basse) : ");
                    String priorite = scanner.nextLine();

                    ticketController.createTicket(titre, desc, priorite, user.getUserID());

                }
                case 2 -> {

                    ticketController.getUserTickets(user.getUserID());

                }
                case 3 -> {
                    System.out.print("ID du ticket : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Commentaire : ");
                    String commentaire = scanner.nextLine();

                    System.out.print("Voulez-vous ajouter un fichier? O/N: ");
                    String decisionFichier = scanner.nextLine();
                    File fichDescription = null;
                    if(decisionFichier.compareToIgnoreCase("O") == 0){
                        System.out.print("Fichier : ");
                        String fichier = "";
                        fichier = scanner.nextLine();

                        try{

                            URL url = new URL("file:///" + fichier);
                            URI uri = url.toURI();
                            fichDescription = new File(uri);

                        }
                        catch (Exception e){
                            e.printStackTrace();
                            System.out.println("Le fichier n'existe pas");
                        }

                    }


                    boolean success = ticketController.addCommentToTicket(id, commentaire, fichDescription, user.getUserID());
                    if (success) {
                        System.out.println("Commentaire ajouté avec succès.");
                    } else {
                        System.out.println("Échec : ticket introuvable ou non autorisé.");
                    }
                }
                case 4 -> { return; }
                default -> System.out.println("Choix invalide.");
            }
        }
    }

    // ================= MENU ADMIN =================
    private static void adminMenu(Admin admin) {
        while (true) {
            System.out.println("\n--- Menu Admin (" + admin.getName() + ") ---");
            System.out.println("1. Voir tous les tickets");
            System.out.println("2. Assigner un ticket");
            System.out.println("3. Mettre à jour le statut");
            System.out.println("4. Fermer un ticket");
            System.out.println("5. Voir mes tickets assignés");
            System.out.println("6. Déconnexion");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> {
                    ticketController.getAllTickets();

                }
                case 2 -> {
                    System.out.print("ID du ticket à assigner : ");
                    int ticketID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("ID de l'Admin assigné : ");
                    int adminID = scanner.nextInt();
                    scanner.nextLine();

                    boolean success = ticketController.assignTicket(ticketID, adminID);
                    if (success) {
                        System.out.println("Ticket " + ticketID + " assigné avec succès.");
                    } else {
                        System.out.println("Échec : ticket ou admin introuvable, ou ticket non OUVERT.");
                    }
                }
                case 3 -> {
                    System.out.print("ID du ticket à mettre à jour : ");
                    int ticketID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nouveau statut (OUVERT/ASSIGNE/VALIDATION/TERMINE) : ");
                    String nouveauStatut = scanner.nextLine();

                    boolean success = ticketController.updateTicketStatus(ticketID, nouveauStatut);
                    if (success) {
                        System.out.println("Statut mis à jour avec succès.");
                    } else {
                        System.out.println("Échec : ticket introuvable.");
                    }
                }
                case 4 -> {
                    System.out.print("ID du ticket à fermer : ");
                    int ticketID = scanner.nextInt();
                    scanner.nextLine();

                    boolean success = ticketController.closeTicket(ticketID);
                    if (success) {
                        System.out.println("Ticket fermé avec succès.");
                    } else {
                        System.out.println("Échec : ticket introuvable.");
                    }
                }
                case 5 -> {
                    ticketController.getAdminAssignedTickets(admin.getAdminID());

                }
                case 6 -> { return; }
                default -> System.out.println("Choix invalide.");
            }
        }
    }
}