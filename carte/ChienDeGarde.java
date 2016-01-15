package carte;
import joueur.Joueur;
import partie.Manche;
import partie.Partie;

/**
 * Classe des cartes alliés "Chien de garde"
 * @author Mathieu & Laurie
 *
 */

public class ChienDeGarde extends Carte{
	
	/**
	 * Constructeur des cartes Chien de garde
	 * @param identifiant Code de la carte
	 * @param tabValCarte Valeur du tableau de la carte
	 * @param nom Nom de la carte pour le joueur
	 */
	
	public ChienDeGarde(String identifiant, int[] tabValCarte, String nom) {
		super(identifiant, tabValCarte, nom);
		this.setAllie(true);
	}
	
	/**
	 * Retourne la force de la carte selon la saison
	 * @param saison Saison choisie
	 */
	
	public int getForce (int saison) {
		return super.getForce(saison);
	}
	
	/**
	 * Permet de choisir une carte Chien de Garde
	 * @param j Joueur qui joue
	 * @param m Manche en cours
	 * @param p Partie en cours
	 */
	
	public void choisirAction(Joueur j, Manche m, Partie p) {
		super.choisirAction(j, m, p);
		System.out.println("Vous avez choisi la carte:\n");
		System.out.println(this.toString());
		j.setNbChiens(this.getForce(m.getSaisonEnCours()));
		System.out.println("Vos chiens de garde permettent d'empêcher le vol de " +j.getNbChiens()+ " graines jusqu'à la fin de cette manche!\n");
		j.getMainDuJoueur().remove(this);
	}
	
	/**
	 * Permet l'affichage d'une carte Chien de Garde
	 */
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("               " + this.getNom() + "\n");
		sb.append("         Primpt.  Ete  Automne  Hiver\n");
		sb.append("Chien       " + this.getForce(0) + "      " + this.getForce(1) + "      " + this.getForce(2) + "       " + this.getForce(3) + "\n");
		return sb.toString();
	}
	
	/**
	 * Méthode abstraite de la classe Carte, non implémentée dans la classe Chien de garde
	 */
	
	public void choisirGeant(Joueur j, Manche m, Partie p) {
		
	}

	/**
	 * Méthode abstraite de la classe Carte, non implémentée dans la classe Chien de garde
	 */
	
	public void choisirEngrais(Joueur j, Manche m, Partie p) {
		
	}

	/**
	 * Méthode abstraite de la classe Carte, non implémentée dans la classe Chien de garde
	 */
	
	public void choisirFarfadet(Joueur j, Manche m, Partie p) {
		
	}


}
