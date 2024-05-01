package redhospitalaria.visual;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class DarkTable extends JTable{
    
    private JScrollPane scrollPane;

    public DarkTable() {
        getTableHeader().setDefaultRenderer(new DarkHeader());
        getTableHeader().setPreferredSize(new Dimension(0,35));
        getTableHeader().setBackground(Util.color.brighter());
        setDefaultRenderer(Object.class, new DarkCell());
        setGridColor(Util.color.brighter());
        setRowHeight(30);
    }
    
    public void styleAndSetScrollPane(JScrollPane scrollPane){
        scrollPane.setBackground(Util.color.brighter());
        scrollPane.getViewport().setBackground(Util.color.brighter());
        scrollPane.setBorder(new EmptyBorder(0,5,0,5));
        scrollPane.setVerticalScrollBar(new ScrollBar());
        this.scrollPane = scrollPane;
    }  
    
    private class DarkHeader extends DefaultTableCellRenderer{
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            c.setBackground(Util.color.brighter());
            c.setForeground(Color.white);
            c.setFont(new Font("Arial", Font.PLAIN, 12));
            return c;
        } 
    }
    
    private class DarkCell extends DefaultTableCellRenderer{

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if(isCellSelected(row, column)){
                if(row % 2 == 0){
                    c.setBackground(new Color(30,115,165));
                }else{
                    c.setBackground(new Color(80,145,220));
                }
            }else{
                if(row % 2 == 0){
                    c.setBackground(new Color(80,80,80));
                }else{
                    c.setBackground(new Color(100,100,100));
                }
            }
            c.setForeground(Color.white);
            setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
            return c;
        }
        
    }
}
