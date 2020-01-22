package de.th.koeln.archilab.fae.faeteam3service.nachricht.service;

import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.AusnahmesituationRepository;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.Nachricht;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TimeoutService {
  @Autowired
  AusnahmesituationRepository ausnahmesituationRepository;

  @Autowired
  NachrichtenService nachrichtenService;

  @Async
  public void checkTimeout(String ausnahmesituationId) throws InterruptedException {
    Thread.sleep(TimeUnit.MINUTES.toMillis(1));

    ausnahmesituationRepository.findById(ausnahmesituationId).ifPresent(ausnahmesituation -> {
      for (Nachricht nachricht : ausnahmesituation.getNachrichten()) {
        if (nachricht.getAntwort() == null) {
          nachrichtenService.send(ausnahmesituation);
        }
      }
    });
  }
}
