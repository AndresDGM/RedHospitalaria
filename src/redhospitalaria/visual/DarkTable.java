package redhospitalaria.visual;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class DarkTable extends JTable {

    public DarkTable() {
        getTableHeader().setDefaultRenderer(new DarkHeader());
        getTableHeader().setPreferredSize(new Dimension(120, 35));
        getTableHeader().setBackground(Util.color.brighter());
        setAutoResizeMode(AUTO_RESIZE_OFF);
        HoverIndex hoverRow = new HoverIndex();
        setDefaultRenderer(Object.class, new DarkCell(hoverRow));
        setGridColor(Util.color.brighter());
        setRowHeight(30);
    }

    public void styleAndSetScrollPane(JScrollPane scrollPane) {
        scrollPane.setBackground(Util.color.brighter());
        scrollPane.getViewport().setBackground(Util.color.brighter());
        scrollPane.setBorder(new EmptyBorder(0, 5, 0, 5));
        scrollPane.setVerticalScrollBar(new ScrollBar());
        ScrollBar hsb = new ScrollBar();
        hsb.setOrientation(JScrollBar.HORIZONTAL);
        scrollPane.setHorizontalScrollBar(hsb);
    }

    private class DarkHeader extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            c.setBackground(Util.color.brighter());
            c.setForeground(Color.white);
            c.setFont(new Font("Arial", Font.PLAIN, 12));
            return c;
        }
    }

    public class DarkCell extends JTextArea implements TableCellRenderer {

        private final List<List<Integer>> rowAndCellHeights = new ArrayList<>();
        private final HoverIndex hoverRow;

        public DarkCell(HoverIndex hoverRow) {
            this.hoverRow = hoverRow;
            setWrapStyleWord(true);
            setLineWrap(true);
            setOpaque(true);
            setBorder(new EmptyBorder(8, 10, 8, 10));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(Objects.toString(value, ""));
            adjustRowHeight(table, row, column);
            if(isSelected){
                if(row % 2 == 0){
                    setBackground(new Color(30,115,165));
                }else{
                    setBackground(new Color(80,145,220));
                }
            }else{
                if(row % 2 == 0){
                    setBackground(new Color(80,80,80));
                }else{
                    setBackground(new Color(100,100,100));
                }
            }
            setFont(table.getFont());
            return this;
        }

        private void adjustRowHeight(JTable table, int row, int column) {
            setBounds(table.getCellRect(row, column, false));
            int preferredHeight = getPreferredSize().height;
            while (rowAndCellHeights.size() <= row) {
                rowAndCellHeights.add(new ArrayList<>(column));
            }
            List<Integer> list = rowAndCellHeights.get(row);
            while (list.size() <= column) {
                list.add(0);
            }
            list.set(column, preferredHeight);
            int max = list.stream().max((x, y) -> Integer.compare(x, y)).get();
            if (table.getRowHeight(row) != max) {
                table.setRowHeight(row, max);
            }
        }
    }

    private class HoverIndex {

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        private int index = -1;
    }
}
