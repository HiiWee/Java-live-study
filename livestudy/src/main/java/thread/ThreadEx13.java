package thread;

import javax.swing.*;

public class ThreadEx13 {
    public static void main(String[] args) {
        ThreadEx13_1 th1 = new ThreadEx13_1();
        th1.start();

        String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
        System.out.println("입력하신 값은 " + input + " 입니다.");
        th1.interrupt(); // 인터럽트를 동작해 interrupted 상태가 true가 된다.
        System.out.println("isInterrupted() : " + th1.isInterrupted());
    }

    static class ThreadEx13_1 extends Thread {

        public void run() {
            int i = 10;
            while (i != 0 && !isInterrupted()) {
                System.out.println(i--);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    interrupt();
                }
            }
            System.out.println("카운트가 종료되었습니다.");
        }
    }

}
