package ru.platiza.service.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/documentation")
public class SwaggerController {

    @GetMapping(value = {"", "/"})
    public String redirectSwagger(){
        return "redirect:/swagger-ui.html";
    }

}
