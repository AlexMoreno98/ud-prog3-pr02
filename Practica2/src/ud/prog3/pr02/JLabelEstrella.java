package ud.prog3.pr02;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JLabelEstrella extends JLabel {

	private static final long serialVersionUID = 1L;
	public static final int tamanyoEstrella = 40;
	public static final int radioEstrella = 17;
	public long horaCreada;
	private double giroEstrella = Math.PI/2;
	private static final boolean dibujarEsferaEstrella = true;
	private Random r1 = new Random(System.currentTimeMillis());
	private Random r2 = new Random(System.currentTimeMillis());
	private int x = r1.nextInt(1000);
	private int y = r2.nextInt(750);

	public JLabelEstrella() {
		try {
			setIcon(new ImageIcon(JLabelEstrella.class.getResource("img/estrella.png").toURI().toURL()));
		} catch (Exception e) {
			System.err.println("Error en carga de recurso: estrella.png no encontrado");
			e.printStackTrace();
		}
		setBounds(x, y, tamanyoEstrella, tamanyoEstrella);
	}

	public void setGiro( double gradosGiro ) {
		// De grados a radianes...
		giroEstrella = gradosGiro/180*Math.PI;
		// El giro en la pantalla es en sentido horario (inverso):
		giroEstrella = -giroEstrella;  // Cambio el sentido del giro

	}

	public long getHoraCreada() {
		return horaCreada;
	}

	public void setHoraCreada(long d) {
		this.horaCreada = d;
	}

	protected void paintComponent(Graphics g) {
		//		super.paintComponent(g);   // En este caso no nos sirve el pintado normal de un JLabel
		Image img = ((ImageIcon)getIcon()).getImage();
		Graphics2D g2 = (Graphics2D) g;  // El Graphics realmente es Graphics2D
		// Escalado más fino con estos 3 parámetros:
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	
		// Prepara rotación (siguientes operaciones se rotarán)
		g2.rotate(giroEstrella, tamanyoEstrella/2, tamanyoEstrella/2); // getIcon().getIconWidth()/2, getIcon().getIconHeight()/2 );
		// Dibujado de la imagen
		g2.drawImage( img, 0, 0, tamanyoEstrella, tamanyoEstrella, null );
		if (dibujarEsferaEstrella) g2.drawOval( tamanyoEstrella/2-radioEstrella, tamanyoEstrella/2-radioEstrella,
				radioEstrella*2, radioEstrella*2 );
	}
}
