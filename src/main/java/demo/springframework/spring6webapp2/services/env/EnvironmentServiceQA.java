package demo.springframework.spring6webapp2.services.env;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("qa")
@Service
public class EnvironmentServiceQA implements EnvironmentService {
    @Override
    public String getDataSource() {
        return "Datasource: Quality Assurance";
    }
}
