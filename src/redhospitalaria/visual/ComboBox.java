/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.visual;

import javax.swing.JComboBox;

/**
 *
 * @author david
 */
public class ComboBox<E> extends JComboBox<E> {

    ComboUI cbUI = new ComboUI();
    public ComboBox() {
        setUI(cbUI);
        setEditable(true);
    }

    public ComboBox(E[] items) {
        super(items);
        setUI(new ComboUI());
        setEditable(true);
    }

    public ComboUI getCbUI() {
        return cbUI;
    }

    public void setCbUI(ComboUI cbUI) {
        this.cbUI = cbUI;
    }
}
