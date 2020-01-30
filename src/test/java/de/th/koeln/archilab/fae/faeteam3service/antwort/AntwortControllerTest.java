package de.th.koeln.archilab.fae.faeteam3service.antwort;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.th.koeln.archilab.fae.faeteam3service.antwort.persistance.Antwort;
import de.th.koeln.archilab.fae.faeteam3service.antwort.persistance.AntwortTyp;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.Ausnahmesituation;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.AusnahmesituationRepository;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.Position;
import de.th.koeln.archilab.fae.faeteam3service.eventing.ausnahmesituation.AusnahmesituationConsumer;
import de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter.persistance.DemenziellErkrankter;
import de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter.persistance.DemenziellErkrankterRepository;
import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.persistance.Kontaktperson;
import de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender.persistance.Positionssender;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.Nachricht;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.NachrichtRepository;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.NachrichtText;
import de.th.koeln.archilab.fae.faeteam3service.utils.AusnahmesituationBuilder;
import de.th.koeln.archilab.fae.faeteam3service.utils.DementiellErkrankterBuilder;
import de.th.koeln.archilab.fae.faeteam3service.utils.KontaktpersonBuilder;
import de.th.koeln.archilab.fae.faeteam3service.utils.PositionssenderBuilder;
import lombok.extern.java.Log;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Log
public class AntwortControllerTest {

    private static final NachrichtText NACHRICHT_TEXT = new NachrichtText("Foo bar");

    @MockBean
    private AusnahmesituationRepository mockAusnahmeSituationRepository;

    @MockBean
    private NachrichtRepository mockNachrichtenRepository;

    @MockBean
    private DemenziellErkrankterRepository mockDemenziellErkrankterRepository;

    @MockBean
    private AntwortService antwortService;

    @Autowired
    private MockMvc mockMvc;

    private List<Kontaktperson> kontaktpersonen;
    private List<Positionssender> positionssenders;
    private List<Nachricht> nachrichten;
    private DemenziellErkrankter demenziellErkrankter;
    private Ausnahmesituation ausnahmesituation;
    private Antwort antwort;

    @Before
    public void setUp(){
        AusnahmesituationConsumer ausnahmesituationConsumer = new AusnahmesituationConsumer();

        this.kontaktpersonen = new ArrayList<>();
        this.kontaktpersonen.add(new KontaktpersonBuilder().build());

        this.positionssenders = new ArrayList<>();
        this.positionssenders.add(new PositionssenderBuilder().build());

        Nachricht nachricht = new Nachricht(NACHRICHT_TEXT, this.kontaktpersonen.get(0));

        this.nachrichten = new ArrayList<>();
        this.nachrichten.add(nachricht);

        Position position = new Position();
        position.setLaengengrad(15.0);
        position.setBreitengrad(20.0);

        this.demenziellErkrankter = new DementiellErkrankterBuilder()
                .setKontaktpersonen(this.kontaktpersonen)
                .setPositionssender(this.positionssenders)
                .build();

        this.ausnahmesituation = new AusnahmesituationBuilder()
                .setEntityId(this.demenziellErkrankter.getId())
                .setPositionssender(this.positionssenders.get(0))
                .setHilfeUnterwegs(false)
                .setPosition(position)
                .build();

        this.antwort = new Antwort(AntwortTyp.KANN_HELFEN);
    }

    @Test
    public void shouldSendStatusOkWhenValidRequest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        when(antwortService.saveAntwortAndCheckAntwortTyp(any(String.class), any(Antwort.class))).thenReturn(antwort);

        JSONObject json = new JSONObject();
        json.put("antwortTyp", AntwortTyp.KANN_HELFEN);
        json.put("antwortId", antwort.getEntityId());

        MvcResult result = mockMvc.perform(post("/nachricht/" + nachrichten.get(0).getEntityId() + "/antwort")
                .contentType(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                .content(json.toString())
                .accept(javax.ws.rs.core.MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}
