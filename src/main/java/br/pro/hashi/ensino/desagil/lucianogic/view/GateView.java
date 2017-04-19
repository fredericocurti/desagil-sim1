package br.pro.hashi.ensino.desagil.lucianogic.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import br.pro.hashi.ensino.desagil.lucianogic.model.Gate;
import br.pro.hashi.ensino.desagil.lucianogic.model.Switch;


public class GateView extends FixedPanel implements MouseListener {

	// Necessario para serializar objetos desta classe.
	private static final long serialVersionUID = 1L;


	private Image image;

	private JCheckBox[] inBoxes;
	private JCheckBox outBox;

	private Switch[] switches;
	private Gate gate;


	private boolean newswitch1;
	private boolean newswitch2;
	private boolean newswitch3;

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
		newswitch3 = false;


		for(int i = 0; i < size; i++) {

			switches[i] = new Switch();
			gate.connect(switches[i], i);
		}

		outBox = new JCheckBox();

		outBox.setEnabled(false);

		add(outBox, 184, 60, 20, 20);

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

		if(gate.getSize() == 1){
			if (e.getX() > 9 && e.getX() < 30 && e.getY() > 20 && e.getY() < 80){
				if(newswitch1 == true){
					newswitch1 = false;
				} else {
					newswitch1 = true;
				}
				System.out.print(newswitch1);
				switches[0].setOn(newswitch1);
				outBox.setSelected(gate.read());
			}
		} else {
			if (e.getX() > 0 && e.getX() < 35 && e.getY() > 25 && e.getY() < 60){
				if(newswitch1 == true){
					newswitch1 = false;
				} else {
					newswitch1 = true;
				}
				switches[0].setOn(newswitch1);
				outBox.setSelected(gate.read());
			}

			if (e.getX() > 0 && e.getX() < 35 && e.getY() > 65 && e.getY() < 100 ){
				if(newswitch2 == true){
					newswitch2 = false;
				} else {
					newswitch2 = true;
				}
				switches[1].setOn(newswitch2);
				outBox.setSelected(gate.read());
			} if (gate.getSize() == 3){
				if (e.getX() > 5 && e.getX() < 20 && e.getY() > 100 && e.getY() < 136 ){
					if(newswitch3 == true){
						newswitch3 = false;
					} else {
						newswitch3 = true;
					}
					switches[2].setOn(newswitch3);
					outBox.setSelected(gate.read());
				}

			}

		}

		System.out.println("repainted");
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		// Evita bugs visuais em alguns sistemas operacionais.
		super.paintComponent(g);

		g.drawImage(image, 10, 20, 184, 140, null);

		if (gate.getSize() == 1) {

			if (newswitch1 == true) {
				g.fillOval(10, 60, 20, 20);
				g.drawLine(20, 60, 30, 20);
				g.fillOval(26, 20, 8, 8);
			} else {
				g.fillOval(10, 60, 20, 20);
				g.drawLine(20, 60, 12, 20);
				g.fillOval(8, 20, 8, 8);
			}

		} else if (gate.getSize() >= 2) {

			if (newswitch1 == true) {
				g.fillOval(10, (1) * 40, 15, 15);
				g.drawLine(20, (1) * 40, 24, (1) * 40 - 10);
				g.fillOval(22, (1) * 40 - 14, 8, 8);
			} else {
				g.fillOval(10, (1) * 40, 15, 15);
				g.drawLine(20, (1) * 40, 12, (1) * 40 - 10);
				g.fillOval(7, (1) * 40 - 14, 8, 8);
			}

			if (newswitch2 == true) {
				g.fillOval(10, (2) * 40, 15, 15);
				g.drawLine(20, (2) * 40, 24, (2) * 40 - 10);
				g.fillOval(22, (2) * 40 - 14, 8, 8);
			} else {
				g.fillOval(10, (2) * 40, 15, 15);
				g.drawLine(20, (2) * 40, 12, (2) * 40 - 10);
				g.fillOval(7, (2) * 40 - 14, 8, 8);
			}

			if (gate.getSize() == 3){
				if (newswitch3 == true) {
					g.fillOval(10, (3) * 40, 15, 15);
					g.drawLine(20, (3) * 40, 24, (3) * 40 - 10);
					g.fillOval(22, (3) * 40 - 14, 8, 8);
				} else {
					g.fillOval(10, (3) * 40, 15, 15);
					g.drawLine(20, (3) * 40, 12, (3) * 40 - 10);
					g.fillOval(7, (3) * 40 - 14, 8, 8);
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
