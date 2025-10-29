package br.udesc.bakeryhub.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "despesa")
public class Despesa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String data;
    private String descricao;
    private double valor;

    public Despesa() {

    }

    public Despesa(String nome, String data, String descricao, double valor) {
        this.nome = nome;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
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
        if (!nome.equals("")) {
            this.nome = nome;
            System.out.println("Sucesso ao setar nome da despesa!");
        } else {
            System.out.println("Erro ao setar nome da despesa!");
        }
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        if (!data.equals("")) {
            this.data = data;
            System.out.println("Sucesso ao setar data da despesa!");
        } else {
            System.out.println("Erro ao setar data da despesa!");
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (!descricao.equals("")) {
            this.descricao = descricao;
            System.out.println("Sucesso ao setar descrição da despesa!");
        } else {
            System.out.println("Erro ao setar descrição da despesa!");
        }
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if (valor >= 0) {
            this.valor = valor;
            System.out.println("Sucesso ao setar valor da despesa!");
        } else {
            System.out.println("Erro ao setar valor da despesa!");
        }
    }

    @Override
    public String toString() {
        return "Despesa{" + "id=" + id + ", nome=" + nome + ", data=" + data + ", descricao=" + descricao + ", valor=" + valor + '}';
    }

}
