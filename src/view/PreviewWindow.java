/*
 * TCSS 305C Winter
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import model.TetrisPiece;

/**
 * JPanel which displays the next piece which will appear on the board.
 * @author coryd343
 * @version 1.0
 */
public class PreviewWindow extends JLabel implements Observer {
    
    /**
     * The generated serial ID.
     */
    private static final long serialVersionUID = -5554720408377889604L;
    
    /**
     * The default length of one side of a tetris block.
     */
    private static final int SQUARE_SIZE = 20;
    
    /**
     * The default size of the panel.
     */
    private static final Dimension DEFAULT_SIZE = new Dimension(120, 100);
    
    
    
    /**
     * The next tetris piece to be instantiated on the board.
     */
    private TetrisPiece myNextPiece;
    
    /**
     * An unassuming no-argument constructor.
     */
    public PreviewWindow() {
        super();
        setPreferredSize(DEFAULT_SIZE);
        setBackground(Color.DARK_GRAY);
    }
    
    /**
     * Updates the next piece preview.
     */
    @Override
    public void update(final Observable theBoard, final Object theData) {
        if (theData instanceof TetrisPiece) {
            myNextPiece = (TetrisPiece) theData;
            repaint();
        }
    }
    
    /**
     * Draws the next tetris piece.
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g = (Graphics2D) theGraphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        g.setPaint(Color.DARK_GRAY);
        g.fillRect(0, 0, (int) DEFAULT_SIZE.getWidth(), (int) DEFAULT_SIZE.getHeight());
        if (myNextPiece != null) {
            for (final model.Point p : myNextPiece.getPoints()) {                
                g.setPaint(Color.CYAN);
                g.fill(new Rectangle(p.x() * SQUARE_SIZE + SQUARE_SIZE,
                                     this.getHeight() - p.y() * SQUARE_SIZE - SQUARE_SIZE,
                                     SQUARE_SIZE, SQUARE_SIZE));
                g.setPaint(Color.BLACK);
                g.draw(new Rectangle(p.x() * SQUARE_SIZE + SQUARE_SIZE,
                                     this.getHeight() - p.y() * SQUARE_SIZE - SQUARE_SIZE,
                                     SQUARE_SIZE, SQUARE_SIZE));
            }
        }
    }

}
