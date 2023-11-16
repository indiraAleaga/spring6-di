package demo.springframework.spring6webapp2;

import demo.springframework.spring6webapp2.controllers.MyController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

@SpringBootTest()
class Spring6webapp2ApplicationTests {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	MyController controller;

	@Test
	void testAutoWiredController(){
		System.out.println(controller.sayHello());


	}

	@Test
	void testControllerFromCtx(){
		MyController controller = applicationContext.getBean(MyController.class);
		System.out.println(controller.sayHello());
	}


}
