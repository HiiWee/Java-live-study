package thread;

import java.util.Map;
import java.util.Set;

class TestThreadGroup extends Thread {
    @Override
    public void run() {
        for (long i = 0; i < 2500000000L; i++) {
            System.out.println("running : " + this.getState());
        }
    }
}

public class ThreadGroupDemo {
    public static void main(String[] args) {
        TestThreadGroup th1 = new TestThreadGroup();
        th1.setName("TestThreadGroup");
        th1.setDaemon(true);
        System.out.println("before start: " + th1.getState());
        th1.start();

        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
        Set<Thread> threads = map.keySet();

        for (Thread thread : threads) {
            System.out.println("Name : " + thread.getName() + (thread.isDaemon() ? "[Daemon]" : "[Main]"));
            System.out.println("\t" + "Group : " + thread.getThreadGroup().getName());
            System.out.println();
        }

        System.out.println("after start : " + th1.getState());
    }
}
