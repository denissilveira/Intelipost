package br.com.intelipost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"br.com.intelipost"})
public class IntelipostWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntelipostWebApplication.class, args);
	}
}
