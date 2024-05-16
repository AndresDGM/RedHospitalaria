package redhospitalaria.visual;

import javax.swing.*;

public class GuiLogin extends JPanel {

    private SingIn singIn = new SingIn();
    private SingUp singUp = new SingUp();

    public GuiLogin() {
        setSize(1000, 600);
        setBackground(Util.color);
        setLayout(null);
        add(singIn);
        add(singUp);
        setVisible(true);
    }

    public SingIn getSingIn() {
        return singIn;
    }

    public SingUp getSingUp() {
        return singUp;
    }
    
    public void setDefault(){
        singIn.setDefault();
        singUp.setDefault();
    }
}
