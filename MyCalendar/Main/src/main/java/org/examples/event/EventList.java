package org.examples.event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class EventList {
    ArrayList<Event> eventList;
    public EventList() {
        eventList = new ArrayList<>();
    }
    public void addEvent(Event event) {
        eventList.add(event);
    }
    public void addAllEvent(ArrayList<Event> eventList) {
        this.eventList.addAll(eventList);
    }
    public void removeEvent(Event event) {
        eventList.remove(event);
    }

    public List<Event> eventsDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        List<Event> result = new ArrayList<>();
        Stream<Event> eventStream = eventList.stream();
        eventStream.filter(e->e.dateDebut.isAfter(debut)).filter(e->e.dateDebut.plusMinutes(e.dureeMinutes.getDuree()).isBefore(fin)).forEach(result::add);
        return result;
    }

    public List<Event> getEvents() {
        return eventList;
    }
    public Event get(int i){
        return eventList.get(i);
    }
    public int size() {
        return eventList.size();
    }


}
