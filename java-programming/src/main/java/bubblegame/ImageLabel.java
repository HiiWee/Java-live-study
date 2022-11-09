package bubblegame;

import javax.swing.*;

class ImageLabel extends JLabel {
    private final String PREFIX = "src/bubblegame/images/";
    private final int LABEL_SIZE = 60;
    private ImageIcon imageIcon;

    public ImageLabel() {
        imageIcon = new ImageIcon(PREFIX + "smile.png");
        setSize(LABEL_SIZE, LABEL_SIZE);
        setIcon(imageIcon);
    }
}
