package strategie;

import java.util.Iterator;

import joueur.Joueur;

import partie.Manche;
import partie.Partie;


import carte.Carte;


/**
 * Cette classe impl�mente l'interface Strategy et correspond � une strat�gie dite offensive.
 * Les IA sont cens�es choisir actions selon l'ordre suivant:
 * D'abord les farfadets
 * Puis les engrais
 * Et enfin les g�ants
 * 
 * @author Mathieu & Laurie
 *
 */

public class OffensiveStrategy implements Strategy {

	/**
	 * La strat�gie utilise un attribut boolean pour savoir si le joueur utilise l'action farfadet ou non
	 * Ainsi que des constantes qui correspondent au choix des actions
	 */
	
	private boolean utiliserFarfadet;
	private final int ENGRAIS = 2;
	private final int FARFADET = 3;
	
	/**
	 * Le constructeur d'une strategie, qui par d�faut met le choix de l'action farfadet � faux
	 */
	
	public OffensiveStrategy() {
		this.utiliserFarfadet = false;
	}
	
	/**
	 * La m�thode pour choisir une carte impl�ment�e dans une strat�gie offensive
	 * Le joueur virtuel passe en revue ses cartes pour d�terminer celle qui a la plus grande valeur d'action farfadet
	 * @param m Manche en cours
	 * @param p Partie en cours
	 * @param j Joueur qui joue
	 * @return La carte qui va �tre choisie par le joueur
	 */
	
	public Carte choisirCarte(Manche m, Partie p, Joueur j) {
		System.out.println("\nAu tour de joueur virtuel " + "de jouer!\n");
		System.out.println("Le joueur virtuel choisi sa carte...");
		
		int saisonEnCours = m.getSaisonEnCours();
		Carte choixCartePrivilegie = null;
		int valSuperieure = 0;
		
		choixCartePrivilegie = j.getMainDuJoueur().get(0);
		valSuperieure = choixCartePrivilegie.getForce(FARFADET -1, saisonEnCours);

		for(Iterator<Carte> it = j.getMainDuJoueur().iterator(); it.hasNext();){
			Carte carteActive = it.next();
			if(carteActive.isAllie()==false){
				if(j.getNbGraineDuJoueur() == 0){
					if(carteActive.getForce(FARFADET -1, saisonEnCours) > valSuperieure){
						choixCartePrivilegie = carteActive;
						valSuperieure = carteActive.getForce(FARFADET -1, saisonEnCours);
						utiliserFarfadet = true;
					}else{
						choixCartePrivilegie = carteActive;
						valSuperieure = carteActive.getForce(ENGRAIS -1, saisonEnCours);
						
						if(carteActive.getForce(ENGRAIS -1, saisonEnCours) > valSuperieure){
							choixCartePrivilegie = carteActive;
							valSuperieure = carteActive.getForce(ENGRAIS -1, saisonEnCours);
						}
					}
				}
			}
		}
	
		return choixCartePrivilegie;
	}
	
	/**
	 * La m�thode pour jouer l'action en fonction de la carte qui a �t� choisie
	 * Si la carte choisie poss�dait suffisamment de farfadets, alors le joueur joue l'action farfadet
	 * Sinon, le joueur regarde s'il a assez de graines � planter, dans ce cas il choisit l'action engrais
	 * Dans tous les autres cas, le joueur va r�cup�rer des graines dans le pot commun � l'aide de l'action g�ant
	 * Le joueur virtuel passe en revue ses cartes pour d�terminer celle qui a la plus grande valeur d'action farfadet
	 * @param c Carte choisie
	 * @param m Manche en cours
	 * @param p Partie en cours
	 * @param j Joueur qui joue
	 */

	public void choisirAction(Carte c, Manche m, Partie p, Joueur j) {
		System.out.println("Le joueur virtuel choisi son action...");
		
		if(utiliserFarfadet){
			c.choisirFarfadet(j, m, p);
			System.out.println("Le joueur virtuel � jou� l'action FARFADET");
		}else{
			if(c.getForce(ENGRAIS, m.getSaisonEnCours()) <= j.getNbMenhirDuJoueur()){
				c.choisirEngrais(j, m, p);
			}else{
				c.choisirGeant(j, m, p);
			}
		}
	}
}
