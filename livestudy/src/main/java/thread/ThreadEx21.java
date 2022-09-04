package thread;

public class ThreadEx21 {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Thread thread1 = new ThreadEx21_1(calculator);
        Thread thread2 = new ThreadEx21_1(calculator);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 예상대로라면 20000이 출력되어야 한다.
        System.out.println("동기화 사용 X : " + calculator.getCount2());
        System.out.println("동기화 사용 O : " + calculator.getCount1());
    }
}

class Calculator {
    private int count1, count2;

    public void calcSum1() {
        synchronized (this) {
            count1 += 1;
        }
    }

    public void calcSum2() {
        count2 += 1;
    }

    public int getCount1() {
        return count1;
    }

    public int getCount2() {
        return count2;
    }
}


class ThreadEx21_1 extends Thread {
    private final Calculator calculator;

    public ThreadEx21_1(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            calculator.calcSum1();
            calculator.calcSum2();
        }
    }
}