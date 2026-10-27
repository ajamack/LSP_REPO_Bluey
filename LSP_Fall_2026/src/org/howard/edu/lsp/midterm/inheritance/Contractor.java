package org.howard.edu.lsp.midterm.inheritance;
 
/**
 * A contractor paid a 15% premium over the hourly rate on all hours worked.
 *
 * @author Aja Wilkes
 */
public class Contractor extends Employee {
    /** Premium multiplier applied to all contractor hours. */
    private static final double PREMIUM_MULTIPLIER = 1.15;
 
    /**
     * Creates a contractor.
     *
     * @param hoursWorked hours worked this week (nonnegative)
     * @param hourlyRate  hourly pay rate (nonnegative)
     */
    public Contractor(double hoursWorked, double hourlyRate) {
        super(hoursWorked, hourlyRate);
    }
 
    /**
     * Calculates pay with a 15% premium on all hours.
     *
     * @return the weekly pay
     */
    @Override
    public double calculatePay() {
        return getHoursWorked() * getHourlyRate() * PREMIUM_MULTIPLIER;
    }
}
 