package DAO;

import Model.Editora;
import Model.Livros;
import com.sun.prism.shader.DrawCircle_LinearGradient_REFLECT_AlphaTest_Loader;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Livros_DAO {
    private Connection conexao;

    public Livros_DAO(Connection conexao) {

        conexao = new ConnectionFactory().getConnection();
    }

    public void inserir (Livros livros){
        String sql = "insert into livros (titulo, data_lancamento, quantidade, preco, editora_id)" +
                     "values (?,?,?,?,?)";
        try{
            //PREPARANDO CONEXÃO
            PreparedStatement stmt =  conexao.prepareStatement(sql);

            stmt.setString(1, livros.getTitulo());
            stmt.setDate(2, Date.valueOf(livros.getData_lancamento()));
            stmt.setInt(3, livros.getQauntidade());
            stmt.setFloat(4, livros.getPreco());
            stmt.setInt(5, livros.getEditora_id().getId());

            //EXECUTANDO
            stmt.execute();

            //FECHANDO CONEXÃO
            conexao.close();

        }catch (SQLException e){
            System.out.println(e);
            throw  new RuntimeException(e);
        }
    }

    public List<Livros> listar_todos(){
        String sql = "select * from autores";
        List<Livros>    Livros = new ArrayList<>();
        try {
            //PREPARAR A CONEXÂO
            PreparedStatement stmt = conexao.prepareStatement(sql);

            //EXECUTAR
            ResultSet resultado  = stmt.executeQuery();

            //PERCORRER OS RESULTADOR
            while (resultado.next()){
                Livros livros = new Livros();
                livros.setId(resultado.getInt("id"));
                livros.setTitulo(resultado.getString("titulo"));
                livros.setData_lancamento(LocalDate.parse(resultado.getDate("data_lacamento").toString()));
                livros.setPreco(resultado.getFloat("preco"));
                livros.setQauntidade(resultado.getInt("Quantidade"));
                livros.setEditora_id(new Editora_DAO().buscar_id(resultado.getInt("editora_id")));

                Livros.add(livros);

            }

            //FECHAR CONEXÃO
            conexao.close();;

        }catch (SQLException e){
            throw new RuntimeException();
        }

        return Livros;
    }

    public Livros buscar_id(int id){
        String sql = "select * from livros where id = ?";
        try{
            //PREPARANDO A CONEXÃO
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, id);

            //EXECUTANDO COMANDO
            Livros livros = new Livros();
            ResultSet resultado = stmt.executeQuery();

            //POPULANDO OBJETO
            resultado.next();
            livros.setId(resultado.getInt("id"));
            livros.setTitulo(resultado.getString("titulo"));
            livros.setData_lancamento(LocalDate.parse(resultado.getDate("data_lacamento").toString()));
            livros.setPreco(resultado.getFloat("preco"));
            livros.setQauntidade(resultado.getInt("Quantidade"));
            livros.setEditora_id(new Editora_DAO().buscar_id(resultado.getInt("editora_id")));

            //FECHANOD CONEXÃO
            conexao.close();
            return livros;

        }catch (SQLException e){
            System.out.println(e);
            throw  new RuntimeException(e);
        }
    }

    public void alterar(Livros livros){
        String sql = "update livros set titulo = ?, data_lancamento = ?, quantidade = ?, preco = ?, editora_id = ? where id = ?";
        try{
            //PREPARANDO CONEXÃO
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, livros.getTitulo());
            stmt.setDate(2, Date.valueOf(livros.getData_lancamento()));
            stmt.setInt(3,livros.getQauntidade());
            stmt.setFloat(4,livros.getPreco());
            stmt.setInt(5,livros.getEditora_id().getId());
            // EXECUTAR
            stmt.execute();

            //FECHAR CONEXÃO
            conexao.close();


        }catch (SQLException e){
            System.out.println(e);
            throw  new RuntimeException();
        }
    }

    public void deletar(int id){
        String sql = "delete from livros where id = ?";
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
