package de.th.koeln.archilab.fae.faeteam3service;

import de.th.koeln.archilab.fae.faeteam3service.antwort.AntwortController;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.AusnahmesituationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class SmokeTest {

    @Autowired
    private AusnahmesituationController ausnahmesituationController;

    @Autowired
    private AntwortController antwortController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(ausnahmesituationController).isNotNull();
        assertThat(antwortController).isNotNull();
    }

}