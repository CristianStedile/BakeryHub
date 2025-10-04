package br.udesc.bakeryhub.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "venda")
public class Venda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String data;
    private String formaPagamento;
    @OneToMany
    @JoinColumn(name = "id_produto")
    private Produto produto;
    @OneToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @OneToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    public Venda(int id, String data, String formaPagamento) {
        this.id = id;
        this.data = data;
        this.formaPagamento = formaPagamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @Override
    public String toString() {
        return "Venda{" + "id=" + id + ", data=" + data + ", formaPagamento=" + formaPagamento + '}';
    }

}
