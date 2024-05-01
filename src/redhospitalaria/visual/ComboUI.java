/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.visual;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author david
 */
public class ComboUI extends BasicComboBoxUI {

    private Color color = Util.color.brighter();
    
    private float alpha = 1f;

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        Border border = new Border();
        JTextField txt = (JTextField) comboBox.getEditor().getEditorComponent();
        txt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                comboBox.showPopup();
            }
        });
        txt.setOpaque(false);
        txt.setForeground(Color.white);
        txt.setBorder(new EmptyBorder(0,4,0,4));
        comboBox.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                arrowButton.setBackground(Util.color);
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                arrowButton.setBackground(Util.color.darker());
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                arrowButton.setBackground(Util.color.darker());
            }
        });
        AutoCompleteDecorator.decorate(comboBox);
        comboBox.setBackground(color);
        comboBox.setBorder(border);
    }

    @Override
    protected JButton createArrowButton() {
        return new ArrowButton();
    }

    @Override
    protected ComboPopup createPopup() {
        return new ItemPopup(comboBox);
    }

    @Override
    protected ListCellRenderer<Object> createRenderer() {
        return new ListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                String text = value == null ? "" : value.toString();
                JLabel label = new JLabel(text);
                label.setFont(comboBox.getFont());
                label.setBorder(new EmptyBorder(5, 8, 5, 8));
                label.setForeground(Color.white);
                if (isSelected) {
                    label.setOpaque(true);
                    label.setBackground(Util.color.brighter().brighter());
                    label.setForeground(new Color(17, 155, 215));
                }
                return label;
            }

        };
    }

    @Override
    public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
    }

    private class ArrowButton extends JButton {

        public ArrowButton() {
            setContentAreaFilled(false);
            setBorder(new EmptyBorder(5, 5, 5, 5));
            setBackground(Util.color.darker());
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int width = getWidth(), height = getHeight(), sizex = 12, sizey = 8;
            int x = (width - sizex) / 2, y = (height - sizey) / 2;
            int[] px = {x, x + sizex, x + sizex / 2}, py = {y, y, y + sizey};
            g2d.setColor(getBackground());
            g2d.fillPolygon(px, py, px.length);
            g2d.dispose();
        }

    }

    private class ItemPopup extends BasicComboPopup {

        public ItemPopup(JComboBox combo) {
            super(combo);
            setBorder(null);
        }

        @Override
        protected JScrollPane createScroller() {
            JScrollPane scroll = super.createScroller();
            list.setBackground(Util.color.brighter());
            list.setForeground(Color.white);
            ScrollBar sb = new ScrollBar();
            sb.setPreferredSize(new Dimension(8, 60));
            scroll.setVerticalScrollBar(sb);
            return scroll;
        }
    }

    private class Border extends EmptyBorder {

        private Color focuscolor = new Color(65, 150, 240);
        private Color color = new Color(128, 128, 128);

        public Border(int border) {
            super(border, border, border, border);
        }

        public Border() {
            this(5);
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        }

        public Color getFocuscolor() {
            return focuscolor;
        }

        public void setFocuscolor(Color focuscolor) {
            this.focuscolor = focuscolor;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }
    
    
}
