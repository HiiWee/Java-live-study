package chapter15.week4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Example01 {
    static class TextFieldPanel extends JPanel implements ActionListener {
        private JTextField jtf;
        private JTextArea jta;

        public TextFieldPanel() {
            jtf = new JTextField(20);
            jta = new JTextArea(5, 20);
            jta.setEditable(false);

            add(jtf);
            add(jta);

            jtf.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String text = e.getActionCommand();
            jta.append(text);
            jtf.setText("");
        }
    }

    static class RadioButtonPanel extends JPanel implements ActionListener {
        private final ButtonGroup buttonGroup = new ButtonGroup();
        private final String[] texts = {"JCheckBox", "JButton", "JComboBox", "JLabel"};
        private final JRadioButton[] jRadioButtons = new JRadioButton[4];
        private final JTextField jta = new JTextField(20);

        public RadioButtonPanel() {
            for (int i = 0; i < 4; i++) {
                jRadioButtons[i] = new JRadioButton(texts[i]);
                add(jRadioButtons[i]);
                jRadioButtons[i].addActionListener(this);
                buttonGroup.add(jRadioButtons[i]);
            }

            add(jta);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 4; i++) {
                if (jRadioButtons[i].isSelected()) {
                    jta.setText(e.getActionCommand());
                }
            }
        }
    }

    static class ComboPanel extends JPanel implements ItemListener {
        private JTextField jtf = new JTextField(20);

        public ComboPanel() {
            JComboBox jcb = new JComboBox();
            jcb.addItem("Flow");
            jcb.addItem("Grid");
            jcb.addItem("Border");
            jcb.addItem("Card");

            jtf.setEditable(false);

            add(jcb);
            add(jtf);

            jcb.addItemListener(this);
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            String item = (String) e.getItem();
            jtf.setText(item);
        }
    }

    static class CheckBoxPanel extends JPanel implements ItemListener {
        private final JCheckBox[] boxes = new JCheckBox[4];
        private final String[] text = {"객체지향", "Interpreter 사용", "높은 이식성", "Multithreaded 제공"};
        private JTextField textField = new JTextField(50);
        public CheckBoxPanel() {

            for (int i = 0; i < 4; i++) {
                boxes[i] = new JCheckBox(text[i]);
                boxes[i].addItemListener(this);
                add(boxes[i]);
            }
            textField.setEditable(false);
            add(textField);

        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < 4; i++) {
                if (boxes[i].isSelected()) {
                    sb.append(boxes[i].getText()).append(" ");
                }
            }
            textField.setText(sb.toString());
        }
    }


    static class CustomTabbedPane extends JFrame {

        public CustomTabbedPane() {
            Container ct = getContentPane();
            JTabbedPane jtp = new JTabbedPane();
            TextFieldPanel textFieldPanel = new TextFieldPanel();
            RadioButtonPanel radioButtonPanel = new RadioButtonPanel();
            ComboPanel comboPanel = new ComboPanel();
            CheckBoxPanel checkBoxPanel = new CheckBoxPanel();

            jtp.addTab("Text", textFieldPanel);
            jtp.addTab("Swing", radioButtonPanel);
            jtp.addTab("Layout", comboPanel);
            jtp.addTab("Java", checkBoxPanel);

            ct.add(jtp);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("GroupHolder Test");
            setSize(600, 200);
            setVisible(true);
        }

        public static void main(String[] args) {
            new CustomTabbedPane();
        }

    }


}
