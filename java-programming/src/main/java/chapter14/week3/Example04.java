package chapter14.week3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Example04 extends JFrame implements ActionListener {
    public JLabel jLabel;
    public JButton[] bt;

    public Example04() {
        Container ct = getContentPane();
        ct.setLayout(new FlowLayout());
        jLabel = new JLabel("");
        bt = new JButton[4];

        for (int i = 0; i < 4; i++) {
            bt[i] = new JButton((i + 1) + "학년");
            bt[i].addActionListener(this);
            ct.add(bt[i]);
        }

        ct.add(jLabel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Event Test1");
        setSize(300, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        Example04 example04 = new Example04();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jLabel.setText(e.getActionCommand());
    }
}

