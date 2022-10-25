package os.producerandconsumer;

public class Buffer {
    int[] buf;
    int size, count, in, out;

    Buffer(int size) {
        buf = new int[size];
        this.size = size;
        count = in = out = 0;
    }

    synchronized void insert(int item) {
        while (count == size) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        buf[in] = item;
        in = (in + 1) % size;
        notify();
        count++;
    }

    synchronized int remove() {
        while (count == 0) {
            try {
                System.out.println("소비할 수 없습니다.");
                wait();
            } catch (InterruptedException e) {
                System.out.println("pass");
                throw new RuntimeException(e);
            }
        }
        int item = buf[out];
        out = (out + 1) % size;
        count--;
        notify();
        return item;
    }
}

class Producer extends Thread {
    Buffer b;
    int N;

    Producer(Buffer b, int N) {
        this.b = b;
        this.N = N;
    }

    public void run() {
        for (int i = 0; i < N; i++) {
            System.out.println(this + " +" + i);
            b.insert(i);
        }
    }
}

class Consumer extends Thread {
    Buffer b;
    int N;

    public Consumer(Buffer b, int n) {
        this.b = b;
        N = n;
    }

    public void run() {
        int item;
        for (int i = 0; i < N; i++) {
            item = b.remove();
            System.out.println(this + " -" + item);
        }
    }
}

class Test {
    public static void main(String[] args) {
        Buffer b = new Buffer(100);
        Producer p = new Producer(b, 200);
        Producer p2 = new Producer(b, 10);
        Consumer c = new Consumer(b, 5);
        c.start();
        p2.start();
        p.start();

        try {
            p.join();
            c.join();
            p2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("number of items in the buf is " + b.count);

    }
}
