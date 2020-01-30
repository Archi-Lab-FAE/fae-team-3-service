package de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation;

import lombok.extern.java.Log;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * We cannot perform this type of test because the KAFKA is not available when the test is performed.
 * Server throws error on startup (endless loop)
 * All attempts at a solution have failed.
 * <p>
 * Local Setup with running Kafka works.
 */

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@Log
public class AusnahmesituationControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private DemenziellErkrankterRepository demenziellErkrankterRepository;
//
//    @MockBean
//    private AusnahmesituationService ausnahmesituationService;
//
//    @MockBean
//    private NachrichtRepository nachrichtRepository;
//
//    private Ausnahmesituation ausnahmesituation;
//    private DemenziellErkrankter dementiellErkrankter;
//    private Kontaktperson kontaktperson;
//    private Positionssender positionssender;
//
//    @Before
//    public void setUp() {
//        positionssender = new PositionssenderBuilder().build();
//        kontaktperson = new KontaktpersonBuilder().build();
//        dementiellErkrankter = new DementiellErkrankterBuilder()
//                .setKontaktpersonen(Collections.singletonList(kontaktperson))
//                .setPositionssender(Collections.singletonList(positionssender))
//                .build();
//
//        ausnahmesituation = new AusnahmesituationBuilder()
//                .setPositionssender(positionssender)
//                .build();
//
//    }
//
//    @Test
//    public void shouldSendStatusOkWhenValidRequest() throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        when(ausnahmesituationService.saveAusnahmesituationAndSendMessage(any(Ausnahmesituation.class))).thenReturn(ausnahmesituation);
//        when(nachrichtRepository.save(any(Nachricht.class))).thenReturn(new Nachricht());
//        when(demenziellErkrankterRepository.findDemenziellErkrankterByPositionssenderContains(any(Positionssender.class))).thenReturn(dementiellErkrankter);
//
//
//        MvcResult result = mockMvc.perform(post("/ausnahmesituation")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(dementiellErkrankter))
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn();
//
//        Ausnahmesituation created = mapper.readValue(result.getResponse().getContentAsString(), Ausnahmesituation.class);
//        DemenziellErkrankter foo = demenziellErkrankterRepository.findDemenziellErkrankterByPositionssenderContains(created.getPositionssender());
//
//        assertEquals(foo.getId(), dementiellErkrankter.getId());
//    }
}