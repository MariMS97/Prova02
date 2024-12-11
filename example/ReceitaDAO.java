package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class ReceitaDAO {
    private Connection connection;
    private static final Logger logger = Logger.getLogger(ReceitaDAO.class.getName());

    public ReceitaDAO(Connection connection) {
        this.connection = connection;
    }

    public void criarReceita(Receita receita) {
        String sql = "INSERT INTO receitas (nome, ingredientes, modo_preparo, usuario_iniciais) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, receita.getNome());
            stmt.setString(2, receita.getIngredientes());
            stmt.setString(3, receita.getModoPreparo());
            stmt.setString(4, receita.getUsuarioIniciais());
            stmt.executeUpdate();
            logger.info("Receita criada: " + receita.getNome());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao criar receita: " + receita.getNome(), e);
        }
    }

    public List<Receita> listarReceitasPorUsuario(String usuarioIniciais) {
        String sql = "SELECT * FROM receitas WHERE usuario_iniciais = ?";
        List<Receita> receitas = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuarioIniciais);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    receitas.add(new Receita(
                            rs.getString("nome"),
                            rs.getString("ingredientes"),
                            rs.getString("modo_preparo"),
                            rs.getString("usuario_iniciais")
                    ));
                }
                logger.info("Receitas listadas para o usuário: " + usuarioIniciais);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao listar receitas do usuário: " + usuarioIniciais, e);
        }
        return receitas;
    }
}
