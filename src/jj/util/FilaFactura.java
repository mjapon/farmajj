/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jj.util;

import java.math.BigDecimal;

/**
 *
 * @author Usuario
 */
public class FilaFactura {
    
    private Integer numFila;
    private Integer codigoArt;
    private String codBarra;
    private String nombreArt;
    private Double cantidad;
    private BigDecimal precioUnitario;
    private boolean isIva;
    private BigDecimal subtotal;
    private BigDecimal total;
    private BigDecimal valorIva;

    public FilaFactura(
            Integer numFila, 
            Integer codigoArt, 
            String codBarra, 
            String nombreArt, 
            Double cantidad, 
            BigDecimal precioUnitario, 
            boolean isIva, 
            BigDecimal subtotal, 
            BigDecimal total) {
        this.numFila = numFila;
        this.codigoArt = codigoArt;
        this.codBarra = codBarra;
        this.nombreArt = nombreArt;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.isIva = isIva;
        this.subtotal = subtotal;
        this.total = total;
        this.valorIva = BigDecimal.ZERO;
    }
    
    public FilaFactura(){
        
    }

    public Integer getNumFila() {
        return numFila;
    }

    public void setNumFila(Integer numFila) {
        this.numFila = numFila;
    }

    public Integer getCodigoArt() {
        return codigoArt;
    }

    public void setCodigoArt(Integer codigoArt) {
        this.codigoArt = codigoArt;
    }

    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }

    public String getNombreArt() {
        return nombreArt;
    }

    public void setNombreArt(String nombreArt) {
        this.nombreArt = nombreArt;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public boolean isIsIva() {
        return isIva;
    }

    public void setIsIva(boolean isIva) {
        this.isIva = isIva;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
    public void updateTotales(){
        
        //Se calcula el total de cada fila        
        this.subtotal = new BigDecimal(this.cantidad).multiply(this.precioUnitario);                
        this.subtotal.setScale(CtesU.NUM_DECIM_VIEW, BigDecimal.ROUND_HALF_UP);
        
        if (this.isIva){
            this.valorIva = this.subtotal.multiply(new BigDecimal(CtesU.IVA));            
        }
        else{
            this.valorIva = BigDecimal.ZERO;
        }
        
        this.total = this.subtotal.add(this.valorIva);
        this.subtotal.setScale(CtesU.NUM_DECIM_VIEW, BigDecimal.ROUND_HALF_UP);
        
    }

    public BigDecimal getValorIva() {
        return valorIva;
    }

    public void setValorIva(BigDecimal valorIva) {
        this.valorIva = valorIva;
    }
    
    
}