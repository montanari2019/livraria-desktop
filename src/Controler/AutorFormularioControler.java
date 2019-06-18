package Controler;

import DAO.AutorDAO;
import Model.Autor;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;

import javafx.event.EventHandler;
import java.net.URL;
import java.util.*;


public class AutorFormularioControler implements Initializable {


    @FXML private TextField txfNome;
    @FXML private TextField txfEmail;
    @FXML private ObservableList <Autor> observableListAutor;

    @FXML private TableView <Autor> tabelaView = new TableView<>();
    @FXML private TableColumn <Autor, Integer> tabela_id = new TableColumn<>("id");
    @FXML private TableColumn <Autor, String> tabela_nome = new TableColumn<>("nome");
    @FXML private TableColumn <Autor, String> tabela_email = new TableColumn<>("email");


    private Autor autor = new Autor();
    private AutorDAO autorDao = new AutorDAO();
    private Autor ObjetoSelecionado = tabelaView.getSelectionModel().getSelectedItem();




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

        System.out.println("Id selecionado: "+ autor.getId());
        new AutorDAO().deletar(autor.getId());
        listar_todos();

    }

    public void listar_todos(){
      try {
          AutorDAO autorDAO = new AutorDAO();
          tabela_id.setCellValueFactory(new PropertyValueFactory<>("id"));
          tabela_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
          tabela_email.setCellValueFactory(new PropertyValueFactory<>("email"));
          observableListAutor = FXCollections.observableArrayList(autorDAO.listar_todos());
          tabelaView.setItems(observableListAutor);
      }catch (Exception e){
          System.out.println(e);
          throw new RuntimeException(e);
      }


    }



    public void limpar_campos(){
        txfNome.setText("");
        txfEmail.setText("");
        txfNome.requestFocus();
    }








    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listar_todos();

        tabelaView.setOnMouseClicked(item_selecionado);



        tabela_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tabela_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tabelaView.setItems(observableListAutor);

        tabelaView.setEditable(true);


        //CONVERTE PARA UM STRING EDITAVEL
        tabela_nome.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getNome()));
        tabela_email.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getEmail()));


        //CONVERTE NOVAMENTE PARA UMA TABELA
        tabela_nome.setCellFactory(TextFieldTableCell.forTableColumn());
        tabela_email.setCellFactory(TextFieldTableCell.forTableColumn());


    }




    public void selecionar_Autor(Autor autor){
        System.out.println("Autor selecionado : " + autor.getNome());
    }

    //EVENTO DE SELECIONAR O CONTEUDO COM O CLICK DO MOUSE
    EventHandler<MouseEvent> item_selecionado = evt -> {

        //PASSANDO PARA O OBJETO AUTOR PARA SER USADO EM OUTROS METÓDOS
       autor = tabelaView.getSelectionModel().getSelectedItem();
       System.out.println("Autor Selecionado: "+ tabelaView.getSelectionModel().getSelectedItem().getNome());
    };


    public void EDITI_EMAIL(TableColumn.CellEditEvent<Autor, String> autorStringCellEditEvent) {
        autor.setEmail(autorStringCellEditEvent.getNewValue());
        autorDao.alterar(autor);
    }

    public void EDITI_NOME(TableColumn.CellEditEvent<Autor, String> autorStringCellEditEvent) {
        autor.setNome(autorStringCellEditEvent.getNewValue());
        autorDao.alterar(autor);
    }
}
