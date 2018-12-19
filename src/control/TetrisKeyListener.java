/*
 * TCSS 305C Winter
 */
package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import view.MainWindow;

/**
 * Sends key commands from the GUI to the board object.
 * @author coryd343
 * @version 1.0
 */
public class TetrisKeyListener extends KeyAdapter {
    
    /**
     * A reference to the MainWindow object which utilizes this listener. Tight coupling much?
     */
    private final MainWindow myParent;
    
    /**
     * The keyboard assignment for the command to move the current piece left.
     */
    private final int myLeft;
    
    /**
     * The keyboard assignment for the command to move the current piece right.
     */
    private final int myRight;
    
    /**
     * The keyboard assignment for the command to move the current piece down.
     */
    private final int myDown;
    
    /**
     * The keyboard assignment for the command to drop the current piece to the bottom.
     */
    private final int myDrop;
    
    /**
     * The keyboard assignment for the command to rotate the current piece clockwise.
     */
    private final int myRotateCW;
    
    /**
     * The keyboard assignment for the command to rotate the current piece counter-clockwise.
     */
    private final int myRotateCCW;
    
    /**
     * The keyboard assignment for the command to pause the game.
     */
    private final int myPause;
    
    /**
     * Constructor which takes a reference to the JFrame containing the GUI.
     * @param theParent The component which utilizes this class.
     */
    public TetrisKeyListener(final MainWindow theParent) {
        super();
        myParent = theParent;
        myLeft = KeyEvent.VK_LEFT;
        myRight = KeyEvent.VK_RIGHT;
        myDown = KeyEvent.VK_DOWN;
        myDrop = KeyEvent.VK_SPACE;
        myRotateCW = KeyEvent.VK_UP;
        myRotateCCW = KeyEvent.VK_F;
        myPause = KeyEvent.VK_ESCAPE;
    }
    
    /**
     * Registers key events and performs the appropriate action.
     */
    @Override
    public void keyPressed(final KeyEvent theEvent) {
        //These controls will function regardless of whether or not the game is paused.
        if (theEvent.getKeyCode() == myPause) {
            myParent.togglePauseTimer();
        }
        
        //These controls will only function if the game is not paused.
        if (!myParent.isPaused() && !myParent.gameIsOver()) {
            if (theEvent.getKeyCode() == myLeft) {
                myParent.getBoard().left();
            } else if (theEvent.getKeyCode() == myRight) {
                myParent.getBoard().right();
            } else if (theEvent.getKeyCode() == myDown) {
                myParent.getBoard().down();
            } else if (theEvent.getKeyCode() == myDrop) {
                myParent.getBoard().drop();
            } else if (theEvent.getKeyCode() == myRotateCW) {
                myParent.getBoard().rotateCW();
            } else if (theEvent.getKeyCode() == myRotateCCW) {
                myParent.getBoard().rotateCCW();
            }
        }
    }
}
