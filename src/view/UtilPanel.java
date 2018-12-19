/*
 * TCSS 305C Winter.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Contains the game info widgets.
 * @author cdavis343
 * @version 1.0
 */
public class UtilPanel extends JPanel {
    
    /**
     * The generated serial ID.
     */
    private static final long serialVersionUID = -7773448802175084550L;

    /**
     * The preferred size of the panel.
     */
    private final Dimension DEFAULT_SIZE = new Dimension(200, 400);
    
    /**
     * The width of the padding around this JPanel.
     */
    private static final int DEFAULT_PADDING = 10;
    
    /**
     * Background color of the panel.
     */
    private static final Color BG_COLOR = new Color(200, 200, 200);
    
    /**
     * The default constructor. Initializes to the default board size.
     */
    public UtilPanel() {
        super();
        //setLayout(new GridLayout(1,1));
        setBorder(new EmptyBorder(DEFAULT_PADDING, DEFAULT_PADDING,
                                  DEFAULT_PADDING, DEFAULT_PADDING));
        setPreferredSize(DEFAULT_SIZE);
        setBackground(BG_COLOR);
    }
}
