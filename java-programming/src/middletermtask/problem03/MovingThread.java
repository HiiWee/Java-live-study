package middletermtask.problem03;

class MovingThread extends Thread {
    private SnakeLabel[] snake;

    public MovingThread(SnakeLabel[] snake) {
        this.snake = snake;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            SnakeLabel head = snake[0];
            switch (head.getDirection()) {
                case NORTH:
                    head.setLocation(head.getX(), head.getY() - 5);
                    break;
                case SOUTH:
                    head.setLocation(head.getX(), head.getY() + 5);
                    break;
                case EAST:
                    head.setLocation(head.getX() + 5, head.getY());
                    break;
                case WEST:
                    head.setLocation(head.getX() - 5, head.getY());
                    break;
            }

            // BODY_SIZE만큼 멀어지면 1칸씩 당긴다.
            if (Math.abs(head.getX() - snake[1].getX()) >= SnakeLabel.BODY_SIZE
                    || Math.abs(head.getY() - snake[1].getY()) >= SnakeLabel.BODY_SIZE) {
                for (int i = 9; i >= 1; i--) {
                    if (i == 1) {
                        snake[i].setLocation(head.getX(), head.getY());
                    } else {
                        snake[i].setLocation(snake[i - 1].getX(), snake[i - 1].getY());
                    }
                }
            }
        }
    }
}
