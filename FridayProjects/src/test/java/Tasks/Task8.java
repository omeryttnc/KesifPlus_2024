package Tasks;

import com.KesifPlus.database.DatabaseUtilities;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;

import java.sql.*;

public class Task8 {


    //    local sqlite database imizde icerisinde firstName,lastName,grade ve age olan Student table olusturalim
//    default olarak 30 tane ogrenci ekleyelim
//    sonra random olarak bir ogrenci ekleyelim
//    eklemis oldugumuz ogrenci database imizde var mi assertion yapalim
//    ogrenci update edelim ve update edilen ogrencinin olduguna eski halinin olmadiginin assertion i yapalim
//    ogrenciyi silelim ve ogrencinin database de olmadiginin assertioni yapalim

    static Statement statement;
    static Faker faker = new Faker();
    static String randomName;
    static String randomLastName;
    static int grade = 7;
    static int age = 14;
    ResultSet resultSet;

    @Test
    public void runTask8() {
        try {
            createSqliteTable();
            fillTable();
            addAndUpdateStudentInfos();
            updateStudentAndVerify();
            deleteStudentAndVerify();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //    local sqlite database imizde icerisinde firstName,lastName,grade ve age olan Student table olusturalim
    @Test
    public void createSqliteTable() throws SQLException {
    /* CREATE TABLE students (
            id        INTEGER PRIMARY KEY AUTOINCREMENT,
            firstname TEXT,
            lastname  TEXT,
            grade     INTEGER,
            age       INTEGER)
    */

        DatabaseUtilities.createSQLITEConnection();
        statement = DatabaseUtilities.getConnection().createStatement();
        String createTableSQL = "CREATE TABLE students (" +
                "id        INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "firstname TEXT, " +
                "lastname  TEXT, " +
                "grade     INTEGER, " +
                "age       INTEGER)";

        statement.execute(createTableSQL);
    }

    //    default olarak 30 tane ogrenci ekleyelim
    @Test
    public void fillTable() throws SQLException {
        DatabaseUtilities.createSQLITEConnection();
        statement = DatabaseUtilities.getConnection().createStatement();
        addStudents(30);
    }

    public void addStudents(int number) {
        for (int i = 0; i < number; i++) {
            addStudent();
        }
    }

    public void addStudent() {
        NewStudent student = new NewStudent();
        executeDbQuery(student.getFirstName(), student.getLastName(), student.getGrade(), student.getAge());
    }

    // INSERT INTO 'students' (firstname, lastname, grade, age) VALUES ('Ahmet', 'Altunsoy', 9, 16)
    public void executeDbQuery(String name, String lastname, int grade, int age) {
        String sqlValues;
        sqlValues = "'".concat(name).concat("'").concat(", ")
                .concat("'").concat(lastname).concat("'").concat(", ") +
                grade + ", " +
                age;
        DatabaseUtilities.createSQLITEConnection();
        try {
            int i = statement.executeUpdate("INSERT INTO 'students' (firstname, lastname, grade, age) VALUES (" + sqlValues + ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //    sonra random olarak bir ogrenci ekleyelim
    @Test
    public void addAndUpdateStudentInfos() {
        randomName = faker.name().firstName();
        randomLastName = faker.name().firstName();

        System.out.println("randomName = " + randomName);
        System.out.println("randomLastName = " + randomLastName);

        executeDbQuery(randomName, randomLastName, grade, age);
        verifyStudent(true);  // dosyada mevcut mu?
    }

    public void verifyStudent(boolean isHere) {
        // SELECT * FROM students WHERE firstname = 'Ahmet' AND lastname = 'Altunsoy' AND grade = 7 AND age = 14
        String query = "SELECT * FROM students WHERE " +
                "firstname = '" + randomName + "' AND " +
                "lastname = '" + randomLastName + "' AND " +
                "grade = " + grade + " AND " +
                "age = " + age;

        try {
            resultSet = statement.executeQuery(query);

            if (isHere) {
                Assert.assertTrue(resultSet.next());
            } else {
                Assert.assertFalse(resultSet.next());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStudentAndVerify() {
//        UPDATE students SET firstname = 'Ali', lastname = 'Yılmaz', grade = 7, age = 14
//                      WHERE firstname = 'Ahmet' AND lastname = 'Altunsoy' AND grade = 7 AND age = 14

        String randomName2 = faker.name().firstName();
        String randomLastName2 = faker.name().firstName();
        int grade2 = 6;
        int age2 = 13;
        String updateQuery = "UPDATE students SET firstname = '" + randomName2 + "', lastname = '" + randomLastName2 +
                "', grade = " + grade2 + ", age = " + age2 + " WHERE firstname = '" + randomName + "' AND lastname = '" + randomLastName + "'";

        try {
            int i = statement.executeUpdate(updateQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Güncellenen öğrencinin eski bilgilerle olmadığını kontrol edelim
        verifyStudent(false);   // dosyada mevcut değilmi

        randomName = randomName2;
        randomLastName = randomLastName2;
        grade = grade2;
        age = age2;

        System.out.println("randomName - new = " + randomName);
        System.out.println("randomLastName - new = " + randomLastName);
    }

    //    ogrenciyi silelim ve ogrencinin database de olmadiginin assertioni yapalim
    @Test
    public void deleteStudentAndVerify(){
        // DELETE FROM students WHERE firstname = 'Ahmet' AND lastname = 'Altunsoy' AND grade = 7 AND age = 14
    String deleteQuery = "DELETE FROM students WHERE firstname = '" + randomName +
            "' AND lastname = '" + randomLastName + "' AND " +
            "grade = " + grade + " AND " +
            "age = " + age;

        try {
            statement.executeUpdate(deleteQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        verifyStudent(false);   // dosyada olmadığını verify et
    }

    private static class NewStudent {
        String firstName;
        String lastName;
        int grade;
        int age;

        public String getFirstName() {
            this.firstName = faker.name().firstName();
            return firstName;
        }

        public String getLastName() {
            this.lastName = faker.name().firstName();
            return lastName;
        }

        public int getGrade() {
            this.grade = faker.number().numberBetween(6, 12);
            return grade;
        }

        public int getAge() {
            this.age = grade + 6 + faker.number().numberBetween(-1, 3);
            return age;
        }
    }

}
