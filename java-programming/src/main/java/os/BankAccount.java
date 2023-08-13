package os;

class Test {
    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount = new BankAccount();
        Parent p = new Parent(bankAccount);
        Parent p2 = new Parent(bankAccount);
        Child c = new Child(bankAccount);
        Child c2 = new Child(bankAccount);
        c.start();
        p.start();
        p2.start();
        p.join();
        c.join();
        c2.join();
        System.out.println("\nbalance = " + bankAccount.getBalance());
    }
}

public class BankAccount {
    int balance;

    synchronized void deposit(int amt) {
        int temp = balance + amt;
        System.out.println("+" + " : " + temp);
        balance = temp;
        notify();
    }

    synchronized void withdraw(int amt) throws InterruptedException {
        if (balance == 0) {
            System.out.println("잔액이 부족합니다.");
            wait();
        }
        int temp = balance - amt;
        System.out.println("-" + " : " + temp);
        balance = temp;

    }

    public int getBalance() {
        return balance;
    }
}

class Parent extends Thread {
    BankAccount bankAccount;

    public Parent(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            bankAccount.deposit(1000);
        }
    }
}

class Child extends Thread {
    BankAccount bankAccount;

    public Child(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                bankAccount.withdraw(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
