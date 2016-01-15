package strategie;

import java.util.Iterator;

import joueur.Joueur;

import partie.Manche;
import partie.Partie;


import carte.Carte;

/**
 * Cette classe impl�mente l'interface Strategy et correspond � une strat�gie dite d�fensive.
 * Les IA sont cens�es choisir actions selon l'ordre suivant:
 * D'abord les g�ants
 * Puis les engrais
 * Et enfin les farfadets
 * 
 * @author Mathieu & Laurie
 *
 */

public class DefensiveStrategy implements Strategy {

	/**
	 * La strat�gie utilise un attribut boolean pour savoir si le joueur utilise l'action g�ant ou non ainsi que des constantes qui correspondent au choix des actions
	 */
	
	private boolean utiliserGeant;
	private final int GEANT = 1;
	private final int ENGRAIS = 2;
	
	/**
	 * Le constructeur d'une strategie, qui par d�faut met le choix de l'action g�ant � faux
	 */
	
	public DefensiveStrategy() {
		this.utiliserGeant = false;
	}

	/**
	 * La m�thode pour choisir une carte impl�ment�e dans une strat�gie offensive
	 * Le joueur virtuel passe en revue ses cartes pour d�terminer celle qui a la plus grande valeur d'action g�ant par rapport aux nombres de graines poss�d�es
	 * @param m Manche en cours
	 * @param p Partie en cours
	 * @param j Joueur qui joue
	 * @return La carte qui va �tre choisie par le joueur
	 */
	
	public Carte choisirCarte(Manche m, Partie p, Joueur j) {
		System.out.println("\nAu tour de joueur virtuel " + "de jouer!\n");
		System.out.println("Le joueur virtuel choisit sa carte...");
		
		int saisonEnCours = m.getSaisonEnCours();
		Carte choixCartePrivilegie = null;
		int valSuperieure = 0;
			
		choixCartePrivilegie = j.getMainDuJoueur().get(0);
		valSuperieure = choixCartePrivilegie.getForce(GEANT -1, saisonEnCours);

		for(Iterator<Carte> it = j.getMainDuJoueur().iterator(); it.hasNext();){
			Carte carteActive = it.next();
			if(carteActive.isAllie()==false){
				if(j.getNbGraineDuJoueur() > 4){
					if(carteActive.getForce(ENGRAIS -1, saisonEnCours) > valSuperieure){
						choixCartePrivilegie = carteActive;
						valSuperieure = carteActive.getForce(ENGRAIS -1, saisonEnCours);
					}
				}else{
					utiliserGeant = true;
					choixCartePrivilegie = j.getMainDuJoueur().get(0);
					valSuperieure = choixCartePrivilegie.getForce(GEANT -1, saisonEnCours);
					
					if(carteActive.getForce(GEANT -1, saisonEnCours) > valSuperieure){
						choixCartePrivilegie = carteActive;
						valSuperieure = carteActive.getForce(GEANT -1, saisonEnCours);
					}
				}
			}
		}
		return choixCartePrivilegie;
	}

	/**
	 * La m�thode pour jouer l'action en fonction de la carte qui a �t� choisie
	 * Si la m�thode pr�c�dente a d�termin� que l'action g�ant �tait la plus adapt�e, c'est celle qui sera jou�e
	 * Sinon le joueur regarde son nombre de graine et la force de l'action engrais pour ce tour:
	 * Si elle la force est inf�rieure au nombre de graines du joueur, alors le joueur plante toutes les graines correspondantes en choisissant cette action
	 * Au contraire si la force est sup�rieure au nombre de graines du joueur, celui-ci va alors en voler � un autre joueur en utilisant l'action farfadet
	 * @param c Carte choisie
	 * @param m Manche en cours
	 * @param p Partie en cours
	 * @param j Joueur qui joue
	 */
	
	public void choisirAction(Carte c, Manche m, Partie p, Joueur j) {
		System.out.println("Le joueur virtuel choisit son action...");
		
		if(utiliserGeant){ 
			c.choisirGeant(j,m,p);		
		}else{
			if(c.getForce(ENGRAIS, m.getSaisonEnCours()) <= j.getNbGraineDuJoueur()){
				c.choisirEngrais(j, m, p);
			}else{
				c.choisirFarfadet(j, m, p);
			}	
		}
	}
}
