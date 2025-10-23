package br.udesc.bakeryhub.control;

import br.udesc.bakeryhub.dao.DaoFuncionario;
import br.udesc.bakeryhub.entidades.Funcionario;
import br.udesc.bakeryhub.model.ModelFuncionario;
import br.udesc.bakeryhub.view.CadastroFuncionarioView;
import br.udesc.bakeryhub.view.ConsultaFuncionariosView;
import br.udesc.bakeryhub.view.LoginView;
import br.udesc.bakeryhub.view.RecuperarInformaçõesView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class ControlFuncionario {
    
    private CadastroFuncionarioView cadFuncionarioView;
    private ConsultaFuncionariosView consFuncionariosView;
    private RecuperarInformaçõesView recInformaçõesView;
    private LoginView loginView;
    private Funcionario funcionarioSelecionado;
    private DaoFuncionario daoFuncionario;
    private ModelFuncionario modelFuncionario;
    
    public ControlFuncionario() {
        this.cadFuncionarioView = new CadastroFuncionarioView();
        this.consFuncionariosView = new ConsultaFuncionariosView();
        this.recInformaçõesView = new RecuperarInformaçõesView();
        this.loginView = new LoginView();
        this.daoFuncionario = new DaoFuncionario();
        this.modelFuncionario = new ModelFuncionario();
        inicializarComponentes();
    }
    
    public void inicializarComponentes() {
        consFuncionariosView.tbFuncionarios.setModel(modelFuncionario);
        cadFuncionarioView.btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrar();
            }
        });
        recInformaçõesView.btRecuperar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recuperarInformacoes();
            }
        });
        consFuncionariosView.btEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });
        consFuncionariosView.btRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });
        loginView.btRecuperar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recInformacoes();
            }
        });
        consFuncionariosView.btPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisar();
            }
        });
        loginView.btRegistrar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cadastrarFuncionario();
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                
            }
            
        });
        loginView.btRecuperar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                recInformacoes();
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                
            }
            
        });
    }
    
    public void cadastrarFuncionario() {
        this.cadFuncionarioView.setVisible(true);
    }
    
    public void recInformacoes() {
        this.recInformaçõesView.setVisible(true);
    }
    
    public void consultarFuncionarios() {
        carregarFuncionario();
        this.consFuncionariosView.setVisible(true);
    }
    
    public void pesquisar() {
        modelFuncionario.limpar();
        String nome = consFuncionariosView.tfPesquisa.getText();
        for (Funcionario f : daoFuncionario.listarNome(nome)) {
            modelFuncionario.inserirFuncionario(f);
        }
    }
    
    public void limpar() {
        cadFuncionarioView.tfCargo.setText("");
        cadFuncionarioView.tfCpf.setText("");
        cadFuncionarioView.tfSenha.setText("");
        cadFuncionarioView.tfCodigo.setText("");
        cadFuncionarioView.tfLogin.setText("");
        cadFuncionarioView.tfNome.setText("");
        cadFuncionarioView.tfEndereco.setText("");
        consFuncionariosView.tfPesquisa.setText("");
        recInformaçõesView.tfCodigo.setText("");
    }
    
    public void carregarFuncionario() {
        modelFuncionario.limpar();
        for (Funcionario f : daoFuncionario.Listar()) {
            modelFuncionario.inserirFuncionario(f);
        }
    }
    
    public void recuperarInformacoes() {
        String codigo = recInformaçõesView.tfCodigo.getText();
        for (Funcionario f : daoFuncionario.Listar()) {
            if (f.getCodigoRecuperacao().equals(codigo)) {
                recInformaçõesView.lInformações.setText("Login: " + f.getLogin() + ", Senha: " + f.getSenha());
            }
        }
    }
    
    public void cadastrar() {
        if (funcionarioSelecionado == null) {
            String nome = cadFuncionarioView.tfNome.getText();
            String login = cadFuncionarioView.tfLogin.getText();
            String senha = cadFuncionarioView.tfSenha.getText();
            String cpf = cadFuncionarioView.tfCpf.getText();
            String endereco = cadFuncionarioView.tfEndereco.getText();
            String codigo = cadFuncionarioView.tfCodigo.getText();
            String cargo = cadFuncionarioView.tfCargo.getText();
            Funcionario f = new Funcionario(nome, cpf, endereco, login, senha, codigo, cargo);
            if (daoFuncionario.inserir(f)) {
                JOptionPane.showMessageDialog(null, "Sucesso ao cadastrar funcionário!");
                limpar();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário!");
            }
        } else {
            funcionarioSelecionado.setCargo(cadFuncionarioView.tfCargo.getText());
            funcionarioSelecionado.setCodigoRecuperacao(cadFuncionarioView.tfCodigo.getText());
            funcionarioSelecionado.setCpf(cadFuncionarioView.tfCpf.getText());
            funcionarioSelecionado.setNome(cadFuncionarioView.tfNome.getText());
            funcionarioSelecionado.setLogin(cadFuncionarioView.tfLogin.getText());
            funcionarioSelecionado.setEndereco(cadFuncionarioView.tfEndereco.getText());
            funcionarioSelecionado.setSenha(cadFuncionarioView.tfSenha.getText());
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo editar o funcionário?") == JOptionPane.YES_OPTION) {
                if (daoFuncionario.editar(funcionarioSelecionado)) {
                    JOptionPane.showMessageDialog(null, "Sucesso ao editar funcionário!");
                    funcionarioSelecionado = null;
                    limpar();
                    cadFuncionarioView.setVisible(false);
                    consFuncionariosView.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao editar funcionário!");
                }
            }
        }
    }
    
    public void editar() {
        int linhaSelecionada = consFuncionariosView.tbFuncionarios.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo editar o funcionário?") == JOptionPane.YES_OPTION) {
                funcionarioSelecionado = modelFuncionario.getFuncionario(linhaSelecionada);
                cadFuncionarioView.tfCodigo.setText(funcionarioSelecionado.getCodigoRecuperacao());
                cadFuncionarioView.tfCargo.setText(funcionarioSelecionado.getCargo());
                cadFuncionarioView.tfCpf.setText(funcionarioSelecionado.getCpf());
                cadFuncionarioView.tfEndereco.setText(funcionarioSelecionado.getEndereco());
                cadFuncionarioView.tfLogin.setText(funcionarioSelecionado.getLogin());
                cadFuncionarioView.tfNome.setText(funcionarioSelecionado.getNome());
                cadFuncionarioView.tfSenha.setText(funcionarioSelecionado.getSenha());
                consFuncionariosView.setVisible(false);
                cadFuncionarioView.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }
    
    public void excluir() {
        int linhaSelecionada = consFuncionariosView.tbFuncionarios.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir o funcionário?") == JOptionPane.YES_OPTION) {
                Funcionario f = modelFuncionario.getFuncionario(linhaSelecionada);
                if (daoFuncionario.excluir(f)) {
                    JOptionPane.showMessageDialog(null, "Funcionário excluído!");
                    modelFuncionario.excluirFuncionario(linhaSelecionada);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir funcionário!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }
}
