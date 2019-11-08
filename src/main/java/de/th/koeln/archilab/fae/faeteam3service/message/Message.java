package de.th.koeln.archilab.fae.faeteam3service.message;

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
public class Message extends AbstractEntity {

    //TODO not null checks

    @CreationTimestamp
    private LocalDateTime timestamp;

    @Setter
    @JsonUnwrapped
    private AffectedId affectedId;

    @Setter
    @JsonUnwrapped
    private ReceiverId receiverId;
}