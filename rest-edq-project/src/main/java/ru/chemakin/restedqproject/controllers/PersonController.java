package ru.chemakin.restedqproject.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.chemakin.restedqproject.dto.PersonDTO;
import ru.chemakin.restedqproject.models.Person;
import ru.chemakin.restedqproject.services.PersonService;
import ru.chemakin.restedqproject.util.PersonErrorResponse;
import ru.chemakin.restedqproject.util.PersonNotCreatedException;
import ru.chemakin.restedqproject.util.PersonNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;
    private final ModelMapper mapper;

    @GetMapping()
    public List<PersonDTO> getPersons(){ // Jackson автоматически конвертирует в JSON
        return personService.findAll().stream()
                .map(this::convertToPersonDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable("id") int id){
        return convertToPersonDTO(personService.findOne(id)); // Jackson автоматически конвертирует в JSON
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDTO personDTO,
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

        personService.save(convertToPerson(personDTO));
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

    private Person convertToPerson(PersonDTO personDTO) {
        return mapper.map(personDTO, Person.class);
    }

    private PersonDTO convertToPersonDTO(Person person){
        return mapper.map(person, PersonDTO.class);
    }




}
