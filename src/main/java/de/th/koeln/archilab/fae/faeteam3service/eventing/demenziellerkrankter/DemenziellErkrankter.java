package de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter;

import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.Kontaktperson;

import de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender.Positionssender;

import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "demenziellErkrankte")
public class DemenziellErkrankter {

  @Id
  private String id;
  private String name;
  private String vorname;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "demenziellErkrankte_id")
  private List<Kontaktperson> kontaktpersonen;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "demenziellErkrankte_id")
  private List<Positionssender> positionssender;


  public DemenziellErkrankter() {
    this.id = UUID.randomUUID().toString();
  }
}