package com.kduraj;

import com.kduraj.models.VoteDomain;
import com.kduraj.repositories.VoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Bean
    public CommandLineRunner initializeDb(VoteRepository repository) {
        return (args) -> {

            repository.deleteAll();
            repository.save(new VoteDomain("Justin Bieber", 0, new Date()));
            repository.save(new VoteDomain("Galantis",0, new Date()));
            repository.save(new VoteDomain("Alan Walker",0, new Date()));
            repository.save(new VoteDomain("Robin Schulz",0, new Date()));
            repository.save(new VoteDomain("Rudimental",0, new Date()));
            repository.save(new VoteDomain("Duke Dumont",0, new Date()));

        };
    }

}
