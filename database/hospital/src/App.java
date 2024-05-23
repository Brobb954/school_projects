import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    static Connection connection = null;
    static int query = 0;
    static ArrayList<String> queries = new ArrayList<String>();

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        // Load the MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Define the connection parameters
        String url = "jdbc:mysql://localhost:3306/hospital"; // connection string
        String username = "root"; // MySQL username
        String password = ""; // MySQL password

        // Establish the database connection
        connection = DriverManager.getConnection(url, username, password);

        // // Set up the database
        // DatabaseUtils.databaseSetUp(connection);
        // DatabaseUtils.fillInDatabase(connection);

        // Ask user what query they would like to run
        System.out.println("Welcome to the Hospital Database!");
        do {
            do {
                System.out.println("Please enter which query you would like to run (1-21) and type 99 to quit: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("That's not a number!");
                    scanner.next();
                }
                query = scanner.nextInt();
            } while (query < 1 || query > 21 && query != 99);

            if (query != 99) {
                // Get the queries
                queries = Queries.getQueries();

                String statement = queries.get(query);

                // Prepare the SQL statement
                PreparedStatement preparedStatement = connection.prepareStatement(statement);

                // Execute the query and retrieve the result set
                ResultSet resultSet = preparedStatement.executeQuery();

                ResultSetMetaData rsmd = resultSet.getMetaData();
                int columnsNumber = rsmd.getColumnCount();

                // Prints Column names
                for (int i = 1; i <= columnsNumber; i++) {
                    System.out.print(rsmd.getColumnName(i) + " ");
                }

                System.out.println();

                // Iterate through the data in the result set and display it.
                while (resultSet.next()) {
                    // Print one row
                    for (int i = 1; i <= columnsNumber; i++) {
                        System.out.print(resultSet.getString(i) + " "); // Print one element of a row
                    }
                    System.out.println(); // Move to the next line to print the next row.
                }
                System.out.println("\n");
            }
        } while (query != 99);
        scanner.close();
    }
}