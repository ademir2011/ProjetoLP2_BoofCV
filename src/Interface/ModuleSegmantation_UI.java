/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Classes.Rotulo;
import DAO.DAO;
import Funcoes.Functions_UI;
import Funcoes.TaskSegmentation;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import static org.imgscalr.Scalr.OP_ANTIALIAS;
import static org.imgscalr.Scalr.OP_BRIGHTER;

/**
 *
 * @author Ademir
 */
public class ModuleSegmantation_UI extends javax.swing.JFrame {
    
    Functions_UI function;
    DefaultTableModel model_table;
    DefaultListModel model_list; 
    TaskSegmentation ts;
    File[] selectedFile;
    DAO dao;
    String rotulo_temp;
    private String nome_imagem;
    private float blurlevel;
    private float colorradius;
    private float minsize;
    private boolean ativar_anotacao = false;
    BufferedImage label_image;
    ArrayList<String> searchpostfixed_list;
    DefaultComboBoxModel model_combobox;
    ArrayList<String> temp_list_postfixed;
    
    public void init_face(){
        getContentPane().setBackground(function.getLight_prmary_color());
        getContentPane().setForeground(function.getLight_prmary_color());
    }
    
    public File[] finder( String dirName){
    	File dir = new File(dirName);

    	return dir.listFiles(new FilenameFilter() { 
    	         public boolean accept(File dir, String filename)
    	              { return filename.endsWith(".jpg");}
    	} );

    }
    
    /**
     * Creates new form ModuleSegmantation
     */
    public ModuleSegmantation_UI(java.awt.Frame parent, boolean modal) {
        
        initComponents();
        
        this.setLocationRelativeTo(null);
        
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                init_face();
                btCarImg_mdSeg = function.change_button(btCarImg_mdSeg);
                btTirIma_mdSeg = function.change_button(btTirIma_mdSeg);
                btSegImg_mdSeg = function.change_button(btSegImg_mdSeg);
                btModImg_mdSeg = function.change_button(btModImg_mdSeg);
                btSalImg_mdSeg = function.change_button(btSalImg_mdSeg);
                btVoltar_mdSeg = function.change_button(btVoltar_mdSeg);
                btAddNotes     = function.change_button(btAddNotes, function.getLight_prmary_color(), function.getPrimary_color());
                btSaveInfo_mdSeg = function.change_button(btSaveInfo_mdSeg, function.getLight_prmary_color(), function.getPrimary_color());

                //buttons
                jpButtons_mdSeg.setBackground(function.getLight_prmary_color());

                //images
                jPImages_mdSeg.setBackground(function.getPrimary_color());

                //menubar
                jMenuBar1.setBackground(function.getDark_primary_color()); //

                //table
                jScrollPane1.setBackground(function.getPrimary_color());
                jScrollPane1.getViewport().setBackground(function.getPrimary_color());
                tbCaminho_mdSeg.setBackground(function.getPrimary_color()); 
                tbCaminho_mdSeg.setForeground(function.getLight_prmary_color());

                //infos
                JPupd_info_mdSeg.setBackground(function.getPrimary_color());
                jpInfos_mdSeg.setBackground(function.getPrimary_color());
                jpInfos_mdSeg.setForeground(function.getText_color());

                jPainel_Notes.setBackground(function.getPrimary_color());

                //
                jComboBox2.setBackground(function.getLight_prmary_color()); //
            }
        });
        
        getContentPane().revalidate();
        getContentPane().repaint(); 
        
        this.function       = new Functions_UI();
        this.model_table    = (DefaultTableModel) tbCaminho_mdSeg.getModel();
        this.model_list     = new DefaultListModel();
        jList_notes.setModel(model_list);
        this.blurlevel      = Float.parseFloat(JSpBluLev_mdSeg.getValue().toString());
        this.colorradius    = Float.parseFloat(JSpColRad_mdSeg.getValue().toString());
        this.minsize        = Float.parseFloat(JSpMinSiz_mdSeg.getValue().toString());
        
        try {
            dao                 = new DAO();
        } catch (IOException ex) {
            Logger.getLogger(ModuleSegmantation_UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        jComboBox2.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            
            public void keyReleased(KeyEvent evt){
                
                String prefix = jComboBox2.getEditor().getItem().toString();
                model_list.removeAllElements();
                
                if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                    rotulo_temp = jComboBox2.getEditor().getItem().toString();
                     
                    File file = new File("C:\\"+dao.getDir_name_root()+"\\"+rotulo_temp+"\\");
                    String[] directories = file.list(new FilenameFilter() {
                      @Override
                      public boolean accept(File current, String name) {
                        return new File(current, name).isFile();
                      }
                    });
                    
                    for(int i = 0; i < directories.length; i++){
                         if (directories[i].endsWith((".jpg"))) {
                              model_list.add(model_list.getSize(), directories[i].replace(".jpg", ""));
                         }
                    }
                    
                } else {
                    if(!prefix.isEmpty() && evt.getKeyCode() != KeyEvent.VK_BACK_SPACE){
                        if(dao.searchByPrefix(prefix) != null){
                            model_combobox = new DefaultComboBoxModel();
                            temp_list_postfixed = new ArrayList<String>(dao.searchByPrefix(prefix));
                            System.out.println("PREFIXO DIGITADO: "+prefix+" LISTA DE NOMES: "+temp_list_postfixed);
                            model_combobox.removeAllElements();

                            for(String key : temp_list_postfixed){
                                model_combobox.addElement(key);
                            }

                            jComboBox2.setModel(model_combobox);

                            JTextComponent editor = (JTextComponent) jComboBox2.getEditor().getEditorComponent();
                            editor.setSelectionStart(prefix.length());
                            editor.setSelectionEnd(temp_list_postfixed.get(0).length());

                            jComboBox2.showPopup();
                        }
                    }
                }
            }
        });
         
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpButtons_mdSeg = new javax.swing.JPanel();
        btCarImg_mdSeg = new javax.swing.JButton();
        btTirIma_mdSeg = new javax.swing.JButton();
        btSegImg_mdSeg = new javax.swing.JButton();
        btModImg_mdSeg = new javax.swing.JButton();
        btSalImg_mdSeg = new javax.swing.JButton();
        btVoltar_mdSeg = new javax.swing.JButton();
        jPImages_mdSeg = new javax.swing.JPanel();
        lbImgOri_mdSeg = new javax.swing.JLabel();
        lbImgSeg_mdSeg = new javax.swing.JLabel();
        JPupd_info_mdSeg = new javax.swing.JPanel();
        jPainel_Notes = new javax.swing.JPanel();
        jTfNotes = new javax.swing.JTextField();
        btAddNotes = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList_notes = new javax.swing.JList();
        jComboBox2 = new javax.swing.JComboBox();
        btSaveInfo_mdSeg = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCaminho_mdSeg = new javax.swing.JTable();
        jpInfos_mdSeg = new javax.swing.JPanel();
        JLblurlb_mdSeg = new javax.swing.JLabel();
        JLTotReg_mdSeg = new javax.swing.JLabel();
        JLColRad_mdSeg = new javax.swing.JLabel();
        JSpBluLev_mdSeg = new javax.swing.JSpinner();
        JLMinSiz_mdSeg = new javax.swing.JLabel();
        JSpMinSiz_mdSeg = new javax.swing.JSpinner();
        JSpColRad_mdSeg = new javax.swing.JSpinner();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(63, 81, 181));

        btCarImg_mdSeg.setFont(new java.awt.Font("Corbel", 0, 16)); // NOI18N
        btCarImg_mdSeg.setText("Carregar Imagem");
        btCarImg_mdSeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCarImg_mdSegActionPerformed(evt);
            }
        });

        btTirIma_mdSeg.setFont(new java.awt.Font("Corbel", 0, 16)); // NOI18N
        btTirIma_mdSeg.setText("Tirar Imagem");
        btTirIma_mdSeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTirIma_mdSegActionPerformed(evt);
            }
        });

        btSegImg_mdSeg.setFont(new java.awt.Font("Corbel", 0, 16)); // NOI18N
        btSegImg_mdSeg.setText("Segmentar");
        btSegImg_mdSeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSegImg_mdSegActionPerformed(evt);
            }
        });

        btModImg_mdSeg.setFont(new java.awt.Font("Corbel", 0, 16)); // NOI18N
        btModImg_mdSeg.setText("Modular");
        btModImg_mdSeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModImg_mdSegActionPerformed(evt);
            }
        });

        btSalImg_mdSeg.setFont(new java.awt.Font("Corbel", 0, 16)); // NOI18N
        btSalImg_mdSeg.setText("Salvar Imagem");
        btSalImg_mdSeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalImg_mdSegActionPerformed(evt);
            }
        });

        btVoltar_mdSeg.setFont(new java.awt.Font("Corbel", 0, 16)); // NOI18N
        btVoltar_mdSeg.setText("Voltar");
        btVoltar_mdSeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltar_mdSegActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpButtons_mdSegLayout = new javax.swing.GroupLayout(jpButtons_mdSeg);
        jpButtons_mdSeg.setLayout(jpButtons_mdSegLayout);
        jpButtons_mdSegLayout.setHorizontalGroup(
            jpButtons_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btCarImg_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btTirIma_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btSegImg_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btModImg_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btSalImg_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btVoltar_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jpButtons_mdSegLayout.setVerticalGroup(
            jpButtons_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpButtons_mdSegLayout.createSequentialGroup()
                .addComponent(btCarImg_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btTirIma_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(btSegImg_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(btModImg_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(btSalImg_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(btVoltar_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPImages_mdSegLayout = new javax.swing.GroupLayout(jPImages_mdSeg);
        jPImages_mdSeg.setLayout(jPImages_mdSegLayout);
        jPImages_mdSegLayout.setHorizontalGroup(
            jPImages_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPImages_mdSegLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lbImgOri_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbImgSeg_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPImages_mdSegLayout.setVerticalGroup(
            jPImages_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPImages_mdSegLayout.createSequentialGroup()
                .addGroup(jPImages_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbImgOri_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbImgSeg_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

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

        jComboBox2.setEditable(true);
        jComboBox2.setPreferredSize(new java.awt.Dimension(120, 20));
        jComboBox2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox2KeyReleased(evt);
            }
        });

        btSaveInfo_mdSeg.setText("SAVE");
        btSaveInfo_mdSeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveInfo_mdSegActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPainel_NotesLayout = new javax.swing.GroupLayout(jPainel_Notes);
        jPainel_Notes.setLayout(jPainel_NotesLayout);
        jPainel_NotesLayout.setHorizontalGroup(
            jPainel_NotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPainel_NotesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPainel_NotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addGroup(jPainel_NotesLayout.createSequentialGroup()
                        .addComponent(jTfNotes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btAddNotes, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btSaveInfo_mdSeg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPainel_NotesLayout.setVerticalGroup(
            jPainel_NotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPainel_NotesLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPainel_NotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTfNotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAddNotes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btSaveInfo_mdSeg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tbCaminho_mdSeg.setBackground(new java.awt.Color(33, 33, 33));
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
        tbCaminho_mdSeg.setName(""); // NOI18N
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

        JLblurlb_mdSeg.setText("BlurLevel");

        JLTotReg_mdSeg.setText("Total de Regioes: ");

        JLColRad_mdSeg.setText("ColorRadius");

        JSpBluLev_mdSeg.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.99f), Float.valueOf(0.0f), Float.valueOf(2.0f), Float.valueOf(0.01f)));
        JSpBluLev_mdSeg.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        JSpBluLev_mdSeg.setRequestFocusEnabled(false);
        JSpBluLev_mdSeg.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                JSpBluLev_mdSegStateChanged(evt);
            }
        });

        JLMinSiz_mdSeg.setText("MinSize");

        JSpMinSiz_mdSeg.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(1000.0f), Float.valueOf(0.0f), Float.valueOf(1500.0f), Float.valueOf(1.0f)));
        JSpMinSiz_mdSeg.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                JSpMinSiz_mdSegStateChanged(evt);
            }
        });

        JSpColRad_mdSeg.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(40.0f), Float.valueOf(0.0f), Float.valueOf(150.0f), Float.valueOf(1.0f)));
        JSpColRad_mdSeg.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                JSpColRad_mdSegStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jpInfos_mdSegLayout = new javax.swing.GroupLayout(jpInfos_mdSeg);
        jpInfos_mdSeg.setLayout(jpInfos_mdSegLayout);
        jpInfos_mdSegLayout.setHorizontalGroup(
            jpInfos_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jpInfos_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpInfos_mdSegLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jpInfos_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(JLTotReg_mdSeg)
                        .addComponent(JLMinSiz_mdSeg)
                        .addComponent(JLblurlb_mdSeg)
                        .addComponent(JLColRad_mdSeg))
                    .addGap(110, 110, 110)
                    .addGroup(jpInfos_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(JSpMinSiz_mdSeg)
                        .addComponent(JSpColRad_mdSeg)
                        .addComponent(JSpBluLev_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jpInfos_mdSegLayout.setVerticalGroup(
            jpInfos_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jpInfos_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpInfos_mdSegLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jpInfos_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JLblurlb_mdSeg)
                        .addComponent(JSpBluLev_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jpInfos_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JLColRad_mdSeg)
                        .addComponent(JSpColRad_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jpInfos_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JLMinSiz_mdSeg)
                        .addComponent(JSpMinSiz_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                    .addComponent(JLTotReg_mdSeg)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout JPupd_info_mdSegLayout = new javax.swing.GroupLayout(JPupd_info_mdSeg);
        JPupd_info_mdSeg.setLayout(JPupd_info_mdSegLayout);
        JPupd_info_mdSegLayout.setHorizontalGroup(
            JPupd_info_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPupd_info_mdSegLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpInfos_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPainel_Notes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        JPupd_info_mdSegLayout.setVerticalGroup(
            JPupd_info_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPupd_info_mdSegLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPupd_info_mdSegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpInfos_mdSeg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPainel_Notes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jMenuBar1.setBackground(new java.awt.Color(40, 63, 159));
        jMenuBar1.setBorder(null);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPupd_info_mdSeg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpButtons_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPImages_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpButtons_mdSeg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPImages_mdSeg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(JPupd_info_mdSeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
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

    
    private void btAddNotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddNotesActionPerformed
        
        
        if(function.tratamento_de_erro_list(jTfNotes, ativar_anotacao, ts.getMultiple_selected_regions(), dao)){
            if(ts.getMultiple_selected_regions().size() > 0){
                String textToNote = jTfNotes.getText();
                model_list.add(model_list.getSize(), textToNote);
                
                Rotulo rotulo = new Rotulo(model_list.getSize(), blurlevel, colorradius, minsize, textToNote, ts.getMultiple_selected_regions(), ts.getOriginal_image(), ts.getImage_lightened(), ts.getNome_imagem());
                dao.adicionar_temp(rotulo);
                
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
            System.out.println("Palavra: "+palavra_selecionada+" | indice da palavra na lista:"+jList_notes.getSelectedIndex()+" ArrayList:"+dao.getRmapbyName(palavra_selecionada));
            try {
                lbImgSeg_mdSeg.setIcon(new ImageIcon(dao.getBufImagebyName(rotulo_temp, palavra_selecionada)));
            } catch (IOException ex) {
                Logger.getLogger(ModuleSegmantation_UI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_jList_notesMouseClicked

    private void jComboBox2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox2KeyReleased
       
    }//GEN-LAST:event_jComboBox2KeyReleased

    private void btVoltar_mdSegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltar_mdSegActionPerformed
        dispose();
    }//GEN-LAST:event_btVoltar_mdSegActionPerformed

    private void btSalImg_mdSegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalImg_mdSegActionPerformed

        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Salve a imagem rotulada");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".jpg", "jpg");
        String defaultFileName = model_list.getElementAt(jList_notes.getSelectedIndex()).toString() +".jpg";
        jfc.setSelectedFile( new File(defaultFileName) );
        jfc.setFileFilter(filter);
        int userSelection = jfc.showSaveDialog(jfc);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = jfc.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            String palavra_selecionada = model_list.getElementAt(jList_notes.getSelectedIndex()).toString();
            try {
                ImageIO.write(dao.getBufImagebyName(rotulo_temp, palavra_selecionada), "jpg", jfc.getSelectedFile());
            } catch (IOException ex) {
                Logger.getLogger(ModuleSegmantation_UI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btSalImg_mdSegActionPerformed

    private void btModImg_mdSegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModImg_mdSegActionPerformed
        

        if(function.tratamento_de_erro_table(tbCaminho_mdSeg)){
            String caminho = model_table.getValueAt(tbCaminho_mdSeg.getSelectedRow(), 0).toString();

            function.setarImageLabel(lbImgSeg_mdSeg, ts.rotular_imagem(caminho, blurlevel, colorradius, minsize));

            JLTotReg_mdSeg.setText("Total de regioes: "+ts.getSegmented_regions());

            
        }
    }//GEN-LAST:event_btModImg_mdSegActionPerformed

    
    
    private void btSegImg_mdSegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSegImg_mdSegActionPerformed
        
        function.pb_execute(50);
        
        ts = new TaskSegmentation();
        
        if(function.tratamento_de_erro_table(tbCaminho_mdSeg)){

            String caminho = model_table.getValueAt(tbCaminho_mdSeg.getSelectedRow(), 0).toString();

            function.resize_image(400, 400, caminho);
            
            new Thread(){
                public void run(){
                    function.setarImageLabel(lbImgSeg_mdSeg, ts.segmentar_imagem(caminho, blurlevel, colorradius, minsize) );
                }
            }.start();
            
            ativar_anotacao = true;
            
            File f = new File(caminho);
            nome_imagem = f.getName();
            nome_imagem = nome_imagem.replace(".jpg","");
            System.out.println(""+nome_imagem);
            ts.setNome_imagem(nome_imagem);
            ts.ativa_selecao(lbImgSeg_mdSeg);
        }
    }//GEN-LAST:event_btSegImg_mdSegActionPerformed

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

    private void btCarImg_mdSegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCarImg_mdSegActionPerformed
        if((selectedFile = function.open_filechoose()) != null){
            if(function.tratamento_de_erro_carregamento(model_table, selectedFile)){
                for(int i = 0; i < selectedFile.length; i++){
                    function.file_list.add(selectedFile[i].getAbsolutePath());
                    System.out.println(""+function.file_list);
                    model_table.addRow(new Object[]{selectedFile[i].getAbsolutePath()});
                }
            }
        }
    }//GEN-LAST:event_btCarImg_mdSegActionPerformed

    private void btSaveInfo_mdSegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveInfo_mdSegActionPerformed
        
        for(Rotulo key : dao.getRotulo_temp_list()){
            dao.adicionar(key);
        }
        
        dao.remove_temp();
        model_list.removeAllElements();
    }//GEN-LAST:event_btSaveInfo_mdSegActionPerformed

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
    private javax.swing.JButton btAddNotes;
    private javax.swing.JButton btCarImg_mdSeg;
    private javax.swing.JButton btModImg_mdSeg;
    private javax.swing.JButton btSalImg_mdSeg;
    private javax.swing.JButton btSaveInfo_mdSeg;
    private javax.swing.JButton btSegImg_mdSeg;
    private javax.swing.JButton btTirIma_mdSeg;
    private javax.swing.JButton btVoltar_mdSeg;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JList jList_notes;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPImages_mdSeg;
    private javax.swing.JPanel jPainel_Notes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTfNotes;
    private javax.swing.JPanel jpButtons_mdSeg;
    private javax.swing.JPanel jpInfos_mdSeg;
    private javax.swing.JLabel lbImgOri_mdSeg;
    private javax.swing.JLabel lbImgSeg_mdSeg;
    private javax.swing.JTable tbCaminho_mdSeg;
    // End of variables declaration//GEN-END:variables

}
