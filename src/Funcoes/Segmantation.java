/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcoes;

import boofcv.io.image.UtilImageIO;
import br.ufrn.imd.lp2.imagesegmentation.ImageInformation;
import br.ufrn.imd.lp2.imagesegmentation.ImageSegmentation;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Ademir e Leonardo
 */
public class Segmantation {
    
    private float blurlevel;
    private float colorradius;
    private float minsize;
    private int totalregioes;
    BufferedImage image_origin;
    BufferedImage image_seg;
    BufferedImage image_map;
    ImageInformation seg;

    /**
     * Inicilização da segmentação com valores default
     */
    public Segmantation() {
        this.blurlevel = 0.99f;
        this.colorradius = 40f;
        this.minsize = 1000f;
        this.totalregioes = 0;
        this.seg = null;
    }
    
    /**
     * Polimorfismo de método para aquele que chamar só caminho ou setando valores
     * @param caminho
     * @return 
     */
    public BufferedImage segmentar_imagem(String caminho) {
        return segmentar_imagem(caminho, this.blurlevel, this.colorradius, this.minsize);
    }
    
    /**
     * nesse polimorfismo é setado valores do blurlevel, colorradius e minsize
     * tem a função de segmentar a imagem de acordo com os valores passados
     * @param caminho
     * @param blurlevel
     * @param colorradius
     * @param minsize
     * @return 
     */
    public BufferedImage segmentar_imagem(String caminho, float blurlevel, float colorradius, float minsize) {
        
        this.blurlevel = blurlevel;
        this.colorradius = colorradius;
        this.minsize = minsize;
        this.image_origin = UtilImageIO.loadImage(caminho);
        this.image_seg = UtilImageIO.loadImage(caminho);

        try {

            // Segmentação com parâmetros 0.99, 40 e 1000 < default
            seg = ImageSegmentation.performSegmentation(caminho, blurlevel, colorradius, minsize);

            // Impressão na tela da quantidade de regiões gerada
            System.out.println("Total de regiões imagem segmentada: " + seg.getTotalRegions());
            this.totalregioes = seg.getTotalRegions();
            
            image_seg = seg.getRegionMarkedImage(); // Imagem segmentada

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao segmentar imagem" + e);
        }

        return image_seg;
    }
    
    /**
     * Método para fazer a rotulação da imagem de acordo com os valores setados
     * @param caminho
     * @param blurlevel
     * @param colorradius
     * @param minsize
     * @return 
     */
    public BufferedImage rotular_imagem(String caminho, float blurlevel, float colorradius, float minsize){
        
        this.blurlevel = blurlevel;
        this.colorradius = colorradius;
        this.minsize = minsize;
        this.image_origin = UtilImageIO.loadImage(caminho);
        this.image_map = UtilImageIO.loadImage(caminho);
        System.out.println("teste");
        try {

            
            seg = ImageSegmentation.performSegmentation(caminho, blurlevel, colorradius, minsize);
            image_map = seg.getRegionMarkedImage(); // Imagem segmentada
            
            // Impressão na tela da quantidade de regiões gerada
            System.out.println("Total de regiões: " + seg.getTotalRegions());
            totalregioes = seg.getTotalRegions();
            
            //image_origin = seg.getOriginalImage(); // Imagem original

            int region_map[] = seg.getSegmentedImageMap(); // retorna mapa de regioes [0_N] da segmentacao

            int tonalidades_cinza[] = new int[totalregioes];
            
            Random r = new Random();
            
            for (int i = 0; i < totalregioes; i++) {
                tonalidades_cinza[i] = r.nextInt(255);
                System.out.print(" "+tonalidades_cinza[i]);
            }
            
            for (int i = 0; i < totalregioes; i++) {
                for (int j = i+1; j < totalregioes; j++) {
                    if(tonalidades_cinza[i] == tonalidades_cinza[j]){
                        System.out.println("existe um valor igual v"+"["+j+"]");
                        tonalidades_cinza[j] = r.nextInt(255);
                    }
                }
            }
            
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

    public float getBlurlevel() {
        return blurlevel;
    }

    public void setBlurlevel(float blurlevel) {
        this.blurlevel = blurlevel;
    }

    public float getColorradius() {
        return colorradius;
    }

    public void setColorradius(float colorradius) {
        this.colorradius = colorradius;
    }

    public float getMinsize() {
        return minsize;
    }

    public void setMinsize(float minsize) {
        this.minsize = minsize;
    }

    public int getTotalregioes() {
        return totalregioes;
    }

    public void setTotalregioes(int totalregioes) {
        this.totalregioes = totalregioes;
    }
    
    
    
    
}
