package Controler;

import DAO.Editora_DAO;
import DAO.Livros_DAO;
import Model.Editora;
import Model.Livros;



import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;

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
    @FXML private TableColumn <Livros ,String> colunaEDITORA = new TableColumn<>("nome");


    @FXML private ComboBox<Editora>  caixa_editora  = new ComboBox<>();
   @FXML private ObservableList <Livros> observableList_livros;




   private Livros LivroObjetoSelecionado;

   private Livros livros = new Livros();
   private Livros_DAO livros_dao = new Livros_DAO();
   private Editora editora = new Editora();
   private ObservableList <Editora> observableListEditora;






   @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      TableView_listar();
      carregar_COMBONBOX();


    }


    public void inserir_livro(){

        livros.setTitulo(titulo02.getText());
        livros.setQuantidade(Integer.parseInt(quantidade.getText()));
        livros.setPreco(Float.parseFloat(txf_preco.getText()));
        livros.setData_lancamento(data_lancamento.getValue());
        livros.setEditora_id(editora.getId());
        System.out.println("Titulo: " + titulo02.getText());
        System.out.println("Data Lancamento: " + data_lancamento.getValue());
        System.out.println("Quantidade: " + quantidade.getText());
        System.out.println("Preço: " + Float.parseFloat(txf_preco.getText()));
        System.out.println("Editora: " + editora.getId());
        limpar_campos();
        livros_dao.inserir(livros);
        TableView_listar();
    }

    public void limpar_campos(){
       titulo02.setText("");
       quantidade.setText("");
       txf_preco.setText("");
       data_lancamento.setValue(null);
       titulo02.requestFocus();
    }

    public void TableView_listar(){
       ;


       try {
           colunaID.setCellValueFactory(new PropertyValueFactory<>("id"));
           colunaTITULO.setCellValueFactory(new PropertyValueFactory<>("titulo"));
           colunaDATA_LANCAMENTO.setCellValueFactory(new PropertyValueFactory<>("data_lancamento"));
           colunaPRECO.setCellValueFactory(new PropertyValueFactory<>("preco"));

           //APRENDA ESSA PORRA SEU RETARDADE
           colunaEDITORA.setCellValueFactory((param) -> new SimpleStringProperty(new Editora_DAO().buscar_id(param.getValue().getEditora_id()).getNome()));
           colunaQUANTIDADE.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
           observableList_livros = FXCollections.observableArrayList(livros_dao.listar_todos());
           System.out.println(livros_dao.listar_todos().size());
           tableViewLivros.setItems(observableList_livros);

           //PEGANDO ITEM SELECIONADO COM O CLICK DO MOUSE
           tableViewLivros.setOnMouseClicked(item_selecionado);
           //PERMITINDO A TABLEA LIVROS SER EDITÁVEL
           tableViewLivros.setEditable(true);

           //CONVERTENDO PARA UM TIPO STRING EDITÁVEL
           colunaTITULO.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getTitulo()));
           colunaPRECO.setCellValueFactory((param) -> (ObservableValue<Float>) new PropertyValueFactory<Livros, Integer>("preco"));
           colunaDATA_LANCAMENTO.setCellValueFactory((param) -> (ObservableValue<LocalDate>) new PropertyValueFactory<Livros, LocalDate>("data_lancamento"));
           colunaQUANTIDADE.setCellValueFactory((param) -> (ObservableValue<Integer>) new PropertyValueFactory<Livros, Integer>("quantidade"));

           //CONVERTENDO NOVAMENTE PARA UMA TABELA
           colunaTITULO.setCellFactory(TextFieldTableCell.forTableColumn());
           colunaPRECO.setCellFactory(TextFieldTableCell.<Livros,Float> forTableColumn(new FloatStringConverter()));
           colunaDATA_LANCAMENTO.setCellFactory(TextFieldTableCell.<Livros, LocalDate> forTableColumn(new LocalDateStringConverter()));
           colunaQUANTIDADE.setCellFactory(TextFieldTableCell.<Livros,Integer> forTableColumn(new IntegerStringConverter()));



       }catch (Exception e){
           System.out.println(e);
           throw new RuntimeException(e);
       }

    }

    public void carregar_COMBONBOX(){
        System.out.println(new Editora_DAO().listar_todos().size());
        caixa_editora.cellFactoryProperty();
        caixa_editora.setCellFactory(format);
        caixa_editora.setOnAction(selectEditora);
        observableListEditora = FXCollections.observableArrayList(new Editora_DAO().listar_todos());
        caixa_editora.setItems(observableListEditora);

    }


   //ALTERAR O NOME COMBONBOX PARA FICAR VISIVEL E ENTENDÍVEL AO USUÁRIO
    EventHandler<ActionEvent> selectEditora = evt ->{
        editora = (Editora) caixa_editora.getSelectionModel().getSelectedItem();
        caixa_editora.setConverter(new StringConverter<Editora>() {
            @Override
            public String toString(Editora editora) {
                return editora.getNome();
            }

            @Override
            public Editora fromString(String s) {
                return null;
            }
        });
    };

   public void deletar(){

   }

   //FUNÇAO QUE SELECIONA UM OBJETO LIVRO COM O CLIK DO MOUSE
    private EventHandler<MouseEvent> item_selecionado = event ->{
        livros = tableViewLivros.getSelectionModel().getSelectedItem();
       System.out.println("Livro selecionado: " + livros.getTitulo());
   };


    //PEGANDO O CONTEUDO DO BANCO QUE ESTA EM EDENREÇO DE MEMORIOA E TRANFORMANDO EM STRING LEGÍVEL
    private Callback<ListView<Editora>,ListCell<Editora>> format = evt-> {
       return new ListCell<>(){
           protected void updateItem(Editora item, boolean empty){
               super.updateItem(item,empty);{
                   if(item==null||empty){
                       setGraphic(null);
                   }else{
                       setText(item.getNome());
                   }
               }
           }
       };
 };



}
