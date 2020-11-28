package guis;

import buildings.Building;
import enums.BuildingType;
import enums.GroundUnitType;
import game.City;
import game.ResourceStack;
import tasks.TaskManager;
import tasks.UnitTrainingTask;
import units.Army;
import units.GroundUnit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BarracksFrame extends JFrame {
    City city;

    public BarracksFrame(City city){
        this.city = city;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle(BuildingType.BARRACKS.getName() + " lvl " + city.getBuilding(BuildingType.BARRACKS).getLevel());
        this.setResizable(true);
        this.setLayout(new GridLayout(GroundUnitType.values().length, 1 , 75,0)); //todo hgap 50 volt

        for(GroundUnitType gu : GroundUnitType.values()){
            JPanel panel = new JPanel();
            panel.setBackground(new Color(254,225,157));
            panel.setLayout(new FlowLayout());
            JLabel nameLabel = new JLabel(gu.getName());
            nameLabel.setIcon(new ImageIcon(gu.getImageName()));
            panel.add(nameLabel);

            ResourceStack cost = gu.getCost();

            ResourcePanel resourcePanel = new ResourcePanel(cost);
            resourcePanel.setHorizontalGap(10);
            resourcePanel.setFontColor(new Color(0,0,0));
            panel.add(resourcePanel);

            long trainingTime = Math.round(gu.getTrainingTime() * ( ( 101 - city.getBuilding(BuildingType.BARRACKS).getLevel()) / 100 ));
            String str = (Math.round(trainingTime / 3600)) + ":" +  (trainingTime / 60) + ":" + (trainingTime % 60);
            JLabel timeLabel = new JLabel(str);
            timeLabel.setIcon(new ImageIcon("images/time_icon.png"));
            panel.add(timeLabel);

            JButton trainButton = new JButton();
            trainButton.setHorizontalAlignment(SwingConstants.RIGHT);
            trainButton.addActionListener(new TrainUnitListener(city,gu));
            panel.add(trainButton);

            if(!city.getResources().hasEnough(cost) || city.getBuilding(BuildingType.BARRACKS).getLevel() < gu.getRequiredLevel()){
                trainButton.setEnabled(false);
            }

            if(city.getBuilding(BuildingType.BARRACKS).getLevel() >= gu.getRequiredLevel()){
                trainButton.setText("Képzés");
            }
            else{
                trainButton.setText("Szükséges kaszárnya szint: lvl " + gu.getRequiredLevel());
            }

            this.add(panel);
        }

        this.setMinimumSize(new Dimension(800,600));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private class TrainUnitListener implements ActionListener {
        City city;
        GroundUnitType groundUnitType;

        public TrainUnitListener(City city, GroundUnitType gu) {
            this.city = city;
            this.groundUnitType = gu;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Army army = new Army();
            army.add(new GroundUnit(groundUnitType));
            TaskManager.getInstance().add(new UnitTrainingTask(groundUnitType.getTrainingTime(),city,army));
        }
    }
}
