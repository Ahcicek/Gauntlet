package View;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel extends JPanel{
	private int x, y, width, height;
	private String modeDeJeu;
	private Panel panel;
	private Button[] buttons = new Button[10];
	private ImageIcon[] ImageIcons;
	private Label[] labels = new Label[15], nameLabels = new Label[4];
	private String[] nameHero = new String[4];
	private JTextField[] name;
	private String type;
	private Image img;
	private CardLayout card;
	
	public Panel(int x, int y, int width, int height){
		this.card = new CardLayout();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.setBounds(x, y, width, height);
		this.setLayout(card);
	}
	
	public Panel(Image img, Panel panel, ImageIcon[] ImageIcons, CardLayout card, int x, int y, int width, int height, String type) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.img = img;
		this.panel = panel;
		this.ImageIcons = ImageIcons;
		this.card = card;
		this.type = type;
		this.setLayout(null);
		initialisation();
	}
	
	private void initialisation(){
		this.setBounds(x, y, width, height);
		panel.add(this, type);
		if (type.equals("Home")){
			buttons[0] = button("Jouer", 450, 480, 100, 30, Color.ORANGE);
			buttons[0].addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e){
	            	card.show(panel, "Menu");}});
			labels[0] = arrow(ImageIcons[0], 450, 450, 100, 30);
	        labels[1] = arrow(ImageIcons[0], 450, 510, 100, 30);
		}else if (type.equals("Menu")){
			arrows();
			labels[10] = text("MODE DE JEU", 390, 150, 225, 35, Color.ORANGE);
	        labels[11] = arrow(ImageIcons[5], 405, 190, 198, 30);
	        buttons[1] = button("Mode Qu�te", 405, 230, 200, 35, Color.ORANGE);
	        actionButton(buttons[1], "Mode Qu�te");
	        buttons[2] = button("Mode Ar�ne", 405, 280, 200, 35, Color.ORANGE);
	        actionButton(buttons[2], "Mode Ar�ne");
	        buttons[3] = button("Mode Survivor", 405, 330, 200, 35, Color.ORANGE);
	        actionButton(buttons[3], "Mode Survivor");
		}else if (type.equals("Player")){
			arrows();
			labels[12] = text("NOMBRE DE JOUEUR", 330, 150, 400, 35, Color.ORANGE);
			for (Integer i = 1; i <= 4; i++){
				buttons[i+3] = button(String.valueOf(i) + " Joueur", 405, 230 + (i-1)*50, 200, 35, Color.ORANGE);
				actionPlayer(buttons[i+3], String.valueOf(i));
			}
		}else if (type.equals("1")||type.equals("2")||type.equals("3")||type.equals("4")){
			arrows();
			name = writeName(Integer.valueOf(type));
			buttons[8] = button("Commencer", 410, 510, 200, 35, Color.ORANGE);
		}
	}

	private void arrows(){
		labels[2] = arrow(ImageIcons[1], 302, 0, 198, 30);
        labels[3] = arrow(ImageIcons[2], 500, 0, 198, 30);
        labels[4] = arrow(ImageIcons[1], 302, 580, 198, 30);
        labels[5] = arrow(ImageIcons[2], 500, 580, 198, 30);
        labels[6] = arrow(ImageIcons[3], 0, 102, 30, 198);
        labels[7] = arrow(ImageIcons[4], 0, 300, 30, 198);
        labels[8] = arrow(ImageIcons[3], 970, 102, 30, 198);
        labels[9] = arrow(ImageIcons[4], 970, 300, 30, 198);
	}

	private void actionPlayer(Button button, String playerNumber){
		button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                card.show(panel, playerNumber);
            }});
	}
	
	private JTextField[] writeName(int playerNumber){
		JTextField[] name = new JTextField[4];
		for(Integer i = 1; i<= playerNumber; i++){
			nameLabels[i-1] = text("Joueur "+i.toString(), 450, 10 + i*100, 200, 30, Color.ORANGE);
			name[i-1] = new JTextField();
			name[i-1].setBounds(550, 60 + i*100, 200, 30);
			this.add(name[i-1]);
		}
		comboBox(playerNumber);
		return name;
	}
	
	private void comboBox(int playerNumber){
		JComboBox[] heros = new JComboBox[4];
		for (Integer i = 1; i<= playerNumber; i++){
			heros[i-1] = new JComboBox();
			heros[i-1].addItem("Guerrier");
			heros[i-1].addItem("Sorcier");
			heros[i-1].addItem("Nain");
			heros[i-1].addItem("Elfe");
			heros[i-1].setBackground(Color.BLACK);
  			heros[i-1].setBounds(250, 60 + i*100, 200, 30);
  			heros[i-1].setOpaque(false);
			this.add(heros[i-1]);
		}
	}
	
	private void actionButton(Button button, String type){
		button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                card.show(panel, "Player");}});
				modeDeJeu = type;
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);               
	}
	
	private Button button(String name, int x, int y, int width, int height, Color color){
		Button button = new Button(name, x, y, width, height, color);
		this.add(button);
		return button;
	}
	
	private Label text(String name, int x, int y, int width, int height, Color color){
		Label label = new Label(name, x, y, width, height, color);
		this.add(label);
		return label;
	}
	
	private Label arrow(ImageIcon icon, int x, int y, int width, int height){
		Label label = new Label(icon, x, y, width, height);
		this.add(label);
		return label;
	}

	public CardLayout getCard() {
		return card;
	}
	
	public Button[] getButtons() {
		return buttons;
	}

	public String getModeDeJeu() {
		return modeDeJeu;
	}
	
}
