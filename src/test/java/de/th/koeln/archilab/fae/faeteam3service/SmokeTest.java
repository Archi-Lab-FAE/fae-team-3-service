package de.th.koeln.archilab.fae.faeteam3service;

import de.th.koeln.archilab.fae.faeteam3service.controller.AntwortController;
import de.th.koeln.archilab.fae.faeteam3service.controller.AusnahmesituationController;
import de.th.koeln.archilab.fae.faeteam3service.controller.NachrichtController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {

    @Autowired
    private AusnahmesituationController ausnahmesituationController;

    @Autowired
    private AntwortController antwortController;

    @Autowired
    private NachrichtController nachrichtController;

    @Test
    public void contextLoads() {
        assertThat(ausnahmesituationController).isNotNull();
        assertThat(antwortController).isNotNull();
        assertThat(nachrichtController).isNotNull();
    }

}
