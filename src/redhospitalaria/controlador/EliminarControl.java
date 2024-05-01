/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import raven.alerts.MessageAlerts;
import redhospitalaria.modelo.Modelo;
import redhospitalaria.modelo.Usuario;
import redhospitalaria.visual.BasicButton;
import redhospitalaria.visual.BuscarGui;

/**
 *
 * @author david
 */
public class EliminarControl implements ActionListener{
    
    private Usuario user;
    private BuscarGui bg;
    private Modelo m;
    private PrincipalControl mainControl;
    private BasicButton bEliminar;

    public EliminarControl(Usuario user, BuscarGui bg, Modelo m, PrincipalControl mainControl) {
        this.user = user;
        this.bg = bg;
        this.m = m;
        this.mainControl = mainControl;
        addListeners();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(bg.getBoton())){
            botonBuscar();
        }else if(e.getSource().equals(bEliminar)){
            botonEliminar();
        }
    }
    
    private void addListeners(){
        bg.getBoton().addActionListener(this);
        for (Component c : bg.getComponents()) {
            if(c instanceof BasicButton){
                if(((BasicButton) c).getTextF().getText().equals("Eliminar")){
                    bEliminar = (BasicButton) c;
                    bEliminar.addActionListener(this);
                }
            }
        }
    }
    
    private void botonBuscar(){
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
    
    private void botonEliminar(){
        try {
            if ((Boolean) mainControl.peticion("val:id:" + bg.getBuscar().getText())) {
                Object o = mainControl.peticion("delete:" + bg.getBuscar().getText());
                if (o.getClass() == Usuario.class) {
                    MessageAlerts
                            .getInstance()
                            .showMessage("Se ha borrado correctamente ha:",
                                    "Nombre: " + ((Usuario) o).getNombre() + "\n" +
                                    "Cedula: " + ((Usuario) o).getCedula(),
                                    MessageAlerts.MessageType.SUCCESS);
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
