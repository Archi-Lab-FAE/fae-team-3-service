package de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
@ToString
public class Position {
  private Double laengengrad;
  private Double breitengrad;
}
