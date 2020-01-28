package de.th.koeln.archilab.fae.faeteam3service.antwort;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_REQUIRED,
    reason = "Die Ausnahmesituation ist bereits in Bearbeitung")
public class AntwortNotAllowedException extends RuntimeException {
}