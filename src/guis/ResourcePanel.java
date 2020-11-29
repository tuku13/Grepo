package guis;

import game.ResourceStack;
import tasks.Tickable;

import javax.swing.*;
import java.awt.*;

public class ResourcePanel extends JPanel implements Tickable {
    private ResourceStack resourceStack;
    private JLabel woodLabel,stoneLabel,silverLabel,favourLabel;
    private FlowLayout flowLayout;

    public ResourcePanel(ResourceStack resourceStack){
        this.resourceStack = resourceStack;
        flowLayout = new FlowLayout();
        flowLayout.setHgap(60);
        flowLayout.setAlignment(FlowLayout.RIGHT);
        this.setLayout(flowLayout);
        this.setOpaque(false);

        woodLabel = new JLabel();
        woodLabel.setText(String.format("%.2f",resourceStack.getWood()));
        woodLabel.setForeground(new Color(255,255,255));
        woodLabel.setFont(new Font("Dialog",Font.BOLD,14));
        woodLabel.setIcon(new ImageIcon("images/wood_icon.png"));
        this.add(woodLabel);

        stoneLabel = new JLabel();
        stoneLabel.setText(String.format("%.2f",resourceStack.getStone()));
        stoneLabel.setForeground(new Color(255,255,255));
        stoneLabel.setFont(new Font("Dialog",Font.BOLD,14));
        stoneLabel.setIcon(new ImageIcon("images/stone_icon.png"));
        this.add(stoneLabel);

        silverLabel = new JLabel();
        silverLabel.setText(String.format("%.2f",resourceStack.getSilver()));
        silverLabel.setForeground(new Color(255,255,255));
        silverLabel.setFont(new Font("Dialog",Font.BOLD,14));
        silverLabel.setIcon(new ImageIcon("images/silver_icon.png"));
        this.add(silverLabel);

        favourLabel = new JLabel();
        favourLabel.setText(String.format("%.2f",resourceStack.getFavour()));
        favourLabel.setForeground(new Color(255,255,255));
        favourLabel.setFont(new Font("Dialog",Font.BOLD,14));
        favourLabel.setIcon(new ImageIcon("images/favour_icon.png"));
        this.add(favourLabel);

    }

    public void setHorizontalGap(int i){
        flowLayout.setHgap(i);
    }

    public void setFontColor(Color c){
        woodLabel.setForeground(c);
        stoneLabel.setForeground(c);
        silverLabel.setForeground(c);
        favourLabel.setForeground(c);
    }

    public void setResourceStack(ResourceStack resourceStack) {
        this.resourceStack = resourceStack;
    }

    @Override
    public void tick() {
        woodLabel.setText(String.format("%.2f",resourceStack.getWood()));
        stoneLabel.setText(String.format("%.2f",resourceStack.getStone()));
        silverLabel.setText(String.format("%.2f",resourceStack.getSilver()));
        favourLabel.setText(String.format("%.2f",resourceStack.getFavour()));
    }
}
