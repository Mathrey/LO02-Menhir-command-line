import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.HashSet;
import java.util.InputMismatchException;

/*
 * Implémentation :
 * 		SINGLETON
 * 		MVC
 * 		Thread
 */

public class Partie  {

	private boolean partieAvancee;
	private ArrayList<Joueur> listeJoueur = new ArrayList<Joueur>();
	private ArrayList<Manche> listeManche = new ArrayList<Manche>();
	
	private int nbJPhysique;
	private int nbJVirtuel;
	private int nbManche = 0;
	private int mancheNumero = 0;
	private Scanner scanChoix;
	
	private int ageJPhysique;
	private String nomJPhysique;
	
	private static Partie partie = null;
	
	public static Partie getInstance(){
		
		if(partie == null){
			partie = new Partie();
		}
		
		return partie;
	}
	
	
	public Partie(){
	/*
		System.out.println("Partie RAPIDE (0) ou avec des REGLES AVANCEES (1) ?");
		int typePartie = -1;
		do {
			try{
				typePartie = getChoix();
			
				if(typePartie == 0){
				this.setDifficulte(typePartie);
				System.out.println("Règles simples.\n");
				
				}else{
					if(typePartie == 1){
						this.setDifficulte(typePartie);
						System.out.println("Règles avancées.\n");
					}else{
						System.out.println("Nombre non valide, veuillez saisir 0 ou 1");
					}
				}
			}catch(InputMismatchException e) {
							System.out.println("Merci d'entrer 0 ou 1.\n");
					}

		}while(typePartie != 0 && typePartie != 1);
		
		setNbrJoueursPhysiques(1);
		setJoueursPhysiques(nbJPhysique);
		//this.nbJPhysique = 1;
		//this.listeJoueur.add(new JoueurPhysique());
		
		System.out.println("Combien y a t'il de joueur(s) virtuel(s) ?");
		do {
			try{
				
				setNbrJoueursVirtuels(getChoix());
			
				setJoueursVirtuels(nbJVirtuel);
				
			}catch(InputMismatchException e) {
				System.out.println("Veuillez entrer un nombre entre 0 et 5.\n");				
			}catch(IndexOutOfBoundsException e) {
				System.out.println("Il doit y avoir au moins 1 et au maximum 5 joueurs virtuels.");
			}
		}while(this.nbJVirtuel <0 && this.nbJVirtuel >5);
			
		for (Iterator<Joueur> it2 = this.listeJoueur.iterator(); it2.hasNext();) {
			Joueur lJoueur = it2.next();
			lJoueur.setNbGraineDuJoueur(2);			
		}
	 
		
		this.setGrainesJoueurs();
	 	*/
	}
		

	public int getNbJPhysique(){
		return this.nbJPhysique;
	}
	
	public int getNbJVirtuel(){
		return this.nbJVirtuel;
	}

	public void lancerPartie() {
		
		if(this.partieAvancee){
			
			this.nbManche = this.nbJPhysique + this.nbJVirtuel;
			for(int i=0; i<this.nbManche; i++){
				this.listeManche.add(new Manche());
			}
			
			for (Iterator<Manche> it = listeManche.iterator(); it.hasNext();) {
				Manche mancheActive = it.next();
				this.mancheNumero++;
				mancheActive.jouerManche();
				System.out.println("La manche est terminée.\n");

				for (Iterator<Joueur> it2 = listeJoueur.iterator(); it2.hasNext();) {
					Joueur joueurActif = it2.next();
					joueurActif.setNbMenhirTotalDuJoueur(joueurActif.getNbMenhirDuJoueur());
					joueurActif.setNbMenhirDuJoueur(0);
				}
				this.afficherClassement();
			}
			
			System.out.println("\nFin de la partie!\nClassement final:\n");
			this.afficherClassement();
		}else{ 
			Manche manche = new Manche();
			manche.jouerManche();
			System.out.println("\nLa partie est terminée.\n");
			this.afficherGagnants();
			}
	}

	public void afficherGagnants() {
		HashSet<Joueur> joueursGagnants = new HashSet<Joueur>();
		int menhirMax = -1;
		int grainesMax = -1;
		for (Iterator<Joueur> it = this.getListeJoueur().iterator(); it.hasNext();) {
			Joueur joueurActif = it.next();
			if (joueurActif.getNbMenhirDuJoueur() >= menhirMax) {
				menhirMax = joueurActif.getNbMenhirDuJoueur();
				grainesMax = joueurActif.getNbGraineDuJoueur();
			}
		}
		
		for (Iterator<Joueur> it = this.getListeJoueur().iterator(); it.hasNext();) {
			Joueur joueurActif = it.next();
			if (joueurActif.getNbMenhirDuJoueur() == menhirMax && joueurActif.getNbGraineDuJoueur() == grainesMax) {
				joueursGagnants.add(joueurActif);
			}
		}
		
		if (joueursGagnants.size() == 1) {
			System.out.println("Le gagnant est: ");
		}else{
			System.out.println("Les gagnants sont: ");
		}
		
		for (Iterator<Joueur> it = joueursGagnants.iterator(); it.hasNext();) {
			Joueur gagnant = it.next();
			System.out.print(gagnant.getNom() + " avec " +gagnant.getNbMenhirDuJoueur()+ " ménhir(s) et " +gagnant.getNbGraineDuJoueur()+ " graine(s).\n");
		}
	}
	
	public void afficherClassement() {
		ArrayList<Joueur> classement = new ArrayList<Joueur>();
		int position = 0;
		int egalite = 0;
		
		for (Iterator<Joueur> it = listeJoueur.iterator(); it.hasNext();) {
			Joueur joueurActif = it.next();
			classement.add(joueurActif);
			Collections.sort(classement);
		}
		
		int nbMenhirsTotal = -1;
		for (Iterator<Joueur> it = classement.iterator(); it.hasNext();){
			Joueur joueurActif = it.next();
			if (nbMenhirsTotal != joueurActif.getNbMenhirTotalDuJoueur()) {
				position = position + 1 + egalite;
				egalite = 0;
				nbMenhirsTotal = joueurActif.getNbMenhirTotalDuJoueur();
			}else{
				egalite++;
			}
			System.out.println(position + ". " +joueurActif.getNom()+ " avec: " +joueurActif.getNbMenhirTotalDuJoueur()+ " menhir(s) au total.");
		}
	}
	
	public boolean getPartieAvancee() {
		return partieAvancee;
	}
	
	public ArrayList<Joueur> getListeJoueur(){
		return listeJoueur;
	}
	
	public int getMancheNumero() {
		return this.mancheNumero;
	}
	
	public int getChoix() {
		scanChoix = new Scanner(System.in);
		int choix = scanChoix.nextInt();
		return choix;
	}
	
	public void setDifficulte(int dif) {
		if (dif == 0) {
			this.partieAvancee = false;
		} else if (dif == 1) {
			this.partieAvancee = true;
		}
	}
	
	public void setNbrJoueursVirtuels(int nbr) {
		this.nbJVirtuel = nbr;
	}
	
	public void setJoueursVirtuels(int nbr) {
		for(int i=1; i<= nbr; i++){
			this.listeJoueur.add(new JoueurVirtuel(i));
		}
	}
	
	public void setNbrJoueursPhysiques(int nbr) {
		this.nbJPhysique = nbr;
	}
	
	public void setJoueursPhysiques(int nbr) {
		for(int i=1; i<= nbr; i++){
			this.listeJoueur.add(new JoueurPhysique());
		}
	}


	public void setAgeJPhysique(int ageJPhysique) {
		
		/*do {
			try {
				ageJPhysique = getChoix();
				if (ageJPhysique < 8) {
					System.out.println("Vous êtes un peu jeune pour jouer! Le jeu du Menhir est pour les 8 ans et plus.\nEntrez un autre âge.\n");
				}
				else{
					if (ageJPhysique > 122) {
						System.out.println("Vous êtes plus vieux que le record de longévité humaine!\nSérieusement, entrez votre VRAI âge. :)\n");
					}
				}
			}catch(InputMismatchException e) {
				System.out.println("Merci d'entrer un nombre entier.\n");
			}
		}while (ageJPhysique < 8 || ageJPhysique > 122);
		*/
		
		this.ageJPhysique = ageJPhysique;
	}


	public void setNomJPhysique(String nomJPhysique) {
		this.nomJPhysique = nomJPhysique;
	}
	
	
	public void setJoueursPhysiques(int age, String nom) {
		this.listeJoueur.add(new JoueurPhysique(age, nom));
	}
	
	public void setGrainesJoueurs() {
		for (Iterator<Joueur> it2 = this.listeJoueur.iterator(); it2.hasNext();) {
			Joueur lJoueur = it2.next();
			lJoueur.setNbGraineDuJoueur(2);			
		}
	}

	
	public int getAgeJPhysique() {
		return this.ageJPhysique;
	}
	
	public String getNomJPhysique() {
		return this.nomJPhysique;
	}
	
}
