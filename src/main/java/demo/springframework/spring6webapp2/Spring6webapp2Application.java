package demo.springframework.spring6webapp2;

import demo.springframework.spring6webapp2.controllers.MyController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "demo.springframework.spring6webapp2")
public class Spring6webapp2Application {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(Spring6webapp2Application.class, args);

		MyController controller = ctx.getBean(MyController.class);

		System.out.println("In Main Method");

		System.out.println(controller.sayHello());
	}

}
