/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jj.gui;

import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import jj.controller.ArticulosJpaController;
import jj.entity.Articulos;
import jj.util.EntityManagerUtil;
import jj.util.FacturaDataModel;
import jj.util.FacturaModelListener;
import jj.util.IVAComboBoxEditor;
import jj.util.IVAComboBoxRenderer;
import jj.util.TotalesFactura;

/**
 *
 * @author Usuario
 */
public class FacturaVentaFrame extends javax.swing.JFrame {
    
    private FacturaDataModel facturaDataModel;
    private FacturaModelListener facturaModelListener;
    private ArticulosJpaController articulosController;
    private EntityManager em;

    /**
     * Creates new form FacturaVentaFrame
     */
    public FacturaVentaFrame() {
        initComponents();
        
        System.out.println("Se inicializa jtable-->");
        
        facturaDataModel = new FacturaDataModel();
        
        facturaDataModel.setFrame(this);
        
        facturaModelListener = new FacturaModelListener();
        facturaDataModel.addTableModelListener(facturaModelListener);
        
        jTableFactura.setModel(facturaDataModel);       
        
        
        String[] values = new String[] { "SI", "NO"};
        
        TableColumn col = jTableFactura.getColumnModel().getColumn(6);
        col.setCellEditor(new IVAComboBoxEditor(values));
        col.setCellRenderer(new IVAComboBoxRenderer(values));
        
        jTableFactura.updateUI();
        
        facturaDataModel.fireTableDataChanged();
        
        System.out.println("Evento lanzado--->");
        
        this.em = EntityManagerUtil.createEntintyManagerFactory();
        
        articulosController = new ArticulosJpaController(em);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelCenter = new javax.swing.JPanel();
        jPanelDetallesFact = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFactura = new javax.swing.JTable();
        barCodeInput = new javax.swing.JTextField();
        jPanelDatosCli = new javax.swing.JPanel();
        jBtnConsFinal = new javax.swing.JButton();
        jTFCI = new javax.swing.JTextField();
        jTFCliente = new javax.swing.JTextField();
        jTFDireccion = new javax.swing.JTextField();
        jTFTelf = new javax.swing.JTextField();
        jTFEmail = new javax.swing.JTextField();
        jPanelNorth = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanelSouth = new javax.swing.JPanel();
        jPanelSubT = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabelSubTotal = new javax.swing.JLabel();
        jPanelTotal = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabelIVA = new javax.swing.JLabel();
        jPanelIva = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabelTOTAL = new javax.swing.JLabel();
        jPanelEst = new javax.swing.JPanel();
        jButtonGuardar = new javax.swing.JButton();
        jButtonArticulo = new javax.swing.JButton();
        jButtonTotales = new javax.swing.JButton();
        jButtonSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelCenter.setLayout(new java.awt.BorderLayout());

        jPanelDetallesFact.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jTableFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableFactura);

        jPanelDetallesFact.add(jScrollPane1, java.awt.BorderLayout.CENTER);
        jPanelDetallesFact.add(barCodeInput, java.awt.BorderLayout.NORTH);

        jPanelCenter.add(jPanelDetallesFact, java.awt.BorderLayout.CENTER);

        jPanelDatosCli.setMinimumSize(new java.awt.Dimension(600, 100));
        jPanelDatosCli.setLayout(new java.awt.GridLayout(8, 1));

        jBtnConsFinal.setText("CONSUMIDOR FINAL--->");
        jBtnConsFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnConsFinalActionPerformed(evt);
            }
        });
        jPanelDatosCli.add(jBtnConsFinal);

        jTFCI.setText("CI/RUC");
        jPanelDatosCli.add(jTFCI);

        jTFCliente.setText("CLIENTE");
        jPanelDatosCli.add(jTFCliente);

        jTFDireccion.setText("DIRECCION");
        jPanelDatosCli.add(jTFDireccion);

        jTFTelf.setText("TELEFONO");
        jPanelDatosCli.add(jTFTelf);

        jTFEmail.setText("EMAIL");
        jPanelDatosCli.add(jTFEmail);

        jPanelCenter.add(jPanelDatosCli, java.awt.BorderLayout.WEST);

        getContentPane().add(jPanelCenter, java.awt.BorderLayout.CENTER);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("FARMACIA JAIME J");

        javax.swing.GroupLayout jPanelNorthLayout = new javax.swing.GroupLayout(jPanelNorth);
        jPanelNorth.setLayout(jPanelNorthLayout);
        jPanelNorthLayout.setHorizontalGroup(
            jPanelNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNorthLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(572, Short.MAX_VALUE))
        );
        jPanelNorthLayout.setVerticalGroup(
            jPanelNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNorthLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanelNorth, java.awt.BorderLayout.NORTH);

        jPanelSouth.setLayout(new java.awt.GridLayout(1, 3));

        jPanelSubT.setLayout(new java.awt.GridLayout(2, 1));

        jLabel2.setText("SUBTOTAL:");
        jPanelSubT.add(jLabel2);

        jLabelSubTotal.setFont(new java.awt.Font("Dialog", 0, 48)); // NOI18N
        jPanelSubT.add(jLabelSubTotal);

        jPanelSouth.add(jPanelSubT);

        jPanelTotal.setLayout(new java.awt.GridLayout(2, 1));

        jLabel4.setText("IVA:");
        jPanelTotal.add(jLabel4);

        jLabelIVA.setFont(new java.awt.Font("Dialog", 0, 48)); // NOI18N
        jPanelTotal.add(jLabelIVA);

        jPanelSouth.add(jPanelTotal);

        jPanelIva.setLayout(new java.awt.GridLayout(2, 1));

        jLabel6.setText("TOTAL:");
        jPanelIva.add(jLabel6);

        jLabelTOTAL.setFont(new java.awt.Font("Dialog", 0, 48)); // NOI18N
        jPanelIva.add(jLabelTOTAL);

        jPanelSouth.add(jPanelIva);

        getContentPane().add(jPanelSouth, java.awt.BorderLayout.SOUTH);

        jPanelEst.setLayout(new java.awt.GridLayout(7, 1));

        jButtonGuardar.setText("GUARDAR");
        jPanelEst.add(jButtonGuardar);

        jButtonArticulo.setText("ARTICULO");
        jButtonArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonArticuloActionPerformed(evt);
            }
        });
        jPanelEst.add(jButtonArticulo);

        jButtonTotales.setText("TOTALES");
        jButtonTotales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTotalesActionPerformed(evt);
            }
        });
        jPanelEst.add(jButtonTotales);

        jButtonSalir.setText("SALIR");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });
        jPanelEst.add(jButtonSalir);

        getContentPane().add(jPanelEst, java.awt.BorderLayout.LINE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnConsFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnConsFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnConsFinalActionPerformed

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jButtonArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonArticuloActionPerformed
    
        String barcode = this.barCodeInput.getText();        
        List<Articulos> arts = this.articulosController.findByBarcode(barcode);
        
        if (arts != null && arts.size()>0){
            Articulos articulo =  arts.get(0);            
            facturaDataModel.addItem(articulo);            
        }
        
        
    }//GEN-LAST:event_jButtonArticuloActionPerformed

    private void jButtonTotalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTotalesActionPerformed
                
        facturaDataModel.totalizarFactura();
        
        //updateLabelsTotales();
        
    }//GEN-LAST:event_jButtonTotalesActionPerformed

    public void updateLabelsTotales(){
        
        TotalesFactura totalesFactura = facturaDataModel.getTotalesFactura();
        
        jLabelSubTotal.setText( totalesFactura.getSubtotal().toPlainString() );
        jLabelIVA.setText( totalesFactura.getIva().toPlainString() );
        jLabelTOTAL.setText( totalesFactura.getTotal().toPlainString() );
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FacturaVentaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FacturaVentaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FacturaVentaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FacturaVentaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FacturaVentaFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barCodeInput;
    private javax.swing.JButton jBtnConsFinal;
    private javax.swing.JButton jButtonArticulo;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JButton jButtonTotales;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelIVA;
    private javax.swing.JLabel jLabelSubTotal;
    private javax.swing.JLabel jLabelTOTAL;
    private javax.swing.JPanel jPanelCenter;
    private javax.swing.JPanel jPanelDatosCli;
    private javax.swing.JPanel jPanelDetallesFact;
    private javax.swing.JPanel jPanelEst;
    private javax.swing.JPanel jPanelIva;
    private javax.swing.JPanel jPanelNorth;
    private javax.swing.JPanel jPanelSouth;
    private javax.swing.JPanel jPanelSubT;
    private javax.swing.JPanel jPanelTotal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFCI;
    private javax.swing.JTextField jTFCliente;
    private javax.swing.JTextField jTFDireccion;
    private javax.swing.JTextField jTFEmail;
    private javax.swing.JTextField jTFTelf;
    private javax.swing.JTable jTableFactura;
    // End of variables declaration//GEN-END:variables
}