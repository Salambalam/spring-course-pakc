package course.crud.spring.controllers;

import course.crud.spring.dao.PersonDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/batch_update")
public class BatchController {
    private final PersonDAO personDAO;

    public BatchController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(){
        return "batch/index";
    }

    @GetMapping("/with")
    public String withBatch(){
        personDAO.batchUpdate();
        return "redirect:/people";
    }
}
