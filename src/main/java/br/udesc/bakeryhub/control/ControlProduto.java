package br.udesc.bakeryhub.control;

import br.udesc.bakeryhub.dao.DaoProduto;
import br.udesc.bakeryhub.entidades.Produto;
import br.udesc.bakeryhub.model.ModelProduto;
import br.udesc.bakeryhub.view.CadastroProdutoView;
import br.udesc.bakeryhub.view.ConsultaProdutosView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class ControlProduto {

    private CadastroProdutoView cadProdutoView;
    private ConsultaProdutosView consProdutoView;
    private Produto produtoSelecionado;
    private DaoProduto daoProduto;
    private ModelProduto modelProduto;

    public ControlProduto() {
        this.cadProdutoView = new CadastroProdutoView();
        this.consProdutoView = new ConsultaProdutosView();
        this.daoProduto = new DaoProduto();
        this.modelProduto = new ModelProduto();
        inicializarComponentes();
    }

    public void inicializarComponentes() {
        consProdutoView.tbProdutos.setModel(modelProduto);
        cadProdutoView.btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrar();
            }
        });
        consProdutoView.btEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });
        consProdutoView.btRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });
        consProdutoView.btPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisar();
            }
        });
        cadProdutoView.rbSim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadProdutoView.rbNao.setSelected(false);
                cadProdutoView.tfPontos.setEditable(true);
            }
        });
        cadProdutoView.rbNao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadProdutoView.rbSim.setSelected(false);
                cadProdutoView.tfPontos.setEditable(false);
            }
        });
    }

    public void cadastrarProduto() {
        this.cadProdutoView.setVisible(true);
    }

    public void consultarProdutos() {
        carregarProdutos();
        this.consProdutoView.setVisible(true);
    }

    public void limpar() {
        cadProdutoView.tfNome.setText("");
        cadProdutoView.tfEstoque.setText("");
        cadProdutoView.tfPromoção.setText("0");
        cadProdutoView.tfPreco.setText("");
        cadProdutoView.tfCodigo.setText("");
        consProdutoView.tfPesquisa.setText("");
    }

    public void carregarProdutos() {
        modelProduto.limpar();
        for (Produto p : daoProduto.Listar()) {
            modelProduto.inserirProduto(p);
        }
    }

    public void pesquisar() {
        modelProduto.limpar();
        String nome = consProdutoView.tfPesquisa.getText();
        for (Produto p : daoProduto.listarNome(nome)) {
            modelProduto.inserirProduto(p);
        }
        modelProduto.fireTableDataChanged();
    }

    public void cadastrar() {
        if (produtoSelecionado == null) {
            String nome = cadProdutoView.tfNome.getText();
            int estoque = Integer.parseInt(cadProdutoView.tfEstoque.getText());
            String codigo = cadProdutoView.tfCodigo.getText();
            double preco = Double.parseDouble(cadProdutoView.tfPreco.getText());
            int promocao = Integer.parseInt(cadProdutoView.tfPromoção.getText());
            int custoPontos = 0;
            if (cadProdutoView.tfPontos.isEditable()) {
                custoPontos = Integer.parseInt(cadProdutoView.tfPontos.getText());
            }
            Produto p = new Produto(codigo, nome, estoque, preco, custoPontos);
            if (promocao > 0) {
                p.setPromocao(promocao);
            }
            if (daoProduto.inserir(p)) {
                JOptionPane.showMessageDialog(null, "Sucesso ao cadastrar produto!");
                limpar();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto!");
            }
        } else {
            produtoSelecionado.setNome(cadProdutoView.tfNome.getText());
            produtoSelecionado.setCodigo(cadProdutoView.tfCodigo.getText());
            produtoSelecionado.setEstoque(Integer.parseInt(cadProdutoView.tfEstoque.getText()));
            produtoSelecionado.setPreco(Double.parseDouble(cadProdutoView.tfPreco.getText()));
            produtoSelecionado.setPromocao(Integer.parseInt(cadProdutoView.tfPromoção.getText()));
            if (cadProdutoView.rbSim.isSelected()) {
                produtoSelecionado.setCustoPontos(Integer.parseInt(cadProdutoView.tfPontos.getText()));
            }
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo editar o produto?") == JOptionPane.YES_OPTION) {
                if (daoProduto.editar(produtoSelecionado)) {
                    JOptionPane.showMessageDialog(null, "Sucesso ao editar produto!");
                    produtoSelecionado = null;
                    limpar();
                    cadProdutoView.setVisible(false);
                    consProdutoView.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao editar produto!");
                }
            }
        }
    }

    public void editar() {
        int linhaSelecionada = consProdutoView.tbProdutos.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo editar o produto?") == JOptionPane.YES_OPTION) {
                produtoSelecionado = modelProduto.getProduto(linhaSelecionada);
                cadProdutoView.tfNome.setText(produtoSelecionado.getNome());
                cadProdutoView.tfCodigo.setText(String.valueOf(produtoSelecionado.getCodigo()));
                cadProdutoView.tfEstoque.setText(String.valueOf(produtoSelecionado.getEstoque()));
                cadProdutoView.tfPreco.setText(String.valueOf(produtoSelecionado.getPreco()));
                cadProdutoView.tfPromoção.setText(String.valueOf(produtoSelecionado.getPromocao()));
                if (produtoSelecionado.getCustoPontos() > 0) {
                    cadProdutoView.rbSim.setSelected(true);
                    cadProdutoView.tfPontos.setEditable(true);
                    cadProdutoView.tfPontos.setText(String.valueOf(produtoSelecionado.getCustoPontos()));
                }else{
                    cadProdutoView.rbNao.setEnabled(true);
                    cadProdutoView.tfPontos.setEditable(false);
                }
                consProdutoView.setVisible(false);
                cadProdutoView.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }

    public void excluir() {
        int linhaSelecionada = consProdutoView.tbProdutos.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir o produto?") == JOptionPane.YES_OPTION) {
                Produto p = modelProduto.getProduto(linhaSelecionada);
                if (daoProduto.excluir(p)) {
                    JOptionPane.showMessageDialog(null, "Produto excluído!");
                    modelProduto.excluirProduto(linhaSelecionada);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir produto!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }
}
