package br.udesc.bakeryhub.model;

import br.udesc.bakeryhub.entidades.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelCliente extends AbstractTableModel{
    private List<Cliente> clientes = new ArrayList<>();

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }
    
    @Override
    public String getColumnName(int column){
        switch (column){
            case 0:{
                return "Cpf";
            }
            case 1:{
                return "Nome";
            }
            case 2:{
                return "Endereço";
            }
            case 3:{
                return "Pontos";
            }
        }
        return "";
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente c = clientes.get(rowIndex);
        switch(columnIndex){
            case 0:{
                return " "+c.getCpf();
            }
            case 1:{
                return " "+c.getNome();
            }
            case 2:{
                return " "+c.getEndereco();
            }
            case 3:{
                return " "+c.getPontos();
            }
        }
        return null;
    }
    
    public void limpar(){
        clientes.clear();
    }
    
    public Cliente getCliente(int rowIndex){
        return clientes.get(rowIndex);
    }
    
    public void excluirCliente(int rowIndex){
        clientes.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    public void inserirCliente(Cliente c){
        clientes.add(c);
        fireTableRowsInserted(clientes.size()-1, clientes.size()-1);
    }
}
