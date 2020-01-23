package de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Ausnahmesituation not found")
public class AusnahmesituationNotFoundException extends RuntimeException {
}