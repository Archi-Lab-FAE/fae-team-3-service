package de.th.koeln.archilab.fae.faeteam3service.message;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmpfaengerId {

    private String empfaengerId;

    public EmpfaengerId(String empfaengerId){
        if(!de.th.koeln.archilab.fae.faeteam3service.message.EmpfaengerId.isValid(empfaengerId)){
            throw new IllegalArgumentException("Not a valid empfaengerId");
        }
        this.empfaengerId = empfaengerId;
    }

    public static boolean isValid(String empfaengerId){
        return true;
    }

}
