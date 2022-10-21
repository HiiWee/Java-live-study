package middletermtask.problem02.view;

import javax.swing.*;
import java.awt.*;

public class TextAreaPanel extends JPanel {
    private JLabel subjectLabel;
    private JTextArea inputArea;

    private TextAreaPanel(JLabel subjectLabel, JTextArea inputArea) {
        this.subjectLabel = subjectLabel;
        this.inputArea = inputArea;
    }

    public TextAreaPanel(String subject, int rows, int columns) {
        this(new JLabel(subject), new JTextArea(rows, columns));
        this.setLayout(new GridLayout(2, 1));
        this.add(subjectLabel);
        this.add(inputArea);
    }

    public String getInputAreaText() {
        return inputArea.getText();
    }
}
