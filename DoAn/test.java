package DoAn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class test {

        public static void main(String[] args) {
// Database connection parameters

            String URL = "jdbc:mysql://localhost:3306/duy";
            String USER = "root";
            String PASS = "";
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
                System.out.println("Connected to database: " + conn.getCatalog());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

}
