package io.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



//@SpringBootApplication
@SpringBootApplication(scanBasePackages={"io.main"})
public class Main {

	//public static IPersistence p = new Persistence();
	
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);

	}

}
