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
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.imgscalr.Scalr;

/**
 *
 * @author Ademir e Leonardo
 */
public class TaskSegmentation {
    
    private float blurlevel;
    private float colorradius;
    private float minsize;
    private int segmented_regions;
    BufferedImage original_image;
    BufferedImage segmented_image;
    BufferedImage labeled_image;
    BufferedImage image_lightened;
    ImageIcon image_lightened_icon;
    ImageInformation seg;
    private int[] mapped_regions;
    private int[] shades_of_gray;
    Random random;
    private int mapped_region_selected;
    BufferedImage scaled_image;

    ArrayList<Integer> multiple_selected_regions; 
    
    /**
     * Inicilização da segmentação com valores default
     */
    public TaskSegmentation() {
        this.blurlevel          = 0.99f;
        this.colorradius        = 40f;
        this.minsize            = 1000f;
        this.segmented_regions  = 0;
        random                  = new Random();
        mapped_region_selected  = 0;
        multiple_selected_regions = new ArrayList<Integer>();
        
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
        
        this.blurlevel          = blurlevel;
        this.colorradius        = colorradius;
        this.minsize            = minsize;
        
        try {
            
            this.seg                 = ImageSegmentation.performSegmentation(caminho, blurlevel, colorradius, minsize);
            this.segmented_regions   = seg.getTotalRegions();
            this.mapped_regions      = seg.getSegmentedImageMap();
            this.segmented_image     = seg.getRegionMarkedImage();
            
            System.out.println("Regiões segmentadas: " + segmented_regions);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao segmentar imagem" + e);
        }
        
        multiple_selected_regions.clear();
        //scaled_image = Scalr.resize(segmented_image, Scalr.Method.ULTRA_QUALITY, 400, 400);
        return segmented_image;
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
        this.original_image = UtilImageIO.loadImage(caminho);
        this.labeled_image  = UtilImageIO.loadImage(caminho);
        
        try {
            
            seg                 = ImageSegmentation.performSegmentation(caminho, blurlevel, colorradius, minsize);
            labeled_image       = seg.getRegionMarkedImage(); // Imagem segmentada
            segmented_regions   = seg.getTotalRegions();
            mapped_regions      = seg.getSegmentedImageMap(); // retorna mapa de regioes [0_N] da segmentacao
            shades_of_gray      = new int[segmented_regions];
            
            for (int i = 0; i < segmented_regions; i++) {
                shades_of_gray[i] = random.nextInt(255);
            }
            
            Arrays.sort(shades_of_gray);
            System.out.println(Arrays.toString(shades_of_gray));
            
            for (int i = 0; i < segmented_regions; i++) {
                for (int j = i+1; j < segmented_regions; j++) {
                    if(shades_of_gray[i] == shades_of_gray[j]){
                        System.out.println("existe um valor igual v"+"["+j+"]");
                        shades_of_gray[j] = random.nextInt(255);
                    }
                }
            }   
            
            Arrays.sort(shades_of_gray);
            System.out.println("Array final: " + Arrays.toString(shades_of_gray));
            
            int cont = 0;
            for (int i = 0; i < labeled_image.getHeight(); i++) {
                for (int j = 0; j < labeled_image.getWidth(); j++) {
                    Color c = new Color(shades_of_gray[mapped_regions[cont]],shades_of_gray[mapped_regions[cont]], shades_of_gray[mapped_regions[cont]]);
                    labeled_image.setRGB(j, i, c.getRGB());
                    cont++;
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao rotular imagem" + e);
        }
        
        return labeled_image;
    }

    public void selecionar_rotulo(int x, int y, JLabel lbImgSeg_mdSeg){
        
        int cont = 0;
        boolean bool_remove = false;
        Iterator<Integer> it;
        
        for (int i = 0; i < lbImgSeg_mdSeg.getHeight(); i++) {
            for (int j = 0; j < lbImgSeg_mdSeg.getWidth(); j++) {
                if(x == j && y == i){

                    it = multiple_selected_regions.iterator();
                    
                    while(it.hasNext()){
                        Integer key = it.next();
                        if(key.equals(mapped_regions[cont])){
                            it.remove();
                            bool_remove = true;
                        }
                    }
                    
                    if(bool_remove == false){
                        multiple_selected_regions.add(mapped_regions[cont]);
                        mapped_region_selected = mapped_regions[cont];
                    }
                    
                    System.out.println("Vetor de regions clicadas: " + multiple_selected_regions);
                    
                    break;
                } else {
                    cont++;                    
                }
            }
        }
        System.out.println("Rotulo selecionado");
    }
    
    public BufferedImage pintar_escuro(int i, int j, BufferedImage bi){
        
        Color c_origin = new Color(segmented_image.getRGB(j, i));
        Color c_star = new Color(c_origin.getRed()/2, c_origin.getGreen()/2, c_origin.getBlue()/2);
        bi.setRGB(j, i, c_star.getRGB());
        return bi;
    }
    
    public BufferedImage clarear_rotulo(JLabel lbImgSeg_mdSeg){
        
        int cont        = 0;
        boolean cont2   = false;
        Iterator<Integer> it;
        
        image_lightened = seg.getRegionMarkedImage();
        for (int i = 0; i < lbImgSeg_mdSeg.getHeight(); i++) {
            for (int j = 0; j < lbImgSeg_mdSeg.getWidth(); j++) {
                
                it = multiple_selected_regions.iterator();
                    
                while(it.hasNext()){
                    Integer key = it.next();
                    if(mapped_regions[cont] == key)
                        cont2 = true;
                }
                
                if(cont2 == false)
                    image_lightened = pintar_escuro(i, j, image_lightened);
                else
                    cont2=false;
                
                cont++;
                
            }
        }
        System.out.println("Rotulo clareado nas regioes: "+multiple_selected_regions);
        image_lightened_icon = new ImageIcon(image_lightened);
        return image_lightened;
    }
    
    public void clarear_regiao_clicada(int x, int y, JLabel lbImgSeg_mdSeg){
        selecionar_rotulo(x,y,lbImgSeg_mdSeg);
        lbImgSeg_mdSeg.setIcon(new ImageIcon( clarear_rotulo(lbImgSeg_mdSeg) ) );
    }
    
    public void ativa_selecao(JLabel lbImgSeg_mdSeg){
        lbImgSeg_mdSeg.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e){
                    clarear_regiao_clicada(e.getX(),e.getY(),lbImgSeg_mdSeg);
                }
            });
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

    public int getSegmented_regions() {
        return segmented_regions;
    }

    public void setSegmented_regions(int segmented_regions) {
        this.segmented_regions = segmented_regions;
    }

    public BufferedImage getOriginal_image() {
        return original_image;
    }

    public void setOriginal_image(BufferedImage original_image) {
        this.original_image = original_image;
    }

    public BufferedImage getSegmented_image() {
        return segmented_image;
    }

    public void setSegmented_image(BufferedImage segmented_image) {
        this.segmented_image = segmented_image;
    }

    public BufferedImage getLabeled_image() {
        return labeled_image;
    }

    public void setLabeled_image(BufferedImage labeled_image) {
        this.labeled_image = labeled_image;
    }

    public BufferedImage getImage_lightened() {
        return image_lightened;
    }

    public void setImage_lightened(BufferedImage image_lightened) {
        this.image_lightened = image_lightened;
    }

    public ImageIcon getImage_lightened_icon() {
        return image_lightened_icon;
    }

    public void setImage_lightened_icon(ImageIcon image_lightened_icon) {
        this.image_lightened_icon = image_lightened_icon;
    }

    public ImageInformation getSeg() {
        return seg;
    }

    public void setSeg(ImageInformation seg) {
        this.seg = seg;
    }

    public int[] getMapped_regions() {
        return mapped_regions;
    }

    public void setMapped_regions(int[] mapped_regions) {
        this.mapped_regions = mapped_regions;
    }

    public int[] getShades_of_gray() {
        return shades_of_gray;
    }

    public void setShades_of_gray(int[] shades_of_gray) {
        this.shades_of_gray = shades_of_gray;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public int getMapped_region_selected() {
        return mapped_region_selected;
    }

    public void setMapped_region_selected(int mapped_region_selected) {
        this.mapped_region_selected = mapped_region_selected;
    }

    public ArrayList<Integer> getMultiple_selected_regions() {
        return multiple_selected_regions;
    }

    public void setMultiple_selected_regions(ArrayList<Integer> multiple_selected_regions) {
        this.multiple_selected_regions = multiple_selected_regions;
    }
    
    
}
