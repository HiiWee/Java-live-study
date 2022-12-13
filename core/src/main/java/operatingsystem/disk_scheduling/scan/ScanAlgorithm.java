package operatingsystem.disk_scheduling.scan;

import static operatingsystem.disk_scheduling.scan.ScanAlgorithm.MAX_TRACK_INDEX;

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

public class ScanAlgorithm extends Thread {
    public static final int MAX_TRACK_INDEX = 199;
    private final PriorityQueue<Integer> leftQueue = new PriorityQueue<>(Collections.reverseOrder());
    private final PriorityQueue<Integer> rightQueue = new PriorityQueue<>();
    private int headValue;
    private String direction;
    private int distance;

    public ScanAlgorithm(final int headValue, String direction) {
        this.headValue = headValue;
        this.direction = direction;
    }

    @Override
    public void run() {
        try {
            startScan(direction);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void startScan(final String direction) throws InterruptedException {
        if (direction.equals("left")) {
            startLeftScan();
        }
        if (direction.equals("right")) {
            startRightScan();
        }
    }

    private void startLeftScan() throws InterruptedException {
        int previewHeadValue;
        while (!leftQueue.isEmpty()) {
            previewHeadValue = headValue;
            headValue = leftQueue.poll();
            distance += Math.abs(headValue - previewHeadValue);
            printMovingStatus(previewHeadValue);
            Thread.sleep(400);
        }
        distance += headValue;
        System.out.println(headValue + " -> " + 0 + " = " + distance);
        headValue = 0;
        if (!rightQueue.isEmpty()) {
            System.out.println("\nturn right\n");
            startRightScan();
        }
    }


    private void startRightScan() throws InterruptedException {
        int previewHeadValue;
        while (!rightQueue.isEmpty()) {
            previewHeadValue = headValue;
            headValue = rightQueue.poll();
            distance += Math.abs(headValue - previewHeadValue);
            printMovingStatus(previewHeadValue);
            Thread.sleep(400);
        }
        distance += Math.abs(MAX_TRACK_INDEX - headValue);
        System.out.println(headValue + " -> " + MAX_TRACK_INDEX + " = " + distance);
        headValue = MAX_TRACK_INDEX;
        if (!leftQueue.isEmpty()) {
            System.out.println("\nturn left\n");
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
                rightQueue.offer(value);
                continue;
            }
            leftQueue.offer(value);
        }
    }

    public int getResult() {
        return distance;
    }

    public void addValue(final int randomTrackValue) {
        System.out.println("input random value: " + randomTrackValue);
        if (headValue < randomTrackValue) {
            rightQueue.offer(randomTrackValue);
            return;
        }
        leftQueue.offer(randomTrackValue);
    }
}

class RandomTrackThread extends Thread {
    private int time;
    private ScanAlgorithm scanAlgorithm;

    public RandomTrackThread(final int time, final ScanAlgorithm scanAlgorithm) {
        this.time = time;
        this.scanAlgorithm = scanAlgorithm;
    }

    @Override
    public void run() {
        while (time-- > 0) {
            int randomBreakTime = RandomGenerator.getRandomNumberInRange(1, 1000);
            int randomTrackValue = RandomGenerator.getRandomNumberInRange(0, MAX_TRACK_INDEX);
            try {
                Thread.sleep(randomBreakTime);
                scanAlgorithm.addValue(randomTrackValue);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Application {
    public static final String LEFT = "left";

    public static void main(String[] args) throws InterruptedException {
        ScanAlgorithm scanAlgorithm = new ScanAlgorithm(50, LEFT);
        RandomTrackThread randomTrackThread = new RandomTrackThread(10, scanAlgorithm);
        List<Integer> input = List.of(105, 180, 40, 120, 10, 125, 65, 70);
        scanAlgorithm.initializeValue(input);

        scanAlgorithm.start();
        randomTrackThread.start();

        scanAlgorithm.join();
        randomTrackThread.join();
        System.out.println("결과: " + scanAlgorithm.getResult());

    }
}
