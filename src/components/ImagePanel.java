package components;

import javax.swing.*;
import java.awt.*;

/**
 * Kép háttérrel rendelkező JPanel
 */
public class ImagePanel extends JPanel {
    private final Image img;

    /**
     * Konstruktor
     * @param img háttérkép
     */
    public ImagePanel(Image img){
        this.img = img;
    }

    /**
     * Kirajzolja a háttérképet
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
    }

}
