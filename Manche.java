import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;

public class Manche {

	private int saisonEnCours;
	private String listeSaison[] = { "printemps", "été", "automne", "hiver" };
	private LinkedList<Carte> listeCIngredients = new LinkedList<Carte>();
	private LinkedList<Carte> listeCAllies = new LinkedList<Carte>();

	public Manche() {
		saisonEnCours = 0;
	}

	public void setSaisonEnCours(int saisonEnCours) {
		this.saisonEnCours = saisonEnCours;
	}

	public void distribuerCarteJoueur() {
		Partie p = Partie.getInstance();
		if (p.getPartieAvancee()) {

			this.initialisationListeCarte();
			Collections.shuffle(listeCIngredients);
			Collections.shuffle(listeCAllies);

			for (Iterator<Joueur> it = p.getListeJoueur().iterator(); it
					.hasNext();) {
				Joueur joueurActif = it.next();
				demanderGrainesOuCarteAllie(joueurActif);
			}

			for (Iterator<Joueur> it = p.getListeJoueur().iterator(); it.hasNext();) {
				Joueur joueurActif = it.next();
				for (int i = 0; i < 4; i++) {
					Carte c = this.listeCIngredients.removeFirst();
					joueurActif.getMainDuJoueur().add(c);
				}
			}
		} else {
			this.initialisationListeCarte();
			Collections.shuffle(listeCIngredients);

			for (Iterator<Joueur> it = p.getListeJoueur().iterator(); it
					.hasNext();) {
				Joueur joueurActif = it.next();
				for (int i = 0; i < 4; i++) {
					Carte c = this.listeCIngredients.removeFirst();
					joueurActif.getMainDuJoueur().add(c);
				}
				this.listeCIngredients.removeAll(joueurActif.getMainDuJoueur());
			}
		}
	}

	public void demanderGrainesOuCarteAllie(Joueur j) {
		Partie p = Partie.getInstance();
		System.out.println(j.getNom() + " choisissez-vous une carte allié (1) ou deux graines (2)?\n");
		int choix = 0;
		do {
			try {
				choix = p.getChoix();
				if (choix == 1) {
					Carte c = listeCAllies.get(0);
					j.getMainDuJoueur().add(c);
					listeCAllies.remove(0);
				} else {
					if (choix == 2) {
						j.setNbGraineDuJoueur(2);
					} else {
						System.out.println("Choix incorrect.\n");
					}
				}
			} catch (InputMismatchException e) {
				System.out
						.println("Veuillez entrer votre choix en tapant 1 ou 2.\n");
			}
		} while (choix != 1 && choix != 2);
	}

	public void attribuerJoueurDeDebut() {
		Partie p = Partie.getInstance();
		Joueur jQuiCommence = null;
		if (p.getPartieAvancee()) {
			int ageMini = 130;
			for (Iterator<Joueur> it = p.getListeJoueur().iterator(); it
					.hasNext();) {
				Joueur joueurActif = it.next();
				if ((ageMini > joueurActif.getAge())
						&& !(joueurActif.getADejaCommence())) {
					jQuiCommence = joueurActif;
					ageMini = joueurActif.getAge();
				}
			}
			int a = p.getListeJoueur().indexOf(p.getListeJoueur().get(0));
			int b = p.getListeJoueur().indexOf(jQuiCommence);
			jQuiCommence.setADejaCommence(true);
			Collections.swap(p.getListeJoueur(), a, b);
		} else {
			if (p.getListeJoueur().get(0).getAge() < p.getListeJoueur().get(1)
					.getAge()) {
				jQuiCommence = p.getListeJoueur().get(0);
			} else {
				jQuiCommence = p.getListeJoueur().get(1);
			}

			for (int i = 2; i < p.getListeJoueur().size(); i++) {

				if (p.getListeJoueur().get(i).getAge() < jQuiCommence.getAge()) {
					jQuiCommence = p.getListeJoueur().get(i);
				}

				int a = p.getListeJoueur().indexOf(p.getListeJoueur().get(0));
				int b = p.getListeJoueur().indexOf(jQuiCommence);

				Collections.swap(p.getListeJoueur(), a, b);
			}
		}
	}

	public int getSaisonEnCours() {
		return this.saisonEnCours;
	}

	public void jouerManche() {
		Partie p = Partie.getInstance();
		boolean partieAvancee = p.getPartieAvancee();
		if (partieAvancee) {
			this.attribuerJoueurDeDebut();
			this.distribuerCarteJoueur();
			System.out.println("\n-----------------\nDébut de la manche "
					+ p.getMancheNumero() + "!\n");

			for (int i = 0; i < 4; i++) {
				for (Iterator<Joueur> it = p.getListeJoueur().iterator(); it
						.hasNext();) {
					Joueur joueurActif = it.next();
					System.out.println("\nSAISON EN COURS : "
							+ this.listeSaison[this.getSaisonEnCours()] + "\n");
					System.out.println("C'est à " + joueurActif.getNom()
							+ " de jouer.\n");
					joueurActif.jouerCarte(this, p);
				}
				this.changerSaison();
			}

			for (Iterator<Joueur> it = p.getListeJoueur().iterator(); it
					.hasNext();) {
				Joueur joueurActif = it.next();
				joueurActif.getMainDuJoueur().clear();
				joueurActif.setNbChiens(0);
				joueurActif.setNbGraineDuJoueur(0);
			}

		} else {
			this.attribuerJoueurDeDebut();
			this.distribuerCarteJoueur();
			System.out.println("\n-----------------\nDébut de la manche!\n");

			for (int i = 0; i < 4; i++) {
				for (Iterator<Joueur> it = p.getListeJoueur().iterator(); it
						.hasNext();) {
					Joueur joueurActif = it.next();
					System.out.println("\nSAISON EN COURS : "
							+ this.listeSaison[this.getSaisonEnCours()] + "\n");
					System.out.println("C'est à " + joueurActif.getNom()
							+ " de jouer.\n");
					joueurActif.jouerCarte(this, p);
				}
				this.changerSaison();
			}
		}
	}

	public LinkedList<Carte> getListeCIngredients() {
		return this.listeCIngredients;
	}

	public LinkedList<Carte> getListeCAllies() {
		return this.listeCAllies;
	}

	public void changerSaison() {
		int nouvSaison = this.saisonEnCours;
		System.out.println("La saison " + this.listeSaison[this.saisonEnCours]
				+ " est terminée.\n");
		nouvSaison++;
		if (nouvSaison == 4) {
			nouvSaison = 0;
		} else {
			System.out.println("La nouvelle saison est "
					+ this.listeSaison[this.saisonEnCours + 1]);
		}
		this.saisonEnCours = nouvSaison;
	}

	public void initialisationListeCarte() {

		int tabIng1[][] = { { 1, 1, 1, 1 }, { 2, 0, 1, 1 }, { 2, 0, 2, 0 } };
		int tabIng2[][] = { { 2, 0, 1, 1 }, { 1, 3, 0, 0 }, { 0, 1, 2, 1 } };
		int tabIng3[][] = { { 0, 0, 4, 0 }, { 0, 2, 2, 0 }, { 0, 0, 1, 3 } };
		int tabIng4[][] = { { 1, 3, 1, 0 }, { 1, 2, 1, 1 }, { 0, 1, 4, 0 } };
		int tabIng5[][] = { { 2, 1, 1, 1 }, { 1, 0, 2, 2 }, { 3, 0, 0, 2 } };
		int tabIng6[][] = { { 1, 2, 2, 0 }, { 1, 1, 2, 1 }, { 2, 0, 1, 2 } };
		int tabIng7[][] = { { 2, 1, 1, 2 }, { 1, 1, 1, 3 }, { 2, 0, 2, 2 } };
		int tabIng8[][] = { { 0, 3, 0, 3 }, { 2, 1, 3, 0 }, { 1, 1, 3, 1 } };
		int tabIng9[][] = { { 1, 2, 1, 2 }, { 1, 0, 1, 4 }, { 2, 4, 0, 0 } };
		int tabIng10[][] = { { 1, 3, 1, 2 }, { 2, 1, 2, 2 }, { 0, 0, 3, 4 } };
		int tabIng11[][] = { { 2, 2, 0, 3 }, { 1, 1, 4, 1 }, { 1, 2, 1, 3 } };
		int tabIng12[][] = { { 2, 2, 3, 1 }, { 2, 3, 0, 3 }, { 1, 1, 3, 3 } };
		int tabIng13[][] = { { 2, 2, 3, 1 }, { 2, 3, 0, 3 }, { 1, 1, 3, 3 } };
		int tabIng14[][] = { { 2, 2, 2, 2 }, { 0, 4, 4, 0 }, { 1, 3, 2, 2 } };
		int tabIng15[][] = { { 3, 1, 3, 1 }, { 1, 4, 2, 1 }, { 2, 4, 1, 1 } };
		int tabIng16[][] = { { 4, 1, 1, 1 }, { 1, 2, 1, 3 }, { 1, 2, 2, 2 } };
		int tabIng17[][] = { { 2, 3, 2, 0 }, { 0, 4, 3, 0 }, { 2, 1, 1, 3 } };
		int tabIng18[][] = { { 2, 2, 3, 0 }, { 1, 1, 1, 4 }, { 2, 0, 3, 2 } };
		int tabIng19[][] = { { 3, 1, 4, 1 }, { 2, 1, 3, 3 }, { 2, 3, 2, 2 } };
		int tabIng20[][] = { { 2, 4, 1, 2 }, { 2, 2, 2, 3 }, { 1, 4, 3, 1 } };
		int tabIng21[][] = { { 3, 3, 3, 0 }, { 1, 3, 3, 2 }, { 2, 3, 1, 3 } };
		int tabIng22[][] = { { 1, 2, 2, 1 }, { 1, 2, 3, 0 }, { 0, 2, 2, 2 } };
		int tabIng23[][] = { { 4, 0, 1, 1 }, { 1, 1, 3, 1 }, { 0, 0, 3, 3 } };
		int tabIng24[][] = { { 2, 0, 1, 3 }, { 0, 3, 0, 3 }, { 1, 2, 2, 1 } };

		int tabTaupeGeante1[] = { 1, 1, 1, 1 };
		int tabTaupeGeante2[] = { 0, 2, 2, 0 };
		int tabTaupeGeante3[] = { 0, 1, 2, 1 };
		int tabChienDeGarde1[] = { 2, 0, 2, 0 };
		int tabChienDeGarde2[] = { 1, 2, 0, 1 };
		int tabChienDeGarde3[] = { 0, 1, 3, 0 };

		this.listeCIngredients.add(new Ingredient("ing1", tabIng1,
				"Rayon de Lune"));
		this.listeCIngredients.add(new Ingredient("ing2", tabIng2,
				"Rayon de Lune"));
		this.listeCIngredients.add(new Ingredient("ing3", tabIng3,
				"Rayon de Lune"));
		this.listeCIngredients.add(new Ingredient("ing4", tabIng4,
				"Chant de sirène"));
		this.listeCIngredients.add(new Ingredient("ing5", tabIng5,
				"Chant de sirène"));
		this.listeCIngredients.add(new Ingredient("ing6", tabIng6,
				"Chant de sirène"));
		this.listeCIngredients.add(new Ingredient("ing7", tabIng7,
				"Larmes de dryade"));
		this.listeCIngredients.add(new Ingredient("ing8", tabIng8,
				"Larmes de dryade"));
		this.listeCIngredients.add(new Ingredient("ing9", tabIng9,
				"Larmes de dryade"));
		this.listeCIngredients.add(new Ingredient("ing10", tabIng10,
				"Fontaine d'eau pure"));
		this.listeCIngredients.add(new Ingredient("ing11", tabIng11,
				"Fontaine d'eau pure"));
		this.listeCIngredients.add(new Ingredient("ing12", tabIng12,
				"Fontaine d'eau pure"));
		this.listeCIngredients.add(new Ingredient("ing13", tabIng13,
				"Poudre d'or"));
		this.listeCIngredients.add(new Ingredient("ing14", tabIng14,
				"Poudre d'or"));
		this.listeCIngredients.add(new Ingredient("ing15", tabIng15,
				"Poudre d'or"));
		this.listeCIngredients.add(new Ingredient("ing16", tabIng16,
				"Racine d'arc en ciel"));
		this.listeCIngredients.add(new Ingredient("ing17", tabIng17,
				"Racine d'arc en ciel"));
		this.listeCIngredients.add(new Ingredient("ing18", tabIng18,
				"Racine d'arc en ciel"));
		this.listeCIngredients.add(new Ingredient("ing19", tabIng19,
				"Esprit de dolmen"));
		this.listeCIngredients.add(new Ingredient("ing20", tabIng20,
				"Esprit de dolmen"));
		this.listeCIngredients.add(new Ingredient("ing21", tabIng21,
				"Esprit de dolmen"));
		this.listeCIngredients.add(new Ingredient("ing22", tabIng22,
				"Rires de fées"));
		this.listeCIngredients.add(new Ingredient("ing23", tabIng23,
				"Rires de fées"));
		this.listeCIngredients.add(new Ingredient("ing24", tabIng24,
				"Rires de fées"));

		this.listeCAllies.add(new ChienDeGarde("CdG1", tabChienDeGarde1,
				"Chien de garde"));
		this.listeCAllies.add(new ChienDeGarde("CdG2", tabChienDeGarde2,
				"Chien de garde"));
		this.listeCAllies.add(new ChienDeGarde("CdG3", tabChienDeGarde3,
				"Chien de garde"));
		this.listeCAllies.add(new TaupeGeante("TaupeG1", tabTaupeGeante1,
				"Taupe Géante"));
		this.listeCAllies.add(new TaupeGeante("TaupeG2", tabTaupeGeante2,
				"Taupe Géante"));
		this.listeCAllies.add(new TaupeGeante("TaupeG3", tabTaupeGeante3,
				"Taupe Géante"));
	}
}
