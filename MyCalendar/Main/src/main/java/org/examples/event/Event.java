package org.examples.event;

import org.examples.objets.Duree;
import org.examples.objets.Personne;
import org.examples.objets.Title;

import java.time.LocalDateTime;

public abstract class Event {
    public Title title;
    public Personne proprietaire;
    public LocalDateTime dateDebut;
    public Duree dureeMinutes;

    public Event(Title title, Personne proprietaire, LocalDateTime dateDebut, Duree dureeMinutes) {
        this.title = title;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.dureeMinutes = dureeMinutes;
    }
    public Boolean conflit(Event event) {
        return event.estEnConflitAvec(this,this.getType());
    }
    public abstract boolean estEnConflitAvec(Event simple, String type);
    public abstract boolean estPresentDansPeriode(LocalDateTime debut, LocalDateTime fin);
    public abstract String getType();
    public abstract String description();
    }
