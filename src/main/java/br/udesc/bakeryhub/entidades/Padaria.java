package br.udesc.bakeryhub.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "padaria")
public class Padaria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    @OneToMany
    @JoinColumn(name = "id_despesa")
    private Despesa despesa;
    @OneToMany
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;
    @OneToMany
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @OneToMany
    @JoinColumn(name = "id_venda")
    private Venda venda;
    @OneToMany
    @JoinColumn(name = "id_produto")
    private Produto produto;

    public Padaria(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Padaria{" + "id=" + id + ", nome=" + nome + '}';
    }

}
