/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import raven.alerts.MessageAlerts;
import redhospitalaria.modelo.Doctor;
import redhospitalaria.modelo.Paciente;
import redhospitalaria.visual.PerfilGui;

/**
 *
 * @author david
 */
public class ModificarControl implements ActionListener{
    private Paciente user;
    private PerfilGui pg;
    private PrincipalControl mainControl;
    
    public ModificarControl(Paciente user, PerfilGui pg, PrincipalControl mainControl) {
        this.user = user;
        this.pg = pg;
        this.mainControl = mainControl;
        addListeners();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(pg.getFechaBoton())){
            pg.getFechaNacimiento().showPopup();
        }else if(e.getSource().equals(pg.getModificar())){
            if(pg.getModificar().getTextF().getText().equals("Modificar")){
                modificar();
            }else{
                if(validar()){
                    confirmar();
                }
            }
        }else if(e.getSource().equals(pg.getCancelar())){
            cancelar();
        }
    }
    
    private void addListeners(){
        pg.getModificar().addActionListener(this);
        pg.getCancelar().addActionListener(this);
        pg.getFechaBoton().addActionListener(this);
    }
    
    private void modificar(){
        pg.getModificar().setText("Confirmar");
        pg.getCancelar().setVisible(true);
        pg.getFields()[0].setEditable(true);
        pg.getFechaBoton().setVisible(true);
        pg.getFields()[5].setEnabled(true);
        pg.getFields()[2].setVisible(false);
        pg.getGenero().setVisible(true);
        pg.getFields()[3].setVisible(false);
        pg.getEps().setVisible(true);
        pg.getFields()[4].setEditable(true);
        pg.getFields()[8].setVisible(false);
        pg.getDepar().setVisible(true);
        pg.getFields()[9].setVisible(false);
        pg.getMuni().setVisible(true);
        pg.getFields()[10].setEditable(true);
    }
    private void confirmar(){
        Paciente antUser = new Paciente(user);
        aplicar();
        Boolean b = (Boolean) mainControl.peticion("modify", user);
        if(b){
            MessageAlerts.getInstance().showMessage("Modificacion Exitosa",
                    "Los datos se han cambiado correctamente", MessageAlerts.MessageType.SUCCESS);
        }else{
            user = antUser;
            MessageAlerts.getInstance().showMessage("Modificacion Fallida",
                    "Los datos no se pudieron cambiar\n Intente de nuevo", MessageAlerts.MessageType.ERROR);
        }
        pg.setInformacion(user);
        if(user.getClass() == Doctor.class){
            pg.getTitle().setText("Datos - " + ((Doctor)user).getEspecializacion());
        }else{
            pg.getTitle().setText("Datos - " + user.getClass().getSimpleName());
        }
        pg.getCancelar().setVisible(false);
        cancelar();
    }
  
    private void cancelar(){
        pg.setInformacion(user);
        pg.getModificar().setText("Modificar");
        pg.getFields()[0].setEditable(false);
        pg.getFechaBoton().setVisible(false);
        pg.getFields()[5].setEnabled(false);
        pg.getFields()[2].setVisible(true);
        pg.getGenero().setVisible(false);
        pg.getFields()[3].setVisible(true);
        pg.getEps().setVisible(false);
        pg.getFields()[4].setEditable(false);
        pg.getFields()[8].setVisible(true);
        pg.getDepar().setVisible(false);
        pg.getFields()[9].setVisible(true);
        pg.getMuni().setVisible(false);
        pg.getFields()[10].setEditable(false);
    }
    
    private void aplicar(){
        user.setNombre(pg.getFields()[0].getText());
        user.setGenero((String) pg.getGenero().getSelectedItem());
        user.setEps((String) pg.getEps().getSelectedItem());
        user.setCorreo(pg.getFields()[4].getText());
        user.setDepartamento((String) pg.getDepar().getSelectedItem());
        user.setMunicipio((String) pg.getMuni().getSelectedItem());
        user.setDireccion(pg.getFields()[10].getText());
        user.setFechaNacimiento(pg.getFields()[5].getText());
        user.setEdad(user.calcularEdad());
    }
    private boolean validar(){
        String warning = "";
        boolean val = true;
        if(pg.getFields()[0].getText().isBlank()){
            warning += "Por favor ingrese su nombre y apellido\n";
            val = false;
        }
        if(pg.getGenero().getSelectedItem() == null){
            warning += "Por favor elija su genero\n";
            val = false;
        }
        if(pg.getEps().getSelectedItem() == null){
            warning += "Por favor elija su eps\n";
            val = false;
        }
        if(!(Boolean) mainControl.peticion("val:email:" + pg.getFields()[4].getText())){
            warning += "Por favor ingrese un correo valido\n";
            val = false;
        }
        if(pg.getDepar().getSelectedItem() == null || pg.getDepar().getSelectedIndex() == 0){
            warning += "Por favor seleccione su departamento\n";
            val = false;
        }
        if(pg.getMuni().getSelectedItem() == null || pg.getMuni().getSelectedIndex() == 0){
            warning += "Por favor seleccione su municipio\n";
            val = false;
        }
        if(!val){
            MessageAlerts.getInstance().showMessage("Campos Erroneos",
                    warning, MessageAlerts.MessageType.ERROR);
        }
        return val;
    }

    public void setUser(Paciente user) {
        this.user = user;
    }
}
