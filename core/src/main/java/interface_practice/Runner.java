package interface_practice;

interface Runnable {
    void run();
}

interface Jumpable {
    void jump();
}

abstract class NationalTeam {
    int age;
    String country;
    String event;


    public abstract void setStatus(int age, String country, String event);

    public void printStatus() {
        System.out.println("age = " + age);
        System.out.println("country = " + country);
        System.out.println("event = " + event);
    }
}

class HighJumper extends NationalTeam implements Runnable, Jumpable {

    @Override
    public void setStatus(int age, String country, String event) {
        this.age = age;
        this.country = country;
        this.event = event;
    }

    @Override
    public void run() {
        System.out.println("시속 20km로 달립니다.");
    }

    @Override
    public void jump() {
        System.out.println("점프합니다.");
    }

    public void play() {
        System.out.println("선수 입장");
        printStatus();
        System.out.println();
        run();
        jump();
    }
}

class Main {
    public static void main(String[] args) {
        HighJumper highJumper = new HighJumper();
        highJumper.setStatus(25, "대한민국", "높이뛰기");
        highJumper.play();

        Runnable runnable = new HighJumper();
        Jumpable jumpable = new HighJumper();

        runnable.run();
        // runnable.setStatus(20, "미국", "높이뛰기");    // 오류 발생
        ((HighJumper) runnable).play();

        jumpable.jump();
        // jumpable.setStatus(20, "일본", "높이뛰기");    // 오류 발생
        ((HighJumper) jumpable).play();
    }
}
