package operatingsystem.secondchance;

import java.util.ArrayList;
import java.util.List;

class Page {
    private final int pageNumber;
    private int referenceBit;

    public Page(final int pageNumber) {
        this.pageNumber = pageNumber;
        this.referenceBit = 1;
    }

    public void hit() {
        referenceBit = 1;
    }

    public boolean isBitSet() {
        return referenceBit == 1;
    }

    public void setBitDown() {
        referenceBit = 0;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Page)) {
            return false;
        }
        Page page = (Page) o;
        return page.pageNumber == this.pageNumber;
    }

    @Override
    public String toString() {
        return +pageNumber +
                "(" + referenceBit + ")";
    }
}

class PageTable {
    private final List<Page> pages = new ArrayList<>();
    private final int pageFrameSize;
    private int currentIndex;
    private int faultCount;

    public PageTable(final int pageFrameSize) {
        this.pageFrameSize = pageFrameSize;
    }

    public int getFaultCount() {
        return faultCount;
    }

    public void loadPage(final int sequence) {
        Page page = new Page(sequence);
        // 이미 존재하는 페이지라면 교체하지 않고 적재된 페이지의 참조비트 1셋업
        if (pages.contains(page)) {
            hitPage(page);
            return;
        }
        // 아직 큐에 공간이 남아있다면 적재(fault)
        if (pages.size() < pageFrameSize) {
            addPage(page);
            return;
        }
        // 존재하지 않는 페이지라면 while을 돌며 검사
        faultPage(page);
    }

    private void faultPage(final Page page) {
        faultCount++;
        while (true) {
            Page loaded = pages.get(currentIndex);
            if (loaded.isBitSet()) {
                loaded.setBitDown();
                moveNext();
                continue;
            }
            replacePage(page);
            moveNext();
            break;
        }
    }

    private void moveNext() {
        currentIndex = (currentIndex + 1) % pageFrameSize;
    }

    private void replacePage(final Page page) {
        pages.remove(currentIndex);
        pages.add(currentIndex, page);
    }

    private void addPage(final Page page) {
        pages.add(page);
        faultCount++;
    }

    private void hitPage(final Page page) {
        Page loaded = pages.get(pages.indexOf(page));
        loaded.hit();
    }

    @Override
    public String toString() {
        return pages.toString();
    }
}

public class SecondChanceAlgorithm {
    public static void main(String[] args) {
        int[] pageToLoad = {8, 1, 2, 3, 1, 4, 1, 5, 3, 4, 1, 4, 3, 2, 3, 1, 2, 8, 1, 2};
        PageTable pageTable = new PageTable(3);

        for (int page : pageToLoad) {
            System.out.println("적재되어야 할 페이지: " + page + " ");
            pageTable.loadPage(page);
            System.out.print("적재 결과: ");
            System.out.println(pageTable);
            System.out.println("부재 수: " + pageTable.getFaultCount());
            System.out.println();
        }
        System.out.println("총 페이지 부재 횟수 : " + pageTable.getFaultCount());
    }
}