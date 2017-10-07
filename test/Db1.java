

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
public class Db1 {

    // private static String url="jdbc:mysql://localhost::3306/oztutor123";
    private String url;
    private String user;
    private String pass;
    private Connection myConn;
    private Statement myStmt;
    private ResultSet myRs;
    private String dbName;

    /**
     * constructor to connect to db
     *
     * @param dbName database name
     */
    public Db1(String dbName) {
        try {
            if (dbName.equals("")) {
                dbName = "waa";
            }
            this.url = "jdbc:mysql://localhost:3306/" + dbName;
            this.user = "admin2";
            this.pass = "admin2";
            myConn = DriverManager
                    .getConnection(this.url, this.user, this.pass);
        } catch (Exception e) {
            // System.out.println("erro" + e.getMessage());
        }
    }

    /**
     * constructor to connect to db
     *
     *
     */
    public Db1() {
        try {

            dbName = "waa";
            this.url = "jdbc:mysql://localhost:3306/" + dbName;
            this.user = "admin2";
            this.pass = "admin2";
            myConn = DriverManager
                    .getConnection(this.url, this.user, this.pass);
        } catch (Exception e) {
            // System.out.println("erro" + e.getMessage());
        }
    }

    public class DbBook {

        private String tableName = "books";
        private String dbName = "waa";

        /*
		 * a consturctor for DbBook class
         */
        public DbBook() {

        }

        /**
         * set status of a specific book with book id
         *
         * @param bookid
         * @param status
         * @return if update is successfull it returns true. otherwise it
         * returns false
         * @throws Exception
         */
        public boolean setStatus(Integer bookid, String status)
                throws Exception {
            try {

                myStmt = myConn.createStatement();
                String sql = "update " + dbName + "." + tableName
                        + " set status='" + status + "' where id=" + bookid;
                // System.out.println(sql);
                myStmt.executeUpdate(sql);

                return true;

            } catch (Exception e) {
                // System.out.println(e.getMessage());
                throw new BookException(
                        "4042-updatetBook-book database there is issue with our database");

            }

        }

        /**
         * function to update book details in db with book id
         *
         * @param bookid
         * @param title
         * @param authorList
         * @param isbn
         * @param publishDate
         * @param publisher
         * @param status
         * @return if action is successfull return true otherwise return false
         * @throws Exception
         */
        public boolean updateBook(Integer bookid, String title,
                String authorList, String isbn, String publishDate,
                String publisher, String status) throws Exception {
            try {

                myStmt = myConn.createStatement();
                String sql = "update " + dbName + "." + tableName
                        + " set title='" + title + "',authorList='"
                        + authorList + "',isbn='" + isbn + "',publishDate='"
                        + publishDate + "',status='" + status + "',publisher='"
                        + publisher + "' where id=" + bookid;
                // System.out.println(sql);
                myStmt.executeUpdate(sql);

                return true;

            } catch (Exception e) {
                // System.out.println(e.getMessage());
                e.printStackTrace();
                throw new BookException(
                        "4042-updatetBook-book database there is issue with our database");

            }

        }

        /**
         * function to delete a book with book id
         *
         * @param bookid
         * @return if action is successfull return true otherwise return false
         * @throws Exception
         */
        public boolean deleteBook(Integer bookid) throws Exception {
            try {

                myStmt = myConn.createStatement();
                String sql = "delete from " + dbName + "." + tableName
                        + " where id=" + bookid;
                // System.out.println(sql);
                myStmt.executeUpdate(sql);

                return true;

            } catch (Exception e) {
                // System.out.println(e.getMessage());
                throw new BookException(
                        "insertBook -book database there is issue with our database");

            }

        }

        /**
         * get all books in database
         *
         * @return rsultSet (it is usable in any other functions)
         * @throws BookException
         */
        public ResultSet getAllBooks() throws Exception {
            try {
                myStmt = myConn.createStatement();
                myRs = myStmt.executeQuery("select * from " + tableName);
                return myRs;

            } catch (Exception e) {
                // System.out.println(e.getMessage());
                throw new BookException(
                        " book database there is issue with our database");

            }
        }

        /**
         * get one pecific book with id
         *
         * @return rsultSet (it is usable in any other functions)
         * @throws BookException
         */
        public ResultSet getBook(Integer bookid) throws Exception {
            try {
                myStmt = myConn.createStatement();
                myRs = myStmt.executeQuery("select * from " + tableName
                        + " where id=" + bookid);
                return myRs;

            } catch (Exception e) {
                // System.out.println(e.getMessage());
                throw new BookException(
                        " book database there is issue with our database");

            }
        }

        /**
         * function to check if book_id exist in database
         *
         * @param bookid
         * @return if book_id exist in db it returns true, otherwise it returns
         * false
         * @throws Exception
         */
        public boolean findBook(Integer bookid) throws Exception {
            boolean result = false;
            try {
                myStmt = myConn.createStatement();
                myRs = myStmt.executeQuery("select count(*) from " + tableName
                        + " where id=" + bookid);
                while (myRs.next()) {
                    if (myRs.getInt(1) > 0) {
                        result = true;
                    } else {
                        result = false;
                    }
                }

                return result;
            } catch (Exception e) {
                // System.out.println(e.getMessage());
                throw new BookException(
                        " book database there is issue with our database");

            }
        }

        /**
         * function to check if book_id exist in database
         *
         * @param bookid
         * @return if book_id exist in db it returns true, otherwise it returns
         * false
         * @throws Exception
         */
        public boolean isIsbnExist(String isbn) throws Exception {
            boolean result = false;
            try {
                myStmt = myConn.createStatement();
                myRs = myStmt.executeQuery("select count(*) from " + tableName
                        + " where isbn=" + isbn);
                while (myRs.next()) {
                    if (myRs.getInt(1) > 0) {
                        result = true;
                    } else {
                        result = false;
                    }
                }

                return result;
            } catch (Exception e) {
                // System.out.println(e.getMessage());
                throw new BookException(
                        " book database there is issue with our database");

            }
        }

        /**
         * function to check if isbn exists in database already(isbn is
         * international unique key)
         *
         * @param isbn
         * @return return true if isbn already exists, otherwise it returns
         * false
         * @throws BookException
         */
        public boolean isBookWithIsbnExist(String isbn) throws BookException {
            boolean result = false;
            try {
                myStmt = myConn.createStatement();
                myRs = myStmt.executeQuery("select count(*) from " + tableName
                        + " where isbn=" + isbn);
                while (myRs.next()) {
                    if (myRs.getInt(1) > 0) {
                        result = true;
                    } else {
                        result = false;
                    }
                }

                return result;
            } catch (Exception e) {
                // System.out.println(e.getMessage());
                throw new BookException(
                        " book database there is issue with our database");

            }

        }

        /**
         * function to return last book entered into db
         *
         * @return last Book_id in db
         * @throws Exception
         */
        public Integer getLastIdinserted() throws Exception {
            try {
                Integer result = 0;
                myStmt = myConn.createStatement();
                myRs = myStmt.executeQuery("select * from " + tableName
                        + " order by id desc limit 1");
                while (myRs.next()) {
                    result = myRs.getInt("id");
                }
                return result;

            } catch (Exception e) {
                // // System.out.println(e.);
                throw new BookException(
                        " book database there is issue with our database");

            }
        }

        /**
         * function to insert a book
         *
         * @param bookId
         * @param bookTitle
         * @param authorList
         * @param isbn
         * @param publisher
         * @param publishDate
         * @param status
         * @param stid
         * @return if inserted successfully it return true
         * @throws BookException
         */
//		public boolean insretBook(Book objBook) throws BookException {
//			try {
//				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//
//				myStmt = myConn.createStatement();
//				String sql = "insert into " + dbName + "." + tableName
//						+ "(title,authorList,isbn,"
//						+ "publisher,publishDate,status,createdTime) "
//						+ "values('" + objBook.getBookTitle() + "','"
//						+ objBook.getAuthorList() + "','" + objBook.getIsbn()
//						+ "'" + ",'" + objBook.getPublisher() + "','"
//						+ sdf.format(objBook.getPublishDate()) + "','"
//						+ objBook.getStatus() + "',NOW())";
//				// System.out.println(sql);
//				myStmt.executeUpdate(sql);
//
//				return true;
//
//			} catch (Exception e) {
//				// System.out.println(e.getMessage());
//				throw new BookException(
//						"insertBook -book database there is issue with our database");
//
//			}
//
//		}
        /**
         * insert book into db
         *
         * @param title
         * @param authorList
         * @param isbn
         * @param publisher
         * @param publishDate
         * @param status
         * @return
         * @throws BookException
         */
        public boolean insretBook2(String title, String authorList,
                String isbn, String publisher, String publishDate, String status)
                throws BookException {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                myStmt = myConn.createStatement();
                String sql = "insert into " + dbName + "." + tableName
                        + "(title,authorList,isbn,"
                        + "publisher,publishDate,status,createdTime) "
                        + "values('" + title + "','" + authorList + "','"
                        + isbn + "'" + ",'" + publisher + "','" + publishDate
                        + "','" + status + "',NOW())";
                // System.out.println(sql);
                myStmt.executeUpdate(sql);

                return true;

            } catch (Exception e) {
                // System.out.println(e.getMessage());
                throw new BookException(
                        "insertBook -book database there is issue with our database");

            }

        }

    }

    public class DbStudentBorrow {

        private String tableName = "student_borrow";

        /**
         * constructor for student records table
         */
        public DbStudentBorrow() {

        }

        /**
         * function to check if a record for specific student_id and book_id
         * exist(we should not have more than one book_id in db records)
         *
         * @param studentId
         * @param bookId
         * @return if it exist it return true
         * @throws BookException
         */
        public boolean isRecordExist(Integer studentId, Integer bookId)
                throws BookException {
            boolean result = false;
            try {
                myStmt = myConn.createStatement();
                myRs = myStmt.executeQuery("select count(*) from " + tableName
                        + " where book_id=" + bookId + " and student_id="
                        + studentId);
                while (myRs.next()) {
                    if (myRs.getInt(1) > 0) {
                        result = true;
                    } else {
                        result = false;
                    }
                }

                return result;
            } catch (Exception e) {
                // System.out.println(e.getMessage());
                throw new BookException(
                        " StudentBorrow database there is issue with our database");

            }
        }

        /**
         * function to retrive records of specific student with student id
         *
         * @param studentId
         * @return it return resultset of that row
         * @throws Exception
         */
        public ResultSet getRecordsOfStudent(Integer studentId)
                throws Exception {
            try {
                myStmt = myConn.createStatement();
                myRs = myStmt.executeQuery("select * from " + tableName
                        + " where student_id=" + studentId);
                return myRs;

            } catch (Exception e) {
                // System.out.println(e.getMessage());
                throw new BookException(
                        " book database there is issue with our database");

            }
        }

        /**
         * it return records of specific student in detail of book
         *
         * @param studentId
         * @return resultset record row
         * @throws Exception
         */
        public ResultSet getRecordsOfStudent2(Integer studentId)
                throws Exception {
            try {
                myStmt = myConn.createStatement();
                myRs = myStmt
                        .executeQuery("select * from "
                                + tableName
                                + " br inner join waa.books bk on bk.id=br.book_id  where student_id="
                                + studentId);
                return myRs;

            } catch (Exception e) {
                // System.out.println(e.getMessage());
                throw new BookException(
                        " book database there is issue with our database");

            }
        }

        /**
         * this function return records of all student in db
         *
         * @return resultset of all records in db borrowrecords
         * @throws Exception
         */
        public ResultSet getAllStudentRecords() throws Exception {
            try {
                myStmt = myConn.createStatement();
                myRs = myStmt.executeQuery("select * from " + tableName);
                return myRs;

            } catch (Exception e) {
                // System.out.println(e.getMessage());
                throw new BookException(
                        " book database there is issue with our database");

            }
        }

        /**
         * function that update records of borrow for student_id and book_id
         * (change action)
         *
         * @param studentId
         * @param bookId
         * @param action
         * @return
         * @throws Exception
         */
        public boolean updateRecords(Integer studentId, Integer bookId,
                String action) throws Exception {
            boolean result = false;
            try {

                myStmt = myConn.createStatement();
                String sql = "update " + tableName + " set action='" + action
                        + "' where student_id=" + studentId + " book_id="
                        + bookId + " ";
                // System.out.println(sql);
                myStmt.executeUpdate(sql);

                result = true;

                return result;

            } catch (Exception e) {
                // System.out.println(e.getMessage());
                throw new BookException(
                        "4044-updateStudent- database there is issue with our database");

            }

        }

        /**
         * function to insert borrow record for student_id and book_id
         *
         * @param studentId
         * @param bookId
         * @param action
         * @return if action is successfull return true otherwise return false
         * @throws Exception
         */
        public boolean insertRecords(Integer studentId, Integer bookId,
                String action) throws Exception {
            boolean result = false;
            try {

                myStmt = myConn.createStatement();
                String sql = "insert into " + tableName
                        + "(student_id,book_id," + "action,createdTime)"
                        + " values(" + studentId + "," + bookId + ",'" + action
                        + "',now())";
                // System.out.println(sql);
                myStmt.executeUpdate(sql);

                result = true;

                return result;

            } catch (Exception e) {
                // System.out.println(e.getMessage());
                e.printStackTrace();
                throw new BookException(
                        "4045-insertStudentrecords- database there is issue with our database");

            }

        }

        /**
         * delete records for student_borrrowing tabale
         *
         * @param studentId
         * @param bookId
         * @param action
         * @return if action is successfull return true otherwise return false
         * @throws Exception
         */
        public boolean deleteRecords(Integer studentId, Integer bookId)
                throws Exception {
            boolean result = false;
            try {

                myStmt = myConn.createStatement();
                String sql = "delete from " + tableName + " where student_id="
                        + studentId + " and book_id=" + bookId;
                // System.out.println(sql);
                myStmt.executeUpdate(sql);

                result = true;

                return result;

            } catch (Exception e) {
                // System.out.println(e.getMessage());
                throw new BookException(
                        "4049-deleteStudentrecords- database there is issue with our database");

            }

        }

    }

}
