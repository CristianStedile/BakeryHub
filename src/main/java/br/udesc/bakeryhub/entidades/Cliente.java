package br.udesc.bakeryhub.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String cpf;
    private String endereco;
    private int pontos;

    public Cliente() {

    }

    public Cliente(String nome, String cpf, String endereco, int pontos) {
        this.nome = nome;
        this.endereco = endereco;
        this.cpf = cpf;
        this.pontos = pontos;
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
            System.out.println("Sucesso ao setar nome do cliente!");
        } else {
            System.out.println("Erro ao setar nome do cliente!");
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (!cpf.equals("")) {
            this.cpf = cpf;
            System.out.println("Sucesso ao setar cpf do cliente!");
        } else {
            System.out.println("Erro ao setar cpf do cliente!");
        }      
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        if (!endereco.equals("")) {
            this.endereco = endereco;
            System.out.println("Sucesso ao setar endereço do cliente!");
        } else {
            System.out.println("Erro ao setar endereço do cliente!");
        }  
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        if (pontos >= 0) {
            this.pontos = pontos;
            System.out.println("Sucesso ao setar pontos do cliente!");
        } else {
            System.out.println("Erro ao setar pontos do cliente");
        }
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", endereco=" + endereco + ", pontos=" + pontos + '}';
    }

}
