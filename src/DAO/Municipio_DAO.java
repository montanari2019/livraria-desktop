package DAO;

import Model.Municipio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Municipio_DAO {
    private Connection conexao;

    public Municipio_DAO(Connection conexao) {
        conexao = new ConnectionFactory() .getConnection();
    }

    public Municipio_DAO() {

    }

    public void inserir(Municipio municipio){
        String sql = "insert into Municipio (nome, id_estado" +
                     "values (?, ?)";

        try{
            //PREPARANDO CONEXÃO
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, municipio.getNome());
            stmt.setInt(2, municipio.getEstado().getId());

            //EXECUNTADO
            stmt.execute();

            //FECHANDO A CONEXÃO
            conexao.close();
        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public Municipio buscar_id(int id){
        String sql = "select *from Municipio where id = ?";
        try{
            //PREPARANDO A CONEXÃO
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, id);

            //EXECUTANDO COMANDO
            Municipio municipio = new Municipio();
            ResultSet resul = stmt.executeQuery();

            //POPULANDO OBJETO
            resul.next();
            municipio.setNome(resul.getString("nome"));

            //FECHANOD CONEXÃO
            conexao.close();
            return municipio;

        }catch (SQLException e){
            System.out.println(e);
            throw  new RuntimeException(e);
        }
    }

    public void alterar(Municipio municipio){
        String sql = "update Municipio set nome = ?, where id = ?";
        try{
            //PREPARANDO A CONEXÃO
            PreparedStatement stmt =  conexao.prepareStatement(sql);

            stmt.setString(1, municipio.getNome());


            //EXECUTANDO O COMANDO
            stmt.execute();

            //FECHANDO CONEXÃO
            conexao.close();

        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public void deletar(int id){
        String sql = "delete from Municipio where id = ?";
        try{
            //PREPARANDO CONEXÃO
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, id);

            //EXECUTANDO O COMANDO
            stmt.execute();

            //FECHANDO CONEXÃO
            conexao.close();

        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

}

