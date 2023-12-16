import java.sql.*;


/**
 * Dit is een voorbeeld Java toepassing waarin je verbinding maakt met een
 * SQLServer database.
 */
public class Database {
    

    private static String connectionUrl = "jdbc:sqlserver://localhost;databaseName=CodeCademyDD;integratedSecurity=false;encrypt=true;trustServerCertificate=true;";
    private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String username = "Steven";
    private static String password = "Striker@2024";
    private static Connection con = null;

    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(connectionUrl, username, password);
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection."); 
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found."); 
        }
        return con;
    }
}
