package org.examples.event;

import org.examples.objets.Duree;
import org.examples.objets.Personne;
import org.examples.objets.Title;

import java.time.LocalDateTime;

public class EventRDVPerso extends Event {


    public EventRDVPerso(Title title, Personne proprietaire, LocalDateTime dateDebut, Duree dureeMinutes) {
        super(title,proprietaire,dateDebut,dureeMinutes);

    }
    public boolean estEnConflitAvec (Event event2,String type) {
        LocalDateTime fin1 = this.dateDebut.plusMinutes(this.dureeMinutes.getDuree()) ;
        LocalDateTime fin2 = event2.dateDebut.plusMinutes(event2.dureeMinutes.getDuree());
        return (this.dateDebut.isBefore(fin2) && fin1.isAfter(event2.dateDebut)&& !("PERIODIQUE".equals(type)));
    }

    public String description() {
        String desc = "RDV : "+ title.getTitle() + " à " + this.dateDebut;

        return desc;
    }
    public String getType(){
        return "RDV_PERSONNEL";
    }
    @Override
    public boolean estPresentDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        return this.dateDebut.isAfter(debut) && this.dateDebut.isBefore(fin);
    }




}