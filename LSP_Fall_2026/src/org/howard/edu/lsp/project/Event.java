package org.howard.edu.lsp.project;

import java.time.LocalDateTime;

/**
 * Represents a university-sponsored event that attendees may register for.
 */
public class Event {

    /** Unique identifier for this event. Never changes. */
    private final String eventId;

    /** The name of the event. */
    private String name;

    /** A description of the event. */
    private String description;

    /** Where the event takes place. */
    private String location;

    /** When the event takes place. */
    private LocalDateTime dateTime;

    /** The maximum number of attendees allowed. */
    private int capacity;

    /** The current status of the event. */
    private EventStatus status;

    /**
     * Creates a new event with a status of OPEN.
     *
     * @param eventId unique identifier for the event
     * @param name the name of the event
     * @param description a description of the event
     * @param location where the event takes place
     * @param dateTime when the event takes place
     * @param capacity the maximum number of attendees
     * @throws IllegalArgumentException if any text value is null or blank,
     *         if dateTime is null, or if capacity is less than 1
     */
    public Event(String eventId, String name, String description,
                 String location, LocalDateTime dateTime, int capacity) {
        this.eventId = requireText(eventId, "Event ID");
        this.name = requireText(name, "Name");
        this.description = requireText(description, "Description");
        this.location = requireText(location, "Location");
        this.dateTime = requireDateTime(dateTime);
        this.capacity = requirePositive(capacity);
        this.status = EventStatus.OPEN;
    }

    /**
     * Returns the event's unique identifier.
     *
     * @return the event ID
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * Returns the name of the event.
     *
     * @return the event name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the event.
     *
     * @return the event description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the location of the event.
     *
     * @return the event location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Returns the date and time of the event.
     *
     * @return the event date and time
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns the maximum number of attendees allowed.
     *
     * @return the event capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns the current status of the event.
     *
     * @return the event status
     */
    public EventStatus getStatus() {
        return status;
    }

    /**
     * Checks that a text value is not null or blank.
     *
     * @param value the value to check
     * @param fieldName the name of the field, used in the error message
     * @return the value, if it is valid
     * @throws IllegalArgumentException if the value is null or blank
     */
    private static String requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be empty.");
        }
        return value;
    }

    /**
     * Checks that a date and time value is not null.
     *
     * @param dateTime the value to check
     * @return the value, if it is valid
     * @throws IllegalArgumentException if the value is null
     */
    private static LocalDateTime requireDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            throw new IllegalArgumentException("Date and time must not be empty.");
        }
        return dateTime;
    }

    /**
     * Checks that a capacity is at least 1.
     *
     * @param capacity the value to check
     * @return the value, if it is valid
     * @throws IllegalArgumentException if the capacity is less than 1
     */
    private static int requirePositive(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be at least 1.");
        }
        return capacity;
    }
}