package operatingsystem.disk_scheduling.cscan;

import static operatingsystem.disk_scheduling.scan.ScanAlgorithm.MAX_TRACK_INDEX;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

class RandomGenerator {
    private RandomGenerator() {
    }

    public static int getRandomNumberInRange(int start, int end) {
        Random random = new Random();
        return random.nextInt(end - start) + start;
    }
}

public class CScan extends Thread {
    public static final int MAX_TRACK_INDEX = 199;
    private final List<Integer> left = new ArrayList<>();
    private final PriorityQueue<Integer> right = new PriorityQueue<>();
    private int headValue;
    private int distance;

    public CScan(final int headValue) {
        this.headValue = headValue;
    }

    @Override
    public void run() {
        try {
            startScan();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void startScan() throws InterruptedException {
        startRightScan();
    }

    private void startLeftScan() throws InterruptedException {
        int previewHeadValue;
        int leftSize = left.size();
        Collections.sort(left);
        while (leftSize-- > 0) {
            previewHeadValue = headValue;
            if (!right.isEmpty() && left.get(0) > right.peek()) {
                headValue = right.poll();
                distance += Math.abs(headValue - previewHeadValue);
                printMovingStatus(previewHeadValue);
                Thread.sleep(400);
                continue;
            }
            headValue = left.get(0);
            left.remove(0);
            distance += Math.abs(headValue - previewHeadValue);
            printMovingStatus(previewHeadValue);
            Thread.sleep(400);
        }
        if (!right.isEmpty()) {
            System.out.println("\n change\n");
            startRightScan();
            return;
        }
        distance += MAX_TRACK_INDEX - headValue;
        System.out.println(headValue + " -> " + MAX_TRACK_INDEX + " = " + distance);
        headValue = MAX_TRACK_INDEX;
    }


    private void startRightScan() throws InterruptedException {
        int previewHeadValue;
        while (!right.isEmpty()) {
            previewHeadValue = headValue;
            headValue = right.poll();
            right.remove(0);
            distance += Math.abs(headValue - previewHeadValue);
            printMovingStatus(previewHeadValue);
            Thread.sleep(400);
        }
        distance += Math.abs(MAX_TRACK_INDEX - headValue);
        System.out.println(headValue + " -> " + MAX_TRACK_INDEX + " = " + distance);
        headValue = MAX_TRACK_INDEX;
        if (!left.isEmpty()) {
            distance += 199;
            System.out.println("\nback to start " + MAX_TRACK_INDEX + " -> " + 0 + "\n");
            headValue = 0;
            startLeftScan();
        }
    }

    private void printMovingStatus(final int previewHeadValue) {
        System.out.print(previewHeadValue + " -> " + headValue);
        System.out.println(" = " + distance);
    }

    public void initializeValue(final List<Integer> input) {
        for (int value : input) {
            if (headValue < value) {
                right.add(value);
                continue;
            }
            left.add(value);
        }
    }

    public int getResult() {
        return distance;
    }

    public void addValue(final int randomTrackValue) {
        System.out.println("input random value: " + randomTrackValue);
        if (headValue < randomTrackValue) {
            right.add(randomTrackValue);
            return;
        }
        left.add(randomTrackValue);
    }
}

class RandomTrackThread extends Thread {
    private int time;
    private CScan cScan;

    public RandomTrackThread(final int time, final CScan CScan) {
        this.time = time;
        this.cScan = CScan;
    }

    @Override
    public void run() {
        while (time-- > 0) {
            int randomBreakTime = RandomGenerator.getRandomNumberInRange(1, 1000);
            int randomTrackValue = RandomGenerator.getRandomNumberInRange(0, MAX_TRACK_INDEX);
            try {
                Thread.sleep(randomBreakTime);
                cScan.addValue(randomTrackValue);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Application {
    public static void main(String[] args) throws InterruptedException {
        CScan CScan = new CScan(50);
        RandomTrackThread randomTrackThread = new RandomTrackThread(10, CScan);
        List<Integer> input = List.of(105, 180, 40, 120, 10, 125, 65, 70);
        CScan.initializeValue(input);

        CScan.start();
        randomTrackThread.start();

        CScan.join();
        randomTrackThread.join();
        System.out.println("결과: " + CScan.getResult());

    }
}
