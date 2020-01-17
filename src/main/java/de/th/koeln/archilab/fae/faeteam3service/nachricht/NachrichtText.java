package de.th.koeln.archilab.fae.faeteam3service.nachricht;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@ToString
@Data
public class NachrichtText {

    private String text;

    NachrichtText() {
        this.text = "Kannst du helfen?";
    }

}
