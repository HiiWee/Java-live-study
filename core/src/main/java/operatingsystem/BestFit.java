package operatingsystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Application {
    public static void main(String[] args) {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(17000, false, 17000));
        // 이미 사용중인 공간을 나타냅니다.
        blocks.add(new Block(1000, true, 0));
        blocks.add(new Block(7000, false, 7000));
        // 이미 사용중인 공간을 나타냅니다.
        blocks.add(new Block(1000, true, 0));
        blocks.add(new Block(15000, false, 15000));
        // 이미 사용중인 공간을 나타냅니다.
        blocks.add(new Block(1000, true, 0));
        blocks.add(new Block(30000, false, 30000));

        Memory mainMemory = new Memory(blocks);
        List<Block> emptyBlocks = mainMemory.findEmptyBlocks();
        List<Integer> memoriesToAllocate = List.of(12000, 5000, 30000);

        BestFit bestFit = new BestFit(emptyBlocks, memoriesToAllocate);
        bestFit.AllocateMemory();
        mainMemory.printMemory();
    }
}

public class BestFit {
    private final List<Block> emptyBlocks;
    private final List<Integer> memoriesToAllocate;

    public BestFit(List<Block> emptyBlocks, List<Integer> memoriesToAllocate) {
        emptyBlocks.sort(Comparator.comparingInt(Block::getSize));
        this.emptyBlocks = emptyBlocks;
        this.memoriesToAllocate = memoriesToAllocate;
    }

    public void AllocateMemory() {
        for (int memoryToAllocate : memoriesToAllocate) {
            emptyBlocks.stream()
                    .filter(block -> block.getSize() >= memoryToAllocate && block.isNotOccupied())
                    .findFirst()
                    .get()
                    .allocate(memoryToAllocate);
        }
    }
}

class Memory {
    public static final String MESSAGE =
            "블록 전체사이즈\t\t\t\t" + "할당여부\t\t\t\t" + "남은 사이즈\t\t\t\t" + "블록 시작 주소\t\t\t\t" + "블록 마지막 주소";
    public static final int INDEX_GAP = 1;
    private final List<Block> blocks;

    public Memory(List<Block> blocks) {
        this.blocks = blocks;
        initBlockIndex();
    }

    private void initBlockIndex() {
        Block previousBlock = blocks.get(0);
        previousBlock.setStartIndex(0);

        for (int blockIndex = 1; blockIndex < blocks.size(); blockIndex++) {
            Block currentBlock = blocks.get(blockIndex);
            currentBlock.setStartIndex(previousBlock.getEndIndex() + INDEX_GAP);
            previousBlock = currentBlock;
        }
    }

    public List<Block> findEmptyBlocks() {
        List<Block> emptyBlocks = new ArrayList<>();
        for (Block block : blocks) {
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

class Block {
    public static final int INDEX_GAP = 1;

    private final int size;
    private boolean isOccupied;
    private int leftSize;
    private int startIndex;
    private int endIndex;

    public Block(int size, boolean isOccupied, int leftSize) {
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