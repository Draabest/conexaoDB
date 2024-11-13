package br.com.aula.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AtualizarDados {

    public static void main(String[] args) {
        // Estabelecendo conexão com o banco de dados
        try (Connection conexao = ConexaoDB.conectar()) {
            if (conexao != null) {
                // Atualizando registros na tabela 'alunos'
                atualizarAluno(conexao, 1, "João Atualizado", 21);
                System.out.println("Dados atualizados com sucesso!");
            } else {
                System.err.println("Falha ao conectar ao banco de dados.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar dados: " + e.getMessage());
        }
    }

    /**
     * Método para atualizar o nome e a idade de um aluno pelo ID.
     * @param conexao - Objeto de conexão com o banco de dados.
     * @param id - ID do aluno a ser atualizado.
     * @param nome - Novo nome do aluno.
     * @param idade - Nova idade do aluno.
     * @throws SQLException - Lança uma exceção em caso de erro na atualização.
     */
    static void atualizarAluno(Connection conexao, int id, String nome, int idade) throws SQLException {
        // Comando SQL para atualizar um registro na tabela 'alunos'
        String sql = "UPDATE alunos SET nome = ?, idade = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome); // Define o novo nome do aluno
            stmt.setInt(2, idade);   // Define a nova idade do aluno
            stmt.setInt(3, id);      // Define o ID do aluno a ser atualizado
            stmt.executeUpdate();    // Executa a atualização no banco de dados
        }
    }
}
