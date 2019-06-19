package DAO;

import Model.Municipio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Municipio_DAO {
    private Connection conexao;

    public void conectar() {
        conexao = new ConnectionFactory() .getConnection();
    }


    public void inserir(Municipio municipio){
        conectar();
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
        conectar();
        String sql = "select * from Municipio where id = ?";
        try{
            //PREPARANDO A CONEXÃO
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, id);

            //EXECUTANDO COMANDO
            Municipio municipio = new Municipio();
            ResultSet resul = stmt.executeQuery();

            //POPULANDO OBJETO
            resul.next();
            municipio.setId(resul.getInt("id"));
            municipio.setNome(resul.getString("nome"));
            municipio.setEstado(new Estado_DAO().buscar_id(resul.getInt("id_estado")));
            //FECHANOD CONEXÃO
            conexao.close();
            return municipio;

        }catch (SQLException e){
            System.out.println(e);
            throw  new RuntimeException(e);
        }
    }

    public void alterar(Municipio municipio){
        conectar();
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
        conectar();
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

