package middletermtask.problem02.service;

import middletermtask.problem02.domain.Person;
import middletermtask.problem02.repository.AddressBookRepository;
import middletermtask.problem02.utils.UtilMessages;

public class AddressBookService {

    private AddressBookRepository addressBookRepository = AddressBookRepository.getInstance();

    /**
     * 예외가 있다면 Person 인스턴스에 예외를 담아 반환
     * 없다면 정상 인스턴스 반환
     */
    public Person insertPerson(Person person) {
        if (isDuplicated(person)) {
            return new Person(UtilMessages.DUPLICATED_PERSON, UtilMessages.HAS_ERROR, "");
        }
        if (person.getName().equals("")) {
            return new Person(UtilMessages.NO_PERSON_NAME, UtilMessages.HAS_ERROR, "");
        }
        return addressBookRepository.save(person);
    }

    /**
     * 예외가 있다면 Person 인스턴스에 예외를 담아 반환
     * 없다면 정상 인스턴스 반환
     */
    public Person searchPerson(String name) {
        Person findPerson = addressBookRepository.findByName(name);
        if (findPerson == null) {
            return new Person(UtilMessages.NO_PERSON, UtilMessages.HAS_ERROR, "");
        }
        return findPerson;
    }

    /**
     * 중복 검증 로직
     */
    private boolean isDuplicated(Person person) {
        Person findPerson = addressBookRepository.findByName(person.getName());
        if (findPerson == null) {
            return false;
        }
        return person.getAddress().equals(findPerson.getAddress())
                && person.getPhone().equals(findPerson.getPhone());
    }

}
