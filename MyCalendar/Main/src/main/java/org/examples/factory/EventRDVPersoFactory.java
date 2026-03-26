package org.examples.factory;

import org.examples.event.Event;
import org.examples.event.EventRDVPerso;
import org.examples.objets.*;

import java.time.LocalDateTime;

public class EventRDVPersoFactory implements EventFactory{
    public EventRDVPersoFactory() {}
    public Event createEvent(Title title, Personne proprietaire, LocalDateTime dateDebut, Duree dureeMinutes,
                             Lieu lieu, Personne[] participants, FrequenceJour frequenceJour) {
        return new EventRDVPerso(title,proprietaire,dateDebut,dureeMinutes);
    }
}
