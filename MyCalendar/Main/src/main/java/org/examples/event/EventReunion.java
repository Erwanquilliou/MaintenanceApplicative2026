package org.examples.event;

import org.examples.objets.Duree;
import org.examples.objets.Lieu;
import org.examples.objets.Personne;
import org.examples.objets.Title;

import java.time.LocalDateTime;

public class EventReunion extends Event {

    public Lieu lieu; // utilisé seulement pour REUNION
    public Personne[] participants; // séparés par virgules (pour REUNION uniquement)

    public EventReunion(Title title, Personne proprietaire, LocalDateTime dateDebut, Duree dureeMinutes,
                        Lieu lieu, Personne[] participants) {
        super(title,proprietaire,dateDebut,dureeMinutes);
        this.lieu = lieu;
        this.participants = participants;
    }

    public String description() {
        String desc = "Réunion : " + title.getTitle() + " à " + lieu.getNomLieu() + " avec ";
        for(Personne p : participants) {
            desc += p.getNom() + ", ";
        }
        desc = desc.substring(0, desc.length() - 2);
        return desc;
    }
    public String getType(){
        return "REUNION";
    }
    public boolean estEnConflitAvec (Event event2, String type) {
        LocalDateTime fin1 = this.dateDebut.plusMinutes(this.dureeMinutes.getDuree()) ;
        LocalDateTime fin2 = event2.dateDebut.plusMinutes(event2.dureeMinutes.getDuree());
        return (this.dateDebut.isBefore(fin2) && fin1.isAfter(event2.dateDebut)) && !("PERIODIQUE".equals(type));
    }
    @Override
    public boolean estPresentDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        return this.dateDebut.isAfter(debut) && this.dateDebut.isBefore(fin);
    }




}