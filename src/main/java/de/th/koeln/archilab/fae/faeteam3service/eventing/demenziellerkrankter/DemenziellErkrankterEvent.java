package de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Getter
@Setter
@JsonPropertyOrder({"id", "key", "version", "timestamp", "type", "payload"})
public class DemenziellErkrankterEvent {

  @NonNull
  public String id;
  @NonNull
  public String key;
  @NonNull
  public Long version;
  @NonNull
  public LocalDateTime timestamp;
  @NonNull
  public String type;
  @NonNull
  public DemenziellErkrankterDto payload;

}
