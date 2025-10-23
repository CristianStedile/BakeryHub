package br.udesc.bakeryhub.control;

import br.udesc.bakeryhub.dao.DaoDespesa;
import br.udesc.bakeryhub.entidades.Despesa;
import br.udesc.bakeryhub.model.ModelDespesa;
import br.udesc.bakeryhub.view.CadastroDespesaView;
import br.udesc.bakeryhub.view.ConsultaDespesasView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class ControlDespesa {

    private CadastroDespesaView cadDespesaView;
    private ConsultaDespesasView consDespesaView;
    private DaoDespesa daoDespesa;
    private ModelDespesa modelDespesa;
    private Despesa despesaSelecionada;

    public ControlDespesa() {
        this.cadDespesaView = new CadastroDespesaView();
        this.consDespesaView = new ConsultaDespesasView();
        this.daoDespesa = new DaoDespesa();
        this.modelDespesa = new ModelDespesa();
        inicializarComponentes();
    }

    public void inicializarComponentes() {
        consDespesaView.tbDespesas.setModel(modelDespesa);
        cadDespesaView.btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrar();
            }
        });
        consDespesaView.btEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });
        consDespesaView.btRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });
        consDespesaView.btFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrar();
            }
        });
    }

    public void cadastrarDespesa() {
        this.cadDespesaView.setVisible(true);
    }

    public void consultarDespesas() {
        carregarDespesas();
        this.consDespesaView.setVisible(true);
    }

    public void limpar() {
        cadDespesaView.tfData.setText("");
        cadDespesaView.tfDescricao.setText("");
        cadDespesaView.tfNome.setText("");
        cadDespesaView.tfValor.setText("");
        consDespesaView.tfAno.setText("");
        consDespesaView.tfMes.setText("");
    }

    public void carregarDespesas() {
        modelDespesa.limpar();
        for (Despesa d : daoDespesa.Listar()) {
            modelDespesa.inserirDespesa(d);
        }
    }

    public void filtrar() {
        modelDespesa.limpar();
        int ano = Integer.parseInt(consDespesaView.tfAno.getText());
        int mes = Integer.parseInt(consDespesaView.tfMes.getText());
        for (Despesa d : daoDespesa.ListarMesAno(mes, ano)) {
            modelDespesa.inserirDespesa(d);
        }
    }

    public void cadastrar() {
        if (despesaSelecionada == null) {
            String nome = cadDespesaView.tfNome.getText();
            String descricao = cadDespesaView.tfDescricao.getText();
            double valor = Double.parseDouble(cadDespesaView.tfValor.getText());
            String dataTexto = cadDespesaView.tfData.getText();
            DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(dataTexto, formatoEntrada);
            Despesa d = new Despesa(nome, String.valueOf(data), descricao, valor);
            if (daoDespesa.inserir(d)) {
                JOptionPane.showMessageDialog(null, "Sucesso ao cadastrar despesa!");
                limpar();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar despesa!");
            }
        } else {
            String dataTexto = cadDespesaView.tfData.getText();
            DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(dataTexto, formatoEntrada);
            despesaSelecionada.setData(String.valueOf(data));
            despesaSelecionada.setDescricao(cadDespesaView.tfDescricao.getText());
            despesaSelecionada.setNome(cadDespesaView.tfNome.getText());
            despesaSelecionada.setValor(Double.parseDouble(cadDespesaView.tfValor.getText()));
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo editar a despesa?") == JOptionPane.YES_OPTION) {
                if (daoDespesa.editar(despesaSelecionada)) {
                    JOptionPane.showMessageDialog(null, "Sucesso ao editar despesa!");
                    despesaSelecionada = null;
                    limpar();
                    cadDespesaView.setVisible(false);
                    consDespesaView.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao editar despesa!");
                }
            }
        }
    }

    public void editar() {
        int linhaSelecionada = consDespesaView.tbDespesas.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo editar a despesa?") == JOptionPane.YES_OPTION) {
                despesaSelecionada = modelDespesa.getDespesa(linhaSelecionada);
                String dataTexto = despesaSelecionada.getData();
                DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate data = LocalDate.parse(dataTexto, formatoEntrada);
                DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                cadDespesaView.tfData.setText(data.format(formatoBR));
                cadDespesaView.tfDescricao.setText(despesaSelecionada.getDescricao());
                cadDespesaView.tfNome.setText(despesaSelecionada.getNome());
                cadDespesaView.tfValor.setText(String.valueOf(despesaSelecionada.getValor()));
                consDespesaView.setVisible(false);
                cadDespesaView.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }

    public void excluir() {
        int linhaSelecionada = consDespesaView.tbDespesas.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir a despesa?") == JOptionPane.YES_OPTION) {
                Despesa d = modelDespesa.getDespesa(linhaSelecionada);
                if (daoDespesa.excluir(d)) {
                    JOptionPane.showMessageDialog(null, "Despesa excluída!");
                    modelDespesa.excluirDespesa(linhaSelecionada);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir despesa!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!");
        }
    }
}
