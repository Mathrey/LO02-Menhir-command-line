public class ChienDeGarde extends Carte{
	
	public ChienDeGarde(String identifiant, int[] tabValCarte, String nom) {
		super(identifiant, tabValCarte, nom);
		this.isAllie = true;
	}

	public int getForce (int saison) {
		return super.getForce(saison);
	}
	
	public void choisirAction(Joueur j, Manche m, Partie p) {
		super.choisirAction(j, m, p);
		System.out.println("Vous avez choisi la carte:\n");
		System.out.println(this.toString());
		j.setNbChiens(this.getForce(m.getSaisonEnCours()));
		System.out.println("Vos chiens de garde permettent d'empêcher le vol de " +j.getNbChiens()+ " graines jusqu'à la fin de cette manche!\n");
		j.getMainDuJoueur().remove(this);
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("               " + this.getNom() + "\n");
		sb.append("         Primpt.  Ete  Automne  Hiver\n");
		sb.append("Chien       " + this.getForce(0) + "      " + this.getForce(1) + "      " + this.getForce(2) + "       " + this.getForce(3) + "\n");
		return sb.toString();
	}

	@Override
	public void choisirGeant(Joueur j, Manche m, Partie p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void choisirEngrais(Joueur j, Manche m, Partie p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void choisirFarfadet(Joueur j, Manche m, Partie p) {
		// TODO Auto-generated method stub
		
	}


}
