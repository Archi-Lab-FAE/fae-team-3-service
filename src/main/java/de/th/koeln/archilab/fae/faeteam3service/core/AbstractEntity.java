package de.th.koeln.archilab.fae.faeteam3service.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

@ToString
@MappedSuperclass
public abstract class AbstractEntity {
  @Id private String entityId;
  private ZonedDateTime created;

  @JsonIgnore private long version = -1;

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

  /**
   * Function to Compare Objects.
   * @param o Object to compare to
   * @return boolean
   */
  public boolean equals(final Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof AbstractEntity)) {
      return false;
    }
    final AbstractEntity other = (AbstractEntity) o;
    if (!other.canEqual((Object) this)) {
      return false;
    }
    final Object this$entityId = this.getEntityId();
    final Object other$entityId = other.getEntityId();
    if (!Objects.equals(this$entityId, other$entityId)) {
      return false;
    }
    final Object this$created = this.created;
    final Object other$created = other.created;
    if (!Objects.equals(this$created, other$created)) {
      return false;
    }
    return this.getVersion() == other.getVersion();
  }

  protected boolean canEqual(final Object other) {
    return other instanceof AbstractEntity;
  }

  /**
   * Calculates a hash.
   * @return int
   */
  public int hashCode() {
    final int prime = 59;
    int result = 1;
    final Object $entityId = this.getEntityId();
    result = result * prime + ($entityId == null ? 43 : $entityId.hashCode());
    final Object $created = this.created;
    result = result * prime + ($created == null ? 43 : $created.hashCode());
    final long $version = this.getVersion();
    result = result * prime + (int) ($version >>> 32 ^ $version);
    return result;
  }
}
