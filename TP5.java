
// 1. Classe d’exception métier : SupportInvalideException
class SupportInvalideException extends Exception {
    public SupportInvalideException(String message) {
        super(message);
    }
}

// 2. Classe métier : SupportDeCours
class SupportDeCours {
    protected int id;
    protected String intitule;
    protected int duree;

    public SupportDeCours(int id, String intitule, int duree) throws SupportInvalideException {
        if (id <= 0) {
            throw new SupportInvalideException("L'identifiant doit être supérieur à zéro.");
        }
        if (intitule == null || intitule.trim().isEmpty()) {
            throw new SupportInvalideException("L'intitulé ne peut pas être nul ou vide.");
        }
        if (duree <= 0) {
            throw new SupportInvalideException("La durée doit être supérieure à zéro.");
        }

        // if (id <= 0 || intitule == null || intitule.trim().isEmpty() || duree <= 0) {
        //     throw new SupportInvalideException("Données du support invalides");
        // }

        this.id = id;
        this.intitule = normalizeIntitule(intitule);
        // intitule = intitule.trim().toLowerCase();
        // this.intitule = intitule.substring(0, 1).toUpperCase() + intitule.substring(1);
        this.duree = duree;
    }

    private String normalizeIntitule(String intitule) {
        String trimmed = intitule.trim();
        return trimmed.substring(0, 1).toUpperCase() + trimmed.substring(1).toLowerCase();
    }

    public void afficher() {
        System.out.println("ID: " + id + "\nIntitulé: " + intitule + "\nDurée: " + duree + " h");
    }

    public void modifierDuree(int nouvelleDuree) {
        if (nouvelleDuree <= 0) {
            throw new IllegalArgumentException("La nouvelle durée doit être supérieure à zéro.");
        }
        this.duree = nouvelleDuree;
    }
}

//3. Interface Accessible
interface Accessible {
    String getLienAcces();
}

// 
// class SupportDeCoursAccessible extends SupportDeCours implements Accessible {
//     public SupportDeCoursAccessible(int id, String intitule, int duree) throws SupportInvalideException {
//         super(id, intitule, duree);
//     }

//     @Override
//     public String getLienAcces() {
//         return "https://cours.univ.ma/" + this.id;
//     }
// }

// 4. Classe CoursEnLigne
class CoursEnLigne extends SupportDeCours implements Accessible {
    public CoursEnLigne(int id, String intitule, int duree) throws SupportInvalideException {
        super(id, intitule, duree);
    }

    @Override
    public String getLienAcces() {
        return "https://cours.univ.ma/" + this.id;
    }

    @Override
    public void afficher() {
        super.afficher();
        System.out.println("Lien d'accès: " + getLienAcces());
    }
}

//6. Classe principale
public class TP5{
    public static void main(String args[]){
        // =====================
        // Scénario 1 : Nominal
        // =====================
        System.out.println("Creation du support...");
        try {
            CoursEnLigne cours = new CoursEnLigne(101, "  programmation JAVA  ", 10);
            System.out.println("Support créé avec succès");
            cours.afficher();
            // System.out.println("Lien : " + cours.getLienAcces());
        } catch (SupportInvalideException e) {
            System.out.println("Erreur lors de la création du support : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Une erreur inattendue est survenue : " + e.getMessage());
        } finally {
            System.out.println("Fin du programme");
        }

        System.out.println("\n=====================");

        // =====================
        // Scénario 2 : Erreur métier
        // =====================
        try {
            System.out.println("Création du support...");
            CoursEnLigne coursInvalide = new CoursEnLigne(0, "", -5);
        } catch (SupportInvalideException e) {
            System.out.println("Erreur métier : données du support invalides");
        } finally {
            System.out.println("Fin du programme");
        }

        System.out.println("\n=====================\n");

        // =====================
        // Scénario 3 : Erreur prédéfinie
        // =====================
        try {
            System.out.println("Création du support...");
            CoursEnLigne cours = new CoursEnLigne(102, "reseaux", 8);
            System.out.println("Support créé avec succès");

            // Provoquer IllegalArgumentException
            cours.modifierDuree(0);

        } catch (SupportInvalideException e) {
            System.out.println("Erreur métier : " + e.getMessage());
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("Erreur standard : argument invalide");
        } finally {
            System.out.println("Fin du programme");
        }
    }
}