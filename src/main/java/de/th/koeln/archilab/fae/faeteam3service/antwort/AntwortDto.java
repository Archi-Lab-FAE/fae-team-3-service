package de.th.koeln.archilab.fae.faeteam3service.antwort;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AntwortDto {
  private String entityId;
  private String antwortTyp;

  public AntwortDto() {
    this.entityId = UUID.randomUUID().toString();
  }

  @JsonProperty("antwortId")
  public String getEntityId() {
    return this.entityId;
  }

}
