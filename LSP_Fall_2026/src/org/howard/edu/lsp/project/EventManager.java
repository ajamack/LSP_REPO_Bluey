package org.howard.edu.lsp.project;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages events, attendees, and registrations, and enforces all business rules.
 */
public class EventManager {

    /** All events, looked up by event ID. */
    private final Map<String, Event> events = new LinkedHashMap<>();

    /** All attendees, looked up by attendee ID. */
    private final Map<String, Attendee> attendees = new LinkedHashMap<>();

    /** All registrations, looked up by registration ID. */
    private final Map<String, Registration> registrations = new LinkedHashMap<>();

    /** Counter used to build unique registration IDs. */
    private int nextRegistrationNumber = 1;

    // ---------------------------------------------------------------
    // Events
    // ---------------------------------------------------------------

    /**
     * Creates a new event and stores it. New events start with a status of OPEN.
     *
     * @param eventId unique identifier for the event
     * @param name the name of the event
     * @param description a description of the event
     * @param location where the event takes place
     * @param dateTime when the event takes place
     * @param capacity the maximum number of attendees
     * @return the newly created event
     * @throws DuplicateEventException if an event with this ID already exists
     * @throws IllegalArgumentException if any value is invalid
     */
    public Event createEvent(String eventId, String name, String description,
                             String location, LocalDateTime dateTime, int capacity)
            throws DuplicateEventException {
        if (events.containsKey(eventId)) {
            throw new DuplicateEventException("Event ID already exists: " + eventId);
        }
        Event event = new Event(eventId, name, description, location, dateTime, capacity);
        events.put(eventId, event);
        return event;
    }

    /**
     * Returns the event with the given ID.
     *
     * @param eventId the ID of the event to find
     * @return the matching event
     * @throws EventNotFoundException if no event has this ID
     */
    public Event getEvent(String eventId) throws EventNotFoundException {
        Event event = events.get(eventId);
        if (event == null) {
            throw new EventNotFoundException("No event found with ID: " + eventId);
        }
        return event;
    }

    /**
     * Updates an event's details. Only allowed before the event date.
     *
     * @param eventId the ID of the event to update
     * @param name the new name
     * @param description the new description
     * @param location the new location
     * @param dateTime the new date and time
     * @param capacity the new maximum number of attendees
     * @throws EventNotFoundException if no event has this ID
     * @throws InvalidOperationException if the event date has passed, the event
     *         is canceled, or the new capacity is below the current number of
     *         active registrations
     * @throws IllegalArgumentException if any new value is invalid
     */
    public void updateEvent(String eventId, String name, String description,
                            String location, LocalDateTime dateTime, int capacity)
            throws EventNotFoundException, InvalidOperationException {
        Event event = getEvent(eventId);

        if (!LocalDateTime.now().isBefore(event.getDateTime())) {
            throw new InvalidOperationException("Cannot modify an event on or after its date.");
        }
        if (capacity < countActiveRegistrations(event)) {
            throw new InvalidOperationException(
                    "New capacity is less than the number of registered attendees.");
        }
        event.update(name, description, location, dateTime, capacity);
    }

    /**
     * Closes an event so it no longer accepts registrations.
     *
     * @param eventId the ID of the event to close
     * @throws EventNotFoundException if no event has this ID
     * @throws InvalidOperationException if the event is not OPEN
     */
    public void closeEvent(String eventId)
            throws EventNotFoundException, InvalidOperationException {
        getEvent(eventId).close();
    }

    /**
     * Cancels an event.
     *
     * @param eventId the ID of the event to cancel
     * @throws EventNotFoundException if no event has this ID
     * @throws InvalidOperationException if the event is already canceled
     */
    public void cancelEvent(String eventId)
            throws EventNotFoundException, InvalidOperationException {
        getEvent(eventId).cancel();
    }

    // ---------------------------------------------------------------
    // Attendees
    // ---------------------------------------------------------------

    /**
     * Creates a new attendee and stores it.
     *
     * @param attendeeId unique identifier for the attendee
     * @param firstName the attendee's first name
     * @param lastName the attendee's last name
     * @param email the attendee's email address
     * @return the newly created attendee
     * @throws DuplicateAttendeeException if an attendee with this ID already exists
     * @throws IllegalArgumentException if any value is invalid
     */
    public Attendee createAttendee(String attendeeId, String firstName,
                                   String lastName, String email)
            throws DuplicateAttendeeException {
        if (attendees.containsKey(attendeeId)) {
            throw new DuplicateAttendeeException("Attendee ID already exists: " + attendeeId);
        }
        Attendee attendee = new Attendee(attendeeId, firstName, lastName, email);
        attendees.put(attendeeId, attendee);
        return attendee;
    }

    /**
     * Returns the attendee with the given ID.
     *
     * @param attendeeId the ID of the attendee to find
     * @return the matching attendee
     * @throws AttendeeNotFoundException if no attendee has this ID
     */
    public Attendee getAttendee(String attendeeId) throws AttendeeNotFoundException {
        Attendee attendee = attendees.get(attendeeId);
        if (attendee == null) {
            throw new AttendeeNotFoundException("No attendee found with ID: " + attendeeId);
        }
        return attendee;
    }

    // ---------------------------------------------------------------
    // Registrations
    // ---------------------------------------------------------------

    /**
     * Registers an attendee for an event.
     *
     * @param attendeeId the ID of the attendee registering
     * @param eventId the ID of the event
     * @return the new registration
     * @throws AttendeeNotFoundException if the attendee does not exist
     * @throws EventNotFoundException if the event does not exist
     * @throws EventNotOpenException if the event is CLOSED or CANCELED
     * @throws DuplicateRegistrationException if the attendee is already registered
     * @throws EventFullException if the event has reached capacity
     */
    public Registration register(String attendeeId, String eventId)
            throws AttendeeNotFoundException, EventNotFoundException,
                   EventNotOpenException, DuplicateRegistrationException,
                   EventFullException {
        Attendee attendee = getAttendee(attendeeId);
        Event event = getEvent(eventId);

        if (event.getStatus() != EventStatus.OPEN) {
            throw new EventNotOpenException("Event is not open: " + eventId);
        }
        if (isRegistered(attendee, event)) {
            throw new DuplicateRegistrationException(
                    "Attendee " + attendeeId + " is already registered for " + eventId);
        }
        if (countActiveRegistrations(event) >= event.getCapacity()) {
            throw new EventFullException("Event is full: " + eventId);
        }

        String registrationId = "R" + nextRegistrationNumber;
        nextRegistrationNumber++;

        Registration registration =
                new Registration(registrationId, attendee, event, LocalDateTime.now());
        registrations.put(registrationId, registration);
        return registration;
    }

    /**
     * Returns the registration with the given ID.
     *
     * @param registrationId the ID of the registration to find
     * @return the matching registration
     * @throws RegistrationNotFoundException if no registration has this ID
     */
    public Registration getRegistration(String registrationId)
            throws RegistrationNotFoundException {
        Registration registration = registrations.get(registrationId);
        if (registration == null) {
            throw new RegistrationNotFoundException(
                    "No registration found with ID: " + registrationId);
        }
        return registration;
    }

    /**
     * Cancels a registration, which frees up a spot in the event.
     *
     * @param registrationId the ID of the registration to cancel
     * @throws RegistrationNotFoundException if no registration has this ID
     * @throws InvalidOperationException if the registration is already canceled
     */
    public void cancelRegistration(String registrationId)
            throws RegistrationNotFoundException, InvalidOperationException {
        getRegistration(registrationId).cancel();
    }

    // ---------------------------------------------------------------
    // Queries
    // ---------------------------------------------------------------

    /**
     * Returns the attendees with an active registration for the given event.
     *
     * @param eventId the ID of the event
     * @return a list of registered attendees (empty if none)
     * @throws EventNotFoundException if no event has this ID
     */
    public List<Attendee> getAttendeesForEvent(String eventId)
            throws EventNotFoundException {
        Event event = getEvent(eventId);
        List<Attendee> result = new ArrayList<>();
        for (Registration registration : registrations.values()) {
            if (registration.isActive() && registration.getEvent() == event) {
                result.add(registration.getAttendee());
            }
        }
        return result;
    }

    /**
     * Returns the events the given attendee has an active registration for.
     *
     * @param attendeeId the ID of the attendee
     * @return a list of events (empty if none)
     * @throws AttendeeNotFoundException if no attendee has this ID
     */
    public List<Event> getEventsForAttendee(String attendeeId)
            throws AttendeeNotFoundException {
        Attendee attendee = getAttendee(attendeeId);
        List<Event> result = new ArrayList<>();
        for (Registration registration : registrations.values()) {
            if (registration.isActive() && registration.getAttendee() == attendee) {
                result.add(registration.getEvent());
            }
        }
        return result;
    }

    // ---------------------------------------------------------------
    // Private helpers
    // ---------------------------------------------------------------

    /**
     * Counts the active registrations for an event.
     *
     * @param event the event to count registrations for
     * @return the number of active registrations
     */
    private int countActiveRegistrations(Event event) {
        int count = 0;
        for (Registration registration : registrations.values()) {
            if (registration.isActive() && registration.getEvent() == event) {
                count++;
            }
        }
        return count;
    }

    /**
     * Checks whether an attendee already has an active registration for an event.
     *
     * @param attendee the attendee to check
     * @param event the event to check
     * @return true if an active registration exists, false otherwise
     */
    private boolean isRegistered(Attendee attendee, Event event) {
        for (Registration registration : registrations.values()) {
            if (registration.isActive()
                    && registration.getAttendee() == attendee
                    && registration.getEvent() == event) {
                return true;
            }
        }
        return false;
    }
}