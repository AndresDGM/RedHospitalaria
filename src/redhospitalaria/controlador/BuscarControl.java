/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import raven.alerts.MessageAlerts;
import redhospitalaria.modelo.Modelo;
import redhospitalaria.modelo.UserSocket;
import redhospitalaria.modelo.Usuario;
import redhospitalaria.visual.BuscarGui;

/**
 *
 * @author david
 */
public class BuscarControl implements ActionListener {

    private Usuario user;
    private BuscarGui bg;
    private Modelo m;
    private PrincipalControl mainControl;

    public BuscarControl(Usuario user, BuscarGui bg, Modelo m, PrincipalControl mainControl) {
        this.user = user;
        this.bg = bg;
        this.m = m;
        this.mainControl = mainControl;
        bg.getBoton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if ((Boolean) mainControl.peticion("val:id:" + bg.getBuscar().getText())) {
                Object o = mainControl.peticion("get:" + bg.getBuscar().getText());
                if (o.getClass() == Usuario.class) {
                    bg.setInfo((Usuario) o);
                } else {
                    MessageAlerts
                            .getInstance()
                            .showMessage("Fallida",
                                    "No se encontro o no existe el usuario buscado",
                                    MessageAlerts.MessageType.WARNING);
                }
            } else {
                MessageAlerts
                        .getInstance()
                        .showMessage("Solo Digitos",
                                "ingrese una cedula sin puntos ni comas",
                                MessageAlerts.MessageType.DEFAULT);
            }
        } catch (NullPointerException ex) {
            MessageAlerts
                    .getInstance()
                    .showMessage("Error",
                            "No se pudo conectar al servidor",
                            MessageAlerts.MessageType.ERROR);
        }
    }
}
