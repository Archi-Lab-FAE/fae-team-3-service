package de.th.koeln.archilab.fae.faeteam3service.nachricht;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;


@Log
@Embeddable
@Data
@Setter(AccessLevel.NONE)
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NachrichtText {
  private String text;
}
