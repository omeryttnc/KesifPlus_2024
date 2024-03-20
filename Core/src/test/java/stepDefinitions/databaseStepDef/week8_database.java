package stepDefinitions.databaseStepDef;

import com.KesifPlus.utility.ConfigurationReader;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class week8_database {
    // connection olusturacagiz
    // statement
    // query gonderecegiz

    /*
    Read   -> SELECT email,id FROM tableName                                                        -> executeQuery()

    Create -> INSERT INTO tableName (column1,column2) VALUES (column1degeri,column2degeri)          -> executeUpdate()
    Update -> UPDATE tableName SET columnName=yeniDeger                                             -> executeUpdate()
    Delete -> DELETE FROM tableName                                                                 -> executeUpdate()


    WHERE       -> condition key wordumuz                                 -> DELETE FROM tableName WHERE email='omer@gmail.com'
    LIMIT       -> gelecek olan query i sinirlandirmak icin kullaniriz    -> SELECT * FROM tableName LIMIT 10
    ORDER BY    -> siralamak icin kullaniyoruz                            -> SELECT * FROM tableName ORDER BY id Desc LIMIT 1

     */

    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public void createMYSQLConnection() throws SQLException {
        connection = DriverManager.getConnection(
                ConfigurationReader.getProperty("url"),
                ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password")
        );
    }

    public void createSQLITEConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/test/resources/SqliteDatabase.db");
    }

    public void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();
        }

        if (statement != null) {
            statement.close();
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (resultSet != null) {
            resultSet.close();
        }
    }

    @Test
    public void insertStatement_1() throws SQLException {
        createSQLITEConnection();
        statement = connection.createStatement();

        int i = statement.executeUpdate("INSERT INTO `promocode` (promocodename,howLong,howMany) VALUES ('promo3',20,3) ");
        System.out.println("i = " + i);
        tearDown();
    }

    @Test
    public void insertStatement_2() throws SQLException {
        createSQLITEConnection();
        statement = connection.createStatement();
        String promoCodeName = "omer";
        int howLong = 23;
        int howMany = 30;
        int i = statement.executeUpdate("INSERT INTO `promocode` (promocodename,howLong,howMany) VALUES ('"+promoCodeName+"',"+howLong+","+howMany+") ");
        System.out.println("i = " + i);
        tearDown();
    }

    @Test
    public void insertPreparedStatement() throws SQLException {
        createSQLITEConnection();
        preparedStatement = connection.prepareStatement("INSERT INTO `promocode` (promocodename,howLong,howMany) VALUES (?,?,?) ");

        String promoCodeName = "omer";
        int howLong = 23;
        int howMany = 30;

        preparedStatement.setString(1,promoCodeName);
        preparedStatement.setInt(2,howLong);
        preparedStatement.setInt(3,howMany);

        preparedStatement.executeUpdate();
        tearDown();
    }

    @Test
    public void updateStatement() throws SQLException {
        createSQLITEConnection();
        statement = connection.createStatement();

        int i = statement.executeUpdate("UPDATE promocode SET howlong = 40 ");
        System.out.println("i = " + i);
        tearDown();
    }

    @Test
    public void updatePreparedStatement() throws SQLException {
        createSQLITEConnection();
        preparedStatement = connection.prepareStatement("UPDATE promocode SET howlong = ? where id = ?");

        preparedStatement.setInt(1,100);
        preparedStatement.setInt(2,1);

        preparedStatement.executeUpdate();
        tearDown();
    }
    @Test
    public void updatePreparedStatement_hataliKullanim() throws SQLException {
        createSQLITEConnection();
        preparedStatement = connection.prepareStatement("UPDATE promocode SET howlong = ? where ? = ?");
        // column name oldugu yere ? koyamayiz sadece
        // sadece value kisimlarina ? koyabiliriz
        preparedStatement.setInt(1,1000);
        preparedStatement.setString(2,"id");
        preparedStatement.setInt(3,1);

        int i = preparedStatement.executeUpdate();
        System.out.println("i = " + i);
        tearDown();
    }

    @Test
    public void deleteStatement() throws SQLException {
        createSQLITEConnection();
        statement = connection.createStatement();

        int i = statement.executeUpdate("delete from promocode where id = 3");
        System.out.println("i = " + i);
        tearDown();
    }

    @Test
    public void readStatement_1() throws SQLException {
        createSQLITEConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery("select * from promocode");

        while (resultSet.next()) {
            System.out.println("resultSet.getInt(\"id\") = " + resultSet.getInt("id"));
            System.out.println("resultSet.getInt(1) = " + resultSet.getInt(1));
            System.out.println(resultSet.getString(2));
            System.out.println("resultSet.getInt(3) = " + resultSet.getInt(3));
            System.out.println("resultSet.getInt(\"howMany\") = " + resultSet.getInt("howMany"));
            System.out.println("****************************");
        }
        tearDown();
    }

    @Test
    public void readStatement_2() throws SQLException {
        createSQLITEConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery("select * from promocode");

        List<PromocodeSQLITE> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(new PromocodeSQLITE(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4)
            ));
        }

        System.out.println("list = " + list);
        tearDown();
    }

    record PromocodeSQLITE(int id, String promocodeName, int howLong, int howMany) {
    }


}
