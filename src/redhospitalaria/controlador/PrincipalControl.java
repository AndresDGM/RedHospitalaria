/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ConnectException;
import javax.swing.AbstractAction;
import static javax.swing.Action.MNEMONIC_KEY;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import redhospitalaria.modelo.Modelo;
import redhospitalaria.modelo.UserSocket;
import redhospitalaria.modelo.Usuario;
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
    private Usuario user;

    public PrincipalControl() throws IOException {
        init();
    }
    
    private void init() throws IOException {
        final CloseAction closeAction = new CloseAction(g);

        g.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeAction.confirmClosing();
            }
        });
        loginControl = new LoginControl(g.getLogin(),m, this);
        drawerControl = new DrawerControl(user, g.getDm(), m, this);
        buscarControl = new BuscarControl(user, g.getBg(), m, this);
        eliminarControl = new EliminarControl(user, g.getRg(), m, this);
        crearControl = new CrearControl(g.getCu(), this, user);
        listarControl = new ListarControl(g.getLg(), this, user);
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
        if(user.getTipo().equals("Doctor")){
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
        switch (user.getTipo()) {
            case "Paciente" -> {
                g.getDm().getcButtons()[4].setVisible(false);
                g.getDm().getcButtons()[5].setVisible(false);
            }
            case "Doctor" -> g.getDm().getcButtons()[5].setVisible(false);
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

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
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
    
    public void setPerfil(Usuario u){
        g.getPg().setInformacion(u);
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
        if(user.getTipo().equals("Doctor")){
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
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
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
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
        return o;
    }
    
    private class CloseAction extends AbstractAction {

        private JFrame mainFrame;

        public CloseAction(JFrame mainFrame) {
            super("Exit");
            putValue(MNEMONIC_KEY, KeyEvent.VK_X);
            this.mainFrame = mainFrame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            confirmClosing();
        }

        public void confirmClosing() {
            int confirmed = JOptionPane.showConfirmDialog(mainFrame,
                    "Are you sure you want to quit?", "Confirm quit",
                    JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                // clean up code
                System.exit(0);
            }
        }
    }
}

