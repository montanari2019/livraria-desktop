package Controler;

import DAO.Livros_DAO;
import Model.Editora;
import Model.Livros;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.net.URL;



import java.time.LocalDate;
import java.util.ResourceBundle;

public class LivroFormularioController implements Initializable {

   @FXML private TextField titulo02;
   @FXML private DatePicker data_lancamento;
   @FXML private TextField quantidade;
   @FXML private TextField txf_preco;

    @FXML private TableView <Livros> tableViewLivros = new TableView<>();
    @FXML private TableColumn <Livros ,Integer> colunaID = new TableColumn<>("id");
    @FXML private TableColumn <Livros ,String> colunaTITULO = new TableColumn<>("titulo");
    @FXML private TableColumn <Livros ,Integer> colunaQUANTIDADE = new TableColumn<>("quantidade");
    @FXML private TableColumn <Livros ,Float> colunaPRECO = new TableColumn<>("preco");
    @FXML private TableColumn <Livros ,LocalDate> colunaDATA_LANCAMENTO = new TableColumn<>("data lancamento");
    @FXML private TableColumn <Livros ,String> colunaEDITORA = new TableColumn<>("editora");


    @FXML private ComboBox<Editora>  caixa_editora  = new ComboBox<>();
   @FXML private ObservableList observableList_livros;




   private Livros LivroObjetoSelecionado;
   private  Editora EditoraObjetoSlecionado = caixa_editora.getSelectionModel().getSelectedItem();
   private Livros livros = new Livros();
   private Livros_DAO livros_dao = new Livros_DAO();
   private Editora editora = new Editora();



   @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       // TableView_listar();

    }


    public void inserir_livro(){

        livros.setTitulo(titulo02.getText());
        livros.setQauntidade(Integer.parseInt(quantidade.getText()));
        livros.setPreco(Float.parseFloat(txf_preco.getText()));
        livros.setData_lancamento(data_lancamento.getValue());
        livros.setEditora_id(EditoraObjetoSlecionado);
        livros_dao.inserir(livros);
    }

    public void TableView_listar(){

       try {
           colunaID.setCellValueFactory(new PropertyValueFactory<>("id"));
           colunaTITULO.setCellValueFactory(new PropertyValueFactory<>("titulo"));
           colunaDATA_LANCAMENTO.setCellValueFactory(new PropertyValueFactory<>("data_lancamento"));
           colunaPRECO.setCellValueFactory(new PropertyValueFactory<>("preco"));
           colunaEDITORA.setCellValueFactory(new PropertyValueFactory<>("editora"));
           colunaQUANTIDADE.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
           observableList_livros = FXCollections.observableArrayList(livros_dao.listar_todos());
           System.out.println(livros_dao.listar_todos().size());
           tableViewLivros.setItems(observableList_livros);
       }catch (Exception e){
           System.out.println(e);
           throw new RuntimeException(e);
       }

    }




}
