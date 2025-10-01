import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class TicketInterface extends JFrame {
    private static final long serialVersionUID = 1L;

    private static List<User> users = new ArrayList<>();
    private static List<Admin> admins = new ArrayList<>();
    private static TicketController ticketController;

    public TicketInterface() {
        setTitle("Système de Gestion de Tickets");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        JLabel welcomeLabel = new JLabel("Bienvenue dans le système de tickets", SwingConstants.CENTER);

        JButton userButton = new JButton("Connexion Utilisateur");
        JButton adminButton = new JButton("Connexion Admin");

        panel.add(welcomeLabel);
        panel.add(userButton);
        panel.add(adminButton);

        add(panel);

        // Actions des boutons
        userButton.addActionListener(e -> showUserLogin());
        adminButton.addActionListener(e -> showAdminLogin());
    }

    // --- Connexion et menus ---

    private void showUserLogin() {
        String email = JOptionPane.showInputDialog(this, "Entrez votre email utilisateur :");
        Optional<User> user = users.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst();

        if (user.isPresent()) {
            JOptionPane.showMessageDialog(this, "Bienvenue " + user.get().getName());
            showUserMenu(user.get());
        } else {
            JOptionPane.showMessageDialog(this, "Utilisateur introuvable !");
        }
    }

    private void showAdminLogin() {
        String email = JOptionPane.showInputDialog(this, "Entrez votre email administrateur :");
        Optional<Admin> admin = admins.stream()
                .filter(a -> a.getEmail().equalsIgnoreCase(email))
                .findFirst();

        if (admin.isPresent()) {
            JOptionPane.showMessageDialog(this, "Bienvenue " + admin.get().getName());
            showAdminMenu(admin.get());
        } else {
            JOptionPane.showMessageDialog(this, "Admin introuvable !");
        }
    }

    // --- Menu Utilisateur ---
    private void showUserMenu(User user) {
        String[] options = {"Créer un ticket", "Voir mes tickets", "Ajouter un commentaire", "Déconnexion"};
        int choix;

        do {
            choix = JOptionPane.showOptionDialog(this,
                    "Menu Utilisateur (" + user.getName() + ")",
                    "Utilisateur",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

            switch (choix) {
                case 0 -> creerTicket(user);
                case 1 -> voirMesTickets(user);
                case 2 -> ajouterCommentaire(user);
            }
        } while (choix != 3);
    }

    private void creerTicket(User user) {
        String titre = JOptionPane.showInputDialog(this, "Titre du ticket :");
        String desc = JOptionPane.showInputDialog(this, "Description :");
        String priorite = JOptionPane.showInputDialog(this, "Priorité (Haute/Moyenne/Basse) :");

        if (titre != null && desc != null && priorite != null) {
            ticketController.createTicket(titre, desc, priorite, user.getUserID());
            JOptionPane.showMessageDialog(this, "Ticket créé avec succès !");
        }
    }

    private void voirMesTickets(User user) {
        List<Ticket> tickets = ticketController.getUserTickets(user.getUserID());
        StringBuilder sb = new StringBuilder("Vos tickets :\n");
        for (Ticket t : tickets) {
            sb.append("ID ").append(t.getTicketID())
              .append(" - ").append(t.getTitle())
              .append(" [").append(t.getStatus()).append("]\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    private void ajouterCommentaire(User user) {
        String idStr = JOptionPane.showInputDialog(this, "ID du ticket :");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                String commentaire = JOptionPane.showInputDialog(this, "Entrez votre commentaire :");

                if (commentaire != null && !commentaire.isBlank()) {
                    boolean success = ticketController.addCommentToTicket(id, commentaire, user.getUserID());
                    if (success) {
                        JOptionPane.showMessageDialog(this, "Commentaire ajouté avec succès.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Échec : ticket introuvable ou non autorisé.");
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID invalide.");
            }
        }
    }

    // --- Menu Admin ---
    private void showAdminMenu(Admin admin) {
        String[] options = {"Voir tous les tickets", "Assigner un ticket", "Mettre à jour un statut",
                            "Fermer un ticket", "Voir mes tickets assignés", "Déconnexion"};
        int choix;

        do {
            choix = JOptionPane.showOptionDialog(this,
                    "Menu Admin (" + admin.getName() + ")",
                    "Administrateur",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

            switch (choix) {
                case 0 -> voirTousLesTickets();
                case 1 -> assignerTicket(admin);
                case 2 -> mettreAJourStatut();
                case 3 -> fermerTicket(admin);
                case 4 -> voirTicketsAssignes(admin);
            }
        } while (choix != 5);
    }

    private void voirTousLesTickets() {
        List<Ticket> tickets = ticketController.getAllTickets();
        StringBuilder sb = new StringBuilder("Tous les tickets :\n");
        for (Ticket t : tickets) {
            sb.append("ID ").append(t.getTicketID())
              .append(" - ").append(t.getTitle())
              .append(" [").append(t.getStatus()).append("]\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    private void assignerTicket(Admin admin) {
        String ticketIdStr = JOptionPane.showInputDialog(this, "ID du ticket à assigner :");
        if (ticketIdStr == null) return;

        String adminIdStr = JOptionPane.showInputDialog(this, "ID de l'Admin assigné :");
        if (adminIdStr == null) return;

        try {
            int ticketID = Integer.parseInt(ticketIdStr);
            int adminID = Integer.parseInt(adminIdStr);

            boolean success = ticketController.assignTicket(ticketID, adminID);
            if (success) {
                JOptionPane.showMessageDialog(this, "Ticket " + ticketID + " assigné à Admin " + adminID);
            } else {
                JOptionPane.showMessageDialog(this, "Échec de l'assignation (ticket introuvable ou déjà assigné).");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID invalide !");
        }
    }

    private void mettreAJourStatut() {
        String ticketIdStr = JOptionPane.showInputDialog(this, "ID du ticket à mettre à jour :");
        if (ticketIdStr == null) return;

        String statut = JOptionPane.showInputDialog(this, "Nouveau statut (OUVERT/ASSIGNE/VALIDATION/TERMINE) :");
        if (statut == null || statut.isBlank()) return;

        try {
            int ticketID = Integer.parseInt(ticketIdStr);
            boolean success = ticketController.updateTicketStatus(ticketID, statut.toUpperCase());
            if (success) {
                JOptionPane.showMessageDialog(this, "Statut mis à jour !");
            } else {
                JOptionPane.showMessageDialog(this, "Échec : ticket introuvable.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID invalide !");
        }
    }

    private void fermerTicket(Admin admin) {
        String ticketIdStr = JOptionPane.showInputDialog(this, "ID du ticket à fermer :");
        if (ticketIdStr == null) return;

        try {
            int ticketID = Integer.parseInt(ticketIdStr);
            boolean success = ticketController.closeTicket(ticketID);
            if (success) {
                JOptionPane.showMessageDialog(this, "Ticket " + ticketID + " fermé.");
            } else {
                JOptionPane.showMessageDialog(this, "Échec : ticket introuvable.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID invalide !");
        }
    }

    private void voirTicketsAssignes(Admin admin) {
        List<Ticket> tickets = ticketController.getAllTickets();
        StringBuilder sb = new StringBuilder("Tickets assignés à " + admin.getName() + " :\n");
        for (Ticket t : tickets) {
            if (t.getAssignedAdminID() == admin.getAdminID()) {
                sb.append("ID ").append(t.getTicketID())
                  .append(" - ").append(t.getTitle())
                  .append(" [").append(t.getStatus()).append("]\n");
            }
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    // --- Main ---
    public static void main(String[] args) {
        // Initialisation exemple
        users.add(new User(1, "Alice", "alice@mail.com", "DEV"));
        users.add(new User(2, "Bob", "bob@mail.com", "DEV"));
        admins.add(new Admin(1, "Admin1", "admin1@mail.com"));

        ticketController = new TicketController(1, users, admins);

        SwingUtilities.invokeLater(() -> new TicketInterface().setVisible(true));
    }
}
