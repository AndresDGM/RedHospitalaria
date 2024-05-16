package redhospitalaria.visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TextField extends JTextField{
    String tooltip = "";
    public TextField(String tooltip){
        this.tooltip = tooltip;
        setFont(new Font("Arial", Font.PLAIN, 18));
        setForeground(Color.white);
        setSize(400, 40);
        setOpaque(false);
        setBackground(Util.color.brighter());
        setBorder(new EmptyBorder(0, 5, 0, 5));
        getCaret().setVisible(false);
        setCaretColor(Color.white);
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
            g2d.drawString(tooltip, ins.left, getHeight() / 2 + fm.getAscent() / 2 - 2);
        }
        super.paint(g);
    }
}
