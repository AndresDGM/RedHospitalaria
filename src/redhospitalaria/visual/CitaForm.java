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
public class CitaForm extends JPanel {

    private ComboBox<String> urgencia, area;
    private TextArea razon;
    private JLabel labelUrgencia, labelArea, labelRazon, title;
    private BasicButton crear, atras;

    public CitaForm() {
        initGui();
    }

    private void initGui() {
        setSize(960, 500);
        setLocation(0, 70);
        setBackground(Util.color);
        setLayout(null);
        title = new JLabel("Crear cita");
        title.setBounds(280, 10, 400, 40);
        title.setFont(new Font("Roboto", Font.PLAIN, 24));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setForeground(Color.white);
        labelUrgencia = new JLabel("Nivel de urgencia");
        labelUrgencia.setBounds(280, 30, 300, 100);
        labelUrgencia.setFont(new Font("Arial", Font.BOLD, 18));
        labelUrgencia.setHorizontalAlignment(JLabel.LEFT);
        labelUrgencia.setVerticalAlignment(JLabel.CENTER);
        labelUrgencia.setForeground(Color.WHITE);
        urgencia = new ComboBox<>(Util.urgencias);
        urgencia.setBounds(280, 100, 400, 40);
        urgencia.setEnabled(true);
        labelArea= new JLabel("Area");
        labelArea.setBounds(280, 120, 300, 100);
        labelArea.setFont(new Font("Arial", Font.BOLD, 18));
        labelArea.setHorizontalAlignment(JLabel.LEFT);
        labelArea.setVerticalAlignment(JLabel.CENTER);
        labelArea.setForeground(Color.WHITE);
        area = new ComboBox<>(Util.areas);
        area.setBounds(280, 190, 400, 40);
        area.setEnabled(true);
        labelRazon = new JLabel("Razon");
        labelRazon.setBounds(280, 210, 300, 100);
        labelRazon.setFont(new Font("Arial", Font.BOLD, 18));
        labelRazon.setHorizontalAlignment(JLabel.LEFT);
        labelRazon.setVerticalAlignment(JLabel.CENTER);
        labelRazon.setForeground(Color.WHITE);
        razon = new TextArea();
        razon.setBounds(280, 280, 400, 200);
        crear = new BasicButton(130, 40, "Solicitar cita");
        crear.setColor(new Color(65, 150, 240));
        crear.setLocation(700,430);
        crear.setFont(new Font("Arial", Font.PLAIN, 16));
        atras = new BasicButton(130, 40, "Atras");
        atras.setColor(new Color(255, 80, 85));
        atras.setLocation(130,430);
        atras.setFont(new Font("Arial", Font.PLAIN, 16));
        add(title);
        add(labelUrgencia);
        add(urgencia);
        add(labelArea);
        add(area);
        add(labelRazon);
        add(razon);
        add(crear);
        add(atras);
        setVisible(false);
    }

    public ComboBox<String> getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(ComboBox<String> urgencia) {
        this.urgencia = urgencia;
    }

    public ComboBox<String> getArea() {
        return area;
    }

    public void setArea(ComboBox<String> area) {
        this.area = area;
    }

    public TextArea getRazon() {
        return razon;
    }

    public void setRazon(TextArea razon) {
        this.razon = razon;
    }

    public JLabel getLabelUrgencia() {
        return labelUrgencia;
    }

    public void setLabelUrgencia(JLabel labelUrgencia) {
        this.labelUrgencia = labelUrgencia;
    }

    public JLabel getLabelArea() {
        return labelArea;
    }

    public void setLabelArea(JLabel labelArea) {
        this.labelArea = labelArea;
    }

    public JLabel getLabelRazon() {
        return labelRazon;
    }

    public void setLabelRazon(JLabel labelRazon) {
        this.labelRazon = labelRazon;
    }

    public JLabel getTitle() {
        return title;
    }

    public void setTitle(JLabel title) {
        this.title = title;
    }

    public BasicButton getCrear() {
        return crear;
    }

    public void setCrear(BasicButton crear) {
        this.crear = crear;
    }

    public BasicButton getAtras() {
        return atras;
    }

    public void setAtras(BasicButton atras) {
        this.atras = atras;
    }
    
    public void setDefault(){
        urgencia.setSelectedIndex(0);
        area.setSelectedIndex(0);
        razon.setText("");
    }
}
