/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcoes;

import Classes.Rotulo;
import DAO.DAO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ademir
 */
public class Functions_UI {
    
    public ArrayList<String> file_list = new ArrayList<String>();
    
    public Functions_UI(){
    }
    
    public File open_filechoose(){
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("C:/Users/Ademir/Desktop/Images"));
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result_fc = fc.showOpenDialog(fc);
        
        if (result_fc == JFileChooser.APPROVE_OPTION) {
            System.out.println("File choose susseful!");
            return fc.getSelectedFile();
        } else if (result_fc == JFileChooser.CANCEL_OPTION) {
            System.out.println("Operacao cancelada com sucesso");
        } else {
            JOptionPane.showConfirmDialog(null, "Error");
        }
        return null;
    }
    
    public boolean tratamento_de_erro_carregamento(DefaultTableModel model, String selectedFile){
        if(model.getRowCount() > 25) {
            JOptionPane.showMessageDialog(null, "Voce atingiu o numero maximo de imagens");
            return false;
        }
        
        for (String key : file_list) {
            if(selectedFile.equals(key)){
                JOptionPane.showMessageDialog(null, "Arquivo ja existente, escolha outro !");
                return false;
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
        
        for(Rotulo key : dao.getRotulo_list()){
            if(jTfNotes.getText().equals(key.getNome())){
                JOptionPane.showMessageDialog(null, "Nome ja inserido");
                return false;
            }
        }
        
        for (Rotulo key : dao.getRotulo_list()) {
            System.out.println("TESTE: "+multiple_selected_regions);
            if(key.getMultiple_selected_regions().equals(multiple_selected_regions)){
                JOptionPane.showMessageDialog(null, "Regioes já selecionadas com o nome: "+key.getNome());
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
    
}
