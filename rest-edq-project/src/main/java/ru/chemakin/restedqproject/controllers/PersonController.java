package ru.chemakin.restedqproject.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.chemakin.restedqproject.models.Person;
import ru.chemakin.restedqproject.services.PersonService;
import ru.chemakin.restedqproject.util.PersonErrorResponse;
import ru.chemakin.restedqproject.util.PersonNotCreatedException;
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

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Person person,
                                             BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error: errors){
                errorMessage.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new PersonNotCreatedException(errorMessage.toString());
        }

        personService.save(person);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException exception){
        PersonErrorResponse response = new PersonErrorResponse(
                "Person with this id not found",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // NOT_FOUND - 404 статус
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException exception){
        PersonErrorResponse response = new PersonErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



}
