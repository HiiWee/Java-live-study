package chapter19.week07;


import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class Example02 extends JFrame {
    public Example02() {
        setTitle("시계 만들기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        JLabel timerLabel = new JLabel();
        timerLabel.setFont(new Font("Gothic", Font.ITALIC, 80));
        c.add(timerLabel);

        TimerLabelThread timerLabelThread = new TimerLabelThread(timerLabel);
        Thread th = new Thread(timerLabelThread);

        setSize(500, 300);
        setVisible(true);
        th.start();
    }

    public static void main(String[] args) {
        new Example02();
    }
}

class TimerLabelThread implements Runnable {
    private JLabel label;
    private final Calendar calendar = Calendar.getInstance();

    public TimerLabelThread(JLabel label) {
        this.label = label;
    }

    @Override
    public void run() {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        while (true) {
            String format = String.format("%02d:%02d:%02d", hour, minute, second);
            label.setText(format);
            second++;
            if (second == 60) {
                minute++;
                second = 0;
            }
            if (minute == 60) {
                hour++;
                minute = 0;
            }
            if (hour == 24) {
                hour = 0;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}