package spring.boot.gerenciamento.usuarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConDataBase {
    private static final String url = "jdbc:mysql://localhost:3306/crep";
    private static final String user = "root";
    private static final String password = "root";
    private static Connection con;

    public static Connection obterConexao() throws SQLException, SQLException {
            return DriverManager.getConnection(url, user, password);
    }
}