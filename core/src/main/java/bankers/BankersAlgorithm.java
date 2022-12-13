package bankers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Application {
    public static void main(String[] args) {
        BankersAlgorithm bankersAlgorithm = new BankersAlgorithm(new Bank(List.of(1, 2, 3)), new ArrayList<>());
        bankersAlgorithm.run();
        bankersAlgorithm.printResult();
    }
}

public class BankersAlgorithm {
    private final List<Integer> results = new ArrayList<>();
    private final Bank bank;
    private final List<Customer> customers;

    public BankersAlgorithm(final Bank bank, final List<Customer> customers) {
        this.bank = bank;
        this.customers = customers;
        initializeBankers();
    }

    private void initializeBankers() {
        customers.add(new Customer(List.of(Resources.of(7, 1), Resources.of(5, 1), Resources.of(4, 1))));
        customers.add(new Customer(List.of(Resources.of(4, 2), Resources.of(1, 1), Resources.of(2, 0))));
        customers.add(new Customer(List.of(Resources.of(7, 3), Resources.of(1, 0), Resources.of(6, 2))));
        customers.add(new Customer(List.of(Resources.of(2, 2), Resources.of(1, 1), Resources.of(3, 0))));
        customers.add(new Customer(List.of(Resources.of(5, 0), Resources.of(3, 0), Resources.of(2, 1))));
    }

    public void run() {
        int loop = customers.size();
        while (loop-- > 0) {
            Customer customer = customers.stream()
                    .filter(bank::borrow)
                    .findFirst()
                    .orElse(null);
            applyBorrowResult(customer);
        }
    }

    private void applyBorrowResult(final Customer customer) {
        if (customer != null) {
            results.add(customers.indexOf(customer) + 1);
        }
    }

    public void printResult() {
        if (customers.size() == results.size()) {
            System.out.println("System is in safe state.");
            System.out.println("Safe sequence is: " + results);
            return;
        }
        System.out.println("System is in unsafe state.");
        System.out.println("Unsafe sequence is: " + results);
    }
}

class Bank {
    private final List<Integer> availables = new ArrayList<>();

    public Bank(final List<Integer> availables) {
        this.availables.addAll(availables);
    }

    public boolean borrow(final Customer customer) {
        if (isPossibleToBorrow(customer.getNeedResources())) {
            addReturnAvailables(customer.processDone());
            return true;
        }
        return false;
    }

    private void addReturnAvailables(final List<Integer> availablesFromCustomer) {
        List<Integer> sumAvailables = new ArrayList<>();
        for (int i = 0; i < availablesFromCustomer.size(); i++) {
            int sumAvailable = availables.get(i) + availablesFromCustomer.get(i);
            sumAvailables.add(sumAvailable);
        }
        availables.clear();
        availables.addAll(sumAvailables);
    }

    private boolean isPossibleToBorrow(final List<Integer> needs) {
        for (int i = 0; i < needs.size(); i++) {
            if (needs.get(i) > availables.get(i)) {
                return false;
            }
        }
        return true;
    }

}

class Customer {
    private final List<Resources> resources;

    public Customer(final List<Resources> resources) {
        this.resources = resources;
    }

    public List<Integer> getNeedResources() {
        return resources.stream()
                .map(Resources::getNeed)
                .collect(Collectors.toList());
    }

    public List<Integer> processDone() {
        return resources.stream()
                .map(Resources::returnResource)
                .collect(Collectors.toList());
    }
}

class Resources {
    private int max;
    private int need;
    private int allocation;

    public Resources(final int max, final int allocation) {
        this.max = max;
        this.allocation = allocation;
        need = max - allocation;
    }

    public static Resources of(final int max, final int allocation) {
        return new Resources(max, allocation);
    }

    public int getNeed() {
        return need;
    }

    public int returnResource() {
        need = Integer.MAX_VALUE;
        allocation = 0;
        return max;
    }
}
