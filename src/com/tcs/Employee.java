package com.tcs;
import java.sql.*;

public class Employee {

    Connection con;

    public Employee() {
        try {
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/employeeDB",
                "root",
                "Root123$"   // your MySQL password
            );
        } catch (Exception e) {
            System.out.println("Database connection failed");
        }
    }

    // CREATE EMPLOYEE
    public void createEmployee(String name, int age, String desig, double salary) {
        try {
            String sql = "INSERT INTO employee VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, desig);
            ps.setDouble(4, salary);
            ps.executeUpdate();
            System.out.println("Employee created and stored in database");
        } catch (SQLException e) {
            System.out.println("Employee already exists!");
        }
    }

    // DISPLAY EMPLOYEE
    public void displayEmployee() {
        try {
            String sql = "SELECT * FROM employee";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (!rs.next()) {
                System.out.println("No employee found!");
                return;
            }

            do {
                System.out.println("----- Employee Details -----");
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Designation: " + rs.getString("designation"));
                System.out.println("Salary: " + rs.getDouble("salary"));
            } while (rs.next());

        } catch (Exception e) {
            System.out.println("Error displaying employee");
        }
    }

    // RAISE SALARY
    public void raiseSalary(String name, double percent) {
        try {
            String fetch = "SELECT salary FROM employee WHERE name=?";
            PreparedStatement ps1 = con.prepareStatement(fetch);
            ps1.setString(1, name);
            ResultSet rs = ps1.executeQuery();

            if (!rs.next()) {
                System.out.println("Employee not found!");
                return;
            }

            double oldSalary = rs.getDouble("salary");
            double newSalary = oldSalary + (oldSalary * percent / 100);

            String update = "UPDATE employee SET salary=? WHERE name=?";
            PreparedStatement ps2 = con.prepareStatement(update);
            ps2.setDouble(1, newSalary);
            ps2.setString(2, name);
            ps2.executeUpdate();

            System.out.println("Salary updated successfully ✅");

        } catch (Exception e) {
            System.out.println("Error updating salary");
        }
    }
}