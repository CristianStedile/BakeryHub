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
    private String codigo;
    private String nome;
    private int estoque;
    private double preco;
    private int pontos;
    private int promocao;
    
    public Produto(){
    
    }

    public Produto(String codigo, String nome, int estoque, double preco, int pontos) {
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (!codigo.equals("")) {
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
        if(getPromocao() > 0){
            return preco-(promocao*preco);
        }else{
            return preco;
        }
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
        if(promocao > 0){
            this.promocao = promocao;
            System.out.println("Sucesso ao setar promoção!");
        }else{
            System.out.println("Erro ao setar promoção!");
        }
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", codigo=" + codigo + ", nome=" + nome + ", estoque=" + estoque + ", preco=" + preco + ", pontos=" + pontos + ", promocao=" + promocao + '}';
    }

}
