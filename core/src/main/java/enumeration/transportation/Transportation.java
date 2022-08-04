package enumeration.transportation;

// 열거형에 추상 메서드 추가하기
public enum Transportation {
    BUS(100) {
        @Override
        int fare(int distance) {
            return distance * getBasicFare();
        }
    }, TRAIN(150) {
        @Override
        int fare(int distance) {
            return distance * getBasicFare();
        }
    }, SHIP(100) {
        @Override
        int fare(int distance) {
            return distance * getBasicFare();
        }
    }, AIRPLANE(300) {
        @Override
        int fare(int distance) {
            return distance * getBasicFare();
        }
    };

    private final int BASIC_FARE;

    abstract int fare(int distance);

    Transportation(int BASIC_FARE) {
        this.BASIC_FARE = BASIC_FARE;
    }

    public int getBasicFare() {
        return BASIC_FARE;
    }
}
