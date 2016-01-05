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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;


public class JeuDuMenhir extends JFrame implements ActionListener, ItemListener, CaretListener{

	private Partie p;
	
	Checkbox rapide;
	Checkbox avancee;
	
	String[] nb = { "1", "2", "3", "4", "5" };
	final JComboBox<Object> nbList = new JComboBox<Object>(nb);
	
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
		rapide.setBounds(50, 50, 80, 15);
		rapide.addItemListener(this);
		panel.add(rapide);
		
		// checkbox
		panel.add(avancee = new Checkbox("AVANCEE"));
		avancee.setBounds(150, 50, 80, 15);
		avancee.addItemListener(this);
		
		// label	
		JLabel label2 = new JLabel("Combien de joueurs virtuels dans la partie ?");
		panel.add(label2);
		label2.setBounds(15, 70, 300, 50);
		
		// combobox
		nbList.setSelectedIndex(4);
		nbList.addItemListener(this);
		panel.add(nbList);
		nbList.setBounds(50, 120, 80, 25);
		
		// label
		JLabel label3 = new JLabel("Quel âge avez-vous ?");
		panel.add(label3);
		label3.setBounds(15, 150, 300, 50);
		
		// champ texte
		JTextField ageJoueur = new JTextField(2);
		panel.add(ageJoueur);
		ageJoueur.setBounds(55, 200, 25, 25);
		ageJoueur.addCaretListener(this);
		
		//label
		JLabel label4 = new JLabel("Quel est votre nom ?");
		panel.add(label4);
		label4.setBounds(15, 220, 300, 50);
		
		// champ texte
		JTextField nomJoueur = new JTextField(2);
		panel.add(nomJoueur);
		nomJoueur.setBounds(25, 270, 100, 25);
		nomJoueur.addCaretListener(this);
		
		// bouton lancement de partie
		btnLancerPartie.setText("LANCER LA PARTIE");
		panel.add(btnLancerPartie);
		btnLancerPartie.setBounds(15, 280, 180, 30);
		
		return panel;
		}

		public void actionPerformed(ActionEvent evt) {
		// problème au niveau du getInstance() ???
		Object source = evt.getSource();
		
			if(source == btnLancerPartie){
				this.p = Partie.getInstance();
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

	public void itemStateChanged(ItemEvent e) {
		if(e.getItem() == rapide){
			p.setDifficulte(0);
		}else if(e.getItem() == avancee){
			p.setDifficulte(1);
		}
		
		if(e.getStateChange() == 1){
			p.setNbrJoueursVirtuels(e.getStateChange());
		}else if((e.getStateChange() == 2)){
			p.setNbrJoueursVirtuels(e.getStateChange());
		}else if((e.getStateChange() == 3)){
			p.setNbrJoueursVirtuels(e.getStateChange());
		}else if((e.getStateChange() == 4)){
			p.setNbrJoueursVirtuels(e.getStateChange());
		}else if((e.getStateChange() == 5)){
			p.setNbrJoueursVirtuels(e.getStateChange());
		}
	}
	
	/*CaretListener caretupdate = new CaretListener() {
        public void caretUpdate(javax.swing.event.CaretEvent e) {
            JTextField text = (JTextField)e.getSource();
            System.out.println(text.getText());
        }
    };*/

	
	public void caretUpdate(CaretEvent e) {
            JTextField text = (JTextField)e.getSource();
            //System.out.println(text.getText());
            // set le nom du joueur dans son attribut
	}
    
    
}
