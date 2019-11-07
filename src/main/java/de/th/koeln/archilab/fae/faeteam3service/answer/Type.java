package de.th.koeln.archilab.fae.faeteam3service.answer;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Type {

    private String type;

    public Type(String type){
        if(!Type.isValid(type)){
            throw new IllegalArgumentException("Not a valid type!!");
        }
        this.type = type;
    }

    public static boolean isValid(String type){
        return true;
    }
}
