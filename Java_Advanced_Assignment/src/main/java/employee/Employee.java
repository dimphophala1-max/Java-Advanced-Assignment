package employee;

/**
 * @author : Dimpho Phala
 * Date: 01/11/2026
 */
public class Employee implements IEmployee {

    // Instance variables
    private String employeeID;
    private String employeeName;
    private String jobTitle;
    private Positions position;
    private String department;
    private double basicSalary;

    // Constants
    private static final double TAX = 0.15;
    private static final double HOUSE_ALLOWANCE = 0.08;

    // No-argument constructor
    public Employee() {
    }

    // Parameterised constructor
    public Employee(String employeeID, String employeeName,
                    String jobTitle, Positions position,
                    String department, double basicSalary) {

        setEmployeeID(employeeID);
        setEmployeeName(employeeName);
        setJobTitle(jobTitle);
        setPosition(position);
        setDepartment(department);
        setBasicSalary(basicSalary);
    }

    // Getters and setters
    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {

        if (employeeID == null || !employeeID.startsWith("EM")) {
            throw new IllegalArgumentException(
                    "Employee ID must start with EM"
            );
        }

        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Positions getPosition() {
        return position;
    }

    public void setPosition(Positions position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {

        if (basicSalary < 4000) {
            throw new IllegalArgumentException(
                    "Basic salary cannot be less than R4000"
            );
        }

        this.basicSalary = basicSalary;
    }

    // Increase employee's basic salary
    @Override
    public double raiseSalary(double percentage)
            throws HighSalaryIncrease {

        double increase = basicSalary * (percentage / 100);

        if (increase > 5000) {
            throw new HighSalaryIncrease(
                    "Salary increase of " + increase +
                            " for " + employeeName +
                            " is more than the threshold R5000"
            );
        }

        basicSalary += increase;

        return basicSalary;
    }

    // Promote employee according to years of experience
    public Positions promote(int years) {

        if (years >= 3 && years <= 5) {

            if (position == Positions.Junior) {
                position = Positions.Intermediate;
            }
            else if (position == Positions.Intermediate) {
                position = Positions.Senior;
            }
        }

        else if (years >= 6 && years <= 10) {

            if (position == Positions.Intermediate) {
                position = Positions.Senior;
            }
            else if (position == Positions.Senior) {
                position = Positions.Director;
            }
        }

        else if (years > 10) {

            if (position == Positions.Senior) {
                position = Positions.Director;
            }
            else if (position == Positions.Director) {
                position = Positions.Executive;
            }
        }

        return position;
    }

    // Calculate house allowance
    @Override
    public double calcHouseAllowance() {
        return basicSalary * HOUSE_ALLOWANCE;
    }

    // Calculate gross salary
    @Override
    public double calcGrossSalary() {
        return basicSalary + calcHouseAllowance();
    }

    // Calculate tax deduction
    @Override
    public double calcTax() {
        return calcGrossSalary() * TAX;
    }

    // Calculate net salary
    @Override
    public double calcNetSalary() {
        return calcGrossSalary() - calcTax();
    }

    // Display employee information
    @Override
    public String toString() {

        return "Employee: [" + employeeID + "] " + employeeName +
                "\nTitle: " + jobTitle +
                "\nPosition: " + position +
                "\nDepartment: " + department +
                "\n======= Salary Breakdown =======" +
                "\nBasic: R" + basicSalary +
                "\nHouse Allowance: R" + calcHouseAllowance() +
                "\nGross: R" + calcGrossSalary() +
                "\nTax Deduction: R" + calcTax() +
                "\nNet: R" + calcNetSalary();
    }
}
