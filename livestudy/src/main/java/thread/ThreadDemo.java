package thread;

public class ThreadDemo extends Thread{
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(getName());
        }
    }
}


class ThreadDemo2 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName());
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
    }
}