package thread;

public class ThreadEx20 {
    public static void main(String[] args) {
        ThreadEx20_1 gc = new ThreadEx20_1();
        gc.setDaemon(true);
        gc.start();

        int requiredMemory = 0;

        for (int i = 0; i < 20; i++) {
            requiredMemory = (int) (Math.random() * 10) * 20;

            if (gc.getFreeMemory() < requiredMemory || gc.getFreeMemory() < gc.getTotalMemory() * 0.4) {
                gc.interrupt();
                try {
                    gc.join(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            gc.usedMemory += requiredMemory;
            System.out.println("usedMemory : " + gc.usedMemory);
        }
    }
}

class ThreadEx20_1 extends Thread {
    final static int MAX_MEMORY = 1000;
    int usedMemory;

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000 * 10);
            } catch (InterruptedException e) {
                System.out.println("Awaken by interrupt()");
            }

            gc();
            System.out.println("Garbage Collected. Free Memory : " + getFreeMemory());

        }
    }

    public void gc() {
        usedMemory -= 300;
        if (usedMemory < 0) {
            usedMemory = 0;
        }
    }

    public int getTotalMemory() {
        return MAX_MEMORY;
    }

    public int getFreeMemory() {
        return MAX_MEMORY - usedMemory;
    }
}
