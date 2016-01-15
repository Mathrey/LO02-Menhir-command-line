package partie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.HashSet;

import joueur.Joueur;
import joueur.JoueurPhysique;
import joueur.JoueurVirtuel;

/**
 * Cette classe permet de cr�er la partie
 * @author Mathieu & Laurie
 *
 */

public class Partie  {
	/**
	 * Permet de savoir si la partie est rapide ou avanc�e
	 */
	private boolean partieAvancee;
	/**
	 * Liste des joueurs de la partie
	 */
	private ArrayList<Joueur> listeJoueur = new ArrayList<Joueur>();
	/**
	 * Liste des manches de la partie
	 */
	private ArrayList<Manche> listeManche = new ArrayList<Manche>();
	
	/**
	 * Nombre de joueurs physiques
	 */
	private int nbJPhysique;
	/**
	 * Nombre de joueurs virtuels
	 */
	private int nbJVirtuel;
	/**
	 * Nombre de manches par d�faut
	 */
	private int nbManche = 0;
	/**
	 * Num�ro de la manche en cours
	 */
	private int mancheNumero = 0;
	/**
	 * Scanner r�cup�rant l'entr�e d'un utilisateur pour un entier
	 */
	private Scanner scanChoix;
	
	/**
	 * �ge du joueur physique pour l'interface graphique
	 */
	private int ageJPhysique;
	/**
	 * Nom du joueur physique pour l'interface graphique
	 */
	private String nomJPhysique;
	
	/**
	 * Instance de la partie pour le singleton
	 */
	private static Partie partie = null;
	
	/**
	 * Impl�mentation du design pattern singleton
	 * @return L'instance de partie
	 */
	public static Partie getInstance(){
		
		if(partie == null){
			partie = new Partie();
		}
		
		return partie;
	}
	
	/**
	 * Constructeur de partie pour l'interface graphique
	 */
	public Partie(){
	
	}
		
	/**
	 * Renvoie le nombre de joueur physique
	 * @return L'entier de nombre de joueurs physiques
	 */
	public int getNbJPhysique(){
		return this.nbJPhysique;
	}
	
	/**
	 * Renvoie le nombre de joueurs virtuels
	 * @return L'entier de nombre de joueurs virtuels
	 */
	public int getNbJVirtuel(){
		return this.nbJVirtuel;
	}
	
	/**
	 * M�thode permettant de lancer la partie, de d�rouler toutes les manches et d'afficher les gagnants
	 * @see partie.Manche
	 * @see Partie#afficherClassement()
	 * @see Partie#afficherGagnants()
	 */
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
				System.out.println("La manche est termin�e.\n");

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
			System.out.println("\nLa partie est termin�e.\n");
			this.afficherGagnants();
			}
	}
	/**
	 * M�thode affichant les gagnants d'une partie rapide
	 */
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
			System.out.print(gagnant.getNom() + " avec " +gagnant.getNbMenhirDuJoueur()+ " m�nhir(s) et " +gagnant.getNbGraineDuJoueur()+ " graine(s).\n");
		}
	}
	/**
	 * M�hode affichant le classement des joueurs dans une partie avanc�e
	 */
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
	
	/**
	 * Renvoie si la partie est avanc�e ou non
	 * @return Vrai pour une partie avanc�e, faux si non
	 */
	public boolean getPartieAvancee() {
		return partieAvancee;
	}
	/**
	 * Renvoie la liste des joueurs de la partie
	 * @return Collection des joueurs de la partie
	 */
	public ArrayList<Joueur> getListeJoueur(){
		return listeJoueur;
	}
	
	/**
	 * Renvoie le num�ro de la manche en cours
	 * @return Entier correspondant au num�ro de la manche
	 */
	public int getMancheNumero() {
		return this.mancheNumero;
	}
	
	/**
	 * M�thode permettant de r�cup�rer l'entr�e d'un utilisateur pour un choix d'entier
	 * Cette m�thode est entour�e d'un try catch l� o� elle est impl�ment�e
	 * @return Renvoie l'entier entr� par l'utilisateur
	 */
	public int getChoix() {
		scanChoix = new Scanner(System.in);
		int choix = scanChoix.nextInt();
		return choix;
	}
	/**
	 * Permet de changer la difficult� de la partie
	 * @param dif 0 pour une partie rapide, 1 pour une partie avanc�e
	 */
	public void setDifficulte(int dif) {
		if (dif == 0) {
			this.partieAvancee = false;
		} else if (dif == 1) {
			this.partieAvancee = true;
		}
	}
	
	/**
	 * Modifie le nombre de joueurs virtuels
	 * @param nbr Nouveau nombre de joueurs virtuels
	 */
	public void setNbrJoueursVirtuels(int nbr) {
		this.nbJVirtuel = nbr;
	}
	/**
	 * Cr�e les instances des joueurs virtuels en fonction du nombre de ceux-ci
	 * @param nbr Nombre de joueurs virtuels � cr�er
	 */
	public void setJoueursVirtuels(int nbr) {
		for(int i=1; i<= nbr; i++){
			this.listeJoueur.add(new JoueurVirtuel(i));
		}
	}
	
	/**
	 * Modifie le nombre de joueurs physiques
	 * @param nbr Nouveau nombre de joueurs physiques
	 */
	public void setNbrJoueursPhysiques(int nbr) {
		this.nbJPhysique = nbr;
	}
	
	/**
	 * Cr�e les instances des joueurs physiques en fonction du nombre de ceux-ci
	 * @param nbr Nombre de joueurs physiques � cr�er
	 */
	public void setJoueursPhysiques(int nbr) {
		for(int i=1; i<= nbr; i++){
			this.listeJoueur.add(new JoueurPhysique());
		}
	}

	/**
	 * Modifie l'�ge du joueur physique
	 * @param ageJPhysique Nouvel �ge du joueur physique
	 */
	public void setAgeJPhysique(int ageJPhysique) {
		this.ageJPhysique = ageJPhysique;
	}

	/**
	 * Modifie le nom du joueur physique
	 * @param nomJPhysique Nouveau nom du joueur physique
	 */
	public void setNomJPhysique(String nomJPhysique) {
		this.nomJPhysique = nomJPhysique;
	}
	
	/**
	 * Cr�e les instances des joueurs physiques en fonction du nombre de ceux-ci avec des param�tres en entr�e
	 * @param age �ge du joueur physique
	 * @param nom Nom du joueur physique
	 */
	public void setJoueursPhysiques(int age, String nom) {
		this.listeJoueur.add(new JoueurPhysique(age, nom));
	}
	
	/**
	 * Permet de mettre � 2 le nombre de graines de chaque joueur
	 */
	public void setGrainesJoueurs() {
		for (Iterator<Joueur> it2 = this.listeJoueur.iterator(); it2.hasNext();) {
			Joueur lJoueur = it2.next();
			lJoueur.setNbGraineDuJoueur(2);			
		}
	}

	/**
	 * Retourne l'�ge du joueur physique
	 * @return L'entier correspondant � l'�ge du joueur physique
	 */
	public int getAgeJPhysique() {
		return this.ageJPhysique;
	}
	
	/**
	 * Retourne le nom du joueur physique
	 * @return Cha�ne de caract�re correspondant au nom du joueur physique
	 */
	public String getNomJPhysique() {
		return this.nomJPhysique;
	}
	
}
