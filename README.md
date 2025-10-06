![UML](https://github.com/Marshmerico/Laboratoires-Architecture-des-logiciels/blob/main/UML.png)
# Section I
## Critique du diagramme de classe initial

Bien que le squelette du programme soit fourni dans le diagramme de classe, les fonctionnalités restent basiques et quelques fonctionnalités ne sont pas incluses.

Certaines méthodes comme `assignTo()` et `assignTicket` assignent au `User`, alors qu'**aucun utilisateur ne devrait avoir un ticket qui lui est assigné**. De plus, certaines fonctions décrites dans le résumé ne sont pas présentes dans le diagramme de classe.

Parmi ces fonctions absentes :
- La plateforme doit permettre à l'utilisateur de fournir des **captures d'écran ou de vidéos** dans les commentaires
- On doit pouvoir **consulter ces informations** dans la plateforme
- On doit pouvoir **exporter en format PDF**

---

# Section II
## Améliorations apportées au diagramme et justifications

| Modification | Justification |
| :--- | :--- |
| La méthode `AssignTicket()` et `AssignTo` prennent maintenant `Admin` en entrée au lieu de `User` | `User` en entrée n'avait pas de sens, les tickets sont assignés aux `Admin` et non aux `Users` |
| Ajout de `fichdescription` en entrée pour la méthode `addComment()` | Pour la partie deux du laboratoire, il faut permettre à l'utilisateur d'ajouter des vidéos ou photos à son commentaire |
| Création de la classe `TicketController` selon le patron de conception **Controller** | Étant donné que plusieurs entités doivent interagir avec les tickets, créer une classe qui s'occupe spécifiquement de gérer les tickets augmente la **cohésion** du projet et des classes. Cela réduit également le nombre de fonctions dans les classes `Admin` et `User` |
| Ajout de la classe `isStatusUpdateLegal()` dans la classe `Ticket` | Vérifie si le changement de statut est adéquat (ex: ne pas pouvoir passer de `TERMINÉ` à `ASSIGNÉ`) |
| Ajout d'une liste de `ticketID` à la classe `User` | Permet plus rapidement de trouver les Tickets associés à un `User`. Très utile pour afficher tous les tickets d'un utilisateur |
| Ajout d'une liste de `ticketID` à la classe `Admin` | Permet plus rapidement de trouver les Tickets associés à un `Admin`. Très utile pour afficher tous les tickets d'un administrateur |

---

# Section III
## Autres améliorations apportées au code et à la conception

### Optimisation de l'affichage
La méthode `viewAllTickets()` sort la **liste des ID** puis ensuite demande si on veut voir plus en détail. Cela permet d'éviter d'afficher tous les tickets et d'envahir la fenêtre de terminal avec beaucoup de texte inutile.

### Implémentation du Pattern Controller
La classe `TicketController` a été ajoutée pour suivre le **patron de conception Controller**. Cela permet de :

- Limiter la propagation des `Ticket`
- Éviter de dupliquer la liste contenant tous les tickets
- Centraliser l'accès, la création et la modification des tickets

> **Note** : Cette amélioration peut sembler négligeable, mais dans un système contenant des centaines d'utilisations et des milliers de tickets, cela améliore les performances et la qualité du système.

### Gestion des pièces jointes
Des modifications ont été apportées dans la fonction `addComment()`, pour qu'il soit désormais possible de mettre des **fichiers en pièce jointe** dans les commentaires des tickets.

### Validation des transitions d'état
Un système de vérification a été ajouté à la classe `Ticket` pour s'assurer que le changement de statut se fasse dans un ordre cohérent : Ouvert → Assignée → Validation → Terminée

---

# Section IV
## Leçons apprises de ce laboratoire

### Conception initiale
La leçon principale est l'importance de **faire un excellent diagramme de classe** et bien designer ses classes dès la conception. Bien choisir les patrons de conception permet de limiter les changements de direction du projet et les modifications lors de la programmation.

> **Attention** : De mauvais diagrammes de classe peuvent faire perdre beaucoup de temps à refaire certaines parties du code.

### Architecture et organisation
- **Délégation des responsabilités** : Relayer certaines tâches à un controller permet de simplifier la compréhension et la complexité du programme
- **Structure des profils** : Nos profils étaient distinctement `Admin` et `User` alors qu'ils auraient pu être groupés et avoir accès à certaines méthodes individuellement

### Bilan global
Le laboratoire a renforcé notre capacité à **optimiser et traduire efficacement** des diagrammes de classe en programme fonctionnel.
