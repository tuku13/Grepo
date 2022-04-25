package guis;

import enums.GroundUnitType;
import enums.NavalUnitType;
import game.City;
import tasks.ConquerTask;
import tasks.WarTask;
import units.Army;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Hadjáart indító ablak
 */
public class StartWarFrame extends JFrame {
    private final City from;
    private final City to;
    private final Army army;
    private HashMap<GroundUnitType,JSpinner> groundUnitSpinner;
    private HashMap<NavalUnitType,JSpinner> navalUnitSpinner;

    /**
     * Konstruktor
     * @param from honnan
     * @param to hova
     */
    public StartWarFrame(City from, City to){
        this.from = from;
        this.to = to;
        this.army = new Army();
        this.groundUnitSpinner = new HashMap<>();
        this.navalUnitSpinner = new HashMap<>();

        init();
    }

    /**
     * Inicializálja a komponenseket
     */
    private void init(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(254,225,157));
        this.setTitle("Grepo - Hadjárat indítása");
        this.setSize(1024,768);
        this.setResizable(false);
        GridBagConstraints gc = new GridBagConstraints();

        JPanel groundPanel = initGroundPanel();
        //groundPanel.setBackground(new Color(255,0,0));
        gc.anchor = GridBagConstraints.PAGE_START;
        gc.gridx = 0;
        gc.gridy = 0;
        this.add(groundPanel,gc);


        JPanel navalPanel = initNavalPanel();
        //navalPanel.setBackground(new Color(0,255,0));
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 0;
        gc.gridy = 1;
        this.add(navalPanel,gc);


        JPanel infoPanel = new JPanel();
        JButton attackButton = new JButton("Támadás indítása");
        attackButton.addActionListener(new StartWarListener());
        infoPanel.add(attackButton);
        //infoPanel.setBackground(new Color(0,0,255));
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 1;
        gc.gridy = 1;
        this.add(infoPanel,gc);

    }

    /**
     * Szárazföldi egységek panelt étrehozza
     * @return szárazföldi egységek panel
     */
    private JPanel initGroundPanel(){
        JPanel panel = new JPanel();
        groundUnitSpinner = new HashMap<>();
        panel.setLayout(new GridLayout(1, GroundUnitType.values().length));
        for(GroundUnitType groundUnitType : GroundUnitType.values()){
            JPanel groundUnitPanel = new JPanel();
            groundUnitPanel.setLayout(new GridLayout(3,1));

            JLabel nameLabel = new JLabel(groundUnitType.getName());
            nameLabel.setIcon(new ImageIcon(groundUnitType.getImageName()));
            nameLabel.setVerticalAlignment(JLabel.CENTER);
            nameLabel.setHorizontalAlignment(JLabel.CENTER);
            nameLabel.setVerticalTextPosition(JLabel.BOTTOM);
            nameLabel.setHorizontalTextPosition(JLabel.CENTER);
            groundUnitPanel.add(nameLabel);

            JPanel middlePanel = new JPanel();
            middlePanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
            middlePanel.setAlignmentY(JPanel.CENTER_ALIGNMENT);
            JLabel maxCountLabel = new JLabel();
            int maxCount = from.getArmy().count(groundUnitType);
            maxCountLabel.setText(maxCount + "");
            middlePanel.add(maxCountLabel);
            groundUnitPanel.add(middlePanel);

            JSpinner spinner = new JSpinner(new SpinnerNumberModel(0,0,maxCount,1));
            groundUnitSpinner.put(groundUnitType,spinner);
            groundUnitPanel.add(spinner);

            panel.add(groundUnitPanel);
        }
        return panel;
    }

    /**
     * Vízi egységek panelt étrehozza
     * @return vízi egységek panel
     */
    private JPanel initNavalPanel(){
        JPanel panel = new JPanel();
        navalUnitSpinner = new HashMap<>();
        panel.setLayout(new GridLayout(1, NavalUnitType.values().length));
        for(NavalUnitType navalUnitType : NavalUnitType.values()){
            JPanel navalUnitPanel = new JPanel();
            navalUnitPanel.setLayout(new GridLayout(3,1));

            JLabel nameLabel = new JLabel(navalUnitType.getName());
            nameLabel.setIcon(new ImageIcon(navalUnitType.getImageName()));
            nameLabel.setVerticalAlignment(JLabel.CENTER);
            nameLabel.setHorizontalAlignment(JLabel.CENTER);
            nameLabel.setVerticalTextPosition(JLabel.BOTTOM);
            nameLabel.setHorizontalTextPosition(JLabel.CENTER);
            navalUnitPanel.add(nameLabel);

            JPanel middlePanel = new JPanel();
            middlePanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
            middlePanel.setAlignmentY(JPanel.CENTER_ALIGNMENT);
            JLabel maxCountLabel = new JLabel();
            int maxCount = from.getArmy().count(navalUnitType);
            maxCountLabel.setText(maxCount + "");
            middlePanel.add(maxCountLabel);
            navalUnitPanel.add(middlePanel);

            JSpinner spinner = new JSpinner(new SpinnerNumberModel(0,0,maxCount,1));
            navalUnitSpinner.put(navalUnitType,spinner);
            navalUnitPanel.add(spinner);

            panel.add(navalUnitPanel);
        }
        return panel;
    }

    /**
     * Gombnyomásra elindítja a támadást vagy foglalást attól függően, hogy van-e gyarmatosító hajó
     */
    private class StartWarListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            HashMap<GroundUnitType,Integer> chosenGroundUnits = new HashMap<>();
            for(Map.Entry<GroundUnitType,JSpinner> g : groundUnitSpinner.entrySet()){
                chosenGroundUnits.put(g.getKey(),(int) g.getValue().getValue());
            }

            HashMap<NavalUnitType,Integer> chosenNavalUnits = new HashMap<>();
            for(Map.Entry<NavalUnitType,JSpinner> n : navalUnitSpinner.entrySet()){
                chosenNavalUnits.put(n.getKey(),(int) n.getValue().getValue());
            }

            army.moveGroundUnits(from.getArmy(),chosenGroundUnits);
            army.moveNavalUnits(from.getArmy(),chosenNavalUnits);

            if(army.usedCapacity() == 0){
                JOptionPane.showMessageDialog(null,"Nem választottál még ki katonákat!","Információ",JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            double speed = army.averageSpeed();
            double distance = from.getIsland().getLocation(from).distance(to.getIsland().getLocation(to));
            long time = Math.round(distance / speed);

            if(from.getIsland() != to.getIsland()){
                if(army.usedCapacity() >= army.maxCapacity()){
                    JOptionPane.showMessageDialog(null,"A városod és a támadni kívánt város, másik szigeten van, ezért szükség van hajókra is. Szükséges helyek száma: "
                            + army.usedCapacity() + ", hajók által biztosított: " + army.maxCapacity(),"Információ",JOptionPane.INFORMATION_MESSAGE);
                    from.getArmy().add(army);
                    return;
                }
            }

            if(army.isColonizingArmy()){
                from.addTask(new ConquerTask(time,from,army,to));
                JOptionPane.showMessageDialog(null,"Foglalási kísérlet elindítva " + to.getName() + " város felé.","Információ",JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                from.addTask(new WarTask(time,from,army,to));
                JOptionPane.showMessageDialog(null,"Támadás elindítva " + to.getName() + " város felé.","Információ",JOptionPane.INFORMATION_MESSAGE);
            }
            dispose();
        }
    }
}
