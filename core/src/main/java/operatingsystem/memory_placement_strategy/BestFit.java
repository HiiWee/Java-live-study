package operatingsystem.memory_placement_strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class BestFitApplication {
    public static void main(String[] args) {
        List<BestFitBlock> blocks = new ArrayList<>();
        blocks.add(new BestFitBlock(17000, false, 17000));
        // 이미 사용중인 공간을 나타냅니다.
        blocks.add(new BestFitBlock(1000, true, 0));
        blocks.add(new BestFitBlock(7000, false, 7000));
        // 이미 사용중인 공간을 나타냅니다.
        blocks.add(new BestFitBlock(1000, true, 0));
        blocks.add(new BestFitBlock(15000, false, 15000));
        // 이미 사용중인 공간을 나타냅니다.
        blocks.add(new BestFitBlock(1000, true, 0));
        blocks.add(new BestFitBlock(30000, false, 30000));

        BestFitMemory mainMemory = new BestFitMemory(blocks);
        List<BestFitBlock> emptyBlocks = mainMemory.findEmptyBlocks();
        List<Integer> memoriesToAllocate = List.of(12000, 5000, 30000);

        BestFit bestFit = new BestFit(emptyBlocks, memoriesToAllocate);
        bestFit.allocateMemory();
        mainMemory.printMemory();
    }
}

public class BestFit {
    private final List<BestFitBlock> emptyBlocks;
    private final List<Integer> memoriesToAllocate;

    public BestFit(List<BestFitBlock> emptyBlocks, List<Integer> memoriesToAllocate) {
        emptyBlocks.sort(Comparator.comparingInt(BestFitBlock::getSize));
        this.emptyBlocks = emptyBlocks;
        this.memoriesToAllocate = memoriesToAllocate;
    }

    public void allocateMemory() {
        for (int memoryToAllocate : memoriesToAllocate) {
            emptyBlocks.stream()
                    .filter(block -> block.getSize() >= memoryToAllocate && block.isNotOccupied())
                    .findFirst()
                    .get()
                    .allocate(memoryToAllocate);
        }
    }
}

class BestFitMemory {
    public static final String MESSAGE =
            "블록 전체사이즈\t\t\t\t" + "할당여부\t\t\t\t" + "남은 사이즈\t\t\t\t" + "블록 시작 주소\t\t\t\t" + "블록 마지막 주소";
    public static final int INDEX_GAP = 1;
    private final List<BestFitBlock> blocks;

    public BestFitMemory(List<BestFitBlock> blocks) {
        this.blocks = blocks;
        initBlockIndex();
    }

    private void initBlockIndex() {
        BestFitBlock previousBlock = blocks.get(0);
        previousBlock.setStartIndex(0);

        for (int blockIndex = 1; blockIndex < blocks.size(); blockIndex++) {
            BestFitBlock currentBlock = blocks.get(blockIndex);
            currentBlock.setStartIndex(previousBlock.getEndIndex() + INDEX_GAP);
            previousBlock = currentBlock;
        }
    }

    public List<BestFitBlock> findEmptyBlocks() {
        List<BestFitBlock> emptyBlocks = new ArrayList<>();
        for (BestFitBlock block : blocks) {
            if (block.isNotOccupied()) {
                emptyBlocks.add(block);
            }
        }
        return emptyBlocks;
    }

    public void printMemory() {
        System.out.println(MESSAGE);
        System.out.println(blocks);
    }
}

class BestFitBlock {
    public static final int INDEX_GAP = 1;

    private final int size;
    private boolean isOccupied;
    private int leftSize;
    private int startIndex;
    private int endIndex;

    public BestFitBlock(int size, boolean isOccupied, int leftSize) {
        this.size = size;
        this.isOccupied = isOccupied;
        this.leftSize = leftSize;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
        this.endIndex = startIndex + size - INDEX_GAP;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public int getSize() {
        return size;
    }

    public boolean isNotOccupied() {
        return !isOccupied;
    }

    public void allocate(int memoryToAllocate) {
        leftSize -= memoryToAllocate;
        isOccupied = true;
    }

    @Override
    public String toString() {
        return "Block{" +
                "size=" + size +
                "\t\t| isOccupied=" + isOccupied +
                "\t\t| leftSize=" + leftSize +
                "\t\t| startIndex=" + startIndex +
                "\t\t| endIndex=" + endIndex +
                '}' + "\n";
    }
}