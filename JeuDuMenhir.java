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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class JeuDuMenhir extends JFrame implements ActionListener{

	private Partie p;
	
	JButton btnLancerPartie = new JButton();
	
	public JeuDuMenhir(){
		super();
		build(); // initialisation de la fenetre
	}
	
	private void build(){
		setContentPane(buildContentPane());
	}
	
	private JPanel buildContentPane(){
		
		JPanel panel = new JPanel();
		//panel.setLayout(new FlowLayout());
		panel.setLayout(null);
		
		/*GridLayout gl = new GridLayout(3, 2);
		gl.setHgap(10); //Cinq pixels d'espace entre les colonnes (H comme Horizontal)
		gl.setVgap(10); //Cinq pixels d'espace entre les lignes (V comme Vertical) 
		*/
		JLabel label = new JLabel("Quel type de partie voulez-vous jouer ?");
		panel.add(label);
		label.setBounds(15, 15, 300, 15);
		
		setTitle("Initialisation du jeu du MENHIR"); 
		setSize(500,500); 
		setLocationRelativeTo(null); //centre la fenêtre sur l'écran
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		panel.setBackground(Color.lightGray);
		
		
		Checkbox rapide;
		panel.add(rapide = new Checkbox("RAPIDE"));
		rapide.setBounds(50, 50, 80, 15);
		
		Checkbox avancee;
		panel.add(avancee = new Checkbox("AVANCEE"));
		avancee.setBounds(150, 50, 80, 15);
		
				
		JLabel label2 = new JLabel("Combien de joueurs dans la partie ?");
		panel.add(label2);
		label2.setBounds(15, 70, 300, 50);
		
		String[] nb = { "2", "3", "4", "5", "6" };
		JComboBox<Object> nbList = new JComboBox<Object>(nb);
		nbList.setSelectedIndex(4);
		nbList.addActionListener(this);
		panel.add(nbList);
		nbList.setBounds(50, 120, 80, 25);
		
		JLabel label3 = new JLabel("Quel âge avez-vous ?");
		panel.add(label3);
		label3.setBounds(15, 150, 300, 50);
		
		
		JTextField ageJoueur = new JTextField(2);
		panel.add(ageJoueur);
		ageJoueur.setBounds(50, 200, 50, 25);
		
		JLabel label4 = new JLabel("Quel est votre nom ?");
		panel.add(label4);
		label4.setBounds(15, 220, 300, 50);
		
		JTextField nomJoueur = new JTextField(2);
		panel.add(nomJoueur);
		nomJoueur.setBounds(50, 270, 50, 25);
		
		btnLancerPartie.setText("LANCER LA PARTIE");
		panel.add(btnLancerPartie);
		btnLancerPartie.setBounds(15, 320, 180, 30);
		btnLancerPartie.addActionListener(this);
		
		return panel;
	}

	public void actionPerformed(ActionEvent evt) {
		// problème au niveau du getInstance() ???
		Object source = evt.getSource();
		if(source == btnLancerPartie){
			Partie p = Partie.getInstance();
			//Rajouter les passages de paramètres avant de lancer la partie
			p.lancerPartie(); 
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
}
