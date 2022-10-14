package chapter14.example;

import javax.swing.*;
import java.awt.*;

public class Ex14_5 extends JFrame {
    public Ex14_5() {
        Container ct = getContentPane();
        GridLayout gl = new GridLayout(4, 5, 10, 10);
        ct.setLayout(gl);
        for (int i = 1; i <= 20; i++) {
            ct.add(new JButton("버튼" + i));
        }
        setTitle("GridLayout Test1");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

class Ex14_5_Main {
    public static void main(String[] args) {
        new Ex14_5();
    }
}
