package dispatch;

public class Dispatch {

    static class SuperClass {
        private String message;

        public String getMessage() {
            return message;
        }

        public SuperClass(String message) {
            this.message = message;
        }

        public SuperClass() {
            this.message = "[This is SuperClass]";
        }

        public void run() {
            System.out.println(getMessage() + " " + this.getClass());
        }
    }

    static class SubClass extends SuperClass {
        public SubClass() {
            super("[This is SubClass]");
        }

        @Override
        public void run() {
            System.out.println(getMessage() + " " + this.getClass());
        }
    }

    public static void main(String[] args) {
        SuperClass superClass1 = new SuperClass();
        superClass1.run();
        SuperClass superClass2 = new SubClass();
        superClass2.run();
    }
}
