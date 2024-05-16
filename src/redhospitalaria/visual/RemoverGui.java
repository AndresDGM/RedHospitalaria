/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.visual;

import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author david
 */
public class RemoverGui extends BuscarGui{

    public RemoverGui() {
        super();
        setVisible(false);
        getTitle().setText("Eliminar");
        BasicButton b = new BasicButton(100, 30, "Eliminar");
        b.setLocation(getBoton().getX(), getBoton().getY());
        b.setColor(new Color(255, 80, 85));
        b.getTextF().setFont(new Font("arial", Font.PLAIN, 18));
        getBoton().setLocation(getBoton().getX() - 50, getBoton().getY());
        getBuscar().setLocation(getBuscar().getX() - 50, getBuscar().getY());
        add(b);
    }   
}
