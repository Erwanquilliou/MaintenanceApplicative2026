package org.examples;



import org.examples.event.Event;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class CalendarTest{
    private CalendarManager manager;

    @BeforeEach
    void setUp() {
        manager = new CalendarManager();
    }

    @Test
    void testAjouterEvent() {
        manager.ajouterEvent("REUNION", "Réunion", "Alice",
                LocalDateTime.of(2026, 3, 26, 10, 0), 60, "Bureau", "Bob", 0);
        assertEquals(1, manager.events.size(), "L'événement devrait être ajouté à la liste.");
    }

    @Test
    void testEventsDansPeriode_Simple() {
        LocalDateTime date = LocalDateTime.of(2026, 3, 26, 14, 0);
        manager.ajouterEvent("REUNION", "RDV", "Alice", date, 30, "Lyon", "Perso", 0);

        List<Event> trouve = manager.eventsDansPeriode(date.minusHours(1), date.plusHours(1));
        assertEquals(1, trouve.size(), "L'événement devrait être trouvé dans la plage horaire.");
    }
    @Test
    void testEventsDansPeriode_Faux() {
        LocalDateTime date = LocalDateTime.of(2026, 3, 26, 14, 0);
        manager.ajouterEvent("REUNION", "RDV", "Alice", date, 30, "Lyon", "Perso", 0);

        List<Event> trouve = manager.eventsDansPeriode(date.plusHours(1), date.plusHours(2));
        assertEquals(0, trouve.size(), "L'événement ne doit pas être pris en compte");
    }
    @Test
    void testEventsDansPeriode_Faux2() {
        LocalDateTime date = LocalDateTime.of(2026, 3, 26, 14, 0);
        manager.ajouterEvent("REUNION", "RDV", "Alice", date, 30, "Lyon", "Perso", 0);

        List<Event> trouve = manager.eventsDansPeriode(date.minusHours(1), date);
        assertEquals(0, trouve.size(), "L'événement ne doit pas être pris en compte");
    }

    @Test
    void testEventsDansPeriode_PeriodiqueFaux() {
        LocalDateTime date = LocalDateTime.of(2026, 3, 26, 14, 0);
        manager.ajouterEvent("PERIODIQUE", "RDV", "Alice", date, 30, "Lyon", "Perso", 1);

        List<Event> trouve = manager.eventsDansPeriode(date.plusHours(2), date.plusHours(3));
        assertEquals(0, trouve.size(), "L'événement ne doit pas être pris en compte");
    }


    @Test
    void testEventsDansPeriode_Periodique() {

        LocalDateTime debutEvent = LocalDateTime.of(2026, 3, 20, 10, 0);
        manager.ajouterEvent("PERIODIQUE", "Sport", "Bob", debutEvent, 60, "Gym", "Solo", 2);

        LocalDateTime debutRecherche = LocalDateTime.of(2026, 3, 24, 0, 0);
        LocalDateTime finRecherche = LocalDateTime.of(2026, 3, 24, 23, 59);

        List<Event> trouve = manager.eventsDansPeriode(debutRecherche, finRecherche);
        assertEquals(1, trouve.size(), "L'occurrence du 24 mars devrait être détectée.");
    }

    @Test
    void testConflit_Simple() {
        LocalDateTime debut = LocalDateTime.of(2026, 3, 26, 10, 0);

        manager.ajouterEvent("REUNION", "Réunion A", "Alice", debut, 60, "A", "P1",0);
        manager.ajouterEvent("REUNION", "Réunion B", "Bob", debut.plusMinutes(30), 60, "B", "P2",0);

        Event e1 = manager.events.get(0);
        Event e2 = manager.events.get(1);

        assertTrue(manager.conflit(e1, e2), "Il devrait y avoir un conflit (chevauchement de 30 min).");
    }

    @Test
    void testConflit_SimpleFaux() {
        LocalDateTime debut = LocalDateTime.of(2026, 3, 26, 10, 0);

        manager.ajouterEvent("REUNION", "Réunion A", "Alice", debut, 60, "A", "P1",0);
        manager.ajouterEvent("REUNION", "Réunion B", "Bob", debut.minusMinutes(61), 60, "B", "P2",0);

        Event e1 = manager.events.get(0);
        Event e2 = manager.events.get(1);

        assertFalse(manager.conflit(e1, e2), "Il ne devrait pas y avoir de conflit.");
    }
    @Test
    void testConflit_SimpleFaux2() {
        LocalDateTime debut = LocalDateTime.of(2026, 3, 26, 10, 0);

        manager.ajouterEvent("REUNION", "Réunion A", "Alice", debut, 60, "A", "P1",0);
        manager.ajouterEvent("REUNION", "Réunion B", "Bob", debut.plusMinutes(61), 60, "B", "P2",0);

        Event e1 = manager.events.get(0);
        Event e2 = manager.events.get(1);

        assertFalse(manager.conflit(e1, e2), "Il ne devrait pas y avoir de conflit.");
    }

    @Test
    void testConflit_Periodique(){
        LocalDateTime debut = LocalDateTime.of(2026, 3, 26, 10, 0);

        manager.ajouterEvent("PERIODIQUE", "Réunion A", "Alice", debut, 60, "A", "P1",0);
        manager.ajouterEvent("REUNION", "Réunion B", "Bob", debut.plusMinutes(30), 60, "B", "P2",0);

        Event e1 = manager.events.get(0);
        Event e2 = manager.events.get(1);

        assertFalse(manager.conflit(e1, e2), "Il ne devrait pas y avoir de conflit car l'event 1 est periodique.");
    }

    @Test
    void testConflit_Periodique2(){
        LocalDateTime debut = LocalDateTime.of(2026, 3, 26, 10, 0);

        manager.ajouterEvent("REUNION", "Réunion A", "Alice", debut, 60, "A", "P1",0);
        manager.ajouterEvent("PERIODIQUE", "Réunion B", "Bob", debut.plusMinutes(30), 60, "B", "P2",0);

        Event e1 = manager.events.get(0);
        Event e2 = manager.events.get(1);

        assertFalse(manager.conflit(e1, e2), "Il ne devrait pas y avoir de conflit car l'event 1 est periodique.");
    }
    @Test
    void testDescriptionReunion() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));
        LocalDateTime debut = LocalDateTime.of(2026, 3, 26, 14, 0);
        manager.ajouterEvent("REUNION", "Réunion A", "Alice", debut, 60, "A", "P1",0);

        manager.afficherEvenements();
        System.setOut(originalOut);

        assertEquals("Réunion : Réunion A à A avec P1" + System.lineSeparator(),output.toString());

    }

    @Test
    void testDescription_Periodique() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));
        LocalDateTime debut = LocalDateTime.of(2026, 3, 26, 14, 0);
        manager.ajouterEvent("PERIODIQUE", "Réunion A", "Alice", debut, 60, "A", "P1",2);
        manager.afficherEvenements();
        System.setOut(originalOut);

        assertEquals("Événement périodique : Réunion A tous les 2 jours" + System.lineSeparator(),output.toString());
    }

    @Test
    void testDescription_RDVPerso() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));
        LocalDateTime debut = LocalDateTime.of(2026, 3, 26, 14, 0);
        manager.ajouterEvent("RDV_PERSONNEL", "Réunion A", "Alice", debut, 60, "A", "P1",2);
        manager.afficherEvenements();
        System.setOut(originalOut);

        assertEquals("RDV : Réunion A à " + debut + System.lineSeparator(),output.toString());
    }
    @Test
    void testDescription_type_inconnu() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));
        LocalDateTime debut = LocalDateTime.of(2026, 3, 26, 14, 0);
        manager.ajouterEvent("RDV", "Réunion A", "Alice", debut, 60, "A", "P1",2);
        manager.afficherEvenements();
        System.setOut(originalOut);

        assertEquals("" + System.lineSeparator(),output.toString());
    }
}