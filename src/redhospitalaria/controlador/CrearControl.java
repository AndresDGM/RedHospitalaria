/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;
import raven.alerts.MessageAlerts;
import raven.popup.component.PopupCallbackAction;
import raven.popup.component.PopupController;
import redhospitalaria.modelo.Administrador;
import redhospitalaria.modelo.Doctor;
import redhospitalaria.modelo.Paciente;
import redhospitalaria.visual.CrearUsusario;
import redhospitalaria.visual.DateChooserDJRaven.DateChooser;

public class CrearControl implements ActionListener {

    private CrearUsusario v;
    private PrincipalControl mainControl;
    private Paciente user;

    public CrearControl(CrearUsusario v, PrincipalControl mainControl, Paciente user) {
        this.v = v;
        this.mainControl = mainControl;
        this.user = user;
        setActions();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == v.getCrearGui().getBotonSiguiente() && v.getCrearGui().getBotonSiguiente().isFocusable()) {
            try {
                if (botonSiguienteVal()) {
                    v.getCrearGui().changeVisible(v.getCrearGui().getMode() + 1);
                    switch (v.getCrearGui().getMode()) {
                        case 2 -> v.getCombo().setEnabled(false);
                        case 3 -> {
                            if(((String) v.getCombo().getSelectedItem()).equals("Doctor")){
                                v.getCrearGui().getEspecializacion().setVisible(true);
                                v.getCrearGui().getEspecializacionLabel().setVisible(true);
                            }
                        }
                        case 4 -> {
                            v.getCrearGui().getEspecializacion().setVisible(false);
                            v.getCrearGui().getEspecializacionLabel().setVisible(false);
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
            }
        }
        if (e.getSource() == v.getCrearGui().getBotonAtras() && v.getCrearGui().getBotonAtras().isFocusable()) {
            v.getCrearGui().changeVisible(v.getCrearGui().getMode() - 1);
            switch (v.getCrearGui().getMode()) {
                case 1 -> v.getCombo().setEnabled(true);
                case 2 -> {
                    v.getCrearGui().getEspecializacion().setVisible(false);
                    v.getCrearGui().getEspecializacionLabel().setVisible(false);
                }
                case 3 -> {
                    if(((String) v.getCombo().getSelectedItem()).equals("Doctor")){
                        v.getCrearGui().getEspecializacion().setVisible(true);
                        v.getCrearGui().getEspecializacionLabel().setVisible(true);
                    }
                }
            }
        }
        if (e.getSource() == v.getCrearGui().getFechaBoton() && v.getCrearGui().getFechaBoton().isFocusable()) {
            v.getCrearGui().getFecha().showPopup();
            if (!v.getCrearGui().getFechatxt().isFocusable()) {
                v.getCrearGui().getFechatxt().setFocusable(true);
            }
            v.getCrearGui().getFechatxt().requestFocus();
        }
        if (e.getSource() == v.getCrearGui().getBotonSingUp() && v.getCrearGui().getBotonSingUp().isFocusable()) {
            try {
                singUpboton();
            } catch (IOException | ClassNotFoundException ex) {
            }
        }
    }

    private void setActions() {
        v.getCrearGui().getBotonAtras().addActionListener(this);
        v.getCrearGui().getBotonSiguiente().addActionListener(this);
        v.getCrearGui().getBotonSingUp().addActionListener(this);
        v.getCrearGui().getFechaBoton().addActionListener(this);
    }

    private void singUpboton() throws IOException, ClassNotFoundException {
        if (botonCrearVal()) {
            Paciente u = new Paciente(
                            Integer.parseInt(v.getCrearGui().getId().getText()),
                            v.getCrearGui().getNombre().getText(),
                            (String) v.getCrearGui().getGenero().getSelectedItem(),
                            (String) v.getCrearGui().getEps().getSelectedItem(),
                            String.valueOf(v.getCrearGui().getPassword().getPassword()),
                            v.getCrearGui().getCorreo().getText(),
                            v.getCrearGui().getFechatxt().getText(),
                            DateChooser.getToDay(),
                            (String) v.getCrearGui().getDepar().getSelectedItem(),
                            (String) v.getCrearGui().getMuni().getSelectedItem(),
                            v.getCrearGui().getDireccion().getText()
                    );
            switch ((String) v.getCombo().getSelectedItem()) {
                case "Doctor" ->
                    u = new Doctor(u, (String) v.getCrearGui().getEspecializacion().getSelectedItem());
                case "Administrador" ->
                    u = new Administrador(u);
            }
            try {
                Boolean b = (Boolean) mainControl.peticion("create", u);
                if (b) {
                    MessageAlerts.getInstance()
                            .showMessage("Listo",
                                    "Se ha creado el "
                                    + (String) v.getCombo().getSelectedItem()
                                    + " Correctamente",
                                    MessageAlerts.MessageType.SUCCESS, MessageAlerts.OK_OPTION, new PopupCallbackAction() {
                                @Override
                                public void action(PopupController pc, int i) {
                                    v.getCrearGui().setDefault();
                                    v.getCrearGui().changeVisible(1);
                                    v.getCombo().setSelectedItem("Seleccionar");
                                }
                            });
                } else {
                    MessageAlerts.getInstance()
                            .showMessage("Error",
                                    "No se pudo crear el usuario, por favor intente nuevamente o mas tarde",
                                    MessageAlerts.MessageType.ERROR);
                }
            } catch (NullPointerException e) {
                MessageAlerts
                        .getInstance()
                        .showMessage("Error",
                                "No se pudo estableser coneccion con el servidor",
                                MessageAlerts.MessageType.ERROR);
            }
        }
    }

    private boolean botonSiguienteVal() throws IOException, ClassNotFoundException {
        boolean verificacion = true;

        switch (v.getCrearGui().getMode()) {
            case 1 -> {
                if (v.getCrearGui().getNombre().getText().isBlank()) {
                    verificacion = false;
                    showWarning(v.getCrearGui().getWarnings()[0], "Por favor ingrese su nombre completo");
                }   if (v.getCrearGui().getFechatxt().getText().isBlank()) {
                    verificacion = false;
                    showWarning(v.getCrearGui().getWarnings()[1], "Por favor ingrese su fecha de nacimiento");
                }   if (v.getCrearGui().getId().getText().isBlank()) {
                    verificacion = false;
                    showWarning(v.getCrearGui().getWarnings()[2], "Por favor ingrese su cedula");
                } else {
                    try {
                        if (!(Boolean) mainControl.peticion("val:id:" + v.getCrearGui().getId().getText())) {
                            verificacion = false;
                            showWarning(v.getCrearGui().getWarnings()[2], "Por favor solo ingrese numeros");
                        } else {
                            try {
                                if ((Boolean) mainControl.peticion("exits:" + v.getCrearGui().getId().getText())) {
                                    verificacion = false;
                                    showWarning(v.getCrearGui().getWarnings()[2], "Ya existe una cuenta con esa cedula");
                                }
                            } catch (NullPointerException e) {
                                verificacion = false;
                                MessageAlerts
                                        .getInstance()
                                        .showMessage("Error",
                                                "No se pudo estableser coneccion con el servidor",
                                                MessageAlerts.MessageType.ERROR);
                            }
                        }
                    } catch (NullPointerException e) {
                        verificacion = false;
                        MessageAlerts
                                .getInstance()
                                .showMessage("Error",
                                        "No se pudo estableser coneccion con el servidor",
                                        MessageAlerts.MessageType.ERROR);
                    }
                }
            }
            case 2 -> {
                if(v.getCrearGui().getDepar().getSelectedItem() == null || v.getCrearGui().getDepar().getSelectedItem().equals("Seleccionar")){
                    verificacion = false;
                    showWarning(v.getCrearGui().getWarnings()[0], "Por favor ingrese su departamento");
                }   if (v.getCrearGui().getMuni().getItemCount() == 0) {
                    verificacion = false;
                    showWarning(v.getCrearGui().getWarnings()[1], "Por favor ingrese su municipio");
                } else if (v.getCrearGui().getMuni().getSelectedItem().equals("Seleccionar")) {
                    verificacion = false;
                    showWarning(v.getCrearGui().getWarnings()[1], "Por favor ingrese su municipio");
                }   if (v.getCrearGui().getDireccion().getText().isBlank()) {
                    verificacion = false;
                    showWarning(v.getCrearGui().getWarnings()[2], "Por favor ingrese su direccion");
                }
            }
            case 3 -> {
                if(v.getCrearGui().getGenero().getSelectedItem() == null)
                    showWarning(v.getCrearGui().getWarnings()[0], "Por favor ingrese su genero");
                if(v.getCrearGui().getEps().getSelectedItem() == null)
                    showWarning(v.getCrearGui().getWarnings()[1], "Por favor ingrese su eps");
                if(v.getCrearGui().getEspecializacion().getSelectedItem() == null)
                    showWarning(v.getCrearGui().getWarnings()[2], "Por favor ingrese la especializacion");
                verificacion = v.getCrearGui().getGenero().getSelectedItem() != null &&
                        v.getCrearGui().getEps().getSelectedItem() != null &&
                        v.getCrearGui().getEspecializacion().getSelectedItem() != null;
            }
        }
        return verificacion;
    }

    private boolean botonCrearVal() throws IOException, ClassNotFoundException {
        boolean verificacion = true;

        if (v.getCrearGui().getCorreo().getText().isBlank()) {
            verificacion = false;
            showWarning(v.getCrearGui().getWarnings()[0], "Por favor ingrese su correo electronico");
        } else {
            try {
                if (!(Boolean) mainControl.peticion("val:email:" + v.getCrearGui().getCorreo().getText())) {
                    verificacion = false;
                    showWarning(v.getCrearGui().getWarnings()[0], "Por favor ingrese un correo como el ejemplo");
                }
            } catch (NullPointerException e) {
                verificacion = false;
                MessageAlerts
                        .getInstance()
                        .showMessage("Error",
                                "No se pudo estableser coneccion con el servidor",
                                MessageAlerts.MessageType.ERROR);
            }
        }

        if (String.valueOf(v.getCrearGui().getPassword().getPassword()).isBlank()) {
            verificacion = false;
            showWarning(v.getCrearGui().getWarnings()[1], "Por favor cree una contraseña");
        } else {
            try {
                if (!(Boolean) mainControl.peticion("val:password:" + String.valueOf(v.getCrearGui().getPassword().getPassword()))) {
                    verificacion = false;
                    showWarning(v.getCrearGui().getWarnings()[1], "longitud minima de 8 caracteres");
                }
            } catch (NullPointerException e) {
                verificacion = false;
                MessageAlerts
                        .getInstance()
                        .showMessage("Error",
                                "No se pudo estableser coneccion con el servidor",
                                MessageAlerts.MessageType.ERROR);
            }
        }

        if (String.valueOf(v.getCrearGui().getPassword2().getPassword()).isBlank()) {
            verificacion = false;
            showWarning(v.getCrearGui().getWarnings()[2], "debe comfirmar la contraseña");
        } else if (!String.valueOf(v.getCrearGui().getPassword2().getPassword())
                .equals(String.valueOf(v.getCrearGui().getPassword().getPassword()))) {
            verificacion = false;
            showWarning(v.getCrearGui().getWarnings()[2], "contraseñas no coinciden");
        } else {
            try {
                if (!(Boolean) mainControl.peticion("val:password:" + String.valueOf(v.getCrearGui().getPassword2().getPassword()))) {
                    verificacion = false;
                    showWarning(v.getCrearGui().getWarnings()[2], "longitud minima de 8 caracteres");
                }
            } catch (NullPointerException e) {
                verificacion = false;
                MessageAlerts
                        .getInstance()
                        .showMessage("Error",
                                "No se pudo estableser coneccion con el servidor",
                                MessageAlerts.MessageType.ERROR);
            }
        }
        return verificacion;
    }

    private void showWarning(JLabel warning, String text) {
        warning.setText(text);
        warning.setVisible(true);
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                warning.setVisible(false);
            }
        }, 5000);
    }
}
