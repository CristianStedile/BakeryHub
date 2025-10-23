package br.udesc.bakeryhub.model;

import br.udesc.bakeryhub.entidades.ItemVenda;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelVendaProdutos extends AbstractTableModel {

    private List<ItemVenda> itens = new ArrayList<>();

    @Override
    public int getRowCount() {
        return itens.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: {
                return "Produto";
            }
            case 1: {
                return "Preço";
            }
            case 2: {
                return "Quantidade";
            }
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ItemVenda iv = itens.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return " " + iv.getProduto();
            }
            case 1: {
                return " " + iv.getPrecoUnitario();
            }
            case 2: {
                return " " + iv.getQuantidade();
            }
        }
        return null;
    }

    public void limpar() {
        itens.clear();
    }

    public ItemVenda getItens(int rowIndex) {
        return itens.get(rowIndex);
    }

    public void excluirItens(int rowIndex) {
        itens.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void inserirItem(ItemVenda iv) {
        itens.add(iv);
        fireTableRowsInserted(itens.size() - 1, itens.size() - 1);
    }
}
