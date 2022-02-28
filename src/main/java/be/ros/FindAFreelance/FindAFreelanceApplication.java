package be.ros.FindAFreelance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FindAFreelanceApplication {


	static {
		System.out.println("Le bloc static est appelé");
	}
	public static void main(String[] args) {
		SpringApplication.run(FindAFreelanceApplication.class, args);
		System.out.println("Ici est le main de l'application");
	}

	static {
		System.out.println("Le 2 bloc static est appelé");
	}
}
