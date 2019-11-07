package de.th.koeln.archilab.fae.faeteam3service.message;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BetroffenerId {

    private String betroffenerId;

    public BetroffenerId(String betroffenerId){
        if(!BetroffenerId.isValid(betroffenerId)){
            throw new IllegalArgumentException("Not a valid betroffenerId");
        }
        this.betroffenerId = betroffenerId;
    }

    public static boolean isValid(String betroffenerId){
        return true;
    }

}
