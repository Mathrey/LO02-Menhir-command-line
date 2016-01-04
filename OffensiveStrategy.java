import java.util.Iterator;

/*NB:
 * Les IA sont toujours en cours de développement.
 * Elles sont fonctionnelles mais pas optimales.
 * Les actions nécessitant un choix de joueur ne sont pas faites pas l'IA
 */

public class OffensiveStrategy implements Strategy {

	private boolean utiliserFarfadet;
	private final int ENGRAIS = 2;
	private final int FARFADET = 3;
	
	public OffensiveStrategy() {
		super();
		this.utiliserFarfadet = false;
	}
	
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
			if(carteActive.isAllie = false){
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
		//System.out.println("La méthode choisirCarte de OffensiveStrategy retourne la carte " + choixCartePrivilegie.getNom());
		//System.out.println("La valeur de cette carte est : " + choixCartePrivilegie.getForce(FARFADET -1, saisonEnCours));
	
		return choixCartePrivilegie;
	}

	public void choisirAction(Carte c, Manche m, Partie p, Joueur j) {
		System.out.println("Le joueur virtuel choisi son action...");
		//System.out.println("La strategie est offensive donc il va choisir l'action FARFADET ou bien ENGRAIS, voire GEANT");
		
		if(utiliserFarfadet){
			c.choisirFarfadet(j, m, p);
			System.out.println("Le joueur virtuel à joué l'action FARFADET");
		}else{
			if(c.getForce(ENGRAIS, m.getSaisonEnCours()) <= j.getNbMenhirDuJoueur()){
				c.choisirEngrais(j, m, p);
				//System.out.println("Le joueur virtuel à joué l'action ENGRAIS");
			}else{
				c.choisirGeant(j, m, p);
				//System.out.println("Le joueur virtuel à joué l'action GEANT");
			}
		}
	}
}
