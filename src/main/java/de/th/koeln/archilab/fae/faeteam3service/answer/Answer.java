package de.th.koeln.archilab.fae.faeteam3service.answer;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import de.th.koeln.archilab.fae.faeteam3service.core.AbstractEntity;
import lombok.*;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Answer extends AbstractEntity {

    @Setter
    @JsonUnwrapped
    private TimestampW timestampW;

    @Setter
    @JsonUnwrapped
    private Type type;

    @Setter
    @JsonUnwrapped
    private MessageId messageId;
}
