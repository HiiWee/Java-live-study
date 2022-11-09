package chapter14.week3;

import javax.swing.*;
import java.awt.*;

public class Example05 extends JFrame {
    private final String[] blueStr =
            {"BackSpace", "", "",
                    "7", "8", "9",
                    "4", "5", "6",
                    "1", "2", "3",
                    "0", "+/-", "."
            };
    private final String[] redStr =
            {"CE", "C", "/",
                    "sqrt", "X", "%",
                    "-", "1/x", "+",
                    "="
            };

    public Example05() {
        Container ct = getContentPane();
        ct.setLayout(new BorderLayout());

        JTextField textField = new JTextField(35);
        textField.setText("0.");
        ct.add(textField, BorderLayout.NORTH);

        // 하단 패널 추가
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new FlowLayout());
        ct.add(totalPanel);

        // 왼쪽 패널에 파란색 버튼 추가
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(5, 3));
        JButton[] leftButtons = new JButton[15];
        for (int i = 0; i < 15; i++) {
            leftButtons[i] = new JButton(blueStr[i]);
            leftButtons[i].setBackground(Color.yellow);
            leftButtons[i].setForeground(Color.BLUE);
            leftPanel.add(leftButtons[i]);
        }
        totalPanel.add(leftPanel, BorderLayout.WEST);


        // 오른쪽 패널에 빨간색 버튼 추가
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(5, 2));
        JButton[] rightButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            rightButtons[i] = new JButton(redStr[i]);
            rightButtons[i].setBackground(Color.YELLOW);
            rightButtons[i].setForeground(Color.RED);
            rightPanel.add(rightButtons[i]);
        }
        totalPanel.add(rightPanel, BorderLayout.EAST);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 220);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Example05();
    }

}
