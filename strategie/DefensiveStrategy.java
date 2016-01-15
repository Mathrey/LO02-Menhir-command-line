package strategie;

import java.util.Iterator;

import joueur.Joueur;

import partie.Manche;
import partie.Partie;


import carte.Carte;

/**
 * Cette classe implémente l'interface Strategy et correspond à une stratégie dite défensive.
 * Les IA sont censées choisir actions selon l'ordre suivant:
 * D'abord les géants
 * Puis les engrais
 * Et enfin les farfadets
 * 
 * @author Mathieu & Laurie
 *
 */

public class DefensiveStrategy implements Strategy {

	/**
	 * La stratégie utilise un attribut boolean pour savoir si le joueur utilise l'action géant ou non ainsi que des constantes qui correspondent au choix des actions
	 */
	
	private boolean utiliserGeant;
	private final int GEANT = 1;
	private final int ENGRAIS = 2;
	
	/**
	 * Le constructeur d'une strategie, qui par défaut met le choix de l'action géant à faux
	 */
	
	public DefensiveStrategy() {
		this.utiliserGeant = false;
	}

	/**
	 * La méthode pour choisir une carte implémentée dans une stratégie offensive
	 * Le joueur virtuel passe en revue ses cartes pour déterminer celle qui a la plus grande valeur d'action géant par rapport aux nombres de graines possédées
	 * @param m Manche en cours
	 * @param p Partie en cours
	 * @param j Joueur qui joue
	 * @return La carte qui va être choisie par le joueur
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
	 * La méthode pour jouer l'action en fonction de la carte qui a été choisie
	 * Si la méthode précédente a déterminé que l'action géant était la plus adaptée, c'est celle qui sera jouée
	 * Sinon le joueur regarde son nombre de graine et la force de l'action engrais pour ce tour:
	 * Si elle la force est inférieure au nombre de graines du joueur, alors le joueur plante toutes les graines correspondantes en choisissant cette action
	 * Au contraire si la force est supérieure au nombre de graines du joueur, celui-ci va alors en voler à un autre joueur en utilisant l'action farfadet
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
