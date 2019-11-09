package de.th.koeln.archilab.fae.faeteam3service.entity.core;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Log
@Embeddable
@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Affected {

    @NotNull(message = "Should not be null!")
    @NotEmpty(message = "Should not be empty!")
    private String affected;

}
