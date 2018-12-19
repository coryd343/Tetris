/*
 * TCSS 305 C Winter
 * 
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import model.Block;
import model.TetrisPiece;

/**
 * Panel which contains the grid and game pieces.
 * 
 * @author coryd343
 * @version 1.0
 */
public class GamePanel extends JPanel implements Observer {
    
    /**
     * The auto-generated serial ID.
     */
    private static final long serialVersionUID = -3017598231597093346L;
    
    /**
     * The default width of the game board expressed in block units.
     */
    private static final int DEFAULT_COLUMNS = 10;
    
    /**
     * The default height of the game board expressed in block units.
     */
    private static final int DEFAULT_ROWS = 20;
    
    /**
     * The width of the padding around this JPanel.
     */
    private static final int DEFAULT_PADDING = 10;
    /**
     * Contains the state of the board.
     */
    
    private List<Block[]> myBoardData;
    
    /**
     * The preferred size of the panel.
     */
    private final Dimension DEFAULT_SIZE = new Dimension(200, 400);
    
    /**
     * The width of the game board expressed in block units.
     */
    private final int myNumCols;
    
    /**
     * The height of the game board expressed in block units.
     */
    private final int myNumRows;
    
    
    
    /**
     * The background color.
     */
    private final Color BACKGROUND_COLOR = Color.GRAY;
    
    /**
     * An array of line2D's which make up the grid.
     */
    //private final List<Line2D> myGrid;
    
    /**
     * The flag indicating whether or not to draw the grid.
     */
    private boolean myGridOn;
    
    /**
     * The default constructor. Initializes to the default board size.
     */
    public GamePanel() {
        super();
        myNumCols = DEFAULT_COLUMNS;
        myNumRows = DEFAULT_ROWS;
        setBorder(BorderFactory.createEmptyBorder(DEFAULT_PADDING, DEFAULT_PADDING,
                                                  DEFAULT_PADDING, DEFAULT_PADDING));
        setPreferredSize(DEFAULT_SIZE);
        setSize(DEFAULT_SIZE);
        setBackground(BACKGROUND_COLOR);
        myGridOn = true;
        //myGrid = setupGrid();
    }
    
    /**
     * The constructor with arguments for a game board of custom dimensions.
     * @param theCols The number of columns wide the board will be.
     * @param theRows The number of rows tall the board will be.
     */
    public GamePanel(final int theCols, final int theRows) {
        super();
        myNumCols = theCols;
        myNumRows = theRows;
        setBorder(BorderFactory.createEmptyBorder(DEFAULT_PADDING, DEFAULT_PADDING,
                                                  DEFAULT_PADDING, DEFAULT_PADDING));
        setPreferredSize(DEFAULT_SIZE);
        setSize(DEFAULT_SIZE);
        setBackground(BACKGROUND_COLOR);
        //myGrid = setupGrid();
    }
    

    /**
     * Draws the grid and tiles.
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g = (Graphics2D) theGraphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        g.setPaint(Color.BLACK);
        
        /*for (final Line2D line : myGrid) {
            g.draw(line);
        }*/
        
        if (myBoardData != null) {
            for (int i = myBoardData.size() - 1; i >= 0; i--) {
                final Block[] row = myBoardData.get(i);
                int col = 0;
                for (final Block aBlock : row) {
                    
                    if (aBlock == null) {
                        //empty space
                        g.setPaint(Color.DARK_GRAY);
                        g.fill(new Rectangle((this.getWidth() / myNumCols) * col,
                                             this.getHeight() - (this.getHeight() / myNumRows)
                                             * (i + 1),
                                             getBlockSide(), getBlockSide()));
                        if (myGridOn) {
                            g.setPaint(Color.WHITE);
                            g.draw(new Rectangle((this.getWidth() / myNumCols) * col,
                                                 this.getHeight() - (this.getHeight() / myNumRows)
                                                 * (i + 1),
                                                 getBlockSide(), getBlockSide()));
                        }
                    } else {
                        //filled block
                        g.setPaint(Color.CYAN);
                        g.fill(new Rectangle((this.getWidth() / myNumCols) * col,
                                             this.getHeight() - (this.getHeight() / myNumRows)
                                             * (i + 1),
                                             getBlockSide(), getBlockSide()));
                        g.setPaint(Color.WHITE);
                        g.draw(new Rectangle((this.getWidth() / myNumCols) * col,
                                             this.getHeight() - (this.getHeight() / myNumRows)
                                             * (i + 1),
                                             getBlockSide(), getBlockSide()));
                    }
                    col++;
                }
            }
        }
    }
    
    /**
     * Draws the grid lines matching the dimensions of the board.
     * @return An ArrayList filled with lines which span the length and width of this panel.
     */
    public final List<Line2D> setupGrid() {
        final ArrayList<Line2D> grid = new ArrayList<Line2D>();
        //Vertical grid lines.
        for (int i = 1; i < myNumCols; i++) {
            final Point top = new Point((getWidth() / myNumCols) * i, 0);
            final Point bottom = new Point((getWidth() / myNumCols) * i, getHeight());
            grid.add(new Line2D.Double(top, bottom));
        }
        
        //Horizontal grid lines.
        for (int i = 1; i < myNumRows; i++) {
            final Point left = new Point(0, (getHeight() / myNumRows) * i);
            final Point right = new Point(getWidth(), (getHeight() / myNumRows) * i);
            grid.add(new Line2D.Double(left, right));
        }
        return grid;
    }
    
    /**
     * Determines the side length of each block. Compares the width and height of this 
     * GamePanel and uses the smaller dimension divided by the number of rows or columns.
     * @return The side length of one block on the grid.
     */
    private int getBlockSide() {
        final int result;
        if (getWidth() > getHeight()) {
            result = getHeight() / myNumRows;
        } else {
            result = getWidth() / myNumCols;
        }
        return result;
    }
    
    /**
     * Sets the color for each different block shape.
     * @return aColor The color selected for the current block.
     */
    private Color setColor() {
        Color aColor;
        switch (myCurrentPiece.getBlock()) {
            case I: aColor = Color.CYAN;
                    break;
            case J: aColor = Color.BLUE;
                    break;
            case L: aColor = Color.ORANGE;
                    break;
            case O: aColor = Color.YELLOW;
                    break;
            case S: aColor = Color.GREEN;
                    break;
            case Z: aColor = Color.RED;
                    break;
            case T: aColor = Color.MAGENTA;
                    break;
            default: aColor = Color.WHITE;
                    break;
        }
        return aColor;
    }
    
    /**
     * Inverts the value of the flag for drawing the grid.
     */
    public void toggleGrid() {
        myGridOn = !myGridOn;
        repaint();
    }

    @Override
    public void update(final Observable theBoard, final Object theBoardData) {
        if (theBoardData instanceof List<?>) {
            myBoardData = (List<Block[]>) theBoardData;
        }
        repaint();
    }
}
