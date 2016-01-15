package joueur;
import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;


import strategie.DefensiveStrategy;
import strategie.OffensiveStrategy;
import strategie.Strategy;

/**
 * Cette classe permet d'instancier un joueur virtuel
 * @author Mathieu
 *
 */
public class JoueurVirtuel extends Joueur {
	
	/**
	 * Nom de la stratégie du joueur virtuel
	 */
	String strategieDuJVirtuel;
	/**
	 * Indice permettant le changement de stratégie
	 */
	private int indice;
	/**
	 * Stratégie du joueur virtuel
	 */
	private Strategy strategy;	
 
	/**
	 * Constructeur d'un joueur virtuel
	 * @param i Numéro du joueur virtuel
	 */
	public JoueurVirtuel(int i) {
		super();
		this.isJoueurPhysique = true;
		this.indice = i;
		this.nom = "JoueurV"+i;  
		this.age = (int) (8+Math.random() * 80 ); // On détermine un âge au hasard entre 8 et 88 ans)
		System.out.println("Age de JoueurV" + this.indice + " = " + this.age);
		int leNbRecup = this.attributionStrategieAJoueur();
		this.indiceDeStratDuJoueurVirtuel = leNbRecup;
		isJoueurPhysique = false;
		
		if(this.indiceDeStratDuJoueurVirtuel == 0){
			this.strategy = new OffensiveStrategy();
		}else{
			this.strategy = new DefensiveStrategy();
		}
	}
	/**
	 * Permet d'attribuer une stratégie au hasard
	 * @return 0 pour une stratégie offensive, 1 pour une stratégie défensive
	 */
	public int attributionStrategieAJoueur(){
		Random rn = new Random();
		int leNbAleatoire = rn.nextInt(2);
		return leNbAleatoire;
	}
	/**
	 * Renvoie le nom du joueur virtuel
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * Renvoie la stratégie du joueur virtuel
	 */
	public Strategy getStrategy() {
		super.getStrategy();
		return strategy;
	}
	/**
	 * Méthode permettant à un joueur virtuel de choisir un autre joueur
	 * Le joueur choisit celui qui a le plus de graines et le plus de menhirs
	 * @param joueurs Liste des joueurs de la partie
	 * @return Le joueur choisi
	 */
	public Joueur choisirJoueur(ArrayList<Joueur> joueurs) {
		Joueur choixJoueur = null;
		int nbGrainesMax = -1;
		int nbMenhirsMax = -1;
		for(Iterator<Joueur> it = joueurs.iterator(); it.hasNext();) {
		Joueur joueurActif = it.next();
		if (joueurActif.getNbGraineDuJoueur()>=nbGrainesMax) {
			if (joueurActif.getNbGraineDuJoueur() > nbGrainesMax) {
				choixJoueur = joueurActif;
				nbGrainesMax = joueurActif.getNbGraineDuJoueur();
				nbMenhirsMax = joueurActif.getNbMenhirDuJoueur();
			}
			else if (joueurActif.getNbMenhirDuJoueur() > nbMenhirsMax) {
				choixJoueur = joueurActif;
				nbMenhirsMax = joueurActif.getNbMenhirDuJoueur();
			}
		}
		}	
		return choixJoueur;
	}
}
