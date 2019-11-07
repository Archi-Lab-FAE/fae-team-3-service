package de.th.koeln.archilab.fae.faeteam3service.core;

import java.util.UUID;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;

//From: https://github.com/Archi-Lab-FAE/fae-shop-demo by @JannIntveen

@EqualsAndHashCode
@MappedSuperclass
public abstract class AbstractEntity {

  @Id
  @Getter
  private UUID id;

  protected AbstractEntity() {
    this.id = UUID.randomUUID();
  }
}
