package de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;

@Data
@AllArgsConstructor
@Entity
@Table(name = "Positionen")
@Log
@Embeddable
@Setter(AccessLevel.NONE)
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Positionssender {

  @Id
  @NotNull(message = "Should not be null!")
  @NotEmpty(message = "Should not be empty!")
  private String positionssenderId;

}
