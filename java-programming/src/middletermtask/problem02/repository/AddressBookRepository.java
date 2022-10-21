package middletermtask.problem02.repository;

import middletermtask.problem02.domain.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * 데이터를 직접적으로 저장하거나 꺼내는 작업을 수행하는 Repository
 * 최초 하나의 객체만 생성되며 싱글톤으로 유지된다. -> 단, Thread Unsafe 함
 */
public class AddressBookRepository {
    private static final AddressBookRepository instance = new AddressBookRepository();
    private static final List<Person> store = new ArrayList<>();

    public static AddressBookRepository getInstance() {
        return instance;
    }

    /**
     * person 저장
     */
    public Person savePerson(Person person) {
        store.add(person);
        return person;
    }

    /**
     * 이름을 통한 Person 찾아서 반환
     */
    public Person findByName(String name) {
        return store.stream()
                .filter(o -> o.getName().equals(name))
                .findAny()
                .orElse(null);
    }
}
