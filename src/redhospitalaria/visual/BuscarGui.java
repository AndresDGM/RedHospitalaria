/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import redhospitalaria.modelo.Usuario;

public class BuscarGui extends JPanel {

    private PerfilGui pg = new PerfilGui();
    private TextField buscar;
    private BasicButton boton;
    private JLabel title;

    public BuscarGui() {
        setSize(pg.getSize());
        setLocation(pg.getX(), pg.getY());
        setLayout(null);
        setBackground(pg.getBackground());
        pg.setSize(pg.getWidth(), pg.getHeight() - 70);
        pg.setLocation(0, 100);
        pg.getTitle().setLocation(30, pg.getTitle().getY() - 10);
        pg.getTitle().setHorizontalAlignment(JLabel.LEFT);
        title = new JLabel("Buscar");
        title.setBounds(getWidth() / 2 - 50, 10, 100, 40);
        title.setFont(new Font("Roboto", Font.PLAIN, 24));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setForeground(Color.white);
        buscar = new TextField("Buscar...");
        buscar.setSize(200, 30);
        buscar.setLocation(getWidth() / 2 - 230 / 2, 50);
        boton = new BasicButton();
        boton.setSize(30, 30);
        boton.setLocation(buscar.getX() + buscar.getWidth() + 5, 50);
        boton.setIcon("src/images/WhhFindfriends.png");
        add(buscar);
        add(pg);
        add(title);
        add(boton);
    }

    public BasicButton getBoton() {
        return boton;
    }

    public void setBoton(BasicButton boton) {
        this.boton = boton;
    }

    public void setInfo(Usuario u) {
        pg.setInformacion(u);
        pg.getTitle().setText("Datos - " + u.getTipo());
    }

    public JLabel getTitle() {
        return title;
    }

    public void setTitle(JLabel title) {
        this.title = title;
    }

    public TextField getBuscar() {
        return buscar;
    }

    public void setBuscar(TextField buscar) {
        this.buscar = buscar;
    }
}
