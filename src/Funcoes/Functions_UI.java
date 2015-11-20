/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcoes;

import Classes.Rotulo;
import DAO.DAO;
import Interface.ModuleSegmantation_UI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ademir
 */
public class Functions_UI {
    
    public ArrayList<String> file_list = new ArrayList<String>();
    
    Color dark_primary_color    = new Color(97,97,97);
    Color primary_color         = new Color(158,158,158);
    Color accent_color          = new Color(70, 77, 87);
    Color text_color            = new Color(33,33,33);
    Color primary_text          = new Color(33,33,33);
    Color light_prmary_color    = new Color(245,245,245);
    
    public Functions_UI(){
    }
    
    public File[] open_filechoose(){
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("C:/Users/Ademir/Desktop/Images"));
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setMultiSelectionEnabled(true);
        int result_fc = fc.showOpenDialog(fc);
        
        if (result_fc == JFileChooser.APPROVE_OPTION) {
            System.out.println("File choose susseful! "+fc.getSelectedFiles());
            return fc.getSelectedFiles();
        } else if (result_fc == JFileChooser.CANCEL_OPTION) {
            System.out.println("Operacao cancelada com sucesso");
        } else {
            JOptionPane.showConfirmDialog(null, "Error");
        }
        return null;
    }
    
    public boolean tratamento_de_erro_carregamento(DefaultTableModel model, File[] selectedFile){
        if(model.getRowCount() > 25) {
            JOptionPane.showMessageDialog(null, "Voce atingiu o numero maximo de imagens");
            return false;
        }
        
        for (String key : file_list) {
            for(int i = 0; i < selectedFile.length; i++){
                if(selectedFile[i].getAbsolutePath().equals(key)){
                    JOptionPane.showMessageDialog(null, "O arquivo: "+selectedFile[i]+" ja existente, escolha outro !");
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean tratamento_de_erro_table(JTable tbCaminho_mdX){
        if(tbCaminho_mdX.getSelectedRow()==-1){
            if(tbCaminho_mdX.getRowCount() == 0){
                JOptionPane.showMessageDialog(null, "Nenhuma imagem selecionada");
                return false;
            } else {
                JOptionPane.showMessageDialog(null, "Voce nao selecionou algum arquivo");
                return false;
            }
        } else {
            System.out.println("Nenhum erro encontrado");
            return true;
        }
    }
    
    public boolean tratamento_de_erro_list(JTextField jTfNotes, boolean ativar_anotacao, ArrayList<Integer> multiple_selected_regions, DAO dao){
        
        if(ativar_anotacao == false) {
            JOptionPane.showMessageDialog(null, "Nenhuma imagem segmentada");
            return false;
        } else if(jTfNotes.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Nenhum texto digitado");
            return false;
        }
        
        for (Rotulo key : dao.getRotulo_list()) {
            System.out.println("TESTE: "+multiple_selected_regions);
            if(key.getMultiple_selected_regions().equals(multiple_selected_regions)){
                JOptionPane.showMessageDialog(null, "Regioes já selecionadas com o nome: "+key.getNome_rotulo());
                return false;
            }
        }
        
        return true;
    }
    
    public ImageIcon image_resize(BufferedImage image_buff, JLabel lbImgSeg_mdX){
       
        int x = lbImgSeg_mdX.getSize().width;
        int y = lbImgSeg_mdX.getSize().height;
        
        return (new ImageIcon(image_buff.getScaledInstance(x, y, image_buff.SCALE_SMOOTH)) );
    }
    
    public void setarImageLabel(JLabel label, BufferedImage image_buff){
        label.setIcon(new ImageIcon(image_buff.getScaledInstance(label.getWidth(), label.getHeight(), image_buff.SCALE_SMOOTH)));
    }
    
    public void resize_image(int width, int height, String caminho){
        BufferedImage originalImage;
        try {
            originalImage = ImageIO.read(new File(caminho));
            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            resizedImage.getGraphics().drawImage(originalImage, 0, 0, width, height, null);
            ImageIO.write(resizedImage, "jpg", new File(caminho));
        } catch (IOException ex) {
            Logger.getLogger(ModuleSegmantation_UI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public JButton change_button(JButton bt){
        bt.setBackground(primary_color);
        bt.setForeground(light_prmary_color);
        
        bt.setContentAreaFilled(false);
        bt.setOpaque(true);
        return bt;
    }
    
    public JButton change_button(JButton bt, Color c){
        bt.setBackground(c);
        bt.setForeground(light_prmary_color);
        
        bt.setContentAreaFilled(false);
        bt.setOpaque(true);
        return bt;
    }
    
    public JButton change_button(JButton bt, Color c, Color c1){
        bt.setBackground(c);
        bt.setForeground(c1);
        
        bt.setContentAreaFilled(false);
        bt.setOpaque(true);
        return bt;
    }

    public void pb_execute(int time){
        new Thread(){
            public void run(){
                
                JFrame frame = new JFrame("Barra de Progresso");
                frame.setSize(400, 75);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
                Dimension frameSize = frame.getSize(); 
                if (frameSize.height > screenSize.height){frameSize.height = screenSize.height; } 
                if (frameSize.width > screenSize.width){frameSize.width = screenSize.width; } 
                frame.setLocation((screenSize.width - frameSize.width) / 2,(screenSize.height - frameSize.height) / 2);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                Container content = frame.getContentPane();
                JProgressBar progressBar = new JProgressBar();
                content.add(progressBar, BorderLayout.CENTER);
                progressBar.setVisible(true);
                progressBar.setStringPainted(true);
                for(int i = 0; i <= 100; i++){
                    try {
                        sleep(time);
                        progressBar.setValue(i);
                        frame.setVisible(true);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ModuleSegmantation_UI.class.getName()).log(Level.SEVERE, null, ex);
                    }       
                }
                frame.dispose();
            }
        }.start();
    }
    
    public ArrayList<String> getFile_list() {
        return file_list;
    }

    public void setFile_list(ArrayList<String> file_list) {
        this.file_list = file_list;
    }

    public Color getDark_primary_color() {
        return dark_primary_color;
    }

    public void setDark_primary_color(Color dark_primary_color) {
        this.dark_primary_color = dark_primary_color;
    }

    public Color getPrimary_color() {
        return primary_color;
    }

    public void setPrimary_color(Color primary_color) {
        this.primary_color = primary_color;
    }

    public Color getAccent_color() {
        return accent_color;
    }

    public void setAccent_color(Color accent_color) {
        this.accent_color = accent_color;
    }

    public Color getText_color() {
        return text_color;
    }

    public void setText_color(Color text_color) {
        this.text_color = text_color;
    }

    public Color getPrimary_text() {
        return primary_text;
    }

    public void setPrimary_text(Color primary_text) {
        this.primary_text = primary_text;
    }

    public Color getLight_prmary_color() {
        return light_prmary_color;
    }

    public void setLight_prmary_color(Color light_prmary_color) {
        this.light_prmary_color = light_prmary_color;
    }
    
    
    
}
