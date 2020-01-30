package de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.Ausnahmesituation;
import de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter.persistance.DemenziellErkrankter;
import de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter.persistance.DemenziellErkrankterRepository;
import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.persistance.Kontaktperson;
import de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender.persistance.Positionssender;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.Nachricht;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.NachrichtRepository;
import de.th.koeln.archilab.fae.faeteam3service.utils.AusnahmesituationBuilder;
import de.th.koeln.archilab.fae.faeteam3service.utils.DementiellErkrankterBuilder;
import de.th.koeln.archilab.fae.faeteam3service.utils.KontaktpersonBuilder;
import de.th.koeln.archilab.fae.faeteam3service.utils.PositionssenderBuilder;
import lombok.extern.java.Log;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.ws.rs.core.MediaType;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@EmbeddedKafka
@Log
public class AusnahmesituationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DemenziellErkrankterRepository demenziellErkrankterRepository;

    @MockBean
    private AusnahmesituationService ausnahmesituationService;

    @MockBean
    private NachrichtRepository nachrichtRepository;

    private Ausnahmesituation ausnahmesituation;
    private DemenziellErkrankter dementiellErkrankter;
    private Kontaktperson kontaktperson;
    private Positionssender positionssender;

    @Before
    public void setUp() {
        positionssender = new PositionssenderBuilder().build();
        kontaktperson = new KontaktpersonBuilder().build();
        dementiellErkrankter = new DementiellErkrankterBuilder()
                .setKontaktpersonen(Collections.singletonList(kontaktperson))
                .setPositionssender(Collections.singletonList(positionssender))
                .build();

        ausnahmesituation = new AusnahmesituationBuilder()
                .setPositionssender(positionssender)
                .build();

    }

    @Test
    public void shouldSendStatusOkWhenValidRequest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        when(ausnahmesituationService.saveAusnahmesituationAndSendMessage(any(Ausnahmesituation.class))).thenReturn(ausnahmesituation);
        when(nachrichtRepository.save(any(Nachricht.class))).thenReturn(new Nachricht());
        when(demenziellErkrankterRepository.findDemenziellErkrankterByPositionssenderContains(any(Positionssender.class))).thenReturn(dementiellErkrankter);


        MvcResult result = mockMvc.perform(post("/ausnahmesituation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dementiellErkrankter))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Ausnahmesituation created = mapper.readValue(result.getResponse().getContentAsString(), Ausnahmesituation.class);
        DemenziellErkrankter foo = demenziellErkrankterRepository.findDemenziellErkrankterByPositionssenderContains(created.getPositionssender());

        assertEquals(foo.getId(), dementiellErkrankter.getId());
    }
}