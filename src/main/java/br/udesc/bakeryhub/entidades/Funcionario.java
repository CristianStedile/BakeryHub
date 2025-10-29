package br.udesc.bakeryhub.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String cpf;
    private String endereco;
    private String senha;
    private String login;
    private String cargo;
    @Column(name = "codigo_recuperacao")
    private String codigoRecuperacao;

    public Funcionario() {

    }

    public Funcionario(String nome, String cpf, String endereco, String login, String senha, String codigoRecuperacao, String cargo) {
        this.nome = nome;
        this.endereco = endereco;
        this.cpf = cpf;
        this.senha = senha;
        this.login = login;
        this.cargo = cargo;
        this.codigoRecuperacao = codigoRecuperacao;
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
            System.out.println("Sucesso ao setar nome do funcionário!");
        } else {
            System.out.println("Erro ao setar nome do funcionário!");
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (!cpf.equals("")) {
            this.cpf = cpf;
            System.out.println("Sucesso ao setar cpf do funcionário!");
        } else {
            System.out.println("Erro ao setar cpf do funcionário!");
        }
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        if (!endereco.equals("")) {
            this.endereco = endereco;
            System.out.println("Sucesso ao setar endereço do funcionário!");
        } else {
            System.out.println("Erro ao setar endereço do funcionário!");
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (!senha.equals("")) {
            this.senha = senha;
            System.out.println("Sucesso ao setar senha do funcionário!");
        } else {
            System.out.println("Erro ao setar senha do funcionário!");
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (!login.equals("")) {
            this.login = login;
            System.out.println("Sucesso ao setar login do funcionário!");
        } else {
            System.out.println("Erro ao setar login do funcionário!");
        }
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        if (!cargo.equals("")) {
            this.cargo = cargo;
            System.out.println("Sucesso ao setar cargo do cliente!");
        } else {
            System.out.println("Erro ao setar cargo do cliente!");
        }
    }

    public String getCodigoRecuperacao() {
        return codigoRecuperacao;
    }

    public void setCodigoRecuperacao(String codigoRecuperação) {
        if (!codigoRecuperação.equals("")) {
            this.codigoRecuperacao = codigoRecuperação;
            System.out.println("Sucesso ao setar código de recuperação do cliente!");
        } else {
            System.out.println("Erro ao setar código de recuperação do cliente!");
        }
    }

    @Override
    public String toString() {
        return "Funcionario{" + "id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", endereco=" + endereco + ", senha=" + senha + ", login=" + login + ", cargo=" + cargo + ", codigoRecuperacao=" + codigoRecuperacao + '}';
    }

}
