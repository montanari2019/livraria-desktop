package Controler;

import DAO.AutorDAO;
import Model.Autor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;


public class AutorFormularioControler implements Initializable {


    @FXML private TextField txfNome;
    @FXML private  TextField txfEmail;
    @FXML private Button btn_Inserir_novo;
    @FXML private TableView <Autor> tabela_listar_todos = new TableView<>();
    @FXML private TableColumn <Autor, Integer> tabela_id = new TableColumn<>("id");
    @FXML private TableColumn <Autor, String> tabela_nome = new TableColumn<>("nome");
    @FXML private TableColumn <Autor, String> tabela_email = new TableColumn<>("email");
    @FXML private ObservableList <Autor> observableListAutor;
    @FXML private Button btn_deletar_autor;
    @FXML private Button btn_alterar;


    public void inserir_novo_autor(){
        Autor aut = new Autor();
        AutorDAO autorDAO = new AutorDAO();
        System.out.printf("Nome: " + txfNome.getText());
        System.out.printf("\nEmail: " + txfEmail.getText());

        aut.setNome(txfNome.getText());
        aut.setEmail(txfEmail.getText());

        autorDAO.inserir(aut);
        limpar_campos();
        listar_todos();


    }
    public void deletar(){
        Autor autor = tabela_listar_todos.getSelectionModel().getSelectedItem();
        AutorDAO autorDAO = new AutorDAO();
        autorDAO.deletar(autor.getId());
        listar_todos();

    }

    public void listar_todos(){
      try {
          Autor autor = new Autor();
          AutorDAO autorDAO = new AutorDAO();
          tabela_id.setCellValueFactory(new PropertyValueFactory<>("id"));
          tabela_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
          tabela_email.setCellValueFactory(new PropertyValueFactory<>("email"));
          observableListAutor = FXCollections.observableArrayList(autorDAO.listar_todos());
          tabela_listar_todos.setItems(observableListAutor);
      }catch (Exception e){
          System.out.println(e);
          throw new RuntimeException(e);
      }


    }
    public void selecionar_item_alterar_autor(){
        Autor autor = tabela_listar_todos.getSelectionModel().getSelectedItem();
        txfNome.setText(autor.getNome());
        txfEmail.setText(autor.getEmail());
    }


    public void limpar_campos(){
        txfNome.setText("");
        txfEmail.setText("");
        txfNome.requestFocus();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listar_todos();
        tabela_listar_todos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)-> selecionar_Autor(newValue));
    }
    public void selecionar_Autor(Autor autor){
        System.out.println("Autor selecionado : " + autor.getNome());
    }
}
