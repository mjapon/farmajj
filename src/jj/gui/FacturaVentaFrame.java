/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jj.gui;

import com.serviestudios.print.util.TextPrinterUtil;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import jj.controller.ArticulosJpaController;
import jj.controller.ClientesJpaController;
import jj.controller.CtesJpaController;
import jj.controller.FacturasJpaController;
import jj.controller.SecuenciasJpaController;
import jj.entity.Articulos;
import jj.entity.Clientes;
import jj.entity.Ctes;
import jj.entity.Secuencias;
import jj.util.DatosCabeceraFactura;
import jj.util.EntityManagerUtil;
import jj.util.FacturaDataModel;
import jj.util.FacturaModelListener;
import jj.util.FechasUtil;
import jj.util.FilaFactura;
import jj.util.GenTxtFactura;
import jj.util.IVAComboBoxEditor;
import jj.util.IVAComboBoxRenderer;
import jj.util.StringUtil;
import jj.util.TotalesFactura;

/**
 *
 * @author Usuario
 */
public class FacturaVentaFrame extends javax.swing.JFrame {
    
    private final FacturaDataModel facturaDataModel;
    private final FacturaModelListener facturaModelListener;
    private final ArticulosJpaController articulosController;
    private final ClientesJpaController clientesController;
    private final FacturasJpaController facturaController;
    private final SecuenciasJpaController secuenciasController;
    private final CtesJpaController ctesController;
    private EntityManager em;
    private Integer cliCodigo;
    private Clientes consFinal;
    

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
        
        updateLabelsTotales();
        
        jTableFactura.updateUI();        
        facturaDataModel.fireTableDataChanged();
        
        this.em = EntityManagerUtil.createEntintyManagerFactory();
        
        articulosController = new ArticulosJpaController(em);
        clientesController = new ClientesJpaController(em);
        facturaController = new FacturasJpaController(em);
        secuenciasController = new SecuenciasJpaController(em);
        ctesController = new CtesJpaController(em);
        
        enableDisableCamposCli(!this.jCBConsFinal.isSelected());
        
        consFinal = clientesController.findById(-1);
        
        initNewFactura();
        
        this.loadDatosConsFinal();
        
    }
    
    public void loadDatosConsFinal(){
        
        System.out.println("SE carga datos de cons final-->");
        
        if (consFinal != null){
            
            System.out.println("cons final es distinto de null-->");
            
            this.cliCodigo = this.consFinal.getCliId();
            this.jTFCI.setText(this.consFinal.getCliCi());
            this.jTFCliente.setText(this.consFinal.getCliNombres());
            
        }
        
    }
    
    public void initNewFactura(){
        
        this.em = EntityManagerUtil.createEntintyManagerFactory();
        
        Secuencias secuencia = secuenciasController.getSecuencia("EST001");
        if (secuencia == null){
            JOptionPane.showMessageDialog(this, "ERROR:No se ha registrado la secuencia de facturas:EST001, favor registrar", "ERROR SECUENCIAS", JOptionPane.ERROR_MESSAGE);
        }
        else{
            jTFNumFact.setText( String.valueOf( secuencia.getSecValor() ) );
        }
        
        String estabPtoEmi = "";
        Ctes ctesStab = ctesController.findByClave("ESTAB");
        
        if (ctesStab == null){
            JOptionPane.showMessageDialog(this, "ERROR:No se ha registrado ESTAB en ctes", "ERROR CONFIG", JOptionPane.ERROR_MESSAGE);
        }
        else{
            estabPtoEmi = ctesStab.getCtesValor();
        }
        
        Ctes ctesPtoEmi = ctesController.findByClave("PTOEMI");
        
        if (ctesPtoEmi == null){
            JOptionPane.showMessageDialog(this, "ERROR:No se ha registrado ESTAB en ctes", "ERROR CONFIG", JOptionPane.ERROR_MESSAGE);
        }
        else{
            estabPtoEmi = estabPtoEmi+ctesStab.getCtesValor();        
        }
        
        this.jLabelEstPtoEmi.setText(estabPtoEmi);
        
        //fecha de emsion
        String fechaActual = FechasUtil.getFechaActual();
        this.jTFFecha.setText( fechaActual );    
        
        this.cliCodigo = 0;
        
        jTableFactura.updateUI();        
        facturaDataModel.getItems().clear();
        facturaDataModel.fireTableDataChanged();
        
        facturaDataModel.encerarTotales();        
        updateLabelsTotales();
        
        this.jTFCI.setText("");
        this.jTFCliente.setText("");
        this.jTFDireccion.setText("");
        this.jTFTelf.setText("");
        this.jTFEmail.setText("");
        
        this.jCBConsFinal.setSelected(true);
        
        barCodeInput.setText("");
        barCodeInput.requestFocus();        
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
        jPanel6 = new javax.swing.JPanel();
        barCodeInput = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFactura = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabelEstPtoEmi = new javax.swing.JLabel();
        jTFNumFact = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTFFecha = new javax.swing.JFormattedTextField();
        jPanelDatosCli = new javax.swing.JPanel();
        jCBConsFinal = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTFCI = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTFCliente = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTFDireccion = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTFTelf = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
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
        jButtonArticulo = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonTotales = new javax.swing.JButton();
        jButtonSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 566));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanelCenter.setLayout(new java.awt.BorderLayout());

        jPanelDetallesFact.setLayout(new java.awt.BorderLayout());

        jPanel6.setLayout(new java.awt.BorderLayout());

        barCodeInput.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        barCodeInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barCodeInputActionPerformed(evt);
            }
        });
        barCodeInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                barCodeInputKeyPressed(evt);
            }
        });
        jPanel6.add(barCodeInput, java.awt.BorderLayout.NORTH);

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

        jPanel6.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanelDetallesFact.add(jPanel6, java.awt.BorderLayout.CENTER);

        jLabel10.setText("Nro:");

        jLabelEstPtoEmi.setText("001002");

        jLabel11.setText("Fecha:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelEstPtoEmi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFNumFact, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(463, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTFFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelEstPtoEmi, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTFNumFact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanelDetallesFact.add(jPanel7, java.awt.BorderLayout.NORTH);

        jPanelCenter.add(jPanelDetallesFact, java.awt.BorderLayout.CENTER);

        jPanelDatosCli.setMinimumSize(new java.awt.Dimension(600, 100));
        jPanelDatosCli.setLayout(new java.awt.GridLayout(8, 1));

        jCBConsFinal.setSelected(true);
        jCBConsFinal.setText("Consumidor Final");
        jCBConsFinal.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCBConsFinalStateChanged(evt);
            }
        });
        jCBConsFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBConsFinalActionPerformed(evt);
            }
        });
        jCBConsFinal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jCBConsFinalPropertyChange(evt);
            }
        });
        jPanelDatosCli.add(jCBConsFinal);

        jPanel1.setLayout(new java.awt.GridLayout(2, 1));

        jLabel3.setText("CI/RUC:");
        jPanel1.add(jLabel3);

        jTFCI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFCIActionPerformed(evt);
            }
        });
        jTFCI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFCIKeyPressed(evt);
            }
        });
        jPanel1.add(jTFCI);

        jPanelDatosCli.add(jPanel1);

        jPanel2.setLayout(new java.awt.GridLayout(2, 1));

        jLabel5.setText("Cliente:");
        jPanel2.add(jLabel5);
        jPanel2.add(jTFCliente);

        jPanelDatosCli.add(jPanel2);

        jPanel3.setLayout(new java.awt.GridLayout(2, 1));

        jLabel7.setText("Direccion:");
        jPanel3.add(jLabel7);
        jPanel3.add(jTFDireccion);

        jPanelDatosCli.add(jPanel3);

        jPanel4.setLayout(new java.awt.GridLayout(2, 1));

        jLabel8.setText("Telf:");
        jPanel4.add(jLabel8);
        jPanel4.add(jTFTelf);

        jPanelDatosCli.add(jPanel4);

        jPanel5.setLayout(new java.awt.GridLayout(2, 1));

        jLabel9.setText("Email:");
        jPanel5.add(jLabel9);
        jPanel5.add(jTFEmail);

        jPanelDatosCli.add(jPanel5);

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
                .addContainerGap(1232, Short.MAX_VALUE))
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

        jButtonArticulo.setText("ARTICULO");
        jButtonArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonArticuloActionPerformed(evt);
            }
        });
        jPanelEst.add(jButtonArticulo);

        jButtonGuardar.setText("GUARDAR");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });
        jPanelEst.add(jButtonGuardar);

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

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void findArticulo(){
        String barcode = this.barCodeInput.getText();
        List<Articulos> arts = this.articulosController.findByBarcode(barcode);
        
        if (arts != null && arts.size()>0){
            Articulos articulo =  arts.get(0);            
            facturaDataModel.addItem(articulo);            
        }
        else{
            JOptionPane.showMessageDialog(this, "No se encontró el código de barra:"+barcode, "Farmacia", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void jButtonArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonArticuloActionPerformed
        
        findArticulo();        
        
    }//GEN-LAST:event_jButtonArticuloActionPerformed

    private void jButtonTotalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTotalesActionPerformed
                
        facturaDataModel.totalizarFactura();
        
    }//GEN-LAST:event_jButtonTotalesActionPerformed

    private void barCodeInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barCodeInputKeyPressed
       
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            findArticulo();
        }
        
        
    }//GEN-LAST:event_barCodeInputKeyPressed

    private void barCodeInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barCodeInputActionPerformed
        
        
        
    }//GEN-LAST:event_barCodeInputActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
        System.out.println("Window opened----->");        
        barCodeInput.requestFocus();
        
    }//GEN-LAST:event_formWindowOpened
    
    public void logicaImpresion(DatosCabeceraFactura cabecera, 
            TotalesFactura totales, 
            List<FilaFactura> detalles){        
        try{
            System.out.println("Logica de impresion-->"); 
            
            String templateCab = ctesController.findValueByClave("TEMPLATE_CAB");
            
            if (templateCab == null){
                System.out.println("Templatecab no definido");
                templateCab = "";
            }
            
            String templateFila = ctesController.findValueByClave("TEMPLATE_DET");
            if (templateFila == null){
                System.out.println("Templatedet no definido");
                templateFila = "";
            }
            
            String templatePie = ctesController.findValueByClave("TEMPLATE_PIE");
            if (templatePie == null){
                System.out.println("Templatepie no definido");
                templatePie = "";
            }
            
            try{                
                String textToPrint = GenTxtFactura.getTxt(cabecera, 
                    detalles, 
                    totales, 
                    templateCab, 
                    templateFila, 
                    templatePie);
                
                System.out.println("texto ha imprimir");
                System.out.println(textToPrint);
                    
                String impresora = "";        
                TextPrinterUtil printerUtil = new TextPrinterUtil();
                printerUtil.imprimir(impresora, textToPrint);                            
            }
            catch(Throwable ex){
                System.out.println("Error al generar txt:"+ex.getMessage());
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al imprimir:"+ex.getMessage());
            }            
        }
        catch(Throwable ex){
            System.out.println("Error al tratar de imprimir:"+ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        
        try{
            DatosCabeceraFactura cabeceraFactura = new DatosCabeceraFactura();
            cabeceraFactura.setNroEstFact(jLabelEstPtoEmi.getText());
            cabeceraFactura.setNumFactura(jTFNumFact.getText());            
            if (this.jCBConsFinal.isSelected()){
                cabeceraFactura.setCliId(-1);
            }
            else{
                cabeceraFactura.setCliId(this.cliCodigo);
            }            
            cabeceraFactura.setCi(this.jTFCI.getText());
            cabeceraFactura.setCliente(this.jTFCliente.getText());
            cabeceraFactura.setDireccion(this.jTFDireccion.getText());
            cabeceraFactura.setTelf(this.jTFTelf.getText());
            cabeceraFactura.setEmail(this.jTFEmail.getText());
            cabeceraFactura.setFechaFactura(this.jTFFecha.getText());
            
             TotalesFactura totalesFactura = facturaDataModel.getTotalesFactura();             
             List<FilaFactura> detalles = facturaDataModel.getItems();
            
            facturaController.crearFactura(cabeceraFactura, totalesFactura, detalles);
            
            int res = JOptionPane.showConfirmDialog(this, "Registrado Satisfactoriamente, Imprimir?", "Factura", JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION){
                logicaImpresion(cabeceraFactura, totalesFactura, detalles);
            }
            
            initNewFactura();            
            
        }
        catch(Throwable ex){
            JOptionPane.showMessageDialog(null, "Error al registrar factura:"+ ex.getMessage(), "NO SE PUDO REGISTRAR FACTURA", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error al registrar factura:"+ ex.getMessage());
            ex.printStackTrace();
        }
        
        
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    public void enableDisableCamposCli(boolean enable){
        this.jTFCI.setEnabled(enable);
        this.jTFCliente.setEnabled(enable);
        this.jTFDireccion.setEnabled(enable);
        this.jTFTelf.setEnabled(enable);
        this.jTFEmail.setEnabled(enable);        
        if (enable){
            this.jTFCI.requestFocus();
        }
    }    
    
    private void jCBConsFinalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jCBConsFinalPropertyChange
        
        System.out.println("prop value changed-->"+ evt.getNewValue() );
        
    }//GEN-LAST:event_jCBConsFinalPropertyChange

    private void jCBConsFinalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCBConsFinalStateChanged
        
        System.out.println("jCBConsFinalStateChanged value changed-->" );
        enableDisableCamposCli(!this.jCBConsFinal.isSelected());
        
        
    }//GEN-LAST:event_jCBConsFinalStateChanged

    private void jCBConsFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBConsFinalActionPerformed
        
        
        
        
    }//GEN-LAST:event_jCBConsFinalActionPerformed

    private void jTFCIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFCIActionPerformed
        
        
        
        
    }//GEN-LAST:event_jTFCIActionPerformed
    
    private void findCliente(){
        
        String CI = this.jTFCI.getText();
        if (StringUtil.isNotEmpty(CI)){
            
            Clientes cliente = clientesController.findByCi(CI);
            if (cliente!=null){
                jTFCliente.setText(cliente.getCliNombres());
                jTFDireccion.setText(cliente.getCliDir());
                jTFTelf.setText(cliente.getCliTelf());
                jTFEmail.setText(cliente.getCliEmail());
                
                this.cliCodigo = cliente.getCliId();
            }
            else{
                jTFCliente.requestFocus();
                System.out.println("Cliente no registrado-->");
            }
            
        }
        else{
            System.out.println("Ingrese el numero de cedula-->");
            //JOptionPane.showMessageDialog(this, "No se encuentra registrado");
            //jTFCliente.requestFocus();
        }
        
    }
    
    private void jTFCIKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCIKeyPressed
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            findCliente();
        }
        
        
    }//GEN-LAST:event_jTFCIKeyPressed
    
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
    private javax.swing.JButton jButtonArticulo;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JButton jButtonTotales;
    private javax.swing.JCheckBox jCBConsFinal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelEstPtoEmi;
    private javax.swing.JLabel jLabelIVA;
    private javax.swing.JLabel jLabelSubTotal;
    private javax.swing.JLabel jLabelTOTAL;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
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
    private javax.swing.JFormattedTextField jTFFecha;
    private javax.swing.JTextField jTFNumFact;
    private javax.swing.JTextField jTFTelf;
    private javax.swing.JTable jTableFactura;
    // End of variables declaration//GEN-END:variables
}
