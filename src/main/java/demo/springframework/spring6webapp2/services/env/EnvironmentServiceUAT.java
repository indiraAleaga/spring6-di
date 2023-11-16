package demo.springframework.spring6webapp2.services.env;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("uat")
@Service
public class EnvironmentServiceUAT implements EnvironmentService {
    @Override
    public String getDataSource() {
        return "Datasource: User Acceptance Testing";
    }
}
