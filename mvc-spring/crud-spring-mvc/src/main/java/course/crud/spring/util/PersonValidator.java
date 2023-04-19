package course.crud.spring.util;

import course.crud.spring.dao.PersonDAO;
import course.crud.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;
    @Autowired
    public PersonValidator(PersonDAO personDAO){
        this.personDAO = personDAO;
    }

    // этот метод показывает какой класс должен валидировать спринг
    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o; // downcast потому что мы знвем какой класс валидируем
        if(personDAO.show(person.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "This email is already taken");
        }
        // посмотреть, есть ли человек с таким же email в базе данных

    }
}
