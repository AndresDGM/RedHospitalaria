package redhospitalaria.visual;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import raven.popup.GlassPanePopup;

public class Gui extends JFrame {

    private GuiLogin login;
    private DrawerMenu dm;
    private CitasGui cg;
    private ListadosGui lg;
    private PerfilGui pg;
    private BuscarGui bg;
    private RemoverGui rg;
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
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        FlatDarkLaf.setup();
        GlassPanePopup.install(this);
        login = new GuiLogin();
        dm = new DrawerMenu(600);
        dm.setVisible(false);
        cg = new CitasGui();
        cg.setVisible(false);
        lg = new ListadosGui();
        lg.setVisible(false);
        pg = new PerfilGui();
        pg.setVisible(false);
        bg = new BuscarGui();
        bg.setVisible(false);
        cu = new CrearUsusario();
        cu.setVisible(false);
        rg = new RemoverGui();
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

    public RemoverGui getRg() {
        return rg;
    }

    public void setRg(RemoverGui rg) {
        this.rg = rg;
    }
}
