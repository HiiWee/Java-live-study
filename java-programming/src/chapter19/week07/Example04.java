package chapter19.week07;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 쓰레드1
 * 임의의 크기를 가진 라벨이 지속적으로 좌측에서 우측으로 이동함
 * 이동하다가 우측끝으로 이동하게 되면 다시 좌측처음으로 넘어와 우측으로 이동
 * 위 과정을 무한 반복
 * <p>
 * 쓰레드 2
 * 임의의 위치에 멈춰있는 라벨이 있음
 * 만약 키보드를 아무거나 누르게 되면 y값이 감소하여 위로 계속 이동
 * 중간에 쓰레드 1의 좌표와 겹치게 되면 충돌하고 쓰레드1과 쓰레드2가 리게임 초기화됨
 * 만약 부딪히지 않는다면 초기화되어 다시 처음자리로 돌아온다.
 */

class Movable implements Runnable {
    private JLabel target;

    public Movable(JLabel target) {
        this.target = target;
    }

    @Override
    public void run() {
        Point startingPoint = target.getLocation();
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            target.setLocation(target.getLocation().x + 5, target.getLocation().y);
            if (target.getLocation().x == 600) {
                target.setLocation(startingPoint);
            }
        }

    }
}

class Shootable implements Runnable {
    private JLabel target;
    private JLabel bulletLabel;
    private boolean flag;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Shootable(JLabel target, JLabel bulletLabel) {
        this.target = target;
        this.bulletLabel = bulletLabel;
    }

    @Override
    public void run() {
        Point startingPoint = bulletLabel.getLocation();
        Point targetStartingPoint = target.getLocation();
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (flag) {
                bulletLabel.setLocation(bulletLabel.getLocation().x, bulletLabel.getLocation().y - 5);
                if (bulletLabel.getLocation().y == 0) {
                    bulletLabel.setLocation(startingPoint);
                    flag = false;
                }

                if (hit()) {

                }
            }
        }
    }

    private boolean hit() {
        Point targetLocation = target.getLocation();
        Point bulletLabelLocation = bulletLabel.getLocation();

        if (targetLocation.getX() == bulletLabelLocation.getX()) {

        }


        return false;
    }
}

public class Example04 extends JFrame {

    public Example04() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        Container ct = getContentPane();
        ct.setLayout(null);
        // 타겟 만들기
        JLabel target = new JLabel(new ImageIcon("rock.jpg"));
        target.setSize(80, 80);
        ct.add(target);

        // 시작 지점 만들기
        JLabel startLabel = new JLabel();
        startLabel.setSize(50, 50);
        startLabel.setOpaque(true);
        startLabel.setLocation(275, 525);
        startLabel.setBackground(Color.BLACK);
        ct.add(startLabel);

        // 총알 만들기
        JLabel bulletLabel = new JLabel();
        bulletLabel.setSize(10, 10);
        bulletLabel.setOpaque(true);
        bulletLabel.setLocation(295, 515);
        bulletLabel.setBackground(Color.RED);
        ct.add(bulletLabel);


        setVisible(true);

        Thread moveThread = new Thread(new Movable(target));
        Shootable shootable = new Shootable(target, bulletLabel);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startLabel.setFocusable(true);
                shootable.setFlag(true);
            }
        });
        Thread shootThread = new Thread(shootable);
        moveThread.start();
        shootThread.start();


    }

    public static void main(String[] args) {
        new Example04();
    }

}
