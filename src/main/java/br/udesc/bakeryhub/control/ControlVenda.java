package br.udesc.bakeryhub.control;

import br.udesc.bakeryhub.dao.DaoCliente;
import br.udesc.bakeryhub.dao.DaoFuncionario;
import br.udesc.bakeryhub.dao.DaoProduto;
import br.udesc.bakeryhub.dao.DaoVenda;
import br.udesc.bakeryhub.entidades.Cliente;
import br.udesc.bakeryhub.entidades.Funcionario;
import br.udesc.bakeryhub.entidades.ItemVenda;
import br.udesc.bakeryhub.entidades.Produto;
import br.udesc.bakeryhub.entidades.Venda;
import br.udesc.bakeryhub.model.ModelProduto;
import br.udesc.bakeryhub.model.ModelVenda;
import br.udesc.bakeryhub.model.ModelVendaProdutos;
import br.udesc.bakeryhub.view.AdicionarProdutoView;
import br.udesc.bakeryhub.view.CadastroVendaView;
import br.udesc.bakeryhub.view.ConsultaVendasView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class ControlVenda {

    private ConsultaVendasView consVendasView;
    private CadastroVendaView cadVendaView;
    private AdicionarProdutoView addProdView;
    private ModelVenda modelVenda;
    private ModelVendaProdutos modelVendaProdutos;
    private ModelProduto modelProduto;
    private DaoVenda daoVenda;
    private DaoProduto daoProduto;
    private DaoCliente daoCliente;
    private DaoFuncionario daoFuncionario;
    private Venda vendaSelecionada;

    public ControlVenda() {
        this.addProdView = new AdicionarProdutoView();
        this.consVendasView = new ConsultaVendasView();
        this.cadVendaView = new CadastroVendaView();
        this.daoVenda = new DaoVenda();
        this.daoProduto = new DaoProduto();
        this.daoFuncionario = new DaoFuncionario();
        this.daoCliente = new DaoCliente();
        this.modelVenda = new ModelVenda();
        this.modelVendaProdutos = new ModelVendaProdutos();
        this.modelProduto = new ModelProduto();
        inicializarComponentes();
    }

    public void inicializarComponentes() {
        consVendasView.tbVendas.setModel(modelVenda);
        cadVendaView.tbProdutos.setModel(modelVendaProdutos);
        addProdView.tbProdutos.setModel(modelProduto);
        cadVendaView.btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrar();
            }
        });
        consVendasView.btEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });
        consVendasView.btRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });
        consVendasView.btFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrar();
            }
        });
        cadVendaView.btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarItem();
            }
        });
        cadVendaView.btRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerItem();
            }
        });
        addProdView.btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionar();
            }
        });
    }

    public void cadastrarVenda() {
        limpar();
        cadVendaView.btAdicionar.setVisible(true);
        cadVendaView.btRemover.setVisible(true);
        this.cadVendaView.setVisible(true);
    }

    public void consultarVendas() {
        carregarVendas();
        this.consVendasView.setVisible(true);
    }

    public void adicionarItem() {
        carregarProdutos();
        this.addProdView.setVisible(true);
    }

    public void removerItem() {
        int linhaSelecionadad = cadVendaView.tbProdutos.getSelectedRow();
        modelVendaProdutos.excluirItens(linhaSelecionadad);
    }

    public void limpar() {
        cadVendaView.tfData.setText("");
        cadVendaView.tfCpfFun.setText("");
        cadVendaView.tfCpfCli.setText("");
        consVendasView.tfAno.setText("");
        consVendasView.tfMes.setText("");
        addProdView.tQtd.setText("");
        modelProduto.limpar();
    }

    public void carregarVendas() {
        modelVenda.limpar();
        for (Venda v : daoVenda.Listar()) {
            modelVenda.inserirVenda(v);
        }
    }

    public void carregarProdutos() {
        modelProduto.limpar();
        for (Produto p : daoProduto.Listar()) {
            modelProduto.inserirProduto(p);
        }
    }

    public void filtrar() {
        if (!consVendasView.tfAno.getText().equals("") && !consVendasView.tfMes.getText().equals("")) {
            modelVenda.limpar();
            int ano = Integer.parseInt(consVendasView.tfAno.getText());
            int mes = Integer.parseInt(consVendasView.tfMes.getText());
            for (Venda v : daoVenda.ListarMesAno(mes, ano)) {
                modelVenda.inserirVenda(v);
            }
            modelVenda.fireTableDataChanged();
        } else {
            carregarVendas();
        }
    }

    public int calcularCustoPontos() {
        int custo = 0;
        for (ItemVenda iv : modelVendaProdutos.getItens()) {
            Produto p = iv.getProduto();
            custo = p.getCustoPontos() * iv.getQuantidade();
        }
        return custo;
    }

    public double calcularValorTotal() {
        double valor = 0;
        for (ItemVenda iv : modelVendaProdutos.getItens()) {
            valor += iv.getPrecoUnitario() * iv.getQuantidade();
        }
        return valor;
    }

    public int calcularPontos() {
        double valor = calcularValorTotal();
        return (int) valor / 10;
    }

    public void adicionar() {
        int linhaSelecionada = addProdView.tbProdutos.getSelectedRow();
        Produto p = modelProduto.getProduto(linhaSelecionada);
        int quantidade = Integer.parseInt(addProdView.tQtd.getText());
        if (quantidade <= p.getEstoque()) {
            ItemVenda iv = new ItemVenda(p, quantidade, p.getPreco());
            modelVendaProdutos.inserirItem(iv);
            cadVendaView.tValorTotal.setText("Valor total: " + calcularValorTotal());
            p.setEstoque(p.getEstoque() - quantidade);
            daoProduto.editar(p);
            modelProduto.fireTableDataChanged();
        } else {
            JOptionPane.showMessageDialog(null, "O produto não possui estoque suficiente para compra!");
        }
    }

    public void cadastrar() {
        if (vendaSelecionada == null) {
            carregarProdutos();
            Cliente c = null;
            for (Cliente cli : daoCliente.Listar()) {
                if (cadVendaView.tfCpfCli.getText().equals(cli.getCpf())) {
                    c = cli;
                }
            }
            Funcionario f = null;
            for (Funcionario fun : daoFuncionario.Listar()) {
                if (cadVendaView.tfCpfFun.getText().equals(fun.getCpf())) {
                    f = fun;
                }
            }
            
            String dataTexto = cadVendaView.tfData.getText();
            DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(dataTexto, formatoEntrada);
            String formaPagamento = String.valueOf(cadVendaView.cbFormaPagamento.getSelectedItem());
            double valor = calcularValorTotal();
            Venda v = new Venda(String.valueOf(data), formaPagamento, valor, c, f);
            if (formaPagamento != "Pontos") {
                for (ItemVenda iv : modelVendaProdutos.getItens()) {
                    iv.setVenda(v);
                    v.addItem(iv);
                }
                if (daoVenda.inserir(v)) {
                    c.setPontos(c.getPontos() + calcularPontos());
                    daoCliente.editar(c);
                    modelVendaProdutos.limpar();
                    JOptionPane.showMessageDialog(null, "Sucesso ao cadastrar venda! O cliente ganhou: " + calcularPontos() + " pontos!");
                    limpar();
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar venda!");
                }
            } else if (c.getPontos() >= calcularCustoPontos()) {
                for (ItemVenda iv : modelVendaProdutos.getItens()) {
                    iv.setVenda(v);
                    v.addItem(iv);
                }
                if (daoVenda.inserir(v)) {
                    for (ItemVenda iv : modelVendaProdutos.getItens()) {
                        iv.setVenda(v);
                        v.addItem(iv);
                    }
                    c.setPontos(c.getPontos() - calcularCustoPontos());
                    daoCliente.editar(c);
                    JOptionPane.showMessageDialog(null, "Sucesso ao cadastrar venda!");
                    limpar();
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar venda!");

                }
            } else {
                JOptionPane.showMessageDialog(null, "Você não possui pontos suficientes");
            }
        } else {
            Cliente c = null;
            for (Cliente cli : daoCliente.Listar()) {
                if (cadVendaView.tfCpfCli.getText().equals(cli.getCpf())) {
                    c = cli;
                }
            }
            Funcionario f = null;
            for (Funcionario fun : daoFuncionario.Listar()) {
                if (cadVendaView.tfCpfFun.getText().equals(fun.getCpf())) {
                    f = fun;
                }
            }
            vendaSelecionada.setCliente(c);
            vendaSelecionada.setFuncionario(f);
            String dataTexto = cadVendaView.tfData.getText();
            DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(dataTexto, formatoEntrada);
            vendaSelecionada.setData(String.valueOf(data));
            vendaSelecionada.setFormaPagamento(String.valueOf(cadVendaView.cbFormaPagamento.getSelectedItem()));
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo editar o produto?") == JOptionPane.YES_OPTION) {
                if (daoVenda.editar(vendaSelecionada)) {
                    JOptionPane.showMessageDialog(null, "Sucesso ao editar produto!");
                    vendaSelecionada = null;
                    limpar();
                    cadVendaView.setVisible(false);
                    consVendasView.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao editar produto!");
                }
            }
        }
    }

    public void editar() {
        int linhaSelecionada = consVendasView.tbVendas.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo editar o produto?") == JOptionPane.YES_OPTION) {
                vendaSelecionada = modelVenda.getVenda(linhaSelecionada);
                modelVendaProdutos.limpar();
                String dataTexto = vendaSelecionada.getData();
                DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate data = LocalDate.parse(dataTexto, formatoEntrada);
                DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                cadVendaView.tfData.setText(data.format(formatoBR));
                cadVendaView.tfCpfCli.setText(vendaSelecionada.getCliente().getCpf());
                cadVendaView.tfCpfFun.setText(vendaSelecionada.getFuncionario().getCpf());
                cadVendaView.cbFormaPagamento.setSelectedItem(vendaSelecionada.getFormaPagamento());
                for (ItemVenda iv : vendaSelecionada.getItens()) {
                    modelVendaProdutos.inserirItem(iv);
                }
                modelVendaProdutos.fireTableDataChanged();
                cadVendaView.btAdicionar.setVisible(false);
                cadVendaView.btRemover.setVisible(false);
                consVendasView.setVisible(false);
                cadVendaView.setVisible(true);

            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }

    public void excluir() {
        int linhaSelecionada = consVendasView.tbVendas.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir a venda?") == JOptionPane.YES_OPTION) {
                Venda v = modelVenda.getVenda(linhaSelecionada);
                if (daoVenda.excluir(v)) {
                    JOptionPane.showMessageDialog(null, "Venda excluída!");
                    modelVenda.excluirVenda(linhaSelecionada);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir venda!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }
}
