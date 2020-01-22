package de.th.koeln.archilab.fae.faeteam3service.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@MappedSuperclass
public abstract class AbstractEntity {
  @Id
  private String entityId;
  private ZonedDateTime created;

  public AbstractEntity() {
    this.entityId = UUID.randomUUID().toString();
    this.created = ZonedDateTime.now(ZoneOffset.UTC);
  }

  public String getEntityId() {
    return entityId;
  }

  @JsonIgnore
  public abstract String getEventClass();
}
