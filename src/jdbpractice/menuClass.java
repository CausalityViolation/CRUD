package jdbpractice;

import java.sql.SQLException;

public class menuClass {

    static JDBCPractice manage = new JDBCPractice();

    public void menu() throws SQLException {

        System.out.println("1. Create new Student");
        System.out.println("2. Update a Students name");
        System.out.println("3. Update a Student age");
        System.out.println("4. Delete a Student");
        System.out.println("5. Show Student information");
        System.out.println("6. Show all Students");
        System.out.println("0. Exit Application");

        int choice = JDBCPractice.input.nextInt();
        JDBCPractice.input.nextLine();

        switch (choice) {

            case 0:
                JDBCPractice.loop = false;
                break;

            case 1:
                manage.createStudent();
                break;

            case 2:
                manage.updateName();
                break;

            case 3:
                manage.updateAge();
                break;

            case 4:
                manage.deleteStudent();
                break;

            case 5:
                manage.showStudent();
                break;

            case 6:
                manage.showAll();
                break;

            default:
                System.out.println("Invalid Input");

        }
    }
}
