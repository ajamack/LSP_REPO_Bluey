package org.howard.edu.lsp.project;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.howard.edu.lsp.project.Attendee;
import org.howard.edu.lsp.project.AttendeeNotFoundException;
import org.howard.edu.lsp.project.Event;
import org.howard.edu.lsp.project.EventManager;
import org.howard.edu.lsp.project.EventNotFoundException;
import org.howard.edu.lsp.project.Registration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for listing attendees by event and events by attendee.
 */
public class EventManagerQueryTest {

    /** The manager under test. A fresh one is created before each test. */
    private EventManager manager;

    /**
     * Creates a manager with two events and two attendees.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @BeforeEach
    public void setUp() throws Exception {
        manager = new EventManager();
        LocalDateTime futureDate = LocalDateTime.now().plusDays(30);

        manager.createEvent("E1", "Homecoming", "Game", "Stadium", futureDate, 10);
        manager.createEvent("E2", "Career Fair", "Recruiting", "Blackburn", futureDate, 10);
        manager.createAttendee("A1", "Aja", "Wilkes", "aja@howard.edu");
        manager.createAttendee("A2", "Kris", "Smith", "kris@howard.edu");
    }

    /**
     * The list should contain every attendee registered for the event, in order.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("getAttendeesForEvent returns registered attendees")
    public void testGetAttendeesForEvent() throws Exception {
        manager.register("A1", "E1");
        manager.register("A2", "E1");

        List<Attendee> result = manager.getAttendeesForEvent("E1");

        assertEquals(2, result.size());
        assertSame(manager.getAttendee("A1"), result.get(0));
        assertSame(manager.getAttendee("A2"), result.get(1));
    }

    /**
     * An event with no registrations should return an empty list, not null.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("getAttendeesForEvent returns an empty list when no one is registered")
    public void testGetAttendeesForEventEmpty() throws Exception {
        List<Attendee> result = manager.getAttendeesForEvent("E1");
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    /**
     * Canceled registrations should not appear in the list.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("getAttendeesForEvent excludes canceled registrations")
    public void testGetAttendeesForEventExcludesCanceled() throws Exception {
        Registration registration = manager.register("A1", "E1");
        manager.register("A2", "E1");
        manager.cancelRegistration(registration.getRegistrationId());

        List<Attendee> result = manager.getAttendeesForEvent("E1");

        assertEquals(1, result.size());
        assertSame(manager.getAttendee("A2"), result.get(0));
    }

    /**
     * Attendees of a different event should not appear in the list.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("getAttendeesForEvent only includes that event's attendees")
    public void testGetAttendeesForEventOnlyThatEvent() throws Exception {
        manager.register("A1", "E1");
        manager.register("A2", "E2");

        List<Attendee> result = manager.getAttendeesForEvent("E1");

        assertEquals(1, result.size());
        assertSame(manager.getAttendee("A1"), result.get(0));
    }

    /**
     * Asking about an event that does not exist should fail.
     */
    @Test
    @DisplayName("getAttendeesForEvent throws for an unknown event")
    public void testGetAttendeesForUnknownEvent() {
        assertThrows(EventNotFoundException.class, () -> manager.getAttendeesForEvent("NOPE"));
    }

    /**
     * The list should contain every event the attendee is registered for, in order.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("getEventsForAttendee returns registered events")
    public void testGetEventsForAttendee() throws Exception {
        manager.register("A1", "E1");
        manager.register("A1", "E2");

        List<Event> result = manager.getEventsForAttendee("A1");

        assertEquals(2, result.size());
        assertSame(manager.getEvent("E1"), result.get(0));
        assertSame(manager.getEvent("E2"), result.get(1));
    }

    /**
     * An attendee with no registrations should get an empty list, not null.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("getEventsForAttendee returns an empty list when not registered")
    public void testGetEventsForAttendeeEmpty() throws Exception {
        List<Event> result = manager.getEventsForAttendee("A1");
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    /**
     * Canceled registrations should not appear in the list.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("getEventsForAttendee excludes canceled registrations")
    public void testGetEventsForAttendeeExcludesCanceled() throws Exception {
        Registration registration = manager.register("A1", "E1");
        manager.register("A1", "E2");
        manager.cancelRegistration(registration.getRegistrationId());

        List<Event> result = manager.getEventsForAttendee("A1");

        assertEquals(1, result.size());
        assertSame(manager.getEvent("E2"), result.get(0));
    }

    /**
     * Asking about an attendee that does not exist should fail.
     */
    @Test
    @DisplayName("getEventsForAttendee throws for an unknown attendee")
    public void testGetEventsForUnknownAttendee() {
        assertThrows(AttendeeNotFoundException.class, () -> manager.getEventsForAttendee("NOPE"));
    }
}