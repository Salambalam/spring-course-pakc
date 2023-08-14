package ru.chemakin.restedqproject.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.chemakin.restedqproject.models.Person;
import ru.chemakin.restedqproject.repositories.PersonRepository;
import ru.chemakin.restedqproject.util.PersonNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public List<Person> findAll(){
        return personRepository.findAll();
    }
    public Person findOne(int id){
        Optional<Person> person = personRepository.findById(id);
        return person.orElseThrow(PersonNotFoundException::new);
    }
    public Optional<Person> findByUsername(Person person){
        return personRepository.findByUsername(person.getUsername());
    }
}
