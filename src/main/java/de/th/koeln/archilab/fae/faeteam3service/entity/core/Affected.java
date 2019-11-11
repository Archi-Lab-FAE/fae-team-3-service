package de.th.koeln.archilab.fae.faeteam3service.entity.core;

import lombok.*;
import lombok.extern.java.Log;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Log
@Embeddable
@Data
@Setter(AccessLevel.NONE)
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Affected {

    @NotNull(message = "Should not be null!")
    @NotEmpty(message = "Should not be empty!")
    private String affected;

}
