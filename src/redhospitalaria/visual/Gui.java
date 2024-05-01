package redhospitalaria.visual;

import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import raven.popup.GlassPanePopup;

public class Gui extends JFrame {

    private GuiLogin login;
    private DrawerMenu dm;
    private CitasGui cg;
    private ListadosGui lg;
    private PerfilGui pg;
    private BuscarGui bg;
    private BuscarGui rg;
    private CrearUsusario cu;

    public Gui() {
        super("Red Hospitalaria");
        initGui();
    }

    private void initGui() {
        setSize(1014, 637);
        setIconImage(new ImageIcon("src/images/edificio-hospitalario.png").getImage());
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(null);
        FlatDarkLaf.setup();
        GlassPanePopup.install(this);
        login = new GuiLogin();
        dm = new DrawerMenu(100, getHeight());
        dm.setVisible(false);
        cg = new CitasGui();
        cg.setVisible(false);
        lg = new ListadosGui();
        lg.setVisible(false);
        pg = new PerfilGui();
        pg.setVisible(false);
        bg = new BuscarGui();
        bg.setVisible(false);
        rg = new BuscarGui();
        rg.setVisible(false);
        rg.getTitle().setText("Eliminar");
        BasicButton b = new BasicButton(100, 30, "Eliminar");
        b.setLocation(rg.getBoton().getX(), rg.getBoton().getY());
        b.setColor(new Color(255, 80, 85));
        b.getTextF().setFont(new Font("arial", Font.PLAIN, 18));
        rg.getBoton().setLocation(rg.getBoton().getX() - 50, rg.getBoton().getY());
        rg.getBuscar().setLocation(rg.getBuscar().getX() - 50, rg.getBuscar().getY());
        rg.add(b);
        cu = new CrearUsusario();
        cu.setVisible(false);
        add(login);
        add(cg);
        add(lg);
        add(dm);
        add(pg);
        add(bg);
        add(cu);
        add(rg);
        setVisible(false);
    }

    public GuiLogin getLogin() {
        return login;
    }

    public DrawerMenu getDm() {
        return dm;
    }

    public CitasGui getCg() {
        return cg;
    }

    public ListadosGui getLg() {
        return lg;
    }

    public PerfilGui getPg() {
        return pg;
    }

    public void setPg(PerfilGui pg) {
        this.pg = pg;
    }

    public BuscarGui getBg() {
        return bg;
    }

    public void setBg(BuscarGui bg) {
        this.bg = bg;
    }

    public CrearUsusario getCu() {
        return cu;
    }

    public void setCu(CrearUsusario cu) {
        this.cu = cu;
    }

    public BuscarGui getRg() {
        return rg;
    }

    public void setRg(BuscarGui rg) {
        this.rg = rg;
    }

}
