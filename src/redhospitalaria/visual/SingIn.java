/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.visual;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import raven.alerts.MessageAlerts;
/**
 *
 * @author david
 */
public class SingIn extends JPanel {

    private  JLabel msgS1;
    private JLabel msgS2;
    private JLabel msgS3;
    private JLabel passLabel;
    private JLabel idLabel;
    private BasicButton botonSingIn;
    private BasicButton botonSingInMode;
    private PasswordField password;
    private TextField id;
    private Image logo = new ImageIcon("src/images/MingcuteUserLockFill.png").getImage();
    private float alpha = 0f;
    private JLabel[] warnings = new JLabel[2];

    public SingIn() {
        setBounds(350, 0, 650, 600);
        setBackground(Util.color);
        setLayout(null);
        singInInit();
    }

    private void singInInit() {
        msgS1 = new JLabel("Iniciar Sesion");
        msgS1.setBounds(175, 50, 300, 100);
        msgS1.setFont(new Font("Arial", Font.BOLD, 34));
        msgS1.setHorizontalAlignment(JLabel.CENTER);
        msgS1.setVerticalAlignment(JLabel.CENTER);
        msgS1.setForeground(Color.WHITE);
        msgS2 = new JLabel("Ya tienes Cuenta?");
        msgS2.setBounds(25, 50, 300, 100);
        msgS2.setFont(new Font("Arial", Font.BOLD, 34));
        msgS2.setHorizontalAlignment(JLabel.CENTER);
        msgS2.setVerticalAlignment(JLabel.CENTER);
        msgS2.setForeground(Color.WHITE);
        msgS2.setVisible(false);
        msgS3 = new JLabel("Iniciar Secion");
        msgS3.setBounds(25, 300, 300, 100);
        msgS3.setFont(new Font("Arial", Font.BOLD, 22));
        msgS3.setHorizontalAlignment(JLabel.CENTER);
        msgS3.setVerticalAlignment(JLabel.CENTER);
        msgS3.setForeground(Color.WHITE);
        msgS3.setVisible(false);

        idLabel = new JLabel("Indentificacion");
        idLabel.setBounds(125, 120, 300, 100);
        idLabel.setFont(new Font("Arial", Font.BOLD, 18));
        idLabel.setHorizontalAlignment(JLabel.LEFT);
        idLabel.setVerticalAlignment(JLabel.CENTER);
        idLabel.setForeground(Color.WHITE);
        id = new TextField("Ingrese su cedula sin puntos ni comas");
        id.setLocation(125, 190);

        passLabel = new JLabel("Contraseña");
        passLabel.setBounds(125, 230, 300, 100);
        passLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passLabel.setHorizontalAlignment(JLabel.LEFT);
        passLabel.setVerticalAlignment(JLabel.CENTER);
        passLabel.setForeground(Color.WHITE);
        password = new PasswordField();
        password.setLocation(125, 300);

        botonSingIn = new BasicButton(100, 50, "Ingresar");
        botonSingIn.setLocation(275, 450);
        botonSingIn.getTextF().setFont(new Font("Arial", Font.PLAIN, 18));
        botonSingIn.getTextF().setVerticalAlignment(JTextField.CENTER);
        botonSingIn.setColor(new Color(65, 150, 240));

        botonSingInMode = new BasicButton(140, 50, "Iniciar secion");
        botonSingInMode.setLocation(105, 450);
        botonSingInMode.getTextF().setFont(new Font("Arial", Font.PLAIN, 18));
        botonSingInMode.getTextF().setVerticalAlignment(JTextField.CENTER);
        botonSingInMode.setColor(new Color(65, 150, 240));
        botonSingInMode.setVisible(false);
        warnings[0] = new JLabel();
        warnings[0].setBounds(125, 230, 400, 20);
        warnings[0].setForeground(new Color(255, 80, 85));
        warnings[0].setFont(new Font("Arial", Font.PLAIN, 16));
        warnings[0].setVisible(false);
        warnings[1] = new JLabel();
        warnings[1].setBounds(125, 340, 400, 20);
        warnings[1].setForeground(new Color(255, 80, 85));
        warnings[1].setFont(new Font("Arial", Font.PLAIN, 16));
        warnings[1].setVisible(false);
        add(botonSingInMode);
        add(botonSingIn);
        add(idLabel);
        add(getId());
        add(passLabel);
        add(password);
        add(msgS1);
        add(msgS2);
        add(msgS3);
        add(warnings[0]);
        add(warnings[1]);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                alpha));
        g2d.drawImage(logo, 100, 150, 150, 150, null);
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public float getAlpha() {
        return alpha;
    }

    public ArrayList<Component> getComps1() {
        return new ArrayList<>(Arrays.asList(msgS1, getId(), idLabel, password, passLabel, getBotonSingIn()));
    }

    public ArrayList<Component> getComps2() {
        return new ArrayList<>(Arrays.asList(msgS2, msgS3, botonSingInMode));
    }

    public JLabel[] getWarnings() {
        return warnings;
    }

    public void setDefault() {
        getId().setText("");
        password.setText("");
    }
   
    public BasicButton getBotonSingIn() {
        return botonSingIn;
    }

    public BasicButton getBotonSingInMode() {
        return botonSingInMode;
    }

    public PasswordField getPassword() {
        return password;
    }

    public TextField getId() {
        return id;
    }
}
