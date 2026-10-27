package org.howard.edu.lsp.midterm.inheritance;
 
/**
 * Abstract base class for all employee types. Stores the hours worked and
 * hourly rate shared by every employee and declares the polymorphic
 * {@link #calculatePay()} operation that each subclass overrides with its
 * own pay rule. This replaces the type-checking conditional in the original
 * EmployeePayCalculator.
 *
 * @author Aja Wilkes
 */
public abstract class Employee {
    private final double hoursWorked;
    private final double hourlyRate;
 
    /**
     * Creates an employee with the given hours and rate.
     *
     * @param hoursWorked hours worked this week (nonnegative)
     * @param hourlyRate  hourly pay rate (nonnegative)
     */
    public Employee(double hoursWorked, double hourlyRate) {
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }
 
    /**
     * Returns the hours worked this week.
     *
     * @return hours worked
     */
    public double getHoursWorked() {
        return hoursWorked;
    }
 
    /**
     * Returns the hourly pay rate.
     *
     * @return hourly rate
     */
    public double getHourlyRate() {
        return hourlyRate;
    }
 
    /**
     * Calculates this employee's pay for the week.
     *
     * @return the weekly pay
     */
    public abstract double calculatePay();
}
 