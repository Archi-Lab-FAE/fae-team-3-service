package de.th.koeln.archilab.fae.faeteam3service.message;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AffectedId {

    private String affectedId;

    public AffectedId(String affectedId){
        if(!AffectedId.isValid(affectedId)){
            throw new IllegalArgumentException("Not a valid affectedId");
        }
        this.affectedId = affectedId;
    }

    public static boolean isValid(String affectedId){
        return true;
    }

}
