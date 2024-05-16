/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.visual;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import redhospitalaria.visual.DateChooserDJRaven.DateChooser;

public class SingUp extends JPanel {

    private GuiLogin g;
    private JLabel passLabel;
    private JLabel pSUComfirmL;
    private JLabel nombreLabel;
    private JLabel fechaLabel;
    private BasicButton botonSingUp;
    private BasicButton botonAtras;
    private BasicButton botonSiguiente;
    private BasicButton botonSingUpMode;
    private JLabel correoLabel;
    private TextField id;
    private JLabel idLabel;
    private PasswordField password;
    private PasswordField password2;
    private TextField nombre;
    private DateChooser fecha = new DateChooser();
    private TextField fechatxt;
    private BasicButton fechaBoton;
    private TextField correo;
    private JLabel msgS1;
    private JLabel msgS2;
    private JLabel msgS3;
    private ComboBox<String> depar;
    private ComboBox<String> muni;
    private JLabel deparLabel;
    private JLabel muniLabel;
    private JLabel dirLabel;
    private ComboBox<String> eps;
    private ComboBox<String> genero;
    private ComboBox<String> especializacion;
    private JLabel epsLabel;
    private JLabel generoLabel;
    private JLabel especializacionLabel;
    private TextField direccion;
    private JLabel[] warnings = new JLabel[3];
    private Image logo = new ImageIcon("src/images/MingcuteUserAdd2Fill.png").getImage();
    private float alpha = 1f;
    private int mode = 1;

    public SingUp() {
        setBounds(0, 0, 350, 600);
        setBackground(Util.color.darker());
        setLayout(null);
        singUpInit();
    }

    private void singUpInit() {
        nombreLabel = new JLabel("Nombre completo");
        nombreLabel.setBounds(125, 120, 300, 100);
        nombreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nombreLabel.setHorizontalAlignment(JLabel.LEFT);
        nombreLabel.setVerticalAlignment(JLabel.CENTER);
        nombreLabel.setForeground(Color.WHITE);
        nombreLabel.setVisible(false);
        nombre = new TextField("Ingrese su nombre completo");
        nombre.setLocation(125, 190);
        nombre.setVisible(false);

        fechaLabel = new JLabel("Fecha de nacimiento");
        fechaLabel.setBounds(125, 230, 300, 100);
        fechaLabel.setFont(new Font("Arial", Font.BOLD, 18));
        fechaLabel.setHorizontalAlignment(JLabel.LEFT);
        fechaLabel.setVerticalAlignment(JLabel.CENTER);
        fechaLabel.setForeground(Color.WHITE);
        fechaLabel.setVisible(false);
        fechatxt = new TextField(fecha.getDateFormat());
        fechatxt.setBounds(125, 300, 365, 40);
        fechatxt.setLocation(125, 300);
        fechatxt.setFont(new Font("Arial", Font.PLAIN, 18));
        fechatxt.setOpaque(false);
        fechatxt.setBorder(null);
        fechatxt.setVisible(false);
        fecha.setTextRefernce(fechatxt);
        fecha.setForeground(new Color(65, 150, 240));
        fecha.setBackground(new Color(45, 45, 45));
        fechaBoton = new BasicButton(40, 40, "...");
        fechaBoton.setLocation(500, 300);
        fechaBoton.getTextF().setFont(new Font("Arial", Font.PLAIN, 18));
        fechaBoton.getTextF().setVerticalAlignment(JTextField.CENTER);
        fechaBoton.setVisible(false);

        idLabel = new JLabel("Indentificacion");
        idLabel.setBounds(125, 340, 300, 100);
        idLabel.setFont(new Font("Arial", Font.BOLD, 18));
        idLabel.setHorizontalAlignment(JLabel.LEFT);
        idLabel.setVerticalAlignment(JLabel.CENTER);
        idLabel.setForeground(Color.WHITE);
        idLabel.setVisible(false);
        id = new TextField("Ingrese su cedula sin puntos ni comas");
        id.setLocation(125, 410);
        id.setVisible(false);

        correoLabel = new JLabel("Correo");
        correoLabel.setBounds(125, 120, 300, 100);
        correoLabel.setFont(new Font("Arial", Font.BOLD, 18));
        correoLabel.setHorizontalAlignment(JLabel.LEFT);
        correoLabel.setVerticalAlignment(JLabel.CENTER);
        correoLabel.setForeground(Color.WHITE);
        correoLabel.setVisible(false);
        correo = new TextField("example@mail.com");
        correo.setLocation(125, 190);
        correo.setVisible(false);

        passLabel = new JLabel("Contraseña");
        passLabel.setBounds(125, 230, 300, 100);
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passLabel.setHorizontalAlignment(JLabel.LEFT);
        passLabel.setVerticalAlignment(JLabel.CENTER);
        passLabel.setForeground(Color.WHITE);
        passLabel.setVisible(false);
        password = new PasswordField();
        password.setLocation(125, 300);
        password.setVisible(false);

        pSUComfirmL = new JLabel("Confirmar contraseña");
        pSUComfirmL.setBounds(125, 340, 300, 100);
        pSUComfirmL.setFont(new Font("Arial", Font.BOLD, 18));
        pSUComfirmL.setHorizontalAlignment(JLabel.LEFT);
        pSUComfirmL.setVerticalAlignment(JLabel.CENTER);
        pSUComfirmL.setForeground(Color.WHITE);
        pSUComfirmL.setVisible(false);
        password2 = new PasswordField();
        password2.setLocation(125, 410);
        password2.setVisible(false);

        deparLabel = new JLabel("Departamento");
        deparLabel.setBounds(125, 120, 300, 100);
        deparLabel.setFont(new Font("Arial", Font.BOLD, 18));
        deparLabel.setHorizontalAlignment(JLabel.LEFT);
        deparLabel.setVerticalAlignment(JLabel.CENTER);
        deparLabel.setForeground(Color.WHITE);
        deparLabel.setVisible(false);
        depar = new ComboBox<>(Util.departamentos);
        depar.setBounds(125, 190, 400, 40);
        depar.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                if (depar.getSelectedItem() != null &&
                        !depar.getSelectedItem().equals(depar.getItemAt(0))) {
                    muni.removeAllItems();
                    int index = Arrays.asList(Util.departamentos).indexOf(depar.getSelectedItem()) - 1;
                    for (String s : Util.municipios[index]) {
                        muni.addItem(s);
                    }
                    muni.setEnabled(true);
                } else {
                    muni.removeAllItems();
                    muni.setEnabled(false);
                }
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                if (depar.getSelectedItem() != null &&
                        !depar.getSelectedItem().equals(depar.getItemAt(0))) {
                    muni.removeAllItems();
                    int index = Arrays.asList(Util.departamentos).indexOf(depar.getSelectedItem()) - 1;
                    for (String s : Util.municipios[index]) {
                        muni.addItem(s);
                    }
                    muni.setEnabled(true);
                } else {
                    muni.removeAllItems();
                    muni.setEnabled(false);
                }
            }
        });
        depar.setVisible(false);

        muniLabel = new JLabel("Municipio");
        muniLabel.setBounds(125, 230, 300, 100);
        muniLabel.setFont(new Font("Arial", Font.BOLD, 18));
        muniLabel.setHorizontalAlignment(JLabel.LEFT);
        muniLabel.setVerticalAlignment(JLabel.CENTER);
        muniLabel.setForeground(Color.WHITE);
        muniLabel.setVisible(false);
        muni = new ComboBox<>();
        muni.setBounds(125, 300, 400, 40);
        muni.setEnabled(false);
        muni.setVisible(false);

        dirLabel = new JLabel("Direccion");
        dirLabel.setBounds(125, 340, 300, 100);
        dirLabel.setFont(new Font("Arial", Font.BOLD, 18));
        dirLabel.setHorizontalAlignment(JLabel.LEFT);
        dirLabel.setVerticalAlignment(JLabel.CENTER);
        dirLabel.setForeground(Color.WHITE);
        dirLabel.setVisible(false);
        direccion = new TextField("Tu direccion");
        direccion.setLocation(125, 410);
        direccion.setVisible(false);
        
        generoLabel = new JLabel("Genero");
        //generoLabel.setBounds(125, 160, 300, 100);
        generoLabel.setBounds(125, 120, 300, 100);
        generoLabel.setFont(new Font("Arial", Font.BOLD, 18));
        generoLabel.setHorizontalAlignment(JLabel.LEFT);
        generoLabel.setVerticalAlignment(JLabel.CENTER);
        generoLabel.setForeground(Color.WHITE);
        generoLabel.setVisible(false);
        String[] generos = {"Mujer", "Hombre", "Otros"};
        genero = new ComboBox<>(generos);
        //genero.setBounds(125, 230, 400, 40);
        genero.setBounds(125, 190, 400, 40);
        genero.setVisible(false);
        
        epsLabel = new JLabel("Eps");
        //epsLabel.setBounds(125, 270, 300, 100);
        epsLabel.setBounds(125, 230, 300, 100);
        epsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        epsLabel.setHorizontalAlignment(JLabel.LEFT);
        epsLabel.setVerticalAlignment(JLabel.CENTER);
        epsLabel.setForeground(Color.WHITE);
        epsLabel.setVisible(false);
        String[] epsItems = {"Emssanar", "Sanitas", "SOS", "salud total", "Sura"};
        eps = new ComboBox<>(epsItems);
        //eps.setBounds(125, 340, 400, 40);
        eps.setBounds(125, 300, 400, 40);
        eps.setVisible(false);
        
        especializacionLabel = new JLabel("Especializacion");
        especializacionLabel.setBounds(125, 340, 300, 100);
        especializacionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        especializacionLabel.setHorizontalAlignment(JLabel.LEFT);
        especializacionLabel.setVerticalAlignment(JLabel.CENTER);
        especializacionLabel.setForeground(Color.WHITE);
        especializacionLabel.setVisible(false);
        especializacion = new ComboBox<>(Util.especializaciones);
        especializacion.setBounds(125, 410, 400, 40);
        especializacion.setVisible(false);

        msgS1 = new JLabel();
        msgS1.setText("Nuevo Afiliado?");
        msgS1.setBounds(25, 50, 300, 100);
        msgS1.setFont(new Font("Arial", Font.BOLD, 34));
        msgS1.setHorizontalAlignment(JLabel.CENTER);
        msgS1.setVerticalAlignment(JLabel.CENTER);
        msgS1.setForeground(Color.WHITE);
        msgS2 = new JLabel("Registrarse");
        msgS2.setBounds(25, 300, 300, 100);
        msgS2.setFont(new Font("Arial", Font.BOLD, 22));
        msgS2.setHorizontalAlignment(JLabel.CENTER);
        msgS2.setVerticalAlignment(JLabel.CENTER);
        msgS2.setForeground(Color.WHITE);
        msgS3 = new JLabel("Registrarse");
        msgS3.setBounds(175, 50, 300, 100);
        msgS3.setFont(new Font("Arial", Font.BOLD, 34));
        msgS3.setHorizontalAlignment(JLabel.CENTER);
        msgS3.setVerticalAlignment(JLabel.CENTER);
        msgS3.setForeground(Color.WHITE);
        msgS3.setVisible(false);

        botonSiguiente = new BasicButton(100, 50, "Siguiente");
        botonSiguiente.setLocation(520, 480);
        botonSiguiente.getTextF().setFont(new Font("Arial", Font.PLAIN, 18));
        botonSiguiente.getTextF().setVerticalAlignment(JTextField.CENTER);
        botonSiguiente.setColor(new Color(65, 150, 240));
        botonSiguiente.setVisible(false);

        botonSingUp = new BasicButton(130, 50, "Crear Cuenta");
        botonSingUp.setLocation(505, 480);
        botonSingUp.getTextF().setFont(new Font("Arial", Font.PLAIN, 18));
        botonSingUp.getTextF().setVerticalAlignment(JTextField.CENTER);
        botonSingUp.setColor(new Color(65, 150, 240));
        botonSingUp.setVisible(false);

        botonAtras = new BasicButton(100, 50, "Atras");
        botonAtras.setLocation(30, 480);
        botonAtras.getTextF().setFont(new Font("Arial", Font.PLAIN, 18));
        botonAtras.getTextF().setVerticalAlignment(JTextField.CENTER);
        botonAtras.setColor(new Color(65, 150, 240));
        botonAtras.setVisible(false);

        botonSingUpMode = new BasicButton(140, 50, "Crear cuenta");
        botonSingUpMode.setLocation(105, 450);
        botonSingUpMode.getTextF().setFont(new Font("Arial", Font.PLAIN, 18));
        botonSingUpMode.getTextF().setVerticalAlignment(JTextField.CENTER);
        botonSingUpMode.setColor(new Color(65, 150, 240));

        warnings[0] = new JLabel();
        warnings[0].setBounds(125, 230, 400, 20);
        warnings[0].setForeground(new Color(255, 80, 85));
        warnings[0].setFont(new Font("Arial", Font.PLAIN, 16));
        warnings[0].setVisible(false);
        warnings[1] = new JLabel();
        warnings[1].setBounds(125, 340, 400, 20);
        warnings[1].setForeground(new Color(255, 80, 85));
        warnings[1].setFont(new Font("Arial", Font.PLAIN, 16));
        warnings[1].setVisible(false);
        warnings[2] = new JLabel();
        warnings[2].setBounds(125, 450, 400, 20);
        warnings[2].setForeground(new Color(255, 80, 85));
        warnings[2].setFont(new Font("Arial", Font.PLAIN, 16));
        warnings[2].setVisible(false);

        add(nombreLabel);
        add(nombre);
        add(fechaLabel);
        add(fechatxt);
        add(fechaBoton);
        add(idLabel);
        add(id);
        add(correoLabel);
        add(correo);
        add(passLabel);
        add(password);
        add(pSUComfirmL);
        add(password2);
        add(deparLabel);
        add(depar);
        add(muniLabel);
        add(muni);
        add(dirLabel);
        add(direccion);
        add(generoLabel);
        add(genero);
        add(epsLabel);
        add(eps);
        add(especializacionLabel);
        add(especializacion);
        add(msgS1);
        add(msgS2);
        add(msgS3);
        add(botonSiguiente);
        add(botonAtras);
        add(botonSingUpMode);
        add(botonSingUp);
        add(warnings[0]);
        add(warnings[1]);
        add(warnings[2]);
        setVisible(true);
    }

    public ArrayList<Component> getComps1() {
        return new ArrayList<>(Arrays.asList(msgS1, msgS2, botonSingUpMode));
    }

    public ArrayList<Component> getComps2() {
        ArrayList<Component> list = null;
        switch (mode) {
            case 1 -> list = new ArrayList<>(Arrays.asList(
                    nombre,
                    nombreLabel,
                    fechaLabel,
                    id,
                    idLabel,
                    fechatxt,
                    fechaBoton,
                    msgS3,
                    botonSiguiente));
            case 2 -> list = new ArrayList<>(Arrays.asList(
                    dirLabel,
                    direccion,
                    muni,
                    muniLabel,
                    depar,
                    deparLabel,
                    botonAtras,
                    botonSiguiente,
                    msgS3));
            case 3 -> list = new ArrayList<>(Arrays.asList(
                    genero,
                    generoLabel,
                    eps,
                    epsLabel,
                    botonAtras,
                    botonSingUp,
                    msgS3));
            default -> list = new ArrayList<>(Arrays.asList(
                    correo,
                    correoLabel,
                    passLabel,
                    password,
                    pSUComfirmL,
                    password2,
                    botonAtras,
                    botonSingUp,
                    msgS3));
        };
        return list;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                alpha));

        g2d.drawImage(logo, 100, 150, 150, 150, null);
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public float getAlpha() {
        return alpha;
    }

    public void changeVisible(int mode) {
        idLabel.setVisible(mode == 1);
        id.setVisible(mode == 1);
        nombreLabel.setVisible(mode == 1);
        nombre.setVisible(mode == 1);
        fechaLabel.setVisible(mode == 1);
        fechaBoton.setVisible(mode == 1);
        fechatxt.setVisible(mode == 1);
        botonSiguiente.setVisible(mode == 1 || mode == 2 || mode == 3);
        dirLabel.setVisible(mode == 2);
        direccion.setVisible(mode == 2);
        muni.setVisible(mode == 2);
        muniLabel.setVisible(mode == 2);
        depar.setVisible(mode == 2);
        deparLabel.setVisible(mode == 2);
        generoLabel.setVisible(mode == 3);
        genero.setVisible(mode == 3);
        epsLabel.setVisible(mode == 3);
        eps.setVisible(mode == 3);
        botonAtras.setVisible(mode == 2 || mode == 3 || mode == 4);
        correoLabel.setVisible(mode == 4);
        correo.setVisible(mode == 4);
        passLabel.setVisible(mode == 4);
        password.setVisible(mode == 4);
        pSUComfirmL.setVisible(mode == 4);
        password2.setVisible(mode == 4);
        botonSingUp.setVisible(mode == 4);
        for (JLabel warning : warnings) {
            warning.setVisible(false);
        }
        this.mode = mode;
    }

    public void setDefault() {
        id.setText("");
        nombre.setText("");
        fechatxt.setText("");
        direccion.setText("");
        depar.setSelectedIndex(0);
        muni.removeAllItems();
        muni.setEnabled(false);
        genero.setSelectedIndex(0);
        eps.setSelectedIndex(0);
        especializacion.setSelectedIndex(0);
        correo.setText("");
        password.setText("");
        password2.setText("");
    }

    public JLabel[] getWarnings() {
        return warnings;
    }

    public JLabel getpSUComfirmL() {
        return pSUComfirmL;
    }

    public BasicButton getBotonSingUp() {
        return botonSingUp;
    }

    public BasicButton getBotonAtras() {
        return botonAtras;
    }

    public BasicButton getBotonSiguiente() {
        return botonSiguiente;
    }

    public BasicButton getBotonSingUpMode() {
        return botonSingUpMode;
    }

    public TextField getId() {
        return id;
    }

    public PasswordField getPassword() {
        return password;
    }

    public PasswordField getPassword2() {
        return password2;
    }

    public void setPassword2(PasswordField password2) {
        this.password2 = password2;
    }

    public TextField getNombre() {
        return nombre;
    }

    public TextField getFechatxt() {
        return fechatxt;
    }

    public DateChooser getFecha() {
        return fecha;
    }

    public void setFecha(DateChooser fecha) {
        this.fecha = fecha;
    }

    public BasicButton getFechaBoton() {
        return fechaBoton;
    }

    public void setFechaBoton(BasicButton fechaBoton) {
        this.fechaBoton = fechaBoton;
    }

    public TextField getCorreo() {
        return correo;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public ComboBox<String> getDepar() {
        return depar;
    }

    public void setDepar(ComboBox<String> depar) {
        this.depar = depar;
    }

    public ComboBox<String> getMuni() {
        return muni;
    }

    public void setMuni(ComboBox<String> muni) {
        this.muni = muni;
    }

    public TextField getDireccion() {
        return direccion;
    }

    public void setDireccion(TextField direccion) {
        this.direccion = direccion;
    }

    public JLabel getMsgS1() {
        return msgS1;
    }

    public void setMsgS1(JLabel msgS1) {
        this.msgS1 = msgS1;
    }

    public JLabel getMsgS2() {
        return msgS2;
    }

    public void setMsgS2(JLabel msgS2) {
        this.msgS2 = msgS2;
    }

    public JLabel getMsgS3() {
        return msgS3;
    }

    public void setMsgS3(JLabel msgS3) {
        this.msgS3 = msgS3;
    }

    public JLabel getMuniLabel() {
        return muniLabel;
    }

    public void setMuniLabel(JLabel muniLabel) {
        this.muniLabel = muniLabel;
    }

    public Image getLogo() {
        return logo;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }

    public ComboBox<String> getEps() {
        return eps;
    }

    public ComboBox<String> getGenero() {
        return genero;
    }

    public ComboBox<String> getEspecializacion() {
        return especializacion;
    }

    public JLabel getEspecializacionLabel() {
        return especializacionLabel;
    }
    
    
}
