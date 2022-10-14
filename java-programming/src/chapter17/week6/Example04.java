package chapter17.week6;

public class Example04 {

    static class Account {
        private int total;

        public synchronized void deposit() {
            if (getTotal() >= 500000) {
                return;
            }
            total += 1000;
        }

        public int getTotal() {
            return total;
        }
    }

    static class Customer implements Runnable {
        private Account account;
        private String name;

        public Customer(Account account, String name) {
            this.account = account;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 200; i++) {
                System.out.println(this + " : " + i + "번째");
                account.deposit();

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Account account = new Account();
        Thread donator1 = new Thread(new Customer(account, "1번째 성금자"));
        Thread donator2 = new Thread(new Customer(account, "2번째 성금자"));
        Thread donator3 = new Thread(new Customer(account, "3번째 성금자"));
        Thread donator4 = new Thread(new Customer(account, "4번째 성금자"));
        Thread donator5 = new Thread(new Customer(account, "5번째 성금자"));

        donator1.start();
        donator2.start();
        donator3.start();
        donator4.start();
        donator5.start();
        donator1.join();
        donator2.join();
        donator3.join();
        donator4.join();
        donator5.join();
        System.out.println("성금 총액은 : " + account.getTotal());
    }

}
