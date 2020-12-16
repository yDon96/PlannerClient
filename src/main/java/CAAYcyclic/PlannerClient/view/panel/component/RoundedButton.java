/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.view.panel.component;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;

/**
 *
 * @author Amos
 */
public final class RoundedButton extends JButton{

    /** Stroke size. it is recommended to set it to 1 for better view */
    protected int strokeSize = 1;
    /** Color of shadow */
    protected Color shadowColor = Color.black;
    /** Sets if it drops shadow */
    protected boolean shady = true;
    /** Sets if it has an High Quality view */
    protected boolean highQuality = true;
    /** Double values for Horizontal and Vertical radius of corner arcs */
    protected Dimension arcs = new Dimension(15, 15);
    /** Distance between shadow border and opaque panel border */
    protected int shadowGap = 5;
    /** The offset of shadow.  */
    protected int shadowOffset = 4;
    /** The transparency value of shadow. ( 0 - 255) */
    protected int shadowAlpha = 150;
    

    public RoundedButton(String text) {
        super(text);
        setOpaque(false);
    }
    
    public RoundedButton() {
        super();
        setOpaque(false);
    }


   @Override
   protected void paintComponent(Graphics g) {
       super.paintComponent(g);
       int width = getWidth();
       int height = getHeight();
       int shadowGap = this.shadowGap;
       /*Color shadowColorA = new Color(shadowColor.getRed(),
       shadowColor.getGreen(), shadowColor.getBlue(), shadowAlpha);*/
       Graphics2D graphics = (Graphics2D) g;

       //Sets antialiasing if HQ.
       if (highQuality) {
           graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
           RenderingHints.VALUE_ANTIALIAS_ON);
       }

       //Draws shadow borders if any.
       /*if (shady) {
           graphics.setColor(shadowColorA);
           graphics.fillRoundRect(
                   shadowOffset,// X position
                   shadowOffset,// Y position
                   width - strokeSize - shadowOffset, // width
                   height - strokeSize - shadowOffset, // height
                   arcs.width, arcs.height);// arc Dimension
       } else {
           shadowGap = 1;
       }*/

       //Draws the rounded opaque panel with borders.
       graphics.setColor(getBackground());
       graphics.fillRoundRect(0, 0, width ,
       height, arcs.width, arcs.height);
       graphics.setColor(Color.WHITE);
       graphics.setStroke(new BasicStroke(strokeSize));
       graphics.drawRoundRect(0, 0, width,
       height, arcs.width, arcs.height);

       //Sets strokes to default, is better.
       graphics.setStroke(new BasicStroke());
       
       
        // Finding size of text so can position in center.
        FontRenderContext frc = 
           new FontRenderContext(null, false, false);
        Rectangle2D r = getFont().getStringBounds(getText(), frc);

        float xMargin = (float)(getWidth()-r.getWidth())/2;
        float yMargin = (float)(getHeight()-getFont().getSize())/2;

        // Draw the text in the center
        graphics.drawString(getText(), xMargin, 
           (float)getFont().getSize() + yMargin);
   }
}
