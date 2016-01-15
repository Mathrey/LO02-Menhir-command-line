package strategie;
import joueur.Joueur;
import partie.Manche;
import partie.Partie;
import carte.Carte;

/**
 * L'interface Strategy permet d'implémenter le design pattern Strategy
 * Cela permet de gérer 2 types d'IA
 * @author Mathieu & Laurie
 *
 */

public interface Strategy {
	
	/**
	 * Permet de choisir une carte selon différents paramètres implémentés dans les stratégies concrètes
	 * @param m Manche en cours
	 * @param p Partie en cours
	 * @param j Joueur qui joue
	 * @return
	 */
	public Carte choisirCarte(Manche m, Partie p, Joueur j);
	
	/**
	 * Permet de choisir l'action de la carte selon la stratégie en cours
	 * Cette fonction s'adapte si la carte choisie ne laisse pas de choix d'action comme les cartes alliés
	 * @param c Carte choisie
	 * @param m Manche en cours
	 * @param p Partie en cours
	 * @param j Joueur qui joue
	 */
	
	public void choisirAction(Carte c, Manche m, Partie p, Joueur j);

}