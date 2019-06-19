package DAO;

import Model.Estado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Estado_DAO {
    private Connection conexao;

    public Estado_DAO() {
        conexao = new ConnectionFactory().getConnection();
    }
    public void inserir(Estado estado){
        String sql = "insert into Estado (nome, uf) " +
                     "values (?,?)";
        try{
            //PREPARANDO A CONEXÃO
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, estado.getNome());
            stmt.setString(2, estado.getUf());

            //EXECUTANDO O COMANDO
            stmt.execute();

            //FECHANDO CONEXÃO
            conexao.close();

        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException(e);
        }

    }

    public Estado buscar_id(int id){
        String sql = "select *from Estado where id = ?";
        try{
            //PREPARANDO A CONEXÃO
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, id);

            //EXECUTANDO COMANDO
            Estado estado = new Estado();
            ResultSet resul = stmt.executeQuery();

            //POPULANDO OBJETO
            resul.next();
            estado.setNome(resul.getString("Nome"));
            estado.setUf(resul.getString("uf"));

            //FECHANOD CONEXÃO
            conexao.close();
            return estado;

        }catch (SQLException e){
            System.out.println(e);
            throw  new RuntimeException(e);
        }
    }

    public void alterar(Estado estado){
        String sql = "update Estado set nome = ?, uf = ? where id = ?";
        try{
            //PREPARANDO A CONEXÃO
            PreparedStatement stmt =  conexao.prepareStatement(sql);

            stmt.setString(1, estado.getNome());
            stmt.setString(2, estado.getUf());

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
        String sql = "delete from Estado where id = ?";
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
