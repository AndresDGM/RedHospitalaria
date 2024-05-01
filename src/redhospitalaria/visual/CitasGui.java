package redhospitalaria.visual;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class CitasGui extends JPanel{
    
    private JLabel title;
    private DarkTable table;
    private BasicButton solicitarCita;
    private BasicButton removerCita;
    
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
        JScrollPane jsp = new JScrollPane(table);
        table.styleAndSetScrollPane(jsp);
        jsp.setBounds(0,50,table.getWidth(), table.getHeight());
        solicitarCita = new BasicButton(130, 40, "Solicitar cita");
        solicitarCita.setColor(new Color(65, 150, 240));
        solicitarCita.setLocation(getWidth() - 140,getHeight() - 50);
        solicitarCita.setFont(new Font("Arial", Font.PLAIN, 16));
        removerCita = new BasicButton(130, 40, "asistir a cita");
        removerCita.setColor(new Color(38,230,102));
        removerCita.setLocation(getWidth() - 280,getHeight() - 50);
        removerCita.setFont(new Font("Arial", Font.PLAIN, 16));
        add(solicitarCita);
        add(removerCita);
        add(title);
        add(jsp);
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
}
