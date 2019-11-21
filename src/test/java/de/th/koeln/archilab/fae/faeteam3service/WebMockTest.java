package de.th.koeln.archilab.fae.faeteam3service;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.th.koeln.archilab.fae.faeteam3service.entity.ausnahmesituation.Ausnahmesituation;
import de.th.koeln.archilab.fae.faeteam3service.entity.ausnahmesituation.DementiellErkrankter;
import de.th.koeln.archilab.fae.faeteam3service.entity.nachricht.Nachricht;
import de.th.koeln.archilab.fae.faeteam3service.repository.AusnahmesituationRepository;
import de.th.koeln.archilab.fae.faeteam3service.repository.NachrichtRepository;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Log
public class WebMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AusnahmesituationRepository ausnahmesituationRepository;

    @MockBean
    private NachrichtRepository nachrichtRepository;

    private Ausnahmesituation ausnahmesituation;
    private Ausnahmesituation ausnahmesituation1;
    private DementiellErkrankter dementiellErkrankter;
    private DementiellErkrankter dementiellErkrankter1;

    @Before
    public void setUp() {
        ausnahmesituation = new Ausnahmesituation();
        dementiellErkrankter = new DementiellErkrankter("1");
        ausnahmesituation.setDementiellErkrankter(dementiellErkrankter);

        ausnahmesituation1 = new Ausnahmesituation();
        dementiellErkrankter1 = new DementiellErkrankter("2");
        ausnahmesituation1.setDementiellErkrankter(dementiellErkrankter1);
    }

    @Test
    public void shouldSendStatusOkWhenValidRequest() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        when(ausnahmesituationRepository.save(any(Ausnahmesituation.class))).thenReturn(ausnahmesituation);
        when(nachrichtRepository.save(any(Nachricht.class))).thenReturn(new Nachricht());

        MvcResult result = mockMvc.perform(post("/level-2/ausnahmesituation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dementiellErkrankter))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Ausnahmesituation created = mapper.readValue(result.getResponse().getContentAsString(), Ausnahmesituation.class);
        assertEquals(created.getDementiellErkrankter().getDementiellErkrankterId(), "1");
    }

    @Test
    public void shouldReturnAllAusnahmesituationenWhenRequested() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        when(ausnahmesituationRepository.findAll()).thenReturn(Arrays.asList(ausnahmesituation, ausnahmesituation1));

        MvcResult result = mockMvc.perform(get("/level-2/ausnahmesituation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        List<Ausnahmesituation> requested = Arrays.asList(mapper.readValue(result.getResponse().getContentAsString(), Ausnahmesituation[].class));
        Assert.assertEquals(Arrays.asList(ausnahmesituation, ausnahmesituation1), requested);
    }
}
