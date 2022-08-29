package thread;

public class ThreadDemo extends Thread{
    public void run() {
        for (int i = 0; i < 5; i++) {
            for(long l = 0; l < 2500000000L; l++);
            System.out.println(1);
        }
    }
}


class ThreadDemo2 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            for(long l = 0; l < 2000000000L; l++);
            System.out.println(2);
        }
    }
}


class ThreadMain {
    public static void main(String[] args) {
        Thread t1 = new ThreadDemo();

        Runnable r = new ThreadDemo2();
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();
        System.out.println("All thread is ended");


    }
}

class DaemonThread extends Thread {
    public void run() {
        while (true) {
            for (long l = 0; l < 2500000000L; l++);
            System.out.println("Run Daemon Thread!!");
        }
    }


    static class DaemonThreadRun {
        static long currentTime;

        public static void main(String[] args) {
            Thread th1 = new DaemonThread();
            th1.setDaemon(true);

            currentTime = System.currentTimeMillis(); // 현재 시간

            th1.start();
            try {
                Thread.sleep(5 * 1000); // 5 초간 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("실행 종료 시간 : " + (System.currentTimeMillis() - currentTime));
        }
    }
}