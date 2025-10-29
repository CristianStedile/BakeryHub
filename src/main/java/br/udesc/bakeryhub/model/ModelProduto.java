package br.udesc.bakeryhub.model;

import br.udesc.bakeryhub.entidades.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelProduto extends AbstractTableModel{
    private List<Produto> produtos = new ArrayList<>();

    @Override
    public int getRowCount() {
        return produtos.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }
    
    @Override
    public String getColumnName(int column){
        switch (column){
            case 0:{
                return "Código";
            }
            case 1:{
                return "Nome";
            }
            case 2:{
                return "Preço";
            }
            case 3:{
                return "Estoque";
            }
            case 4:{
                return "Custo de Pontos";
            }
        }
        return "";
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto p = produtos.get(rowIndex);
        switch(columnIndex){
            case 0:{
                return " "+p.getCodigo();
            }
            case 1:{
                return " "+p.getNome();
            }
            case 2:{
                return " "+p.getPreco();
            }
            case 3:{
                return " "+p.getEstoque();
            }
            case 4:{
                return " "+p.getCustoPontos();
            }
        }
        return null;
    }
    
    public void limpar(){
        produtos.clear();
    }
    
    public Produto getProduto(int rowIndex){
        return produtos.get(rowIndex);
    }
    
    public void excluirProduto(int rowIndex){
        produtos.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    public void inserirProduto(Produto p){
        produtos.add(p);
        fireTableRowsInserted(produtos.size()-1, produtos.size()-1);
    }
}
