import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<User> users = new ArrayList<>();
    static List<Admin> admins = new ArrayList<>();
    static List<Ticket> tickets = new ArrayList<>();
    static int ticketCounter = 1;

    public static void main(String[] args) {
        // Création de comptes
        users.add(new User(1, "Alice", "alice@mail.com", "DEV"));
        users.add(new User(2, "Bob", "bob@mail.com", "DEV"));
        users.add(new User(3, "Charlie", "charlie@mail.com", "DEV"));

        admins.add(new Admin(1, "Admin1", "admin1@mail.com"));
        admins.add(new Admin(2, "Admin2", "admin2@mail.com"));

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
                    User user = loginUser();
                    if (user != null) {
                        userMenu(user);
                    }
                }
                case 2 -> {
                    Admin admin = loginAdmin();
                    if (admin != null) {
                        adminMenu(admin);
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

    // ================= CONNEXION UTILISATEUR =================
    private static User loginUser() {
        System.out.print("Entrez votre email utilisateur : ");
        String email = scanner.nextLine();

        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Bienvenue " + u.getName() + " !");
                return u;
            }
        }
        System.out.println("Utilisateur introuvable.");
        return null;
    }

    // ================= CONNEXION ADMIN =================
    private static Admin loginAdmin() {
        System.out.print("Entrez votre email administrateur : ");
        String email = scanner.nextLine();

        for (Admin a : admins) {
            if (a.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Bienvenue " + a.getName() + " !");
                return a;
            }
        }
        System.out.println("Administrateur introuvable.");
        return null;
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
                    Ticket t = new Ticket(ticketCounter++, titre, desc, "OUVERT", "Moyenne", user.getUserID());
                    user.addTicketID(t.getTicketID()); // on lie par ID
                    user.createTicket(t);
                    //tickets.add(t);
                    System.out.println("Ticket créé avec ID " + t.getTicketID());
                }
                case 2 -> {
                    for (Ticket t : tickets) {
                        if (user.getTicketsID().contains(t.getTicketID())) {
                            System.out.println(t);
                        }
                    }
                }
                case 3 -> {
                    System.out.print("ID du ticket : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Optional<Ticket> ticket = findTicket(id);
                    if (ticket.isPresent() && user.getTicketsID().contains(id)) {
                        System.out.print("Commentaire : ");
                        String c = scanner.nextLine();
                        ticket.get().addComment(c, user.getUserID());
                    } else {
                        System.out.println("Ticket introuvable ou non autorisé.");
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
                    for (Ticket t : tickets) {
                        System.out.println(t);
                    }
                }
                case 2 -> {
                    System.out.print("ID du ticket à assigner : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Optional<Ticket> ticket = findTicket(id);
                    if (ticket.isPresent()) {
                        System.out.print("ID de l'Admin assigné : ");
                        int idAdmin = scanner.nextInt();
                        scanner.nextLine();
                        Optional<Admin> adminATrouver = findAdmin(idAdmin);
                        if(adminATrouver.isPresent()) {
                            admin.assignTicket(ticket.get(), admins.get(idAdmin - 1)); // on marque comme assigné
                            //admin.addTicketID(id); // admin stocke juste l’ID
                            System.out.println("Ticket " + id + " assigné à " + idAdmin);
                        }
                        else{
                            System.out.println("Admin introuvable.");
                        }
                    } else {
                        System.out.println("Ticket introuvable.");
                    }
                }
                case 3 -> {
                    System.out.print("ID du ticket à mettre à jour : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    Optional<Ticket> ticket = findTicket(id);
                    if (ticket.isPresent()) {
                        System.out.print("Nouveau statut (OUVERT/ASSIGNE/VALIDATION/TERMINE): ");
                        String s = scanner.nextLine();
                        ticket.get().updateStatus(s);
                    } else {
                        System.out.println("Ticket introuvable.");
                    }
                }
                case 4 -> {
                    System.out.print("ID du ticket à fermer : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    //Optional<Ticket> ticket = tickets.stream()
                      //      .filter(t -> t.getTicketID() == id)
                        //    .findFirst();
                    Optional<Ticket> ticket = findTicket(id);
                    if (ticket.isPresent()) {
                        admin.closeTicket(ticket.get());
                        System.out.println("Ticket fermé.");
                    } else {
                        System.out.println("Ticket introuvable.");
                    }
                }
                case 5 -> {
                    for (Ticket t : tickets) {
                        if (admin.getTicketsID().contains(t.getTicketID())) {
                            System.out.println(t);
                        }
                    }
                }
                case 6 -> { return; }
                default -> System.out.println("Choix invalide.");
            }
        }
    }

    public static Optional<Ticket> findTicket(int ticketID){
        Optional<Ticket> ticket = tickets.stream()
                .filter(t -> t.getTicketID() == ticketID)
                .findFirst();
        return ticket;
    }

    public static Optional<Admin> findAdmin(int adminID){
        Optional<Admin> ticket = admins.stream()
                .filter(t -> t.getAdminID() == adminID)
                .findFirst();
        return ticket;
    }
}
