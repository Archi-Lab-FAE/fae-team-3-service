package de.th.koeln.archilab.fae.faeteam3service.utils;

import de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender.persistance.Positionssender;

import java.util.Date;

public class PositionssenderBuilder {
    private String id = "0a1ad64b-48ff-44e3-8260-9b584e875ac1";
    private Date letzteWartung;

    public PositionssenderBuilder() {
    }

    public PositionssenderBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public PositionssenderBuilder setLetzteWartung(Date letzteWartung) {
        this.letzteWartung = letzteWartung;
        return this;
    }

    public Positionssender build() {
        return new Positionssender(this.id);
    }
}