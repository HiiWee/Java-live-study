package chapter19.example;

import chapter15.week4.Example02;

import javax.swing.*;
import java.awt.*;

class TimerRunnable implements Runnable {

    private JLabel timerLabel; // 타이머 값이 출력된 레이블

    public TimerRunnable(JLabel timerLabel) {
        this.timerLabel = timerLabel;
    }

    @Override
    public void run() {
        int n = 0; // 타이머 카운트 값
        while (true) {
            timerLabel.setText(Integer.toString(n));
            n++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return; // 예외 발생시 스레드 저ㅗㅇ료
            }
        }
    }

}

public class Ex01 extends JFrame {
    public Ex01() {
        setTitle("Runnable을 구현한 타이머 스레드 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        // 타이머 값을 출력할 레이블 생성
        JLabel timerLabel = new JLabel();
        timerLabel.setFont(new Font("Gothic", Font.ITALIC, 80));
        c.add(timerLabel);

        TimerRunnable runnable = new TimerRunnable(timerLabel);
        Thread th = new Thread(runnable);

        setSize(250, 150);
        setVisible(true);

        th.start();

    }

    public static void main(String[] args) {
        new Ex01();
    }
}
