package SistemaLivraria_Backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {

    private static final String DATABASE = "livraria_db";
    private static final String ROOT_URL =
            "jdbc:mysql://127.0.0.1:3306/?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String URL =
            "jdbc:mysql://127.0.0.1:3306/" + DATABASE + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "123456789";

    public static Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try {
                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexão realizada com sucesso com a base '" + DATABASE + "'.");
                return conn;
            } catch (SQLException e) {
                if (e.getErrorCode() == 1049 || "42000".equals(e.getSQLState())) {
                    criarDatabaseSeNaoExistir();
                    Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                    System.out.println("Conexão realizada com sucesso após criar a base '" + DATABASE + "'.");
                    return conn;
                }
                throw e;
            }

        } catch (SQLException e) {
            System.err.println("Erro na conexão: " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL não encontrado: " + e.getMessage());
            return null;
        }
    }

    private static void criarDatabaseSeNaoExistir() throws SQLException {
        try (Connection rootConn = DriverManager.getConnection(ROOT_URL, USER, PASSWORD);
             Statement stmt = rootConn.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DATABASE +
                    " CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci");
            System.out.println("Database '" + DATABASE + "' criada ou já existente.");
        }
    }
}