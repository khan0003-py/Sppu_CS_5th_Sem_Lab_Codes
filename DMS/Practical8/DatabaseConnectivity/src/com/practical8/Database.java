
/*
 
CREATE SCHEMA arshad; 
USE arshad;
CREATE TABLE student(
    ID INT PRIMARY KEY,
    Name VARCHAR(255),
    City VARCHAR(255),
    Marks INT);
    
*/

package com.practical8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Database{

    public static Connection ConnectionToDatabase() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/arshad"; 
        String user = "root";
        String password = "Admin@3103";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect = DriverManager.getConnection(url, user, password);

        return connect;
    }

    public static void insertStudent(Connection connection, int id, String name, String city, int marks) {
        try {
            String insertQuery = "INSERT INTO student (id, name, city, marks) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, city);
            preparedStatement.setInt(4, marks);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student record inserted successfully.");
            } else {
                System.out.println("Failed to insert student record.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void retrieveStudent(Connection connection, int id) {
        try {
            String retrieveQuery = "SELECT * FROM Student WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(retrieveQuery);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int studentID = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                String city = resultSet.getString("City");
                int marks = resultSet.getInt("Marks");
                System.out.println("--------------------------");
                System.out.println("Student ID: " + studentID);
                System.out.println("Name: " + name);
                System.out.println("City: " + city);
                System.out.println("Marks: " + marks);
                System.out.println("--------------------------");
            } else {
                System.out.println("Student not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateStudent(Connection connection, int id, String name, String city, int marks) {
        try {
            String updateQuery = "UPDATE Student SET Name = ?, City = ?, Marks = ? WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, city);
            preparedStatement.setInt(3, marks);
            preparedStatement.setInt(4, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student record updated successfully.");
            } else {
                System.out.println("Failed to update student record.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteStudent(Connection connection, int id) {
        try {
            String deleteQuery = "DELETE FROM Student WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student record deleted successfully.");
            } else {
                System.out.println("Failed to delete student record.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (Connection connection = ConnectionToDatabase()) {
            Scanner scanner = new Scanner(System.in);
            int choice;
            do {
            	System.out.println();
                System.out.println("Student Database CRUD Operations:");
                System.out.println("1. Insert Student");
                System.out.println("2. Retrieve Student");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                    	System.out.println();
                        System.out.print("Enter Student ID: ");
                        int id = scanner.nextInt();
                        System.out.print("Enter Student Name: ");
                        String name = scanner.next();
                        System.out.print("Enter Student City: ");
                        String city = scanner.next();
                        System.out.print("Enter Student Marks: ");
                        int marks = scanner.nextInt();
                        insertStudent(connection, id, name, city, marks);
                        break;
                    case 2:
                    	System.out.println();
                        System.out.print("Enter Student ID to retrieve: ");
                        int retrieveID = scanner.nextInt();
                        retrieveStudent(connection, retrieveID);
                        break;
                    case 3:
                    	System.out.println();
                        System.out.print("Enter Student ID to update: ");
                        int updateID = scanner.nextInt();
                        System.out.print("Enter updated Student Name: ");
                        String updatedName = scanner.next();
                        System.out.print("Enter updated Student City: ");
                        String updatedCity = scanner.next();
                        System.out.print("Enter updated Student Marks: ");
                        int updatedMarks = scanner.nextInt();
                        updateStudent(connection, updateID, updatedName, updatedCity, updatedMarks);
                        break;
                    case 4:
                    	System.out.println();
                        System.out.print("Enter Student ID to delete: ");
                        int deleteID = scanner.nextInt();
                        deleteStudent(connection, deleteID);
                        break;
                    case 5:
                    	System.out.println();
                        System.out.println("Exiting...");
                        System.out.println();
                        System.out.println("By Arshad Khan : @whoami0003.py");
                        System.out.println();
                        break;
                    default:
                    	System.out.println();
                        System.out.println("Invalid choice. Please select a valid option.");
                        break;
                }
            } while (choice != 5);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
