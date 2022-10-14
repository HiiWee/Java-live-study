package chapter15.week4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Example04 extends JFrame {
    private Container ct;
    private JLabel[] gameLabels = new JLabel[10];
    private int index;

    public Example04() {
        ct = getContentPane();
        ct.setLayout(null);

        for (int i = 0; i < 10; i++) {
            gameLabels[i] = new JLabel(Integer.toString(i));
            gameLabels[i].setOpaque(true);
            gameLabels[i].setForeground(Color.magenta);
            gameLabels[i].setLocation(getRandomNum(), getRandomNum());
            gameLabels[i].setSize(15, 15);
            gameLabels[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    JLabel label = (JLabel) e.getSource();
                    if (label.getText().equals(Integer.toString(index))) {
                        label.setVisible(false);
                        index++;
                        if (index == 10) {
                            for (int i = 0; i < 10; i++) {
                                gameLabels[i].setLocation(getRandomNum(), getRandomNum());
                                gameLabels[i].setVisible(true);
                                index = 0;
                            }
                        }
                    }
                }
            });
            ct.add(gameLabels[i]);
        }

        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public int getRandomNum() {
        Random random = new Random();
        int num = random.nextInt(251);
        return num;
    }

    public static void main(String[] args) {
        new Example04();
    }

}