package de.th.koeln.archilab.fae.faeteam3service.entity.core;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import de.th.koeln.archilab.fae.faeteam3service.entity.Abstract;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.Valid;

@Entity
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Emergency extends Abstract {

    @Valid
    @Setter
    @JsonUnwrapped
    private Affected affected;

}
