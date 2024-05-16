/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author david
 */
public class TextArea extends JTextArea{
    String tooltip = "Escribe aqui...";
    public TextArea(){
        setFont(new Font("Arial", Font.PLAIN, 16));
        setForeground(Color.white);
        setOpaque(false);
        setBackground(Util.color.brighter());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        getCaret().setVisible(false);
        setLineWrap(true);
        setWrapStyleWord(true);
        setCaretColor(Color.white);
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                repaint();
            }
            
        });
    }
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
        g2d.setColor(getBackground().brighter());
        g2d.setFont(getFont());
        Insets ins = getInsets();
        FontMetrics fm = g.getFontMetrics();
        if(!isFocusOwner() && getText().isEmpty()){
            g2d.drawString(tooltip, ins.left, fm.getAscent() + 2);
        }
        super.paint(g);
    }
}
