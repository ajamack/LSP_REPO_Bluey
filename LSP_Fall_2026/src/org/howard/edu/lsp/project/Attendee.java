package org.howard.edu.lsp.project;

/**
 * Represents an individual eligible to register for university events.
 */
public class Attendee {

    /** Unique identifier for this attendee. */
    private final String attendeeId;

    /** The attendee's first name. */
    private final String firstName;

    /** The attendee's last name. */
    private final String lastName;

    /** The attendee's email address. */
    private final String email;

    /**
     * Creates a new attendee.
     *
     * @param attendeeId unique identifier for the attendee
     * @param firstName the attendee's first name
     * @param lastName the attendee's last name
     * @param email the attendee's email address
     * @throws IllegalArgumentException if any value is null or blank,
     *         or if the email does not contain an @ symbol
     */
    public Attendee(String attendeeId, String firstName, String lastName, String email) {
        this.attendeeId = requireText(attendeeId, "Attendee ID");
        this.firstName = requireText(firstName, "First name");
        this.lastName = requireText(lastName, "Last name");
        this.email = requireText(email, "Email");

        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email must contain an @ symbol.");
        }
    }

    /**
     * Returns the attendee's unique identifier.
     *
     * @return the attendee ID
     */
    public String getAttendeeId() {
        return attendeeId;
    }
    /** Returns the attendee's first name
     * 
     * @return the attendee first name
     */
    public String getFirstName() {
    	return firstName;
    }
    /**
     * Returns attendee last name
     * @return the attendee last name
     */
    public String getLastName() {
    	return lastName;
    }
    /**
     * Returns attendee's email
     * @return the attendee's email
     */
    public String getEmail() {
    	return email;
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
}