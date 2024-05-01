/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.visual;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author david
 */
public class ListadosGui extends JPanel{
    private JLabel title;
    private DarkTable table;
    private BasicButton listar;
    private BasicButton limpiar;
    private BasicButton lista;
    private BasicButton graficos;
    private ComboBox<String> box;
    
    public ListadosGui() {
        initGui();
    }
    
    private void initGui(){
        setSize(960,600);
        setLocation(40,0);
        setBackground(Util.color);
        setLayout(null);
        title = new JLabel("Listar");
        title.setBounds(getWidth()/2-50, 10, 100, 40);
        title.setFont(new Font("Arial", Font.PLAIN, 24));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setForeground(Color.white);
        table = new DarkTable();
        table.setSize(getWidth(), getHeight() - 170);
        table.setEnabled(false);
        JScrollPane jsp = new JScrollPane(table);
        table.styleAndSetScrollPane(jsp);
        jsp.setBounds(0,110,table.getWidth(), table.getHeight());
        box = new ComboBox<>(new String[]{
            "Seleccionar",
            "doctores con mas de 10 años de experiencia",
            "doctores con menos de 10 años de experiencia",
            "doctores en el departamento",
            "doctores en el municipio",
            "mayores de 30 años"
        });
        box.setBounds(getWidth() / 2 - 290, 55, 280, 30);
        add(box);
        listar = new BasicButton(130, 40, "Listar");
        listar.setColor(new Color(65, 150, 240));
        listar.setLocation(getWidth() - 140,getHeight() - 50);
        listar.setFont(new Font("Arial", Font.PLAIN, 16));
        limpiar = new BasicButton(130, 40, "Limpiar");
        limpiar.setColor(new Color(235,15,60));
        limpiar.setLocation(getWidth() - 280,getHeight() - 50);
        limpiar.setFont(new Font("Arial", Font.PLAIN, 16));
        lista = new BasicButton(150, 30, "Lista");
        lista.setLocation(475, 55);
        lista.setColor(new Color(65, 150, 240));
        lista.setFont(new Font("arial", Font.PLAIN, 14));
        graficos = new BasicButton(150, 30, "graficos");
        graficos.setLocation(630, 55);
        graficos.setColor(new Color(65, 150, 240));
        graficos.setFont(new Font("arial", Font.PLAIN, 14));
        add(listar);
        add(limpiar);
        add(title);
        add(lista);
        add(graficos);
        add(jsp);
        setVisible(true);
    }

    public DarkTable getTable() {
        return table;
    }

    public void setTable(DarkTable table) {
        this.table = table;
    }

    public BasicButton getListar() {
        return listar;
    }

    public void setListar(BasicButton listar) {
        this.listar = listar;
    }

    public BasicButton getLista() {
        return lista;
    }

    public void setLista(BasicButton lista) {
        this.lista = lista;
    }

    public BasicButton getLimpiar() {
        return limpiar;
    }

    public void setLimpiar(BasicButton limpiar) {
        this.limpiar = limpiar;
    }

    public BasicButton getGraficos() {
        return graficos;
    }

    public void setGraficos(BasicButton graficos) {
        this.graficos = graficos;
    }

    public ComboBox<String> getBox() {
        return box;
    }

    public void setBox(ComboBox<String> box) {
        this.box = box;
    }
    
    
    
    public static void main(String[] args) {
        JFrame f = new JFrame("Sus");
        f.setSize(1014, 637);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setLayout(null);
        ListadosGui l = new ListadosGui();
        f.add(l);
        f.setVisible(true);
    }
}
