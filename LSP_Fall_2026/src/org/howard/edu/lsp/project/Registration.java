package org.howard.edu.lsp.project;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents one attendee's registration for one event.
 */
public class Registration {

    /** Unique identifier for this registration. */
    private final String registrationId;

    /** The attendee who registered. */
    private final Attendee attendee;

    /** The event the attendee registered for. */
    private final Event event;

    /** When the registration was created. */
    private final LocalDateTime timestamp;

    /** The current status of the registration. */
    private RegistrationStatus status;

    /**
     * Creates a new registration with a status of ACTIVE.
     *
     * @param registrationId unique identifier for the registration
     * @param attendee the attendee who is registering
     * @param event the event being registered for
     * @param timestamp when the registration was created
     * @throws NullPointerException if any value is null
     */
    Registration(String registrationId, Attendee attendee,
                 Event event, LocalDateTime timestamp) {
        this.registrationId = Objects.requireNonNull(registrationId, "Registration ID must not be null.");
        this.attendee = Objects.requireNonNull(attendee, "Attendee must not be null.");
        this.event = Objects.requireNonNull(event, "Event must not be null.");
        this.timestamp = Objects.requireNonNull(timestamp, "Timestamp must not be null.");
        this.status = RegistrationStatus.ACTIVE;
    }

    /**
     * Returns the registration's unique identifier.
     *
     * @return the registration ID
     */
    public String getRegistrationId() {
        return registrationId;
    }

    /**
     * Returns the attendee for this registration.
     *
     * @return the attendee
     */
    public Attendee getAttendee() {
        return attendee;
    }

    /**
     * Returns the event for this registration.
     *
     * @return the event
     */
    public Event getEvent() {
        return event;
    }

    /**
     * Returns when the registration was created.
     *
     * @return the registration timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Returns the current status of the registration.
     *
     * @return the registration status
     */
    public RegistrationStatus getStatus() {
        return status;
    }

    /**
     * Returns whether this registration is currently active.
     *
     * @return true if the status is ACTIVE, false otherwise
     */
    public boolean isActive() {
        return status == RegistrationStatus.ACTIVE;
    }

    /**
     * Cancels this registration.
     *
     * @throws InvalidOperationException if the registration is already canceled
     */
    void cancel() throws InvalidOperationException {
        if (status == RegistrationStatus.CANCELED) {
            throw new InvalidOperationException("Registration is already canceled.");
        }
        status = RegistrationStatus.CANCELED;
    }
}