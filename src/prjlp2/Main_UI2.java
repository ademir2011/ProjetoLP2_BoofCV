/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjlp2;

import br.ufrn.imd.lp2.imagesegmentation.ImageInformation;
import br.ufrn.imd.lp2.imagesegmentation.ImageSegmentation;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 *
 * @author Ademir e Leonardo
 */
public class Main_UI2 {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Main_UI2();
    }
    
    public Main_UI2(){
        // Segmentação com parâmetros 0.99, 40 e 1000
        ImageInformation seg = ImageSegmentation.performSegmentation("imgs/model.jpg", 0.99,40,1000);
        
        // Impressão na tela da quantidade de regiões gerada
        System.out.println("Total de regiões: " + seg.getTotalRegions());
        
        // Criação de um JFrame e inserção de 2 JLabels com cada uma das imagens.
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(seg.getOriginalImage()))); // Imagem original
        frame.getContentPane().add(new JLabel(new ImageIcon(seg.getRegionMarkedImage()))); // Imagem segmentada
        frame.pack();
        frame.setVisible(true);
    }
    
}
