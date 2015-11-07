/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Classes.Rotulo;
import DAO.RotuloDAO;
import Funcoes.Functions_UI;
import Funcoes.TaskSegmentation;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ademir
 */
public class ModuleSegmantation_UI extends javax.swing.JDialog {
    
    Functions_UI function;
    DefaultTableModel model_table;
    DefaultListModel model_list; 
    TaskSegmentation seg;
    File selectedFile;
    RotuloDAO rotuloDAO;
    private float blurlevel;
    private float colorradius;
    private float minsize;
    private boolean ativar_anotacao = false;
    BufferedImage label_image;
    
    
    /**
     * Creates new form ModuleSegmantation
     */
    public ModuleSegmantation_UI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        initComponents();
        this.function = new Functions_UI();
        this.model_table = (DefaultTableModel) tbCaminho_mdSeg.getModel();
        this.model_list = new DefaultListModel();
        jList_notes.setModel(model_list);
        this.blurlevel = Float.parseFloat(JSpBluLev_mdSeg.getValue().toString());
        this.colorradius = Float.parseFloat(JSpColRad_mdSeg.getValue().toString());
        this.minsize = Float.parseFloat(JSpMinSiz_mdSeg.getValue().toString());
        seg = new TaskSegmentation();
        rotuloDAO = new RotuloDAO();
         
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
        btTirIma_mdSeg = new javax.swing.JButton();
        btSegImg_mdSeg = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btSalImg_mdSeg = new javax.swing.JButton();
        btVoltar_mdSeg = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCaminho_mdSeg = new javax.swing.JTable();
        jPImages_mdSeg = new javax.swing.JPanel();
        lbImgOri_mdSeg = new javax.swing.JLabel();
        lbImgSeg_mdSeg = new javax.swing.JLabel();
        JPupd_info_mdSeg = new javax.swing.JPanel();
        JLblurlb_mdSeg = new javax.swing.JLabel();
        JLColRad_mdSeg = new javax.swing.JLabel();
        JLMinSiz_mdSeg = new javax.swing.JLabel();
        JSpBluLev_mdSeg = new javax.swing.JSpinner();
        JSpColRad_mdSeg = new javax.swing.JSpinner();
        JSpMinSiz_mdSeg = new javax.swing.JSpinner();
        JLTotReg_mdSeg = new javax.swing.JLabel();
        jPb_mdSeg = new javax.swing.JProgressBar();
        jPainel_Notes = new javax.swing.JPanel();
        jTfNotes = new javax.swing.JTextField();
        btAddNotes = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList_notes = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(6, 0));

        brCarImg_mdSeg.setText("Carregar Imagem");
        brCarImg_mdSeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brCarImg_mdSegActionPerformed(evt);
            }
        });
        jPanel1.add(brCarImg_mdSeg);

        btTirIma_mdSeg.setText("Tirar Imagem");
        btTirIma_mdSeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTirIma_mdSegActionPerformed(evt);
            }
        });
        jPanel1.add(btTirIma_mdSeg);

        btSegImg_mdSeg.setText("Segmentar Imagem");
        btSegImg_mdSeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSegImg_mdSegActionPerformed(evt);
            }
        });
        jPanel1.add(btSegImg_mdSeg);

        jButton1.setText("Modular Imagem");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        btSalImg_mdSeg.setText("Salvar Imagem");
        btSalImg_mdSeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalImg_mdSegActionPerformed(evt);
            }
        });
        jPanel1.add(btSalImg_mdSeg);

        btVoltar_mdSeg.setText("Voltar");
        btVoltar_mdSeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltar_mdSegActionPerformed(evt);
            }
        });
        jPanel1.add(btVoltar_mdSeg);

        tbCaminho_mdSeg.setModel(new javax.swing.table.DefaultTableModel(
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
        tbCaminho_mdSeg.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbCaminho_mdSeg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCaminho_mdSegMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbCaminho_mdSeg);
        if (tbCaminho_mdSeg.getColumnModel().getColumnCount() > 0) {
            tbCaminho_mdSeg.getColumnModel().getColumn(0).setResizable(false);
        }

        javax.swing.GroupLayout jPImages_mdSegLayout = new javax.swing.GroupLayout(jPImages_mdSeg);
        jPImages_mdSeg.setLayout(jPImages_mdSegLayout);
        jPImages_mdSegLayout.setHorizontalGroup(
            jPImages_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPImages_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPImages_mdSegLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lbImgOri_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbImgSeg_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPImages_mdSegLayout.setVerticalGroup(
            jPImages_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 411, Short.MAX_VALUE)
            .addGroup(jPImages_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPImages_mdSegLayout.createSequentialGroup()
                    .addGroup(jPImages_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbImgOri_mdSeg, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                        .addComponent(lbImgSeg_mdSeg, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        JLblurlb_mdSeg.setText("BlurLevel");

        JLColRad_mdSeg.setText("ColorRadius");

        JLMinSiz_mdSeg.setText("MinSize");

        JSpBluLev_mdSeg.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.99f), Float.valueOf(0.0f), Float.valueOf(2.0f), Float.valueOf(0.01f)));
        JSpBluLev_mdSeg.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        JSpBluLev_mdSeg.setRequestFocusEnabled(false);
        JSpBluLev_mdSeg.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                JSpBluLev_mdSegStateChanged(evt);
            }
        });

        JSpColRad_mdSeg.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(40.0f), Float.valueOf(0.0f), Float.valueOf(150.0f), Float.valueOf(1.0f)));
        JSpColRad_mdSeg.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                JSpColRad_mdSegStateChanged(evt);
            }
        });

        JSpMinSiz_mdSeg.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(1000.0f), Float.valueOf(0.0f), Float.valueOf(1500.0f), Float.valueOf(1.0f)));
        JSpMinSiz_mdSeg.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                JSpMinSiz_mdSegStateChanged(evt);
            }
        });

        JLTotReg_mdSeg.setText("Total de Regioes: ");

        javax.swing.GroupLayout JPupd_info_mdSegLayout = new javax.swing.GroupLayout(JPupd_info_mdSeg);
        JPupd_info_mdSeg.setLayout(JPupd_info_mdSegLayout);
        JPupd_info_mdSegLayout.setHorizontalGroup(
            JPupd_info_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPupd_info_mdSegLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPupd_info_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JLTotReg_mdSeg)
                    .addComponent(JLMinSiz_mdSeg)
                    .addComponent(JLblurlb_mdSeg)
                    .addComponent(JLColRad_mdSeg))
                .addGroup(JPupd_info_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JSpMinSiz_mdSeg)
                    .addComponent(JSpColRad_mdSeg)
                    .addComponent(JSpBluLev_mdSeg))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        JPupd_info_mdSegLayout.setVerticalGroup(
            JPupd_info_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPupd_info_mdSegLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPupd_info_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLblurlb_mdSeg)
                    .addComponent(JSpBluLev_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPupd_info_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLColRad_mdSeg)
                    .addComponent(JSpColRad_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPupd_info_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLMinSiz_mdSeg)
                    .addComponent(JSpMinSiz_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JLTotReg_mdSeg)
                .addContainerGap())
        );

        jPb_mdSeg.setStringPainted(true);

        jPainel_Notes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Anotações", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP));

        btAddNotes.setText("+");
        btAddNotes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddNotesActionPerformed(evt);
            }
        });

        jList_notes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList_notes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList_notesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList_notes);

        javax.swing.GroupLayout jPainel_NotesLayout = new javax.swing.GroupLayout(jPainel_Notes);
        jPainel_Notes.setLayout(jPainel_NotesLayout);
        jPainel_NotesLayout.setHorizontalGroup(
            jPainel_NotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPainel_NotesLayout.createSequentialGroup()
                .addComponent(jTfNotes, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btAddNotes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPainel_NotesLayout.setVerticalGroup(
            jPainel_NotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPainel_NotesLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPainel_NotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTfNotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAddNotes)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPb_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(JPupd_info_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPainel_Notes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPImages_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPImages_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jPb_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(JPupd_info_mdSeg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPainel_Notes, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void brCarImg_mdSegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brCarImg_mdSegActionPerformed
        if((selectedFile = function.open_filechoose()) != null){
            if(function.tratamento_de_erro_carregamento(model_table, selectedFile.getAbsolutePath())){
                function.file_list.add(selectedFile.getAbsolutePath());
                System.out.println(""+function.file_list);
                model_table.addRow(new Object[]{selectedFile.getAbsolutePath()});
            }
        }
    }//GEN-LAST:event_brCarImg_mdSegActionPerformed

    private void btSegImg_mdSegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSegImg_mdSegActionPerformed
        
        if(function.tratamento_de_erro_table(tbCaminho_mdSeg)){
            
            String caminho = model_table.getValueAt(tbCaminho_mdSeg.getSelectedRow(), 0).toString();
            
            BufferedImage image_buff = seg.segmentar_imagem(caminho, blurlevel, colorradius, minsize);
            
            function.setarImageLabel(lbImgSeg_mdSeg, seg.getSegmented_image());
           
            ativar_anotacao = true;
            
            //funções que irão rodar constantemente
            seg.ativa_selecao(lbImgSeg_mdSeg);
        }
    }//GEN-LAST:event_btSegImg_mdSegActionPerformed

    private void btVoltar_mdSegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltar_mdSegActionPerformed
        dispose();
    }//GEN-LAST:event_btVoltar_mdSegActionPerformed

    private void tbCaminho_mdSegMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCaminho_mdSegMouseClicked
      
        if(function.tratamento_de_erro_table(tbCaminho_mdSeg)){
            try {
                String caminho = model_table.getValueAt(tbCaminho_mdSeg.getSelectedRow(), 0).toString();
                label_image = ImageIO.read(new File(caminho));
                function.setarImageLabel(lbImgOri_mdSeg, label_image); 
            } catch (IOException ex) {
                System.out.println("Erro ao setar imagem");
                Logger.getLogger(ModuleSegmantation_UI.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
        
    }//GEN-LAST:event_tbCaminho_mdSegMouseClicked

    private void btSalImg_mdSegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalImg_mdSegActionPerformed
        //ImageIO.write(bi, "png", outputfile);
    }//GEN-LAST:event_btSalImg_mdSegActionPerformed

    
    private void JSpBluLev_mdSegStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_JSpBluLev_mdSegStateChanged
        this.blurlevel = Float.parseFloat(JSpBluLev_mdSeg.getValue().toString());
        System.out.println("Blur Level: "+this.blurlevel);
    }//GEN-LAST:event_JSpBluLev_mdSegStateChanged

    private void JSpColRad_mdSegStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_JSpColRad_mdSegStateChanged
        this.colorradius = Float.parseFloat(JSpColRad_mdSeg.getValue().toString());
        System.out.println("Color Radius: "+this.colorradius);
    }//GEN-LAST:event_JSpColRad_mdSegStateChanged

    private void JSpMinSiz_mdSegStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_JSpMinSiz_mdSegStateChanged
        this.minsize = Float.parseFloat(JSpMinSiz_mdSeg.getValue().toString());
        System.out.println("Min Size: "+this.minsize);
    }//GEN-LAST:event_JSpMinSiz_mdSegStateChanged

    private void btTirIma_mdSegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTirIma_mdSegActionPerformed
        
        if(function.tratamento_de_erro_table(tbCaminho_mdSeg)){
            String caminho_remove = model_table.getValueAt(tbCaminho_mdSeg.getSelectedRow(), 0).toString();
            
            Iterator<String> it = function.file_list.iterator();
                    
            while(it.hasNext()){
                String key = it.next();
                if(key.equals(caminho_remove)){
                    System.out.println("cancela da arraylist");
                    model_table.removeRow(tbCaminho_mdSeg.getSelectedRow());
                    function.file_list.remove(key);
                    System.out.println(""+function.file_list.toString());
                }
            }
        }
    }//GEN-LAST:event_btTirIma_mdSegActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jPb_mdSeg.setValue(0);
                
        if(function.tratamento_de_erro_table(tbCaminho_mdSeg)){
            String caminho = model_table.getValueAt(tbCaminho_mdSeg.getSelectedRow(), 0).toString();

            BufferedImage image_buff = seg.rotular_imagem(caminho, blurlevel, colorradius, minsize);
            
            function.setarImageLabel(lbImgSeg_mdSeg, image_buff);

            JLTotReg_mdSeg.setText("Total de regioes: "+seg.getSegmented_regions());
            
            jPb_mdSeg.setValue(100);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btAddNotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddNotesActionPerformed
        
        if(function.tratamento_de_erro_list(jTfNotes, ativar_anotacao, seg.getMultiple_selected_regions(), rotuloDAO)){
            if(lbImgSeg_mdSeg.getIcon().equals(seg.getImage_lightened_icon())){
                String textToNote = jTfNotes.getText();
                function.list_list.add(textToNote);
                model_list.add(model_list.getSize(), textToNote);
                rotuloDAO.adicionar(new Rotulo(model_list.getSize(), textToNote, seg.getMultiple_selected_regions(), seg.getImage_lightened()));
                System.out.println(function.list_list.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Rotulo nao selecionado!");
            }
        }
        
        jTfNotes.setText("");
        
    }//GEN-LAST:event_btAddNotesActionPerformed

    private void jList_notesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList_notesMouseClicked
        
        if(jList_notes.getSelectedIndex() < 0){
            System.out.println("Nenhuma palavra selecionada");
        } else {
            String palavra_selecionada = model_list.getElementAt(jList_notes.getSelectedIndex()).toString();
            System.out.println("Palavra: "+palavra_selecionada+" | indice da palavra na lista:"+jList_notes.getSelectedIndex()+" ArrayList:"+rotuloDAO.getRmapofName(palavra_selecionada));
            lbImgSeg_mdSeg.setIcon(new ImageIcon(rotuloDAO.getBufImageofName(palavra_selecionada)));
        }
        
    }//GEN-LAST:event_jList_notesMouseClicked

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
            java.util.logging.Logger.getLogger(ModuleSegmantation_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModuleSegmantation_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModuleSegmantation_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModuleSegmantation_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ModuleSegmantation_UI dialog = new ModuleSegmantation_UI(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel JLColRad_mdSeg;
    private javax.swing.JLabel JLMinSiz_mdSeg;
    private javax.swing.JLabel JLTotReg_mdSeg;
    private javax.swing.JLabel JLblurlb_mdSeg;
    private javax.swing.JPanel JPupd_info_mdSeg;
    private javax.swing.JSpinner JSpBluLev_mdSeg;
    private javax.swing.JSpinner JSpColRad_mdSeg;
    private javax.swing.JSpinner JSpMinSiz_mdSeg;
    private javax.swing.JButton brCarImg_mdSeg;
    private javax.swing.JButton btAddNotes;
    private javax.swing.JButton btSalImg_mdSeg;
    private javax.swing.JButton btSegImg_mdSeg;
    private javax.swing.JButton btTirIma_mdSeg;
    private javax.swing.JButton btVoltar_mdSeg;
    private javax.swing.JButton jButton1;
    private javax.swing.JList jList_notes;
    private javax.swing.JPanel jPImages_mdSeg;
    private javax.swing.JPanel jPainel_Notes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jPb_mdSeg;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTfNotes;
    private javax.swing.JLabel lbImgOri_mdSeg;
    private javax.swing.JLabel lbImgSeg_mdSeg;
    private javax.swing.JTable tbCaminho_mdSeg;
    // End of variables declaration//GEN-END:variables


}
