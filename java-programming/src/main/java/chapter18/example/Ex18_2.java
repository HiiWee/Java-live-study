package chapter18.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex18_2 {
    private static final String URL = "jdbc:mysql://localhost:3306/java2-jdbc";
    private static final String USER = "hoseok";
    private static final String PASSWORD = "1234";
    private static final String QUERY = "select * from student";

    public static void main(String[] args) {
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("DB 연결 완료");

            statement = connection.createStatement();
            resultSet = statement.executeQuery(QUERY);

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String dept = resultSet.getString("dept");
                int score = resultSet.getInt("score");
                int grade = resultSet.getInt("grade");

                System.out.println(id + " " + name + " " + dept + " " + score + " " + grade);
            }
        } catch (SQLException e) {
            System.out.println("DB 연결 오류");
            throw new RuntimeException(e);
        }

    }
}
