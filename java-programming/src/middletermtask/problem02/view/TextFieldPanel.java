package middletermtask.problem02.view;

import javax.swing.*;

public class TextFieldPanel extends JPanel {
    private JLabel subjectLabel;
    private JTextField inputField;

    // 임의 호출 방지
    private TextFieldPanel(JLabel subjectLabel, JTextField inputField) {
        this.subjectLabel = subjectLabel;
        this.inputField = inputField;
    }
    public TextFieldPanel(String subject, int columns) {
        this(new JLabel(subject), new JTextField(columns));

        this.add(subjectLabel);
        this.add(inputField);
    }

    public JTextField getInputField() {
        return inputField;
    }
}
