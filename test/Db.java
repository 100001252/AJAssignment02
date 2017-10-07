
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/**
 * class DB to connect to db for book database and student record database
 *
 * @author XC8184
 *
 */
public class Db {

    private static String url;
    private static String user;
    private static String pass;
    private Connection myConn;
    private Statement myStmt;
    private ResultSet myRs;
    private String dbName = "advanced_java_ass01";
    private String table_race = "race";

    public static Connection ConnecrDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://localhost:3306/waa";
            user = "admin2";
            pass = "admin2";
            Connection conn = DriverManager.getConnection(Db.url, Db.user, Db.pass);
            // System.out.println("connected");
            return conn;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Db() {
        this.myConn = ConnecrDb();

    }

    public boolean insert()
            throws Exception {
        try {

            myStmt = myConn.createStatement();
            String sql = "insert into " + dbName + "." + this.table_race
                    + "( `race_name`, `description`) values('test1','test1desc')";
            // System.out.println(sql);
            myStmt.executeUpdate(sql);

            return true;

        } catch (Exception e) {
            // System.out.println(e.getMessage());
            throw new BookException(
                    "4042-updatetBook-book database there is issue with our database");

        }

    }

    public boolean btnSaveToDatabaseClicked()
            throws Exception {
        try {

            myStmt = myConn.createStatement();
            String sql = "insert into " + dbName + "." + this.table_race
                    + "( `race_name`, `description`) values('test1','test1desc')";
            // System.out.println(sql);
            myStmt.executeUpdate(sql);

            return true;

        } catch (Exception e) {
            // System.out.println(e.getMessage());
            throw new BookException(
                    "4042-updatetBook-book database there is issue with our database");

        }

    }

}
