/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjlp2;

import com.sun.javafx.scene.control.SelectedCellsMap;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ademir
 */
public class Main_UI extends javax.swing.JFrame {

    private ArrayList<String> file_list = new ArrayList<String>();
    
    /**
     * Creates new form Main_UI
     */
    public Main_UI() {
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

        jPanel_images = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_caminho = new javax.swing.JTable();
        jPanel_imageview = new javax.swing.JPanel();
        jLabel_image = new javax.swing.JLabel();
        jPanel1_buttons = new javax.swing.JPanel();
        jButton_CarregarImagens = new javax.swing.JButton();
        jButton2_ProcessarImagens = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel_images.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Segmentação de imagens", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jPanel_images.setLayout(new java.awt.GridLayout());

        jTable_caminho.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable_caminho);
        if (jTable_caminho.getColumnModel().getColumnCount() > 0) {
            jTable_caminho.getColumnModel().getColumn(0).setResizable(false);
        }

        jPanel_images.add(jScrollPane1);

        jPanel_imageview.setLayout(new java.awt.GridLayout());
        jPanel_imageview.add(jLabel_image);

        jPanel_images.add(jPanel_imageview);

        jPanel1_buttons.setLayout(new java.awt.GridLayout());

        jButton_CarregarImagens.setText("Carregar Imagens");
        jButton_CarregarImagens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CarregarImagensActionPerformed(evt);
            }
        });
        jPanel1_buttons.add(jButton_CarregarImagens);

        jButton2_ProcessarImagens.setText("Processar");
        jButton2_ProcessarImagens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2_ProcessarImagensActionPerformed(evt);
            }
        });
        jPanel1_buttons.add(jButton2_ProcessarImagens);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_images, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel1_buttons, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_images, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1_buttons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_CarregarImagensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CarregarImagensActionPerformed
        
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result_fc = fc.showOpenDialog(this);
        
        if (result_fc == JFileChooser.APPROVE_OPTION) {
            
            //Obtendo caminho das imagens e armazenando no arraylist
            File selectedFile = fc.getSelectedFile();
            file_list.add(selectedFile.getAbsolutePath());
            
            //Adicionando caminhos das imagens na tabela
            DefaultTableModel model = (DefaultTableModel) jTable_caminho.getModel();
            model.addRow(new Object[]{selectedFile.getAbsolutePath()});
            
        } else {
            JOptionPane.showConfirmDialog(null, "Error");
        }
    }//GEN-LAST:event_jButton_CarregarImagensActionPerformed

    private void jButton2_ProcessarImagensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2_ProcessarImagensActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable_caminho.getModel();
        
        if(jTable_caminho.getSelectedRow()==-1){
            if(jTable_caminho.getRowCount() == 0){
                JOptionPane.showConfirmDialog(null, "Tabela vazia !");
            } else {
                JOptionPane.showConfirmDialog(null, "Voce nao selecionou algum arquivo");
            }
        } else {
            
            String caminho = model.getValueAt(jTable_caminho.getSelectedRow(), 0).toString();
            JOptionPane.showConfirmDialog(null, caminho);
            Segmantation seg = new Segmantation();
            
            ImageIcon image = seg.segmentar_imagem(caminho);
            Image new_image = image.getImage();
            BufferedImage image_buff = (BufferedImage) new_image;
            int x = jLabel_image.getSize().width;
            int y = jLabel_image.getSize().height;
            int ix = image_buff.getWidth();
            int iy = image_buff.getWidth();
            int dx = 0;
            int dy = 0;
            if (x / y > ix / iy){
                dy = y;
                dx = dy * ix / iy;
            } else {
                dx = x;
                dy = dx * iy / ix;
            }
            ImageIcon icon = new ImageIcon(image_buff.getScaledInstance(dx, dy, image_buff.SCALE_SMOOTH));
            jLabel_image.setIcon(icon);
            
        }
        
        //delete row
        //model.removeRow();
        
        
        
    }//GEN-LAST:event_jButton2_ProcessarImagensActionPerformed

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
            java.util.logging.Logger.getLogger(Main_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_UI().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2_ProcessarImagens;
    private javax.swing.JButton jButton_CarregarImagens;
    private javax.swing.JLabel jLabel_image;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1_buttons;
    private javax.swing.JPanel jPanel_images;
    private javax.swing.JPanel jPanel_imageview;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_caminho;
    // End of variables declaration//GEN-END:variables
}

