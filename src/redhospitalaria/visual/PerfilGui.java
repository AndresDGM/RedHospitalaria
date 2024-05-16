/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.visual;

import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import redhospitalaria.modelo.Paciente;
import redhospitalaria.visual.DateChooserDJRaven.DateChooser;

public class PerfilGui extends JPanel {

    private TextField[] fields = new TextField[11];
    private JLabel[] labels = new JLabel[11];
    private JLabel title = new JLabel("Datos");
    private String[] labelsName = {
        "Nombre y Apellido",
        "Cedula",
        "Genero",
        "Eps",
        "Correo",
        "Fecha de nacimiento",
        "Edad",
        "Fecha de registro",
        "Departamento",
        "Municipio",
        "Direccion"
    };
    private BasicButton modificar;
    private BasicButton cancelar;
    private ComboBox<String> genero;
    private ComboBox<String> eps;
    private ComboBox<String> depar;
    private ComboBox<String> muni;
    private DateChooser fechaNacimiento = new DateChooser();
    private BasicButton fechaBoton;

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
        title.setSize(500, 30);
        title.setForeground(Color.white);
        title.setFont(new Font("Arial", Font.PLAIN, 24));
        title.setLocation(getWidth() / 2 - 200, 10);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        fields[0].setLocation(50, 120);
        fields[1].setLocation(490, 120);
        fields[2].setLocation(50, 200);
        genero = new ComboBox<>(Util.generos);
        genero.setBounds(fields[2].getBounds());
        genero.setVisible(false);
        fields[3].setLocation(490, 200);
        eps = new ComboBox<>(Util.epsItems);
        eps.setBounds(fields[3].getBounds());
        eps.setVisible(false);
        fields[4].setLocation(50, 280);
        fields[5].setLocation(490, 280);
        fechaNacimiento.setTextRefernce(fields[5]);
        fechaNacimiento.setForeground(new Color(65, 150, 240));
        fechaNacimiento.setBackground(new Color(45, 45, 45));
        fechaBoton = new BasicButton(30, 30, "...");
        fields[5].setEnabled(false);
        fechaBoton.setLocation(900, 280);
        fechaBoton.getTextF().setFont(new Font("Arial", Font.PLAIN, 16));
        fechaBoton.getTextF().setVerticalAlignment(JTextField.CENTER);
        fechaBoton.setVisible(false);
        fields[6].setLocation(50, 360);
        fields[7].setLocation(490, 360);
        fields[8].setLocation(50, 440);
        depar = new ComboBox<>(Util.departamentos);
        depar.setBounds(fields[8].getBounds());
        depar.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                if (depar.getSelectedItem() != null &&
                        !depar.getSelectedItem().equals(depar.getItemAt(0))) {
                    muni.removeAllItems();
                    int index = Arrays.asList(Util.departamentos).indexOf(depar.getSelectedItem()) - 1;
                    for (String s : Util.municipios[index]) {
                        muni.addItem(s);
                    }
                    muni.setEnabled(true);
                } else {
                    muni.removeAllItems();
                    muni.setEnabled(false);
                }
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                if (depar.getSelectedItem() != null &&
                        !depar.getSelectedItem().equals(depar.getItemAt(0))) {
                    muni.removeAllItems();
                    int index = Arrays.asList(Util.departamentos).indexOf(depar.getSelectedItem()) - 1;
                    for (String s : Util.municipios[index]) {
                        muni.addItem(s);
                    }
                    muni.setEnabled(true);
                } else {
                    muni.removeAllItems();
                    muni.setEnabled(false);
                }
            }
        });
        depar.setVisible(false);
        fields[9].setLocation(490, 440);
        muni = new ComboBox<>();
        muni.setBounds(fields[9].getBounds());
        muni.setVisible(false);
        fields[10].setLocation(getWidth() / 2 - 200, 520);
        labels[0].setLocation(50, 95);
        labels[1].setLocation(490, 95);
        labels[2].setLocation(50, 175);
        labels[3].setLocation(490, 175);
        labels[4].setLocation(50, 255);
        labels[5].setLocation(490, 255);
        labels[6].setLocation(50, 335);
        labels[7].setLocation(490, 335);
        labels[8].setLocation(50, 415);
        labels[9].setLocation(490, 415);
        labels[10].setLocation(getWidth() / 2 - 200, 495);
        modificar = new BasicButton(150, 30, "Modificar");
        modificar.setLocation(690, 520);
        modificar.setColor(new Color(65, 150, 240));
        modificar.setFont(new Font("arial", Font.PLAIN, 14));
        cancelar = new BasicButton(150, 30, "Cancelar");
        cancelar.setLocation(120, 520);
        cancelar.setColor(new Color(65, 150, 240));
        cancelar.setFont(new Font("arial", Font.PLAIN, 14));
        cancelar.setVisible(false);
        add(modificar);
        add(cancelar);
        add(genero);
        add(depar);
        add(muni);
        add(eps);
        add(fechaBoton);
        add(modificar);
        add(cancelar);
        add(title);
        setVisible(true);
    }
    
    public void setInformacion(Paciente u){
        fields[0].setText(u.getNombre());
        fields[1].setText(String.valueOf(u.getCedula()));
        fields[2].setText(u.getGenero());
        genero.setSelectedItem(u.getGenero());
        fields[3].setText(u.getEps());
        eps.setSelectedItem(u.getEps());
        fields[4].setText(u.getCorreo());
        fields[5].setText(u.getFechaNacimiento());
        fields[6].setText(String.valueOf(u.getEdad()));
        fields[7].setText(u.getFechaRegistro());
        fields[8].setText(u.getDepartamento());
        depar.setSelectedItem(u.getDepartamento());
        fields[9].setText(u.getMunicipio());
        int index = Arrays.asList(Util.departamentos).indexOf(depar.getSelectedItem()) - 1;
        for (String s : Util.municipios[index]) {
            muni.addItem(s);
        }
        muni.setSelectedItem(u.getMunicipio());
        fields[10].setText(u.getDireccion());
    }

    public JLabel getTitle() {
        return title;
    }

    public TextField[] getFields() {
        return fields;
    }

    public JLabel[] getLabels() {
        return labels;
    }

    public String[] getLabelsName() {
        return labelsName;
    }

    public BasicButton getModificar() {
        return modificar;
    }

    public BasicButton getCancelar() {
        return cancelar;
    }

    public ComboBox<String> getGenero() {
        return genero;
    }

    public ComboBox<String> getEps() {
        return eps;
    }

    public ComboBox<String> getDepar() {
        return depar;
    }

    public ComboBox<String> getMuni() {
        return muni;
    }

    public BasicButton getFechaBoton() {
        return fechaBoton;
    }

    public DateChooser getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    public void setDefault(){
        cancelar.setVisible(false);
        genero.setSelectedIndex(0);
        eps.setSelectedIndex(0);
        depar.setSelectedIndex(0);
        muni.removeAllItems();
        muni.setEnabled(false);
        fechaBoton.setVisible(false);
        fields[5].setEnabled(false);
        for (TextField field : fields) {
            field.setVisible(true);
            field.setText("");
        }
        title.setText("Datos");
    }
}
