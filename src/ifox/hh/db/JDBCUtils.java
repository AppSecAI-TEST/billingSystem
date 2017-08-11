package ifox.hh.db;

import java.sql.*;

/**
 * Created by 41988 on 2017/8/9.
 */
public class JDBCUtils {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql:///billingsystem";
    private static final String ROOT = "root";
    private static final String PASS = "748596";
    private static Connection connection =null;

    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL,ROOT,PASS);
            connection.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(PreparedStatement preparedStatement) throws SQLException {
        if(connection != null) {
            connection.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }

    public static void close(ResultSet resultSet, PreparedStatement preparedStatement) throws SQLException {
        if(connection != null) {
            connection.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (resultSet != null) {
            resultSet.close();
        }
    }
}
