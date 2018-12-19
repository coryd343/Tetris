package control;

import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlConfigDialog extends JPanel {
    
    private JLabel controlDisplay;
    
    public ControlConfigDialog() {
        controlDisplay = new JLabel();
        controlDisplay.setText("Controls\nMove Left: Left Arrow"
                    + "\nMove Right: Right Arrow"
                    + "\nMove Down: Down Arrow"
                    + "\nDrop: Space Bar"
                    + "\nRotate Clockwise: Up Arrow"
                    + "\nRotate Counter Clockwise: F");
    }
}
