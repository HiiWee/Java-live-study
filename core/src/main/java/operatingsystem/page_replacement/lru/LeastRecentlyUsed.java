package operatingsystem.page_replacement.lru;

import java.util.ArrayList;
import java.util.List;

class Page {
    private final int pageNumber;
    private int referenceCount;

    public Page(final int pageNumber) {
        this.pageNumber = pageNumber;
        this.referenceCount = 1;
    }

    public int getReferenceCount() {
        return referenceCount;
    }

    public void hit() {
        referenceCount = 1;
    }

    public void increaseCount() {
        referenceCount++;
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
        return pageNumber +
                "(" + referenceCount + ")";
    }

}

class PageTable {
    private final List<Page> pages = new ArrayList<>();
    private final int pageFrameSize;
    private int faultCount;

    public PageTable(final int pageFrameSize) {
        this.pageFrameSize = pageFrameSize;
    }

    public int getFaultCount() {
        return faultCount;
    }

    public void loadPage(final int sequence) {
        Page page = new Page(sequence);
        // 이미 존재하는 페이지라면 교체하지 않고, 참조 카운트 1로 제일 높게 세팅, 나머지 페이지들의 카운트는 증가함
        if (pages.contains(page)) {
            hitPage(page);
            return;
        }
        // 아직 큐에 공간이 남아있다면 적재(fault), 나머지 페이지들의 카운트는 증가
        if (pages.size() < pageFrameSize) {
            addPage(page);
            return;
        }
        // 존재하지 않는 페이지라면 제일 카운트 값이 높은 페이지를 제거하고, 적재함, 이때도 적재외 나머지 페이지들의 카운트 증가됨
        faultPage(page);

    }

    private void faultPage(final Page page) {
        faultCount++;
        int maxReferenceCount = getMaxReferenceCount();
        Page findPage = findPage(maxReferenceCount);
        replacePage(findPage, page);
        increaseCountOfExistPage(page);
    }

    private Page findPage(final int maxReferenceCount) {
        return pages.stream()
                .filter(page -> page.getReferenceCount() == maxReferenceCount)
                .findFirst()
                .get();
    }

    private int getMaxReferenceCount() {
        return pages.stream()
                .map(Page::getReferenceCount)
                .max(Integer::compare)
                .get();
    }

    private void replacePage(final Page findPage, final Page loadPage) {
        int index = pages.indexOf(findPage);
        pages.remove(findPage);
        pages.add(index, loadPage);
    }

    private void addPage(final Page page) {
        pages.add(page);
        increaseCountOfExistPage(page);
        faultCount++;
    }

    private void hitPage(final Page page) {
        Page loaded = pages.get(pages.indexOf(page));
        loaded.hit();
        increaseCountOfExistPage(loaded);
    }

    private void increaseCountOfExistPage(final Page loaded) {
        pages.stream()
                .filter(p -> p != loaded)
                .forEach(Page::increaseCount);
    }

    @Override
    public String toString() {
        return pages.toString();
    }
}

public class LeastRecentlyUsed {
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