package org.howard.edu.lsp.project.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.howard.edu.lsp.project.AttendeeNotFoundException;
import org.howard.edu.lsp.project.DuplicateRegistrationException;
import org.howard.edu.lsp.project.EventFullException;
import org.howard.edu.lsp.project.EventManager;
import org.howard.edu.lsp.project.EventNotFoundException;
import org.howard.edu.lsp.project.EventNotOpenException;
import org.howard.edu.lsp.project.InvalidOperationException;
import org.howard.edu.lsp.project.Registration;
import org.howard.edu.lsp.project.RegistrationNotFoundException;
import org.howard.edu.lsp.project.RegistrationStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for registering attendees, canceling registrations, and the
 * related business rules.
 */
public class EventManagerRegistrationTest {

    /** The manager under test. A fresh one is created before each test. */
    private EventManager manager;

    /** A date safely in the future. */
    private LocalDateTime futureDate;

    /**
     * Creates a manager with one event (capacity 2) and three attendees.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @BeforeEach
    public void setUp() throws Exception {
        manager = new EventManager();
        futureDate = LocalDateTime.now().plusDays(30);

        manager.createEvent("E1", "Homecoming", "Game", "Stadium", futureDate, 2);
        manager.createAttendee("A1", "Aja", "Wilkes", "aja@howard.edu");
        manager.createAttendee("A2", "Kris", "Smith", "kris@howard.edu");
        manager.createAttendee("A3", "Sam", "Jones", "sam@howard.edu");
    }

    /**
     * A successful registration should link the attendee and event and be ACTIVE.
     *
     * @throws Exception if registration fails unexpectedly
     */
    @Test
    @DisplayName("register creates an ACTIVE registration")
    public void testRegister() throws Exception {
        Registration registration = manager.register("A1", "E1");

        assertNotNull(registration.getRegistrationId());
        assertNotNull(registration.getTimestamp());
        assertSame(manager.getAttendee("A1"), registration.getAttendee());
        assertSame(manager.getEvent("E1"), registration.getEvent());
        assertEquals(RegistrationStatus.ACTIVE, registration.getStatus());
        assertTrue(registration.isActive());
    }

    /**
     * Each registration should get its own unique ID.
     *
     * @throws Exception if registration fails unexpectedly
     */
    @Test
    @DisplayName("register gives each registration a unique ID")
    public void testRegistrationIdsAreUnique() throws Exception {
        Registration first = manager.register("A1", "E1");
        Registration second = manager.register("A2", "E1");
        assertNotEquals(first.getRegistrationId(), second.getRegistrationId());
    }

    /**
     * Registering an attendee that does not exist should fail.
     */
    @Test
    @DisplayName("register rejects an unknown attendee")
    public void testRegisterUnknownAttendee() {
        assertThrows(AttendeeNotFoundException.class, () -> manager.register("NOPE", "E1"));
    }

    /**
     * Registering for an event that does not exist should fail.
     */
    @Test
    @DisplayName("register rejects an unknown event")
    public void testRegisterUnknownEvent() {
        assertThrows(EventNotFoundException.class, () -> manager.register("A1", "NOPE"));
    }

    /**
     * A CLOSED event should not accept registrations.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("register rejects a CLOSED event")
    public void testRegisterClosedEvent() throws Exception {
        manager.closeEvent("E1");
        assertThrows(EventNotOpenException.class, () -> manager.register("A1", "E1"));
    }

    /**
     * A CANCELED event should not accept registrations.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("register rejects a CANCELED event")
    public void testRegisterCanceledEvent() throws Exception {
        manager.cancelEvent("E1");
        assertThrows(EventNotOpenException.class, () -> manager.register("A1", "E1"));
    }

    /**
     * An attendee cannot register twice for the same event.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("register rejects a duplicate registration")
    public void testRegisterTwice() throws Exception {
        manager.register("A1", "E1");
        assertThrows(DuplicateRegistrationException.class, () -> manager.register("A1", "E1"));
    }

    /**
     * Filling the event exactly to capacity works; one more fails (boundary case).
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("register enforces capacity exactly")
    public void testCapacityBoundary() throws Exception {
        manager.register("A1", "E1");
        manager.register("A2", "E1");

        assertThrows(EventFullException.class, () -> manager.register("A3", "E1"));
        assertEquals(2, manager.getAttendeesForEvent("E1").size());
    }

    /**
     * Canceling a registration should free up a spot.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("cancelRegistration makes capacity available again")
    public void testCancelFreesCapacity() throws Exception {
        Registration first = manager.register("A1", "E1");
        manager.register("A2", "E1");

        manager.cancelRegistration(first.getRegistrationId());
        Registration third = manager.register("A3", "E1");

        assertTrue(third.isActive());
        assertEquals(2, manager.getAttendeesForEvent("E1").size());
    }

    /**
     * Canceling a registration should set its status to CANCELED.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("cancelRegistration sets status to CANCELED")
    public void testCancelRegistration() throws Exception {
        Registration registration = manager.register("A1", "E1");
        manager.cancelRegistration(registration.getRegistrationId());

        assertEquals(RegistrationStatus.CANCELED, registration.getStatus());
        assertFalse(registration.isActive());
    }

    /**
     * Canceling the same registration twice should fail.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("cancelRegistration rejects an already canceled registration")
    public void testCancelRegistrationTwice() throws Exception {
        Registration registration = manager.register("A1", "E1");
        manager.cancelRegistration(registration.getRegistrationId());

        assertThrows(InvalidOperationException.class, () ->
                manager.cancelRegistration(registration.getRegistrationId()));
    }

    /**
     * Canceling a registration that does not exist should fail.
     */
    @Test
    @DisplayName("cancelRegistration throws for an unknown ID")
    public void testCancelUnknownRegistration() {
        assertThrows(RegistrationNotFoundException.class, () ->
                manager.cancelRegistration("NOPE"));
    }

    /**
     * An attendee who canceled can register again.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("Attendee can re-register after canceling")
    public void testReRegisterAfterCancel() throws Exception {
        Registration first = manager.register("A1", "E1");
        manager.cancelRegistration(first.getRegistrationId());

        Registration second = manager.register("A1", "E1");
        assertTrue(second.isActive());
    }

    /**
     * getRegistration should return the stored registration.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("getRegistration returns the stored registration")
    public void testGetRegistration() throws Exception {
        Registration registration = manager.register("A1", "E1");
        assertSame(registration, manager.getRegistration(registration.getRegistrationId()));
    }

    /**
     * Lowering capacity below the number of registered attendees should fail.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("updateEvent rejects capacity below current registrations")
    public void testUpdateCapacityBelowRegistrations() throws Exception {
        manager.register("A1", "E1");
        manager.register("A2", "E1");

        assertThrows(InvalidOperationException.class, () ->
                manager.updateEvent("E1", "Homecoming", "Game", "Stadium", futureDate, 1));
    }

    /**
     * Lowering capacity to exactly the number of registrations is allowed (boundary case).
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("updateEvent allows capacity equal to current registrations")
    public void testUpdateCapacityEqualToRegistrations() throws Exception {
        manager.register("A1", "E1");

        manager.updateEvent("E1", "Homecoming", "Game", "Stadium", futureDate, 1);
        assertEquals(1, manager.getEvent("E1").getCapacity());
    }
}