package employee;

/**
 * @author : Dimpho Phala
 * Date : 01/11/2026
 **/

public class EmployeeRunner {

    public static void main(String[] args) {

        Employee[] employees = new Employee[4];

        employees[0] = new Employee(
                "EM123",
                "Mike",
                "Developer",
                Positions.Junior,
                "IT",
                25000
        );

        employees[1] = new Employee(
                "EM124",
                "Thabo",
                "Accountant",
                Positions.Director,
                "FIN",
                26000
        );

        employees[2] = new Employee(
                "EM125",
                "Jessica",
                "Tester",
                Positions.Intermediate,
                "IT",
                31000
        );

        employees[3] = new Employee(
                "EM126",
                "Lerato",
                "Accountant",
                Positions.Senior,
                "FINANCE",
                34000
        );

        for (Employee emp : employees) {

            try {

                emp.raiseSalary(15);

                emp.promote(7);

                System.out.println(emp);

            } catch (HighSalaryIncrease e) {

                System.out.println(e.getMessage());
            }

            System.out.println("++++++++++++++++++++++++++++++++++++");
        }
    }
}