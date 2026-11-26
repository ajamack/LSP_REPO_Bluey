package org.howard.edu.lsp.project;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.howard.edu.lsp.project.DuplicateEventException;
import org.howard.edu.lsp.project.Event;
import org.howard.edu.lsp.project.EventManager;
import org.howard.edu.lsp.project.EventNotFoundException;
import org.howard.edu.lsp.project.EventStatus;
import org.howard.edu.lsp.project.InvalidOperationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for creating, retrieving, updating, closing, and canceling events.
 */
public class EventManagerEventTest {

    /** The manager under test. A fresh one is created before each test. */
    private EventManager manager;

    /** A date safely in the future, so events can be updated. */
    private LocalDateTime futureDate;

    /**
     * Creates a new, empty manager before each test so tests do not affect each other.
     */
    @BeforeEach
    public void setUp() {
        manager = new EventManager();
        futureDate = LocalDateTime.now().plusDays(30);
    }

    /**
     * A new event should store all its details and start as OPEN.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("createEvent stores details and starts OPEN")
    public void testCreateEvent() throws Exception {
        Event event = manager.createEvent("E1", "Homecoming", "Annual game",
                "Greene Stadium", futureDate, 100);

        assertEquals("E1", event.getEventId());
        assertEquals("Homecoming", event.getName());
        assertEquals("Annual game", event.getDescription());
        assertEquals("Greene Stadium", event.getLocation());
        assertEquals(futureDate, event.getDateTime());
        assertEquals(100, event.getCapacity());
        assertEquals(EventStatus.OPEN, event.getStatus());
    }

    /**
     * Creating two events with the same ID should fail.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("createEvent rejects a duplicate event ID")
    public void testCreateEventDuplicateId() throws Exception {
        manager.createEvent("E1", "Homecoming", "Annual game", "Stadium", futureDate, 100);

        assertThrows(DuplicateEventException.class, () ->
                manager.createEvent("E1", "Other", "Other", "Other", futureDate, 50));
    }

    /**
     * Capacity of 1 is the smallest allowed value (boundary case).
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("createEvent accepts capacity of 1")
    public void testCreateEventCapacityOne() throws Exception {
        Event event = manager.createEvent("E1", "Small", "Tiny", "Room 1", futureDate, 1);
        assertEquals(1, event.getCapacity());
    }

    /**
     * Capacity of 0 is invalid (boundary case).
     */
    @Test
    @DisplayName("createEvent rejects capacity of 0")
    public void testCreateEventCapacityZero() {
        assertThrows(IllegalArgumentException.class, () ->
                manager.createEvent("E1", "Bad", "Bad", "Room", futureDate, 0));
    }

    /**
     * A blank name is invalid.
     */
    @Test
    @DisplayName("createEvent rejects a blank name")
    public void testCreateEventBlankName() {
        assertThrows(IllegalArgumentException.class, () ->
                manager.createEvent("E1", "  ", "Desc", "Room", futureDate, 10));
    }

    /**
     * getEvent should return the same event that was created.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("getEvent returns the stored event")
    public void testGetEvent() throws Exception {
        Event created = manager.createEvent("E1", "Homecoming", "Game", "Stadium", futureDate, 100);
        assertSame(created, manager.getEvent("E1"));
    }

    /**
     * getEvent should fail for an ID that does not exist.
     */
    @Test
    @DisplayName("getEvent throws for an unknown ID")
    public void testGetEventNotFound() {
        assertThrows(EventNotFoundException.class, () -> manager.getEvent("NOPE"));
    }

    /**
     * Updating an event before its date should change its details.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("updateEvent changes details before the event date")
    public void testUpdateEvent() throws Exception {
        manager.createEvent("E1", "Homecoming", "Game", "Stadium", futureDate, 100);
        LocalDateTime newDate = futureDate.plusDays(1);

        manager.updateEvent("E1", "Homecoming 2026", "Big game", "Main Yard", newDate, 200);

        Event event = manager.getEvent("E1");
        assertEquals("Homecoming 2026", event.getName());
        assertEquals("Big game", event.getDescription());
        assertEquals("Main Yard", event.getLocation());
        assertEquals(newDate, event.getDateTime());
        assertEquals(200, event.getCapacity());
    }

    /**
     * Updating an event whose date has passed should fail.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("updateEvent rejects changes after the event date")
    public void testUpdateEventAfterDate() throws Exception {
        LocalDateTime pastDate = LocalDateTime.now().minusDays(1);
        manager.createEvent("E1", "Old", "Done", "Room", pastDate, 10);

        assertThrows(InvalidOperationException.class, () ->
                manager.updateEvent("E1", "New", "New", "Room", futureDate, 10));
    }

    /**
     * Updating a canceled event should fail.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("updateEvent rejects changes to a canceled event")
    public void testUpdateCanceledEvent() throws Exception {
        manager.createEvent("E1", "Homecoming", "Game", "Stadium", futureDate, 100);
        manager.cancelEvent("E1");

        assertThrows(InvalidOperationException.class, () ->
                manager.updateEvent("E1", "New", "New", "Room", futureDate, 10));
    }

    /**
     * Updating an event that does not exist should fail.
     */
    @Test
    @DisplayName("updateEvent throws for an unknown ID")
    public void testUpdateEventNotFound() {
        assertThrows(EventNotFoundException.class, () ->
                manager.updateEvent("NOPE", "New", "New", "Room", futureDate, 10));
    }

    /**
     * Closing an OPEN event should set its status to CLOSED.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("closeEvent sets status to CLOSED")
    public void testCloseEvent() throws Exception {
        manager.createEvent("E1", "Homecoming", "Game", "Stadium", futureDate, 100);
        manager.closeEvent("E1");
        assertEquals(EventStatus.CLOSED, manager.getEvent("E1").getStatus());
    }

    /**
     * Closing an event that is already closed should fail.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("closeEvent rejects an already closed event")
    public void testCloseEventTwice() throws Exception {
        manager.createEvent("E1", "Homecoming", "Game", "Stadium", futureDate, 100);
        manager.closeEvent("E1");
        assertThrows(InvalidOperationException.class, () -> manager.closeEvent("E1"));
    }

    /**
     * Canceling an event should set its status to CANCELED.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("cancelEvent sets status to CANCELED")
    public void testCancelEvent() throws Exception {
        manager.createEvent("E1", "Homecoming", "Game", "Stadium", futureDate, 100);
        manager.cancelEvent("E1");
        assertEquals(EventStatus.CANCELED, manager.getEvent("E1").getStatus());
    }

    /**
     * A CLOSED event can still be canceled.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("cancelEvent works on a closed event")
    public void testCancelClosedEvent() throws Exception {
        manager.createEvent("E1", "Homecoming", "Game", "Stadium", futureDate, 100);
        manager.closeEvent("E1");
        manager.cancelEvent("E1");
        assertEquals(EventStatus.CANCELED, manager.getEvent("E1").getStatus());
    }

    /**
     * Canceling an event twice should fail.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("cancelEvent rejects an already canceled event")
    public void testCancelEventTwice() throws Exception {
        manager.createEvent("E1", "Homecoming", "Game", "Stadium", futureDate, 100);
        manager.cancelEvent("E1");
        assertThrows(InvalidOperationException.class, () -> manager.cancelEvent("E1"));
    }
}