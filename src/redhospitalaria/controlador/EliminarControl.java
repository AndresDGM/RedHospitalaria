package redhospitalaria.controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import raven.alerts.MessageAlerts;
import redhospitalaria.modelo.Administrador;
import redhospitalaria.modelo.Doctor;
import redhospitalaria.modelo.Modelo;
import redhospitalaria.modelo.Paciente;
import redhospitalaria.visual.BasicButton;
import redhospitalaria.visual.BuscarGui;

/**
 *
 * @author david
 */
public class EliminarControl implements ActionListener{
    
    private Paciente user;
    private BuscarGui bg;
    private Modelo m;
    private PrincipalControl mainControl;
    private BasicButton bEliminar;

    public EliminarControl(Paciente user, BuscarGui bg, Modelo m, PrincipalControl mainControl) {
        this.user = user;
        this.bg = bg;
        this.m = m;
        this.mainControl = mainControl;
        bEliminar = new BasicButton();
        bEliminar.setSize(30, 30);
        bEliminar.setLocation(bg.getBoton().getX() + 35, 50);
        bEliminar.setColor(new Color(235,15,60));
        bEliminar.setIcon("src/images/ClarityRemoveSolid.png");
        bg.add(bEliminar, 3);
        bg.getPg().getModificar().setVisible(false);
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
        bEliminar.addActionListener(this);
    }
    
    private void botonBuscar(){
        try {
            if (!bg.getBuscar().getText().isBlank() && (Boolean) mainControl.peticion("val:id:" + bg.getBuscar().getText())) {
                Object o = mainControl.peticion("get:" + bg.getBuscar().getText());
                if (o.getClass() == Paciente.class ||
                        o.getClass() == Doctor.class ||
                        o.getClass() == Administrador.class) {
                    Paciente u = (Paciente) o;
                    bg.setInfo(u);
                    bEliminar.setVisible(true);
                    if(u.equals(user)){
                        MessageAlerts
                                .getInstance()
                                .showMessage("El perfil",
                                        "Tu propia imformacion este en el perfil en todo momento",
                                        MessageAlerts.MessageType.DEFAULT);
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
    
    private void botonEliminar(){
        try {
            if (!bg.getBuscar().getText().isBlank() && (Boolean) mainControl.peticion("val:id:" + bg.getBuscar().getText())) {
                if(Long.parseLong(bg.getBuscar().getText()) != user.getCedula()){
                    Object o = mainControl.peticion("delete:" + bg.getBuscar().getText());
                    if (o.getClass() == Paciente.class ||
                            o.getClass() == Doctor.class ||
                            o.getClass() == Administrador.class) {
                        MessageAlerts
                                .getInstance()
                                .showMessage("Se ha borrado correctamente ha:",
                                        "Nombre: " + ((Paciente) o).getNombre() + "\n" +
                                        "Cedula: " + ((Paciente) o).getCedula(),
                                        MessageAlerts.MessageType.SUCCESS);
                    } else {
                        MessageAlerts
                                .getInstance()
                                .showMessage("Fallida",
                                        "No se encontro o no existe el usuario buscado",
                                        MessageAlerts.MessageType.WARNING);
                    }
                }else{
                    MessageAlerts
                                .getInstance()
                                .showMessage("Cuidado",
                                        "No te puedes eliminar a ti mismo",
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
    }
}
