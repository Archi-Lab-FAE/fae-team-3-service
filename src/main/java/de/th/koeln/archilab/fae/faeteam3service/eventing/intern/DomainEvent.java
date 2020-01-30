package de.th.koeln.archilab.fae.faeteam3service.eventing.intern;

import de.th.koeln.archilab.fae.faeteam3service.core.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class DomainEvent {
  private String id;
  private String key;
  private ZonedDateTime timestamp;
  private Long version;
  private String type;
  private AbstractEntity payload;
}
