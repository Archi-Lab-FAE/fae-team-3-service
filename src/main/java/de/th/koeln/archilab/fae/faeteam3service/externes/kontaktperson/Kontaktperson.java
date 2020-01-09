package de.th.koeln.archilab.fae.faeteam3service.externes.kontaktperson;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@AllArgsConstructor
@Entity
@Table(name = "kontaktPersonen")
public class Kontaktperson {

    @Id
    private String id;
    private String name;
    private String vorname;
    private String telefonnummer;
    private Boolean aktiv;

    public Kontaktperson() {
        this.id = UUID.randomUUID().toString();
    }
}