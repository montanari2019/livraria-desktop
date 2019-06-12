package DAO;

import Model.Editora;
import Model.Municipio;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Editora_DAO {
    private Connection conexao;

    public Editora_DAO() {
        conexao = new ConnectionFactory().getConnection();
    }

    public void inserir(Editora editora){
        String sql = "insert into editoras (nome, site, bairro, endereco, telefone, id_municipio)" +
                      "values (?, ?, ?, ?, ?, ?)";
        try{
            //PREPARANDO CONEXÃO
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, editora.getNome());
            stmt.setString(2, editora.getSite());
            stmt.setString(3, editora.getBairro());
            stmt.setString(4, editora.getEnredeco());
            stmt.setInt(5, editora.getTelefone());
            stmt.setInt(6, editora.getMunicipio().getId());

            //EXECUTAR O COMANDO
            stmt.execute();

            //FECHANDO CONEXÃO
            conexao.close();

        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException(e);

        }
    }

    public void alterar(Editora editora){
        String sql = "update editora set nome = ?, site = ?, bairro = ?, endereco = ?, telefone = ?, id_municipio = ? where id = ?";
        try{
            //PREPARANDO CONEXÃO
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, editora.getNome());
            stmt.setString(2, editora.getSite());
            stmt.setString(3, editora.getBairro());
            stmt.setString(4, editora.getEnredeco());
            stmt.setInt(5, editora.getTelefone());
            stmt.setInt(6, editora.getMunicipio().getId());

            //EXECUTAR O COMANDO
            stmt.execute();

            //FECHANDO CONEXÃO
            conexao.close();


        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public Editora buscar_id(int id){
        String sql = "select * from autores where id = ?";

        try{
            //PREPARAR A CONEXÃO
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            // EXECUTAR
            Editora editora1 = new Editora();
            Municipio municipio = new Municipio();
            ResultSet resultado = stmt.executeQuery();

            //POPULANOD OBJETO AUTOR
            resultado.next();
            editora1.setId(resultado.getInt("id"));
            editora1.setNome(resultado.getString("nome"));
            editora1.setSite(resultado.getString("site"));
            editora1.setBairro(resultado.getString("bairro"));
            editora1.setEnredeco(resultado.getString("endereco"));
            editora1.setTelefone(resultado.getInt("telefone"));
            editora1.setMunicipio(new Municipio_DAO().buscar_id(resultado.getInt("id_municipio")));

            resultado.close();

            //FECHANDO CONEXÃO
            conexao.close();
            return  editora1;

        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException();
        }

    }

    public List<Editora> listar_todos(){
        String sql = "select *from editoras" +
                     "order by nome";
        List<Editora> editoras = new ArrayList<>();
        try {
            //PREPARAR A CONEXÂO
            PreparedStatement stmt = conexao.prepareStatement(sql);

            //EXECUTAR
            ResultSet resultado  = stmt.executeQuery();

            //PERCORRER OS RESULTADOR
            while (resultado.next()){
                Editora editora1 = new Editora();
                editora1.setId(resultado.getInt("id"));
                editora1.setNome(resultado.getString("nome"));
                editora1.setSite(resultado.getString("site"));
                editora1.setBairro(resultado.getString("bairro"));
                editora1.setEnredeco(resultado.getString("endereco"));
                editora1.setTelefone(resultado.getInt("telefone"));
                editora1.setMunicipio(new Municipio_DAO().buscar_id(resultado.getInt("id_municipio")));

                editoras.add(editora1);
            }

            //FECHAR CONEXÃO
            conexao.close();;

        }catch (SQLException e){
            throw new RuntimeException();
        }

        return editoras;
    }

    public void deletar(int id){
        String sql = "delete from editoras where id = ?";
        try{
            //PREPARANDO A CONEXÃO
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            //EXECUTANDO
            stmt.execute();

            //FECHANDO A CONEXÃO
            conexao.close();


        }catch (SQLException e){
            System.out.println(e);
            throw new  RuntimeException(e);
        }
    }
}

