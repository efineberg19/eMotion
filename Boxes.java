import javax.sound.midi.*;
/**
 * Creates easy structure for utilizing and generating boxes. Good way to 
 * store and retrieve values that will determine what the boxes look like in
 * the GUI and what notes are played.
 *
 * @author Beth Fineberg
 * @version 1.0
 */
public class Boxes
{
    int x, y, height, width;
    boolean visible;
    int n;

    /**
     * Constructor
     *
     * Stores the location, size, visibilty, and name of note associated with
     * each box.
     */
    public Boxes(int xLoc, int yLoc, int h, int w, boolean canSee, int note)
    {
        x = xLoc;
        y = yLoc;
        height = h;
        width = w;
        visible = canSee;
        n = note;
    }

    /**
     * Is Visible
     * 
     * Returns the value of visible to determine whether or not the boxes 
     * should be filled in.
     * 
     * @return boolean visible
     */
    public boolean isVisible()
    {
        return visible;
    }

    /**
     * Empty
     * 
     * Sets visible variable to false so the box won't be filled in.
     */
    public void empty()
    {
        visible = false;
    }

    /**
     * Show
     * 
     * Sets visible cariable to true so the box will be filled in.
     */
    public void show()
    {
        visible = true;
    }

    /**
     * Get X
     * 
     * Returns the x location of the box.
     * 
     * @return int x the x location of the box
     */
    public int getX()
    {
        return x;
    }

    /**
     * Get Y
     * 
     * Returns the y location of the box.
     * 
     * @return int y the y location of the box
     */
    public int getY()
    {
        return y;
    }

    /**
     * Get Width
     * 
     * Returns the width of the box.
     * 
     * @return int width the width location of the box
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * Get Height
     * 
     * Returns the height of the box.
     * 
     * @return int height the height location of the box
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * Get Note
     * 
     * Returns the note associated with the box.
     * 
     * @return int note the note to be played when box is clicked
     */
    public int getNote()
    {
        return n;
    }
}
