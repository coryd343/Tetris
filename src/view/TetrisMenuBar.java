/*
 * TCSS 305C Winter
 */
package view;

import com.sun.glass.events.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * Creates a fully-loaded menu bar.
 * @author cdavis343
 * @version 1.0
 */
public class TetrisMenuBar extends JMenuBar {
    
    /**
     * Auto-generated serial number.
     */
    private static final long serialVersionUID = -8436264661459312329L;
    
    /**
     * A reference to the GUI.
     */
    private MainWindow myMain;
    
    /**
     * Run-of-the-mill default constructor for the menu bar used in Tetris.
     * @param theMainWindow The MainWindow class containing the GUI.
     */
    public TetrisMenuBar(final MainWindow theMainWindow) {
        super();
        myMain = theMainWindow;
        populate();        
    }
    
    /**
     * Adds the menus and menu items to the menu bar.
     */
    private void populate() {
      //declare menus.
        final JMenu game = new JMenu("Game");
        final JMenu options = new JMenu("Options");
        final JMenu help = new JMenu("Help");
        
        //set mnemonics.
        game.setMnemonic(KeyEvent.VK_G);
        options.setMnemonic(KeyEvent.VK_O);
        help.setMnemonic(KeyEvent.VK_H);
        
        //add items to "Game" menu
        final JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                if (myMain.gameIsOver()) {
                    myMain.start();
                }
            }
        });
        newGame.setMnemonic(KeyEvent.VK_N);
        game.add(newGame);
        final JMenuItem endGame = new JMenuItem("End Game");
        endGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myMain.endGame();                
            }
        });
        endGame.setMnemonic(KeyEvent.VK_E);
        game.add(endGame);
        final JMenuItem pause = new JMenuItem("Pause");
        pause.setMnemonic(KeyEvent.VK_P);
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myMain.togglePauseTimer();
            }
        });
        game.add(pause);
        
        //add items to "Options" menu
        final JMenuItem grid = new JMenuItem("Toggle Grid");
        grid.setMnemonic(KeyEvent.VK_T);
        grid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myMain.toggleGrid();
            }
        });
        options.add(grid);
        
        //add items to "Help" menu
        final JMenuItem scoring = new JMenuItem("Scoring");
        scoring.setMnemonic(KeyEvent.VK_S);
        scoring.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(myMain, "<html><body><u>Scoring Algorithm</u>"
                    + "<table border='5'><th><td>1 Line</td><td>2 Lines"
                    + "</td><td>3 Lines</td><td>4 Lines</td></th><tr><td>Level 1</td>"
                    + "<td>40</td><td>100</td><td>300</td><td>1200</td></tr><tr><td>"
                    + "Level 2</td><td>80</td><td>200</td><td>600</td><td>2400</td></tr>"
                    + "<tr><td>Level 3</td><td>120</td><td>300</td><td>900</td><td>3600</td>"
                    + "</tr><tr><td>Level N</td><td>40*N</td><td>100*N</td><td>300*N</td>"
                    + "<td>1200*N</td></tr>",
                                              "Scoring",
                                              JOptionPane.INFORMATION_MESSAGE);
            }
        });
        help.add(scoring);
        
        //add menus to menu bar
        add(game);
        add(options);
        add(help);
    }

}
