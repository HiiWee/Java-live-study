package middletermtask.problem02.view;


import middletermtask.problem02.enums.ActionCategory;

import javax.swing.*;
public class NormalButton extends JButton {
    public NormalButton(String buttonName, ActionCategory actionCategory) {
        this.setText(buttonName);
        this.setActionCommand(String.valueOf(actionCategory));
    }
}
