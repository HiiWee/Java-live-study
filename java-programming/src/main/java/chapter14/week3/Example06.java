package chapter14.week3;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Example06 extends JFrame {
    public Example06() {
        super("Random Labels");
        Container ct = getContentPane();
        ct.setLayout(null);
        JLabel[] jLabels = new JLabel[20];
        for (int i = 0; i < 20; i++) {
            jLabels[i] = new JLabel(Integer.toString(i));
            jLabels[i].setOpaque(true);
            jLabels[i].setBackground(Color.BLUE);
            jLabels[i].setSize(10, 10);
            jLabels[i].setLocation(getRandomNum(), getRandomNum());
            ct.add(jLabels[i]);
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
    }

    public int getRandomNum() {
        Random random = new Random();
        int num = random.nextInt(201);
        return num + 50;
    }

    public static void main(String[] args) {
        new Example06();
    }
}
