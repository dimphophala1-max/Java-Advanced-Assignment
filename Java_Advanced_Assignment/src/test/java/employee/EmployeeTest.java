package employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Dimpho Phala
 * Date : 01/11/2026
 **/
public class EmployeeTest {
    private Employee employee;

    @BeforeEach
    public void setUp() {

        employee = new Employee(
                "EM123",
                "Mike",
                "Developer",
                Positions.Junior,
                "IT",
                25000
        );
    }

    // Test salary increase
    @Test
    public void testRaiseSalary() throws HighSalaryIncrease {

        double result = employee.raiseSalary(15);

        assertEquals(28750.0, result, 0.01);

    }

    // Test salary increase exception
    @Test
    public void testRaiseSalaryThrowsException() {

        Employee highSalaryEmployee = new Employee(
                "EM126",
                "Lerato",
                "Accountant",
                Positions.Senior,
                "FINANCE",
                34000
        );

        assertThrows(
                HighSalaryIncrease.class,
                () -> highSalaryEmployee.raiseSalary(15)
        );


    }

    // Test house allowance
    @Test
    public void testCalcHouseAllowance() {

        double result = employee.calcHouseAllowance();

        assertEquals(2000.0, result, 0.01);
    }

    // Test gross salary
    @Test
    public void testCalcGrossSalary() {

        double result = employee.calcGrossSalary();

        assertEquals(27000.0, result, 0.01);
    }

    // Test tax deduction
    @Test
    public void testCalcTax() {

        double result = employee.calcTax();

        assertEquals(4050.0, result, 0.01);
    }

    // Test net salary
    @Test
    public void testCalcNetSalary() {

        double result = employee.calcNetSalary();

        assertEquals(22950.0, result, 0.01);
    }

    // Test getters
    @Test
    public void testGetters() {
        assertEquals("EM123", employee.getEmployeeID());
        assertEquals("Mike", employee.getEmployeeName());
        assertEquals("Developer", employee.getJobTitle());
        assertEquals(Positions.Junior, employee.getPosition());
        assertEquals("IT", employee.getDepartment());
        assertEquals(25000, employee.getBasicSalary(), 0.01);
    }

    // Test setters and their exception branches
    @Test
    public void testSetters() {
        employee.setEmployeeName("John");
        assertEquals("John", employee.getEmployeeName());

        // Test invalid ID exception
        assertThrows(IllegalArgumentException.class, () -> employee.setEmployeeID("AB123"));

        // Test invalid Salary exception
        assertThrows(IllegalArgumentException.class, () -> employee.setBasicSalary(3000));

        // Test null ID exception to cover the first half of the OR condition
        assertThrows(IllegalArgumentException.class, () -> employee.setEmployeeID(null));

        employee.setBasicSalary(30000); // Valid salary to cover the success path
        assertEquals(30000, employee.getBasicSalary(), 0.01);
    }

    // Test all promote() branches
    @Test
    public void testPromoteBranches() {
        // 1. 3-5 years: Junior -> Intermediate
        Employee emp1 = new Employee("EM1", "A", "T", Positions.Junior, "IT", 10000);
        assertEquals(Positions.Intermediate, emp1.promote(4));

        // 2. 3-5 years: Intermediate -> Senior
        Employee emp2 = new Employee("EM2", "B", "T", Positions.Intermediate, "IT", 10000);
        assertEquals(Positions.Senior, emp2.promote(4));

        // 3. 6-10 years: Intermediate -> Senior
        Employee emp3 = new Employee("EM3", "C", "T", Positions.Intermediate, "IT", 10000);
        assertEquals(Positions.Senior, emp3.promote(7));

        // 4. 6-10 years: Senior -> Director
        Employee emp4 = new Employee("EM4", "D", "T", Positions.Senior, "IT", 10000);
        assertEquals(Positions.Director, emp4.promote(7));

        // 5. >10 years: Senior -> Director
        Employee emp5 = new Employee("EM5", "E", "T", Positions.Senior, "IT", 10000);
        assertEquals(Positions.Director, emp5.promote(12));

        // 6. >10 years: Director -> Executive
        Employee emp6 = new Employee("EM6", "F", "T", Positions.Director, "IT", 10000);
        assertEquals(Positions.Executive, emp6.promote(12));

        // 7. No promotion (Year < 3)
        Employee emp7 = new Employee("EM7", "G", "T", Positions.Junior, "IT", 10000);
        assertEquals(Positions.Junior, emp7.promote(1));

        // 8. No promotion (Year 6-10 but already Director)
        Employee emp8 = new Employee("EM8", "H", "T", Positions.Director, "IT", 10000);
        assertEquals(Positions.Director, emp8.promote(8));

        // 9. No promotion (Year 6-10 but still Junior)
        Employee emp9 = new Employee("EM9", "I", "T", Positions.Junior, "IT", 10000);
        assertEquals(Positions.Junior, emp9.promote(8));

        // 10. Junior with >10 years (stays Junior)
        Employee emp10 = new Employee("EM10", "J", "T", Positions.Junior, "IT", 10000);
        assertEquals(Positions.Junior, emp10.promote(12));

        // 11. 3-5 years: Senior remains Senior
        Employee emp11 = new Employee(
                "EM11", "K", "T",
                Positions.Senior, "IT", 10000
        );

        assertEquals(Positions.Senior, emp11.promote(4));
    }

    // Test toString
    @Test
    public void testToString() {
        String result = employee.toString();
        assertTrue(result.contains("Mike"));
        assertTrue(result.contains("R25000.0"));
    }

    // Test No-Arg Constructor
    @Test
    public void testNoArgConstructor() {
        Employee emptyEmployee = new Employee();
        assertNotNull(emptyEmployee);
    }

    // Test the other  setters
    @Test
    public void testRemainingSetters() {
        employee.setJobTitle("Manager");
        assertEquals("Manager", employee.getJobTitle());

        employee.setPosition(Positions.Senior);
        assertEquals(Positions.Senior, employee.getPosition());

        employee.setDepartment("HR");
        assertEquals("HR", employee.getDepartment());
    }

    @Test
    public void testMinimumBasicSalary() {
        employee.setBasicSalary(4000);

        assertEquals(4000, employee.getBasicSalary(), 0.01);
    }

    @AfterEach
    public void printEmployeeDetails() {
        // Prints the details of the employee after each test
        System.err.println(employee.toString());
    }
}
