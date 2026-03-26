package org.examples.event;

import org.examples.objets.Duree;
import org.examples.objets.FrequenceJour;
import org.examples.objets.Personne;
import org.examples.objets.Title;

import java.time.LocalDateTime;

public class EventPeriodique extends Event{

    public Personne[] participants; // séparés par virgules (pour REUNION uniquement)
    public FrequenceJour frequenceJours; // uniquement pour PERIODIQUE

    public EventPeriodique(Title title, Personne proprietaire, LocalDateTime dateDebut, Duree dureeMinutes, Personne[] participants, FrequenceJour frequenceJours) {
        super(title, proprietaire, dateDebut, dureeMinutes);
        this.participants = participants;
        this.frequenceJours = frequenceJours;
    }

    public String description() {
        return "Événement périodique : " + title.getTitle() + " tous les " + frequenceJours.getFrequenceJour() + " jours";
    }

    public boolean estEnConflitAvec (Event event2,String type) {
        return false;
    }
    public String getType(){
        return "PERIODIQUE";
    }
    @Override
    public boolean estPresentDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        LocalDateTime temp = this.dateDebut;
        LocalDateTime tempPrec = null;
        while (temp.isBefore(fin)) {
            tempPrec = temp;
            temp = temp.plusDays(this.frequenceJours.getFrequenceJour());
        }
        return tempPrec.isAfter(debut);
    }
}