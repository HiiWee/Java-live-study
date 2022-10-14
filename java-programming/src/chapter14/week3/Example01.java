package chapter14.week3;

import javax.swing.*;
import java.awt.*;

public class Example01 {
    public static void main(String[] args) {
        new SimpleGUI01();
    }
}

class SimpleGUI01 extends JFrame {
    public SimpleGUI01() {
        Container ct = getContentPane();
        ct.setLayout(new BorderLayout(10, 10));

        JButton bt1 = new JButton("버튼1");
        JButton bt2 = new JButton("버튼2");
        JButton bt3 = new JButton("버튼3");

        ct.add(bt1, BorderLayout.NORTH);
        ct.add(bt3, BorderLayout.SOUTH);
        ct.add(bt2, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("BorderLayout Test");
        setSize(400, 150);
        setVisible(true);

    }
}
