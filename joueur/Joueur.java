package joueur;

import java.util.*;

import partie.Manche;
import partie.Partie;

import strategie.Strategy;

import carte.Carte;

/**
 * Cette classe abstraite permet de cr�er des instances de Joueur
 * Elle impl�mente l'interface Comparable qui permet de comparer des joueurs entre eux gr�ce � la fonction compareTo
 * 
 * @see Joueur#compareTo(Joueur)
 * @author Mathieu & Laurie
 *
 */

public abstract class Joueur implements Comparable<Joueur> {

	/**
	 * Son nom
	 */
	protected String nom;
	/**
	 * Son �ge
	 */
	protected int age = 0;
	/**
	 * Sa strategie pour l'impl�mentation des m�thodes du joueur virtuel
	 */
	protected Strategy strategy;
	/**
	 * Les cartes qu'il poss�de 
	 */
	protected ArrayList<Carte> mainDuJoueur = new ArrayList<Carte>();
	/**
	 * Son nombre de menhirs
	 */
	private int nbMenhirDuJoueur = 0;
	/**
	 * Son nombre de menhirs total pour une partie avanc�e
	 * @see Partie#afficherClassement()
	 */
	private int nbMenhirTotalDuJoueur = 0;
	/**
	 * Son nombre de graines
	 */
	private int nbGraineDuJoueur = 0;
	/**
	 * Son nombre de chiens de garde pour une partie avanc�e
	 * @see carte.ChienDeGarde
	 */
	private int nbChiens = 0;
	/**
	 * S'il a d�j� commenc�
	 * @see Manche#attribuerJoueurDeDebut()
	 */
	private boolean aDejaCommence;
	/**
	 * Si c'est un joueur physique
	 * @see joueur.JoueurPhysique
	 * @see joueur.JoueurVirtuel
	 */
	public boolean isJoueurPhysique;
	/**
	 * Un indice pour conna�tre la strat�gie utilis�e
	 */
	protected int indiceDeStratDuJoueurVirtuel;

	/**
	 * Le constructeur de Joueur auquel on fait appel dans les classes h�ritant de Joueur (puisque Joueur est une classe abstraite)
	 */
	public Joueur(){
		this.nbGraineDuJoueur = 0;
		this.nbMenhirDuJoueur = 0;
		this.aDejaCommence = false;
	}
	
	/**
	 * M�thode affichant les cartes dans la main du joueur	
	 */
	public void afficherMainJoueur(){
		int i = 1;
		for(Carte c: this.mainDuJoueur){
			System.out.println("Carte " + i + " :");
			i++;
			System.out.println(c.toString());
		}
	}
	/**
	 * M�thode retournant le nombre de graines du joueur
	 * @return Le nombre entier de graines du joueur
	 */
	public int getNbGraineDuJoueur(){
		return nbGraineDuJoueur;
	}
	
	/**
	 * M�thode modifiant le nombre de graines du joueur avec deux param�tres
	 * @param nbGraineDuJoueur Le nombre de graines de d�part
	 * @param nbAAjouterGraine Le nombre de graines � ajouter
	 */
	public void setNbGraineDuJoueur(int nbGraineDuJoueur, int nbAAjouterGraine){
		this.nbGraineDuJoueur = nbGraineDuJoueur + nbAAjouterGraine;
	}
	
	/**
	 * M�thode modifiant le nombre de graines du joueur
	 * @param nbGraine Le nouveau nombre de graines
	 */
	public void setNbGraineDuJoueur(int nbGraine){
		this.nbGraineDuJoueur = nbGraine;
	}
	
	/**
	 * Renvoie le nombre de menhirs du joueur
	 * @return Le nombre de menhirs sous orme d'entier
	 */
	public int getNbMenhirDuJoueur(){
		return nbMenhirDuJoueur;
	}
	/**
	 * Modifie le nombre de menhirs du joueur avec deux param�tres
	 * @param nbMenhirDuJoueur Le nombre de menhirs de d�part
	 * @param nbAAjouterMenhir Le nombre de menhirs � ajouter
	 */
	public void setNbMenhirDuJoueur(int nbMenhirDuJoueur, int nbAAjouterMenhir){
		this.nbMenhirDuJoueur = nbMenhirDuJoueur + nbAAjouterMenhir;
	}
	/**
	 * Modifie le nombre de menhirs du joueur
	 * @param nbMenhir Le nouveau nombre de menhirs
	 */
	public void setNbMenhirDuJoueur(int nbMenhir) {
		this.nbMenhirDuJoueur = nbMenhir;
	}
	
	/**
	 * Modifie le nombre de menhirs total du joueur
	 * @param nbMenhirDuJoueur Le nouveau nombre de menhirs
	 * @see Partie#afficherClassement()
	 */
	public void setNbMenhirTotalDuJoueur(int nbMenhirDuJoueur) {
		this.nbMenhirTotalDuJoueur = this.nbMenhirTotalDuJoueur + nbMenhirDuJoueur;
	}
	/**
	 * Renvoie le nombre de menhirs total du joueur
	 * @return Nombre de menhirs total sous forme d'entier
	 * @see Partie#afficherClassement()
	 */
	public int getNbMenhirTotalDuJoueur() {
		return this.nbMenhirTotalDuJoueur;
	}
	
	/**
	 * Renvoie les cartes de la main du joueur
	 * @return Le collection des cartes du joueur
	 */
	public ArrayList<Carte> getMainDuJoueur() {
		return this.mainDuJoueur;
	}
	
	/**
	 * Renvoie l'attribut du joueur indiquant s'il a d�j� commenc�
	 * @return Un boolean vrai si le joueur a d�j� commenc�, faux sinon
	 * @see Joueur#aDejaCommence
	 */
	public boolean getADejaCommence() {
		return this.aDejaCommence;
	}
	
	/**
	 * Modifie l'attribut du joueur s'il a commenc� ou non
	 * @param aCommence Le nouvel �tat du joueur
	 */
	public void setADejaCommence(boolean aCommence) {
		this.aDejaCommence = aCommence;
	}
	/**
	 * Renvoie le nombre de chiens que poss�de le joueur
	 * @return Le nombre de chiens sous forme d'entier
	 * @see carte.ChienDeGarde
	 */
	public int getNbChiens () {
		return this.nbChiens;
	}
	/**
	 * 
	 * @param chiens
	 */
	public void setNbChiens (int chiens) {
		this.nbChiens = chiens;
	}
	
	/**
	 * M�thode qui permet au joueur de jouer une carte
	 * @param m Manche en cours
	 * @param p Partie en cours
	 */
	public void jouerCarte(Manche m, Partie p) {
		if (this.isJoueurPhysique) {
			System.out.println(+this.getNbGraineDuJoueur()+ " graines " +this.getNbMenhirDuJoueur()+ " menhirs.\n");
			this.choisirCarte(m, p);
			
			}else{
				System.out.println(+this.getNbGraineDuJoueur()+ " graines " +this.getNbMenhirDuJoueur()+ " menhirs.\n");
				Carte c = this.getStrategy().choisirCarte(m, p, this);
				this.getStrategy().choisirAction(c, m, p, this);
			}
	}

	/**
	 * M�thode qui permet de choisir une carte
	 * @param m Manche en cours
	 * @param p Partie en cours
	 */
	public void choisirCarte(Manche m, Partie p){
		System.out.println("\nChoisir une carte � jouer : ");
		this.afficherMainJoueur();

		int choixCarte = 0;

		do {
			try {
				choixCarte = p.getChoix();
				
			}catch(InputMismatchException e) {
				System.out.println("Choisissez une carte en tapant 1, 2, 3, 4 ou 5.\n");
			}catch(IndexOutOfBoundsException e) {
				System.out.println("Vous n'avez pas autant de cartes!\nR�essayez.\n");

			}
			}while(choixCarte < 1 || choixCarte >this.mainDuJoueur.size());
			this.mainDuJoueur.get(choixCarte - 1).choisirAction(this, m, p);
	}
		
	/**
	 * Renvoie l'�ge du joueur
	 * @return �ge du joueur sous forme d'entier
	 */
	public int getAge() {
		return age;
	}
	/**
	 * Modifie l'�ge du joueur
	 * @param age Nouvel �ge
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * M�thode abtraite implant�e dans joueur.JoueurPhysique pour choisir un joueur
	 * @param listDeJoueur Joueurs de la partie
	 * @return Le joueur choisi
	 * @see joueur.JoueurPhysique
	 */
	public abstract Joueur choisirJoueur(ArrayList<Joueur> listDeJoueur);
	
	/**
	 * Renvoie le nom du joueur
	 * @return Le nom sous forme de cha�ne de caract�re
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * M�thode permettant de comparer le nombre de m�nhirs total de 2 joueurs
	 * @param autreJoueur Joueur auquel on compare le nombre de menhirs
	 * @return -1 si le joueur compar� a plus de menhirs que l'autre joueur, 0 si les deux ont le m�me nombre, 1 si le joueur compar� a moins de menhirs
	 */
	public int compareTo(Joueur autreJoueur) {
		int nbj1 = this.getNbMenhirTotalDuJoueur();
		int nbj2 = autreJoueur.getNbMenhirTotalDuJoueur();
		int resultat;
		
		if (nbj1 > nbj2) {
			resultat = -1;
		}else{
			if (nbj1 == nbj2) {
				resultat =  0;
			}else{
				resultat = 1;
			}
		}
		return resultat;
	}
	
	/**
	 * Renvoie la strategy du joueur
	 * @return Strat�gie du joueur
	 */
	public Strategy getStrategy() {
		return strategy;
	}
}
