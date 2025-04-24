package spring.boot.gerenciamento.usuarios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Usuarios> buscarTodosUsuarios() throws Exception {
        var sql = "SELECT * FROM usuarios";
        
        List<Usuarios> listaUsuarios = new ArrayList<>();
    
        try (var connection = ConDataBase.obterConexao();
                 var stmt = connection.prepareStatement(sql)) {
    
                    try (ResultSet rs = stmt.executeQuery()){
                        while (rs.next()) {
                            Usuarios usuarios = new Usuarios(rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getInt("dia"),
                            rs.getInt("mes"),
                            rs.getInt("ano"),
                            rs.getString("escolaridade"),
                            rs.getString("genero"),
                            rs.getString("etnia"));
    
                            listaUsuarios.add(usuarios);
                        }
                    }
    
            } catch (SQLException e) {
                throw new Exception(e);
            }
            return listaUsuarios;
    }
}