import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Rectangle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;
import java.io.File;
import javax.sound.midi.*;

/**
 * JFrame with buttons and JPanel canvas. Moving mouse over buttons will make 
 * a noise and cause a change in color. Canvas is repainted when the mouse is 
 * moved or clicked.
 */
public class GUI extends JFrame implements ActionListener, MouseMotionListener, MouseListener
{
    //declare instance variables
    //including declarations of Canvas, JPanels, JButtons, etc.

    //the area that the piano kets are on
    JPanel keyArea;

    //area where boxes are generated
    Canvas c = new Canvas();

    //determines the value for which midi instrument to be played
    int instrument = 0;

    /**
     * GUI Constructor
     * 
     * Creates button and sets us framework for drawings.
     */
    public GUI(String title)
    {
        super(title);

        //creates panel on bottom for buttons to be shown
        JPanel buttonPanel = new JPanel();

        //creates button that will show user instructions when clicked
        JButton instructions = new JButton("Instructions");
        instructions.setActionCommand("instructions");
        buttonPanel.add(instructions);

        //creates button that will cause a new instrument to be randomly picked
        JButton newInstrument = new JButton("New Instrument");
        newInstrument.setActionCommand("newInstrument");
        buttonPanel.add(newInstrument);

        //creates button that will close the program when clicked
        JButton quit = new JButton("Quit");
        quit.setActionCommand("quit");
        buttonPanel.add(quit);

        //adds listeners so that stuff actually happens when the mouse is clicked or moved
        instructions.addActionListener(this);
        newInstrument.addActionListener(this);
        quit.addActionListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        //determines the location and layout for the buttons and boxes
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.add(c, BorderLayout.CENTER);

        this.setSize(1300, 200);
        setVisible(true);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }
    
    /**
     * Action Performed
     * 
     * Responds to a click of a button.
     */
    public void actionPerformed (ActionEvent e)
    {
        if (e.getActionCommand().equals("quit"))
        {
            System.exit(0);
        }
        else if (e.getActionCommand().equals("newInstrument"))
        {
            //randomly generates a new number that corresponds with an instrument
            instrument = (int)(Math.random() * 100);
        }
        else if (e.getActionCommand().equals("instructions"))
        {
            JOptionPane.showMessageDialog(null, "Hover or click on keys to play " 
                + "a note. \nClick \"New Instrument\" to get a new random instrument.");
        }
    }

    /**
     * Mouse Moved
     * 
     * Causes the GUI to be repainted. It causes a note to be played if the 
     * mouse is found to be within the horizontal bounds of the keys.
     */
    @Override
    public void mouseMoved(MouseEvent e) 
    {
        c.repaint();

        //goes through list of generated boxes
        for (int i = 0; i < c.getBoxes().size(); i++)
        {
            //reacts if mouse is in the right location
            if (e.getX() >= (10 + 100 * i) && e.getX() <= (110 + 100 * i))
            {
                //fills box
                c.getBox(i).show();

                try
                {
                    //note: I figured out how to do this through Stack Overflow
                    
                    //creates synthesizer
                    Synthesizer midi = MidiSystem.getSynthesizer(); 
                    midi.open();
                    //loads the sounds and instruments
                    Soundbank sb = midi.getDefaultSoundbank();
                    midi.loadAllInstruments(sb);
                    Instrument[] instr = midi.getAvailableInstruments();
                    MidiChannel[] channels = midi.getChannels();
                    //determines the instrument
                    midi.loadInstrument(instr[instrument]);
                    midi.getChannels()[0].programChange(instrument);
                    
                    //causes notes to actually be played
                    channels[0].noteOn(c.getBox(i).getNote(), 10000); 

                    //pauses execution for a moment
                    try { Thread.sleep(10); 
                    } catch( InterruptedException n ) { }

                    //turns the note off
                    channels[0].noteOff(c.getBox(i).getNote());
                }
                catch (MidiUnavailableException m) {}
            }
            else
            {
                //shows empty box if mouse is not in the right location
                c.getBox(i).empty();
            }
        }
    }

    /**
     * Mouse Clicked
     * 
     * Causes the GUI to be repainted. It causes a note to be played if the 
     * mouse is clicked within the horizontal bounds of the keys.
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
        repaint();
        
        //goes through list of generated boxes
        for (int i = 0; i < c.getBoxes().size(); i++)
        {
            //reacts if mouse is in the right location
            if (e.getX() >= (10 + 100 * i) && e.getX() <= (110 + 100 * i))
            {
                //fills box
                c.getBox(i).show();

                try
                {
                    //note: I figured out how to do this through Stack Overflow
                    
                    //creates synthesizer
                    Synthesizer midi = MidiSystem.getSynthesizer(); 
                    midi.open();
                    //loads the sounds and instruments
                    Soundbank sb = midi.getDefaultSoundbank();
                    midi.loadAllInstruments(sb);
                    Instrument[] instr = midi.getAvailableInstruments();
                    MidiChannel[] channels = midi.getChannels();
                    //determines the instrument
                    midi.loadInstrument(instr[instrument]);
                    midi.getChannels()[0].programChange(instrument);
                    
                    //causes notes to actually be played
                    channels[0].noteOn(c.getBox(i).getNote(), 10000); 

                    //pauses execution for a moment
                    try { Thread.sleep(10); 
                    } catch( InterruptedException n ) { }

                    //turns the note off
                    channels[0].noteOff(c.getBox(i).getNote());
                }
                catch (MidiUnavailableException m) {}
            }
            else
            {
                //shows empty box if mouse is not in the right location
                c.getBox(i).empty();
            }
        }
    }

    /**
     * Mouse Released
     * 
     * Necessary class due to implementation. Does nothing.
     */
    @Override
    public void mouseReleased(MouseEvent e) 
    {
    }

    /**
     * Mouse Pressed
     * 
     * Necessary class due to implementation. Does nothing.
     */
    @Override
    public void mousePressed(MouseEvent e) 
    {
    }

    /**
     * Mouse Entered
     * 
     * Necessary class due to implementation. Does nothing.
     */
    @Override
    public void mouseEntered(MouseEvent e) 
    {
    }

    /**
     * Mouse Exited
     * 
     * Necessary class due to implementation. Does nothing.
     */
    @Override
    public void mouseExited(MouseEvent e) 
    {
    }

    /**
     * Mouse Dragged
     * 
     * Necessary class due to implementation. Does nothing.
     */
    @Override
    public void mouseDragged(MouseEvent e) 
    {

    }
}
