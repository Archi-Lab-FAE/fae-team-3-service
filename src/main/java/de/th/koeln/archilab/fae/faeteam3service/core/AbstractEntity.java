package de.th.koeln.archilab.fae.faeteam3service.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

@EqualsAndHashCode
@ToString
@MappedSuperclass
public abstract class AbstractEntity {
  @Id
  private String entityId;
  private ZonedDateTime created;

  @JsonIgnore
  private long version = -1;

  public AbstractEntity() {
    this.entityId = UUID.randomUUID().toString();
    this.created = ZonedDateTime.now(ZoneOffset.UTC);
  }

  public AbstractEntity(String entityId) {
    this.entityId = entityId;
  }

  public String getEntityId() {
    return entityId;
  }

  public long getVersion() {
    return version;
  }

  public void incrementVersion() {
    this.version = getVersion() + 1;
  }

  @JsonIgnore
  public abstract String getEventClass();
}
