package operatingsystem.memory_placement_strategy;

import java.util.ArrayList;
import java.util.List;

class FirstFitApplication {
    public static void main(String[] args) {
        List<FirstFitBlock> blocks = new ArrayList<>();
        blocks.add(new FirstFitBlock(17000, false, 17000));
        // 이미 사용중인 공간을 나타냅니다.
        blocks.add(new FirstFitBlock(1000, true, 0));
        blocks.add(new FirstFitBlock(7000, false, 7000));
        // 이미 사용중인 공간을 나타냅니다.
        blocks.add(new FirstFitBlock(1000, true, 0));
        blocks.add(new FirstFitBlock(15000, false, 15000));
        // 이미 사용중인 공간을 나타냅니다.
        blocks.add(new FirstFitBlock(1000, true, 0));
        blocks.add(new FirstFitBlock(30000, false, 30000));

        FirstFitMemory mainMemory = new FirstFitMemory(blocks);
        List<FirstFitBlock> emptyBlocks = mainMemory.findEmptyBlocks();
        List<Integer> memoriesToAllocate = List.of(12000, 5000, 30000);

        FirstFit firstFit = new FirstFit(emptyBlocks, memoriesToAllocate);
        firstFit.allocateMemory();
        mainMemory.printMemory();
    }
}

public class FirstFit {
    private final List<FirstFitBlock> emptyBlocks;
    private final List<Integer> memoriesToAllocate;

    public FirstFit(List<FirstFitBlock> emptyBlocks, List<Integer> memoriesToAllocate) {
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

class FirstFitMemory {
    public static final String MESSAGE =
            "블록 전체사이즈\t\t\t\t" + "할당여부\t\t\t\t" + "남은 사이즈\t\t\t\t" + "블록 시작 주소\t\t\t\t" + "블록 마지막 주소";
    public static final int INDEX_GAP = 1;
    private final List<FirstFitBlock> blocks;

    public FirstFitMemory(List<FirstFitBlock> blocks) {
        this.blocks = blocks;
        initBlockIndex();
    }

    private void initBlockIndex() {
        FirstFitBlock previousBlock = blocks.get(0);
        previousBlock.setStartIndex(0);

        for (int blockIndex = 1; blockIndex < blocks.size(); blockIndex++) {
            FirstFitBlock currentBlock = blocks.get(blockIndex);
            currentBlock.setStartIndex(previousBlock.getEndIndex() + INDEX_GAP);
            previousBlock = currentBlock;
        }
    }

    public List<FirstFitBlock> findEmptyBlocks() {
        List<FirstFitBlock> emptyBlocks = new ArrayList<>();
        for (FirstFitBlock block : blocks) {
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

class FirstFitBlock {
    public static final int INDEX_GAP = 1;

    private final int size;
    private boolean isOccupied;
    private int leftSize;
    private int startIndex;
    private int endIndex;

    public FirstFitBlock(int size, boolean isOccupied, int leftSize) {
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