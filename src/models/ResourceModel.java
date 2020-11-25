package models;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResourceModel extends AbstractTableModel {

    private List<Double> resourcePerHour = new ArrayList<>();

    @Override
    public int getRowCount() {
        return resourcePerHour.size();
    }

    public void addData(Double d){
        resourcePerHour.add(d);
        Collections.sort(resourcePerHour);
        fireTableDataChanged();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0) {
            return rowIndex + 1;
        }
        else{
            return resourcePerHour.get(rowIndex);
        }
    }

    @Override
    public String getColumnName(int column) {
        if(column == 1){
            return "Hozam";
        }
        else{
            return "Szint";
        }
    }
}
