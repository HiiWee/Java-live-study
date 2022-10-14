package chapter15.week4;
// TODO 4주차 2번문제 미완료
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Example02 extends JFrame implements ActionListener {
    private JPanel topPanel, middlePanel, bottomPanel;
    private final String[] functions = {"7", "8", "9", "/", "sqrt", "4", "5", "6", "*", "%", "1", "2", "3", "-", "1/x", "0", "+/-", ".", "+", "="};

    public Example02() {
        Container ct = getContentPane();
        ct.setLayout(new GridLayout(3, 1));

        // 패널 만들기
        topPanel = new JPanel(new GridLayout(2, 1));
        middlePanel = new JPanel();
        bottomPanel = new JPanel(new GridLayout(4, 5));

        // top panel 설정
        JLabel resultAreaLabel = new JLabel("계산 결과 창");
        JTextField resultField = new JTextField(40);
        resultField.setEditable(false);
        topPanel.add(resultAreaLabel);
        topPanel.add(resultField);

        // middle panel 설정
        JLabel deleteButtonLabel = new JLabel("지우기 버튼");
        JButton deleteCharButton = new JButton("Backspace");
        JButton deleteAllButton = new JButton("C");
        deleteCharButton.addActionListener(this);
        deleteAllButton.addActionListener(this);
        middlePanel.add(deleteButtonLabel);
        middlePanel.add(deleteCharButton);
        middlePanel.add(deleteAllButton);

        // bottom panel 설정
        JButton[] buttons = new JButton[20];
        for (int i = 0; i < 20; i++) {
            buttons[i] = new JButton(functions[i]);
            buttons[i].addActionListener(this);
            bottomPanel.add(buttons[i]);
        }


        ct.add(topPanel);
        ct.add(middlePanel);
        ct.add(bottomPanel);

        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Example02();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        System.out.println(actionCommand);
    }
}
