package br.udesc.bakeryhub.control;

import br.udesc.bakeryhub.view.*;
import br.udesc.bakeryhub.dao.*;
import br.udesc.bakeryhub.entidades.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControlPrincipal {

    private LoginView tLogin;
    private InicialView tInicial;
    private ControlFuncionario controlFuncionario;
    private ControlCliente controlCliente;
    private ControlProduto controlProduto;
    private ControlDespesa controlDespesa;
    private ControlVenda controlVenda;
    private DaoFuncionario daoFuncionario;

    public ControlPrincipal() {
        this.tLogin = new LoginView();
        this.daoFuncionario = new DaoFuncionario();
        controlFuncionario = new ControlFuncionario();
        controlCliente = new ControlCliente();
        controlFuncionario = new ControlFuncionario();
        controlVenda = new ControlVenda();
        controlDespesa = new ControlDespesa();
        tInicial = new InicialView();
        inicializarComponentes();
    }

    public void executar() {
        this.tLogin.setVisible(true);
    }

    public void inicializarComponentes() {
        tLogin.btRegistrar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controlFuncionario.cadastrarFuncionario();
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
        tLogin.btRecuperar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controlFuncionario.recInformacoes();
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
        tInicial.miCadastroCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlCliente.cadastrarCliente();
            }
        });
        tInicial.miConsultaCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlCliente.consultarClientes();
            }
        });
        tInicial.miCadastroFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlFuncionario.cadastrarFuncionario();
            }
        });
        tInicial.miConsultaFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlFuncionario.consultarFuncionarios();
            }
        });
        tInicial.miCadastroDespesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlDespesa.cadastrarDespesa();
            }
        });
        tInicial.miConsultaDespesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlDespesa.consultarDespesas();
            }
        });
        tLogin.btLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

    }

    public void login() {
        String login = tLogin.tfLogin.getText();
        String senha = tLogin.tfSenha.getText();
        for (Funcionario f : daoFuncionario.Listar()) {
            if (f.getLogin().equals(login) && f.getSenha().equals(senha)) {
                tLogin.setVisible(false);
                tInicial.setVisible(true);
            }
        }
    }
}
