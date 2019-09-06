package application;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

//clasa mosteneste JPanel
public class Creare_Salvare_Histograma extends JPanel {

	private static final long serialVersionUID = 1L;

	static int[] data=new int[257];

	 final int PAD = 70;
	 final int SPAD = 2;

	 protected void paintComponent(Graphics g) {
		
	     Graphics2D g2 = (Graphics2D)g;
	     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                         RenderingHints.VALUE_ANTIALIAS_ON);
	     int w = getWidth();
	     int h = getHeight();
	     // Desenare ordonata
	     g2.draw(new Line2D.Double(PAD, PAD, PAD, h-PAD));
	     // Desenare abscisa
	     g2.draw(new Line2D.Double(PAD, h-PAD, w-PAD, h-PAD));
	     //Desenare etichete 
	     Font font = g2.getFont();
	     FontRenderContext frc = g2.getFontRenderContext();
	     LineMetrics lm = font.getLineMetrics("0", frc);
	     float sh = lm.getAscent() + lm.getDescent();
	     // linia y
	     String s = "Nr.aparitii";
	     float sy = PAD + ((h - 2*PAD) - s.length()*sh)/2 + lm.getAscent();
	     for(int i = 0; i < s.length(); i++) {
	         String letter = String.valueOf(s.charAt(i));
	         float sw = (float)font.getStringBounds(letter, frc).getWidth();
	         float sx = (PAD - sw)/2;
	         g2.drawString(letter, sx, sy);
	         sy += sh;
	     }
	     // linia x
	     s = "Niveluri de gri(0-255)";
	     sy = h - PAD + (PAD - sh)/2 + lm.getAscent();
	     float sw = (float)font.getStringBounds(s, frc).getWidth();
	     float sx = (w - sw)/2;
	     g2.drawString(s, sx, sy);
	     // Deseneaza liniile
	     double xInc = (double)(w - 2*PAD)/(data.length-1);
	     double scale = (double)(h - 2*PAD)/getMax();
	     g2.setPaint(Color.green.darker());
	   
	     for(int i = 0; i < data.length-1; i++) {
	         double x1 = PAD + i*xInc;
	         double y1 = h - PAD - scale*data[i];
	         double x2 = PAD + (i+1)*xInc;
	         double y2 = h - PAD - scale*data[i+1];
	         g2.draw(new Line2D.Double(x1, y1, x2, y2));
	     }
	     // marcheaza punctele 
	     
	     g2.setPaint(Color.red);
	     
	     int maxim=getMax();
	     
	     //Marcheaza pe axa y valoare maxima
	 	String s1 = String.valueOf(maxim);
	     float sw1 = (float)font.getStringBounds(s1, frc).getWidth();
	     float sx1 = PAD - sw1 - SPAD;
	     float sy1 = (float) (h - PAD - scale*maxim + lm.getAscent()/2);
	     g2.drawString(s1, sx1, sy1);
	    
	     //Marcheaza pe axa x valoari din 100 in 100
	     int val_axa=65000;
	     for(;val_axa>0;val_axa=val_axa-100)
	     {if(val_axa<maxim) {
	 	 s1 = String.valueOf(val_axa);
	      sw1 = (float)font.getStringBounds(s1, frc).getWidth();
	      sx1 = PAD - sw1 - SPAD;
	      sy1 = (float) (h - PAD - scale*val_axa + lm.getAscent()/2);
	     g2.drawString(s1, sx1, sy1);
	     }
	       }
	    
	     
	     for(int i = 0; i < data.length; i++) {
	         double x = PAD + i*xInc;
	         double y = h - PAD - scale*data[i];
	         g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
	     }

	 }
	 //Punere graphic in imagine
	 public static BufferedImage getScreenShot(Component component) {
	 	BufferedImage image= new BufferedImage(component.getWidth(),component.getHeight(),BufferedImage.TYPE_INT_RGB);
	 	component.paint(image.getGraphics());
	 	return image;
	 }

	 //punere imagine in fisier
	 public static void SaveScreenShot(Component component,String filename)throws Exception {
	 	
	 	BufferedImage img= getScreenShot(component);
	 	ImageIO.write(img, "bmp", new File(filename));
	 }

	 //functie pentru maximul din vector
	 private int getMax() {
	     int max = -Integer.MAX_VALUE;
	     for(int i = 0; i < data.length; i++) {
	         if(data[i] > max)
	             max = data[i];
	     }
	     return max;
	 }
}
