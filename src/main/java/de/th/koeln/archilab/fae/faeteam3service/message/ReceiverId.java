package de.th.koeln.archilab.fae.faeteam3service.message;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReceiverId {

    private String receiverId;

    public ReceiverId(String receiverId){
        if(!ReceiverId.isValid(receiverId)){
            throw new IllegalArgumentException("Not a valid receiverId");
        }
        this.receiverId = receiverId;
    }

    public static boolean isValid(String receiverId){
        return true;
    }

}
