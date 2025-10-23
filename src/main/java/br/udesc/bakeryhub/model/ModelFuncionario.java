package br.udesc.bakeryhub.model;

import br.udesc.bakeryhub.entidades.Funcionario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelFuncionario extends AbstractTableModel {

    private List<Funcionario> funcionarios = new ArrayList<>();

    @Override
    public int getRowCount() {
        return funcionarios.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: {
                return "Cpf";
            }
            case 1: {
                return "Nome";
            }
            case 2: {
                return "Endereço";
            }
            case 3: {
                return "Cargo";
            }
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Funcionario f = funcionarios.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return " " + f.getCpf();
            }
            case 1: {
                return " " + f.getNome();
            }
            case 2: {
                return " " + f.getEndereco();
            }
            case 3: {
                return " " + f.getCargo();
            }
        }
        return null;
    }

    public void limpar() {
        funcionarios.clear();
    }

    public Funcionario getFuncionario(int rowIndex) {
        return funcionarios.get(rowIndex);
    }

    public void excluirFuncionario(int rowIndex) {
        funcionarios.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void inserirFuncionario(Funcionario f) {
        funcionarios.add(f);
        fireTableRowsInserted(funcionarios.size() - 1, funcionarios.size() - 1);
    }
}
