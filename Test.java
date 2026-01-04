
class Personne{
    protected String nom;
    String prenom;
    int age;

    // constructeur sans parametre
    public Personne(){}
    // constructeur avec parametres
    public Personne(String nom, String prenom, int age){
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    // methode pour afficher les informations de la personne
    public String toString(){
        return "Nom: " + nom + ", Prenom: " + prenom + ", Age: " + age;
    }


    // Methode egales pour comparer deux personnes
    // 1 methode
    public boolean equals(Object obj){
        if(!(obj instanceof Personne))
            return false;
        Personne p = (Personne) obj;
        // return this.nom.equals(p.nom) && this.prenom.equals(p.prenom);
        return p.nom.equals(nom) && p.prenom.equals(prenom);

    }

    // 2 methode
    // static boolean equals(Personne p1, Personne p2){
    //     return p1.nom.equals(p2.nom) && p1.prenom.equals(p2.prenom) && p1.age == p2.age;  
    // }

    // 3 methode
    // public boolean equals(Personne o){
    //     return this.nom.equals(o.nom) && this.prenom.equals(o.prenom) && this.age == o.age;

}

// Sous classe Etudiant qui herite de la classe Personne
class Etudiant extends Personne{
    String cne, filiere;

    // constructeur sans parametre
    Etudiant(){}
    // constructeur avec parametres
    Etudiant(String nom, String prenom, int age, String cne, String filiere){
        // this(); // appel du constructeur sans parametre (Etudiant())
        super(nom, prenom, age); // appel du constructeur de la classe mere
        this.cne = cne;
        this.filiere = filiere;
    }

    // redefinition de la methode toString
    public String toString(){
        return super.toString() + ", CNE: " + cne + ", Filiere: " + filiere;
        // remarque
        // return "Nom: " + nom + ", Prenom: " + prenom + ", Age: " + age + ", CNE: " + cne + ", Filiere: " + filiere;
    }

    // redefinition de la methode equals
    public boolean equals(Object obj){
        if(!(obj instanceof Etudiant))
            return false;
        Etudiant e = (Etudiant) obj;
        // return super.equals(e) && this.cne.equals(e.cne);
        // return e.nom.equals(nom) && e.prenom.equals(prenom) && e.cne.equals(cne);
        return e.nom.equals(cne);
    }

}

// Sous classe Enseignant qui herite de la classe Personne
class Enseignant extends Personne{
    String grade, specialite;

    // constructeur avec parametre
    Enseignant(String n, String p, int a, String g, String sp){
        super(n, p, a);
        this.grade = g;
        this.specialite = sp;
    }


    // redefinition de la methode toString
    public String toString(){
        return super.toString() + ", Grade: " + grade + ", Specialite: " + specialite;
    }

    // redefinition de la methode equals
    public boolean equals(Object obj){
        if(!(obj instanceof Enseignant))
            return false;
        Enseignant e = (Enseignant) obj;
        
        return super.equals(e) && this.grade.equals(e.grade) && this.specialite.equals(e.specialite);
        // return e.nom.equals(n) && e.prenom.equals(p) && e.grade.equals(g) && e.specialite.equals(sp);
    }   
}


class Filiere{
    String nom;
    // int duree; // en annees
    Enseignant responsable;
    Etudiant[] etudiants;
    int nombreEtudiants;

    // constructeur avec parametres
    Filiere(String nom, Enseignant resp, int N){
        this.nom = nom;
        this.responsable = resp;
        this.etudiants = new Etudiant[N]; 
        this.nombreEtudiants = 0;
    }

    // methode pour ajouter un etudiant
    public void ajouterEtudiant(Etudiant e){
        if(nombreEtudiants < etudiants.length){
            // etudiants[nombreEtudiants++] = e;
            etudiants[nombreEtudiants] = e;
            nombreEtudiants++;
        }
        else
            System.out.println("Capacite maximale atteinte!");

    }

    // methode pour afficher Etudiants
    public void afficherEtudiants(){
        System.out.println("Etudiants de la filiere " + nom + ":");
        
        // for(int i=0; i<nombreEtudiants; i++){
        //     System.out.println(etudiants[i]);
        // }

        // ou
        
        // for(Etudiant e : etudiants){
        //     if(e != null)
        //         System.out.println(e);
        // }

        // ou
        for(Etudiant e : etudiants){
            System.out.println(e.toString());
        }
    }

    // methode pour rechercher un Etudiant par son CNE
    public Etudiant rechercherEtudiantParCNE(String cne){
        for(Etudiant e : etudiants){
            if(e != null && e.cne.equals(cne))
                return e;
        }
        return null;
    }

    // methode toString
    public String toString(){
        return "Filiere: " + nom + ", Responsable: " + responsable.nom + " " + responsable.prenom + ", Nombre d'etudiants: " + nombreEtudiants;
    }
}

// 
class Module{
    String intitule;
    Enseignant professeur;
    int coefficient;

    // constructeur avec parametres
    Module(String intitule, Enseignant prof, int coeff){
        this.intitule = intitule;
        this.professeur = prof;
        this.coefficient = coeff;
    }

    // methode toString
    public String toString(){
        return "Module: " + intitule + ", Professeur: " + professeur.nom + " " + professeur.prenom + ", Coefficient: " + coefficient;
    }
}

public class Test {
    public static void main(String[] args) {
        // creation d'un enseignant
        Enseignant ens1 = new Enseignant("alaoui", "nazih", 45, "Professeur", "Informatique");
        Enseignant ens2 = new Enseignant("fergougi", "mostapha", 45, "Professeur", "reseaux");
        System.out.println(ens1);
        System.out.println(ens2);

        // creation d'une filiere
        Filiere filiereInfo = new Filiere("Informatique", ens1, 3);

        // creation des etudiants
        Etudiant etu1 = new Etudiant("kassimi", "Soulaymane", 20, "CNE123", "Informatique");
        Etudiant etu2 = new Etudiant("hamidi", "amine", 22, "CNE456", "Informatique");

        // ajout des etudiants a la filiere
        filiereInfo.ajouterEtudiant(etu1);
        filiereInfo.ajouterEtudiant(etu2);

        // affichage des etudiants de la filiere
        filiereInfo.afficherEtudiants();

        // recherche d'un etudiant par CNE
        Etudiant rechercheEtu = filiereInfo.rechercherEtudiantParCNE("CNE123");
        if(rechercheEtu != null){
            System.out.println("Etudiant trouve: " + rechercheEtu);
        } else {
            System.out.println("Etudiant non trouve.");
        }       

    }
}