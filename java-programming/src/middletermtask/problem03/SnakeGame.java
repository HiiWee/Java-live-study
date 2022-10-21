package middletermtask.problem03;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;

class GamePanel extends JPanel {
    private Image background = new ImageIcon("src/middletermtask/problem03/images/background.jpg").getImage();


    public void paint(Graphics graphics) {
        graphics.drawImage(background, 0, 0, null);

    }

    public GamePanel() {
        setSize(640, 480);
    }
}

class Snake {
    private final int BODY_SIZE = 35;
    private final String PREFIX = "src/middletermtask/problem03/images/";
    private final int[] INIT_LOCATION = {315, 280, 245, 210, 175, 140, 105, 70, 35, 0};
    private JLabel[] bodyLabels = new JLabel[10];
    private Coordinate current;

    public Snake(Container container) {
        for (int i = 0; i < 10; i++) {
            bodyLabels[i] = new JLabel();
            bodyLabels[i].setSize(BODY_SIZE, BODY_SIZE);
            bodyLabels[i].setOpaque(false);
            bodyLabels[i].setBackground(Color.gray);
            bodyLabels[i].setLocation(INIT_LOCATION[i], 0);
            if (i == 0) {
                bodyLabels[i].setIcon(new ImageIcon(PREFIX + "head.png"));
            } else {
                bodyLabels[i].setIcon(new ImageIcon(PREFIX + "body.png"));
            }
            container.add(bodyLabels[i]);
        }
    }

    public void moveSnake() {
        Coordinate poll = MovingThread.queue.poll();
        if (poll != null) {
            current = poll;
            switch (poll.getDirection()) {
                case NORTH:
                    bodyLabels[0].setLocation(bodyLabels[0].getX(), bodyLabels[0].getY() - 5);
                case SOUTH:
                    bodyLabels[0].setLocation(bodyLabels[0].getX(), bodyLabels[0].getY() + 5);
                case EAST:
                    bodyLabels[0].setLocation(bodyLabels[0].getX() + 5, bodyLabels[0].getY());
                case WEST:
                    bodyLabels[0].setLocation(bodyLabels[0].getX() - 5, bodyLabels[0].getY());
            }
            for (int i = 9; i >= 1; i--) {
                bodyLabels[i].setLocation(bodyLabels[i - 1].getX(), bodyLabels[i - 1].getY());
            }
        }

    }

    public JLabel getHead() {
        return bodyLabels[0];
    }

    public void follow(Point location) {


        for (int i = 9; i >= 1; i--) {
            if (i == 1) {
                bodyLabels[i].setLocation(location.x, location.y);
            } else {

                bodyLabels[i].setLocation(bodyLabels[i - 1].getX(), bodyLabels[i - 1].getY());
            }
        }
        print();
    }


    private void print() {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (int i = 0; i < 10; i++) {
            sb.append(bodyLabels[i].getX() + " " + bodyLabels[i].getY()).append("\n");
        }
        sb.append("]\n");

        System.out.println(sb);
    }
}

enum Direction {
    NORTH, SOUTH, EAST, WEST
}

class Coordinate {
    private int x;
    private int y;


    private Direction direction;

    public Coordinate(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }
}

class MovingThread extends Thread {
    private Snake snake;
    public static final Queue<Coordinate> queue = new LinkedList<>();

    public MovingThread(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void run() {
        Coordinate current = new Coordinate(snake.getHead().getX(), snake.getHead().getY(), Direction.EAST);
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            JLabel head = snake.getHead();
            Coordinate poll = queue.poll();
            if (poll != null) {
                current = poll;
            }

            switch (current.getDirection()) {
                case NORTH:
                    Point preLocation = head.getLocation();
                    head.setLocation(head.getX(), head.getY() - 5);
                    snake.follow(preLocation);
                    break;
                case SOUTH:
                    preLocation = head.getLocation();
                    head.setLocation(head.getX(), head.getY() + 5);
                    snake.follow(preLocation);
                    break;
                case EAST:
                    preLocation = head.getLocation();
                    head.setLocation(head.getX() + 5, head.getY());
                    snake.follow(preLocation);
                    break;
                case WEST:
                    preLocation = head.getLocation();
                    head.setLocation(head.getX() - 5, head.getY());
                    snake.follow(preLocation);
                    break;
            }
//            queue.stream().forEach(o -> System.out.println(o.getX() + " " + o.getY() + " " + o.getDirection()));
        }
    }
}

public class SnakeGame extends JFrame {
    public SnakeGame() {
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = getContentPane();
        Snake snake = new Snake(container);
        GamePanel gamePanel = new GamePanel();
        container.add(gamePanel);

        JLabel head = snake.getHead();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        MovingThread.queue.add(new Coordinate(head.getX(), head.getY(), Direction.NORTH));
                        break;
                    case KeyEvent.VK_DOWN:
                        MovingThread.queue.add(new Coordinate(head.getX(), head.getY(), Direction.SOUTH));
                        break;
                    case KeyEvent.VK_LEFT:
                        MovingThread.queue.add(new Coordinate(head.getX(), head.getY(), Direction.WEST));
                        break;
                    case KeyEvent.VK_RIGHT:
                        MovingThread.queue.add(new Coordinate(head.getX(), head.getY(), Direction.EAST));
                        break;

                }
            }
        });

        setVisible(true);

        MovingThread movingThread = new MovingThread(snake);
        movingThread.start();
    }

    public static void main(String[] args) {
        new SnakeGame();
    }
}
