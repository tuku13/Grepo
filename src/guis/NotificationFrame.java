package guis;

import game.City;
import tasks.Task;
import tasks.TaskManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class NotificationFrame extends JFrame {
    private City city;
    private List<Task> tasks;
    private JPanel panel;

    public NotificationFrame(City city) {
        this.city = city;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Értesítések");
        this.setResizable(false);

        init();
    }

    private void init(){
        tasks = new ArrayList<>();

        for(Task t : TaskManager.getInstance().getTasks()){
            if(t.getCity() == city){
                this.tasks.add(t);
            }
        }

        this.setLayout(new GridLayout(tasks.size(), 1, 50, 0)); //todo hgap 50 volt

        if(tasks.size() > 0){
            for(Task t : tasks){
                panel = new JPanel();
                panel.setBackground(new Color(254,225,157));
                panel.setLayout(new FlowLayout());
                JLabel nameLabel = new JLabel(t.toString());
                panel.add(nameLabel);

                long remainingTime = t.getTime();
                String str = (Math.round(remainingTime/ 3600)) + ":" +  (remainingTime / 60) + ":" + (remainingTime % 60);
                JLabel timeLabel = new JLabel(str);
                timeLabel.setIcon(new ImageIcon("images/time_icon.png"));
                panel.add(timeLabel);

                this.add(panel);
            }

            this.setMinimumSize(new Dimension(400,100 * tasks.size()));
        }
        else{
            this.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel label = new JLabel("Jelenleg nincs ehhez a városhoz tartozó értesítés");
            label.setBorder(BorderFactory.createEmptyBorder(15,0,0,0));
            this.add(label);
            this.setMinimumSize(new Dimension(400,100));
        }
    }

}
