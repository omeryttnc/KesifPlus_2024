package Tasks;

import com.KesifPlus.database.DatabaseUtilities;
import com.github.javafaker.Faker;
import lombok.Data;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

import java.sql.*;


public class Task8 {

    static Statement statement;
    static String randomName;
    static String randomLastName;
    static int grade = 7;
    static int age = 14;
    static NewStudent student;
    static Faker faker = new Faker();
    ResultSet resultSet;

//    local sqlite database imizde icerisinde firstName,lastName,grade ve age olan Student table olusturalim
//    default olarak 30 tane ogrenci ekleyelim
//    sonra random olarak bir ogrenci ekleyelim
//    eklemis oldugumuz ogrenci database imizde var mi assertion yapalim
//    ogrenci update edelim ve update edilen ogrencinin olduguna eski halinin olmadiginin assertion i yapalim
//    ogrenciyi silelim ve ogrencinin database de olmadiginin assertioni yapalim

//    }

    @Test
    public void runTask8() {
        try {
            createSqliteTable();
            fillTable();
            addAndUpdateStudentInfos();
            deleteStudentAndVerify();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //    local sqlite database imizde icerisinde firstName,lastName,grade ve age olan Student table olusturalim
    @Test
    public void createSqliteTable() throws SQLException {
        // CREATE TABLE students (id INTEGER PRIMARY KEY AUTOINCREMENT,
        //                        firstname TEXT, lastname TEXT, grade INTEGER, age INTEGER)
        DatabaseUtilities.createSQLITEConnection();
        statement = DatabaseUtilities.getConnection().createStatement();
        String createTableSQL = "CREATE TABLE students (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "firstname TEXT, " +
                "lastname TEXT, " +
                "grade INTEGER, " +
                "age INTEGER)";

        try {
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            System.out.println("Tablo mevcut olduğundan yeniden oluşturulmadı");
        }
    }

    //        default olarak 30 tane ogrenci ekleyelim
    @Test
    public void fillTable() {
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

    public void executeDbQuery(String name, String lastName, int grade, int age) {
        // INSERT INTO 'students' (firstname, lastname, grade, age) VALUES ('Ahmet', 'Altunsoy', 9, 16)
        String sqlValues;
        sqlValues = "'".concat(name).concat("'") + ", " +
                "'".concat(lastName).concat("'") + ", " +
                grade + "," +
                age;

        DatabaseUtilities.createSQLITEConnection();
        try {
//            statement = DatabaseUtilities.getConnection().createStatement();
            int j = statement.executeUpdate("INSERT INTO 'students' (firstname, lastname, grade, age) VALUES (" + sqlValues + ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //        1 tane ogrenci ekleyelim ve bilgilerini alalım
    @Test
    public void addAndUpdateStudentInfos() {
        randomName = faker.name().firstName();
        randomLastName = faker.name().lastName();
        executeDbQuery(randomName, randomLastName, grade, age);
        verifyStudent();
        System.out.println("randomName = " + randomName);
        System.out.println("randomLastName = " + randomLastName);

        //    ogrenci update edelim ve update edilen ogrencinin olduguna eski halinin olmadiginin assertion i yapalim
        updateStudentAndVerify();
        System.out.println("randomName = " + randomName);
        System.out.println("randomLastName = " + randomLastName);
    }

    public void verifyStudent() {
        // SELECT * FROM students WHERE firstname = 'Ahmet' AND lastname = 'Altunsoy' AND grade = 7 AND age = 14
//        DatabaseUtilities.createSQLITEConnection();
        try {
//            statement = DatabaseUtilities.getConnection().createStatement();
            String query = "SELECT * FROM students WHERE " +
                    "firstname = '" + randomName +
                    "' AND lastname = '" + randomLastName +
                    "' AND grade = " + grade +
                    " AND age = " + age;
            resultSet = statement.executeQuery(query);
            Assert.assertTrue(resultSet.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStudentAndVerify() {
//        UPDATE students SET firstname = 'Ali', lastname = 'Yılmaz', grade = 7, age = 14
//                      WHERE firstname = 'Ahmet' AND lastname = 'Altunsoy' AND grade = 7 AND age = 14
        String randomName2 = faker.name().firstName();
        String randomLastName2 = faker.name().lastName();
        int grade2 = 6;
        int age2 = 13;
        String updateQuery = "UPDATE students SET firstname = '" + randomName2 + "', lastname = '" + randomLastName2 +
                "', grade = " + grade2 + ", age = " + age2 + " WHERE firstname = '" + randomName + "' AND lastname = '" + randomLastName + "'";

//        DatabaseUtilities.createSQLITEConnection();
        try {
//            statement = DatabaseUtilities.getConnection().createStatement();
            int i = statement.executeUpdate(updateQuery);
            System.out.println("update edilen satır sayısı = " + i);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Güncellenen öğrencinin eski bilgilerle artık veritabanında olmadığını teyit edelim
        String query = "SELECT * FROM students WHERE " +
                "firstname = '" + randomName +
                "' AND lastname = '" + randomLastName +
                "' AND grade = " + grade +
                " AND age = " + age;
        try {
            resultSet = statement.executeQuery(query);
            Assert.assertFalse(resultSet.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        öğrenci bilgilerini güncelleyelim
        randomName = randomName2;
        randomLastName = randomLastName2;
        grade = grade2;
        age = age2;
    }


    //    ogrenciyi silelim ve ogrencinin database de olmadiginin assertioni yapalim
    @Test
    public void deleteStudentAndVerify() {
        // DELETE FROM students WHERE firstname = 'Ahmet' AND lastname = 'Altunsoy' AND grade = 7 AND age = 14
        // Öğrenciyi silelim
        String deleteQuery = "DELETE FROM students WHERE firstname = '" + randomName +
                "' AND lastname = '" + randomLastName + "'";

        try {
//            DatabaseUtilities.createSQLITEConnection();
//            statement = DatabaseUtilities.getConnection().createStatement();
            statement.executeUpdate(deleteQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        // Güncellenen öğrencinin eski bilgilerle artık veritabanında olmadığını teyit edelim
        String query = "SELECT * FROM students WHERE " +
                "firstname = '" + randomName +
                "' AND lastname = '" + randomLastName +
                "' AND grade = " + grade +
                " AND age = " + age;
        try {
            resultSet = statement.executeQuery(query);
            Assert.assertFalse(resultSet.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
            this.lastName = faker.name().lastName();
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
