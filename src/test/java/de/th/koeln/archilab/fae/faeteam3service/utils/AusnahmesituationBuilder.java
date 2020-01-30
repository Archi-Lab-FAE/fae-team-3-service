package de.th.koeln.archilab.fae.faeteam3service.utils;

import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.Ausnahmesituation;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.Position;
import de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender.persistance.Positionssender;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.Nachricht;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.NachrichtText;

import java.util.Set;

public class AusnahmesituationBuilder {
    private String entityId;
    private Positionssender positionssender;
    private NachrichtText text = new NachrichtText("Foo bar");
    private Set<Nachricht> nachrichten;
    private boolean hilfeUnterwegs;
    private Position position;

    public AusnahmesituationBuilder() {
    }

    public AusnahmesituationBuilder setEntityId(String entityId) {
        this.entityId = entityId;
        return this;
    }

    public AusnahmesituationBuilder setPositionssender(Positionssender positionssender) {
        this.positionssender = positionssender;
        return this;
    }

    public AusnahmesituationBuilder setText(String text) {
        this.text = new NachrichtText(text);
        return this;
    }

    public AusnahmesituationBuilder setNachrichten(Set<Nachricht> nachrichten) {
        this.nachrichten = nachrichten;
        return this;
    }

    public AusnahmesituationBuilder setHilfeUnterwegs(boolean hilfeUnterwegs) {
        this.hilfeUnterwegs = hilfeUnterwegs;
        return this;
    }

    public AusnahmesituationBuilder setPosition(Position position) {
        this.position = position;
        return this;
    }

    public Ausnahmesituation build() {
        return new Ausnahmesituation(positionssender, text, nachrichten, hilfeUnterwegs, position);
    }
}