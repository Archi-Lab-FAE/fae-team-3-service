package de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;


@Log
@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class NachrichtText {
  private String text;
}
