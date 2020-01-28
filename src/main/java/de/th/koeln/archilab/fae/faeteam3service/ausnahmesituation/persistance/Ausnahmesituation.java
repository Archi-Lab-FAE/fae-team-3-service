package de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import de.th.koeln.archilab.fae.faeteam3service.core.AbstractEntity;
import de.th.koeln.archilab.fae.faeteam3service.eventing.EventPublishingEntityListener;
import de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender.persistance.Positionssender;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.Nachricht;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.NachrichtText;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@EntityListeners(EventPublishingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString(callSuper = true)
public class Ausnahmesituation extends AbstractEntity {

  @OneToOne
  @Valid
  @Setter
  @JsonUnwrapped
  private Positionssender positionssender;

  @Getter
  @JsonUnwrapped
  private NachrichtText nachrichtText;

  @OneToMany(mappedBy = "ausnahmesituation",
      cascade = CascadeType.ALL,
      fetch = FetchType.EAGER)
  @JsonManagedReference
  private Set<Nachricht> nachrichten = new HashSet<>();

  @Setter
  @Getter
  private Boolean hilfeUnterwegs = false;

  @Override
  @JsonProperty("ausnahmesituationId")
  public String getEntityId() {
    return super.getEntityId();
  }

  @Override
  public String getEventClass() {
    return "ausnahmesituation";
  }

  public NachrichtText getNachrichtText() {
    return nachrichtText;
  }

  public void addNachricht(Nachricht nachricht) {
    if (!nachrichten.contains(nachricht)) {
      nachricht.setAusnahmesituation(this);
      this.nachrichten.add(nachricht);
    }
  }

}

