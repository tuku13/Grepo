package guis;

import game.City;

import javax.swing.*;
import java.awt.*;

public class SenateFrame extends JFrame {
    City city;

    public SenateFrame(City city){
        this.city = city;
        this.setLayout(new GridLayout(7, 1 , 50,50));
        for(int i = 0; i <= 7-1;i++ ){
            this.add(new JButton("asd" + i));
        }
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

}
