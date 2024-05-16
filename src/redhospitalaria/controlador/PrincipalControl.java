/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ConnectException;
import raven.alerts.MessageAlerts;
import redhospitalaria.modelo.Doctor;
import redhospitalaria.modelo.Modelo;
import redhospitalaria.modelo.UserSocket;
import redhospitalaria.modelo.Paciente;
import redhospitalaria.visual.*;

/**
 *
 * @author david
 */
public class PrincipalControl implements ActionListener{
    
    private Gui g = new Gui();
    private Modelo m;
    private LoginControl loginControl;
    private DrawerControl drawerControl;
    private BuscarControl buscarControl;
    private EliminarControl eliminarControl;
    private CrearControl crearControl;
    private ListarControl listarControl;
    private CitasControl citasControl;
    private ModificarControl modificarControl;
    private Paciente user;

    public PrincipalControl() throws IOException {
        init();
    }
    
    private void init() throws IOException {
        loginControl = new LoginControl(g.getLogin(),m, this);
        drawerControl = new DrawerControl(user, g.getDm(), m, this);
        buscarControl = new BuscarControl(user, g.getBg(), m, this);
        eliminarControl = new EliminarControl(user, g.getRg(), m, this);
        crearControl = new CrearControl(g.getCu(), this, user);
        listarControl = new ListarControl(g.getLg(), this, user);
        citasControl = new CitasControl(user, g.getCg(), m, this);
        modificarControl = new ModificarControl(user, g.getPg(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
    public void mostrarGui(){
        g.setVisible(true);
    }
    
    public void mostrarLogin(){
        g.getLogin().setVisible(true);
    }
    
    public void ocultarLogin(){
        g.getLogin().setVisible(false);
    }
    
    public void mostrarCitas(){
        if(user.getClass() == Doctor.class){
            g.getCg().getRemoverCita().setText("Atender Paciente");
            g.getCg().getRemoverCita().setLocation(g.getCg().getSolicitarCita().getLocation());
            g.getCg().getSolicitarCita().setVisible(false);
        }
        g.getDm().getcButtons()[2].setBackground(Util.color);
        g.getDm().getcButtons()[2].setFocusable(false);
        g.getCg().setVisible(true);
    }
    
    public void ocultarCitas(){
        g.getCg().setVisible(false);
    }
    
    public void mostrarListados(){
        g.getLg().setVisible(true);
    }
    
    public void ocultarListados(){
        g.getLg().setVisible(false);
    }
    
    public void mostrarMenu(){
        if (user.getClass() == Paciente.class) {
            g.getDm().getcButtons()[4].setVisible(false);
            g.getDm().getcButtons()[5].setVisible(false);
        }else if(user.getClass() == Doctor.class){
            g.getDm().getcButtons()[5].setVisible(false);
        }
        g.getDm().setVisible(true);
    }
    
    public void ocultarMenu(){
        g.getDm().setVisible(false);
    }
    
    public void mostrarPerfil(){
        g.getPg().setVisible(true);
    }
    
    public void ocultarPerfil(){
        g.getPg().setVisible(false);
    }

    public Paciente getUser() {
        return user;
    }

    public void setUser(Paciente user) {
        this.user = user;
        modificarControl.setUser(this.user);
        citasControl.setUser(this.user);
        buscarControl.setUser(this.user);
        eliminarControl.setUser(this.user);
        drawerControl.setUser(this.user);
    }
    
    public void ocultComps(){
        ocultarCitas();
        ocultarListados();
        ocultarLogin();
        ocultarBuscar();
        ocultarPerfil();
        ocultarEliminar();
        ocultarCrear();
    }
    
    public void setPerfil(Paciente u){
        g.getPg().setInformacion(u);
        if(u.getClass() == Doctor.class){
            g.getPg().getTitle().setText("Datos - " + ((Doctor)u).getEspecializacion());
        }else{
            g.getPg().getTitle().setText("Datos - " + u.getClass().getSimpleName());
        }
    }
    
    public void mostrarBuscar(){
        g.getBg().setVisible(true);
    }
    
    public void ocultarBuscar(){
        g.getBg().setVisible(false);
    }
    
    public void mostrarEliminar(){
        g.getRg().setVisible(true);
    }
    
    public void ocultarEliminar(){
        g.getRg().setVisible(false);
    }
    
    public void mostrarCrear(){
        if(user.getClass() == Doctor.class){
            g.getCu().getCombo().setSelectedItem("Paciente");
            g.getCu().getCombo().setEnabled(false);
        }
        g.getCu().setVisible(true);
    }
    
    public void ocultarCrear(){
        g.getCu().setVisible(false);
    }
    
    public Object peticion(String Comand) {
        Object o = null;
        try {
            UserSocket conection = new UserSocket(UserSocket.host, UserSocket.port);
            conection.getOut().writeObject(Comand);
            o = conection.getIn().readObject();
            conection.close();
        } catch (ConnectException ex) {
        } catch (IOException | ClassNotFoundException ex) {
        }
        return o;
    }

    public Object peticion(String Comand, Object object) {
        Object o = null;
        try {
            UserSocket conection = new UserSocket(UserSocket.host, UserSocket.port);
            conection.getOut().writeObject(Comand);
            conection.getOut().writeObject(object);
            o = conection.getIn().readObject();
            conection.close();
        } catch (ConnectException ex) {
        } catch (IOException | ClassNotFoundException ex) {
        }
        return o;
    }
    
    public boolean actualizar(){
        Object o = peticion("get:" + user.getCedula());
        if(o != null && o instanceof Boolean){
            MessageAlerts.getInstance().
                    showMessage("Error",
                            "No se pudo actualizar la informacion del usuario",
                            MessageAlerts.MessageType.ERROR);
            if(!(Boolean)peticion("exits:" + user.getCedula())){
                MessageAlerts.getInstance().showMessage("Lo lamentamos",
                    "Al parecer ya no te encuentras en nuestra base de datos",
                    MessageAlerts.MessageType.ERROR);
                logOut();
            }
            return false;
        }else{
            setUser((Paciente) o);
            return true;
        }
    }
    
    public void setDefault(){
        g.getBg().setDefault();
        g.getCg().setDefault();
        g.getDm().setVisible(false);
        g.getRg().setDefault();
        g.getLg().setDefault();
        g.getPg().setDefault();
        g.getCu().setDefault();
        g.getLogin().setDefault();
    }

    public void logOut() {
        setDefault();
        ocultComps();
        mostrarLogin();
    }
    
    public void notificaciones(){
        String notify = "";
        if(!(notify = (String) peticion("notify:" + user.getCedula())).equals("")){
            MessageAlerts.getInstance()
                .showMessage("Notificacion",
                        user.getNotify().pop() + "\nVer siguiente notificacion?",
                        MessageAlerts.MessageType.DEFAULT, MessageAlerts.YES_NO_OPTION, (pc, i) -> {
                                if(i == MessageAlerts.YES_OPTION){
                                    notificaciones();
                                }
                            });
        }else{
            MessageAlerts.getInstance()
                .showMessage("Informacion",
                        "No hay notificaciones",
                        MessageAlerts.MessageType.DEFAULT);
        }
    }
}

