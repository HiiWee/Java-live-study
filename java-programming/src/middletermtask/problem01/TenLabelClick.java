package middletermtask.problem01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class TenLabelClick extends JFrame {
    // Magic Number 상수 선언
    private final int CONTAINER_SIZE = 400;
    private final int MAX_COORDINATE = 350;

    private Container ct;
    private JLabel[] gameLabels = new JLabel[10];
    private int index;

    public TenLabelClick() {
        ct = getContentPane();
        ct.setLayout(null);

        for (int i = 0; i < 10; i++) {
            gameLabels[i] = new JLabel(Integer.toString(i));
            gameLabels[i].setOpaque(true);
            gameLabels[i].setForeground(Color.magenta);
            gameLabels[i].setLocation(getRandomNum(MAX_COORDINATE), getRandomNum(MAX_COORDINATE));
            gameLabels[i].setSize(15, 15);
            gameLabels[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    JLabel label = (JLabel) e.getSource();
                    if (label.getText().equals(Integer.toString(index))) {
                        label.setVisible(false);
                        index++;
                        if (index == 10) {
                            rearrange();
                        }
                    }
                }

                // 10번까지 전부 눌렀을경우 레이블 위치 재배치
                private void rearrange() {
                    for (int i = 0; i < 10; i++) {
                        gameLabels[i].setLocation(getRandomNum(MAX_COORDINATE), getRandomNum(MAX_COORDINATE));
                        gameLabels[i].setVisible(true);
                        index = 0;
                    }
                }
            });
            ct.add(gameLabels[i]);
        }

        setTitle("Ten 레이블 클릭");
        setSize(CONTAINER_SIZE, CONTAINER_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public int getRandomNum(int maxCoordinates) {
        Random random = new Random();
        return random.nextInt(maxCoordinates);
    }

    public static void main(String[] args) {
        new TenLabelClick();
    }

}