/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Funcoes.Functions_UI;
import Funcoes.Segmantation;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ademir
 */
public class ModuleLabeled extends javax.swing.JDialog {

    private ArrayList<String> file_list = new ArrayList<String>();
    
    /**
     * Creates new form ModuleLabeled
     */
    public ModuleLabeled(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        brCarImg_mdSeg = new javax.swing.JButton();
        btSegImg_mdSeg = new javax.swing.JButton();
        btSalImg_mdSeg = new javax.swing.JButton();
        btVoltar_mdSeg = new javax.swing.JButton();
        lbImgOri_mdLab = new javax.swing.JLabel();
        lbImgSeg_mdLab = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCaminho_mdLab = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(4, 0));

        brCarImg_mdSeg.setText("Carregar Imagem");
        brCarImg_mdSeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brCarImg_mdSegActionPerformed(evt);
            }
        });
        jPanel1.add(brCarImg_mdSeg);

        btSegImg_mdSeg.setText("Rotular Imagem");
        btSegImg_mdSeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSegImg_mdSegActionPerformed(evt);
            }
        });
        jPanel1.add(btSegImg_mdSeg);

        btSalImg_mdSeg.setText("Salvar Imagem");
        jPanel1.add(btSalImg_mdSeg);

        btVoltar_mdSeg.setText("Voltar");
        jPanel1.add(btVoltar_mdSeg);

        tbCaminho_mdLab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Caminho"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbCaminho_mdLab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbImgOri_mdLab, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lbImgSeg_mdLab, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbImgOri_mdLab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbImgSeg_mdLab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void brCarImg_mdSegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brCarImg_mdSegActionPerformed
        
        Functions_UI function = new Functions_UI();
        
        //Obtendo caminho das imagens e armazenando no arraylist
        File selectedFile = function.open_filechoose();
        function.file_list.add(selectedFile.getAbsolutePath());

        //Adicionando caminhos das imagens na tabela
        DefaultTableModel model = (DefaultTableModel) tbCaminho_mdLab.getModel();
        model.addRow(new Object[]{selectedFile.getAbsolutePath()});
        
    }//GEN-LAST:event_brCarImg_mdSegActionPerformed

    private void btSegImg_mdSegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSegImg_mdSegActionPerformed
        
        DefaultTableModel model = (DefaultTableModel) tbCaminho_mdLab.getModel();

        Functions_UI function = new Functions_UI();
        
        function.tratamento_de_erro_table(tbCaminho_mdLab);

        String caminho = model.getValueAt(tbCaminho_mdLab.getSelectedRow(), 0).toString();

        Segmantation seg = new Segmantation();

        BufferedImage image_buff = seg.rotular_imagem(caminho);
        
        lbImgSeg_mdLab.setIcon(function.image_resize(image_buff, lbImgSeg_mdLab));
        
        //delete row
        //model.removeRow();
    }//GEN-LAST:event_btSegImg_mdSegActionPerformed

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
            java.util.logging.Logger.getLogger(ModuleLabeled.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModuleLabeled.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModuleLabeled.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModuleLabeled.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ModuleLabeled dialog = new ModuleLabeled(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton brCarImg_mdSeg;
    private javax.swing.JButton btSalImg_mdSeg;
    private javax.swing.JButton btSegImg_mdSeg;
    private javax.swing.JButton btVoltar_mdSeg;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbImgOri_mdLab;
    private javax.swing.JLabel lbImgSeg_mdLab;
    private javax.swing.JTable tbCaminho_mdLab;
    // End of variables declaration//GEN-END:variables
}
