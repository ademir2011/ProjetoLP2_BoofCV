/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcoes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
        fc.setCurrentDirectory(new File("C:/Users/Ademir/Desktop/Images/"));
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
    
    public ImageIcon image_resize(BufferedImage image_buff, JLabel lbImgSeg_mdX){
        
        int x = lbImgSeg_mdX.getSize().width;
        int y = lbImgSeg_mdX.getSize().height;
        int ix = image_buff.getWidth();
        int iy = image_buff.getHeight();
        int dx = 0;
        int dy = 0;
        if (x / y > ix / iy){
            dy = y;
            dx = dy * ix / iy;
        } else {
            dx = x;
            dy = dx * iy / ix;
        }

        return (new ImageIcon(image_buff.getScaledInstance(dx, dy, image_buff.SCALE_SMOOTH)) );
    }
    
}
