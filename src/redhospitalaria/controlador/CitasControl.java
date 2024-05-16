/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import raven.alerts.MessageAlerts;
import redhospitalaria.modelo.Cita;
import redhospitalaria.modelo.Doctor;
import redhospitalaria.modelo.Modelo;
import redhospitalaria.modelo.Paciente;
import redhospitalaria.visual.CitasGui;
import redhospitalaria.visual.DarkTable;
import redhospitalaria.visual.Util;

/**
 *
 * @author david
 */
public class CitasControl implements ActionListener{

    private Paciente user;
    private CitasGui cg;
    private Modelo m;
    private PrincipalControl mainControl;

    public CitasControl(Paciente user, CitasGui cg, Modelo m, PrincipalControl mainControl) {
        this.user = user;
        this.cg = cg;
        this.m = m;
        this.mainControl = mainControl;
        addListeners();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(cg.getSolicitarCita())){
            botonSolicitar();
        }else if(e.getSource().equals(cg.getRemoverCita())){
            if((Boolean) mainControl.peticion("exits:"+ user.getCedula())){
                botonRemover();
            }else{
                MessageAlerts.getInstance().showMessage("Lo lamentamos",
                    "Al parecer ya no te encuentras en nuestra base de datos",
                    MessageAlerts.MessageType.ERROR);
                mainControl.logOut();
            } 
        }else if(e.getSource().equals(cg.getCitaCrear().getAtras())){
            botonAtras();
        }else if(e.getSource().equals(cg.getCitaCrear().getCrear())){
            if((Boolean) mainControl.peticion("exits:"+ user.getCedula())){
                botonCrear();
            }else{
                MessageAlerts.getInstance().showMessage("Lo lamentamos",
                    "Al parecer ya no te encuentras en nuestra base de datos",
                    MessageAlerts.MessageType.ERROR);
                mainControl.logOut();
            }
        }
    }
    
    private void addListeners(){
        cg.getSolicitarCita().addActionListener(this);
        cg.getRemoverCita().addActionListener(this);
        cg.getCitaCrear().getCrear().addActionListener(this);
        cg.getCitaCrear().getAtras().addActionListener(this);
    }
    
    private void botonSolicitar(){
        cg.ocultList();
        cg.getCitaCrear().setVisible(true);
    }

    private void botonRemover() {
        if(!user.getCitas().isEmpty()){
            String mensaje = (String) mainControl.peticion("appointment:remove:" +
                    user.getCitas().peek().getDoctor().getCedula() + ":" + user.getCedula());
            if(!mensaje.equals("La cita se atendio correctamente")){
                MessageAlerts.getInstance().
                        showMessage("Importante", mensaje, MessageAlerts.MessageType.DEFAULT);
            }else{
                MessageAlerts.getInstance().
                        showMessage("Todo listo", mensaje, MessageAlerts.MessageType.SUCCESS);
            }
        }else{
            MessageAlerts.getInstance().
                        showMessage("Nada por aqui", "No hay ninguna cita", MessageAlerts.MessageType.DEFAULT);
        }
        mainControl.actualizar();
    }
    
    private void botonAtras() {
        cg.showList();
        cg.getCitaCrear().setVisible(false);
    }
    
    private void botonCrear(){
        if(val()){
            Boolean b = (Boolean) mainControl.peticion("appointment:new:" + user.getCedula() +
                    ":" + Arrays.asList(Util.urgencias).
                    indexOf((String) cg.getCitaCrear().getUrgencia().getSelectedItem()) +
                    ":" + (String) cg.getCitaCrear().getArea().getSelectedItem() + 
                    ":" + cg.getCitaCrear().getRazon().getText());
            if(b){
                MessageAlerts.getInstance().showMessage("Se a creado la cita correctamente",
                    "Puede ver ahora el resumen de sus citas",
                    MessageAlerts.MessageType.SUCCESS);
                cg.getCitaCrear().setDefault();
                cg.getCitaCrear().setVisible(false);
                cg.showList();
                mainControl.actualizar();
            }else{
                MessageAlerts.getInstance().showMessage("No se pudo crear la cita",
                    "es probable que no se disponga del especialista necesario para su cita",
                    MessageAlerts.MessageType.ERROR);
            }
        }
    }

    private boolean val() {
        boolean val = true;
        if(cg.getCitaCrear().getArea().getSelectedItem() == null){
            val = false;
            MessageAlerts.getInstance().showMessage("Campos erroneos",
                    "Por favor ingrese el area al que va dirigido",
                    MessageAlerts.MessageType.WARNING);
        }
        
        if(cg.getCitaCrear().getUrgencia().getSelectedItem() == null){
            val = false;
            MessageAlerts.getInstance().showMessage("Campos erroneos",
                    "Por favor ingrese que tan urgente es la cita",
                    MessageAlerts.MessageType.WARNING);
        }
        
        if(cg.getCitaCrear().getRazon().getText().isEmpty()){
            val = false;
            MessageAlerts.getInstance().showMessage("Campos erroneos",
                    "Por favor ingrese la razon por que necesita la cita",
                    MessageAlerts.MessageType.WARNING);
        }
        return val;
    }

    void setUser(Paciente user) {
        this.user = user;
        Queue<Cita> temp = new LinkedList<>();
        String[][] data = new String[user.getCitas().size()][user.getClass() == Doctor.class ? 7 : 6];
        for(int i = 0; !user.getCitas().isEmpty(); i++){
            data[i] = user.getCitas().peek().getData(user);
            temp.offer(this.user.getCitas().poll());
        }
        this.user.setCitas(temp);
        DefaultTableModel dtm = new DefaultTableModel(data, new Cita().getDataName(user));
        cg.getTable().setModel(dtm);
        resizeTable(cg.getTable());
    }
    
    private void resizeTable(DarkTable table) {
        for (int column = 0; column < table.getColumnCount(); column++) {
            TableColumn tableColumn = table.getColumnModel().getColumn(column);
            int currentWidth = cg.getJsp().getWidth() / table.getColumnCount();
            tableColumn.setPreferredWidth(currentWidth);
            int maxWidth = tableColumn.getMinWidth();

            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
                Component c = table.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
                maxWidth = Math.max(maxWidth, width);
            }
            if(maxWidth > currentWidth){
                tableColumn.setPreferredWidth(maxWidth);
            }
        }
    }
}
