package de.th.koeln.archilab.fae.faeteam3service.eventing.intern;

import de.th.koeln.archilab.fae.faeteam3service.core.AbstractEntity;

import java.time.ZonedDateTime;

public class DomainEvent {
  private String id;
  private String key;
  private ZonedDateTime timestamp;
  private Long version;
  private String type;
  private AbstractEntity payload;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public ZonedDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(ZonedDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(final Long version) {
    this.version = version;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public AbstractEntity getPayload() {
    return payload;
  }

  public void setPayload(AbstractEntity payload) {
    this.payload = payload;
  }
}
