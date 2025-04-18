package br.com.crep;

import java.sql.SQLException;

public class UsuariosDAO {
    public void save(Usuarios usuarios) throws Exception {
        String sql = "INSERT INTO USUARIOS (nome, cpf, dia, mes, ano, escolaridade, genero, etnia) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (var connection = ConDataBase.obterConexao();
             var stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, usuarios.nome());
            stmt.setString(2, usuarios.cpf());
            stmt.setInt(3, usuarios.dia());
            stmt.setInt(4, usuarios.mes());
            stmt.setInt(5, usuarios.ano());
            stmt.setString(6, usuarios.escolaridade());
            stmt.setString(7, usuarios.genero());
            stmt.setString(8, usuarios.etnia());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
}
    /*

    public List<DadosPessoais> pesquisar() throws Exception {
        String sql = "SELECT * FROM USUARIOS";
        List<DadosPessoais> dados = new ArrayList<>();

        try (Connection con = conDataBase.obterConexao();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                DadosPessoais dp = new DadosPessoais(
                        rs.getLong("idusuarios"),
                        rs.getString("nome"),
                        rs.getString("cpf")
                );
                dados.add(dp);
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return dados;
    }
} -> class usuarios

*/
