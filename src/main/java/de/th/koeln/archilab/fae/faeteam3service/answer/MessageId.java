package de.th.koeln.archilab.fae.faeteam3service.answer;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageId {

    private String messageId;

    public MessageId(String messageId){
        if(!MessageId.isValid(messageId)){
            throw new IllegalArgumentException("Not a valid messageId");
        }
        this.messageId = messageId;
    }

    public static boolean isValid(String messageId){
        return true;
    }

}
