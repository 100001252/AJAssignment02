
public class RunDb {

    public static void main(String[] args) {// ToDO
        try {
            testGetAllStudent();
            // testinsertStudent();
            // testdeleteStudent(1);
            // testisStudentExist(1, "33");

        } catch (Exception e) {
            // System.out.println(e.getMessage());
        }
    }

    public static void testisStudentExist(Integer stid, String pin) {
        Ass02StudentSoap uniStudent = new Ass02StudentSoap();
        uniStudent.isStudentExist(stid, pin);
    }

    public static void testdeleteStudent(Integer stid) {
        try {
            Ass02StudentSoap uniStudent = new Ass02StudentSoap();
            uniStudent.deleteStudent(stid);
        } catch (Exception e) {
            // System.out.println(e.getMessage());
        }
    }

    public static void testGetAllStudent() {
        Ass02StudentSoap uniStudent = new Ass02StudentSoap();
        uniStudent.getAllStudent();
    }

    public static void testinsertStudent() {
        try {
            Ass02StudentSoap uniStudent = new Ass02StudentSoap();
            uniStudent.addStudent("mansour", "3345");
        } catch (Exception e) {
            // System.out.println(e.getMessage());
        }
    }

}
