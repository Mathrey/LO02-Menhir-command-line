/*
 * 
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
 * Laurie BOURQUENCIER et Mathieu GUERY
 * 
 */

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class JeuDuMenhir extends JFrame implements ActionListener, ItemListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Partie p;
//	
//	Checkbox rapide = new Checkbox("RAPIDE");
//	Checkbox avancee  = new Checkbox("AVANCEE");;
	
	String[] nb = { "1", "2", "3", "4", "5" };
	final JComboBox<Object> nbList = new JComboBox<Object>(nb);
	JTextField nomJoueur = new JTextField(2);
	JTextField ageJoueur = new JTextField(2);
//	JTextField nomJoueur;
//	JTextField ageJoueur;
	Checkbox rapide;
	Checkbox avancee;
//	JComboBox<Object> nbList;
	
	
	JButton btnLancerPartie = new JButton();
	
	public JeuDuMenhir(){
		super();
		p = Partie.getInstance();
		build(); // initialisation de la fenetre
	}
	
	private void build(){
		setContentPane(buildContentPane());
	}
	
	private JPanel buildContentPane(){
		
//		String[] nb = { "1", "2", "3", "4", "5" };
//		final JComboBox<Object> nbList = new JComboBox<Object>(nb);
//		JTextField nomJoueur = new JTextField(2);
//		JTextField ageJoueur = new JTextField(2);
		
		
		JPanel panel = new JPanel();
		//panel.setLayout(new FlowLayout());
		panel.setLayout(null);
		
		/*GridLayout gl = new GridLayout(3, 2);
		gl.setHgap(10); //Cinq pixels d'espace entre les colonnes (H comme Horizontal)
		gl.setVgap(10); //Cinq pixels d'espace entre les lignes (V comme Vertical) 
		*/
		
		setTitle("Initialisation du jeu du MENHIR"); 
		setSize(500,500); 
		setLocationRelativeTo(null); //centre la fenêtre sur l'écran
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		panel.setBackground(Color.lightGray);
		
		// label
		JLabel label = new JLabel("Quel type de partie voulez-vous jouer ?");
		label.setBounds(15, 15, 300, 15);
		
		// checkbox
		Checkbox rapide = new Checkbox("RAPIDE");
		Checkbox avancee = new Checkbox("AVANCEE");
		//Checkbox rapide = new Checkbox("RAPIDE");
		rapide.setBounds(50, 50, 80, 15);
		rapide.addItemListener(this);
		panel.add(rapide);
		
		// checkbox
		panel.add(avancee);
		avancee.setBounds(150, 50, 80, 15);
		avancee.addItemListener(this);
		
		// label	
		JLabel label2 = new JLabel("Combien de joueurs virtuels dans la partie ?");
		panel.add(label2);
		label2.setBounds(15, 70, 300, 50);
		
		// combobox
		nbList.setSelectedIndex(4);
		nbList.addActionListener(this);
		panel.add(nbList);
		nbList.setBounds(50, 120, 80, 25);
		
		// label
		JLabel label3 = new JLabel("Quel âge avez-vous ?");
		panel.add(label3);
		label3.setBounds(15, 150, 300, 50);
		
		// champ texte
		panel.add(ageJoueur);
		ageJoueur.setBounds(55, 200, 25, 25);
		ageJoueur.addActionListener(this);
		
		//label
		JLabel label4 = new JLabel("Quel est votre nom ?");
		panel.add(label4);
		label4.setBounds(15, 220, 300, 50);
		
		// champ texte
		nomJoueur.addActionListener(this);
		panel.add(nomJoueur);
		nomJoueur.setBounds(55, 270, 100, 25);
		//nomJoueur.addCaretListener(this);
		
		
		// bouton lancement de partie
		btnLancerPartie.setText("LANCER LA PARTIE");
		btnLancerPartie.addActionListener(this);
		panel.add(btnLancerPartie);
		btnLancerPartie.setBounds(15, 320, 180, 30);

		return panel;
		}

	// /!\ les SYSO ne s'affichent pas lors de l'éxecution...
	// 		le renvoie des elements entrés par l'utilisateur n'a pas l'air de fonctionner
	//		peut-être un problème au niveau de la récupération des valeurs qui n'est pas celles attendues 
	//			ce qui provoquerait un conflit ?
	// /!\
		public void actionPerformed(ActionEvent evt) {
			
		Object source = evt.getSource();		
		
			if(source == btnLancerPartie){
				this.p = Partie.getInstance();
				System.out.println("Lancement de la partie");
				p.lancerPartie(); 
			}
			
			if(source == ageJoueur){
				//this.p = Partie.getInstance();
				int aJ = Integer.parseInt(ageJoueur.getText());System.out.println("age du j p : " + aJ);	
				p.setAgeJPhysique(aJ);
				
			}
			
			if(source == nomJoueur){
				//this.p = Partie.getInstance();
				String nJ = nomJoueur.getText();
				p.setNomJPhysique(nJ);
				System.out.println("nom du j p : " + nJ);
			}
			
			if(source == nbList){
				//this.p = Partie.getInstance();
				//p.setNbrJoueursVirtuels((Integer) nbList.getSelectedItem());
				p.setNbrJoueursVirtuels(nbList.getSelectedIndex());
				System.out.println(nbList.getSelectedIndex());
			}
	}
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				JeuDuMenhir fenetre = new JeuDuMenhir();
				fenetre.setVisible(true);
			}
			});
	}

	public void itemStateChanged(ItemEvent e) {
		
		Object source = e.getSource();
		
			if(e.getItem() == rapide){
				p.setDifficulte(0);
				System.out.println("partie rapide");
			}else if(e.getItem() == avancee){
				p.setDifficulte(1);
				System.out.println("partie avancee");
			}

	}    
}
