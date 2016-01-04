import java.util.InputMismatchException;

public class Ingredient extends Carte {

	public Ingredient(String identifiant, int[][] tabValCarte, String nom) {
		super(identifiant, tabValCarte, nom);
	}
	
	public int getForce(int action, int saison) {
		return super.getForce(action, saison);
	}
	
	public String getNom() {
		return super.getNom();
	}
	
	public void choisirAction(Joueur j, Manche m, Partie p) {
		super.choisirAction(j, m, p);
		System.out.println("Vous avez choisi la carte:\n");
		System.out.println(this.toString());
		System.out.println("Choisir l'action à effectuer : \n");
		System.out.println("1- Géant");
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
	
	public void choisirGeant(Joueur j, Manche m, Partie p) {
		j.setNbGraineDuJoueur(j.getNbGraineDuJoueur(), this.getForce(0,m.getSaisonEnCours())); 
		System.out.println("Vous avez pris " + this.getForce(0, m.getSaisonEnCours()) + " graines dans le pot commun !");
		System.out.println("Vous avez maintenant " + j.getNbGraineDuJoueur() + " graines et " + j.getNbMenhirDuJoueur() + " menhirs\n");
	}
	
	public void choisirEngrais(Joueur j, Manche m, Partie p) {
		if(j.getNbGraineDuJoueur() == 0){
			System.out.println("Vous n'avez pas de graines à planter!\n");
			System.out.println("Vous avez toujours " +j.getNbGraineDuJoueur()+ " graines et " +j.getNbMenhirDuJoueur()+ " menhirs.");
		}else{
			if(j.getNbGraineDuJoueur() >= this.getForce(1, m.getSaisonEnCours())){
				j.setNbMenhirDuJoueur(j.getNbMenhirDuJoueur()+this.getForce(1, m.getSaisonEnCours()));
				j.setNbGraineDuJoueur(j.getNbGraineDuJoueur()-this.getForce(1, m.getSaisonEnCours())); 
			
				System.out.println("Résultat : " + this.getForce(1, m.getSaisonEnCours()) + " de vos graines sont devenues des menhirs !");
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
	
	public void choisirFarfadet(Joueur j, Manche m, Partie p) {
		if(j.isJoueurPhysique) {
			System.out.println("Veuillez choisir un joueur :\n");
			Joueur joueurChoisi = j.choisirJoueur(p.getListeJoueur());
			
			if(p.getPartieAvancee()) {
				System.out.println(joueurChoisi.getNom() + " possède " +joueurChoisi.getNbChiens() + " chiens de garde.\n");
				if(joueurChoisi.getNbGraineDuJoueur() == 0) {
					System.out.println("Il n'y avait aucune graine à voler...\n");
				}else{
					if(joueurChoisi.getNbGraineDuJoueur()<joueurChoisi.getNbChiens()) {
						System.out.println("Les graines de " +joueurChoisi.getNom() + " sont trop bien gardées, vous n'avez pas pu en voler.");
					}else{
						if(joueurChoisi.getNbGraineDuJoueur() >= joueurChoisi.getNbChiens()) {
							int volPossible = joueurChoisi.getNbGraineDuJoueur()-joueurChoisi.getNbChiens();
							if(volPossible < this.getForce(2, m.getSaisonEnCours())) {
								j.setNbGraineDuJoueur(j.getNbGraineDuJoueur()+volPossible);
								System.out.println("Vous avez volé " +volPossible+ " graines, vous en avez maintenant " +j.getNbGraineDuJoueur());
							}else{
								if(volPossible >= this.getForce(2, m.getSaisonEnCours())) {
									j.setNbGraineDuJoueur(j.getNbGraineDuJoueur()+this.getForce(2, m.getSaisonEnCours()));
									System.out.println("Vous avez volé " +this.getForce(2, m.getSaisonEnCours())+ " graines, vous en avez maintenant " +j.getNbGraineDuJoueur());
								}
							}
						}
					}
				}
			}else{
				if(joueurChoisi.getNbGraineDuJoueur() == 0) {
					System.out.println("Il n'y aucune graine à voler...\n");
				}else{
					if(joueurChoisi.getNbGraineDuJoueur() < this.getForce(2, m.getSaisonEnCours())) {
						j.setNbGraineDuJoueur(j.getNbGraineDuJoueur()+joueurChoisi.getNbGraineDuJoueur());
						System.out.println("Il y avait moins de graines à voler que la force de votre carte.\nVous avez volé les "+joueurChoisi.getNbGraineDuJoueur()+ "\nVous possédez maintenant " +j.getNbGraineDuJoueur() + " graines!");
						joueurChoisi.setNbGraineDuJoueur(0);
					}else{
						if(joueurChoisi.getNbGraineDuJoueur() >= this.getForce(2, m.getSaisonEnCours())) {
							j.setNbGraineDuJoueur(j.getNbGraineDuJoueur()+this.getForce(2, m.getSaisonEnCours()));
							joueurChoisi.setNbGraineDuJoueur(joueurChoisi.getNbGraineDuJoueur()-this.getForce(2, m.getSaisonEnCours()));
							System.out.println("Vous avez volé " +this.getForce(2, m.getSaisonEnCours())+ " graine(s), vous en avez maintenant " +j.getNbGraineDuJoueur());
						}
					}
				}
			}
		}
		else{
			Joueur joueurChoisi = j.choisirJoueur(p.getListeJoueur());
			if(p.getPartieAvancee()) {
				System.out.println(joueurChoisi.getNom() + " possède " +joueurChoisi.getNbChiens() + " chiens de garde.\n");
				if(joueurChoisi.getNbGraineDuJoueur() == 0) {
					System.out.println("Il n'y avait aucune graine à voler...\n");
				}else{
					if(joueurChoisi.getNbGraineDuJoueur()<joueurChoisi.getNbChiens()) {
						System.out.println("Les graines de " +joueurChoisi.getNom() + " sont trop bien gardées, vous n'avez pas pu en voler.");
					}else{
						if(joueurChoisi.getNbGraineDuJoueur() >= joueurChoisi.getNbChiens()) {
							int volPossible = joueurChoisi.getNbGraineDuJoueur()-joueurChoisi.getNbChiens();
							if(volPossible < this.getForce(2, m.getSaisonEnCours())) {
								j.setNbGraineDuJoueur(j.getNbGraineDuJoueur()+volPossible);
								System.out.println("Vous avez volé " +volPossible+ " graines, vous en avez maintenant " +j.getNbGraineDuJoueur());
							}else{
								if(volPossible >= this.getForce(2, m.getSaisonEnCours())) {
									j.setNbGraineDuJoueur(j.getNbGraineDuJoueur()+this.getForce(2, m.getSaisonEnCours()));
									System.out.println("Vous avez volé " +this.getForce(2, m.getSaisonEnCours())+ " graines, vous en avez maintenant " +j.getNbGraineDuJoueur());
								}
							}
						}
					}
				}
			}else{
				if(joueurChoisi.getNbGraineDuJoueur() == 0) {
					System.out.println("Il n'y aucune graine à voler...\n");
				}else{
					if(joueurChoisi.getNbGraineDuJoueur() < this.getForce(2, m.getSaisonEnCours())) {
						j.setNbGraineDuJoueur(j.getNbGraineDuJoueur()+joueurChoisi.getNbGraineDuJoueur());
						System.out.println("Il y avait moins de graines à voler que la force de votre carte.\nVous avez volé les "+joueurChoisi.getNbGraineDuJoueur()+ "\nVous possédez maintenant " +j.getNbGraineDuJoueur() + " graines!");
						joueurChoisi.setNbGraineDuJoueur(0);
					}else{
						if(joueurChoisi.getNbGraineDuJoueur() >= this.getForce(2, m.getSaisonEnCours())) {
							j.setNbGraineDuJoueur(j.getNbGraineDuJoueur()+this.getForce(2, m.getSaisonEnCours()));
							joueurChoisi.setNbGraineDuJoueur(joueurChoisi.getNbGraineDuJoueur()-this.getForce(2, m.getSaisonEnCours()));
							System.out.println("Vous avez volé " +this.getForce(2, m.getSaisonEnCours())+ " graine(s), vous en avez maintenant " +j.getNbGraineDuJoueur());
						}
					}
				}
			}
			
		}
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("               " + this.getNom() + "\n");
		sb.append("         Print.  Ete  Automne  Hiver\n");
		sb.append("Géant       " + this.getForce(0, 0) + "      " + this.getForce(0, 1) + "      " + this.getForce(0, 2) + "       " + this.getForce(0, 3) + "\n");
		sb.append("Engrais     " + this.getForce(1, 0) + "      " + this.getForce(1, 1) + "      " + this.getForce(1, 2) + "       " + this.getForce(1, 3) + "\n");
		sb.append("Farfadet    " + this.getForce(2, 0) + "      " + this.getForce(2, 1) + "      " + this.getForce(2, 2) + "       " + this.getForce(2, 3) + "\n");
		return sb.toString();
	}
	
}
