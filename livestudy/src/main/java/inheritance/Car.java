package inheritance;

class Car {
    private int gear;
    private int speed;
    private int motor;

    public Car() {
    }

    public Car(int gear, int speed, int motor) {
        this.gear = gear;
        this.speed = speed;
        this.motor = motor;
    }

    public int getGear() {
        return gear;
    }

    public int getSpeed() {
        return speed;
    }

    public int getMotor() {
        return motor;
    }

    public void printStatus() {
        System.out.println("Car status");
        System.out.println("gear = " + getGear());
        System.out.println("speed = " + getSpeed());
        System.out.println("motor = " + getMotor());
    }

    public void basicStatus() {
        System.out.println("Car status");
        System.out.println("gear = " + getGear());
        System.out.println("speed = " + getSpeed());
        System.out.println("motor = " + getMotor());
    }

}

class SportsCar extends Car {
    private int booster;

    public SportsCar() {
    }

    public SportsCar(int gear, int speed, int motor, int booster) {
        super(gear, speed, motor);
        this.booster = booster;
    }

    public void printStatus() {
        System.out.println("SportsCar status");
        System.out.println("gear = " + getGear());
        System.out.println("speed = " + getSpeed());
        System.out.println("motor = " + getMotor());
        System.out.println("booster = " + booster);
    }

    public void newPrintStatus() {
        super.printStatus();
        System.out.println();
        this.printStatus();

    }

    public void sportsStatus() {
        System.out.println("SportsCar status");
        System.out.println("booster = " + booster);
    }
}

class RunCar {
    public static void main(String[] args) {
        Car car = new SportsCar(1, 2, 3, 4);
        System.out.println("<<<Car>>>");
        car.printStatus();

        System.out.println();
        System.out.println("<<<SportsCar>>>");
        SportsCar newCar = (SportsCar) car;
        newCar.printStatus();
    }
}