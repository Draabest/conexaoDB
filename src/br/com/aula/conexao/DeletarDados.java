package br.com.aula.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletarDados {

    public static void main(String[] args) {
        // Estabelecendo conexão com o banco de dados
        try (Connection conexao = ConexaoDB.conectar()) {
            if (conexao != null) {
                // Deletando um aluno pelo ID
                deletarAluno(conexao, 1);
                System.out.println("Aluno deletado com sucesso!");
            } else {
                System.err.println("Falha ao conectar ao banco de dados.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar dados: " + e.getMessage());
        }
    }

    /**
     * Método para deletar um aluno pelo ID.
     * @param conexao - Objeto de conexão com o banco de dados.
     * @param id - ID do aluno a ser deletado.
     * @throws SQLException - Lança uma exceção em caso de erro na exclusão.
     */
    private static void deletarAluno(Connection conexao, int id) throws SQLException {
        // Comando SQL para deletar um registro da tabela 'alunos'
        String sql = "DELETE FROM alunos WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id); // Define o ID do aluno a ser deletado
            stmt.executeUpdate(); // Executa a exclusão no banco de dados
        }
    }
}
