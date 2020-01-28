package de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender.persistance;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;

@Getter
@Setter
@Entity
@Table(name = "Positionen")
@Log
@Embeddable
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Positionssender {

  @Id
  @NotNull(message = "Should not be null!")
  @NotEmpty(message = "Should not be empty!")
  private String positionssenderId;

}
