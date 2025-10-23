package br.udesc.bakeryhub.model;

import br.udesc.bakeryhub.entidades.Despesa;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelDespesa extends AbstractTableModel {

    private List<Despesa> despesas = new ArrayList<>();

    @Override
    public int getRowCount() {
        return despesas.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: {
                return "Data";
            }
            case 1: {
                return "Nome";
            }
            case 2: {
                return "Descrição";
            }
            case 3: {
                return "Valor";
            }
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Despesa d = despesas.get(rowIndex);
        String dataTexto = d.getData();
        DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate data = LocalDate.parse(dataTexto, formatoEntrada);
        DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        switch (columnIndex) {
            case 0: {
                return " " + data.format(formatoBR);
            }
            case 1: {
                return " " + d.getNome();
            }
            case 2: {
                return " " + d.getDescricao();
            }
            case 3: {
                return " " + d.getValor();
            }
        }
        return null;
    }

    public void limpar() {
        despesas.clear();
    }

    public Despesa getDespesa(int rowIndex) {
        return despesas.get(rowIndex);
    }

    public void excluirDespesa(int rowIndex) {
        despesas.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void inserirDespesa(Despesa d) {
        despesas.add(d);
        fireTableRowsInserted(despesas.size() - 1, despesas.size() - 1);
    }
}
