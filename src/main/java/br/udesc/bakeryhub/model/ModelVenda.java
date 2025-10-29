package br.udesc.bakeryhub.model;

import br.udesc.bakeryhub.entidades.Venda;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelVenda extends AbstractTableModel {

    private List<Venda> vendas = new ArrayList<>();

    @Override
    public int getRowCount() {
        return vendas.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: {
                return "Data";
            }
            case 1: {
                return "Forma de Pagamento";
            }
            case 2: {
                return "Funcionário";
            }
            case 3: {
                return "Cliente";
            }
            case 4: {
                return "Valor Total";
            }
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Venda v = vendas.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return " " + v.getData();
            }
            case 1: {
                return " " + v.getFormaPagamento();
            }
            case 2: {
                return " " + v.getFuncionario().getNome();
            }
            case 3: {
                return " " + v.getCliente().getNome();
            }
            case 4: {
                return " " + v.getValor();
            }
        }
        return null;
    }

    public void limpar() {
        vendas.clear();
    }

    public Venda getVenda(int rowIndex) {
        return vendas.get(rowIndex);
    }

    public void excluirVenda(int rowIndex) {
        vendas.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void inserirVenda(Venda v) {
        vendas.add(v);
        fireTableRowsInserted(vendas.size() - 1, vendas.size() - 1);
    }
}
