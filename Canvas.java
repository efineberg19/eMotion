import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Rectangle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;
import java.io.File;
import java.util.ArrayList;

/**
 * Canvas to display lines, circles, squares, images, text...
 */
public class Canvas extends JPanel
{
    //ArrayList of boxes to be drawn
    ArrayList<Boxes> boxes = new ArrayList<Boxes>();
    //ArrayList of the color of the boxes
    ArrayList<Color> colors = new ArrayList<Color>();

    //paintComponent dictates what is shown on the GUI and manages boxes
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setStroke(new BasicStroke(0));

        //generates the boxes and adds them to the ArrayList
        for(int i = 0; i < 14; i++)
        {
            boxes.add(new Boxes(100 * i, 10, 100, 100, false, 60 + i));
        }

        //adds the colors to be randomly chosen to an ArrayList
        colors.add(Color.RED);
        colors.add(Color.ORANGE);
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.PINK);

        //goes through ArrayList of boxes and determines how they should be drawn
        for(Boxes b : boxes)
        {
            if(!b.isVisible()) //shows empty box if it is determined to not be visible
            {
                g.setColor(Color.BLACK);
                g.drawRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
            }
            else //draws the boxes if it is visible
            {
                //generates random color for box to be
                Color c = colors.get((int)(Math.random() * colors.size()));
                g2.setColor(c);

                g2.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
            }
        }
    }

    /**
     * Get Box
     * 
     * Returns the box stored at a certain index of the ArrayList of boxes.
     * 
     * @param i index of desired box
     * @return Boxes the desired box
     */
    public Boxes getBox(int i)
    {
        return boxes.get(i);
    }
    
    /**
     * Get Boxes
     * 
     * Returns the ArrayList of generated Boxes.
     * 
     * @return ArrayList<Boxes> boxes the generated boxes
     */
    public ArrayList<Boxes> getBoxes()
    {
        return boxes;
    }
}
