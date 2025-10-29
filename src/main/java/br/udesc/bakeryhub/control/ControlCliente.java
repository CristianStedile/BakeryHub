package br.udesc.bakeryhub.control;

import br.udesc.bakeryhub.dao.DaoCliente;
import br.udesc.bakeryhub.entidades.Cliente;
import br.udesc.bakeryhub.model.ModelCliente;
import br.udesc.bakeryhub.view.CadastroClienteView;
import br.udesc.bakeryhub.view.ConsultaClientesView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ControlCliente {
    
    private CadastroClienteView cadClienteView;
    private ConsultaClientesView consClientesView;
    private Cliente clienteSelecionado;
    private DaoCliente daoCliente;
    private ModelCliente modelCliente;
    
    public ControlCliente() {
        this.cadClienteView = new CadastroClienteView();
        this.consClientesView = new ConsultaClientesView();
        this.daoCliente = new DaoCliente();
        this.modelCliente = new ModelCliente();
        inicializarComponentes();
    }
    
    public void inicializarComponentes() {
        consClientesView.tbClientes.setModel(modelCliente);
        cadClienteView.btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrar();
            }
        });
        consClientesView.btEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });
        consClientesView.btRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });
        consClientesView.btPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisar();
            }
        });
    }
    
    public void cadastrarCliente() {
        this.cadClienteView.setVisible(true);
    }
    
    public void consultarClientes() {
        carregarClientes();
        this.consClientesView.setVisible(true);
    }
    
    public void limpar() {
        cadClienteView.tfCpf.setText("");
        cadClienteView.tfEndereco.setText("");
        cadClienteView.tfNome.setText("");
        consClientesView.tfPesquisa.setText("");
        cadClienteView.tfPontos.setText("");
    }
    
    public void carregarClientes() {
        modelCliente.limpar();
        for (Cliente c : daoCliente.Listar()) {
            modelCliente.inserirCliente(c);
        }
    }
    
    public void pesquisar() {
        String nome = consClientesView.tfPesquisa.getText();
        modelCliente.limpar();
        for (Cliente c : daoCliente.listarNome(nome)) {
            modelCliente.inserirCliente(c);
        }
        modelCliente.fireTableDataChanged();
    }
    
    public void cadastrar() {
        if (clienteSelecionado == null) {
            String nome = cadClienteView.tfNome.getText();
            String cpf = cadClienteView.tfCpf.getText();
            String endereco = cadClienteView.tfEndereco.getText();
            int pontos = Integer.parseInt(cadClienteView.tfPontos.getText());
            Cliente c = new Cliente(nome, cpf, endereco, pontos);
            if (daoCliente.inserir(c)) {
                JOptionPane.showMessageDialog(null, "Sucesso ao cadastrar cliente!");
                limpar();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente!");
            }
        } else {
            clienteSelecionado.setCpf(cadClienteView.tfCpf.getText());
            clienteSelecionado.setNome(cadClienteView.tfNome.getText());
            clienteSelecionado.setEndereco(cadClienteView.tfEndereco.getText());
            clienteSelecionado.setPontos(Integer.parseInt(cadClienteView.tfPontos.getText()));
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo editar o cliente?") == JOptionPane.YES_OPTION) {
                if (daoCliente.editar(clienteSelecionado)) {
                    JOptionPane.showMessageDialog(null, "Sucesso ao editar cliente!");
                    clienteSelecionado = null;
                    limpar();
                    cadClienteView.setVisible(false);
                    consClientesView.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao editar cliente!");
                }
            }
        }
    }
    
    public void editar() {
        int linhaSelecionada = consClientesView.tbClientes.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo editar o cliente?") == JOptionPane.YES_OPTION) {
                clienteSelecionado = modelCliente.getCliente(linhaSelecionada);
                cadClienteView.tfCpf.setText(clienteSelecionado.getCpf());
                cadClienteView.tfEndereco.setText(clienteSelecionado.getEndereco());
                cadClienteView.tfNome.setText(clienteSelecionado.getNome());
                cadClienteView.tfPontos.setText(String.valueOf(clienteSelecionado.getPontos()));
                consClientesView.setVisible(false);
                cadClienteView.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }
    
    public void excluir() {
        int linhaSelecionada = consClientesView.tbClientes.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir o cliente?") == JOptionPane.YES_OPTION) {
                Cliente c = modelCliente.getCliente(linhaSelecionada);
                if (daoCliente.excluir(c)) {
                    JOptionPane.showMessageDialog(null, "Cliente excluído!");
                    modelCliente.excluirCliente(linhaSelecionada);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir cliente!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }
}
