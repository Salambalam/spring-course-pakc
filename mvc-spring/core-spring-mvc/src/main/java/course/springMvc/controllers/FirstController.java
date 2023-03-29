package course.springMvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/first")
public class FirstController {
    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name, //required - отвечатет за то что если мы в Get запросе не передаем никаких параметров, он кладет в них NULL
                            @RequestParam(value = "surname", required = false) String surname,
                            Model model){

        //System.out.println("Hello, " + name + " " + surname);
        model.addAttribute("message", "Hello, " + name + " " + surname);
        return "first/hello";
    }
    @GetMapping("/goodbye")
    public String goodByePage(){
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String calcApp(@RequestParam("a") int a, @RequestParam("b") int b,
                          @RequestParam("action") String action, Model model){
        double result;
        switch (action){
            case "multiplication":
                result = a * b;
                break;
            case "addition":
                result = a + b;
                break;
            case "subtraction":
                result = a - b;
                break;
            case "division":
                result = a / (double)b;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + action);
        }
        model.addAttribute("result", "Result " + action + ": " + result);
        return "first/calculator";
    }
}
