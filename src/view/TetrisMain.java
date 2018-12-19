/*
 * TCSS 305 C Winter
 */
package view;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Launches the Tetris program.
 * @author cdavis343
 * @version 1.0
 */
public final class TetrisMain {
    
    /**
     * Sets the look and feel to metal.
     * @throws IllegalStateException
     */
    private TetrisMain() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (final IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (final InstantiationException ex) {
            ex.printStackTrace();
        } catch (final ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        throw new IllegalStateException();
    }
    
        

    /**
     * The main method. This calls the start() method from the GUI which starts new game.
     * @param theArgs the command line arguments.
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow();
            }
        });
    }
}
