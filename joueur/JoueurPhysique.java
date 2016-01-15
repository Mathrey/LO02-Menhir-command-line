package joueur;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


import partie.Manche;
import partie.Partie;

/**
 * Cette classe permet d'instancier un joueur physique
 * @author Mathieu & Laurie
 *
 */

public class JoueurPhysique extends Joueur {

	/**
	 * Choix d'un joueur pour une action
	 */
	Joueur joueurChoisi = null;
	/**
	 * Un scanner pour récupérer l'entrée de l'utilisateur de type entier
	 */
	private Scanner scanChoix;
	/**
	 * Un scanner pour récupérer l'entrée de l'utilisateur de type caractère
	 */
	private Scanner scanNom;

	/**
	 * Constructeur d'un joueur physique avec des paramètres en entrée pour l'interface graphique
	 * @param age Âge du joueur
	 * @param nom Nom du joueur
	 */
	public JoueurPhysique(int age, String nom) {
		super();
		this.isJoueurPhysique = true;
		this.setAge(age);
		this.setNom(nom);
	}
	
	/**
	 * Constructeur d'un joueur physique sans paramètres en entrée pour une interface en ligne de commande
	 */
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
	
	/**
	 * Fonction jouer carte faisant appel à la même fonction présente dans joueur
	 * @param m Manche en cours
	 * @param p Partie en cours
	 */
	public void jouerCarte(Manche m, Partie p){
		super.jouerCarte(m, p);
	}
	
	/**
	 * Fonction permettant à un joueur physique de choisir un autre joueur
	 * @param listDeJoueur Liste des joueurs de la partie
	 */
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
	
	/**
	 * Méthode permettant de récupérer le choix d'un utilisateur
	 * @return Un entier entré par l'utilisateur
	 */
	public int getChoix() {
		scanChoix = new Scanner(System.in);
		int choix = scanChoix.nextInt();
		return choix;
	}
	
	/**
	 * Méthode permettant de modifier le nom du joueur physique
	 * @param nom Nouveau nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
