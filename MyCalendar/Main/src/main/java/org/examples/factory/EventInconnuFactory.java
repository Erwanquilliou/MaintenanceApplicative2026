package org.examples.factory;

import org.examples.objets.*;
import org.examples.event.Event;
import org.examples.event.EventInconnu;

import java.time.LocalDateTime;

public class EventInconnuFactory implements EventFactory{
    public EventInconnuFactory() {}
    public Event createEvent(Title title, Personne proprietaire, LocalDateTime dateDebut, Duree dureeMinutes,
                             Lieu lieu, Personne[] participants, FrequenceJour frequenceJour) {
        return new EventInconnu(title,proprietaire,dateDebut,dureeMinutes);
    }
}
