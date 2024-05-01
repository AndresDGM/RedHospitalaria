/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.visual;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;
import redhospitalaria.visual.ModernScrollBarUI;

/**
 *
 * @author david
 */
public class ScrollBar extends JScrollBar{

    public ScrollBar() {
        ModernScrollBarUI ms = new ModernScrollBarUI();
        ms.setColorThumb(new Color(65, 150, 240));
        ms.setColorTracker(Util.color.brighter());
        setUI(ms);
        setPreferredSize(new Dimension(8,8));
        setForeground(new Color(48,144,216));
        setBackground(Util.color.brighter());
    }
}
