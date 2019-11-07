package de.th.koeln.archilab.fae.faeteam3service.message;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import de.th.koeln.archilab.fae.faeteam3service.core.TimestampW;
import de.th.koeln.archilab.fae.faeteam3service.core.AbstractEntity;
import lombok.*;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Message extends AbstractEntity {

    @Setter
    @JsonUnwrapped
    private TimestampW timestampW;

    @Setter
    @JsonUnwrapped
    private BetroffenerId betroffenerId;

    @Setter
    @JsonUnwrapped
    private EmpfaengerId empfaengerId;
}