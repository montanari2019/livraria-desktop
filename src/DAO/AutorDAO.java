package DAO;

import Model.Autor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AutorDAO {
 private Connection conexao;


 public AutorDAO() {
  conexao = new ConnectionFactory().getConnection();
 }


 public void inserir(Autor autor){
  String sql = "insert into autores (nome, email)" +
          "values (?, ?)";
  try{
   //PREPARAR A CONEXÃO
   PreparedStatement stmt = conexao.prepareStatement(sql);
   stmt.setString(1, autor.getNome());
   stmt.setString(2, autor.getEmail());

   //EXECUTANDO O COMANDO
   stmt.execute();

   //FECHAR CONEXÃO
   conexao.close();
  }catch(SQLException e){
   throw new RuntimeException(e);
  }
 }
 public List<Autor> listar_todos(){
  String sql = "select * from autores";
  List<Autor> autores = new ArrayList<>();
  try {
   //PREPARAR A CONEXÂO
   PreparedStatement stmt = conexao.prepareStatement(sql);

   //EXECUTAR
   ResultSet resultador  = stmt.executeQuery();

   //PERCORRER OS RESULTADOR
   while (resultador.next()){
    Autor autorr1 = new Autor();
    autorr1.setId(resultador.getInt("id"));
    autorr1.setNome(resultador.getString("nome"));
    autorr1.setEmail(resultador.getString("email"));

    autores.add(autorr1);
   }

   //FECHAR CONEXÃO
   conexao.close();;

  }catch (SQLException e){
   throw new RuntimeException();
  }

  return autores;
 }
 public Autor buscar_id(int id){
  String sql = "select * from autores where id = ?";

  try{
   //PREPARAR A CONEXÃO
   PreparedStatement stmt = conexao.prepareStatement(sql);
   stmt.setInt(1, id);

   // EXECUTAR
   Autor autor = new Autor();
   ResultSet resultado = stmt.executeQuery();

   //POPULANOD OBJETO AUTOR
   resultado.next();
   autor.setId(resultado.getInt("id"));
   autor.setNome(resultado.getString("nome"));
   autor.setEmail(resultado.getString("email"));
   resultado.close();

   //FECHANDO CONEXÃO
   conexao.close();
   return  autor;

  }catch (SQLException e){
   System.out.println(e);
   throw new RuntimeException();
  }

 }
 public void alterar (Autor autor){
  String sql = "update autores set nome = ?, email = ? where id = ?";
  try{
   //PREPARANDO CONEXÃO
   PreparedStatement stmt = conexao.prepareStatement(sql);
   stmt.setString(1, autor.getNome());
   stmt.setString(2, autor.getEmail());
   stmt.setInt(3, autor.getId());

   // EXECUTAR
   stmt.execute();

   //FECHAR CONEXÃO
   conexao.close();


  }catch (SQLException e){
   System.out.println(e);
   throw  new RuntimeException();
  }
 }
 public void deletar (int id){
  String sql = "delete from autores where id = ?";
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
