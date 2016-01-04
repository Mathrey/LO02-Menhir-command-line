import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;

public class JoueurVirtuel extends Joueur {
	
	String strategieDuJVirtuel;
	private int indice;
	private Strategy strategy;	
 
	public JoueurVirtuel(int i) {
		super();
		this.isJoueurPhysique = true;
		this.indice = i;
		this.nom = "JoueurV"+i;  
		this.age = (int) (8+Math.random() * 80 ); 
		System.out.println("Age de JoueurV" + this.indice + " = " + this.age);
		int leNbRecup = this.attributionStrategieAJoueur();
		this.indiceDeStratDuJoueurVirtuel = leNbRecup;
		isJoueurPhysique = false;
		
		if(this.indiceDeStratDuJoueurVirtuel == 0){
			this.strategy = new OffensiveStrategy();
		}else{
			this.strategy = new DefensiveStrategy();
		}
	}
	
	public int attributionStrategieAJoueur(){
		Random rn = new Random();
		int leNbAleatoire = rn.nextInt(2);
		//System.out.println("Le nb aleatoire est " + leNbAleatoire);
		return leNbAleatoire;
	}
	
	public String getNom() {
		return nom;
	}

	public Strategy getStrategy() {
		super.getStrategy();
		return strategy;
	}
	
	public Joueur choisirJoueur(ArrayList<Joueur> joueurs) {
		Joueur choixJoueur = null;
		int nbGrainesMax = -1;
		int nbMenhirsMax = -1;
		for(Iterator<Joueur> it = joueurs.iterator(); it.hasNext();) {
		Joueur joueurActif = it.next();
		if (joueurActif.getNbGraineDuJoueur()>=nbGrainesMax) {
			if (joueurActif.getNbGraineDuJoueur() > nbGrainesMax) {
				choixJoueur = joueurActif;
				nbGrainesMax = joueurActif.getNbGraineDuJoueur();
				nbMenhirsMax = joueurActif.getNbMenhirDuJoueur();
			}
			else if (joueurActif.getNbMenhirDuJoueur() > nbMenhirsMax) {
				choixJoueur = joueurActif;
				nbMenhirsMax = joueurActif.getNbMenhirDuJoueur();
			}
		}
		}	
		return choixJoueur;
	}
}
