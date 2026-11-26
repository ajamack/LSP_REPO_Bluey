package org.howard.edu.lsp.project;

import static org.junit.jupiter.api.Assertions.*;

import org.howard.edu.lsp.project.Attendee;
import org.howard.edu.lsp.project.AttendeeNotFoundException;
import org.howard.edu.lsp.project.DuplicateAttendeeException;
import org.howard.edu.lsp.project.EventManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for creating and retrieving attendees.
 */
public class EventManagerAttendeeTest {

    /** The manager under test. A fresh one is created before each test. */
    private EventManager manager;

    /**
     * Creates a new, empty manager before each test.
     */
    @BeforeEach
    public void setUp() {
        manager = new EventManager();
    }

    /**
     * A new attendee should store all its details.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("createAttendee stores all details")
    public void testCreateAttendee() throws Exception {
        Attendee attendee = manager.createAttendee("A1", "Aja", "Wilkes", "aja@howard.edu");

        assertEquals("A1", attendee.getAttendeeId());
        assertEquals("Aja", attendee.getFirstName());
        assertEquals("Wilkes", attendee.getLastName());
        assertEquals("aja@howard.edu", attendee.getEmail());
    }

    /**
     * Creating two attendees with the same ID should fail.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("createAttendee rejects a duplicate attendee ID")
    public void testCreateAttendeeDuplicateId() throws Exception {
        manager.createAttendee("A1", "Aja", "Wilkes", "aja@howard.edu");

        assertThrows(DuplicateAttendeeException.class, () ->
                manager.createAttendee("A1", "Other", "Person", "other@howard.edu"));
    }

    /**
     * A failed duplicate should not replace the original attendee.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("Duplicate attempt leaves the original attendee unchanged")
    public void testDuplicateDoesNotOverwrite() throws Exception {
        manager.createAttendee("A1", "Aja", "Wilkes", "aja@howard.edu");

        assertThrows(DuplicateAttendeeException.class, () ->
                manager.createAttendee("A1", "Other", "Person", "other@howard.edu"));

        assertEquals("Aja", manager.getAttendee("A1").getFirstName());
    }

    /**
     * A blank first name is invalid.
     */
    @Test
    @DisplayName("createAttendee rejects a blank first name")
    public void testCreateAttendeeBlankFirstName() {
        assertThrows(IllegalArgumentException.class, () ->
                manager.createAttendee("A1", "", "Wilkes", "aja@howard.edu"));
    }

    /**
     * A null ID is invalid.
     */
    @Test
    @DisplayName("createAttendee rejects a null ID")
    public void testCreateAttendeeNullId() {
        assertThrows(IllegalArgumentException.class, () ->
                manager.createAttendee(null, "Aja", "Wilkes", "aja@howard.edu"));
    }

    /**
     * An email without an @ symbol is invalid.
     */
    @Test
    @DisplayName("createAttendee rejects an email without @")
    public void testCreateAttendeeBadEmail() {
        assertThrows(IllegalArgumentException.class, () ->
                manager.createAttendee("A1", "Aja", "Wilkes", "aja.howard.edu"));
    }

    /**
     * getAttendee should return the same attendee that was created.
     *
     * @throws Exception if setup fails unexpectedly
     */
    @Test
    @DisplayName("getAttendee returns the stored attendee")
    public void testGetAttendee() throws Exception {
        Attendee created = manager.createAttendee("A1", "Aja", "Wilkes", "aja@howard.edu");
        assertSame(created, manager.getAttendee("A1"));
    }

    /**
     * getAttendee should fail for an ID that does not exist.
     */
    @Test
    @DisplayName("getAttendee throws for an unknown ID")
    public void testGetAttendeeNotFound() {
        assertThrows(AttendeeNotFoundException.class, () -> manager.getAttendee("NOPE"));
    }
}