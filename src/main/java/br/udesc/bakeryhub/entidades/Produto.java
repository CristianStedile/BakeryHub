package br.udesc.bakeryhub.entidades;

import java.io.Serializable;
import javax.persistence.Column;
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
    @Column(name = "custo_pontos")
    private int custoPontos;
    private int promocao;
    
    public Produto(){
    
    }

    public Produto(String codigo, String nome, int estoque, double preco, int custoPontos) {
        this.codigo = codigo;
        this.nome = nome;
        this.estoque = estoque;
        this.preco = preco;
        this.custoPontos = custoPontos;
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

    public int getCustoPontos() {
        return custoPontos;
    }

    public void setCustoPontos(int custoPontos) {
        if (custoPontos >= 0) {
            this.custoPontos = custoPontos;
            System.out.println("Sucesso ao setar custo de pontos do produto!");
        } else {
            System.out.println("Erro ao setar custo de pontos do produto!");
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
        if (estoque >= 0) {
            this.estoque = estoque;
            System.out.println("Sucesso ao setar produto!");
        } else {
            System.out.println("Erro ao setar produto!");
        }
    }

    public double getPreco() {
        if(getPromocao() > 0){
            double promocao = this.promocao;
            return preco-((promocao/100)*preco);
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
        return "Produto{" + "id=" + id + ", codigo=" + codigo + ", nome=" + nome + ", estoque=" + estoque + ", preco=" + preco + ", custoPontos=" + custoPontos + ", promocao=" + promocao + '}';
    }

   
}
