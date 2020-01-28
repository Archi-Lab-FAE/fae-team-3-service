package de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.persistance;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "kontaktPersonen")
@ToString
@NoArgsConstructor
public class Kontaktperson {

  @Id
  private String id;
  private String name;
  private String vorname;
  private String telefonnummer;
  private Boolean aktiv;

}