package chapter19.week07;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


class CirclePanel extends JPanel {
    private int x, y;
    private boolean flag;

    public CirclePanel() {
        setVisible(false);
    }

    // 화면에 그리기, repaint() 사용 가능
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.magenta);
        graphics.drawOval(x, y, 50, 50);
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}


class CircleThread extends Thread {
    private final Container container;
    private final CirclePanel circlePanel;

    public CircleThread(Container container, CirclePanel circlePanel) {
        this.container = container;
        this.circlePanel = circlePanel;
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (circlePanel.getFlag()) {
                circlePanel.setVisible(true);
                int x = ((int) (Math.random() * container.getWidth()));
                int y = ((int) (Math.random() * container.getHeight()));
                circlePanel.setX(x);
                circlePanel.setY(y);
                circlePanel.repaint();

            }
        }
    }
}

public class Example01 extends JFrame {
    public Example01() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        CirclePanel circlePanel = new CirclePanel();
        circlePanel.setSize(400, 400);
        c.setLayout(null);
        c.add(circlePanel);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                circlePanel.setFlag(true);
            }
        });

        setSize(400, 400);
        setVisible(true);

        Thread th = new CircleThread(c, circlePanel);
        th.start();
    }

    public static void main(String[] args) {
        new Example01();
    }
}

