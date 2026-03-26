package org.examples.factory;

import org.examples.event.Event;
import org.examples.event.EventPeriodique;
import org.examples.objets.*;

import java.time.LocalDateTime;

public class EventPeriodiqueFactory implements EventFactory {
    public EventPeriodiqueFactory() {}
    @Override
    public Event createEvent(Title title, Personne proprietaire, LocalDateTime dateDebut, Duree dureeMinutes,
                             Lieu lieu, Personne[] participants, FrequenceJour frequenceJour) {
        return new EventPeriodique(title,proprietaire,dateDebut,dureeMinutes,participants,frequenceJour);
    }
}
