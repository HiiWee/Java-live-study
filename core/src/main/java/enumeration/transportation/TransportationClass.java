package enumeration.transportation;

public class TransportationClass {
    public static void main(String[] args) {

        System.out.println("bus fare=" + Transportation.BUS.fare(100));
        System.out.println("train fare=" + Transportation.TRAIN.fare(100));
        System.out.println("ship fare=" + Transportation.SHIP.fare(100));
        System.out.println("airplane fare=" + Transportation.AIRPLANE.fare(100));
    }
}
