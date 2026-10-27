
package org.howard.edu.lsp.midterm.inheritance;
 
/**
 * A part-time employee paid straight hourly pay for all hours worked,
 * with no overtime.
 *
 * @author Aja Wilkes
 */
public class PartTimeEmployee extends Employee {
 
    /**
     * Creates a part-time employee.
     *
     * @param hoursWorked hours worked this week (nonnegative)
     * @param hourlyRate  hourly pay rate (nonnegative)
     */
    public PartTimeEmployee(double hoursWorked, double hourlyRate) {
        super(hoursWorked, hourlyRate);
    }
 
    /**
     * Calculates straight hourly pay.
     *
     * @return the weekly pay
     */
    @Override
    public double calculatePay() {
        return getHoursWorked() * getHourlyRate();
    }
}