package middletermtask.problem03;

import middletermtask.problem03.enums.Direction;

import javax.swing.*;

class SnakeLabel extends JLabel {
    public static final int BODY_SIZE = 35;
    private final String PREFIX = "src/middletermtask/problem03/images/";
    private Direction direction = Direction.EAST; // 초기값

    public SnakeLabel(String part) {
        setSize(BODY_SIZE, BODY_SIZE);
        setIcon(new ImageIcon(PREFIX + part + ".png"));
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
