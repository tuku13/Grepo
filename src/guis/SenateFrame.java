package guis;

import buildings.Building;
import enums.BuildingType;
import exceptions.NotEnoughResource;
import game.City;
import game.ResourceStack;
import tasks.BuildingTask;
import tasks.Task;
import tasks.TaskManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SenateFrame extends JFrame {
    City city;

    public SenateFrame(City city){
        this.city = city;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle(BuildingType.SENATE.getName() + " lvl " + city.getBuilding(BuildingType.SENATE).getLevel());
        this.setResizable(false);
        this.setLayout(new GridLayout(city.getBuildings().size(), 1 , 75,0)); //todo hgap 50 volt

        for(Building b : city.getBuildings()){
            JPanel panel = new JPanel();
            panel.setBackground(new Color(254,225,157));
            panel.setLayout(new FlowLayout());
            JLabel nameLabel = new JLabel(b.getBuildingType().getName());
            nameLabel.setIcon(new ImageIcon(b.getBuildingType().getImageName()));
            panel.add(nameLabel);

            ResourceStack buildingCost = b.getBuildingType().getCost(b.getLevel()+1);

            ResourcePanel resourcePanel = new ResourcePanel(buildingCost);
            resourcePanel.setHorizontalGap(10);
            resourcePanel.setFontColor(new Color(0,0,0));
            panel.add(resourcePanel);

            //todo talán alap font kellene
            long buildingTime = Math.round(b.getBuildingType().getBuildingTime(b.getLevel()+1) * (( 101 - city.getBuilding(BuildingType.SENATE).getLevel()) / 100 ));
            String str = (Math.round(buildingTime / 3600)) + ":" +  (buildingTime / 60) + ":" + (buildingTime % 60);
            JLabel timeLabel = new JLabel(str);
            timeLabel.setIcon(new ImageIcon("images/time_icon.png"));
            panel.add(timeLabel);

            if(b.getLevel() != b.getBuildingType().getMaxLevel()){

                JButton buildButton = new JButton();
                buildButton.setHorizontalAlignment(SwingConstants.RIGHT);
                buildButton.addActionListener(new upgradeBuildingListener(b,buildingCost,buildingTime));
                panel.add(buildButton);

                if(!city.getResources().hasEnough(buildingCost)){
                    buildButton.setEnabled(false);
                }

                if(b.getLevel() == 0){
                    buildButton.setText("Építés");
                }
                else{
                    buildButton.setText("Fejlesztés " + (b.getLevel()+1) + " szintre");
                }
            }

            this.add(panel);
        }

        this.setMinimumSize(new Dimension(700,600));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private class upgradeBuildingListener implements ActionListener {
        private Building building;
        private ResourceStack cost;
        private long buildingTime;

        public upgradeBuildingListener(Building b, ResourceStack c,long buildingTime){
            this.building = b;
            this.cost = c;
            this.buildingTime = buildingTime;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                building.getCity().getResources().subtract(cost);
                Task task = new BuildingTask(buildingTime,city,building);
                TaskManager.getInstance().add(task);
            } catch (NotEnoughResource notEnoughResource) {
                notEnoughResource.printStackTrace();
                JOptionPane.showMessageDialog(null,"Nincs elég nyersanyagod!","Hiba",JOptionPane.ERROR_MESSAGE);
            }
            dispose();
        }

    }

}
