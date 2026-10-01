import java.util.*;

public class EmployeeTest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Create Employee object
        Employee employee = new Employee();

        // Create Attendance object
        Attendance attendance = new Attendance();

        // Create Salary object
        Salary salary = new Salary();

        int choice;

        do {

            System.out.println("\n=====================================");
            System.out.println("   Employee Payroll Management System");
            System.out.println("=====================================");
            System.out.println("1. Enter Employee Details");
            System.out.println("2. Calculate Attendance");
            System.out.println("3. Calculate Salary");
            System.out.println("4. Display Employee Details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

            // ==================================================
            // CASE 1 : ENTER EMPLOYEE DETAILS
            // ==================================================

            case 1:

                System.out.println("\n--- ENTER EMPLOYEE DETAILS ---");

                System.out.print("Enter Employee ID: ");
                employee.employeeId = sc.nextInt();

                /*
                 * nextInt() leaves the Enter key in the input buffer.
                 * Therefore nextLine() is used first to consume it.
                 */
                sc.nextLine();

                System.out.print("Enter Employee Name: ");
                employee.employeeName = sc.nextLine();

                // ----------------------------------------------
                // Department Selection
                // ----------------------------------------------

                System.out.println("\nSelect Department");
                System.out.println("1. IT");
                System.out.println("2. HR");
                System.out.println("3. Finance");
                System.out.println("4. Marketing");

                System.out.print("Enter Department Choice: ");
                employee.departmentChoice = sc.nextInt();

                // Switch for department selection

                switch (employee.departmentChoice) {

                case 1:
                    employee.department = "IT";
                    break;

                case 2:
                    employee.department = "HR";
                    break;

                case 3:
                    employee.department = "Finance";
                    break;

                case 4:
                    employee.department = "Marketing";
                    break;

                default:
                    employee.department = "Unknown";
                    System.out.println("Invalid department");

                }

                // ----------------------------------------------
                // Basic Salary
                // ----------------------------------------------

                System.out.print("Enter Basic Salary: ");
                employee.basicSalary = sc.nextDouble();

                if (employee.basicSalary > 0) {

                    System.out.println(
                        "Employee details entered successfully."
                    );

                } else {

                    System.out.println(
                        "Invalid salary. Salary must be greater than zero."
                    );
                }

                break;

            // ==================================================
            // CASE 2 : CALCULATE ATTENDANCE
            // ==================================================

            case 2:

                System.out.println("\n--- ATTENDANCE CALCULATION ---");

                System.out.print(
                    "Enter Total Working Days: "
                );

                attendance.totalWorkingDays = sc.nextInt();

                /*
                 * Check whether working days are valid.
                 *
                 * IMPORTANT:
                 * The FOR LOOP and attendance calculation
                 * must be INSIDE this if block.
                 */

                if (attendance.totalWorkingDays > 0) {

                    // Reset previous values
                    attendance.presentDays = 0;
                    attendance.absentDays = 0;

                    /*
                     * for loop is used because we know
                     * the number of working days.
                     */
                    for (
                        int day = 1;
                        day <= attendance.totalWorkingDays;
                        day++
                    ) {

                        System.out.print(
                            "Day " + day
                            + " - Enter 1 for Present, 0 for Absent: "
                        );

                        attendance.attendance = sc.nextInt();

                        /*
                         * Check attendance
                         */
                        if (attendance.attendance == 1) {

                            attendance.presentDays++;

                        } else if (attendance.attendance == 0) {

                            attendance.absentDays++;

                        } else {

                            System.out.println(
                                "Invalid input. Enter only 1 or 0."
                            );

                            /*
                             * Invalid input is not counted
                             * as present or absent.
                             */
                        }
                    }

                    // ------------------------------------------
                    // Calculate Attendance Percentage
                    // ------------------------------------------

                    attendance.attendancePercentage =
                        ((double) attendance.presentDays
                        / attendance.totalWorkingDays) * 100;

                    System.out.println(
                        "\nPresent Days : "
                        + attendance.presentDays
                    );

                    System.out.println(
                        "Absent Days  : "
                        + attendance.absentDays
                    );

                    System.out.println(
                        "Attendance % : "
                        + attendance.attendancePercentage
                        + "%"
                    );

                    // ------------------------------------------
                    // Attendance Eligibility
                    // ------------------------------------------

                    if (attendance.attendancePercentage >= 75) {

                        System.out.println(
                            "Attendance Status: Eligible"
                        );

                    } else {

                        System.out.println(
                            "Attendance Status: Not Eligible"
                        );
                    }

                } else {

                    System.out.println(
                        "Working days must be greater than zero."
                    );
                }

                break;

            // ==================================================
            // CASE 3 : CALCULATE SALARY
            // ==================================================

            case 3:

                System.out.println("\n--- SALARY CALCULATION ---");

                /*
                 * First check whether employee
                 * salary has been entered.
                 */

                if (employee.basicSalary > 0) {

                    /*
                     * Check whether attendance
                     * has been calculated.
                     */

                    if (attendance.totalWorkingDays > 0) {

                        /*
                         * Check attendance percentage.
                         */

                        if (attendance.attendancePercentage >= 90) {

                            // 10% incentive

                            salary.incentive =
                                employee.basicSalary * 0.10;

                            salary.deduction = 0;

                            salary.finalSalary =
                                employee.basicSalary
                                + salary.incentive;

                            System.out.println(
                                "Attendance Category: Excellent"
                            );

                            System.out.println(
                                "Attendance Incentive: 10%"
                            );

                        } else if (
                            attendance.attendancePercentage >= 75
                        ) {

                            // No incentive
                            // No deduction

                            salary.incentive = 0;

                            salary.deduction = 0;

                            salary.finalSalary =
                                employee.basicSalary;

                            System.out.println(
                                "Attendance Category: Good"
                            );

                            System.out.println(
                                "Attendance Incentive: 0%"
                            );

                        } else {

                            // 10% deduction

                            salary.incentive = 0;

                            salary.deduction =
                                employee.basicSalary * 0.10;

                            salary.finalSalary =
                                employee.basicSalary
                                - salary.deduction;

                            System.out.println(
                                "Attendance Category: Low"
                            );

                            System.out.println(
                                "Attendance Deduction: 10%"
                            );
                        }

                        // --------------------------------------
                        // Display Salary
                        // --------------------------------------

                        System.out.println(
                            "Basic Salary : ₹"
                            + employee.basicSalary
                        );

                        System.out.println(
                            "Final Salary : ₹"
                            + salary.finalSalary
                        );

                    } else {

                        System.out.println(
                            "Please calculate attendance first."
                        );
                    }

                } else {

                    System.out.println(
                        "Please enter valid employee details first."
                    );
                }

                break;

            // ==================================================
            // CASE 4 : DISPLAY EMPLOYEE DETAILS
            // ==================================================

            case 4:

                System.out.println("\n--- EMPLOYEE DETAILS ---");

                /*
                 * Check whether employee details
                 * have been entered.
                 */

                if (employee.employeeId != 0) {

                    System.out.println(
                        "Employee ID   : "
                        + employee.employeeId
                    );

                    System.out.println(
                        "Employee Name : "
                        + employee.employeeName
                    );

                    System.out.println(
                        "Department    : "
                        + employee.department
                    );

                    System.out.println(
                        "Basic Salary  : ₹"
                        + employee.basicSalary
                    );

                    System.out.println(
                        "Present Days  : "
                        + attendance.presentDays
                    );

                    System.out.println(
                        "Absent Days   : "
                        + attendance.absentDays
                    );

                    System.out.println(
                        "Attendance %  : "
                        + attendance.attendancePercentage
                        + "%"
                    );

                    System.out.println(
                        "Final Salary  : ₹"
                        + salary.finalSalary
                    );

                } else {

                    System.out.println(
                        "No employee details available."
                    );
                }

                break;

            // ==================================================
            // CASE 5 : EXIT
            // ==================================================

            case 5:

                System.out.println(
                    "\nThank you for using the system."
                );

                break;

            // ==================================================
            // INVALID MENU CHOICE
            // ==================================================

            default:

                System.out.println(
                    "Invalid choice. Please enter 1 to 5."
                );
            }

        } while (choice != 5);

        sc.close();
    }
}