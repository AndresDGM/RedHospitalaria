package redhospitalaria.visual;

import javax.swing.JPanel;

public class DrawerMenu extends JPanel{
    private String[] categories = new String[]{
        "Perfil",
        "Buscar",
        "Listado de citas",
        "Listar",
        "Añadir",
        "Eliminar"
            //,"Notificaciones"
    };
    
    private BasicButton[] cButtons = new BasicButton[categories.length];
    
    //private JPanel head = new JPanel();
    
    private boolean full = false;
    
    private int fullWidth;
    private int width = 40;
    private int height;
    
    public DrawerMenu(int showWidth, int height){
        this.height = height;
        this.fullWidth = showWidth;
        initGui();
    }
    
    private void initGui(){
        setSize(width,height);
        setLayout(null);
        setBackground(Util.color.brighter());
        //head.setBounds(0, 0, width, width);
        //head.setBackground(Util.color);
        for (int i = 0; i < categories.length; i++) {
            cButtons[i] = new BasicButton(width, width, categories[i]);
            cButtons[i].setIcon("src/images/"+i+".png");
            cButtons[i].setArcH(0);
            cButtons[i].setArcW(0);
            cButtons[i].setLocation(0, width*(i));
            cButtons[i].setColor(getBackground());
            add(cButtons[i]);
        }
        //add(head);
        setVisible(true);
    }

    public BasicButton[] getcButtons() {
        return cButtons;
    }

    public String[] getCategories() {
        return categories;
    }

    public boolean isFull() {
        return full;
    }

    public int getFullWidth() {
        return fullWidth;
    }

    public int getW() {
        return width;
    }

    public int getH() {
        return height;
    }

    public void setcButtons(BasicButton[] cButtons) {
        this.cButtons = cButtons;
    }

    /*public void setHead(JPanel head) {
        this.head = head;
    }*/

    public void setFull(boolean full) {
        this.full = full;
    }

    public void setFullWidth(int fullWidth) {
        this.fullWidth = fullWidth;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
