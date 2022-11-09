package chapter19.week07;


import javax.swing.*;
import java.awt.*;

/**
 * 눈이 내리는 쓰레드를 구헌해보자
 * 눈이 내리는 쓰레드 30개?
 * <p>
 * [스레드]
 * 스레드는 임의의 시작좌표를 배정받음 (x값 임의, y값은 0)
 * 스레드는 일정하게 내려온다.
 * 만약 컨테이너의 크기를 벗어나면 스레드는 다시 초기로 돌아간다.
 */

class SnowPanel extends JPanel {
    private final int SNOW_SIZE = 5;
    private Point p;

    public void setP(Point p) {
        this.p = p;
    }

    // 화면에 그리기, repaint() 사용 가능
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.WHITE);
        graphics.drawOval(p.x, p.y, SNOW_SIZE, SNOW_SIZE);
    }
}

class SnowThread extends Thread {

    @Override
    public void run() {
    }
}
public class Example05 extends JFrame {
    public Example05() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        Container ct = getContentPane();
        ct.setLayout(new FlowLayout());

        SnowPanel snow = new SnowPanel();
        snow.setP(new Point(20, 30));

        ct.add(snow);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Example05();
    }
}

