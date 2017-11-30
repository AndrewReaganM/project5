import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * Panel for drawing a single view of an infant
 * 
 * @author CS2334, modified by ???
 * @version 2017-11-10
 *
 */
public class KinematicPanel extends JPanel
{
    /** Drawing flip direction for X: +/-1*/
    private double flipX;
    /** Drawing flip direction for Y: +/-1*/
    private double flipY;
    
    /** Subfield used for X dimension. */
    private String screenXSubfield;
    /** Subfield used for Y dimension. */
    private String screenYSubfield;
    /** Root of the kinematic tree.  */
    private KinematicPointAbstract rootPoint;
    /** State to render.  */
    private State state;
    /** Panel title */
    private String title;
    /** Font used for panel title.  */
    // TODO: initialize the font
    private static final Font FONT;

    /**
     * Constructor 
     * 
     * @param rootPoint Root of the kinematic tree for this panel
     * @param flipX Drawing direction for X dimension: +/- 1
     * @param flipY Drawing direction for Y dimension: +/- 1
     * @param screenXSubfield Subfield name for the X dimension
     * @param screenYSubfield Subfield name for the Y dimension
     * @param title Panel title
     */
    public KinematicPanel(KinematicPointAbstract rootPoint, 
            double flipX, double flipY, 
            String screenXSubfield, String screenYSubfield, String title)
    {
        
        super();
        this.flipX = flipX;
        this.flipY = flipY;
        this.rootPoint = rootPoint;
        this.screenXSubfield = screenXSubfield;
        this.screenYSubfield = screenYSubfield;
        this.setPreferredSize(new Dimension(400, 200));
        this.title = title;
        
        //Font Setup
        File file = new File("font/Raleway-Regular.ttf");
        FileInputStream stream = new FileInputStream(file);
        FONT = Font.createFont(Font.TRUETYPE_FONT, stream);
        
        // Set up the border for the panel
        Border border = BorderFactory.createLineBorder(Color.black);
        this.setBorder(border);
    }

    /**
     * Set the state to render
     * 
     * @param state State to be rendered
     */
    public void setState(State state)
    {
        this.state = state;
        this.repaint();
    }

    /**
     * Render the panel
     * 
     * @param g Graphics context
     */
    protected void paintComponent(Graphics g)   
    {
        super.paintComponent(g);
        
        // TODO: Draw the title  

        // Render as long as state is defined
        if (this.state != null)
        {
            Graphics2D g2 = (Graphics2D) g;
            
            // Translate the graphics context origin to the center of the panel
            g2.translate(this.getWidth() / 2, this.getHeight() / 2);
            // Flip the drawing directions
            g2.scale(flipX, flipY);
            
            // TODO: Draw the kinematic tree 

            
            // These next two lines make the border drawing work properly
            // Flip back
            g2.scale(flipX, flipY);
            // Translate the origin back 
            g2.translate(-this.getWidth() / 2, -this.getHeight() / 2);
        }
    }
}