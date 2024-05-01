package redhospitalaria.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import redhospitalaria.modelo.Usuario;
import redhospitalaria.visual.ListadosGui;

public class ListarControl implements ActionListener {

    private ListadosGui lg;
    private PrincipalControl mainControl;
    private Usuario user;

    public ListarControl(ListadosGui lg, PrincipalControl mainControl, Usuario user) {
        this.lg = lg;
        this.mainControl = mainControl;
        this.user = user;
        setActions();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lg.getLista()) {
            lg.getTable().setVisible(true);
        }

        if (e.getSource() == lg.getLimpiar()) {
            lg.getTable().setModel(new DefaultTableModel());
        }

        if (e.getSource() == lg.getListar()) {
            listarFunc();
        }

        if (e.getSource() == lg.getGraficos()) {
            lg.getTable().setVisible(false);
        }
    }

    private void setActions() {
        lg.getLista().addActionListener(this);
        lg.getListar().addActionListener(this);
        lg.getGraficos().addActionListener(this);
        lg.getLimpiar().addActionListener(this);
    }

    private void listarFunc() {
        user = mainControl.getUser();
        String[] colums = {
            "Cedula",
                "Nombre",
                "Edad",
                "Correo",
                "Fecha de nacimiento",
                "Fecha de registro",
                "Dirrecion",
                "Departamento",
                "Municipio"
        };
        ArrayList<String[]> b = (ArrayList<String[]>) mainControl.peticion("list:" + (String) lg.getBox().getSelectedItem(), user);
        String[][] lista = new String[b.size()][b.get(0).length];
        for (int i = 0; i < lista.length; i++) {
            for (int j = 0; j < lista[0].length; j++) {
                lista[i][j] = b.get(i)[j];
            }
        }
        DefaultTableModel dtm = new DefaultTableModel(lista,colums);
        lg.getTable().setModel(dtm);
    }
}

