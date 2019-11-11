package de.th.koeln.archilab.fae.faeteam3service.entity.message;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import de.th.koeln.archilab.fae.faeteam3service.entity.Abstract;
import de.th.koeln.archilab.fae.faeteam3service.entity.answer.Answer;
import de.th.koeln.archilab.fae.faeteam3service.entity.core.Emergency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Data
@AllArgsConstructor
@ToString(callSuper = true)
public class Message extends Abstract {

    @JsonUnwrapped
    private MessageText messageText;

    @Setter
    @ManyToOne
    @JsonBackReference
    @ToString.Exclude
    private Emergency emergency;

    @OneToOne(cascade = CascadeType.ALL)
    private Answer answer;

    public Message() {
        this.messageText = new MessageText();
    }
}
