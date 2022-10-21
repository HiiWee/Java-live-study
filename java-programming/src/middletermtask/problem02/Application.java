package middletermtask.problem02;

import middletermtask.problem02.controller.AddressBookController;

public class Application {
    public static void main(String[] args) {
        AddressBookController addressBookController = new AddressBookController();
        addressBookController.startAddressBook();
    }

}
