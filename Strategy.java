public interface Strategy {
	
	public Carte choisirCarte(Manche m, Partie p, Joueur j);
	public void choisirAction(Carte c, Manche m, Partie p, Joueur j);

}