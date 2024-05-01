package redhospitalaria.visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.JPasswordField;

public class PasswordField extends JPasswordField{
    
    String tooltip = "";
    public PasswordField(){
        setFont(new Font("Arial", Font.PLAIN, 22));
        setForeground(Color.white);
        setSize(400, 40);
        setOpaque(false);
        setBackground(Util.color.brighter());
        setBorder(null);
        getCaret().setVisible(false);
        setCaretColor(Color.white);
        for(int i = 0; i < 8; i++){
            tooltip += getEchoChar();
        }
    }
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
        g2d.setColor(getBackground().brighter());
        g2d.setFont(getFont());
        Insets ins = getInsets();
        FontMetrics fm = g.getFontMetrics();
        if(!isFocusOwner() && String.valueOf(getPassword()).isEmpty()){
            g.drawString(tooltip, ins.left, getHeight() / 2 + fm.getAscent() / 2 - 2);
        }
        super.paint(g);
    }
}
