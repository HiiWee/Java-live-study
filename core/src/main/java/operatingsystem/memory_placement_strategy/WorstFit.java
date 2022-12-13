package operatingsystem.memory_placement_strategy;

import java.util.ArrayList;
import java.util.List;

class WorstFitApplication {
    public static void main(String[] args) {
        List<WorstFitBlock> blocks = new ArrayList<>();
        blocks.add(new WorstFitBlock(17000, false, 17000));
        // 이미 사용중인 공간을 나타냅니다.
        blocks.add(new WorstFitBlock(1000, true, 0));
        blocks.add(new WorstFitBlock(7000, false, 7000));
        // 이미 사용중인 공간을 나타냅니다.
        blocks.add(new WorstFitBlock(1000, true, 0));
        blocks.add(new WorstFitBlock(15000, false, 15000));
        // 이미 사용중인 공간을 나타냅니다.
        blocks.add(new WorstFitBlock(1000, true, 0));
        blocks.add(new WorstFitBlock(30000, false, 30000));

        WorstFitMemory mainMemory = new WorstFitMemory(blocks);
        List<WorstFitBlock> emptyBlocks = mainMemory.findEmptyBlocks();
        List<Integer> memoriesToAllocate = List.of(12000, 5000, 30000);

        WorstFit worstFit = new WorstFit(emptyBlocks, memoriesToAllocate);
        try {
            worstFit.allocateMemory();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            System.out.println();
        }
        mainMemory.printMemory();
    }
}

public class WorstFit {
    private final List<WorstFitBlock> emptyBlocks;
    private final List<Integer> memoriesToAllocate;

    public WorstFit(List<WorstFitBlock> emptyBlocks, List<Integer> memoriesToAllocate) {
        emptyBlocks.sort((o1, o2) -> o2.getSize() - o1.getSize());
        this.emptyBlocks = emptyBlocks;
        this.memoriesToAllocate = memoriesToAllocate;
    }

    public void allocateMemory() {
        for (int memoryToAllocate : memoriesToAllocate) {
            emptyBlocks.stream()
                    .filter(block -> block.getSize() >= memoryToAllocate && block.isNotOccupied())
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(
                            String.format("[ERROR] %d의 크기를 할당할 수 있는 블록이 없습니다.", memoryToAllocate)))
                    .allocate(memoryToAllocate);
        }
    }
}

class WorstFitMemory {
    public static final String MESSAGE =
            "블록 전체사이즈\t\t\t\t" + "할당여부\t\t\t\t" + "남은 사이즈\t\t\t\t" + "블록 시작 주소\t\t\t\t" + "블록 마지막 주소";
    public static final int INDEX_GAP = 1;
    private final List<WorstFitBlock> blocks;

    public WorstFitMemory(List<WorstFitBlock> blocks) {
        this.blocks = blocks;
        initBlockIndex();
    }

    private void initBlockIndex() {
        WorstFitBlock previousBlock = blocks.get(0);
        previousBlock.setStartIndex(0);

        for (int blockIndex = 1; blockIndex < blocks.size(); blockIndex++) {
            WorstFitBlock currentBlock = blocks.get(blockIndex);
            currentBlock.setStartIndex(previousBlock.getEndIndex() + INDEX_GAP);
            previousBlock = currentBlock;
        }
    }

    public List<WorstFitBlock> findEmptyBlocks() {
        List<WorstFitBlock> emptyBlocks = new ArrayList<>();
        for (WorstFitBlock block : blocks) {
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

class WorstFitBlock {
    public static final int INDEX_GAP = 1;

    private final int size;
    private boolean isOccupied;
    private int leftSize;
    private int startIndex;
    private int endIndex;

    public WorstFitBlock(int size, boolean isOccupied, int leftSize) {
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