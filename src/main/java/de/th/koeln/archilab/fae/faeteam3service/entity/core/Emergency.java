package de.th.koeln.archilab.fae.faeteam3service.entity.core;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import de.th.koeln.archilab.fae.faeteam3service.entity.Abstract;
import de.th.koeln.archilab.fae.faeteam3service.entity.message.Message;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Emergency extends Abstract {

    @Valid
    @Setter
    @JsonUnwrapped
    private Affected affected;

    @OneToMany(mappedBy = "emergency",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Message> messages = new HashSet<Message>();

    public void addMessage(Message message) {
        if(!messages.contains(message)){
            this.messages.add(message);
            message.setEmergency(this);
        }
    }
}
