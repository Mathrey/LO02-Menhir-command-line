package carte;
import joueur.Joueur;
import partie.Manche;
import partie.Partie;

public abstract class Carte {

	private String nom;
	//private String cheminImage;
	private int[][] tableauIng;
	private int[] tableauAlli;
	private boolean isAllie;
	protected String nomReel;
	
	public Carte(String identifiant, int[][] tabValCarte, String nom) {
		this.nom = nom;
		this.tableauIng = tabValCarte;
		this.tableauAlli = null;
		//this.cheminImage = "img/" + nom + ".png";
		this.setAllie(false);
	}
	
	public Carte(String identifiant, int[] tabValCarte, String nom) {
		this.nom = nom;
		this.tableauIng = null;
		this.tableauAlli = tabValCarte;
		this.nomReel = identifiant;
	}
	
	public String getNom(){
		return this.nom;
	}
	

	public int getForce(int action, int saison) {
		return this.tableauIng[action][saison];
	}
	
	public int getForce(int saison) {
		return this.tableauAlli[saison];
	}

	public abstract void choisirGeant(Joueur j, Manche m, Partie p);
	public abstract void choisirEngrais(Joueur j, Manche m, Partie p);
	public abstract void choisirFarfadet(Joueur j, Manche m, Partie p);
	
	public void choisirAction(Joueur j, Manche m, Partie p){
		
	}

	public boolean isAllie() {
		return isAllie;
	}

	public boolean setAllie(boolean isAllie) {
		this.isAllie = isAllie;
		return isAllie;
	}
}
