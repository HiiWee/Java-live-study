package chapter15.week4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Example03 extends JFrame implements ActionListener {
    private final String[] imgName = {"rock.jpg", "paper.jpg", "scissors.jpg"};
    private final String[] name = {"rock", "paper", "scissors"};
    private final JButton[] buttons = new JButton[3];
    private final JLabel userResult = new JLabel();
    private final JLabel computerResult = new JLabel();
    private final JLabel gameResult = new JLabel();

    public Example03() {
        Container ct = getContentPane();
        ct.setLayout(new GridLayout(2, 1));
        JPanel top = new JPanel();
        JPanel bottom = new JPanel();

        top.setBackground(Color.gray);
        bottom.setBackground(Color.YELLOW);

        for (int i = 0; i < 3; i++) {
            buttons[i] = new JButton(new ImageIcon(imgName[i]));
            buttons[i].addActionListener(this);
            buttons[i].setActionCommand(name[i]);
            top.add(buttons[i]);

        }

        bottom.add(userResult);
        bottom.add(computerResult);
        bottom.add(gameResult);

        ct.add(top);
        ct.add(bottom);

        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userSelect = e.getActionCommand();
        int computerIdx = (int) (Math.random() * 3);
        String computerSelect = name[computerIdx];

        if (userSelect.equals("rock") && computerSelect.equals("rock")
                || userSelect.equals("paper") && computerSelect.equals("paper")
                || userSelect.equals("scissors") && computerSelect.equals("scissors")) {
            showResult(userSelect, computerSelect, "  Same!!!");
        } else if (userSelect.equals("rock") && computerSelect.equals("scissors")
                || userSelect.equals("paper") && computerSelect.equals("rock")
                || userSelect.equals("scissors") && computerSelect.equals("paper")) {
            showResult(userSelect, computerSelect, "  Computer!!!");
        } else {
            showResult(userSelect, computerSelect, "  ME!!!");
        }
    }

    private void showResult(String userSelect, String computerSelect, String result) {
        userResult.setIcon(new ImageIcon(userSelect + ".jpg"));
        userResult.setText("me");
        computerResult.setIcon(new ImageIcon(computerSelect + ".jpg"));
        computerResult.setText("com");
        gameResult.setText(result);
        gameResult.setForeground(Color.RED);
    }

    public static void main(String[] args) {
        new Example03();
    }
}
