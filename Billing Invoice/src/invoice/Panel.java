package invoice;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panel extends JPanel{

    private BufferedImage image;
    private int w=1000,h=750;
    public Panel(String fname){
    	try {
            image = ImageIO.read(new File(fname));
        } catch (IOException ioe) {
            System.out.println("Could not read in the pic");
        }
    }
    public Dimension getPreferredSize() {
        return new Dimension(w,h);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image,0,0,this);
    }
}
