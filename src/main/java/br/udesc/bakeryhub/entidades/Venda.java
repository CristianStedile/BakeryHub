package br.udesc.bakeryhub.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "venda")
public class Venda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String data;
    private double valor;
    @Column(name = "forma_pagamento")
    private String formaPagamento;
    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemVenda> itens = new ArrayList();
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    public Venda() {

    }

    public Venda(String data, String formaPagamento, double valor, Cliente cliente, Funcionario funcionario) {
        this.data = data;
        this.formaPagamento = formaPagamento;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void addItem(ItemVenda iv) {
        itens.add(iv);
    }

    public void removerItem(ItemVenda iv) {
        itens.remove(iv);
    }

    public double getTotal() {
        double total = 0;
        for (ItemVenda it : itens) {
            total += it.getPrecoUnitario() * it.getQuantidade();
        }
        return total;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
            System.out.println("Sucesso ao setar id da venda!");
        } else {
            System.out.println("Erro ao setar id da venda!");
        }
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        if (data.length() == 10) {
            this.data = data;
            System.out.println("Sucesso ao setar data da venda!");
        } else {
            System.out.println("Erro ao setar data da venda!");
        }
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        if (formaPagamento.equals("Cartão") || formaPagamento.equals("Pix") || formaPagamento.equals("Dinheiro")) {
            this.formaPagamento = formaPagamento;
            System.out.println("Sucesso ao setar forma de pagamento da venda!");
        } else {
            System.out.println("Erro ao setar forma de pagamento da venda!");
        }
    }

    @Override
    public String toString() {
        return "Venda{" + "id=" + id + ", data=" + data + ", valor=" + valor + ", formaPagamento=" + formaPagamento + ", itens=" + itens + ", cliente=" + cliente + ", funcionario=" + funcionario + '}';
    }

}
