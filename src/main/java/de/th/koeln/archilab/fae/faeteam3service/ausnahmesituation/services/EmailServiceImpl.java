package de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.services;

import de.th.koeln.archilab.fae.faeteam3service.nachricht.Nachricht;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Log
@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    public JavaMailSender emailSender;

    public void sendMessage(String to, Nachricht nachricht) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(buildSubject());
        message.setText(buildText(nachricht));
        emailSender.send(message);
        log.info("E-Mail an " + to + " gesendet...");
    }

    // TODO: Name ersetzen, sobald wir die notwendigen Daten dafür bekommen
    private String buildSubject() {
        String subject = "Maria benötigt deine Hilfe!";

        return subject;
    }

    // TODO: Namen ersetzen, sobald wir die notwendigen Daten dafür bekommen
    private String buildText(Nachricht nachricht) {
        return String.join(System.getProperty("line.separator"),
                "Hallo Fritz,",
                "",
                "Maria benötgt gerade deine Hilfe!",
                "",
                "Klicke auf den folgenden Link um uns mitzuteilen, ob du Maria helfen kannst: ",
                "https://fae.aletutto.de/?nachricht=" + nachricht.getNachrichtId());
    }

}