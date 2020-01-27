package de.th.koeln.archilab.fae.faeteam3service.eventing.intern;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.th.koeln.archilab.fae.faeteam3service.core.AbstractEntity;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class EventPublishingEntityListenerAdapter implements ApplicationContextAware {

  private static ApplicationContext applicationContext;
  private final Logger logger = LoggerFactory.getLogger(EventPublishingEntityListenerAdapter.class);
  private final KafkaTemplate<String, String> template;
  private final ObjectMapper objectMapper;

  @Autowired
  public EventPublishingEntityListenerAdapter(
      final KafkaTemplate<String, String> template,
      final ObjectMapper objectMapper) {
    this.template = template;
    this.objectMapper = objectMapper;
  }

  public static EventPublishingEntityListenerAdapter lookup() {
    return applicationContext.getBean(EventPublishingEntityListenerAdapter.class);
  }

  public void send(final AbstractEntity entity, String action) throws JsonProcessingException {
    final DomainEvent domainEvent = toEvent(entity, action);

    ListenableFuture<SendResult<String, String>> future = this.template
        .send(entity.getEventClass(), entity.getEntityId(),
            this.objectMapper.writeValueAsString(domainEvent));

    future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
      @Override
      public void onSuccess(SendResult<String, String> result) {
        EventPublishingEntityListenerAdapter.this.logger
            .info("Successfully sent message with key: {}", result.getProducerRecord().key());
      }

      @Override
      public void onFailure(Throwable ex) {
        EventPublishingEntityListenerAdapter.this.logger
            .error("Error while sending message with message:");
        EventPublishingEntityListenerAdapter.this.logger.error(ex.getMessage());
      }
    });
  }

  private DomainEvent toEvent(final AbstractEntity entity, final String eventType) {
    try {
      final DomainEvent result = new DomainEvent();
      result.setId(UUID.randomUUID().toString());
      result.setKey(entity.getEntityId());
      result.setTimestamp(ZonedDateTime.now(ZoneOffset.UTC));
      result.setVersion(entity.getVersion());
      result.setType(entity.getEventClass() + "-" + eventType);
      result.setPayload(entity);
      return result;
    } catch (final Exception ex) {
      EventPublishingEntityListenerAdapter.this.logger.error("Could not create domain event", ex);
      throw new RuntimeException("could not create DomainEvent from Entity ", ex);
    }
  }

  @Override
  public void setApplicationContext(ApplicationContext context) {
    applicationContext = context;
  }
}
