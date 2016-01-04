import java.util.*;

public abstract class Joueur implements Comparable<Joueur> {

	protected String nom;
	protected int age = 0;
	protected Strategy strategy;
	protected ArrayList<Carte> mainDuJoueur = new ArrayList<Carte>();
	private int nbMenhirDuJoueur = 0;
	private int nbMenhirTotalDuJoueur = 0;
	private int nbGraineDuJoueur = 0;
	private int nbChiens = 0;
	private boolean aDejaCommence;
	protected boolean isJoueurPhysique;	
	protected int indiceDeStratDuJoueurVirtuel;

	public Joueur(){
		this.nbGraineDuJoueur = 0;
		this.nbMenhirDuJoueur = 0;
		this.aDejaCommence = false;
	}
	
	public void afficherMainJoueur(){
		int i = 1;
		for(Carte c: this.mainDuJoueur){
			System.out.println("Carte " + i + " :");
			i++;
			System.out.println(c.toString());
		}
	}
	
	public int getNbGraineDuJoueur(){
		return nbGraineDuJoueur;
	}
	
	public void setNbGraineDuJoueur(int nbGraineDuJoueur, int nbAAjouterGraine){
		this.nbGraineDuJoueur = nbGraineDuJoueur + nbAAjouterGraine;
	}
	
	public void setNbGraineDuJoueur(int nbGraine){
		this.nbGraineDuJoueur = nbGraine;
	}
	
	public int getNbMenhirDuJoueur(){
		return nbMenhirDuJoueur;
	}
	
	public void setNbMenhirDuJoueur(int nbMenhirDuJoueur, int nbAAjouterMenhir){
		this.nbMenhirDuJoueur = nbMenhirDuJoueur + nbAAjouterMenhir;
	}
	
	public void setNbMenhirDuJoueur(int nbMenhir) {
		this.nbMenhirDuJoueur = nbMenhir;
	}
	
	public void setNbMenhirTotalDuJoueur(int nbMenhirDuJoueur) {
		this.nbMenhirTotalDuJoueur = this.nbMenhirTotalDuJoueur + nbMenhirDuJoueur;
	}
	
	public int getNbMenhirTotalDuJoueur() {
		return this.nbMenhirTotalDuJoueur;
	}
	
		
	public ArrayList<Carte> getMainDuJoueur() {
		return this.mainDuJoueur;
	}
	
	public boolean getADejaCommence() {
		return this.aDejaCommence;
	}
	
	public void setADejaCommence(boolean a) {
		this.aDejaCommence = a;
	}
	
	public int getNbChiens () {
		return this.nbChiens;
	}
	
	public void setNbChiens (int chiens) {
		this.nbChiens = chiens;
	}
	
	public void jouerCarte(Manche m, Partie p) {
		if (this.isJoueurPhysique) {
			System.out.println(+this.getNbGraineDuJoueur()+ " graines " +this.getNbMenhirDuJoueur()+ " menhirs.\n");
			this.choisirCarte(m, p);
			
			}else{
				System.out.println(+this.getNbGraineDuJoueur()+ " graines " +this.getNbMenhirDuJoueur()+ " menhirs.\n");
				Carte c = this.getStrategy().choisirCarte(m, p, this);
				this.getStrategy().choisirAction(c, m, p, this);
			}
	}

	public void choisirCarte(Manche m, Partie p){
		System.out.println("\nChoisir une carte à jouer : ");
		this.afficherMainJoueur();

		int choixCarte = 0;

		do {
			try {
				choixCarte = p.getChoix();
				
			}catch(InputMismatchException e) {
				System.out.println("Choisissez une carte en tapant 1, 2, 3, 4 ou 5.\n");
			}catch(IndexOutOfBoundsException e) {
				System.out.println("Vous n'avez pas autant de cartes!\nRéessayez.\n");

			}
			}while(choixCarte < 1 || choixCarte >this.mainDuJoueur.size());
			this.mainDuJoueur.get(choixCarte - 1).choisirAction(this, m, p);
	}
		
		
	public void choisirCarte(Manche m, Partie p, Joueur j){
					
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public abstract Joueur choisirJoueur(ArrayList<Joueur> listDeJoueur);
	
	public String getNom() {
		return nom;
	}
	
	public int compareTo(Joueur autreJoueur) {
		int nbj1 = this.getNbMenhirTotalDuJoueur();
		int nbj2 = autreJoueur.getNbMenhirTotalDuJoueur();
		int resultat;
		
		if (nbj1 > nbj2) {
			resultat = -1;
		}else{
			if (nbj1 == nbj2) {
				resultat =  0;
			}else{
				resultat = 1;
			}
		}
		return resultat;
	}
	
	public Strategy getStrategy() {
		return strategy;
	}
}
