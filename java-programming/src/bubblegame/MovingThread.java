package bubblegame;

import javax.swing.*;
import java.awt.*;

class MovingThread extends Thread {
    private Container container;
    private JLabel imageLabel;

    public MovingThread(Container container, JLabel imageLabel) {
        this.container = container;
        this.imageLabel = imageLabel;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            imageLabel.setLocation(imageLabel.getX(), imageLabel.getY() - 5);

            if (imageLabel.getY() <= 0) {
                container.remove(imageLabel);
                break;
            }
        }
    }
}
