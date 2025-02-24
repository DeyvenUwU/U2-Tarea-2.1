package ventanas;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import conexiones.AccesoAreas;
import conexiones.AccesoInventarios;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import objetos.Area;
import objetos.Inventario;

public class CrudInventarios extends javax.swing.JFrame {
    
    private AccesoInventarios inventarios;
    private ArrayList<Inventario> inventariosData;
    private AccesoAreas areas;
    private ArrayList<Area> areasData;
    
    private static Point puntoInicial = new Point();

    public CrudInventarios() {
        initConfig();
    }
    
    public void initConfig() {
        setUndecorated(true);
        initComponents();
        
        jPanel1.setFocusable(true);
        setResizable(false);
        setLocationRelativeTo(null);
        popEditar.setIcon(new FlatSVGIcon("images/editar.svg", 16, 16));
        popEliminar.setIcon(new FlatSVGIcon("images/eliminar.svg", 16, 16));
        moverVentanaConMouse(this, jPanel1);

        TableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setFont(new Font("Segoe UI", Font.BOLD, 14));
                c.setForeground(Color.white);

                return c;
            }
        };
        
        TableColumnModel columnModel = tblInventarios.getColumnModel();  
        int[] width = {50, 200, 105, 105, 105, 140};
        for (int i=0; i<6; i++) {
            tblInventarios.getColumnModel().getColumn(i).setPreferredWidth(width[i]);
            columnModel.getColumn(i).setHeaderRenderer(headerRenderer);
        }
        
        JTableHeader header = tblInventarios.getTableHeader();
        header.setDefaultRenderer(headerRenderer);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        
        inventarios = new AccesoInventarios();
        inventariosData = new ArrayList<>();
        inventariosData = inventarios.getTodosLosInventarios();
        areas = new AccesoAreas();
        areasData = new ArrayList<>();
        areasData = areas.getTodasLasAreas();
        
        actualizarTabla(inventariosData);
        actualizarDetalles(null);
    }
    
    public void actualizarTabla(ArrayList<Inventario> lista) {
        DefaultTableModel tabla = (DefaultTableModel) tblInventarios.getModel();
        tabla.setRowCount(0);
        
        for (Inventario i : lista) {
            Object[] Fila = new Object[6];
            Fila[0] = i.getId();
            Fila[1] = i.getNombreCorto();
            Fila[2] = i.getSerie();
            Fila[3] = i.getFechaAdq();
            Fila[4] = i.getTipoAdq();
            Fila[5] = areasData.get(i.getIdArea()-1).getNombre();
            
            tabla.addRow(Fila);
        }
        
        lblCant.setText("" + lista.size());
    }
    
    public void actualizarDetalles(Inventario seleccionado) {
        txpDetalles.setText("");
        
        StyledDocument doc = txpDetalles.getStyledDocument();
        
        Style negrita = txpDetalles.addStyle("EstiloNegrita", null);
        StyleConstants.setBold(negrita, true);
        
        Style grande = txpDetalles.addStyle("EstiloGrande", null);
        StyleConstants.setBold(grande, true);
        StyleConstants.setFontSize(grande, 24);
        
        Style gris = txpDetalles.addStyle("EstiloGris", null);
        StyleConstants.setForeground(gris, Color.GRAY);
        
        try {
            doc.insertString(doc.getLength(), "DETALLES\n\n", grande);
            if (seleccionado == null)
                doc.insertString(doc.getLength(), "(Selecciona un Inventario de la tabla para ver los detalles)", gris);
            else {
                doc.insertString(doc.getLength(), "ID: ", negrita);
                doc.insertString(doc.getLength(), seleccionado.getId() + "\n\n", null);
                doc.insertString(doc.getLength(), "Nombre Corto: ", negrita);
                doc.insertString(doc.getLength(), seleccionado.getNombreCorto() + "\n\n", null);
                doc.insertString(doc.getLength(), "Descripción: ", negrita);
                doc.insertString(doc.getLength(), seleccionado.getDescripcion() + "\n\n", null);
                doc.insertString(doc.getLength(), "Serie: ", negrita);
                doc.insertString(doc.getLength(), seleccionado.getSerie() + "\n\n", null);
                doc.insertString(doc.getLength(), "Color: ", negrita);
                doc.insertString(doc.getLength(), seleccionado.getColor() + "\n\n", null);
                doc.insertString(doc.getLength(), "Fecha Adquisición: ", negrita);
                doc.insertString(doc.getLength(), seleccionado.getFechaAdq() + "\n\n", null);
                doc.insertString(doc.getLength(), "Tipo Adquisición: ", negrita);
                doc.insertString(doc.getLength(), seleccionado.getTipoAdq() + "\n\n", null);
                doc.insertString(doc.getLength(), "Obsrvaciones: : ", negrita);
                doc.insertString(doc.getLength(), seleccionado.getObservaciones() + "\n\n", null);
                doc.insertString(doc.getLength(), "Área: ", negrita);
                doc.insertString(doc.getLength(), areasData.get(seleccionado.getIdArea()-1).getNombre() + "", null);
            }
        } catch (BadLocationException e) {}
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        popEditar = new javax.swing.JMenuItem();
        popEliminar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnMinimizar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInventarios = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txpDetalles = new javax.swing.JTextPane();
        lblCant = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();

        popEditar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        popEditar.setForeground(new java.awt.Color(255, 255, 255));
        popEditar.setText("Editar");
        popEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popEditarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popEditar);

        popEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        popEliminar.setForeground(new java.awt.Color(170, 0, 0));
        popEliminar.setText("Eliminar");
        popEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popEliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popEliminar);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        btnCerrar.setBackground(new java.awt.Color(0, 0, 0));
        btnCerrar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnCerrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrar.setText("X");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 0, 0));
        jButton4.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("□");
        jButton4.setEnabled(false);

        btnMinimizar.setBackground(new java.awt.Color(0, 0, 0));
        btnMinimizar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnMinimizar.setForeground(new java.awt.Color(255, 255, 255));
        btnMinimizar.setText("_");
        btnMinimizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAgregar.setBackground(new java.awt.Color(0, 0, 0));
        btnAgregar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Agregar Inventario...");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        tblInventarios.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblInventarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre Corto", "Serie", "Fecha Adq.", "Tipo Adq.", "Área"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblInventarios.setComponentPopupMenu(jPopupMenu1);
        tblInventarios.setRowHeight(31);
        tblInventarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblInventarios.setShowGrid(true);
        tblInventarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblInventariosMousePressed(evt);
            }
        });
        tblInventarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblInventariosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblInventarios);

        txpDetalles.setEditable(false);
        txpDetalles.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txpDetalles.setForeground(new java.awt.Color(255, 255, 255));
        txpDetalles.setPreferredSize(new java.awt.Dimension(62, 490));
        jScrollPane2.setViewportView(txpDetalles);

        lblCant.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCant.setText("0");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Inventario(s)");

        btnVolver.setBackground(new java.awt.Color(0, 0, 0));
        btnVolver.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCant)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCant)
                            .addComponent(jLabel1))
                        .addContainerGap(27, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnMinimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizarActionPerformed
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        this.dispose();
        FormInventario nuevaVentana = new FormInventario("Agregar");
        nuevaVentana.setVisible(true);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void popEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popEditarActionPerformed
        this.dispose();
        FormInventario sigVentana = new FormInventario("Editar", inventariosData.get(tblInventarios.getSelectedRow()));
        sigVentana.setVisible(true);
    }//GEN-LAST:event_popEditarActionPerformed

    private void popEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popEliminarActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(null, 
                                                     "¿Estás seguro de que quieres borrar este Inventario?", 
                                                     "<html><b>CONFIRMACIÓN</b><br></html>", 
                                                     JOptionPane.YES_NO_OPTION, 
                                                     JOptionPane.QUESTION_MESSAGE);
        if (respuesta == JOptionPane.YES_OPTION) {
            int index = tblInventarios.getSelectedRow();
            Inventario i = inventariosData.get(index);

            boolean ok = inventarios.eliminarInventario(i.getId());
            if (ok) {
                JOptionPane.showMessageDialog(this, "El Inventario se ha eliminado", "", JOptionPane.INFORMATION_MESSAGE);
                inventariosData = inventarios.getTodosLosInventarios();
                actualizarTabla(inventariosData);
                actualizarDetalles(null);
            }
            else
                JOptionPane.showMessageDialog(this, "El Inventario no se pudo eliminar", "<html><b>¡ERROR!</b><br></html>", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_popEliminarActionPerformed

    private void tblInventariosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInventariosMousePressed
        int index = tblInventarios.rowAtPoint(tblInventarios.getMousePosition());
        tblInventarios.setRowSelectionInterval(index, index);
        
        actualizarDetalles(inventariosData.get(index));
    }//GEN-LAST:event_tblInventariosMousePressed

    private void tblInventariosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblInventariosKeyReleased
        actualizarDetalles(inventariosData.get(tblInventarios.getSelectedRow()));
    }//GEN-LAST:event_tblInventariosKeyReleased

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        this.dispose();
        Menu nuevaVentana = new Menu();
        nuevaVentana.setVisible(true);
    }//GEN-LAST:event_btnVolverActionPerformed

    private static void moverVentanaConMouse(JFrame frame, JPanel panel) {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                puntoInicial = e.getPoint();
            }
        });
        
        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point pointArrastre = e.getLocationOnScreen();
                frame.setLocation(pointArrastre.x - puntoInicial.x, pointArrastre.y - puntoInicial.y);
            }
        });
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {}

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrudInventarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnMinimizar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCant;
    private javax.swing.JMenuItem popEditar;
    private javax.swing.JMenuItem popEliminar;
    private javax.swing.JTable tblInventarios;
    private javax.swing.JTextPane txpDetalles;
    // End of variables declaration//GEN-END:variables
}
