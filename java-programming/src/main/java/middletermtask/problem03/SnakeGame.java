package middletermtask.problem03;

import middletermtask.problem03.enums.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeGame extends JFrame {
    // 초기 좌표
    private final int[] INIT_LOCATION = {315, 280, 245, 210, 175, 140, 105, 70, 35, 0};
    private SnakeLabel[] snake;

    public SnakeGame() {
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = getContentPane();
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        container.setLayout(null);
        initSnake(container);
        container.add(backgroundPanel);
        SnakeLabel head = snake[0];

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        head.setDirection(Direction.NORTH);
                        break;
                    case KeyEvent.VK_DOWN:
                        head.setDirection(Direction.SOUTH);
                        break;
                    case KeyEvent.VK_LEFT:
                        head.setDirection(Direction.WEST);
                        break;
                    case KeyEvent.VK_RIGHT:
                        head.setDirection(Direction.EAST);
                        break;
                }
            }
        });
        setVisible(true);
        MovingThread movingThread = new MovingThread(snake);
        movingThread.start();
    }

    private void initSnake(Container container) {
        snake = new SnakeLabel[10];
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                snake[i] = new SnakeLabel("head");
            } else {
                snake[i] = new SnakeLabel("body");
            }
            snake[i].setOpaque(false);
            snake[i].setLocation(INIT_LOCATION[i], 0);
            container.add(snake[i]);
        }
    }
}
