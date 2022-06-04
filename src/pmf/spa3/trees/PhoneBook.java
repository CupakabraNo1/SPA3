package pmf.spa3.trees;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class PhoneBook {

    private Map<String, String> persons;
    private Map<String, String> phones;

    public PhoneBook() {
        persons = new TreeMap<>();
        phones = new TreeMap<>();
    }

    public String getPhone(String person) {
        return persons.get(person);
    }

    public String getPerson(String phone) {
        return phones.get(phone);
    }

    public boolean put(String person, String phone) {
        phones.put(phone, person);
        persons.put(person, phone);
        return true;
    }

    public Set<String> getPhones() {
        return phones.keySet();
    }

    public Set<String> getPersons() {
        return persons.keySet();
    }
}
