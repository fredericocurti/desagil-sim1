package br.pro.hashi.ensino.desagil.lucianogic.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import br.pro.hashi.ensino.desagil.lucianogic.model.Gate;
import br.pro.hashi.ensino.desagil.lucianogic.model.Switch;


public class GateView extends FixedPanel implements ItemListener,MouseListener {

	// Necessario para serializar objetos desta classe.
	private static final long serialVersionUID = 1L;


	private Image image;

	private JCheckBox[] inBoxes;
	private JCheckBox outBox;

	private Switch[] switches;
	private Gate gate;


	private boolean newswitch1;
	private boolean newswitch2;

	public GateView(Gate gate) {
		super(205, 180);

		this.gate = gate;
		
		this.addMouseListener(this);

		image = loadImage(gate.toString());

		int size = gate.getSize();

		inBoxes = new JCheckBox[size];

		switches = new Switch[size];
		
		newswitch1 = false;
		newswitch2 = false;
		
		
		for(int i = 0; i < size; i++) {
			inBoxes[i] = new JCheckBox();

			inBoxes[i].addItemListener(this);

			switches[i] = new Switch();

			gate.connect(switches[i], i);
		}

		outBox = new JCheckBox();

		outBox.setEnabled(false);

//		if(size == 1) {
//			add(inBoxes[0], 0, 60, 20, 20);
//		}
//		else {
//			for(int i = 0; i < size; i++) {
//				add(inBoxes[i], 0, (i + 1) * 40, 20, 20);			
//			}			
//		}

		add(outBox, 184, 60, 20, 20);

		outBox.setSelected(gate.read());
	}


	@Override
	public void itemStateChanged(ItemEvent event) {
		int i;
		for(i = 0; i < inBoxes.length; i++) {
			if(inBoxes[i] == event.getSource()) {
				break;
			}
		}

		switches[i].setOn(inBoxes[i].isSelected());

		outBox.setSelected(gate.read());
	}


	// Necessario para carregar os arquivos de imagem.
	private Image loadImage(String filename) {
		URL url = getClass().getResource("/img/" + filename + ".png");
		ImageIcon icon = new ImageIcon(url);
		return icon.getImage();
	}
	
	public void mousePressed(MouseEvent e) {
		System.out.println(e.getX());
		System.out.println(e.getY());
		
		if (e.getX() > 0 && e.getX() < 35 && e.getY() > 25 && e.getY() < 60){
			if(newswitch1 = true){
				newswitch1 = false;	
			} else {
				newswitch1 = true;
			}
		}
		
		if (e.getX() > 0 && e.getX() < 35 && e.getY() > 65 && e.getY() < 100 ){
			if(newswitch2 == true){
				newswitch2 = false;
			} else {
				newswitch2 = true;
			}
		}
		
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		// Evita bugs visuais em alguns sistemas operacionais.
		super.paintComponent(g);

		g.drawImage(image, 10, 20, 184, 140, null);
 		
		if(gate.getSize() == 1) {
			g.fillOval(10, 60, 20, 20);
			g.drawLine(10, 60, 20, 20);
		}

		else {
			if(newswitch1 == false && newswitch2 == false){
				// both are off
				for(int i = 0; i < gate.getSize(); i++) {
					g.fillOval(10, (i + 1) * 40, 15, 15);	
					g.drawLine(20,(i+1)*40,12,(i+1)*40-10);
					g.fillOval(7, (i+1)*40-14,8,8);
				}		
			}
			if(newswitch1 == false && newswitch2 == true){
				// only the second is on
				for(int i = 0; i < gate.getSize(); i++) {
					g.fillOval(10, (i + 1) * 40, 15, 15);	
					g.drawLine(20,(i+1)*40,32,(i+1)*40-10);
					g.fillOval(27, (i+1)*40-14,8,8);
				}
			}
			
			if(newswitch1 == true && newswitch2 == false){
				// only the first is on
				for(int i = 0; i < gate.getSize(); i++) {
					g.fillOval(10, (i + 1) * 40, 15, 15);
					g.drawLine(20,(i+1)*40,32,(i+1)*40-10);
					g.fillOval(27, (i+1)*40-14,8,8);
				}
			}
			if(newswitch1 == true && newswitch2 == true){
				// both are on
				for(int i = 0; i < gate.getSize(); i++) {
					g.fillOval(10, (i + 1) * 40, 15, 15);	
					g.drawLine(20,(i+1)*40,32,(i+1)*40-10);
					g.fillOval(27, (i+1)*40-14,8,8);
				}
			}
		}
		
		// Evita bugs visuais em alguns sistemas operacionais.
		getToolkit().sync();
    }


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
