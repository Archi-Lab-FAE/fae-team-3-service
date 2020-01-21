package de.th.koeln.archilab.fae.faeteam3service.nachricht;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import de.th.koeln.archilab.fae.faeteam3service.antwort.Antwort;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.Ausnahmesituation;
import de.th.koeln.archilab.fae.faeteam3service.core.AbstractEntity;
import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.Kontaktperson;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@ToString(callSuper = true)
public class Nachricht extends AbstractEntity {

  @JsonUnwrapped
  private NachrichtText nachrichtText;

  @Setter
  @ManyToOne
  @JsonBackReference
  @ToString.Exclude
  private Ausnahmesituation ausnahmesituation;

  @OneToOne(cascade = CascadeType.ALL)
  private Antwort antwort;

  @OneToOne
  @Setter
  private Kontaktperson kontaktperson;

  public Nachricht() {
    this.nachrichtText = new NachrichtText();
  }

  public Nachricht(NachrichtText nachrichtenText, Kontaktperson kontaktperson) {
    this.nachrichtText = nachrichtenText;
    this.kontaktperson = kontaktperson;
  }

  @Override
  @JsonProperty("nachrichtId")
  public String getEntityId() {
    return super.getEntityId();
  }
}
