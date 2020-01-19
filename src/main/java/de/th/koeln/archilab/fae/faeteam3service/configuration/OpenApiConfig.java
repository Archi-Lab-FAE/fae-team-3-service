package de.th.koeln.archilab.fae.faeteam3service.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sebastian Quast
 * @since 1.0.0
 */
@Configuration
public class OpenApiConfig {

  private List<Server> configServer() {
    Server server = new Server();
    server.setUrl("https://api.fae.archi-lab.io/");
    server.description("Generated server url");
    return Arrays.asList(server);
  }

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .components(new Components())
        .info(new Info().title("Nachrichtensystem API").description(
            "OpenAPI 3 Dokumentation des Nachrichtensystems "
        ).version("1"))
        .servers(configServer());
  }

}
