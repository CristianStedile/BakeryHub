package br.udesc.bakeryhub.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "venda")
public class Venda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String data;
    @Column(name = "forma_pagamento")
    private String formaPagamento;
    private boolean pago;
    private List<ItemVenda> itens = new ArrayList();
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    public Venda() {

    }

    public Venda(String data, String formaPagamento, boolean pago, Cliente cliente, Funcionario funcionario) {
        this.data = data;
        this.formaPagamento = formaPagamento;
        this.pago = pago;
        this.funcionario = funcionario;
        this.cliente = cliente;
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
    

    public void addItem(Produto p, int quantidade) {
        ItemVenda item = new ItemVenda(this, p, quantidade, p.getPreco());
        itens.add(item);
    }

    public void removerItem(Produto p) {
        for (ItemVenda it : itens) {
            if (it.getProduto().equals(p)) {
                itens.remove(it);
            }
        }
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

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        if (pago == true || pago == false) {
            this.pago = pago;
            System.out.println("Sucesso ao setar status do pagamento da venda!");
        } else {
            System.out.println("Erro ao setar status do pagamento da venda!");
        }
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
        return "Venda{" + "id=" + id + ", data=" + data + ", formaPagamento=" + formaPagamento + ", pago=" + pago + ", itens=" + itens + ", cliente=" + cliente + ", funcionario=" + funcionario + '}';
    }

}
