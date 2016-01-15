package partie;

/**
 * Bonjour!
 * 
 * Bienvenue sur notre version du Jeu du Menhir.
 * 
 * Avant de jouer avec des joueurs virtuels, il est nécessaire de préciser qu'ils ne sont
 * pas tout à fait opérationnels...
 * En effet, si le joueur virtuel doit choisir un autre joueur, il faudra encore lui donner
 * un petit coup de main en choisissant le joueur à sa place...
 * 
 * D'autres améliorations sont prévues et ceci ne reflète pas la qualité finale du programme.
 * 
 * Bon jeu!
 * 
 * 
 * @author 	Laurie BOURQUENCIER & Mathieu GUERY
 * 
 */

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;



public class JeuDuMenhir extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	/**
	 * p est une référence de partie
	 * 
	 * @see Partie
	 */
	private Partie p;
	
	String[] nb = { "1", "2", "3", "4", "5" };

	final JComboBox<Object> nbList = new JComboBox<Object>(nb);
	Checkbox rapide;
	Checkbox avancee;	
	JButton btnLancerPartie = new JButton();
	
	/**
	 * Constructeur de la classe JeuDuMenhir 
	 * getInstance() permet récupération de l'instance de la partie
	 * 
	 * @see #build() permet de construire le panel pour l'interface graphique swing
	 * 
	 */
	public JeuDuMenhir(){
		super();
		this.p = Partie.getInstance();
		build();
	}
	
	/**
	 * Le setter de build, setContentPane(), permet de faire appel à la méthode de construction du panel
	 * 
	 * @see #buildContentPane() construit le panel
	 * 
	 */
	private void build(){
		setContentPane(buildContentPane());
	}
	
	/**
	 * 
	 * @return panel conteneur élémentaire contenant d'autres composants
	 * @see ActionListener 
	 */
	private JPanel buildContentPane(){		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		setTitle("Initialisation du jeu du MENHIR"); 
		setSize(500,500); 
		setLocationRelativeTo(null); //centre la fenêtre sur l'écran
		setResizable(false); // ne permet pas le redimensionnement de la fenêtre 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		panel.setBackground(Color.lightGray);
		
		// label titre
		JLabel labelTitre = new JLabel("JEU DU MENHIR 2016 ");
		Font font = new Font("Arial",Font.BOLD,20);
		labelTitre.setFont(font);
		// ajout du titre dans le panel
		panel.add(labelTitre);
		labelTitre.setBounds(100, 370, 230, 55);
						
		// label type de partie
		JLabel label = new JLabel("Quel type de partie voulez-vous jouer ?");
		// ajout du label dans le panel
		panel.add(label);
		label.setBounds(15, 15, 300, 15);
		
		// instanciation des boutons radio pour le choix du type de partie
		final JRadioButton rapide = new JRadioButton();
		rapide.setText("Rapide");
		
		final JRadioButton avancee = new JRadioButton();
		avancee.setText("Avancée");
		
		/**
		 * un actionListener est un EventListener pour les JButton
		 * ici traitement de l'action effectuée sur les radioButton "avancee" et "rapide"
		 * permet ainsi de mémoriser le type de partie choisi par l'utilisateur ayant 
		 * interagi avec l'interface graphique swing d'initialisation de partie
		 * 
		 * @see EventListener, radioButton
		 * 
		 */
		rapide.addActionListener(new ActionListener() {
			/**
			 * Obligatoire car JeuDuMenhir implémente l'interface ActionListener
			 * 
			 * @param evt prend en paramètre un évènement de type ActionEvent
			 * @see ActionEvent
			 */
			public void actionPerformed(ActionEvent evt) {
				// au clique sur le bouton
				System.out.println("Vous jouez une PARTIE RAPIDE");	
				p.setDifficulte(0);
				avancee.setEnabled(false);
			}
		});
		
		avancee.addActionListener(new ActionListener() {
			/**
			 * Obligatoire car JeuDuMenhir implémente l'interface ActionListener
			 */
			public void actionPerformed(ActionEvent arg0) {
				// au clique sur le bouton
				System.out.println("Vous jouez une PARTIE AVANCEE");	
				p.setDifficulte(1);
				rapide.setEnabled(false);
			}
		});
		
		rapide.setBounds(50, 50, 80, 15);
		avancee.setBounds(150, 50, 80, 15);
		
		// ajout des boutons radios dans le panel
		panel.add(rapide);
		panel.add(avancee);
		
		// label demande nombre de joueurs virtuels
		JLabel lNbJV = new JLabel("Combien de joueurs virtuels dans la partie ?");
		panel.add(lNbJV);
		lNbJV.setBounds(15, 70, 300, 50);
		
		// combobox propose une liste de nombre de joueurs virtuels possible
		nbList.setSelectedIndex(4);
		nbList.addActionListener(this);
		panel.add(nbList);
		nbList.setBounds(50, 120, 80, 25);
		
		// label demande age au joueur physique
		JLabel lAgeJ = new JLabel("Quel âge avez-vous ?");
		panel.add(lAgeJ);
		lAgeJ.setBounds(15, 150, 300, 50);
		
		// JTextField permet à l'utilisateur d'entrer une donnée
		final JTextField ageJoueur = new JTextField(3);
		
		// champ texte
		ageJoueur.addActionListener(this);
		panel.add(ageJoueur);
		ageJoueur.setBounds(55, 200, 25, 25);
		
		//label demande nom du joueur physique
		JLabel lNomJ = new JLabel("Quel est votre nom ?");
		
		panel.add(lNomJ);
		lNomJ.setBounds(15, 220, 300, 50);
		
		// JTextField permet à l'utilisateur d'entrer une donnée
		final JTextField nomJoueur = new JTextField(20);
		
		nomJoueur.addActionListener(this);
		panel.add(nomJoueur);
		nomJoueur.setBounds(55, 270, 100, 25);
		
		/**
		 * instanciation et ajout d'un ActionListener sur le JTextField nomJoueur 
		 * @see ActionListener
		 */
		nomJoueur.addActionListener(new ActionListener() {
			/**
			 * Obligatoire car JeuDuMenhir implémente l'interface ActionListener
			 *  @param evt prend en paramètre un évènement de type ActionEvent
			 *  @see ActionEvent
			 */
			public void actionPerformed(ActionEvent evt) {
				String nJ = nomJoueur.getText();
				System.out.println("Nom du joueur physique : " + nJ );
				p.setNomJPhysique(nJ);	
			}
		});
		
		/**
		 * instanciation et ajout d'un ActionListener sur le JTextField ageJoueur
		 */
		ageJoueur.addActionListener(new ActionListener() {
			/**
			 * Obligatoire car JeuDuMenhir implémente l'interface ActionListener
			 * 
			 *  @param evt prend en paramètre un évènement de type ActionEvent
			 *  @see ActionEvent
			 */
			public void actionPerformed(ActionEvent evt) {
				int aJ = Integer.parseInt(ageJoueur.getText());System.out.println("age du j p : " + aJ);	
				System.out.println("Age du joueur physique : " + aJ);
				p.setAgeJPhysique(aJ);
			}
		});
		
		// bouton lancement de partie
		btnLancerPartie.setText("LANCER LA PARTIE");
		btnLancerPartie.addActionListener(this);
		panel.add(btnLancerPartie);
		btnLancerPartie.setBounds(15, 320, 180, 30);		

		return panel;
	}

	/**
	 * @param evt prend en paramètre un évènement de type ActionEvent
	 * @see ActionEvent
	 */
	public void actionPerformed(ActionEvent evt) {
			
		Object source = evt.getSource();		
		
			if(source == btnLancerPartie){

				this.p = Partie.getInstance();
				System.out.println("Lancement de la partie");
				p.setJoueursPhysiques(p.getAgeJPhysique(),p.getNomJPhysique());

				p.lancerPartie(); 
			}

			if(source == nbList){
				nbList.setEnabled(false);
				p.setNbrJoueursVirtuels(Integer.parseInt(nb[nbList.getSelectedIndex()]));
				p.setJoueursVirtuels(Integer.parseInt(nb[nbList.getSelectedIndex()]));
			}
	}
	
	/**
	 * La méthode main est statique, elle est chargée en mémoire au démarrage de l'applciation
	 * et peut donc être utilisée avant la création de tout objet
	 * 
	 * Cette méthode est donc le point d'entrée du programme
	 * 
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			/**
			 * la méthode run() doit être redéfinie pour contenir le code
			 * des traitements qui seront exécutés dans le thread
			 * 
			 * (non-Javadoc)
			 * @see java.lang.Runnable#run()
			 */
			public void run(){
				JeuDuMenhir fenetre = new JeuDuMenhir();
				fenetre.setVisible(true);
			}
			});
	}
}