package carte;

import java.util.InputMismatchException;

import joueur.Joueur;

import partie.Manche;
import partie.Partie;

/**
 * La classe ingr�dient h�rite de carte et permet de cr�er les cartes de type Ingr�dient
 * @author Mathieu & Laurie
 *
 */

public class Ingredient extends Carte {

	/**
	 * Le constructeur d'une carte ingr�dient, qui fait appel au constructeur de carte pour les cartes poss�dant un tableau � deux entr�es
	 * @param identifiant Le code de la carte
	 * @param tabValCarte Le tableau de valeurs de la carte en fonction des saisons et des actions
	 * @param nom Le nom de la carte pour le joueur
	 */
	public Ingredient(String identifiant, int[][] tabValCarte, String nom) {
		super(identifiant, tabValCarte, nom);
	}
	
	/**
	 * Cette m�thode renvoie la force de la carte en fonction des valeurs du tableau entr�es
	 * @param action L'action correspondant � la ligne du tableau de la carte
	 * @param saison La saison correspondant � la colonne du tableau de la carte
	 * @return La force de la carte selon les param�tres qui est un nombre entier
	 */
	public int getForce(int action, int saison) {
		return super.getForce(action, saison);
	}
	
	/**
	 * Renvoie le nom de la carte pour le joueur
	 * @return Le nom sous forme d'une cha�ne de caract�res
	 */
	public String getNom() {
		return super.getNom();
	}
	
	/**
	 * Cette m�thode est utilis�e par les joueurs physiques pour afficher et jouer une carte
	 * @param j Joueur qui joue
	 * @param m Manche en cours
	 * @param p Partie en cours
	 * @see Partie#getChoix()
	 */
	public void choisirAction(Joueur j, Manche m, Partie p) {
		super.choisirAction(j, m, p);
		System.out.println("Vous avez choisi la carte:\n");
		System.out.println(this.toString());
		System.out.println("Choisir l'action � effectuer : \n");
		System.out.println("1- G�ant");
		System.out.println("2- Engrais");
		System.out.println("3- Farfadet");
		
		int choixAction = 0;
		do {
			try{
				choixAction = p.getChoix();
			}catch(InputMismatchException e) {
				System.out.println("Entrez 1, 2 ou 3.\n");
			}
		}while(choixAction != 1 && choixAction != 2 && choixAction != 3);

		System.out.println("Vous avez choisi l'action : " + choixAction);
		
		if(choixAction == 1){this.choisirGeant(j, m, p);}
		else{
			if(choixAction == 2){this.choisirEngrais(j, m, p);}
				else{
				if(choixAction == 3){this.choisirFarfadet(j, m, p);}
					else{
					System.out.println("Veuillez entrer une valeur juste\n\n");
				}
			}
		}
		j.getMainDuJoueur().remove(this);
	}
	
	/**
	 * Cette m�thode permet d'effectuer l'action g�ant
	 * Elle incr�mente le nombre de graines du joueur en fonction de la force de l'action jou�e selon la saison
	 * @param j Joueur qui joue
	 * @param m Manche en cours
	 * @param p Partie en cours
	 */
	public void choisirGeant(Joueur j, Manche m, Partie p) {
		j.setNbGraineDuJoueur(j.getNbGraineDuJoueur(), this.getForce(0,m.getSaisonEnCours())); 
		System.out.println("Vous avez pris " + this.getForce(0, m.getSaisonEnCours()) + " graines dans le pot commun !");
		System.out.println("Vous avez maintenant " + j.getNbGraineDuJoueur() + " graines et " + j.getNbMenhirDuJoueur() + " menhirs\n");
	}
	
	/**
	 * Cette m�thode permet d'effectuer l'action engrais
	 * Regarde le nombre de graines du joueurs et transforme selon celui-ci le nombre appropri� de ces graines en m�nhirs
	 * @param j Joueur qui joue
	 * @param m Manche en cours
	 * @param p Partie en cours
	 */
	public void choisirEngrais(Joueur j, Manche m, Partie p) {
		if(j.getNbGraineDuJoueur() == 0){
			System.out.println("Vous n'avez pas de graines � planter!\n");
			System.out.println("Vous avez toujours " +j.getNbGraineDuJoueur()+ " graines et " +j.getNbMenhirDuJoueur()+ " menhirs.");
		}else{
			if(j.getNbGraineDuJoueur() >= this.getForce(1, m.getSaisonEnCours())){
				j.setNbMenhirDuJoueur(j.getNbMenhirDuJoueur()+this.getForce(1, m.getSaisonEnCours()));
				j.setNbGraineDuJoueur(j.getNbGraineDuJoueur()-this.getForce(1, m.getSaisonEnCours())); 
			
				System.out.println("R�sultat : " + this.getForce(1, m.getSaisonEnCours()) + " de vos graines sont devenues des menhirs !");
				System.out.println("Vous avez maintenant " + j.getNbGraineDuJoueur() + " graines et " + j.getNbMenhirDuJoueur() + " menhirs\n");
			}else{
				if(j.getNbGraineDuJoueur() < this.getForce(1, m.getSaisonEnCours())){
					j.setNbMenhirDuJoueur(j.getNbMenhirDuJoueur()+j.getNbGraineDuJoueur()); 
					j.setNbGraineDuJoueur(0); 
				
					System.out.println("Vous aviez moins de graines que la force de votre carte... Elles sont toutes devenues des menhirs.");
					System.out.println("Vous avez maintenant " + j.getNbGraineDuJoueur() + " graines et " + j.getNbMenhirDuJoueur() + " menhirs\n");
				}
			}
		}
	}		
	
	/**
	 * Cette m�thode permet d'effectuer l'action farfadet
	 * Si la partie est une partie rapide, elle regarde le nombre de graines qu'a le joueur choisi et vole le nombre de graines selon la carte
	 * Si c'est une partie avanc�e, elle regarde le nombre de Chiens de Garde du joueur et vole le nombre de graines en cons�quences
	 * @see ChienDeGarde
	 * @see Joueur#choisirJoueur(java.util.ArrayList)
	 * @param j Joueur qui joue
	 * @param m Manche en cours
	 * @param p Partie en cours
	 */
	public void choisirFarfadet(Joueur j, Manche m, Partie p) {
		if(j.isJoueurPhysique) {
			System.out.println("Veuillez choisir un joueur :\n");
			Joueur joueurChoisi = j.choisirJoueur(p.getListeJoueur());
			
			if(p.getPartieAvancee()) {
				System.out.println(joueurChoisi.getNom() + " poss�de " +joueurChoisi.getNbChiens() + " chiens de garde.\n");
				if(joueurChoisi.getNbGraineDuJoueur() == 0) {
					System.out.println("Il n'y avait aucune graine � voler...\n");
				}else{
					if(joueurChoisi.getNbGraineDuJoueur()<joueurChoisi.getNbChiens()) {
						System.out.println("Les graines de " +joueurChoisi.getNom() + " sont trop bien gard�es, vous n'avez pas pu en voler.");
					}else{
						if(joueurChoisi.getNbGraineDuJoueur() >= joueurChoisi.getNbChiens()) {
							int volPossible = joueurChoisi.getNbGraineDuJoueur()-joueurChoisi.getNbChiens();
							if(volPossible < this.getForce(2, m.getSaisonEnCours())) {
								j.setNbGraineDuJoueur(j.getNbGraineDuJoueur()+volPossible);
								System.out.println("Vous avez vol� " +volPossible+ " graines, vous en avez maintenant " +j.getNbGraineDuJoueur());
							}else{
								if(volPossible >= this.getForce(2, m.getSaisonEnCours())) {
									j.setNbGraineDuJoueur(j.getNbGraineDuJoueur()+this.getForce(2, m.getSaisonEnCours()));
									System.out.println("Vous avez vol� " +this.getForce(2, m.getSaisonEnCours())+ " graines, vous en avez maintenant " +j.getNbGraineDuJoueur());
								}
							}
						}
					}
				}
			}else{
				if(joueurChoisi.getNbGraineDuJoueur() == 0) {
					System.out.println("Il n'y aucune graine � voler...\n");
				}else{
					if(joueurChoisi.getNbGraineDuJoueur() < this.getForce(2, m.getSaisonEnCours())) {
						j.setNbGraineDuJoueur(j.getNbGraineDuJoueur()+joueurChoisi.getNbGraineDuJoueur());
						System.out.println("Il y avait moins de graines � voler que la force de votre carte.\nVous avez vol� les "+joueurChoisi.getNbGraineDuJoueur()+ "\nVous poss�dez maintenant " +j.getNbGraineDuJoueur() + " graines!");
						joueurChoisi.setNbGraineDuJoueur(0);
					}else{
						if(joueurChoisi.getNbGraineDuJoueur() >= this.getForce(2, m.getSaisonEnCours())) {
							j.setNbGraineDuJoueur(j.getNbGraineDuJoueur()+this.getForce(2, m.getSaisonEnCours()));
							joueurChoisi.setNbGraineDuJoueur(joueurChoisi.getNbGraineDuJoueur()-this.getForce(2, m.getSaisonEnCours()));
							System.out.println("Vous avez vol� " +this.getForce(2, m.getSaisonEnCours())+ " graine(s), vous en avez maintenant " +j.getNbGraineDuJoueur());
						}
					}
				}
			}
		}
		else{
			Joueur joueurChoisi = j.choisirJoueur(p.getListeJoueur());
			if(p.getPartieAvancee()) {
				System.out.println(joueurChoisi.getNom() + " poss�de " +joueurChoisi.getNbChiens() + " chiens de garde.\n");
				if(joueurChoisi.getNbGraineDuJoueur() == 0) {
					System.out.println("Il n'y avait aucune graine � voler...\n");
				}else{
					if(joueurChoisi.getNbGraineDuJoueur()<joueurChoisi.getNbChiens()) {
						System.out.println("Les graines de " +joueurChoisi.getNom() + " sont trop bien gard�es, vous n'avez pas pu en voler.");
					}else{
						if(joueurChoisi.getNbGraineDuJoueur() >= joueurChoisi.getNbChiens()) {
							int volPossible = joueurChoisi.getNbGraineDuJoueur()-joueurChoisi.getNbChiens();
							if(volPossible < this.getForce(2, m.getSaisonEnCours())) {
								j.setNbGraineDuJoueur(j.getNbGraineDuJoueur()+volPossible);
								System.out.println("Vous avez vol� " +volPossible+ " graines, vous en avez maintenant " +j.getNbGraineDuJoueur());
							}else{
								if(volPossible >= this.getForce(2, m.getSaisonEnCours())) {
									j.setNbGraineDuJoueur(j.getNbGraineDuJoueur()+this.getForce(2, m.getSaisonEnCours()));
									System.out.println("Vous avez vol� " +this.getForce(2, m.getSaisonEnCours())+ " graines, vous en avez maintenant " +j.getNbGraineDuJoueur());
								}
							}
						}
					}
				}
			}else{
				if(joueurChoisi.getNbGraineDuJoueur() == 0) {
					System.out.println("Il n'y aucune graine � voler...\n");
				}else{
					if(joueurChoisi.getNbGraineDuJoueur() < this.getForce(2, m.getSaisonEnCours())) {
						j.setNbGraineDuJoueur(j.getNbGraineDuJoueur()+joueurChoisi.getNbGraineDuJoueur());
						System.out.println("Il y avait moins de graines � voler que la force de votre carte.\nVous avez vol� les "+joueurChoisi.getNbGraineDuJoueur()+ "\nVous poss�dez maintenant " +j.getNbGraineDuJoueur() + " graines!");
						joueurChoisi.setNbGraineDuJoueur(0);
					}else{
						if(joueurChoisi.getNbGraineDuJoueur() >= this.getForce(2, m.getSaisonEnCours())) {
							j.setNbGraineDuJoueur(j.getNbGraineDuJoueur()+this.getForce(2, m.getSaisonEnCours()));
							joueurChoisi.setNbGraineDuJoueur(joueurChoisi.getNbGraineDuJoueur()-this.getForce(2, m.getSaisonEnCours()));
							System.out.println("Vous avez vol� " +this.getForce(2, m.getSaisonEnCours())+ " graine(s), vous en avez maintenant " +j.getNbGraineDuJoueur());
						}
					}
				}
			}
			
		}
	}
	
	/**
	 * Cette m�thode permet d'afficher une carte Ingr�dient
	 * @return Une cha�ne de caract�res affichant le nom et les valeurs de la carte
	 */
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("               " + this.getNom() + "\n");
		sb.append("         Print.  Ete  Automne  Hiver\n");
		sb.append("G�ant       " + this.getForce(0, 0) + "      " + this.getForce(0, 1) + "      " + this.getForce(0, 2) + "       " + this.getForce(0, 3) + "\n");
		sb.append("Engrais     " + this.getForce(1, 0) + "      " + this.getForce(1, 1) + "      " + this.getForce(1, 2) + "       " + this.getForce(1, 3) + "\n");
		sb.append("Farfadet    " + this.getForce(2, 0) + "      " + this.getForce(2, 1) + "      " + this.getForce(2, 2) + "       " + this.getForce(2, 3) + "\n");
		return sb.toString();
	}
	
}
