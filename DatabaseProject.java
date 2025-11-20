import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

public final class DatabaseProject {
    private DatabaseProject() {
    }

    private static final String DATABASE = "project.db";

    public static Connection conn = null;

    public static Connection initializeDB(String databaseFileName) {
        String url = "jdbc:sqlite:" + databaseFileName;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("The connection to the database was successful.");
            } else {
                System.out.println("Null Connection");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("There was a problem connecting to the database.");
        }
        return conn;
    }

    public static void main(String args[]) {
        conn = initializeDB(DATABASE);
        Model model = new Model();
        Controller controller = new Controller(model);

        Scanner in = new Scanner(System.in);
        String userAction = "";
        while (Pattern.matches("[1-8]", userAction) || userAction == "") {
            controller.printOptions();
            System.out.print("Enter option number: ");
            userAction = in.nextLine();
            controller.processOptions(userAction, in);
        }
        in.close();

        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
