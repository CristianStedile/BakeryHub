package br.udesc.bakeryhub.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int codigo;
    private String nome;
    private int estoque;
    private double preco;
    private int pontos;
    private int promocao;

    public Produto(int id, int codigo, String nome, int estoque, double preco, int pontos) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.estoque = estoque;
        this.preco = preco;
        this.pontos = pontos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
            System.out.println("Sucesso ao setar id do produto!");
        } else {
            System.out.println("Erro ao setar id do produto!");
        }
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        if (codigo > 0) {
            this.codigo = codigo;
            System.out.println("Sucesso ao setar codigo do produto!");
        } else {
            System.out.println("Erro ao setar codigo do produto!");
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (!nome.equals("")) {
            this.nome = nome;
            System.out.println("Sucesso ao setar nome do produto!");
        } else {
            System.out.println("Erro ao setar nome do produto!");
        }
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        if (estoque > 0) {
            this.estoque = estoque;
            System.out.println("Sucesso ao setar produto!");
        } else {
            System.out.println("Erro ao setar produto!");
        }
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco > 0) {
            this.preco = preco;
            System.out.println("Sucesso ao setar preço do produto!");
        } else {
            System.out.println("Erro ao setar preço do produto!");
        }
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        if (pontos > 0) {
            this.pontos = pontos;
            System.out.println("Sucesso ao setar pontos do produto!");
        } else {
            System.out.println("Erro ao setar pontos do produto!");
        }
    }

    public int getPromocao() {
        return promocao;
    }

    public void setPromocao(int promocao) {
        this.promocao = promocao;
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", codigo=" + codigo + ", nome=" + nome + ", estoque=" + estoque + ", preco=" + preco + ", pontos=" + pontos + ", promocao=" + promocao + '}';
    }

}
