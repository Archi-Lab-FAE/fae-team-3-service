package de.th.koeln.archilab.fae.faeteam3service.eventing;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.th.koeln.archilab.fae.faeteam3service.core.AbstractEntity;
import de.th.koeln.archilab.fae.faeteam3service.eventing.intern.EventPublishingEntityListenerAdapter;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import lombok.extern.java.Log;

@Log
public class EventPublishingEntityListener {

  @PrePersist
  void onPersist(AbstractEntity entity) {
    publishEvent(entity, "created");
  }

  @PreUpdate
  public void onUpdate(AbstractEntity entity) {
    publishEvent(entity, "updated");
  }

  @PreRemove
  public void onRemove(AbstractEntity entity) {
    publishEvent(entity, "deleted");
  }

  private void publishEvent(AbstractEntity entity, String action) {
    try {
      EventPublishingEntityListenerAdapter.lookup().send(entity, action);
    } catch (JsonProcessingException e) {
      log.info(e.getMessage());
    }
  }
}
