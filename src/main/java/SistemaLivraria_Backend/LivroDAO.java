package SistemaLivraria_Backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LivroDAO {

    public LivroDAO() {
        criarTabelaSeNaoExistir();
    }

    private void criarTabelaSeNaoExistir() {
        String sql = "CREATE TABLE IF NOT EXISTS livros (" +
                "idLivros INT AUTO_INCREMENT PRIMARY KEY, " +
                "Titulo VARCHAR(255), " +
                "Autor VARCHAR(255), " +
                "Categoria VARCHAR(100), " +
                "Preco DECIMAL(10,2)" +
                ")";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement()) {
            if (conn != null) {
                stmt.executeUpdate(sql);
                System.out.println("Tabela 'livros' criada ou já existente.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela livros: " + e.getMessage());
        }
    }

    public void cadastrarLivro(
            String titulo,
            String autor,
            String categoria,
            double preco) {

        String sqlCheck = "SELECT COUNT(*) FROM livros WHERE Titulo = ? AND Autor = ?";
        String sqlInsert =
                "INSERT INTO livros (Titulo, Autor, Categoria, Preco) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar()) {
            // Verificar se livro já existe
            try (PreparedStatement stmtCheck = conn.prepareStatement(sqlCheck)) {
                stmtCheck.setString(1, titulo);
                stmtCheck.setString(2, autor);
                ResultSet rs = stmtCheck.executeQuery();
                rs.next();
                int count = rs.getInt(1);
                
                if (count > 0) {
                    System.out.println("Livro já existe: " + titulo);
                    return;
                }
            }

            // Inserir novo livro
            try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {
                stmt.setString(1, titulo);
                stmt.setString(2, autor);
                stmt.setString(3, categoria);
                stmt.setDouble(4, preco);

                stmt.executeUpdate();
                System.out.println("Livro cadastrado: " + titulo);
            }

        } catch (Exception e) {
            System.err.println("Erro ao cadastrar livro: " + e.getMessage());
        }
    }

    public void limparDuplicatas() {
        String sql = "DELETE FROM livros WHERE idLivros NOT IN (" +
                "SELECT * FROM (SELECT MIN(idLivros) FROM livros GROUP BY Titulo, Autor) AS t" +
                ")";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement()) {
            int deletados = stmt.executeUpdate(sql);
            if (deletados > 0) {
                System.out.println("Registros duplicados removidos: " + deletados);
            }
        } catch (Exception e) {
            System.err.println("Erro ao limpar duplicatas: " + e.getMessage());
        }
    }

    public void listarLivros() {
        String sql = "SELECT idLivros, Titulo, Autor, Categoria, Preco FROM livros";

        try (
                Connection conn = Conexao.conectar();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {
            System.out.println("\nLivros no banco de dados:");
            while (rs.next()) {
                System.out.printf("%d - %s | %s | %s | R$ %.2f%n",
                        rs.getInt("idLivros"),
                        rs.getString("Titulo"),
                        rs.getString("Autor"),
                        rs.getString("Categoria"),
                        rs.getDouble("Preco"));
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar livros: " + e.getMessage());
        }
    }
}