package ru.chemakin.SecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chemakin.SecurityApp.models.Person;
import ru.chemakin.SecurityApp.repositories.PeopleRepository;

import java.util.Optional;

@Service
public class PersonService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Optional<Person> findByUsername(Person person){
        return peopleRepository.findByUsername(person.getUsername());
    }
}
