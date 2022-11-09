package chapter17.week6;

public class Example02 {

    static class NumThread implements Runnable {
        private int lastNum;

        public NumThread(int lastNum) {
            this.lastNum = lastNum;
        }

        @Override
        public void run() {
            for (int i = 1; i <= lastNum; i++) {
                System.out.print(i + ",");
            }

        }
    }

    static class CharThread implements Runnable {
        private char token;

        public CharThread(char token) {
            this.token = token;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 50; i++) {
                System.out.print(token + ",");
            }

        }
    }

    public static void main(String[] args) {
        Thread th1 = new Thread(new NumThread(50));
        Thread th2 = new Thread(new CharThread('x'));
        Thread th3 = new Thread(new CharThread('y'));

        th1.start();
        th2.start();
        th3.start();
    }

}
