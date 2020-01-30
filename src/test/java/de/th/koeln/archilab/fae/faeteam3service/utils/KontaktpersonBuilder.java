package de.th.koeln.archilab.fae.faeteam3service.utils;

import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.persistance.Kontaktperson;

public class KontaktpersonBuilder {
    private String id = "3e3d4fbd-1e38-46b2-8987-82dbcc1de23c";
    private String name = "Fritz";
    private String vorname = "Fritz";
    private String telefonnummer = "12645981";
    private Boolean aktiv = true;

    public KontaktpersonBuilder() {
    }

    public KontaktpersonBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public KontaktpersonBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public KontaktpersonBuilder setVorname(String vorname) {
        this.vorname = vorname;
        return this;
    }

    public KontaktpersonBuilder setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
        return this;
    }

    public KontaktpersonBuilder setAktiv(Boolean aktiv) {
        this.aktiv = aktiv;
        return this;
    }

    public Kontaktperson build() {
        return new Kontaktperson(this.id, this.name, this.vorname, this.telefonnummer, this.aktiv);
    }
}