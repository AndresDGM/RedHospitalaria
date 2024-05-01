/*Boton con bordes redondeados para proposito general
se hizo a la clase abstracto para definir al momento de
instanciar que va a hacer el boton
 */
package redhospitalaria.visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

public class BasicButton extends JButton {

    private JLabel text = new JLabel("");
    
    private Image icon;

    private Color color = new Color(77, 77, 77);

    private int arcW = 15;//redondeo horizontal

    private int arcH = 15;//redondeo vertical

    public BasicButton(int w, int h, String texto) {
        setSize(w, h);
        setBackground(color);
        setForeground(Color.WHITE);
        setBorder(null);
        setLayout(null);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setFocusPainted(false);
        text.setText(texto);
        text.setBounds(0, 0, w, h);
        text.setFont(new Font("Arial", Font.PLAIN, 32));
        text.setHorizontalAlignment(JTextField.CENTER);
        text.setVerticalAlignment(JTextField.CENTER);
        setContentAreaFilled(false);
        setOpaque(false);
        add(text);
        accionMouse();
        setVisible(true);
    }

    public BasicButton() {
        setBounds(457, 650, 160, 54);
        setBackground(color);
        setLayout(null);
        setBorder(null);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        text.setBounds(0, 0, getWidth(), getHeight());
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Arial", Font.PLAIN, 32));
        text.setHorizontalAlignment(JTextField.CENTER);
        text.setVerticalAlignment(JTextField.CENTER);
        setContentAreaFilled(false);
        setOpaque(false);
        setFocusPainted(false);
        add(text);
        accionMouse();
        setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcW, arcH);
        if(icon != null){
            g2.drawImage(icon, 5, 5, getHeight() - 10, getHeight() - 10, null);
        }
        super.paintComponent(g);
    }

    public void accionMouse() {
        MouseListener m = new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                if (isFocusable()) {
                    setBackground(color.darker());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (isFocusable()) {
                    setBackground(color);
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (isFocusable()) {
                    setBackground(color);
                }
            }
        };
        addMouseListener(m);
    }

    public JLabel getTextF() {
        return text;
    }

    public void setText(JLabel text) {
        this.text = text;
    }

    public void setText(String texto) {
        text.setText(texto);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }

    public int getArcW() {
        return arcW;
    }

    public void setArcW(int arcW) {
        this.arcW = arcW;
    }

    public int getArcH() {
        return arcH;
    }

    public void setArcH(int arcH) {
        this.arcH = arcH;
    }

    public void setRound(int arcW, int arcH) {
        this.arcW = arcW;
        this.arcH = arcH;
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        text.setSize(width, height);
    }

    @Override
    public void setForeground(Color fg) {
        super.setForeground(fg);
        if (text != null) {
            text.setForeground(fg);
        }
    }

    @Override
    public void setFont(Font font) {
        super.setFont(font); 
        if(text != null) text.setFont(font);
    }
    
    

    public void setIcon(String source) {
        if(icon == null){
            text.setLocation(getHeight(), text.getY());
        }
        icon = new ImageIcon(source).getImage();
    }
}
