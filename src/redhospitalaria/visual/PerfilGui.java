/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.visual;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import redhospitalaria.modelo.Usuario;

public class PerfilGui extends JPanel {

    private TextField[] fields = new TextField[9];
    private JLabel[] labels = new JLabel[9];
    private JLabel title = new JLabel("Datos");
    private String[] labelsName = {
        "Nombre y Apellido",
        "Cedula",
        "Correo",
        "Fecha de nacimiento",
        "Edad",
        "Fecha de registro",
        "Departamento",
        "Municipio",
        "Direccion"
    };

    public PerfilGui() {
        setSize(960, 600);
        setLocation(40, 0);
        setBackground(Util.color);
        setLayout(null);
        for (int i = 0; i < fields.length; i++) {
            fields[i] = new TextField("");
            fields[i].setSize(400, 30);
            fields[i].setEditable(false);
            labels[i] = new JLabel(labelsName[i]);
            labels[i].setSize(400, 30);
            labels[i].setForeground(Color.white);
            labels[i].setFont(new Font("Arial", Font.PLAIN, 14));
            add(fields[i]);
            add(labels[i]);
        }
        title.setSize(400, 30);
        title.setForeground(Color.white);
        title.setFont(new Font("Arial", Font.PLAIN, 24));
        title.setLocation(getWidth() / 2 - 200, 10);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        fields[0].setLocation(50, 120);
        fields[1].setLocation(490, 120);
        fields[2].setLocation(50, 200);
        fields[3].setLocation(490, 200);
        fields[4].setLocation(50, 280);
        fields[5].setLocation(490, 280);
        fields[6].setLocation(50, 360);
        fields[7].setLocation(490, 360);
        fields[8].setLocation(getWidth() / 2 - 200, 440);
        labels[0].setLocation(50, 95);
        labels[1].setLocation(490, 95);
        labels[2].setLocation(50, 175);
        labels[3].setLocation(490, 175);
        labels[4].setLocation(50, 255);
        labels[5].setLocation(490, 255);
        labels[6].setLocation(50, 335);
        labels[7].setLocation(490, 335);
        labels[8].setLocation(getWidth() / 2 - 200, 415);
        add(title);
        setVisible(true);
    }
    
    public void setInformacion(Usuario u){
        fields[0].setText(u.getNombre());
        fields[1].setText(String.valueOf(u.getCedula()));
        fields[2].setText(u.getCorreo());
        fields[3].setText(u.getFechaNacimiento());
        fields[4].setText(String.valueOf(u.getEdad()));
        fields[5].setText(u.getFechaRegistro());
        fields[6].setText(u.getDepartamento());
        fields[7].setText(u.getMunicipio());
        fields[8].setText(u.getDireccion());
    }

    public JLabel getTitle() {
        return title;
    }
}
