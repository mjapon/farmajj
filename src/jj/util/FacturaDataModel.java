/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jj.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import jj.entity.Articulos;
import jj.gui.FacturaVentaFrame;

/**
 *
 * @author mjapon
 */
public class FacturaDataModel extends AbstractTableModel{
    
    private String[] columNames = {
        "Item",
        "Codbar",
        "Articulo",        
        "Cantidad",
        "Precio U.",
        "Subtotal",
        "IVA",        
        "Total"
    };
    
    private FacturaVentaFrame frame;
    
    private List<FilaFactura> items = new ArrayList<>();
    
    private TotalesFactura totalesFactura;
    
    public FacturaDataModel(){
        super();
        totalesFactura = new TotalesFactura();
        
        items.add(new FilaFactura(1,1,"001","ASPIRINA", 1.0, BigDecimal.ONE, false,BigDecimal.ZERO, BigDecimal.ZERO));
        items.add(new FilaFactura(1,1,"001","ASPIRINA", 1.0, BigDecimal.ONE, false,BigDecimal.ZERO, BigDecimal.ZERO));
        
        
        
    }
    
    public void addItem(Articulos articulo){
                
        FilaFactura filafactura = new FilaFactura(1,
                articulo.getArtId(),
                articulo.getArtCodbar(),
                articulo.getArtNombre(),
                new Double("1.0"),
                articulo.getArtPrecio(),
                articulo.getArtIva(),
                BigDecimal.ZERO,
                BigDecimal.ZERO
            );
        
        
        filafactura.updateTotales();
        
        items.add(filafactura);
        
        totalizarFactura();
        
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
        
        System.out.println("setValueAt--->columnIndex;"+columnIndex);
        if (rowIndex>=0 && rowIndex<items.size()){
            FilaFactura filafactura = items.get(rowIndex);
            switch (columnIndex){
                case 3: filafactura.setCantidad(Double.valueOf(aValue.toString()));break;
                case 6: filafactura.setIsIva("SI".equalsIgnoreCase(aValue.toString())?true:false);break;
                case 4: filafactura.setPrecioUnitario(new BigDecimal(aValue.toString()));break;
            }
            
            filafactura.updateTotales();    
            totalizarFactura();
            fireTableCellUpdated(rowIndex, columnIndex);
            
            frame.updateLabelsTotales();
        }        
        super.setValueAt(aValue, rowIndex, columnIndex); //To change body of generated methods, choose Tools | Templates.
    }   
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
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
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        
        switch(columnIndex){
            case 3: return true;//cantidad
            case 6: return true;//iva
            case 4: return true;//precioUnitario            
            default: return false;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        
        switch(columnIndex){
            case 0: return Integer.class;//cantidad
            case 1: return String.class;//iva
            case 2: return String.class;//precioUnitario            
            case 3: return String.class;//precioUnitario            
            case 4: return Double.class;//precioUnitario            
            case 5: return BigDecimal.class;//precioUnitario            
            case 6: return Boolean.class;//precioUnitario            
            case 7: return BigDecimal.class;//precioUnitario            
            case 8: return BigDecimal.class;//precioUnitario            
            default: return Object.class;
        }        
        //return super.getColumnClass(columnIndex); //To change body of generated methods, choose Tools | Templates.
    }    
    
    public void totalizarFactura(){
        totalesFactura.encerar();
        for(FilaFactura fila: items){
            fila.updateTotales();
            totalesFactura.setSubtotal( totalesFactura.getSubtotal().add(fila.getSubtotal()) );
            totalesFactura.setIva(totalesFactura.getIva().add(fila.getValorIva()));
            totalesFactura.setTotal(totalesFactura.getTotal().add(fila.getTotal()));
        }
        
        //Redondear ha 2 decimales
        totalesFactura.setSubtotal(totalesFactura.getSubtotal().setScale(2, BigDecimal.ROUND_HALF_UP));
        totalesFactura.setIva(totalesFactura.getIva().setScale(2, BigDecimal.ROUND_HALF_UP) );
        totalesFactura.setTotal( totalesFactura.getTotal().setScale(2, BigDecimal.ROUND_HALF_UP) );
        
        frame.updateLabelsTotales();
    }

    public List<FilaFactura> getItems() {
        return items;
    }

    public void setItems(List<FilaFactura> items) {
        this.items = items;
    }

    public TotalesFactura getTotalesFactura() {
        return totalesFactura;
    }

    public void setTotalesFactura(TotalesFactura totalesFactura) {
        this.totalesFactura = totalesFactura;
    }

    public FacturaVentaFrame getFrame() {
        return frame;
    }

    public void setFrame(FacturaVentaFrame frame) {
        this.frame = frame;
    }
    
    
}







