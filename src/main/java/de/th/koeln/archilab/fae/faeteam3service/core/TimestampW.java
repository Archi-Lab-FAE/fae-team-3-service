package de.th.koeln.archilab.fae.faeteam3service.core;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.sql.Timestamp;

@Embeddable
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimestampW {

    private Timestamp timestampW;

    public TimestampW(Timestamp timestampW){
        if(!TimestampW.isValid(timestampW)){
            throw new IllegalArgumentException("Timestamp not Valid!!");
        }
        this.timestampW = timestampW;
    }

    //do something important
    public static boolean isValid(Timestamp timestampW){
        return true;
    }
}
