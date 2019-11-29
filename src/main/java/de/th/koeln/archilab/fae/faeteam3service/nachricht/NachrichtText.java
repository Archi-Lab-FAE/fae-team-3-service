package de.th.koeln.archilab.fae.faeteam3service.nachricht;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@ToString
@Data
class NachrichtText {

    private String text;

    NachrichtText() {
        this.text = "Das ist ein Test!";
    }

}
