package com.tcs;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Employee dao = new Employee();

        while (true) {
            System.out.println("\n1. Create Employee");
            System.out.println("2. Display Employee");
            System.out.println("3. Raise Salary");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Name (max 2 words): ");
                    String name = sc.nextLine();
                    if (name.split("\\s+").length > 2) {
                        System.out.println("Only 2 words allowed!");
                        break;
                    }

                    System.out.print("Enter Age (20-60): ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    if (age < 20 || age > 60) {
                        System.out.println("Invalid age!");
                        break;
                    }

                    System.out.print("Enter Designation (Programmer/Manager/Tester): ");
                    String desig = sc.nextLine();

                    double salary;
                    if (desig.equalsIgnoreCase("programmer"))
                        salary = 20000;
                    else if (desig.equalsIgnoreCase("manager"))
                        salary = 25000;
                    else if (desig.equalsIgnoreCase("tester"))
                        salary = 15000;
                    else {
                        System.out.println("Invalid designation!");
                        break;
                    }

                    dao.createEmployee(name, age, desig, salary);
                    break;

                case 2:
                    dao.displayEmployee();
                    break;

                case 3:
                    System.out.print("Enter Employee Name: ");
                    String empName = sc.nextLine();

                    System.out.print("Enter increment percent (1-10): ");
                    double percent = sc.nextDouble();
                    sc.nextLine();

                    if (percent < 1 || percent > 10) {
                        System.out.println("Invalid percent!");
                        break;
                    }

                    dao.raiseSalary(empName, percent);
                    break;

                case 4:
                    System.out.println("Thank you for using this application 😊");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}