package de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender;

import java.util.Date;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "Positionen")
public class Positionssender {

  @Id
  private String id;
  private Date letzteWartung;

  public Positionssender() {
    this.id = UUID.randomUUID().toString();
  }
}