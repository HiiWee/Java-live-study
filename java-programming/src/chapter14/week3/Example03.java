package chapter14.week3;

import javax.swing.*;
import java.awt.*;

public class Example03 extends JFrame {
    public Example03() {
        JPanel pt, pb, pt1, pt2, pt3, pb1, pb2;
        Container ct = getContentPane();
        ct.setLayout(new GridLayout(2, 1));

        pt = new JPanel();
        pb = new JPanel();
        pt.setLayout(new GridLayout(1, 3));
        pb.setLayout(new GridLayout(1, 2));

        pt1 = new JPanel();
        pt1.setLayout(new GridLayout(3, 1));
        JCheckBox red = new JCheckBox("Red", true);
        JCheckBox green = new JCheckBox("Green");
        JCheckBox blue = new JCheckBox("Blue");

        pt2 = new JPanel();
        pt2.setLayout(new GridLayout(3, 1));
        JRadioButton a = new JRadioButton("A형");
        JRadioButton b = new JRadioButton("B형");
        JRadioButton c = new JRadioButton("C형");

        pt3 = new JPanel();
        pt3.setLayout(new FlowLayout());
        JButton select = new JButton("선 택");
        JButton cancel = new JButton("취 소");

        pb1 = new JPanel();
        pb1.setLayout(new FlowLayout());
        JTextArea textArea = new JTextArea(null, "자료입력공간", 35, 50);

        pb2 = new JPanel();
        pb2.setLayout(new FlowLayout(0, 0, 100));
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("1구간 지역");
        comboBox.addItem("2구간 지역");
        comboBox.addItem("3구간 지역");
        comboBox.addItem("4구간 지역");

        pt1.add(red);
        pt1.add(green);
        pt1.add(blue);
        pt2.add(a);
        pt2.add(b);
        pt2.add(c);
        pt3.add(select);
        pt3.add(cancel);

        pb1.add(textArea);
        pb2.add(comboBox);

        pt.add(pt1);
        pt.add(pt2);
        pt.add(pt3);

        pb.add(pb1);
        pb.add(pb2);

        ct.add(pt);
        ct.add(pb);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setTitle("Panel Test");
        setVisible(true);
    }

    public static void main(String[] args) {
        new Example03();
    }

}
