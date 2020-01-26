package de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.persistance;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "kontaktPersonen")
@ToString
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