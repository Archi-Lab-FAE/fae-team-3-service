package de.th.koeln.archilab.fae.faeteam3service.entity.message;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@ToString
@Data
class MessageText {

    private String text;

    public MessageText() {
        this.text = "This is a test";
    }

}
