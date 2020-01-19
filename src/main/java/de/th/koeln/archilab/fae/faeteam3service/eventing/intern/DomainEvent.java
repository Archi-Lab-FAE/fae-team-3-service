package de.th.koeln.archilab.fae.faeteam3service.eventing.intern;

import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DomainEvent {
  @Id
  private String id;
  private String key;
  private ZonedDateTime timestamp;
  private Long version;
  private String type;
  private String payload;

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

  public String getPayload() {
    return payload;
  }

  public void setPayload(String payload) {
    this.payload = payload;
  }
}
