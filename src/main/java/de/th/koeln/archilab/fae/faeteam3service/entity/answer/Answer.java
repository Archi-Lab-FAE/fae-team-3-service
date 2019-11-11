package de.th.koeln.archilab.fae.faeteam3service.entity.answer;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import de.th.koeln.archilab.fae.faeteam3service.entity.Abstract;
import lombok.*;

import javax.persistence.Entity;

@Entity
@Data
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class Answer extends Abstract {

    @Setter
    @JsonUnwrapped
    private AnswerType type;

}
