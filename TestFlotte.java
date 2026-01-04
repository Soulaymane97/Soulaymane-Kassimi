
// Créer une classe abstraite Vehicule avec les attributs suivants :
abstract class Vehicule{
    String immatriculation;
    String marque;

    // Constructeur d’initialisation
    public Vehicule(String immatriculation, String marque) {
        this.immatriculation = immatriculation;
        this.marque = marque;
    }

    // Méthode concrète afficherInfos() affichant les deux attributs,
    public void afficherInfos() {
        System.out.println("Immatriculation: " + immatriculation);
        System.out.println("Marque: " + marque);
    }

    // Méthode abstraite double calculerAutonomie().
    public abstract double calculerAutonomie();
}

// 2. Interface Connectable
interface Connectable {
    default void connecterServeur(){
        System.out.println("Connecté au serveur central.....");
    }
    default void envoyerDiagnostic(){
        System.out.println("Diagnostic envoyé au serveur.");
    }
}

// Interface Autonome
interface Autonome {
    default void activerPilotageAuto(){
        System.out.println("Pilotage automatique activé.");
    }
    default int niveauAutonomie(){
        return 5;
    }
}

//  Classe VoitureThermique
class VoitureThermique extends Vehicule {
    // — capaciteReservoir : double
    double capaciteReservoir;
    double consommationMoyenne;

    // Constructeur complet
    public VoitureThermique(String immatriculation, String marque, double capaciteReservoir, double consommationMoyenne) {
        super(immatriculation, marque);
        this.capaciteReservoir = capaciteReservoir;
        this.consommationMoyenne = consommationMoyenne;
    }

    // Redéfinition de afficherInfos() pour ajouter les détails du véhicule thermique.
    @Override
    public void afficherInfos() {
        super.afficherInfos();
        System.out.println("Capacité du réservoir: " + capaciteReservoir + " litres");
        System.out.println("Consommation moyenne: " + consommationMoyenne + " L/100km");
    }

    // Redéfinition de calculerAutonomie() :
    @Override
    public double calculerAutonomie() {
        return (capaciteReservoir / consommationMoyenne) * 100;
    }

    // Implémentation de connecterServeur() et envoyerDiagnostic()
    public void connecterServeur(){
        System.out.println("Voiture Thermique connectée au serveur central.....");
    }

    public void envoyerDiagnostic(){
        System.out.println("Diagnostic de la Voiture Thermique envoyé au serveur.");
    }
    
}

// Classe VoitureElectrique
class VoitureElectrique extends Vehicule implements Connectable {
    double capaciteBatterie;
    double consoKWhPar100Km;

    // Constructeur complet
    public VoitureElectrique(String immatriculation, String marque, double capaciteBatterie, double consoKWhPar100Km) {
        super(immatriculation, marque);
        this.capaciteBatterie = capaciteBatterie;
        this.consoKWhPar100Km = consoKWhPar100Km;
    }

    // Redéfinition de afficherInfos() pour ajouter les détails du véhicule Electrique.
    @Override
    public void afficherInfos() {
        super.afficherInfos();
        System.out.println("Capacité de la batterie: " + capaciteBatterie + " kWh");
        System.out.println("Consommation moyenne: " + consoKWhPar100Km + " kWh/100km");
    }

    // Redéfinition de calculerAutonomie() 
    @Override
    public double calculerAutonomie() {
        return (capaciteBatterie / consoKWhPar100Km) * 100;
    }

    // Implémentation de connecterServeur() et envoyerDiagnostic().
    public void connecterServeur(){
        System.out.println("Voiture Electrique connectée au serveur central.....");
    }
    public void envoyerDiagnostic(){
        System.out.println("Diagnostic de la Voiture Electrique envoyé au serveur.");
    }
}

// Classe CamionAutonomeConnecte
class CamionAutonomeConnecte extends Vehicule implements Connectable, Autonome {
   double capaciteReservoir;
   int niveauIA;

    // Constructeur complet
    public CamionAutonomeConnecte(String immatriculation, String marque, double capaciteReservoir, int niveauIA) {
         super(immatriculation, marque);
         this.capaciteReservoir = capaciteReservoir;
         this.niveauIA = niveauIA;
    }
    // Redéfinition de afficherInfos() pour ajouter les détails du Camion Autonome Connecté.
    @Override
    public void afficherInfos() {
        super.afficherInfos();
        System.out.println("Capacité du réservoir: " + capaciteReservoir + " litres");
        System.out.println("Niveau d'IA: " + niveauIA);
    }

    //  Redéfinition de calculerAutonomie() (modèle simple)
    @Override
    public double calculerAutonomie() {
        return capaciteReservoir * 3 * (1 + 0.02 * niveauIA);
    }

    // mplémentation des méthodes de Connectable,
    public void connecterServeur(){
        System.out.println("Camion Autonome Connecté au serveur central.....");
    }

    // mplémentation des méthodes de Autonome.
    public void envoyerDiagnostic(){
        System.out.println("Diagnostic du Camion Autonome Connecté envoyé au serveur.");
    }

}

public class TestFlotte {
    public static void main(String[] args) {
        // 1 . Creation d’un tableau de VVehicule
        Vehicule[] flotte = new Vehicule[] {
            new VoitureThermique("1234-AB-01" , " Peugeot " , 50.0 , 6.5 ) ,
            new VoitureElectrique ("5678-CD-02", " Renault ", 40.0, 15.0) ,
            new CamionAutonomeConnecte ( "9012-EF-03" , " Volvo " , 300.0 , 4 )
        } ;
        // 2 . Parcours du t a b l e a u
        for ( Vehicule v : flotte ) {
            System.out.println( "−−−−−−−−−−−−−−−−−−−−−−−−−−−−−" ) ;
            
            // a ) Afficher les informations du vehicule
            v.afficherInfos();
            
            // b ) Afficher l’autonomie calculee
            System.out.println ( " Autonomie estimee : " + v.calculerAutonomie() + " km" ) ;
            
            // c ) Verifier si le vehicule est Connectable
            if ( v instanceof Connectable ) {
                Connectable c = ( Connectable ) v ;
                c.connecterServeur();
                c.envoyerDiagnostic();
            }

            // d ) Verifier si le vehicule est Autonome
            if ( v instanceof Autonome ) {
                Autonome a = ( Autonome ) v ;
                a.activerPilotageAuto ( ) ;
                System.out.println ( " Niveau autonomie : "+ a.niveauAutonomie()) ;
            }
        }    
    }
}

