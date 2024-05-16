/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import raven.alerts.MessageAlerts;
import redhospitalaria.modelo.Administrador;
import redhospitalaria.modelo.Doctor;
import redhospitalaria.modelo.Modelo;
import redhospitalaria.modelo.Paciente;
import redhospitalaria.visual.BuscarGui;

/**
 *
 * @author david
 */
public class BuscarControl implements ActionListener {

    private Paciente user;
    private BuscarGui bg;
    private Modelo m;
    private PrincipalControl mainControl;
    private ModificarControl modificarControl;
    public BuscarControl(Paciente user, BuscarGui bg, Modelo m, PrincipalControl mainControl) {
        this.user = user;
        this.bg = bg;
        this.m = m;
        this.mainControl = mainControl;
        bg.getBoton().addActionListener(this);
        modificarControl = new ModificarControl(user, bg.getPg(), mainControl);
        bg.getPg().getModificar().setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (!bg.getBuscar().getText().isBlank() && (Boolean) mainControl.peticion("val:id:" + bg.getBuscar().getText())) {
                Object o = mainControl.peticion("get:" + bg.getBuscar().getText());
                if (o.getClass() == Paciente.class ||
                        o.getClass() == Doctor.class ||
                        o.getClass() == Administrador.class) {
                    Paciente u = (Paciente) o;
                    bg.setInfo(u);
                    if(user.getClass() == Doctor.class && u.getClass() == Paciente.class ||
                            user.getClass() == Administrador.class){
                        modificarControl.setUser(u);
                        bg.getPg().getModificar().setVisible(true);
                    }else{
                        bg.getPg().getModificar().setVisible(false);
                    }
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

    public void setUser(Paciente user) {
        this.user = user;
        modificarControl.setUser(user);
    }
    
}
