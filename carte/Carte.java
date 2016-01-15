package carte;
import joueur.Joueur;
import partie.Manche;
import partie.Partie;

/**
 * 
 * Carte est la classe abstraite à partir de laquelle les cartes alliés et ingrédients sont créées
 * 
 * @author Mathieu & Laurie
 *
 */

public abstract class Carte {

	/**
	 * Son nom dans le code
	 */
	private String nom;
	/**
	 * Son nom pour les joueurs
	 */
	protected String nomReel;
	/**
	 * Ses valeurs pour un tableau à double entrée
	 */
	private int[][] tableauIng;
	/**
	 * Ses valeurs pour un tableau à simple entrée
	 */ 
	private int[] tableauAlli;
	/**
	 * Si c'est une carte allié ou non
	 */
	
	private boolean isAllie;

	
	/**
	 * Le constructeur d'une carte ingrédient
	 * @param identifiant Nom de la carte dans le code
	 * @param tabValCarte Tableau de valeur de la carte
	 * @param nom Nom de la carte pour le joueur
	 */
	
	public Carte(String identifiant, int[][] tabValCarte, String nom) {
		this.nom = nom;
		this.tableauIng = tabValCarte;
		this.tableauAlli = null;
		this.nomReel = identifiant;
		this.setAllie(false);
	}
	
	/**
	 * Le constructeur d'une carte allié
	 * @param identifiant Nom de la carte dans le code
	 * @param tabValCarte Tableau de valeur de la carte
	 * @param nom Nom de la carte pour le joueur
	 */
	
	public Carte(String identifiant, int[] tabValCarte, String nom) {
		this.nom = nom;
		this.tableauIng = null;
		this.tableauAlli = tabValCarte;
		this.nomReel = identifiant;
	}
	
	/**
	 * Retourne le nom dans le code de la carte
	 * @return Le nom en chaîne de caractère
	 */
	
	public String getNom(){
		return this.nom;
	}
	
	/**
	 * Retourne la force d'une carte ingrédient en fonction de l'action choisie et de la saison
	 * @param action Action choisie
	 * @param saison Saison choisie
	 * @return La force correspondante de type entier
	 */

	public int getForce(int action, int saison) {
		return this.tableauIng[action][saison];
	}
	
	/**
	 * Retourne la force de la carte allié en fonction de la saison choisie
	 * @param saison Saison choisie
	 * @return
	 */
	
	public int getForce(int saison) {
		return this.tableauAlli[saison];
	}
	
	/**
	 * Méthode abstraite permettant de faire appel aux méthodes implémentées dans ingrédient et les cartes alliés avec la classe carte
	 * @param j Le joueur possédant la carte
	 * @param m La manche en cours
	 * @param p La partie en cours
	 */

	public abstract void choisirGeant(Joueur j, Manche m, Partie p);
	
	/**
	 * Méthode abstraite permettant de faire appel aux méthodes implémentées dans ingrédient et les cartes alliés avec la classe carte
	 * @param j Le joueur possédant la carte
	 * @param m La manche en cours
	 * @param p La partie en cours
	 */
	
	public abstract void choisirEngrais(Joueur j, Manche m, Partie p);
	
	
	/**
	 * Méthode abstraite permettant de faire appel aux méthodes implémentées dans ingrédient et les cartes alliés avec la classe carte
	 * @param j Le joueur possédant la carte
	 * @param m La manche en cours
	 * @param p La partie en cours
	 */
	
	public abstract void choisirFarfadet(Joueur j, Manche m, Partie p);
	
	/**
	 *  Méthode étant appelée par les joueurs virtuels qui jouent une carte.
	 *  Elle permet en fonction de la carte choisie de directement choisir une action si c'est une carte allié
	 * @param j Le joueur possédant la carte
	 * @param m La manche en cours
	 * @param p La partie en cours
	 */
	public void choisirAction(Joueur j, Manche m, Partie p){
		
	}

	/**
	 * Retourne si la carte est du type allié ou non
	 * @return Vrai si la carte est allié, faux sinon
	 */
	
	public boolean isAllie() {
		return isAllie;
	}

	/**
	 * Permet de passer le boolean de carte isAllie à true
	 * @param isAllie 
	 * @return
	 */
	
	public void setAllie(boolean isAllie) {
		this.isAllie = isAllie;
	}
}
