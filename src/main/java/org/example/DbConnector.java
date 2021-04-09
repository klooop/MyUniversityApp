package org.example;

import java.sql.*;

public class DbConnector {
    private static Connection connection;
    private static Statement statement;

    public static void connect() throws SQLException {
        try {
            //загружает класс в память
            Class.forName("org.sqlite.JDBC");// использовалась в старых версиях драйверов
            connection = DriverManager.getConnection("jdbc:sqlite:secondProject.db");
            statement = connection.createStatement();
            System.out.println("База подключена");
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Unable to connect");
        }
    }

    public static void disconnect() {
        try {
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
//    public static ResultSet selectQuestions() throws SQLException {
//        return statement.executeQuery("SELECT distinct * FROM simpleQuestions ORDER BY RANDOM() LIMIT 8;" );
//    }
//
//    public static ResultSet selectPicQuestions() throws SQLException {
//        return statement.executeQuery("SELECT distinct * FROM pictureQuestions ORDER BY RANDOM() LIMIT 2;" );
//    }

    public static ResultSet selectEasyLevelQuestions() throws SQLException {
        return statement.executeQuery("SELECT distinct * FROM easyQue ORDER BY RANDOM() LIMIT 8;" );
    }
    public static ResultSet selectMiddleLevelQuestions() throws SQLException {
    return statement.executeQuery("SELECT distinct * FROM middleQue ORDER BY RANDOM() LIMIT 8;" );
    }
    public static ResultSet selectHardLevelQuestions() throws SQLException {
        return statement.executeQuery("SELECT distinct * FROM hardQue ORDER BY RANDOM() LIMIT 8;" );
    }
    public static ResultSet selectEasyLevelQuestionsPic() throws SQLException {
        return statement.executeQuery("SELECT distinct * FROM easyQuePic ORDER BY RANDOM() LIMIT 2;" );
    }
    public static ResultSet selectMiddleLevelQuestionsPic() throws SQLException {
        return statement.executeQuery("SELECT distinct * FROM middleQuePic ORDER BY RANDOM() LIMIT 2;" );
    }
    public static ResultSet selectHardLevelQuestionsPic() throws SQLException {
        return statement.executeQuery("SELECT distinct * FROM hardQuePic ORDER BY RANDOM() LIMIT 2;" );
    }


}

