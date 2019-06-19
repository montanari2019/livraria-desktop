package DAO;

import Model.Livros;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Livros_DAO {
    private Connection conexao;

    public Livros_DAO(Connection conexao) {

        conexao = new ConnectionFactory().getConnection();
    }

    public Livros_DAO() {

    }

    public void conectar(){
        conexao = new ConnectionFactory().getConnection();
    }

    public void inserir (Livros livros){
        conectar();
        String sql = "insert into livros (titulo, data_lancamento, quantidade, preco, editoras_id)" +
                     "values (?,?,?,?,?)";
        try{
            //PREPARANDO CONEXÃO
            PreparedStatement stmt =  conexao.prepareStatement(sql);

            stmt.setString(1, livros.getTitulo());
            stmt.setDate(2, Date.valueOf(livros.getData_lancamento()));
            stmt.setInt(3, livros.getQuantidade());
            stmt.setFloat(4, livros.getPreco());
            stmt.setInt(5, livros.getEditora_id());

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
        conectar();
        String sql = "select * from livros";
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
                livros.setData_lancamento(LocalDate.parse(resultado.getDate("data_lancamento").toString()));
                livros.setPreco(resultado.getFloat("preco"));
                livros.setQuantidade(resultado.getInt("quantidade"));
                livros.setEditora_id(resultado.getInt("editoras_id"));

                Livros.add(livros);

            }

            //FECHAR CONEXÃO
            conexao.close();;

        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException();
        }

        return Livros;
    }

    public Livros buscar_id(int id){
        conectar();
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
            livros.setQuantidade(resultado.getInt("quantidade"));
            livros.setEditora_id(resultado.getInt("editoras_id"));

            //FECHANOD CONEXÃO
            conexao.close();
            return livros;

        }catch (SQLException e){
            System.out.println(e);
            throw  new RuntimeException(e);
        }
    }

    public void alterar(Livros livros){
        conectar();
        String sql = "update livros set titulo = ?, data_lancamento = ?, quantidade = ?, preco = ?, editora_id = ? where id = ?";
        try{
            //PREPARANDO CONEXÃO
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, livros.getTitulo());
            stmt.setDate(2, Date.valueOf(livros.getData_lancamento()));
            stmt.setInt(3,livros.getQuantidade());
            stmt.setFloat(4,livros.getPreco());
            stmt.setInt(5,livros.getEditora_id());
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
        conectar();
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
