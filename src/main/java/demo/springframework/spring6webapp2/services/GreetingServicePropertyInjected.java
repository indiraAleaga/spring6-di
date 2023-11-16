package demo.springframework.spring6webapp2.services;

import org.springframework.stereotype.Service;

@Service("propertyService")
public class GreetingServicePropertyInjected implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Friends don't let friends to do property injection!!";
    }
}
