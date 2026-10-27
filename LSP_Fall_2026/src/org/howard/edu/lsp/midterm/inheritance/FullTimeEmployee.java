
package org.howard.edu.lsp.midterm.inheritance;
 
/**
 * A full-time employee paid straight time for the first 40 hours of the week
 * and 1.5 times the hourly rate for every hour beyond 40.
 *
 * @author Aja Wilkes
 */
public class FullTimeEmployee extends Employee {
    /** Hours per week paid at the regular rate. */
    private static final double REGULAR_HOURS = 40.0;
    /** Multiplier applied to overtime hours. */
    private static final double OVERTIME_MULTIPLIER = 1.5;
 
    /**
     * Creates a full-time employee.
     *
     * @param hoursWorked hours worked this week (nonnegative)
     * @param hourlyRate  hourly pay rate (nonnegative)
     */
    public FullTimeEmployee(double hoursWorked, double hourlyRate) {
        super(hoursWorked, hourlyRate);
    }
 
    /**
     * Calculates pay with overtime at 1.5 times the rate beyond 40 hours.
     *
     * @return the weekly pay
     */
    @Override
    public double calculatePay() {
        double hours = getHoursWorked();
        double rate = getHourlyRate();
        double regular = Math.min(hours, REGULAR_HOURS);
        double overtime = Math.max(0.0, hours - REGULAR_HOURS);
        return regular * rate + overtime * rate * OVERTIME_MULTIPLIER;
    }
}