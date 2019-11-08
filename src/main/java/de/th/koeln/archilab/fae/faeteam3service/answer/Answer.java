package de.th.koeln.archilab.fae.faeteam3service.answer;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import de.th.koeln.archilab.fae.faeteam3service.core.AbstractEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Answer extends AbstractEntity {

    //TODO not null checks

    @CreationTimestamp
    private LocalDateTime timestamp;

    @Setter
    @JsonUnwrapped
    private Type type;

    //add relation
    @Setter
    @JsonUnwrapped
    private MessageId messageId;
}
