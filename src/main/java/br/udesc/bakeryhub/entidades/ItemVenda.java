package br.udesc.bakeryhub.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item_venda")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id;

    @ManyToOne
    @JoinColumn(name = "id_venda")
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    private int quantidade;
    @Column(name = "preco_unitario")
    private double precoUnitario;

    public ItemVenda() {
        
    }

    public ItemVenda(Produto produto, int quantidade, double precoUnitario) {
        this.venda = null;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public int getId() {
        return id;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        if (venda != null) {
            this.venda = venda;
            System.out.println("Sucesso ao setar venda!");
        } else {
            System.out.println("Erro ao setar venda!");
        } 
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        if (produto != null) {
            this.produto = produto;
            System.out.println("Sucesso ao setar produto!");
        } else {
            System.out.println("Erro ao setar produto!");
        } 
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade > 0) {
            this.quantidade = quantidade;
            System.out.println("Sucesso ao setar quantidade!");
        } else {
            System.out.println("Erro ao setar quantidade!");
        } 
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        if (precoUnitario >= 0) {
            this.precoUnitario = precoUnitario;
            System.out.println("Sucesso ao setar preço!");
        } else {
            System.out.println("Erro ao setar preço!");
        } 
    }

    @Override
    public String toString() {
        return "ItemVenda{" + "id=" + id + ", quantidade=" + quantidade + ", precoUnitario=" + precoUnitario + '}';
    }

}
