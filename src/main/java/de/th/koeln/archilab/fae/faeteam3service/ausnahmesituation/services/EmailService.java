package de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.services;

import de.th.koeln.archilab.fae.faeteam3service.nachricht.Nachricht;

public interface EmailService {
    void sendMessage(String to, Nachricht nachricht);
}