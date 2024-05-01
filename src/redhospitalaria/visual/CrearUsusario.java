/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.visual;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author david
 */
public class CrearUsusario extends JPanel {

    private SingUp crearGui;
    private JLabel title;
    private ComboBox<String> combo;

    public CrearUsusario() {
        setBounds(40, 0, 960, 600);
        setBackground(Util.color);
        setLayout(null);
        crearGui = new SingUp();
        crearGui.setBackground(Util.color);
        crearGui.changeVisible(1);
        crearGui.setSize(650, 600);
        crearGui.setLocation(getWidth() / 2 - 325, 0);
        crearGui.setAlpha(0f);
        crearGui.getMsgS1().setVisible(false);
        crearGui.getMsgS2().setVisible(false);
        crearGui.getMsgS3().setVisible(false);
        crearGui.getBotonSingUpMode().setVisible(false);
        combo = new ComboBox<>(new String[]{"Seleccionar", "Paciente", "Doctor", "Administrador"});
        combo.setBounds(getWidth() / 2 - 100, 70, 200, 30);
        title = new JLabel("Crear");
        title.setBounds(getWidth() / 2 - 50, 10, 100, 40);
        title.setFont(new Font("Roboto", Font.PLAIN, 24));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setForeground(Color.white);
        add(combo);
        add(title);
        add(crearGui);
    }

    public SingUp getCrearGui() {
        return crearGui;
    }

    public void setCrearGui(SingUp crearGui) {
        this.crearGui = crearGui;
    }

    public ComboBox<String> getCombo() {
        return combo;
    }

    public void setCombo(ComboBox<String> combo) {
        this.combo = combo;
    }    
}
