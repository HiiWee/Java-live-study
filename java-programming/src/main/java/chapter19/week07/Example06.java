package chapter19.week07;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 */

class TextFieldPanel extends JPanel implements ActionListener {
    private JTextField jTextField;
    private String inputText;

    public TextFieldPanel() {
        setSize(400,50);
        setBackground(Color.gray);
        setLocation(0, 550);
        jTextField = new JTextField(30);
        add(jTextField);
        jTextField.addActionListener(this);
    }

    public String getInputText() {
        return inputText;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = e.getActionCommand();
        inputText = text;
        jTextField.setText("");
    }
}

class MoveThread extends Thread {
    private List<String> wordList;
    private JLabel textLabel;
    private TextFieldPanel textFieldPanel;
    private JPanel dropWordPanel;
    private Point startingPoint;

    public MoveThread(List<String> wordList, JLabel textLabel, TextFieldPanel textFieldPanel, Point startingPoint, JPanel dropWordPanel) {
        this.wordList = wordList;
        this.textLabel = textLabel;
        this.textFieldPanel = textFieldPanel;
        this.startingPoint = startingPoint;
        this.dropWordPanel = dropWordPanel;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            textLabel.setLocation(textLabel.x, textLabel.y + 5);

            if (textLabel.getY() == 550) {

            }
        }
    }

}



public class Example06 extends JFrame {
    public Example06() throws FileNotFoundException {
        List<String> wordList = new ArrayList<>();
        Scanner sc = new Scanner(new FileReader("words.txt"));
        while (sc.hasNext()) {
            wordList.add(sc.nextLine());
        }
        sc.close();


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 620);
        Container ct = getContentPane();
        ct.setLayout(null);

        JPanel dropWordPanel = new JPanel();
        dropWordPanel.setSize(400, 550);

        TextFieldPanel textFieldPanel = new TextFieldPanel();

        ct.add(dropWordPanel);
        ct.add(textFieldPanel);

        setVisible(true);

    }

    public static void main(String[] args) throws FileNotFoundException {
        new Example06();
    }
}
