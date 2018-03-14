package DateBase;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler {

    private static String HOST = "jdbc:mysql://localhost:3306/itpdb?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String USERNAME = "root";
    private static String PASSWORD = "megakek228";

    private Connection connection;

    /**
    * Method for opening connection with date base
    */
    public DBHandler() {

        try {

            connection =DriverManager.getConnection(HOST, USERNAME, PASSWORD);

            /*if (!getConnection().isClosed()){
                System.out.println("DB is active");
            }*/
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public Connection getConnection() {
        return connection;
    }
}
