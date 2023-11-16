package demo.springframework.spring6webapp2.services.env;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("prod")
@Service
public class EnvironmentServicePROD implements EnvironmentService {
    @Override
    public String getDataSource() {
        return "Datasource: Production";
    }
}
