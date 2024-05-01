/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 *
 * @author david
 */
public class ModernScrollBarUI extends BasicScrollBarUI{
    
    private Color colorTracker = new Color(200, 200, 200);
    private Color colorThumb = new Color(200, 200, 200);
    private final int thumbSize = 60;

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int size, x, y, width, height;
        if(scrollbar.getOrientation() == JScrollBar.VERTICAL){
            size = trackBounds.width / 2;
            x = trackBounds.x + ((trackBounds.width - size) / 2);
            y = trackBounds.y;
            width = size;
            height = trackBounds.height;
        }else{
            size = trackBounds.height / 2;
            y = trackBounds.y + ((trackBounds.height - size) / 2);
            x = 0;
            width = trackBounds.width;
            height = size;
        }
        g2d.setColor(colorTracker);
        g2d.fillRect(x, y, width, height);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int x = thumbBounds.x, y = thumbBounds.y, width = thumbBounds.width, height = thumbBounds.height;
        if(scrollbar.getOrientation() == JScrollBar.VERTICAL){
            y+=8;
            height -= 16;
        }else{
            x+=8;
            width -= 16;
        }
        g2d.setColor(colorThumb);
        g2d.fillRoundRect(x,y,width,height,10,10);
    }

    @Override
    protected Dimension getMaximumThumbSize() {
        if(scrollbar.getOrientation() == JScrollBar.VERTICAL){
            return new Dimension(0, thumbSize);
        }else{
            return new Dimension(thumbSize, 0);
        }
    }

    @Override
    protected Dimension getMinimumThumbSize() {
        if(scrollbar.getOrientation() == JScrollBar.VERTICAL){
            return new Dimension(0, thumbSize);
        }else{
            return new Dimension(thumbSize, 0);
        }
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new Button();
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
       return new Button();
    }
    
    private class Button extends JButton{

        public Button() {
            setBorder(BorderFactory.createEmptyBorder());
        }

        @Override
        public void paint(Graphics g) {
        }
    }

    public Color getColorTracker() {
        return colorTracker;
    }

    public void setColorTracker(Color colorTracker) {
        this.colorTracker = colorTracker;
    }

    public Color getColorThumb() {
        return colorThumb;
    }

    public void setColorThumb(Color colorThumb) {
        this.colorThumb = colorThumb;
    }
}
