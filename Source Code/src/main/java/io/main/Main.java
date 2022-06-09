package io.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"io.main", "io.Actions", "io.ApplicationUsers", "io.Controllers", "io.Cores", "io.Persistence"})
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
