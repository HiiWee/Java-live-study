package chapter17.week6;


public class Example05 {
    static class Bank {
        private int totalMoney;

        public synchronized void save(int money) {
            this.totalMoney += money;
            System.out.println("저축 " + money + "원 잔액 : " + totalMoney);
            notifyAll();
        }

        public synchronized void withdraw(int money) {
            try {
                while (totalMoney < money) {
                    wait();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.totalMoney -= money;
            System.out.println("인출 " + money + "원 잔액 : " + totalMoney);
        }
    }

    static class Customer extends Thread {
        private Bank bank;

        public Customer(Bank bank) {
            this.bank = bank;
        }

        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                int money = (int) (Math.random() * 10 + 1);
                bank.withdraw(money);
            }
        }
    }

    static class Producer extends Thread {
        private Bank bank;

        public Producer(Bank bank) {
            this.bank = bank;
        }

        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int money = (int) (Math.random() * 10 + 1);
                bank.save(money);
            }
        }
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        Thread producer = new Producer(bank);
        Thread customer = new Customer(bank);

        producer.start();
        customer.start();
    }
}
