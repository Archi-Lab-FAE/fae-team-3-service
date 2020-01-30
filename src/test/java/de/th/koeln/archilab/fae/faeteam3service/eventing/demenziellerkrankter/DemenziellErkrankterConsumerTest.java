package de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter;

import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter.persistance.DemenziellErkrankterRepository;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class DemenziellErkrankterConsumerTest {

  private DemenziellErkrankterConsumer demenziellErkrankterConsumer;

  /** Initialisiere Klassen für DemenziellErkrankterConsumerTests. */
  @Before
  public void setUp() {
    DemenziellErkrankterRepository mockDemenziellErkrankterRepository =
        Mockito.mock(DemenziellErkrankterRepository.class);
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());

    demenziellErkrankterConsumer =
        new DemenziellErkrankterConsumer(mockDemenziellErkrankterRepository, objectMapper);
  }

  @Test
  public void consumeWithoutException() {
    try {
      String jsonString1 =
          "{\"id\" : \"cf8983ef-c757-4e7a-978c-d5217ff85f31\",\"key\" : \"215c7729-4cc0-4f3a-af87-97d6e979d4d1\",\"version\" : 0,\"timestamp\" : \"2020-01-30T16:24:07.429\",\"type\" : \"updated\",\"payload\" : {\"id\" : \"215c7729-4cc0-4f3a-af87-97d6e979d4d1\",\"name\" : \"Hilde\",\"vorname\" : \"Hilde\",\"zustimmung\" : true,\"kontaktpersonen\" : [ {\"id\" : \"94604b1b-adf5-4cf1-a6af-48eb748d6f4f\",\"name\" : \"Fritz\",\"vorname\" : \"Fritz\",\"telefonnummer\" : \"123456789\",\"aktiv\" : true}, {\"id\" : \"19ee91e4-78a4-4273-8cbe-1ae972d15eab\",\"name\" : \"Maria\",\"vorname\" : \"Maria\",\"telefonnummer\" : \"987654321\",\"aktiv\" : true}, {\"id\" : \"29dd6319-ca33-4ca6-994a-84ce38bbf5b0\",\"name\" : \"Altersheim\",\"vorname\" : \"Altersheim\",\"telefonnummer\" : \"987654321\",\"aktiv\" : true} ],\"positionssender\" : [ {\"id\" : \"738fb43e-7374-4739-8318-2897db08ff4b\",\"letzteWartung\" : \"2020-01-11T00:00:00.000+0000\"} ]}}";
      demenziellErkrankterConsumer.consume(jsonString1);
    } catch (IOException e) {
      fail("Es sollte keine Exception geworfen werden.");
    }
  }

  @Test(expected = Exception.class)
  public void consumeWithException() throws IOException {
    String jsonString1 = "{}";
    demenziellErkrankterConsumer.consume(jsonString1);
  }
}
