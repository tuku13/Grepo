package guis;

import buildings.Building;
import models.ResourceModel;
import game.City;

import javax.swing.*;
import java.awt.*;

/**
 * Táblázatba kiírja az szintenkénti termelését
 */
public class ResourceInfoFrame extends JFrame {
    private City city;
    private Building building;
    private ResourceModel data;

    /**
     * Konstruktor
     * @param c város
     * @param b épület
     */
    public ResourceInfoFrame(City c, Building b){
        super(b.getBuildingType().getName() + " lvl " + b.getLevel());
        this.building = b;
        this.city = c;
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        setMinimumSize(new Dimension(300,200));

        //táblázat feltöltése szint | hozam párokkal
        data = new ResourceModel();
        for(int i = 0; i<= building.getBuildingType().getMaxLevel()-1;i++){
            switch (building.getBuildingType()){
                case QUARRY: data.addData((i+1) * 20.0 * city.getIsland().getStoneMultiplier() * 2);
                    break;
                case TEMPLE: data.addData((i+1) * 25.0 * 2 );
                    break;
                case TIMBER_CAMP: data.addData((i+1) * 20.0 * city.getIsland().getWoodMultiplier() * 2);
                    break;
                case SILVER_MINE: data.addData((i+1) * 20.0 * city.getIsland().getSilverMultiplier() *2);
                    break;
            }
        }

        initComponents();
    }

    /**
     * Inicializálja a komponenseket
     */
    private void initComponents(){
        JTable table = new JTable(data);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);

        table.setBackground(new Color(255,223,151));
        table.getTableHeader().setBackground(new Color(43,83,134));
        Font f = table.getTableHeader().getFont();
        table.getTableHeader().setForeground(new Color(255,255,255));
        table.getTableHeader().setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));

        this.add(scrollPane,BorderLayout.CENTER);
    }
}
