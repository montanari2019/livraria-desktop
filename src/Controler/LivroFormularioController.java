package Controler;

import DAO.Livros_DAO;
import Model.Livros;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import javax.swing.text.TableView;
import java.awt.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class LivroFormularioController implements Initializable {

   @FXML private TextField txf_titulo;
   @FXML private JFormattedTextField txf_data_lancamento;
   @FXML private TextField txf_qauntidade;
   @FXML private TextField txf_preco;

   @FXML private TableView tableView_livros;
   @FXML private Button btn_cadastrar;
   @FXML private ComboBox caixa_editora;
   @FXML private ObservableList observableList_livros;





   private Livros livros = new Livros();
   private Livros_DAO livros_dao = new Livros_DAO();


    public JFormattedTextField formatar_data(){
        DateFormat format = new SimpleDateFormat ("dd/MM/yyyy");
        txf_data_lancamento = new JFormattedTextField(format);
        return txf_data_lancamento;
    }

    public void  transformar_data_americano{
        DateFormat format =  new SimpleDateFormat("yyyy/MM/dd");
        txf_data_lancamento = new JFormattedTextField(format);
    }

    public void inserir_livro(){

        private Date =  formatar_data();
        livros.setTitulo(txf_titulo.getName());
        livros.setQauntidade(Integer.parseInt(txf_qauntidade.getText()));




    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
