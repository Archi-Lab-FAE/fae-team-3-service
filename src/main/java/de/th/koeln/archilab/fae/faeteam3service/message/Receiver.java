package de.th.koeln.archilab.fae.faeteam3service.message;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Receiver {

    private String receiverId;

    public Receiver(String receiverId){
        if(!Receiver.isValid(receiverId)){
            throw new IllegalArgumentException("Not a valid reciverId!");
        }
        this.receiverId = receiverId;
    }

    public static boolean isValid(String receiverId){
        return true;
    }
}
