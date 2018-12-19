/*
 * TCSS 305C Winter
 */
package view;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import control.TetrisKeyListener;
import model.Board;

/**
 * The main JFrame with all its components.
 * @author cdavis343
 * @version 1.0
 *
 */
public class MainWindow extends JFrame implements Observer {

    /**
     * The delay used by the timer.
     */
    private static final int INIT_DELAY = 1000;
    
    /**
     * The auto-generated serial ID.
     */
    private static final long serialVersionUID = 8505832448531165414L;

    /**
     * The panel which contains the tetris blocks.
     */
    private GamePanel myGamePanel;
    
    /**
     * The left-side panel which contains in-game info.
     */
    private UtilPanel myLPanel;
    
    /**
     * The right-side panel which contains in-game info.
     */
    private UtilPanel myRPanel;
    
    /**
     * The display of the next piece.
     */
    private PreviewWindow myPreview;
    
    /**
     * The panel containing game data.
     */
    private GameDataPanel myHUD;
    
    /**
     * The Board object which contains the state of the tetris game.
     */
    private Board myBoard;
    
    /**
     * A Swing timer used to advance the game.
     */
    private Timer myTimer;
    
    /**
     * The delay used by the timer to progress the game.
     */
    private int myStepInterval;
    
    /**
     * Determines whether or not the game is paused.
     */
    private boolean myPauseValue;
    
    /**
     * Determines whether or not the current game is still playable.
     */
    private boolean myGameOver;
    
    /**
     * The icon to indicate a Game Over.
     */
    private ImageIcon myIcon;
    
    
    /**
     * Your garden-variety no-argument constructor.
     * @throws HeadlessException The French nobleman who got away.
     */
    public MainWindow() throws HeadlessException {
        super();
        initialize();
    }
    
    /**
     * Constructor which sets the title of the JFrame.
     * @param theName The text to appear at the top of the JFrame.
     */
    public MainWindow(final String theName) {
        super(theName);
        initialize();
    }    
    
    /**
     * Loads the main JFrame and its components.
     */
    public final void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);
        setVisible(true);
        setJMenuBar(new TetrisMenuBar(this));
        
        myGameOver = true;
        myIcon = new ImageIcon("./images/stickdeath.jpg");
        myGamePanel = new GamePanel();
        myLPanel = new UtilPanel();
        myRPanel = new UtilPanel();
        myPreview = new PreviewWindow();
        myHUD = new GameDataPanel();
        
        myRPanel.add(new JLabel("Next Piece"));
        myRPanel.add(myPreview);
        myRPanel.add(new JLabel("<html><body>Controls<p>Move Left: Left Arrow<br>"
                    + "Move Right: Right Arrow<br>"
                    + "Move Down: Down Arrow<br>"
                    + "Drop: Space Bar<br>"
                    + "Rotate Clockwise: Up Arrow<br>"
                    + "Rotate Counter Clockwise: F<br>"
                    + "Pause: Escape</body></html>"));
        myLPanel.add(myHUD);
        myStepInterval = INIT_DELAY;
        myTimer = new Timer(myStepInterval, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myBoard.step();
            };
        });
        myBoard = new Board();
        myBoard.addObserver(this);
        myBoard.addObserver(myGamePanel);
        myBoard.addObserver(myPreview);
        myBoard.addObserver(myHUD);
        addKeyListener(new TetrisKeyListener(this));
        add(myGamePanel, BorderLayout.CENTER);
        add(myLPanel, BorderLayout.WEST);
        add(myRPanel, BorderLayout.EAST);
        pack();
    }
    
    /**
     * Toggles the function of the timer. If it is running, the timer is stopped.
     * Otherwise, the timer is started.
     */
    public void togglePauseTimer() {
        if (!myGameOver) {
            if (myTimer.isRunning()) {
                myTimer.stop();
                myPauseValue = true;
            } else {
                myTimer.start();
                myPauseValue = false;
            }
        }
    }
    
    /**
     * Updates the GUI on each tick of the timer.
     */
    @Override
    public void update(final Observable theBoard, final Object theData) {        
        //System.out.println(theData.toString());
        if (theData instanceof Boolean) {
            //trigger game over.
            endGame();
            myPauseValue = true;
            JOptionPane.showMessageDialog(this, "Well, this is the end...",
                                          "Game Over",
                                          JOptionPane.INFORMATION_MESSAGE,
                                          myIcon);
        } else if (theData instanceof Integer[]) {
            speedUp();
        }
    }
    
    /**
     * Starts the Tetris program.
     */
    public void start() {
        myBoard.newGame();
        myTimer.setDelay(INIT_DELAY);
        myTimer.start();
        myGameOver = false;
        myPauseValue = false;
        myHUD.newGame();
    }
    
    /**
     * Returns a reference to the Board object.
     * @return The board object.
     */
    public Board getBoard() {
        return myBoard;
    }
    
    /**
     * Returns true if the game is paused.
     * @return The value of the flag triggered by the pause command.
     */
    public boolean isPaused() {
        return myPauseValue;
    }
    
    /**
     * Returns the boolean flag indicating whether or not a game is in progress.
     * @return The boolean flag for a game in progress.
     */
    public boolean gameIsOver() {
        return myGameOver;
    }
    
    /**
     * Discontinues the current game in progress.
     */
    public void endGame() {
        myGameOver = true;
        myTimer.stop();
    }
    
    /**
     * Turns the grid on and off.
     */
    public void toggleGrid() {
        if (!myGameOver) {
            myGamePanel.toggleGrid();
        }
    }
    
    /**
     * Sets the timer's delay according to the current level.
     */
    private void speedUp() {
        myStepInterval = INIT_DELAY / myHUD.getLevel();
        myTimer.setDelay(myStepInterval);
    }
}
