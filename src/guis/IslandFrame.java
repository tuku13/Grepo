package guis;

import components.ImagePanel;
import game.City;
import game.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

/**
 * Sziget nézet, szigetre kattintva meg lehet támadni a megjelenő városokat
 */
public class IslandFrame extends JFrame {
    private final Game game;
    private JPanel panel;
    private final City city;
    private JFrame openedFrame;

    /**
     * Konstruktor
     * @param game játék
     * @param city játékos szigete
     */
    public IslandFrame(Game game,City city) {
        this.game = game;
        this.city = city;
        this.setTitle("Grepo - Sziget nézet");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1024,768);
        this.setResizable(false);

        try {
            panel = new ImagePanel(ImageIO.read(new File("images/islands.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.setSize(1024,768);
        panel.addMouseListener(new ClickIslandEvent());

        this.add(panel,BorderLayout.CENTER);
    }

    /**
     * Szigetre kattintva megjeleníti a rajta lévő szigeteket egy ablakban
     */
    private class ClickIslandEvent implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {

            if((new Point(190,566)).distance(e.getPoint()) <= 250){
                if(openedFrame != null){
                    openedFrame.dispose();
                }
                openedFrame = new IslandListFrame(game,game.getIslands().get(0),city);
                openedFrame.setVisible(true);
            }

            if((new Point(470,140)).distance(e.getPoint()) <= 200){
                if(openedFrame != null){
                    openedFrame.dispose();
                }
                openedFrame = new IslandListFrame(game,game.getIslands().get(1),city);
                openedFrame.setVisible(true);
            }

            if((new Point(911,454)).distance(e.getPoint()) <= 300){
                if(openedFrame != null){
                    openedFrame.dispose();
                }
                openedFrame = new IslandListFrame(game,game.getIslands().get(2),city);
                openedFrame.setVisible(true);
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}
