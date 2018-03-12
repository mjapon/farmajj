/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jj.util;

import java.math.BigDecimal;

/**
 *
 * @author mjapon
 */
public class TotalesFactura {
    
    private BigDecimal subtotal;
    private BigDecimal iva;
    private BigDecimal total;
    
    public TotalesFactura(){
        subtotal = BigDecimal.ZERO;
        iva = BigDecimal.ZERO;
        total = BigDecimal.ZERO;
    }

    public TotalesFactura(BigDecimal subtotal, BigDecimal iva, BigDecimal total) {
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
    public void encerar(){
        subtotal = BigDecimal.ZERO;
        iva = BigDecimal.ZERO;
        total = BigDecimal.ZERO;
    }
    
}
