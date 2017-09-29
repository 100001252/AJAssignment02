
import java.sql.ResultSet;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author XC8184
 */
public class DatabaseTest {

    public static void main(String[] args) {
        Db.ConnecrDb();
        Db db = new Db();
        try {
            db.insert();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // System.out.println(getAllBook());
        //  mytest_TestVwMoveCars4();//expect to avoid accident(work on two different thread to fix it)
    }

//    public static String getAllBook() {
//
//        String result = "";
//        try {
//
//            Db.DbBook dbBook = new Db().new DbBook();
//            ResultSet myRs = dbBook.getAllBooks();
//            while (myRs.next()) {
//                result += "" + myRs.getInt("id") + "/#"
//                        + myRs.getString("title") + "/#"
//                        + myRs.getString("authorList") + "/#"
//                        + myRs.getString("isbn") + "/#"
//                        + myRs.getString("publisher") + "/#"
//                        + myRs.getString("publishDate") + "/#"
//                        + myRs.getString("status") + "##";
//                // lstBook.add(new
//                // Book(myRs.getInt("id")/#myRs.getString(2),myRs.getString(3),myRs.getString(4),myRs.getString("publisher"),myRs.getString("publishDate"),myRs.getString("status"),myRs.getInt("student_id")));
//            }
//
//            System.out.println("myNotegetallbookunibbbb \n" + result);
//            return result;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return e.getMessage();
//        }
//
//    }
}
