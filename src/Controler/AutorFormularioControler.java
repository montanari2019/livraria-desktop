package Controler;

import DAO.AutorDAO;
import Model.Autor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AutorFormularioControler {


    @FXML private TextField txfNome;
    @FXML private  TextField txfEmail;
    @FXML private Button btnSalvar;
    public void salvar(){
        Autor aut = new Autor();
        AutorDAO autorDAO = new AutorDAO();
        System.out.printf("Nome: " + txfNome.getText());
        System.out.printf("\nEmail: " + txfEmail.getText());

        aut.setNome(txfNome.getText());
        aut.setEmail(txfEmail.getText());

        autorDAO.inserir(aut);
        limpar_campos();



    }
    public void limpar_campos(){
        txfNome.setText("");
        txfEmail.setText("");
        txfNome.requestFocus();
    }




}
