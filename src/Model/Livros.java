package Model;


import java.time.LocalDate;

public class Livros {
    private int id;
    private String titulo;
    private LocalDate data_lancamento;
    private int qauntidade;
    private float preco;
    private Editora editora_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getData_lancamento() {
        return data_lancamento;
    }

    public void setData_lancamento(LocalDate data_lancamento) {
        this.data_lancamento = data_lancamento;
    }

    public int getQauntidade() {
        return qauntidade;
    }

    public void setQauntidade(int qauntidade) {
        this.qauntidade = qauntidade;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public Editora getEditora_id() {
        return editora_id;
    }

    public void setEditora_id(Editora editora_id) {
        this.editora_id = editora_id;
    }

}
