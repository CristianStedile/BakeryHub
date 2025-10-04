package br.udesc.bakeryhub.entidades;

import java.io.Serializable;
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
    private String senha;
    private String login;
    private String tipo;
    private String codigoRecuperação;

    public Funcionario(int id, String senha, String login, String tipo, String codigoRecuperação) {
        this.id = id;
        this.senha = senha;
        this.login = login;
        this.tipo = tipo;
        this.codigoRecuperação = codigoRecuperação;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCodigoRecuperação() {
        return codigoRecuperação;
    }

    public void setCodigoRecuperação(String codigoRecuperação) {
        this.codigoRecuperação = codigoRecuperação;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "id=" + id + ", senha=" + senha + ", login=" + login + ", tipo=" + tipo + ", codigoRecupera\u00e7\u00e3o=" + codigoRecuperação + '}';
    }

}
