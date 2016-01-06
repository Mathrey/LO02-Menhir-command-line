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
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;


public class JeuDuMenhir extends JFrame implements ActionListener, ItemListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Partie p;
	
	String[] nb = { "1", "2", "3", "4", "5" };

	final JComboBox<Object> nbList = new JComboBox<Object>(nb);
	//final JComboBox<Integer> nbList = new JComboBox<Integer>(nb);
//	JTextField nomJoueur = new JTextField(20);
//	JTextField ageJoueur = new JTextField(3);
	Checkbox rapide;
	Checkbox avancee;
	
	
	JButton btnLancerPartie = new JButton();
	
	public JeuDuMenhir(){
		super();

		this.p = Partie.getInstance();

		build(); // initialisation de la fenetre
	}
	
	private void build(){
		setContentPane(buildContentPane());
	}
	
	private JPanel buildContentPane(){		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		setTitle("Initialisation du jeu du MENHIR"); 
		setSize(500,500); 
		setLocationRelativeTo(null); //centre la fenêtre sur l'écran
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		panel.setBackground(Color.lightGray);
		
		// label
				JLabel labelTitre = new JLabel("JEU DU MENHIR 2016 ");
				Font font = new Font("Arial",Font.BOLD,20);
				labelTitre.setFont(font);
				panel.add(labelTitre);
				labelTitre.setBounds(100, 370, 230, 55);
				
		// label
		JLabel label = new JLabel("Quel type de partie voulez-vous jouer ?");
		panel.add(label);
		label.setBounds(15, 15, 300, 15);
		
		final JRadioButton rapide = new JRadioButton();
		rapide.setText("Rapide");
		final JRadioButton avancee = new JRadioButton();
		avancee.setText("Avancée");
		//rapide.setActionCommand(rapide);
		rapide.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Vous jouez une PARTIE RAPIDE");	
				p.setDifficulte(0);
				avancee.setEnabled(false);
			}
		});
		
		avancee.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Vous jouez une PARTIE AVANCEE");	
				p.setDifficulte(1);
				rapide.setEnabled(false);
			}
		});
		
		rapide.setBounds(50, 50, 80, 15);
		avancee.setBounds(150, 50, 80, 15);
		
		panel.add(rapide);
		panel.add(avancee);
		
		
		
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
		
		
		final JTextField nomJoueur = new JTextField(20);
		final JTextField ageJoueur = new JTextField(3);
		
		
		// champ texte
		panel.add(ageJoueur);
		ageJoueur.addActionListener(this);
		ageJoueur.setBounds(55, 200, 25, 25);
//		ageJoueur.addActionListener(this);
		
		//label
		JLabel label4 = new JLabel("Quel est votre nom ?");
		panel.add(label4);
		label4.setBounds(15, 220, 300, 50);
		nomJoueur.addActionListener(this);
		
		// champ texte
		nomJoueur.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String nJ = nomJoueur.getText();
				System.out.println("Nom du joueur physique : " + nJ );
				p.setNomJPhysique(nJ);	
			}
		});
		
		ageJoueur.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int aJ = Integer.parseInt(ageJoueur.getText());System.out.println("age du j p : " + aJ);	
				System.out.println("Age du joueur physique : " + aJ);
				p.setAgeJPhysique(aJ);
			}
		});
		
//		nomJoueur.addActionListener(this);
//		ageJoueur.addActionListener(this);
		panel.add(nomJoueur);
		nomJoueur.setBounds(55, 270, 100, 25);
		
		// bouton lancement de partie
		btnLancerPartie.setText("LANCER LA PARTIE");
		btnLancerPartie.addActionListener(this);
		panel.add(btnLancerPartie);
		btnLancerPartie.setBounds(15, 320, 180, 30);		

		return panel;
	}

	public void actionPerformed(ActionEvent evt) {
			
		Object source = evt.getSource();		
		
			if(source == btnLancerPartie){

				this.p = Partie.getInstance();
				System.out.println("Lancement de la partie");
				p.setJoueursPhysiques(p.getAgeJPhysique(),p.getNomJPhysique());

				p.lancerPartie(); 
			}
			
//			if(source == ageJoueur){
//				int aJ = Integer.parseInt(ageJoueur.getText());
//     			System.out.println("Age du joueur physique : " + aJ);
//				p.setAgeJPhysique(aJ);
//			}
//			
//			if(source == nomJoueur){
//				String nJ = nomJoueur.getText();
//				System.out.println("Nom du joueur physique : " + nJ );
//				p.setNomJPhysique(nJ);	
//			}
//			
			if(source == nbList){
				nbList.setEnabled(false);
				p.setNbrJoueursVirtuels(Integer.parseInt(nb[nbList.getSelectedIndex()]));
				p.setJoueursVirtuels(Integer.parseInt(nb[nbList.getSelectedIndex()]));
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

	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

