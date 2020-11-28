package guis;

import enums.BuildingType;
import enums.GroundUnitType;
import enums.NavalUnitType;
import game.City;
import game.ResourceStack;
import tasks.TaskManager;
import tasks.UnitTrainingTask;
import units.Army;
import units.GroundUnit;
import units.NavalUnit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HarborFrame extends JFrame {
    City city;

    public HarborFrame(City city){
        this.city = city;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle(BuildingType.HARBOR.getName() + " lvl " + city.getBuilding(BuildingType.HARBOR).getLevel());
        this.setResizable(true);
        this.setLayout(new GridLayout(NavalUnitType.values().length, 1 , 50,0)); //todo hgap 50 volt

        for(NavalUnitType nu : NavalUnitType.values()){
            JPanel panel = new JPanel();
            panel.setBackground(new Color(254,225,157));
            panel.setLayout(new FlowLayout());
            JLabel nameLabel = new JLabel(nu.getName());
            nameLabel.setIcon(new ImageIcon(nu.getImageName()));
            panel.add(nameLabel);

            ResourceStack cost = nu.getCost();

            ResourcePanel resourcePanel = new ResourcePanel(cost);
            resourcePanel.setHorizontalGap(10);
            resourcePanel.setFontColor(new Color(0,0,0));
            panel.add(resourcePanel);

            long trainingTime = Math.round(nu.getTrainingTime() * ( ( 101 - city.getBuilding(BuildingType.HARBOR).getLevel()) / 100 ));
            String str = (Math.round(trainingTime / 3600)) + ":" +  (trainingTime / 60) + ":" + (trainingTime % 60);
            JLabel timeLabel = new JLabel(str);
            timeLabel.setIcon(new ImageIcon("images/time_icon.png"));
            panel.add(timeLabel);

            JButton trainButton = new JButton();
            trainButton.setHorizontalAlignment(SwingConstants.RIGHT);
            trainButton.addActionListener(new TrainUnitListener(city,nu));
            panel.add(trainButton);

            if(!city.getResources().hasEnough(cost) || city.getBuilding(BuildingType.HARBOR).getLevel() < nu.getRequiredLevel()){
                trainButton.setEnabled(false);
            }

            if(city.getBuilding(BuildingType.BARRACKS).getLevel() >= nu.getRequiredLevel()){
                trainButton.setText("Építés");
            }
            else{
                trainButton.setText("Szükséges kikötő szint: lvl " + nu.getRequiredLevel());
            }

            this.add(panel);
        }

        this.setMinimumSize(new Dimension(900,400));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private class TrainUnitListener implements ActionListener {
        City city;
        NavalUnitType navalUnitType;

        public TrainUnitListener(City city, NavalUnitType nu) {
            this.city = city;
            this.navalUnitType = nu;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Army army = new Army();
            army.add(new NavalUnit(navalUnitType));
            TaskManager.getInstance().add(new UnitTrainingTask(navalUnitType.getTrainingTime(),city,army));
        }
    }
}
