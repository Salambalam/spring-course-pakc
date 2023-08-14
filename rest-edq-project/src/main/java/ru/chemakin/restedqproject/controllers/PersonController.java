package ru.chemakin.restedqproject.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.chemakin.restedqproject.models.Person;
import ru.chemakin.restedqproject.services.PersonService;
import ru.chemakin.restedqproject.util.PersonErrorResponse;
import ru.chemakin.restedqproject.util.PersonNotFoundException;

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

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException exception){
        PersonErrorResponse response = new PersonErrorResponse(
                "Person with this id not found",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // NOT_FOUND - 404 статус
    }



}
