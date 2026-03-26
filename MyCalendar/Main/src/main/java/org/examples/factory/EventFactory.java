package org.examples.factory;

import org.examples.event.Event;
import org.examples.objets.*;

import java.time.LocalDateTime;

public interface EventFactory {
    public Event createEvent(Title title, Personne proprietaire, LocalDateTime dateDebut, Duree dureeMinutes,
                             Lieu lieu, Personne[] participants, FrequenceJour frequenceJours);
}
