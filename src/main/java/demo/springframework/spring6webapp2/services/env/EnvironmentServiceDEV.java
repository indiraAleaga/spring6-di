package demo.springframework.spring6webapp2.services.env;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({"dev", "default"})
@Service
public class EnvironmentServiceDEV implements EnvironmentService {
    @Override
    public String getDataSource() {
        return "Datasource: Development";
    }
}
