package de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter.persistance;

import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.persistance.Kontaktperson;
import de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender.persistance.Positionssender;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "demenziellErkrankte")
@ToString
public class DemenziellErkrankter {

  @Id
  private String id;
  private String name;
  private String vorname;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "demenziellErkrankte_id")
  private List<Kontaktperson> kontaktpersonen;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "demenziellErkrankte_id")
  private List<Positionssender> positionssender;


  public DemenziellErkrankter() {
    this.id = UUID.randomUUID().toString();
  }
}