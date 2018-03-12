/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jj.util;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import jj.entity.Articulos;

/**
 *
 * @author manuel.japon
 */
public class ArticulosDataModel extends AbstractTableModel{
    
    private List<Articulos> items = new ArrayList<>();
     private String[] columNames = {
        "Articulo",
        "Codbar",
        "Precio"
    };    

    public void addItem(Articulos articulo){
        items.add(articulo);      
        fireTableDataChanged();
    }
    

    @Override
    public String getColumnName(int column) {
        
        if (column>=0 && column<columNames.length){
            return columNames[column];
        }
        
        return super.getColumnName(column); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return this.columNames.length;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        /*
        System.out.println("setValueAt--->columnIndex;"+columnIndex);
        if (rowIndex>=0 && rowIndex<items.size()){
            Articulos articulo = items.get(rowIndex);
            switch (columnIndex){
                case 0: filafactura.setCantidad(Double.valueOf(aValue.toString()));break;
                case 1: filafactura.setIsIva("SI".equalsIgnoreCase(aValue.toString())?true:false);break;
                case 2: filafactura.setPrecioUnitario(new BigDecimal(aValue.toString()));break;
            }
            
            filafactura.updateTotales();    
            totalizarFactura();
            fireTableCellUpdated(rowIndex, columnIndex);
            
            frame.updateLabelsTotales();
        }  
        */
        super.setValueAt(aValue, rowIndex, columnIndex); //To change body of generated methods, choose Tools | Templates.
    }   
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        /*
        if (rowIndex>=0 && rowIndex<items.size()){
            FilaFactura filafactura = items.get(rowIndex);
            
            switch (columnIndex){
                case 0: return filafactura.getNumFila();
                case 1: return filafactura.getCodBarra();
                case 2: return filafactura.getNombreArt();
                case 3: return filafactura.getCantidad();
                case 4: return filafactura.getPrecioUnitario();
                case 5: return filafactura.getSubtotal();
                case 6: return filafactura.isIsIva()?"SI":"NO";
                case 7: return filafactura.getTotal();
                default: return "";
            }
        }
        else{
            return "";
        }
        */  
        return "";
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        
        return false;
        /*
        switch(columnIndex){
            case 3: return true;//cantidad
            case 6: return true;//iva
            case 4: return true;//precioUnitario            
            default: return false;
        }
        */
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        
        switch(columnIndex){
            case 0: return Integer.class;//cantidad
            case 1: return String.class;//iva
            case 2: return String.class;//precioUnitario                        
            default: return Object.class;
        }        
        //return super.getColumnClass(columnIndex); //To change body of generated methods, choose Tools | Templates.
    }        
    
}
