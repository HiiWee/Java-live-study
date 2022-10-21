package middletermtask.problem02.controller;

import middletermtask.problem02.domain.Person;
import middletermtask.problem02.service.AddressBookService;
import middletermtask.problem02.utils.UtilMessages;
import middletermtask.problem02.view.SwingView;
import middletermtask.problem02.enums.ActionCategory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddressBookController implements ActionListener {

    private final AddressBookService addressBookService = new AddressBookService();
    private SwingView view;

    public void startAddressBook() {
        view = new SwingView();
        view.getSaveButton().addActionListener(this);
        view.getSearchButton().addActionListener(this);
        view.getExitButton().addActionListener(this);

    }

    /**
     * 뷰에 텍스트 올리고, 내리는 메소드
     */
    public void clearView() {
        view.setPersonNameField("");
        view.setPersonPhoneField("");
        view.setPersonAddressField("");
    }

    public void setView(String name, String phone, String address) {
        view.setPersonNameField(name);
        view.setPersonPhoneField(phone);
        view.setPersonAddressField(address);
    }

    /**
     * Person 저장
     */
    public void savePerson() {
        Person person = new Person(view.getPersonName(), view.getPersonPhone(), view.getPersonAddress());
        Person savePerson = addressBookService.insertPerson(person);
        if (hasException(savePerson)) {
            clearView();
            setView(savePerson.getName(), "", "");
            return;
        }
        clearView();
        setView(UtilMessages.SAVE_OK + " 이름: " + savePerson.getName(), "", "");
    }

    /**
     * 이름으로 Person 검색
     */
    private void searchPerson() {
        String personName = view.getPersonName();
        Person findPerson = addressBookService.searchPerson(personName);
        if (hasException(findPerson)) {
            clearView();
            setView(findPerson.getName(), "", "");
            return;
        }
        setView(findPerson.getName(), findPerson.getPhone(), findPerson.getAddress());
    }

    /**
     * 종료
     */
    public void exitAddressBook() {
        System.exit(0);
    }

    /**
     * 인스턴스에 예외가 존재하는지 확인
     */
    private boolean hasException(Person savePerson) {
        if (savePerson.getPhone().equals(UtilMessages.HAS_ERROR)) {
            return true;
        }
        return false;
    }

    /**
     * 각 액션에 맞게 분기
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        ActionCategory select = ActionCategory.findAction(actionCommand);
        switch (select) {
            case SAVE:
                savePerson();
                break;
            case SEARCH:
                searchPerson();
                break;
            case EXIT:
                exitAddressBook();
                break;
        }
    }
}
