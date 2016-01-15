package strategie;

import java.util.Iterator;

import joueur.Joueur;

import partie.Manche;
import partie.Partie;


import carte.Carte;


/**
 * Cette classe implémente l'interface Strategy et correspond à une stratégie dite offensive.
 * Les IA sont censées choisir actions selon l'ordre suivant:
 * D'abord les farfadets
 * Puis les engrais
 * Et enfin les géants
 * 
 * @author Mathieu & Laurie
 *
 */

public class OffensiveStrategy implements Strategy {

	/**
	 * La stratégie utilise un attribut boolean pour savoir si le joueur utilise l'action farfadet ou non
	 * Ainsi que des constantes qui correspondent au choix des actions
	 */
	
	private boolean utiliserFarfadet;
	private final int ENGRAIS = 2;
	private final int FARFADET = 3;
	
	/**
	 * Le constructeur d'une strategie, qui par défaut met le choix de l'action farfadet à faux
	 */
	
	public OffensiveStrategy() {
		this.utiliserFarfadet = false;
	}
	
	/**
	 * La méthode pour choisir une carte implémentée dans une stratégie offensive
	 * Le joueur virtuel passe en revue ses cartes pour déterminer celle qui a la plus grande valeur d'action farfadet
	 * @param m Manche en cours
	 * @param p Partie en cours
	 * @param j Joueur qui joue
	 * @return La carte qui va être choisie par le joueur
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
	 * La méthode pour jouer l'action en fonction de la carte qui a été choisie
	 * Si la carte choisie possédait suffisamment de farfadets, alors le joueur joue l'action farfadet
	 * Sinon, le joueur regarde s'il a assez de graines à planter, dans ce cas il choisit l'action engrais
	 * Dans tous les autres cas, le joueur va récupérer des graines dans le pot commun à l'aide de l'action géant
	 * Le joueur virtuel passe en revue ses cartes pour déterminer celle qui a la plus grande valeur d'action farfadet
	 * @param c Carte choisie
	 * @param m Manche en cours
	 * @param p Partie en cours
	 * @param j Joueur qui joue
	 */

	public void choisirAction(Carte c, Manche m, Partie p, Joueur j) {
		System.out.println("Le joueur virtuel choisi son action...");
		
		if(utiliserFarfadet){
			c.choisirFarfadet(j, m, p);
			System.out.println("Le joueur virtuel à joué l'action FARFADET");
		}else{
			if(c.getForce(ENGRAIS, m.getSaisonEnCours()) <= j.getNbMenhirDuJoueur()){
				c.choisirEngrais(j, m, p);
			}else{
				c.choisirGeant(j, m, p);
			}
		}
	}
}
