package demo.springframework.spring6webapp2.controllers.env;

import demo.springframework.spring6webapp2.services.env.EnvironmentService;
import org.springframework.stereotype.Controller;

@Controller
public class EnvironmentController {


    private final EnvironmentService environmentService;

    public EnvironmentController(EnvironmentService environmentService) {
        this.environmentService = environmentService;
    }

    public String getEnvDatasource(){
        return environmentService.getDataSource();
    }


}
