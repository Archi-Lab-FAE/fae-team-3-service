package de.th.koeln.archilab.fae.faeteam3service.entity.antwort;


import lombok.Data;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@Data
@ToString
class AntwortTyp {

    private String typ;

    AntwortTyp() {
        this.typ = "Das ist auch ein Test!";
    }
}
