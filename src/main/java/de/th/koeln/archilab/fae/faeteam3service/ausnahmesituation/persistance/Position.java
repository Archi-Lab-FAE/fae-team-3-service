package de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@Embeddable
@ToString
public class Position {
  private Double laengengrad;
  private Double breitengrad;
}
