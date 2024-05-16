/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import raven.alerts.MessageAlerts;
import redhospitalaria.modelo.Modelo;
import redhospitalaria.modelo.Paciente;
import redhospitalaria.visual.BasicButton;
import redhospitalaria.visual.DrawerMenu;
import redhospitalaria.visual.Util;

/**
 *
 * @author david
 */
public class DrawerControl implements ActionListener{
    private Paciente user;
    private DrawerMenu dm;
    private Modelo m;
    private PrincipalControl mainControl;

    public DrawerControl(Paciente user, DrawerMenu dm, Modelo m, PrincipalControl mainControl) {
        this.user = user;
        this.dm = dm;
        this.m = m;
        this.mainControl = mainControl;
        addActions();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == dm.getcButtons()[0]){
            botonPerfilFunc();
        }
        if(e.getSource() == dm.getcButtons()[1]){
            botonBuscarFunc();
        }
        if(e.getSource() == dm.getcButtons()[2]){
            botonCitasFunc();
        }
        if(e.getSource() == dm.getcButtons()[3]){
            botonListarFunc();
        }
        if(e.getSource() == dm.getcButtons()[4]){
            botonCrearFunc();
        }
        if(e.getSource() == dm.getcButtons()[5]){
            botonEliminarFunc();
        }
        if(e.getSource() == dm.getcButtons()[6]){
            if(mainControl.actualizar()){
                MessageAlerts.getInstance().
                    showMessage("Actualizar",
                            "Su informacion a sido Actualizada correctamente",
                            MessageAlerts.MessageType.SUCCESS);
            }
        }
        if(e.getSource() == dm.getcButtons()[7]){
            if((Boolean) mainControl.peticion("exits:"+ user.getCedula())){
                mainControl.notificaciones();
            }else{
                MessageAlerts.getInstance().showMessage("Lo lamentamos",
                    "Al parecer ya no te encuentras en nuestra base de datos",
                    MessageAlerts.MessageType.ERROR);
                mainControl.logOut();
            }
            
        }
    }
    
    private void botonPerfilFunc(){
        mainControl.ocultComps();
        for (BasicButton b : dm.getcButtons()) {
            if(b.getBackground()== Util.color){
                b.setBackground(Util.color.brighter());
                b.setFocusable(true);
            }
        }
        dm.getcButtons()[0].setBackground(Util.color);
        dm.getcButtons()[0].setFocusable(false);
        mainControl.mostrarPerfil();
    }
    
    private void botonBuscarFunc(){
        mainControl.ocultComps();
        for (BasicButton b : dm.getcButtons()) {
            if(b.getBackground() == Util.color){
                b.setBackground(Util.color.brighter());
                b.setFocusable(true);
            }
        }
        dm.getcButtons()[1].setBackground(Util.color);
        dm.getcButtons()[1].setFocusable(false);
        mainControl.mostrarBuscar();
    }
    
    private void botonListarFunc(){
        mainControl.ocultComps();
        for (BasicButton b : dm.getcButtons()) {
            if(b.getBackground() == Util.color){
                b.setBackground(Util.color.brighter());
                b.setFocusable(true);
            }
        }
        dm.getcButtons()[3].setBackground(Util.color);
        dm.getcButtons()[3].setFocusable(false);
        mainControl.mostrarListados();
    }
    
    private void botonCrearFunc(){
        mainControl.ocultComps();
        for (BasicButton b : dm.getcButtons()) {
            if(b.getBackground() == Util.color){
                b.setBackground(Util.color.brighter());
                b.setFocusable(true);
            }
        }
        dm.getcButtons()[4].setBackground(Util.color);
        dm.getcButtons()[4].setFocusable(false);
        mainControl.mostrarCrear();
    }
    
    private void botonCitasFunc(){
        mainControl.ocultComps();
        for (BasicButton b : dm.getcButtons()) {
            if(b.getBackground() == Util.color){
                b.setBackground(Util.color.brighter());
                b.setFocusable(true);
            }
        }
        dm.getcButtons()[2].setBackground(Util.color);
        dm.getcButtons()[2].setFocusable(false);
        mainControl.mostrarCitas();
    }
    
    private void botonEliminarFunc(){
        mainControl.ocultComps();
        for (BasicButton b : dm.getcButtons()) {
            if(b.getBackground() == Util.color){
                b.setBackground(Util.color.brighter());
                b.setFocusable(true);
            }
        }
        dm.getcButtons()[5].setBackground(Util.color);
        dm.getcButtons()[5].setFocusable(false);
        mainControl.mostrarEliminar();
    }
    
    
    private void addActions(){
        for (BasicButton b : dm.getcButtons()) {
            b.addActionListener(this);
        }
    }

    public Paciente getUser() {
        return user;
    }

    public void setUser(Paciente user) {
        this.user = user;
    }
    
    
}
