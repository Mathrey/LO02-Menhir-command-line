import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class JoueurPhysique extends Joueur {

	Joueur joueurChoisi = null;
	private Scanner scanChoix;
	private Scanner scanNom;

	public JoueurPhysique(int age, String nom) {
		super();
		this.isJoueurPhysique = true;
		this.setAge(age);
		this.setNom(nom);
	}
	
	public JoueurPhysique() {
		super();
		this.isJoueurPhysique = false;
		
		System.out.println("Nom du joueur :");
		scanNom = new Scanner(System.in);
		String nomJPhysique = scanNom.nextLine();
		
		System.out.println("Age du joueur :");
		int ageJoueurPhysique = 0;
		do {
			try {
				ageJoueurPhysique = getChoix();
				if (ageJoueurPhysique < 8) {
					System.out.println("Vous êtes un peu jeune pour jouer! Le jeu du Menhir est pour les 8 ans et plus.\nEntrez un autre âge.\n");
				}
				else{
					if (ageJoueurPhysique > 122) {
						System.out.println("Vous êtes plus vieux que le record de longévité humaine!\nSérieusement, entrez votre VRAI âge. :)\n");
					}
				}
			}catch(InputMismatchException e) {
				System.out.println("Merci d'entrer un nombre entier.\n");
			}
		}while (ageJoueurPhysique < 8 || ageJoueurPhysique > 122);
		
		setAge(ageJoueurPhysique);
		setNom(nomJPhysique);
		isJoueurPhysique = true;
	}

	public void jouerCarte(Manche m, Partie p){
		super.jouerCarte(m, p);
	}
	
	public Joueur choisirJoueur(ArrayList<Joueur> listDeJoueur){
		int i = 0;
			for(Joueur j : listDeJoueur){
				if(j.getNom() != this.getNom()){
					System.out.println(+i+ " pour choisir " +j.nom+ ", " +j.getNbGraineDuJoueur()+ " graines et " +j.getNbMenhirDuJoueur()+ " ménhirs." );					
				}
				i++;
			}
			
		int choixJoueur = -1;
		do {
			try {
				choixJoueur = getChoix();
				
				if (listDeJoueur.get(choixJoueur) == this) {
					choixJoueur = -1;
					System.out.println("Ce n'est pas un choix valide, réessayez.\n");
				}
				
			}catch(InputMismatchException e) {
				System.out.println("Choisissez un joueur en entrant un nombre entier\n.");
			}catch(IndexOutOfBoundsException e) {
				System.out.println("Il n'y a pas de joueurs correspondant à ce choix.");
			}
		}while(choixJoueur < 0 || choixJoueur > listDeJoueur.size()-1);
		return listDeJoueur.get(choixJoueur);
	}
	
	public int getChoix() {
		scanChoix = new Scanner(System.in);
		int choix = scanChoix.nextInt();
		return choix;
	}
	
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
