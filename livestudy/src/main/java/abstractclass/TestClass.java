package abstractclass;

public abstract class TestClass {
    public abstract void run();

    public void printThisClass() {
        System.out.println(this.getClass());
    }
}

abstract class ImplementationClass1 extends TestClass {
    public abstract void jump();
}

class ImplementationClass2 extends TestClass {

    @Override
    public void run() {
        System.out.println("run!");
    }
}