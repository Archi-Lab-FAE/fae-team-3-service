package de.th.koeln.archilab.fae.faeteam3service.utils;

import de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter.persistance.DemenziellErkrankter;
import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.persistance.Kontaktperson;
import de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender.persistance.Positionssender;

import java.util.List;

public class DementiellErkrankterBuilder {
    private String id = "0a1ad64b-48ff-44e3-8260-9b584e875ac1";
    private String name = "Hilde";
    private String vorname = "Hilde";
    private Boolean zustimmung = true;
    private List<Kontaktperson> kontaktpersonen;
    private List<Positionssender> positionssender;

    public DementiellErkrankterBuilder() {
    }

    public DementiellErkrankterBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public DementiellErkrankterBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public DementiellErkrankterBuilder setVorname(String vorname) {
        this.vorname = vorname;
        return this;
    }

    public DementiellErkrankterBuilder setZustimmung(Boolean zustimmung) {
        this.zustimmung = zustimmung;
        return this;
    }

    public DementiellErkrankterBuilder setKontaktpersonen(List<Kontaktperson> kontaktpersonen) {
        this.kontaktpersonen = kontaktpersonen;
        return this;
    }

    public DementiellErkrankterBuilder setPositionssender(List<Positionssender> positionssender) {
        this.positionssender = positionssender;
        return this;
    }

    public DemenziellErkrankter build() {
        return new DemenziellErkrankter(this.id, this.name, this.vorname, this.zustimmung, this.kontaktpersonen, this.positionssender);
    }
}