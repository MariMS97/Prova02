package org.example;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class UsuarioDAO {
    private Connection connection;
    private static final Logger logger = Logger.getLogger(UsuarioDAO.class.getName());

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public void criarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (iniciais, senha, nome, sobrenome, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getIniciais());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getNome());
            stmt.setString(4, usuario.getSobrenome());
            stmt.setString(5, usuario.getEmail());
            stmt.executeUpdate();
            logger.info("Usuário criado com sucesso: " + usuario.getIniciais());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao criar usuário: " + usuario.getIniciais(), e);
        }
    }

    public boolean autenticar(String iniciais, String senha) {
        String sql = "SELECT * FROM usuarios WHERE iniciais = ? AND senha = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, iniciais);
            stmt.setString(2, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                boolean autenticado = rs.next();
                logger.info("Autenticação realizada para: " + iniciais);
                return autenticado;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao autenticar usuário: " + iniciais, e);
            return false;
        }
    }
}
