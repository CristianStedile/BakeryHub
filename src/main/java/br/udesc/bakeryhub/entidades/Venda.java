package br.udesc.bakeryhub.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "venda")
public class Venda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String data;
    private String formaPagamento;
    private boolean pago;
    @ManyToMany
    @JoinColumn(name = "id_produto")
    private Produto produto;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    public Venda(int id, String data, String formaPagamento, boolean pago) {
        this.id = id;
        this.data = data;
        this.formaPagamento = formaPagamento;
        this.pago = pago;
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
        return "Venda{" + "id=" + id + ", data=" + data + ", formaPagamento=" + formaPagamento + ", pago=" + pago + ", produto=" + produto + ", cliente=" + cliente + ", funcionario=" + funcionario + '}';
    }

}
