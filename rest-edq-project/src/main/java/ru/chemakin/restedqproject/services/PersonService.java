package ru.chemakin.restedqproject.services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chemakin.restedqproject.models.Person;
import ru.chemakin.restedqproject.repositories.PersonRepository;
import ru.chemakin.restedqproject.util.PersonNotFoundException;

import java.time.LocalDateTime;
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
    @Transactional
    public void save(Person person){
        enrichPerson(person);
        personRepository.save(person);
    }
    private void enrichPerson(Person person) {

        person.setCreatedAt(LocalDateTime.now());
        person.setUpdatedAt(LocalDateTime.now());
        person.setCreatedWho("admin");
    }

}
