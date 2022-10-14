package chapter19.week07;


import javax.swing.*;
import java.awt.*;

class Vibratable implements Runnable {
    private final int[] row = {-5, 0, 5, 0};
    private final int[] col = {0, 5, 0, -5};
    private JLabel vibrateLabel;

    public Vibratable(JLabel vibrateLabel) {
        this.vibrateLabel = vibrateLabel;
    }

    @Override
    public void run() {

        int count = 0;
        while (true) {
            count++;
            Point location = vibrateLabel.getLocation();
            vibrateLabel.setLocation(location.x + col[count % 4], location.y + row[count % 4]);

            // overflow 방지
            if (count == Integer.MAX_VALUE) {
                count = 0;
            }
        }
    }
}
public class Example03 extends JFrame {

    public Example03() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        JLabel vibrateLabel = new JLabel("진동 레이블");
        vibrateLabel.setFont(new Font("Gothic", Font.PLAIN, 30));
        vibrateLabel.setHorizontalAlignment(JLabel.CENTER); //JLabel 가운데 정렬
        Container ct = getContentPane();
        ct.add(vibrateLabel);
        setVisible(true);

        // 쓰레드 시작
        Thread th = new Thread(new Vibratable(vibrateLabel));
        th.start();

    }

    public static void main(String[] args) {
        new Example03();
    }

}
