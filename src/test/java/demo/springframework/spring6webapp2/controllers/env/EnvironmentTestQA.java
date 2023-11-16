package demo.springframework.spring6webapp2.controllers.env;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"EN", "qa"})
@SpringBootTest
class EnvironmentTestQA {

    @Autowired
    EnvironmentController environmentController;


    @Test
    void getEnvDatasource() {
        System.out.println(environmentController.getEnvDatasource());
    }

}