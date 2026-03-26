package org.examples.factory;

import org.examples.event.Event;
import org.examples.event.EventReunion;
import org.examples.objets.*;

import java.time.LocalDateTime;

public class EventReunionFactory implements EventFactory {
    public EventReunionFactory(){}
    @Override
    public Event createEvent(Title title, Personne proprietaire, LocalDateTime dateDebut, Duree dureeMinutes,
                             Lieu lieu, Personne[] participants, FrequenceJour frequenceJour) {
        return new EventReunion(title,proprietaire,dateDebut,dureeMinutes,lieu,participants);
    }


}
