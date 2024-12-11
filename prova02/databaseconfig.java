package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class databaseconfig {
    private static final Logger logger = Logger.getLogger(databaseconfig.class.getName());

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/receitas_db?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "2597";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            logger.info("Conexão estabelecida com o banco de dados.");

            UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
            ReceitaDAO receitaDAO = new ReceitaDAO(connection);

            // Criar usuário
            Usuario usuario = new Usuario("MMS", "2211", "Mariana", "Magalhães", "mariana@gmail.com");
            usuarioDAO.criarUsuario(usuario);

            // Autenticar
            if (usuarioDAO.autenticar("MMS", "2211")) {
                logger.info("Usuário autenticado com sucesso.");
            }

            // Criar receita
            Receita receita = new Receita("Bolo de fubá", "Farinha, Ovo, fubá, leite, oleo", "Misturar, untar a forma e assar", "MMS");
            receitaDAO.criarReceita(receita);

            // Listar receitas
            List<Receita> receitas = receitaDAO.listarReceitasPorUsuario("MMS");
            receitas.forEach(r -> logger.info("Receita encontrada: " + r.getNome()));

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro na conexão com o banco de dados.", e);
        }
    }
}