package carte;
import joueur.Joueur;
import partie.Manche;
import partie.Partie;

/**
 * Classe des cartes alliés "Taupe Géante"
 * @author Mathieu & Laurie
 *
 */

public class TaupeGeante extends Carte {
	
	/**
	 * Constructeur des cartes Taupe Géante
	 * @param identifiant Code de la carte
	 * @param tabValCarte Valeur du tableau de la carte
	 * @param nom Nom de la carte pour le joueur
	 */
	
	public TaupeGeante(String identifiant, int[] tabValCarte, String nom) {
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
	 * Permet de choisir une carte Taupe Géante
	 * @param j Joueur qui joue
	 * @param m Manche en cours
	 * @param p Partie en cours
	 */
	
	public void choisirAction(Joueur j, Manche m, Partie p) {
		super.choisirAction(j, m, p);
		System.out.println("Vous avez choisi la carte:\n");
		System.out.println(this.toString());
		System.out.println("Veuillez choisir un joueur: \n");
		Joueur joueurChoisi = j.choisirJoueur(p.getListeJoueur());
		if (joueurChoisi.getNbMenhirDuJoueur() < this.getForce(m.getSaisonEnCours())) {
			joueurChoisi.setNbMenhirDuJoueur(0);
			System.out.println(joueurChoisi.getNom()+ " avait moins de ménhirs que la force de votre carte, il a désormais 0 ménhir!");
		}else{
			if (joueurChoisi.getNbMenhirDuJoueur() >= this.getForce(m.getSaisonEnCours())) {
				joueurChoisi.setNbMenhirDuJoueur(joueurChoisi.getNbMenhirDuJoueur()-this.getForce(m.getSaisonEnCours()));
				System.out.println("Vous avez retiré " +this.getForce(m.getSaisonEnCours())+ " ménhirs du champs de " +joueurChoisi.getNom()+ ", il lui en reste " +joueurChoisi.getNbMenhirDuJoueur());
			}
		}
		j.getMainDuJoueur().remove(this);
	}
	
	/**
	 * Permet l'affichage d'une carte Chien de Garde
	 */
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("                   " + this.getNom() + "\n");
		sb.append("         Primpt.  Ete  Automne  Hiver\n");
		sb.append("Taupe       " + this.getForce(0) + "      " + this.getForce(1) + "      " + this.getForce(2) + "       " + this.getForce(3) + "\n");
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
