package middletermtask.problem02.view;

import middletermtask.problem02.enums.ActionCategory;
import javax.swing.*;
import java.awt.*;

public class SwingView extends JFrame {
    private final int CONTAINER_WIDTH = 350;
    private final int CONTAINER_HEIGHT = 300;

    private Container container;
    private TextFieldPanel nameTextFieldPanel;
    private TextFieldPanel phoneTextFieldPanel;
    private TextAreaPanel addressTextAreaPanel;

    private NormalButton saveButton = new NormalButton("저장", ActionCategory.SAVE);
    private NormalButton searchButton = new NormalButton("검색", ActionCategory.SEARCH);
    private NormalButton exitButton = new NormalButton("종료", ActionCategory.EXIT);


    public SwingView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Address Book");
        setSize(CONTAINER_WIDTH, CONTAINER_HEIGHT);
        container = getContentPane();
        container.setLayout(new FlowLayout());
        nameTextFieldPanel = new TextFieldPanel("이름:", 20);
        phoneTextFieldPanel = new TextFieldPanel("전화번호:", 20);
        addressTextAreaPanel = new TextAreaPanel("주소:", 4, 20);

        container.add(nameTextFieldPanel);
        container.add(phoneTextFieldPanel);
        container.add(addressTextAreaPanel);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(saveButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(exitButton);
        container.add(buttonPanel);

        setVisible(true);
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public String getPersonName() {
        return nameTextFieldPanel.getInputFieldText();
    }

    public String getPersonPhone () {
        return phoneTextFieldPanel.getInputFieldText();
    }

    public String getPersonAddress() {
        return addressTextAreaPanel.getInputAreaText();
    }

    public void setPersonNameField(String text) {
        nameTextFieldPanel.setInputFieldText(text);
    }

    public void setPersonPhoneField(String text) {
        phoneTextFieldPanel.setInputFieldText(text);
    }

    public void setPersonAddressField(String text) {
        addressTextAreaPanel.setInputAreaText(text);
    }
}
