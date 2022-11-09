package chapter14.week3;

import javax.swing.*;
import java.awt.*;

public class Example02 {
    public static void main(String[] args) {
        new SimpleGUI02();
    }
}

class SimpleGUI02 extends JFrame {
    public SimpleGUI02() {
        Container ct = getContentPane();
        ct.setLayout(new GridLayout(4, 5));

        for (int i = 1; i <= 10; i++) {
            JButton bt = new JButton("레이아웃" + i);
            ct.add(bt);
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("GridLayout Test");
        setSize(400, 300);
        setVisible(true);

    }
}