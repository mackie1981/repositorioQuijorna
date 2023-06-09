package es.cenjorapps.pizzacar.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextUtils {
	
	private static final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	public static Object getBean(String beanName){
		try {
			return applicationContext.getBean(beanName);
		} catch(Exception e) {
			System.out.println("Error al intentar obtener el bean "+ beanName + ": " + e.getMessage());
		}
		return null;
	}
}