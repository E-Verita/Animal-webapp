package Animal.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class AnimalWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimalWebappApplication.class, args);
	}
	@GetMapping("/error")
	public String error(){
		return "error";
	}

}
