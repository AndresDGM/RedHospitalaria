package redhospitalaria.visual;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CitasGui extends JPanel{
    
    private JLabel title;
    private DarkTable table;
    private BasicButton solicitarCita;
    private BasicButton removerCita;
    private CitaForm citaCrear;
    private JScrollPane jsp;
    
    public CitasGui() {
        initGui();
    }
    
    private void initGui(){
        setSize(960,600);
        setLocation(40,0);
        setBackground(Util.color);
        setLayout(null);
        title = new JLabel("Citas");
        title.setBounds(getWidth()/2-50, 10, 100, 40);
        title.setFont(new Font("Roboto", Font.PLAIN, 24));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setForeground(Color.white);
        table = new DarkTable();
        table.setSize(getWidth(), getHeight() - 120);
        jsp = new JScrollPane(table);
        table.styleAndSetScrollPane(jsp);
        jsp.setBounds(0,50,table.getWidth(), table.getHeight());
        solicitarCita = new BasicButton(130, 40, "Solicitar cita");
        solicitarCita.setColor(new Color(65, 150, 240));
        solicitarCita.setLocation(getWidth() - 140,getHeight() - 50);
        solicitarCita.setFont(new Font("Arial", Font.PLAIN, 16));
        removerCita = new BasicButton(130, 40, "Asistir a cita");
        removerCita.setColor(new Color(38,230,102));
        removerCita.setLocation(getWidth() - 280,getHeight() - 50);
        removerCita.setFont(new Font("Arial", Font.PLAIN, 16));
        citaCrear = new CitaForm();
        add(solicitarCita);
        add(removerCita);
        add(title);
        add(jsp);
        add(citaCrear);
        setVisible(true);
    }

    public DarkTable getTable() {
        return table;
    }

    public void setTable(DarkTable table) {
        this.table = table;
    }

    public BasicButton getSolicitarCita() {
        return solicitarCita;
    }

    public void setSolicitarCita(BasicButton solicitarCita) {
        this.solicitarCita = solicitarCita;
    }

    public BasicButton getRemoverCita() {
        return removerCita;
    }

    public void setRemoverCita(BasicButton removerCita) {
        this.removerCita = removerCita;
    }

    public CitaForm getCitaCrear() {
        return citaCrear;
    }

    public void setCitaCrear(CitaForm citaCrear) {
        this.citaCrear = citaCrear;
    }
    
    public void ocultList(){
        jsp.setVisible(false);
        solicitarCita.setVisible(false);
        removerCita.setVisible(false);
    }
    
    public void showList(){
        jsp.setVisible(true);
        solicitarCita.setVisible(true);
        removerCita.setVisible(true);
    }

    public JScrollPane getJsp() {
        return jsp;
    }
    
    public void setDefault(){
        table.setModel(new DefaultTableModel());
        jsp.setVisible(true);
        citaCrear.setVisible(false);
        citaCrear.setDefault();
        citaCrear.getRazon().setText("");
        citaCrear.getUrgencia().setSelectedIndex(0);
        citaCrear.getArea().setSelectedIndex(0);
        showList();
    }
}
