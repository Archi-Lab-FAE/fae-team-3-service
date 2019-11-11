package de.th.koeln.archilab.fae.faeteam3service.entity.answer;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@Data
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class AnswerType {
    private String type;
}
