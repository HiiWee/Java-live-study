package bubblegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class BubbleContainer extends JFrame {
    private final int CONTAINER_SIZE = 600;
    private Container container;

    public BubbleContainer() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(CONTAINER_SIZE, CONTAINER_SIZE);
        container = getContentPane();
        container.setLayout(null);
        container.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                ImageLabel imageLabel = new ImageLabel();
                imageLabel.setLocation(point);
                container.add(imageLabel);
                MovingThread thread = new MovingThread(container, imageLabel);
                thread.start();
            }
        });

        setVisible(true);

    }
}
