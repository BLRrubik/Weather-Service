package ru.rubik.weather.DAO;

import org.springframework.stereotype.Component;
import ru.rubik.weather.entity.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private int id;
    private List<Person> persons;

    public PersonDAO(){
        id = 1;
        persons = new ArrayList<>();
    }

    public void add(Person person){
        person.setId(id++);
        persons.add(person);
    }

    public Person findById(int id){
        return persons.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean update(int id, String name) {
        Person person = findById(id);

        if (person == null) {
            return false;
        }

        person.setName(name);

        return true;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void delete(Person person) {
        persons.remove(person);
    }
}
