/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcoes;

import boofcv.alg.filter.binary.BinaryImageOps;
import boofcv.core.image.ConvertBufferedImage;
import boofcv.gui.binary.VisualizeBinaryData;
import boofcv.io.image.UtilImageIO;
import boofcv.struct.ConnectRule;
import boofcv.struct.image.ImageSInt32;
import boofcv.struct.image.ImageUInt8;
import br.ufrn.imd.lp2.imagesegmentation.ImageInformation;
import br.ufrn.imd.lp2.imagesegmentation.ImageSegmentation;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Ademir e Leonardo
 */
public class Segmantation {

    public Segmantation() {

    }

    public BufferedImage segmentar_imagem(String caminho) {

        // Segmentação com parâmetros 0.99, 40 e 1000
        ImageInformation seg = null;
        BufferedImage image_origin = UtilImageIO.loadImage(caminho);
        BufferedImage image_seg = UtilImageIO.loadImage(caminho);
        BufferedImage image_map = UtilImageIO.loadImage(caminho);

        try {

            // Segmentação com parâmetros 0.99, 40 e 1000 < default
            seg = ImageSegmentation.performSegmentation(caminho, 0.99, 40, 1000);

            // Impressão na tela da quantidade de regiões gerada
            System.out.println("Total de regiões imagem segmentada: " + seg.getTotalRegions());
            
            image_origin = seg.getOriginalImage(); // Imagem original
            image_seg = seg.getRegionMarkedImage(); // Imagem segmentada

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao segmentar imagem" + e);
        }

        return image_seg;
    }
    
    public BufferedImage rotular_imagem(String caminho){
        
        // Segmentação com parâmetros 0.99, 40 e 1000
        ImageInformation seg = null;
        BufferedImage image_origin = UtilImageIO.loadImage(caminho);
        BufferedImage image_map = UtilImageIO.loadImage(caminho);
    
        try {

            // Segmentação com parâmetros 0.99, 40 e 1000
            seg = ImageSegmentation.performSegmentation(caminho, 0.99, 40, 1000);
            
            // Impressão na tela da quantidade de regiões gerada
            System.out.println("Total de regiões: " + seg.getTotalRegions());
            
            //image_origin = seg.getOriginalImage(); // Imagem original

            int region_map[] = seg.getSegmentedImageMap(); // retorna mapa de regioes [0_N] da segmentacao

            int tonalidades_cinza[] = new int[]{10,20,30,40,50,60,70,100,120,130,150,160,170,200,210,230,235,250,250,255};
            int cont = 0;
            for (int i = 0; i < image_map.getHeight(); i++) {
                for (int j = 0; j < image_map.getWidth(); j++) {
                    Color c = new Color(tonalidades_cinza[region_map[cont]],tonalidades_cinza[region_map[cont]], tonalidades_cinza[region_map[cont]]);
                    image_map.setRGB(j, i, c.getRGB());
                    cont++;
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao rotular imagem" + e);
        }

        return image_map;
    }

}
