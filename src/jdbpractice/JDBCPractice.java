package jdbpractice;

import java.sql.*;
import java.util.Scanner;

public class JDBCPractice {

    static Scanner input = new Scanner(System.in);
    static Connection connection;
    static Statement statement;
    static boolean loop = true;
    static String url = "jdbc:mysql://localhost:3306/students";
    static String username = "root";
    static String password = "root";

    public static void main(String[] args) throws SQLException {

        menuClass menu = new menuClass();

        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();

        } catch (SQLException ex) {
            System.out.println("Could not connect to database");
        }

        while (loop) {
            menu.menu();
        }

    }

    public void createStudent() throws SQLException {

        PreparedStatement createStudent = connection.prepareStatement("INSERT into registration(name, age, SocSecNr) values(?,?,?)");

        System.out.println("Input name: ");
        String name = input.nextLine();

        System.out.println("Input age: ");
        int age = input.nextInt();
        input.nextLine();

        System.out.println("<A Social Security Number(SoSNr) will ge generated for you>");
        var sosNr = randomSoSNr();


        createStudent.setString(1, name);
        createStudent.setInt(2, age);
        createStudent.setInt(3, (int) sosNr);

        createStudent.executeUpdate();


    }

    public void showAll() throws SQLException {

        ResultSet rs = statement.executeQuery("SELECT * from registration");
        while (rs.next()) {

            System.out.println("ID: " + rs.getInt("ID")
                    + "\nName: " + rs.getString("Name")
                    + "\nAge: " + rs.getInt("Age")
                    + "\nSocial Security Number: " + rs.getInt("SocSecNr") + "\n");

        }

    }

    public void updateName() throws SQLException {

        PreparedStatement updateName = connection.prepareStatement("UPDATE registration set name=? where id=?");

        System.out.println("Enter the ID of the student you'd like to update: ");
        int id = input.nextInt();
        input.nextLine();

        System.out.println("New name: ");
        String name = input.nextLine();

        updateName.setInt(2, id);
        updateName.setString(2, name);

        updateName.executeUpdate();

    }

    public void updateAge() throws SQLException {

        PreparedStatement updateSalary = connection.prepareStatement("update registration set age=?");

        System.out.println("Enter the ID of the student you'd like to update: ");
        int id = input.nextInt();
        input.nextLine();

        System.out.println("Enter new value for AGE: ");
        int age = input.nextInt();
        input.nextLine();


        updateSalary.setInt(id, age);
        updateSalary.executeUpdate();

    }

    public void deleteStudent() throws SQLException {

        PreparedStatement deleteStatement = connection.prepareStatement("delete from registration where id=?");

        System.out.println("Enter the ID of the students records you'd like to remove: ");
        int id = input.nextInt();
        input.nextLine();

        deleteStatement.setInt(1, id);

        deleteStatement.executeUpdate();

    }

    public void showStudent() throws SQLException {

        PreparedStatement showStudent = connection.prepareStatement("select * from registration where id=?");

        System.out.println("Enter the ID of the student you'd like to display additional information about: ");
        int id = input.nextInt();
        input.nextLine();

        showStudent.setInt(1, id);

        ResultSet rs = showStudent.executeQuery();

        while (rs.next()) {

            System.out.print("ID: " + rs.getInt("ID")
                    + "\nName: " + rs.getString("Name")
                    + "\nAge: " + rs.getInt("Age")
                    + "\nSocial Security Number: " + rs.getInt("SocSecNr") + "\n");
        }

    }

    public double randomSoSNr() {

        var min = Math.ceil(10000000);
        var max = Math.floor(99999999);

        return (int)Math.round(Math.floor(Math.random() * (max - min) + min));

    }

}
