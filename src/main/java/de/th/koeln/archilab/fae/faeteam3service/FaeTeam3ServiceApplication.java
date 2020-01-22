package de.th.koeln.archilab.fae.faeteam3service;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableEurekaClient
@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class FaeTeam3ServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(FaeTeam3ServiceApplication.class, args);
  }

  @Bean
  public Executor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(2);
    executor.setMaxPoolSize(2);
    executor.setQueueCapacity(500);
    executor.setThreadNamePrefix("TimeoutChecker-");
    executor.initialize();
    return executor;
  }
}
