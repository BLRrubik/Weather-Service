package ru.rubik.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rubik.weather.DAO.PersonDAO;
import ru.rubik.weather.entity.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonDAO personDAO;

    @GetMapping()
    public ResponseEntity<List<Person>> main(){
        return ResponseEntity.of(Optional.of(personDAO.getPersons()));
    }

    @GetMapping("{id}")
    public ResponseEntity<Map<Integer, String>> getPerson(@PathVariable int id) {
        Person person = personDAO.findById(id);
        Map<Integer, String> map = new HashMap<>();
        map.put(person.getId(), person.getName());

        return ResponseEntity.of(Optional.of(map));
    }

    @PostMapping()
    public ResponseEntity<Map<Integer, String>> create(@RequestBody Person person) {
        personDAO.add(person);

        Map<Integer, String> map = new HashMap<>();
        map.put(person.getId(), person.getName());

        return ResponseEntity.of(Optional.of(map));
    }

    @PutMapping("{id}")
    public ResponseEntity<Map<Integer, String>> update(@PathVariable int id, @RequestBody Person person) {
        Person personToUpdate = personDAO.findById(id);
        personToUpdate.setName(person.getName());
        personToUpdate.setId(id);

        Map<Integer, String> map = new HashMap<>();
        map.put(person.getId(), person.getName());

        return ResponseEntity.of(Optional.of(map));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        Person person = personDAO.findById(id);
        personDAO.delete(person);
    }
}
