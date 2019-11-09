package de.th.koeln.archilab.fae.faeteam3service.entity.message;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class MessageText {

    private String text = "This is a test";
}
