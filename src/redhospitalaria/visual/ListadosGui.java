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
import javax.swing.table.DefaultTableModel;

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
    private ComboBox<String> opcion1;
    private ComboBox<String> opcion2;
    private ComboBox<String> opcion3;
    public final String[] colums = {
                "Cedula",
                "Nombre",
                "Genero",
                "Eps",
                "Edad",
                "Correo",
                "Fecha de nacimiento",
                "Fecha de registro",
                "Dirrecion",
                "Departamento",
                "Municipio"
        };
    
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
        table.setSize(getWidth(), getHeight() - 200);
        table.setModel(new DefaultTableModel(colums,0));
        JScrollPane jsp = new JScrollPane(table);
        table.styleAndSetScrollPane(jsp);
        jsp.setBounds(0,140,table.getWidth(), table.getHeight());
        box = new ComboBox<>(new String[]{
            "Seleccionar",
            "Doctores con mas de 10 años de experiencia",
            "Doctores con menos de 10 años de experiencia",
            "Doctores en el departamento",
            "Doctores en el municipio",
            "Doctores mayores de 30 años",
            "Doctores - Especializacion - Citas Atendidas - Departamento",
            "Genero - Edad - Eps",
            "Genero - Departamento - Municipio",
            "Eps - Edad - Departamento",
            "Edad - Registro - Citas Solicitadas",
            "Eps - Departamento - Municipio",
            "Edad - Registro - Citas Atendidas",
            "Genero - Edad - Citas Atendidas",
            "Genero - Edad - Citas Solicitadas",
            "Registro - Citas Atendidas - Citas Solicitadas"
        });
        box.setBounds(getWidth() / 2 - 340, 55, 330, 30);
        opcion1 = new ComboBox<>();
        opcion1.setBounds(getWidth() / 2 - 430, 95, 280, 30);
        opcion2 = new ComboBox<>();
        opcion2.setBounds(getWidth() / 2 - 140, 95, 280, 30);
        opcion3 = new ComboBox<>();
        opcion3.setBounds(getWidth() / 2 + 150, 95, 280, 30);
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
        opcion1.setVisible(false);
        opcion2.setVisible(false);
        opcion3.setVisible(false);
        add(listar);
        add(limpiar);
        add(title);
        add(lista);
        add(graficos);
        add(jsp);
        add(opcion1);
        add(opcion2);
        add(opcion3);
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
    
    public void setDefault(){
        table.setVisible(true);
        table.setModel(new DefaultTableModel(colums,0));
        box.setSelectedIndex(0);
    }

    public ComboBox<String> getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(ComboBox<String> opcion1) {
        this.opcion1 = opcion1;
    }

    public ComboBox<String> getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(ComboBox<String> opcion2) {
        this.opcion2 = opcion2;
    }

    public ComboBox<String> getOpcion3() {
        return opcion3;
    }

    public void setOpcion3(ComboBox<String> opcion3) {
        this.opcion3 = opcion3;
    }
    
    public static void main(String[] args) {
        JFrame f = new JFrame("Sus");
        f.setSize(1014, 637);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setLayout(null);
        ListadosGui l = new ListadosGui();
        l.setVisible(true);
        f.add(l);
        f.setVisible(true);
    }
}
