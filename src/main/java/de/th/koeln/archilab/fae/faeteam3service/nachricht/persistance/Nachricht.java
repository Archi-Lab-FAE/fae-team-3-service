package de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import de.th.koeln.archilab.fae.faeteam3service.antwort.persistance.Antwort;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.Ausnahmesituation;
import de.th.koeln.archilab.fae.faeteam3service.core.AbstractEntity;
import de.th.koeln.archilab.fae.faeteam3service.eventing.EventPublishingEntityListener;
import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.persistance.Kontaktperson;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.*;

@Entity
@EntityListeners(EventPublishingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

  public Nachricht(String id) {
    super(id);
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

  @Override
  public String getEventClass() {
    return "nachricht";
  }
}
