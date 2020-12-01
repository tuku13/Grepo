package guis;

import components.ImagePanel;
import game.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class ChooseIslandFrame extends JFrame {
    private Game game;
    private JPanel panel;
    private JFrame openedFrame;

    public ChooseIslandFrame(Game game){
        this.game = game;
        this.setTitle("Grepo - Válassz szigetet, majd várost!");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1024,768);
        this.setResizable(false);

        try {
            panel = new ImagePanel(ImageIO.read(new File("images/islands.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.setSize(1024,768);
        panel.addMouseListener(new ClickIslandEvent(this));

        this.add(panel,BorderLayout.CENTER);

    }

    private class ClickIslandEvent implements MouseListener{
        private JFrame chooseIslandFrame;

        private ClickIslandEvent(JFrame chooseIslandFrame) {
            this.chooseIslandFrame = chooseIslandFrame;
        }

        @Override
        public void mouseClicked(MouseEvent e) {

            if((new Point(190,566)).distance(e.getPoint()) <= 250){
                if(openedFrame != null){
                    openedFrame.dispose();
                }
                openedFrame = new FoundCityFrame(game,game.getIslands().get(0),chooseIslandFrame);
                openedFrame.setVisible(true);
            }

            if((new Point(470,140)).distance(e.getPoint()) <= 200){
                if(openedFrame != null){
                    openedFrame.dispose();
                }
                openedFrame = new FoundCityFrame(game,game.getIslands().get(1),chooseIslandFrame);
                openedFrame.setVisible(true);
            }

            if((new Point(911,454)).distance(e.getPoint()) <= 300){
                if(openedFrame != null){
                    openedFrame.dispose();
                }
                openedFrame = new FoundCityFrame(game,game.getIslands().get(2),chooseIslandFrame);
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
