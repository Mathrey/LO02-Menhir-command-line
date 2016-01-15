package strategie;
import joueur.Joueur;
import partie.Manche;
import partie.Partie;
import carte.Carte;

/**
 * L'interface Strategy permet d'impl�menter le design pattern Strategy
 * Cela permet de g�rer 2 types d'IA
 * @author Mathieu & Laurie
 *
 */

public interface Strategy {
	
	/**
	 * Permet de choisir une carte selon diff�rents param�tres impl�ment�s dans les strat�gies concr�tes
	 * @param m Manche en cours
	 * @param p Partie en cours
	 * @param j Joueur qui joue
	 * @return
	 */
	public Carte choisirCarte(Manche m, Partie p, Joueur j);
	
	/**
	 * Permet de choisir l'action de la carte selon la strat�gie en cours
	 * Cette fonction s'adapte si la carte choisie ne laisse pas de choix d'action comme les cartes alli�s
	 * @param c Carte choisie
	 * @param m Manche en cours
	 * @param p Partie en cours
	 * @param j Joueur qui joue
	 */
	
	public void choisirAction(Carte c, Manche m, Partie p, Joueur j);

}