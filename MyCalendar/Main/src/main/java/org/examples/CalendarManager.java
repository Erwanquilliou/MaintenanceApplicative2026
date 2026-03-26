package org.examples;

import org.examples.event.Event;
import org.examples.event.EventList;
import org.examples.factory.*;
import org.examples.objets.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarManager {
    public EventList events;
    public Map<String,EventFactory> eventFactorys;

    public CalendarManager() {
        this.events = new EventList();
        eventFactorys = new HashMap<>();
        eventFactorys.put("PERIODIQUE",new EventPeriodiqueFactory());
        eventFactorys.put("REUNION",new EventReunionFactory());
        eventFactorys.put("RDV_PERSONNEL",new EventRDVPersoFactory());
        eventFactorys.put("DEFAUT",new EventInconnuFactory());
    }

    public void ajouterEvent(String type, String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes,
    String lieu, String participants, int frequenceJours) {
        Title titre = new Title(title);
        Personne proprio = new Personne(proprietaire,"",18);
        Duree duree = new Duree(dureeMinutes);
        Lieu lieu1 = new Lieu(lieu);
        String[] participantsTab = participants.split(",");
        Personne[] ps = new Personne[participantsTab.length];
        int i = 0;
        for(String s : participantsTab){
            ps[i] = new Personne(s,"",18);
        }
        FrequenceJour fr = new FrequenceJour(frequenceJours);
        EventFactory factory = eventFactorys.getOrDefault(type,eventFactorys.get("DEFAUT"));
        Event e = factory.createEvent(titre,proprio,dateDebut,duree,lieu1,ps,fr);
        events.addEvent(e);
    }

    public List<Event> eventsDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        List<Event> result = new ArrayList<>();
        events.getEvents().stream().filter(e->e.estPresentDansPeriode(debut,fin)).forEach(result::add);
        return result;
    }

    public boolean conflit(Event e1, Event e2) {
        return e1.conflit(e2);
    }

    public void afficherEvenements() {
        for (Event e : events.getEvents()) {
            System.out.println(e.description());
        }
    }
}