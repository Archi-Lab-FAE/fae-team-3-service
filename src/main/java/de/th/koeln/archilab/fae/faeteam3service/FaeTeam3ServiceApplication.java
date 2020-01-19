package de.th.koeln.archilab.fae.faeteam3service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableEurekaClient
@SpringBootApplication
@EnableJpaAuditing
public class FaeTeam3ServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(FaeTeam3ServiceApplication.class, args);
  }
}
