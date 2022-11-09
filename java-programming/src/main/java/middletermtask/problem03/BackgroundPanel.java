package middletermtask.problem03;

import javax.swing.*;
import java.awt.*;

class BackgroundPanel extends JPanel {
    private Image background = new ImageIcon("src/middletermtask/problem03/images/background.jpg").getImage();

    public void paint(Graphics graphics) {
        graphics.drawImage(background, 0, 0, null);
    }

    public BackgroundPanel() {
        setLayout(null);
        setSize(640, 480);
    }
}
