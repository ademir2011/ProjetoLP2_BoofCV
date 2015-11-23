/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcoes;

import Interface.ModuleSegmantation_UI;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

/**
 *
 * @author Ademir
 */
public class Functions_UI {
    
    public Functions_UI(){
    }
    
    /**
     * Operação para abrir o painel de seleção de arquivos,
     * no nosso caso podes escolher 1 ou mais arquivos pois setamos como true o método
     * setMultiSelectionEnabled(). Caso dê erro ou a operação for cancelada
     * será retornado null
     * @param current_directory local onde será aberor a pasta inicial para escolhas de arquivos
     * @return se de sucesso será retornado todo(s) os arquivos escolhidos
     */
    
    public File[] open_filechoose(String current_directory){
        
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(current_directory));
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
    
    /**
     * Seta no label da assinatura do método a imagem
     * @param label label a ser utilizado
     * @param image_buff imagem a ser adicinada
     */
    
    public void setarImageLabel(JLabel label, BufferedImage image_buff){
        label.setIcon(new ImageIcon(image_buff.getScaledInstance(label.getWidth(), label.getHeight(), image_buff.SCALE_SMOOTH)));
    }
    
    /**
     * rediomensiona a imagem que está no diretório do "caminho" dado os parametros width e height
     * e sobrescreve na imagem 
     * @param width largura
     * @param height altura
     * @param caminho local da imagem a ser redimensionada
     */
    
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

    /**
     * método para adicionar uma barra de progresso dados um instante de loop numa nova frame
     * o barra fica localizada no centro da tela
     * @param time tempo do loop
     */
    
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
                    } catch (InterruptedException ex) { Logger.getLogger(ModuleSegmantation_UI.class.getName()).log(Level.SEVERE, null, ex);}       
                }
                frame.dispose();
            }
        }.start();
    }
    
}
