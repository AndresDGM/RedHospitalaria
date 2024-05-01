package redhospitalaria.controlador;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JTextField;
import raven.alerts.MessageAlerts;
import raven.popup.component.PopupController;
import redhospitalaria.modelo.Modelo;
import redhospitalaria.modelo.Usuario;
import redhospitalaria.visual.ComboBox;
import redhospitalaria.visual.DateChooserDJRaven.DateChooser;
import redhospitalaria.visual.GuiLogin;

public class LoginControl implements ActionListener {

    private GuiLogin v;
    private Modelo m;
    private PrincipalControl mainControl;
    //change mode variables
    private Timer timer = new Timer();
    private int Counter = 255;
    private float Counter2;
    private float Counter3;
    private boolean done = false;

    public LoginControl(GuiLogin v, Modelo m, PrincipalControl mainControl) throws IOException {
        this.v = v;
        this.m = m;
        this.mainControl = mainControl;
        Counter2 = v.getSingIn().getWidth();
        Counter3 = v.getSingUp().getWidth();
        setActions();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == v.getSingUp().getBotonSingUpMode() && v.getSingUp().getBotonSingUpMode().isFocusable()) {
            singUpModeboton();
        }
        if (e.getSource() == v.getSingIn().getBotonSingInMode() && v.getSingIn().getBotonSingInMode().isFocusable()) {
            singInModeboton();
        }

        if (e.getSource() == v.getSingUp().getBotonSingUp() && v.getSingUp().getBotonSingUp().isFocusable()) {
            try {
                singUpboton();
            } catch (IOException | ClassNotFoundException ex) {
            }
        }
        if (e.getSource() == v.getSingIn().getBotonSingIn() && v.getSingIn().getBotonSingIn().isFocusable()) {
            try {
                singInboton();
            } catch (IOException | ClassNotFoundException ex) {
            }
        }
        if (e.getSource() == v.getSingUp().getBotonSiguiente() && v.getSingUp().getBotonSiguiente().isFocusable()) {
            try {
                if (botonSiguienteVal()) {
                    v.getSingUp().changeVisible(v.getSingUp().getMode() + 1);
                }
            } catch (IOException | ClassNotFoundException ex) {
            }
        }
        if (e.getSource() == v.getSingUp().getBotonAtras() && v.getSingUp().getBotonAtras().isFocusable()) {
            v.getSingUp().changeVisible(v.getSingUp().getMode() - 1);
        }
        if (e.getSource() == v.getSingUp().getFechaBoton() && v.getSingUp().getFechaBoton().isFocusable()) {
            v.getSingUp().getFecha().showPopup();
            if (!v.getSingUp().getFechatxt().isFocusable()) {
                v.getSingUp().getFechatxt().setFocusable(true);
            }
            v.getSingUp().getFechatxt().requestFocus();
        }
    }

    private void setActions() {
        v.getSingUp().getBotonSingUpMode().addActionListener(this);
        v.getSingUp().getBotonAtras().addActionListener(this);
        v.getSingUp().getBotonSiguiente().addActionListener(this);
        v.getSingUp().getBotonSingUp().addActionListener(this);
        v.getSingUp().getFechaBoton().addActionListener(this);
        v.getSingIn().getBotonSingInMode().addActionListener(this);
        v.getSingIn().getBotonSingIn().addActionListener(this);
    }

    public GuiLogin getV() {
        return v;
    }

    public void changeMode(ArrayList<Component> compOcult, ArrayList<Component> compShow, float x, float y, boolean mode) {
        if (Counter >= 0 && !done) {
            for (Component c : compOcult) {
                c.setFocusable(false);
                int r = c.getForeground().getRed(), gr = c.getForeground().getGreen(), b = c.getForeground().getBlue();
                c.setForeground(new Color(r, gr, b, Counter));
                if (c instanceof ComboBox comboBox) {
                    JTextField txt = (JTextField) comboBox.getEditor().getEditorComponent();
                    txt.setForeground(new Color(255, 255, 255, Counter));
                }
                r = c.getBackground().getRed();
                gr = c.getBackground().getGreen();
                b = c.getBackground().getBlue();
                c.setBackground(new Color(r, gr, b, Counter));
            }
            Counter--;
            if (mode) {
                v.getSingUp().setAlpha(v.getSingUp().getAlpha() - 0.0039f);
            } else {
                v.getSingIn().setAlpha(v.getSingIn().getAlpha() - 0.0039f);
            }

            Counter2 += x;
            Counter3 += y;
            v.getSingUp().setSize((int) Counter3, v.getSingUp().getHeight());
            v.getSingIn().setBounds((int) Counter3, 0, (int) Counter2, v.getSingIn().getHeight());
            if (Counter == -1) {
                done = true;
                Counter = 0;
            }
        } else if (Counter <= 255 && done) {
            for (Component c : compShow) {
                c.setVisible(true);
                c.setFocusable(false);
                int r = c.getForeground().getRed(), gr = c.getForeground().getGreen(), b = c.getForeground().getBlue();
                c.setForeground(new Color(r, gr, b, Counter));
                r = c.getBackground().getRed();
                gr = c.getBackground().getGreen();
                b = c.getBackground().getBlue();
                c.setBackground(new Color(r, gr, b, Counter));
            }
            Counter++;
            Counter2 += x;
            Counter3 += y;
            if (mode) {
                v.getSingIn().setAlpha(v.getSingIn().getAlpha() + 0.0039f);
            } else {
                v.getSingUp().setAlpha(v.getSingUp().getAlpha() + 0.0039f);
            }

            v.getSingUp().setSize((int) Counter3, v.getSingUp().getHeight());
            v.getSingIn().setBounds((int) Counter3, 0, (int) Counter2, v.getSingIn().getHeight());
        } else {
            for (Component c : compShow) {
                c.setFocusable(true);
            }
            for (Component c : compOcult) {
                c.setVisible(false);
                int r = c.getForeground().getRed(), gr = c.getForeground().getGreen(), b = c.getForeground().getBlue();
                c.setForeground(new Color(r, gr, b, 255));
                r = c.getBackground().getRed();
                gr = c.getBackground().getGreen();
                b = c.getBackground().getBlue();
                c.setBackground(new Color(r, gr, b, 255));
                if (c instanceof ComboBox comboBox) {
                    JTextField txt = (JTextField) comboBox.getEditor().getEditorComponent();
                    txt.setForeground(new Color(255, 255, 255, 255));
                }
                c.setFocusable(true);
            }
            timer.cancel();
            done = false;
            Counter = 255;
            timer = new Timer();
            if (mode) {
                v.getSingUp().setAlpha(0f);
                v.getSingIn().setAlpha(1f);
            } else {
                v.getSingUp().setAlpha(1f);
                v.getSingIn().setAlpha(0f);
                v.getSingUp().setMode(1);
            }
            v.getSingUp().setDefault();
            v.getSingIn().setDefault();
        }
    }

    private void singUpModeboton() {
        Arrays.stream(v.getSingIn().getWarnings())
                .forEach(j -> j.setVisible(false));
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ArrayList<Component> a = v.getSingIn().getComps1();

                a.addAll(v.getSingUp().getComps1());
                ArrayList<Component> b = v.getSingIn().getComps2();

                b.addAll(v.getSingUp().getComps2());
                changeMode(a, b, -300f / 510f, 300f / 510f, true);
            }
        }, 0, 1);
    }

    private void singInModeboton() {
        Arrays.stream(v.getSingUp().getWarnings())
                .forEach(j -> j.setVisible(false));
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ArrayList<Component> a = v.getSingUp().getComps2();
                a.addAll(v.getSingIn().getComps2());
                ArrayList<Component> b = v.getSingUp().getComps1();
                b.addAll(v.getSingIn().getComps1());
                changeMode(a, b, 300f / 510f, -300f / 510f, false);
            }
        }, 0, 1);
    }

    private void singInboton() throws IOException, ClassNotFoundException {
        if (botonIniciarVal()) {
            try {
                var o = mainControl.peticion("sesion:" + v.getSingIn().getId().getText() + ":" + String.valueOf(v.getSingIn().getPassword().getPassword()));
                if (o instanceof String) {
                    MessageAlerts.getInstance()
                            .showMessage("Error",
                                    "Combinacion de usuario y contraseña incorrectos",
                                    MessageAlerts.MessageType.ERROR);
                } else if (o instanceof Usuario usuario) {
                    MessageAlerts.getInstance()
                            .showMessage("Listo",
                                    "Inicio de secion Exitoso",
                                    MessageAlerts.MessageType.SUCCESS, MessageAlerts.OK_OPTION, (PopupController pc, int i) -> {
                                        mainControl.setUser(usuario);
                                        mainControl.setPerfil(usuario);
                                        mainControl.ocultarLogin();
                                        mainControl.mostrarCitas();
                                        mainControl.mostrarMenu();
                    });
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

    private void singUpboton() throws IOException, ClassNotFoundException {
        if (botonCrearVal()) {
            Usuario u = new Usuario(
                    Integer.parseInt(v.getSingUp().getId().getText()),
                    v.getSingUp().getNombre().getText(),
                    String.valueOf(v.getSingUp().getPassword().getPassword()),
                    "Paciente",
                    v.getSingUp().getCorreo().getText(),
                    v.getSingUp().getFechatxt().getText(),
                    DateChooser.getToDay(),
                    (String) v.getSingUp().getDepar().getSelectedItem(),
                    (String) v.getSingUp().getMuni().getSelectedItem(),
                    v.getSingUp().getDireccion().getText()
            );
            try {
                Boolean b = (Boolean) mainControl.peticion("create", u);
                if (b) {
                    MessageAlerts.getInstance()
                            .showMessage("Listo",
                                    "Se ha creado su usuario",
                                    MessageAlerts.MessageType.SUCCESS, MessageAlerts.OK_OPTION, (PopupController pc, int i) -> {
                                        mainControl.setUser(u);
                                        mainControl.setPerfil(u);
                                        mainControl.ocultarLogin();
                                        mainControl.mostrarCitas();
                                        mainControl.mostrarMenu();
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

    private boolean botonIniciarVal() throws IOException, ClassNotFoundException {
        boolean verificacion = true;
        boolean excep = false;

        if (v.getSingIn().getId().getText().isEmpty()) {
            verificacion = false;
            showWarning(v.getSingIn().getWarnings()[0], "Por favor ingrese su cedula");
        } else {
            try {
                if (!(Boolean) mainControl.peticion("val:id:" + v.getSingIn().getId().getText())) {
                    verificacion = false;
                    showWarning(v.getSingIn().getWarnings()[0], "Por favor solo ingrese numeros");
                }
            } catch (NullPointerException e) {
                excep = true;
                verificacion = false;
                MessageAlerts
                        .getInstance()
                        .showMessage("Error",
                                "No se pudo estableser coneccion con el servidor",
                                MessageAlerts.MessageType.ERROR);
            }
        }

        if (String.valueOf(v.getSingIn().getPassword().getPassword()).isEmpty()) {
            verificacion = false;
            showWarning(v.getSingIn().getWarnings()[1], "Por favor ingrese su contraseña");
        } else {
            try {
                if (!(Boolean) mainControl.peticion("val:password:" + String.valueOf(v.getSingIn().getPassword().getPassword()))) {
                    verificacion = false;
                    showWarning(v.getSingIn().getWarnings()[1], "longitud minima de 8 caracteres");
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

    private boolean botonSiguienteVal() throws IOException, ClassNotFoundException {
        boolean verificacion = true;

        if (v.getSingUp().getMode() == 1) {
            if (v.getSingUp().getNombre().getText().isBlank()) {
                verificacion = false;
                showWarning(v.getSingUp().getWarnings()[0], "Por favor ingrese su nombre completo");
            }

            if (v.getSingUp().getFechatxt().getText().isBlank()) {
                verificacion = false;
                showWarning(v.getSingUp().getWarnings()[1], "Por favor ingrese su fecha de nacimiento");
            }

            if (v.getSingUp().getId().getText().isBlank()) {
                verificacion = false;
                showWarning(v.getSingUp().getWarnings()[2], "Por favor ingrese su cedula");
            } else {
                try {
                    if (!(Boolean) mainControl.peticion("val:id:" + v.getSingUp().getId().getText())) {
                        verificacion = false;
                        showWarning(v.getSingUp().getWarnings()[2], "Por favor solo ingrese numeros");
                    } else {
                        try {
                            if ((Boolean) mainControl.peticion("exits:" + v.getSingUp().getId().getText())) {
                                verificacion = false;
                                showWarning(v.getSingUp().getWarnings()[2], "Ya existe una cuenta con esa cedula");
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
        } else {
            if(v.getSingUp().getDepar().getSelectedItem() != null){
                if (v.getSingUp().getDepar().getSelectedItem().equals("Seleccionar")) {
                    verificacion = false;
                    showWarning(v.getSingUp().getWarnings()[0], "Por favor ingrese su departamento");
                }
            }else{
                verificacion = false;
                showWarning(v.getSingUp().getWarnings()[0], "Por favor ingrese su departamento");
            }
            if (v.getSingUp().getMuni().getItemCount() == 0) {
                verificacion = false;
                showWarning(v.getSingUp().getWarnings()[1], "Por favor ingrese su municipio");
            } else if (v.getSingUp().getMuni().getSelectedItem().equals("Seleccionar")) {
                verificacion = false;
                showWarning(v.getSingUp().getWarnings()[1], "Por favor ingrese su municipio");
            }

            if (v.getSingUp().getDireccion().getText().isBlank()) {
                verificacion = false;
                showWarning(v.getSingUp().getWarnings()[2], "Por favor ingrese su direccion");
            }
        }
        return verificacion;
    }

    private boolean botonCrearVal() throws IOException, ClassNotFoundException {
        boolean verificacion = true;

        if (v.getSingUp().getCorreo().getText().isBlank()) {
            verificacion = false;
            showWarning(v.getSingUp().getWarnings()[0], "Por favor ingrese su correo electronico");
        } else {
            try {
                if (!(Boolean) mainControl.peticion("val:email:" + v.getSingUp().getCorreo().getText())) {
                    verificacion = false;
                    showWarning(v.getSingUp().getWarnings()[0], "Por favor ingrese un correo como el ejemplo");
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

        if (String.valueOf(v.getSingUp().getPassword().getPassword()).isBlank()) {
            verificacion = false;
            showWarning(v.getSingUp().getWarnings()[1], "Por favor cree una contraseña");
        } else {
            try {
                if (!(Boolean) mainControl.peticion("val:password:" + String.valueOf(v.getSingUp().getPassword().getPassword()))) {
                    verificacion = false;
                    showWarning(v.getSingUp().getWarnings()[1], "longitud minima de 8 caracteres");
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

        if (String.valueOf(v.getSingUp().getPassword2().getPassword()).isBlank()) {
            verificacion = false;
            showWarning(v.getSingUp().getWarnings()[2], "debe comfirmar la contraseña");
        } else if (!String.valueOf(v.getSingUp().getPassword2().getPassword())
                .equals(String.valueOf(v.getSingUp().getPassword().getPassword()))) {
            verificacion = false;
            showWarning(v.getSingUp().getWarnings()[2], "contraseñas no coinciden");
        } else {
            try {
                if (!(Boolean) mainControl.peticion("val:password:" + String.valueOf(v.getSingUp().getPassword2().getPassword()))) {
                    verificacion = false;
                    showWarning(v.getSingUp().getWarnings()[2], "longitud minima de 8 caracteres");
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
