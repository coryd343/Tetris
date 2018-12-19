/*
 * TCSS 305C
 */

package view;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.TetrisPiece;

/**
 * This panel contains the score, level and the number of lines cleared.
 * @author coryd343
 * @version 1.0
 */
public class GameDataPanel extends JPanel implements Observer {
    
    /**
     * The default length of the text fields.
     */
    private static final int DEFAULT_LENGTH = 8;
    
    /**
     * The number of rows for the grid layout.
     */
    private static final int DEFAULT_ROWS = 4;
    
    /**
     * The number of columns for the grid layout.
     */
    private static final int DEFAULT_COLS = 2;
    
    /**
     * The generated serial ID.
     */    
    private static final long serialVersionUID = 6891694874353100455L;
    
    /**
     * The score for freezing a single block on the board.
     */
    private static final int SCORE_BLOCK = 4;
    
    /**
     * The score for completing a single line.
     */
    private static final int SCORE_ONE = 40;
    
    /**
     * The score for completing two lines.
     */
    private static final int SCORE_TWO = 100;
    
    /**
     * The score for completing three lines.
     */
    private static final int SCORE_THREE = 300;
    
    /**
     * The score for completing four lines.
     */
    private static final int SCORE_FOUR = 1200;
    
    /**
     * The int value of one line cleared.
     */
    private static final int ONE_LINE = 1;
    
    /**
     * The int value of two lines cleared.
     */
    private static final int TWO_LINES = 2;
    
    /**
     * The int value of three lines cleared.
     */
    private static final int THREE_LINES = 3;
    
    /**
     * The int value of four lines cleared.
     */
    private static final int FOUR_LINES = 4;
    
    /**
     * The number of lines cleared to reach the highest level.
     */
    private static final int LEVEL_CAP = 45;
    
    /**
     * The number of lines cleared to reach each level.
     */
    private static final int NEXT_LEVEL = 5;
    
    /**
     * The up-to-date score for the current game.
     */
    private Integer myScore;
    
    /**
     * The up-to-date number of lines cleared for the current game.
     */
    private Integer myLines;
    
    /**
     * The up-to-date difficulty level for the current game.
     */
    private Integer myLevel;
    
    /**
     * The number of lines still remaining to reach the next level.
     */
    private Integer myLvCountDown;
    
    /**
     * Text field containing the store.
     */
    private final JTextField myScoreField;
    
    /**
     * Text field containing the number of lines cleared.
     */
    private final JTextField myLinesField;
    
    /**
     * Text field containing the current difficulty level.
     */
    private final JTextField myLevelField;    
    
    /**
     * Text field containing the number of lines required to reach the next level.
     */
    private final JTextField myLvCountDownField;
    
    /**
     * Constructor without arguments.
     */
    public GameDataPanel() {
        super();
        myScore = 0;
        myLines = 0;
        myLevel = 1;
        myLvCountDown = NEXT_LEVEL;
        myScoreField = new JTextField(myScore.toString(), DEFAULT_LENGTH);        
        myLinesField = new JTextField(myLines.toString(), DEFAULT_LENGTH);
        myLevelField = new JTextField(myLevel.toString(), DEFAULT_LENGTH);
        myLvCountDownField = new JTextField(myLvCountDown.toString(), DEFAULT_LENGTH);
        initialize();
    }
    
    /**
     * Sets the properties of the LayoutManager and text fields.
     */
    private void initialize() {        
        setLayout(new GridLayout(DEFAULT_ROWS, DEFAULT_COLS));
        
        myScoreField.setFocusable(false);
        myLinesField.setFocusable(false);
        myLevelField.setFocusable(false);
        myLvCountDownField.setFocusable(false);
        
        add(new JLabel("Score: "));
        add(myScoreField);
        add(new JLabel("Cleared: "));
        add(myLinesField);
        add(new JLabel("Level: "));
        add(myLevelField);
        add(new JLabel("To Next LV: "));
        add(myLvCountDownField);
    }
    
    /**
     * Sets the level to the correct value.
     */
    private void updateLevel() {
        if (myLines <= LEVEL_CAP) {
            myLevel = (myLines / NEXT_LEVEL) + 1;
            myLvCountDown = NEXT_LEVEL - (myLines % NEXT_LEVEL);
        } else { //Maximum level (10) already reached.
            myLvCountDown = 0;
        }
        myLevelField.setText(myLevel.toString());
        myLvCountDownField.setText(myLvCountDown.toString());
    }
    
    /**
     * Updates the score after each row completed.
     * @param theRows The array of completed rows received from the Board object.
     */
    private void calcScore(final Integer[] theRows) {
        if (theRows.length == ONE_LINE) {
            myScore += SCORE_ONE * myLevel;
        } else if (theRows.length == TWO_LINES) {
            myScore += SCORE_TWO * myLevel;
        } else if (theRows.length == THREE_LINES) {
            myScore += SCORE_THREE * myLevel;
        } else if (theRows.length == FOUR_LINES) {
            myScore += SCORE_FOUR * myLevel;
        }
        myScoreField.setText(myScore.toString());
    }
    
    /**
     * Returns the current level of the game in progress.
     * @return myLevel The current level of the game in progress.
     */
    public int getLevel() {
        return myLevel;
    }
    
    /**
     * Sets the game data back to the default values for a new game.
     */
    public void newGame() {
        myScore = 0;  
        myLines = 0;
        myLevel = 1;
        
        myScoreField.setText(myScore.toString());
        myLinesField.setText(myLines.toString());
        myLevelField.setText(myLevel.toString());
    }
    
    /**
     * Gets an array of completed lines from the board.
     */
    @Override
    public void update(final Observable theBoard, final Object theData) {
        if (theData instanceof Integer[]) {
            myLines += ((Integer[]) theData).length;
            myLinesField.setText(myLines.toString());
            updateLevel();
            calcScore((Integer[]) theData);
        } else if (theData instanceof TetrisPiece) {
            myScore += SCORE_BLOCK;
            myScoreField.setText(myScore.toString());
        }
    }
}
