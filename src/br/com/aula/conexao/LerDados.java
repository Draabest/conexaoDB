package br.com.aula.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LerDados {

    public static void main(String[] args) {
        // Estabelecendo conexão com o banco de dados
        try (Connection conexao = ConexaoDB.conectar()) {
            if (conexao != null) {
                // Lendo e exibindo todos os registros da tabela 'alunos'
                lerAlunos(conexao);
            } else {
                System.err.println("Falha ao conectar ao banco de dados.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao ler dados: " + e.getMessage());
        }
    }

    /**
     * Método para ler e exibir todos os registros da tabela 'alunos'.
     * @param conexao - Objeto de conexão com o banco de dados.
     * @throws SQLException - Lança uma exceção em caso de erro na leitura.
     */
    private static void lerAlunos(Connection conexao) throws SQLException {
        // Comando SQL para selecionar todos os registros da tabela 'alunos'
        String sql = "SELECT id, nome, idade FROM alunos";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            // Iterando pelos resultados e exibindo cada registro
            while (rs.next()) {
                int id = rs.getInt("id"); // Obtém o ID do aluno
                String nome = rs.getString("nome"); // Obtém o nome do aluno
                int idade = rs.getInt("idade"); // Obtém a idade do aluno
                // Exibe o registro formatado no console
                System.out.printf("ID: %d | Nome: %s | Idade: %d%n", id, nome, idade);
            }
        }
    }
}
