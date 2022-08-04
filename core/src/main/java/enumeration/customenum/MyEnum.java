package enumeration.customenum;

public abstract class MyEnum<T extends MyEnum<T>> implements Comparable<T> {
    static int id = 0;
    int ordianl;
    String name = "";

    public int ordianl() {
        return ordianl;
    }

    MyEnum(String name) {
        this.name = name;
        ordianl = id++;
    }

    @Override
    public int compareTo(T t) {
        return ordianl - t.ordianl();
    }
}
