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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Ademir e Leonardo
 */
public class TaskSegmentation {
    
    private float blurlevel;
    private float colorradius;
    private float minsize;
    private int totalregioes;
    BufferedImage image_origin;
    BufferedImage image_seg;
    BufferedImage image_rotulada;
    BufferedImage image_clareada;
    ImageIcon imageicon_image_clareada;
    ImageInformation seg;
    private int region_map[];
    private int tonalidades_cinza[];
    Random random;
    private int regionmap_selected;

    /**
     * Inicilização da segmentação com valores default
     */
    public TaskSegmentation() {
        this.blurlevel      = 0.99f;
        this.colorradius    = 40f;
        this.minsize        = 1000f;
        this.totalregioes   = 0;
        this.seg            = null;
        random              = new Random();
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
            totalregioes = seg.getTotalRegions();
            region_map = seg.getSegmentedImageMap();
            image_seg = seg.getRegionMarkedImage(); // Imagem segmentada
            
            // Impressão na tela da quantidade de regiões gerada
            System.out.println("Total de regiões imagem segmentada: " + seg.getTotalRegions());

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
        
        this.blurlevel      = blurlevel;
        this.colorradius    = colorradius;
        this.minsize        = minsize;
        this.image_origin   = UtilImageIO.loadImage(caminho);
        this.image_rotulada = UtilImageIO.loadImage(caminho);
        
        try {

            
            seg                 = ImageSegmentation.performSegmentation(caminho, blurlevel, colorradius, minsize);
            image_rotulada      = seg.getRegionMarkedImage(); // Imagem segmentada
            totalregioes        = seg.getTotalRegions();
            region_map          = seg.getSegmentedImageMap(); // retorna mapa de regioes [0_N] da segmentacao
            tonalidades_cinza   = new int[totalregioes];
            
            System.out.println("Total de regiões: " + totalregioes);
            
            for (int i = 0; i < totalregioes; i++) {
                tonalidades_cinza[i] = random.nextInt(255);
            }
            
            Arrays.sort(tonalidades_cinza);
            System.out.println(Arrays.toString(tonalidades_cinza));
            
            for (int i = 0; i < totalregioes; i++) {
                for (int j = i+1; j < totalregioes; j++) {
                    if(tonalidades_cinza[i] == tonalidades_cinza[j]){
                        System.out.println("existe um valor igual v"+"["+j+"]");
                        tonalidades_cinza[j] = random.nextInt(255);
                    }
                }
            }   
            
            Arrays.sort(tonalidades_cinza);
            System.out.println("Array final: " + Arrays.toString(tonalidades_cinza));
            
            int cont = 0;
            for (int i = 0; i < image_rotulada.getHeight(); i++) {
                for (int j = 0; j < image_rotulada.getWidth(); j++) {
                    Color c = new Color(tonalidades_cinza[region_map[cont]],tonalidades_cinza[region_map[cont]], tonalidades_cinza[region_map[cont]]);
                    image_rotulada.setRGB(j, i, c.getRGB());
                    cont++;
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao rotular imagem" + e);
        }
        
        return image_rotulada;
    }

    public void selecionar_rotulo(int x, int y, JLabel lbImgSeg_mdSeg){
        
        int cont = 0;
        
        for (int i = 0; i < lbImgSeg_mdSeg.getHeight(); i++) {
            for (int j = 0; j < lbImgSeg_mdSeg.getWidth(); j++) {
                if(x == j && y == i){
                    regionmap_selected = region_map[cont]; 
                    System.out.println("Region map encontrada: " + regionmap_selected);
                    break;
                } else {
                    cont++;                    
                }
            }
        }
        System.out.println("Rotulo selecionado");
    }
    
    public void clarear_rotulo(JLabel lbImgSeg_mdSeg){
        int cont = 0;
        image_clareada = seg.getRegionMarkedImage();
        for (int i = 0; i < lbImgSeg_mdSeg.getHeight(); i++) {
            for (int j = 0; j < lbImgSeg_mdSeg.getWidth(); j++) {
                if(regionmap_selected != region_map[cont]){
                    Color c_origin = new Color(image_seg.getRGB(j, i));
                    Color c_star = new Color(c_origin.getRed()/2, c_origin.getGreen()/2, c_origin.getBlue()/2);
                    image_clareada.setRGB(j, i, c_star.getRGB());
                } 
                cont++;
            }
        }
        System.out.println("Rotulo clareado");
    }
    
    public void ativa_selecao(JLabel lbImgSeg_mdSeg){
        lbImgSeg_mdSeg.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e){
                    System.out.println("Imagem selecionala nos pixels: X = "+e.getX()+" e Y = "+e.getY());
                    selecionar_rotulo(e.getX(),e.getY(),lbImgSeg_mdSeg);
                    clarear_rotulo(lbImgSeg_mdSeg);
                    imageicon_image_clareada = new ImageIcon(image_clareada);
                    lbImgSeg_mdSeg.setIcon(imageicon_image_clareada);
                }
            });
    }
    
    public void module_notes(){
        
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

    public BufferedImage getImage_origin() {
        return image_origin;
    }

    public void setImage_origin(BufferedImage image_origin) {
        this.image_origin = image_origin;
    }

    public BufferedImage getImage_seg() {
        return image_seg;
    }

    public void setImage_seg(BufferedImage image_seg) {
        this.image_seg = image_seg;
    }

    public BufferedImage getImage_rotulada() {
        return image_rotulada;
    }

    public void setImage_rotulada(BufferedImage image_rotulada) {
        this.image_rotulada = image_rotulada;
    }

    public BufferedImage getImage_clareada() {
        return image_clareada;
    }

    public void setImage_clareada(BufferedImage image_clareada) {
        this.image_clareada = image_clareada;
    }

    public ImageIcon getImageicon_image_clareada() {
        return imageicon_image_clareada;
    }

    public void setImageicon_image_clareada(ImageIcon imageicon_image_clareada) {
        this.imageicon_image_clareada = imageicon_image_clareada;
    }

    public ImageInformation getSeg() {
        return seg;
    }

    public void setSeg(ImageInformation seg) {
        this.seg = seg;
    }

    public int[] getRegion_map() {
        return region_map;
    }

    public void setRegion_map(int[] region_map) {
        this.region_map = region_map;
    }

    public int[] getTonalidades_cinza() {
        return tonalidades_cinza;
    }

    public void setTonalidades_cinza(int[] tonalidades_cinza) {
        this.tonalidades_cinza = tonalidades_cinza;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public int getRegionmap_selected() {
        return regionmap_selected;
    }

    public void setRegionmap_selected(int regionmap_selected) {
        this.regionmap_selected = regionmap_selected;
    }
    
}
