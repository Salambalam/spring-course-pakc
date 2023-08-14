package ru.chemakin.restedqproject.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chemakin.restedqproject.models.Person;
import ru.chemakin.restedqproject.services.PersonService;

import java.util.List;

@RestController
@RequestMapping("/people")
@AllArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping()
    public List<Person> getPersons(){
        return personService.findAll(); // Jackson автоматически конвертирует в JSON
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") int id){
        return personService.findOne(id); // Jackson автоматически конвертирует в JSON
    }



}
