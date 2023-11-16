package demo.springframework.spring6webapp2.controllers;

import demo.springframework.spring6webapp2.services.GreetingService;
import demo.springframework.spring6webapp2.services.GreetingServiceImpl;
import org.springframework.stereotype.Controller;

@Controller
public class MyController {

    private final GreetingService greetingService= new GreetingServiceImpl();

    public String sayHello(){
        return greetingService.sayGreeting();
    }
}
