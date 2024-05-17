package redhospitalaria.controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import raven.alerts.MessageAlerts;
import redhospitalaria.modelo.Doctor;
import redhospitalaria.modelo.Paciente;
import redhospitalaria.modelo.Predicado;
import redhospitalaria.visual.BasicButton;
import redhospitalaria.visual.DarkTable;
import redhospitalaria.visual.DateChooserDJRaven.DateChooser;
import redhospitalaria.visual.ListadosGui;
import redhospitalaria.visual.Util;

public class ListarControl implements ActionListener {

    private ListadosGui lg;
    private PrincipalControl mainControl;
    private Paciente user;

    public ListarControl(ListadosGui lg, PrincipalControl mainControl, Paciente user) {
        this.lg = lg;
        this.mainControl = mainControl;
        this.user = user;
        setActions();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lg.getLista()) {
            lg.getJsp().setVisible(true);
            lg.getPanelGraficos().setVisible(false);
        }

        if (e.getSource() == lg.getLimpiar()) {
            lg.getJsp().setVisible(true);
            lg.getTable().setModel(new DefaultTableModel(lg.colums, 0));
            lg.getBox().setSelectedIndex(0);
            lg.getOpcion1().setVisible(false);
            lg.getOpcion2().setVisible(false);
            lg.getOpcion3().setVisible(false);
            lg.getPieDataset().setValue("Usuarios encontrados", 0);
            lg.getPieDataset().setValue("Usuarios descartados", 0);
            lg.getPanelGraficos().setVisible(false);
            comboFunc();
        }

        if (e.getSource() == lg.getListar()) {
            if (!((String) lg.getBox().getSelectedItem()).equals("Seleccionar") && 
                    ((lg.getOpcion1().getSelectedIndex() != -1 &&
                    lg.getOpcion2().getSelectedIndex() != -1 &&
                    lg.getOpcion3().getSelectedIndex() != -1) ||
                    lg.getBox().getSelectedIndex() <= 5 && lg.getBox().getSelectedIndex() > 0)) {
                listarFunc();
            } else {
                MessageAlerts.getInstance()
                        .showMessage("Advertencia",
                                "Seleccione un tipo de listado",
                                MessageAlerts.MessageType.WARNING);
            }
        }

        if (e.getSource() == lg.getGraficos()) {
            lg.getJsp().setVisible(false);
            lg.getPanelGraficos().setVisible(true);
        }
    }

    private void setActions() {
        lg.getLista().addActionListener(this);
        lg.getListar().addActionListener(this);
        lg.getGraficos().addActionListener(this);
        lg.getLimpiar().addActionListener(this);
        lg.getBox().addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                comboFunc();
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                comboFunc();
            }
        });
    }

    private void listarFunc() {
        user = mainControl.getUser();
        ArrayList<String[]> rows = (ArrayList<String[]>) mainControl.peticion("list",
                getPredicate(lg.getBox().getSelectedIndex()));
        int cant = (Integer) mainControl.peticion("cont",
                getPredicate(lg.getBox().getSelectedIndex()));
        int total = 0;
        if(lg.getBox().getSelectedIndex() < 7){
            total = (Integer) mainControl.peticion("cant:doctors");
        }else{
            total = (Integer) mainControl.peticion("cant:all");
        }
        
        lg.getPieDataset().setValue("Usuarios encontrados", cant);
        lg.getPieDataset().setValue("Usuarios descartados", total - cant);
        String[][] lista = {{}};
        if (!rows.isEmpty()) {
            lista = new String[rows.size()][rows.get(0).length];
            for (int i = 0; i < rows.size(); i++) {
                System.arraycopy(rows.get(i), 0, lista[i], 0, lista[0].length);
            }
        }

        DefaultTableModel dtm = new DefaultTableModel(lista, lg.colums) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        lg.getTable().setModel(dtm);
        resizeTable(lg.getTable());
        /**/
    }

    private void resizeTable(DarkTable table) {
        for (int column = 0; column < table.getColumnCount(); column++) {
            TableColumn tableColumn = table.getColumnModel().getColumn(column);
            int currentWidth = tableColumn.getWidth();
            int maxWidth = tableColumn.getMinWidth();

            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
                Component c = table.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
                maxWidth = Math.max(maxWidth, width);
            }
            if (maxWidth > currentWidth) {
                tableColumn.setPreferredWidth(maxWidth);
            }
        }
    }

    Predicado<Paciente> getPredicate(int opcion) {
        Predicado<Paciente> p = paciente -> false;

        switch (opcion) {
            case 1 -> {
                p = paciente -> paciente.getClass() == Doctor.class && paciente.getEdad() >= 33; 
            }
            case 2 -> {
                p = paciente -> paciente.getClass() == Doctor.class && paciente.getEdad() < 33;
            }
            case 3 -> {
                String departamento = user.getDepartamento();
                p = paciente -> paciente.getClass() == Doctor.class && paciente.getDepartamento().equals(departamento);
            }
            case 4 -> {
                String muni = user.getMunicipio();
                p = paciente -> paciente.getClass() == Doctor.class && paciente.getMunicipio().equals(muni);
            }
            case 5 -> {
                p = paciente -> paciente.getClass() == Doctor.class && paciente.getEdad() > 30;
            }
            case 6 -> {
                String esp = (String) lg.getOpcion1().getSelectedItem();
                String deper = (String) lg.getOpcion3().getSelectedItem();
                Predicado<Integer> citas = citasPre.get(lg.getOpcion2().getSelectedIndex());
                p = paciente -> paciente.getClass() == Doctor.class &&
                        ((Doctor) paciente).getEspecializacion().equals(esp) &&
                        citas.test(paciente.getCitasAtendidas()) &&
                        paciente.getDepartamento().equals(deper);
            }
            case 7 -> {
                String genero = (String) lg.getOpcion1().getSelectedItem();
                String eps = (String) lg.getOpcion3().getSelectedItem();
                Predicado<Integer> edad = edadPre.get(lg.getOpcion2().getSelectedIndex());
                p = paciente -> paciente.getGenero().equals(genero) &&
                        edad.test(paciente.getEdad()) &&
                        paciente.getEps().equals(eps);
            }
            case 8 -> {
                String genero = (String) lg.getOpcion1().getSelectedItem();
                String muni = (String) lg.getOpcion3().getSelectedItem();
                String depar = (String) lg.getOpcion2().getSelectedItem();
                p = paciente -> paciente.getGenero().equals(genero) &&
                        paciente.getDepartamento().equals(depar) && 
                        paciente.getMunicipio().equals(muni);
            }
            case 9 -> {
                String eps = (String) lg.getOpcion1().getSelectedItem();
                String depar = (String) lg.getOpcion3().getSelectedItem();
                Predicado<Integer> edad = edadPre.get(lg.getOpcion2().getSelectedIndex());
                p = paciente -> paciente.getEps().equals(eps) &&
                        edad.test(paciente.getEdad()) && 
                        paciente.getDepartamento().equals(depar);
            }
            case 10 -> {
                Predicado<Integer> edad = edadPre.get(lg.getOpcion1().getSelectedIndex());
                Predicado<Integer> cita = citasPre.get(lg.getOpcion3().getSelectedIndex());
                Predicado<String> regis = regPre.get(lg.getOpcion2().getSelectedIndex());
                p = paciente -> edad.test(paciente.getEdad()) &&
                        regis.test(paciente.getFechaRegistro()) && 
                        cita.test(paciente.getCitasSolicitadas());
            }
            case 11 -> {
                String eps = (String) lg.getOpcion1().getSelectedItem();
                String depar = (String) lg.getOpcion2().getSelectedItem();
                String muni = (String) lg.getOpcion3().getSelectedItem();
                p = paciente -> paciente.getEps().equals(eps) &&
                        paciente.getDepartamento().equals(depar) && 
                        paciente.getMunicipio().equals(muni);
            }
            case 12 -> {
                Predicado<Integer> edad = edadPre.get(lg.getOpcion1().getSelectedIndex());
                Predicado<Integer> cita = citasPre.get(lg.getOpcion3().getSelectedIndex());
                Predicado<String> regis = regPre.get(lg.getOpcion2().getSelectedIndex());
                p = paciente -> edad.test(paciente.getEdad()) &&
                        regis.test(paciente.getFechaRegistro()) && 
                        cita.test(paciente.getCitasAtendidas());
            }
            case 13 -> {
                String genero = (String) lg.getOpcion1().getSelectedItem();
                Predicado<Integer> edad = edadPre.get(lg.getOpcion2().getSelectedIndex());
                Predicado<Integer> cita = citasPre.get(lg.getOpcion3().getSelectedIndex());
                p = paciente -> paciente.getGenero().equals(genero) &&
                        edad.test(paciente.getEdad()) &&
                        cita.test(paciente.getCitasAtendidas());
            }
            case 14 -> {
                String genero = (String) lg.getOpcion1().getSelectedItem();
                Predicado<Integer> edad = edadPre.get(lg.getOpcion2().getSelectedIndex());
                Predicado<Integer> cita = citasPre.get(lg.getOpcion3().getSelectedIndex());
                p = paciente -> paciente.getGenero().equals(genero) &&
                        edad.test(paciente.getEdad()) &&
                        cita.test(paciente.getCitasSolicitadas());
            }
            case 15 -> {
                Predicado<String> regis = regPre.get(lg.getOpcion1().getSelectedIndex());
                Predicado<Integer> cita = citasPre.get(lg.getOpcion2().getSelectedIndex());
                Predicado<Integer> cita2 = citasPre.get(lg.getOpcion3().getSelectedIndex());
                p = paciente -> regis.test(paciente.getFechaRegistro()) && 
                        cita.test(paciente.getCitasAtendidas()) &&
                        cita2.test(paciente.getCitasSolicitadas());
            }
        }
        return p;
        /*
        "Doctores - Especializacion - Citas Atendidas - Departamento",
            "Genero - Edad - Eps",
            "Genero - Departamento - Municipio",
            "Eps - Edad - Departamento",
            "Edad - Registro - Citas Solicitadas",
            "Eps - Departamento - Municipio",
            "Edad - Registro - Citas Atendidas",
            "Genero - Edad - Citas Atendidas",
            "Genero - Edad - Citas Solicitadas",
            "Registro - Citas Atendidas - Citas Solicitadas"
         */
    }

    public void comboFunc() {
        if (lg.getBox().getSelectedIndex() > 5) {
            lg.getOpcion1().removeAllItems();
            lg.getOpcion2().removeAllItems();
            lg.getOpcion3().removeAllItems();
            switch (lg.getBox().getSelectedIndex()) {
                case 6 -> {
                    lg.getOpcion1().setModel(new DefaultComboBoxModel<String>(Util.especializaciones));
                    lg.getOpcion2().setModel(new DefaultComboBoxModel<String>(citas));
                    lg.getOpcion3().setModel(new DefaultComboBoxModel<String>(Util.departamentos));
                    lg.getOpcion3().removeItemAt(0);
                }
                case 7 -> {
                    lg.getOpcion1().setModel(new DefaultComboBoxModel<String>(Util.generos));
                    lg.getOpcion2().setModel(new DefaultComboBoxModel<String>(edad));
                    lg.getOpcion3().setModel(new DefaultComboBoxModel<String>(Util.epsItems));
                }
                case 8 -> {
                    lg.getOpcion1().setModel(new DefaultComboBoxModel<String>(Util.generos));

                    lg.getOpcion2().setModel(new DefaultComboBoxModel<String>(Util.departamentos));
                    lg.getOpcion2().addPopupMenuListener(new PopupMenuListener() {
                        @Override
                        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                        }

                        @Override
                        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                            if (lg.getOpcion2().getSelectedItem() != null
                                    && !lg.getOpcion2().getSelectedItem().equals(lg.getOpcion2().getItemAt(0))) {
                                lg.getOpcion3().removeAllItems();
                                int index = Arrays.asList(Util.departamentos).indexOf(lg.getOpcion2().getSelectedItem()) - 1;
                                lg.getOpcion3().setModel(new DefaultComboBoxModel<String>(Util.municipios[index]));
                                lg.getOpcion3().removeItemAt(0);
                                lg.getOpcion3().setEnabled(true);
                            } else {
                                lg.getOpcion3().removeAllItems();
                                lg.getOpcion3().setEnabled(false);
                            }
                        }
                        @Override
                        public void popupMenuCanceled(PopupMenuEvent e) {
                            popupMenuWillBecomeInvisible(e);
                        }
                    });
                    lg.getOpcion2().removeItemAt(0);
                    lg.getOpcion3().setModel(new DefaultComboBoxModel<String>(Util.municipios[0]));
                    lg.getOpcion3().removeItemAt(0);
                }
                case 9 -> {
                    lg.getOpcion1().setModel(new DefaultComboBoxModel<String>(Util.epsItems));
                    lg.getOpcion2().setModel(new DefaultComboBoxModel<String>(edad));
                    lg.getOpcion3().setModel(new DefaultComboBoxModel<String>(Util.departamentos));
                    lg.getOpcion3().removeItemAt(0);
                }
                case 10 -> {
                    lg.getOpcion1().setModel(new DefaultComboBoxModel<String>(edad));
                    lg.getOpcion2().setModel(new DefaultComboBoxModel<String>(registro));
                    lg.getOpcion3().setModel(new DefaultComboBoxModel<String>(citas));
                }
                case 11 -> {
                    lg.getOpcion1().setModel(new DefaultComboBoxModel<String>(Util.epsItems));
                    lg.getOpcion2().setModel(new DefaultComboBoxModel<String>(Util.departamentos));
                    lg.getOpcion2().addPopupMenuListener(new PopupMenuListener() {
                        @Override
                        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                        }

                        @Override
                        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                            if (lg.getOpcion2().getSelectedItem() != null
                                    && !lg.getOpcion2().getSelectedItem().equals(lg.getOpcion2().getItemAt(0))) {
                                lg.getOpcion3().removeAllItems();
                                int index = Arrays.asList(Util.departamentos).indexOf(lg.getOpcion2().getSelectedItem()) - 1;
                                lg.getOpcion3().setModel(new DefaultComboBoxModel<String>(Util.municipios[index]));
                                lg.getOpcion3().removeItemAt(0);
                                lg.getOpcion3().setEnabled(true);
                            } else {
                                lg.getOpcion3().removeAllItems();
                                lg.getOpcion3().setEnabled(false);
                            }
                        }
                        @Override
                        public void popupMenuCanceled(PopupMenuEvent e) {
                            popupMenuWillBecomeInvisible(e);
                        }
                    });
                    lg.getOpcion2().removeItemAt(0);
                    lg.getOpcion3().setModel(new DefaultComboBoxModel<String>(Util.municipios[0]));
                    lg.getOpcion3().removeItemAt(0);
                }
                case 12 -> {
                    lg.getOpcion1().setModel(new DefaultComboBoxModel<String>(edad));
                    lg.getOpcion2().setModel(new DefaultComboBoxModel<String>(registro));
                    lg.getOpcion3().setModel(new DefaultComboBoxModel<String>(citas));
                }
                case 13 -> {
                    lg.getOpcion1().setModel(new DefaultComboBoxModel<String>(Util.generos));
                    lg.getOpcion2().setModel(new DefaultComboBoxModel<String>(edad));
                    lg.getOpcion3().setModel(new DefaultComboBoxModel<String>(citas));
                }
                case 14 -> {
                    lg.getOpcion1().setModel(new DefaultComboBoxModel<String>(Util.generos));
                    lg.getOpcion2().setModel(new DefaultComboBoxModel<String>(edad));
                    lg.getOpcion3().setModel(new DefaultComboBoxModel<String>(citas));
                }
                case 15 -> {
                    lg.getOpcion1().setModel(new DefaultComboBoxModel<String>(registro));
                    lg.getOpcion2().setModel(new DefaultComboBoxModel<String>(citas));
                    lg.getOpcion3().setModel(new DefaultComboBoxModel<String>(citas));
                }
            }
            lg.getOpcion1().setVisible(true);
            lg.getOpcion2().setVisible(true);
            lg.getOpcion3().setVisible(true);
        } else {
            lg.getOpcion1().setVisible(false);
            lg.getOpcion2().setVisible(false);
            lg.getOpcion3().setVisible(false);
        }
    }

    private String[] edad = {"Niños (12 años o menos)", "Adolecentes (entr 12 y 18)", "Adultos (entre 18 y 65)", "Adultos mayores (mas de 65)"};
    private ArrayList<Predicado<Integer>> edadPre = new ArrayList<>(
            Arrays.asList(
                    edad -> edad <= 12,
                    edad -> edad > 12 && edad < 18,
                    edad -> edad >= 18 && edad <= 65,
                    edad -> edad > 65));

    private String[] citas = {"10 o menos", "Entre 10 y 50", "Entre 50 y 100", "Mas de 100"};
    private ArrayList<Predicado<Integer>> citasPre = new ArrayList<>(
            Arrays.asList(cant -> cant <= 10,
                    cant -> cant > 10 && cant < 50,
                    cant -> cant >= 50 && cant < 100,
                    cant -> cant > 100));

    private String[] registro = {"Ultimo mes", "Ultimo año", "Ultimos 5 años", "Cualquier Antiguedad"};
    private ArrayList<Predicado<String>> regPre = new ArrayList<>(
            Arrays.asList(
                    fecha -> Util.dayBeetwen(fecha, DateChooser.getToDay()) <= 30L,
                    fecha -> Util.dayBeetwen(fecha, DateChooser.getToDay()) <= 365L,
                    fecha -> Util.dayBeetwen(fecha, DateChooser.getToDay()) <= 1825L,
                    fecha -> true));
}
