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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
    private String nome_imagem;
    Functions_UI functions;
    
    ArrayList<Integer> multiple_selected_regions; 
    
    /**
     * Inicilização da segmentação com valores default
     * Intancias de variaveis
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
     * @return envia para o método de segmentação apenas com os valores default
     */
    
    public BufferedImage segmentar_imagem(String caminho) {
        return segmentar_imagem(caminho, this.blurlevel, this.colorradius, this.minsize);
    }
    
    /**
     * Seta valores pre estabelecido nos métodos chamados
     * @param caminho caminho local de onde se encontra a imagem
     * @param blurlevel informação de edição de imagem
     * @param colorradius informação de edição de imagem
     * @param minsize informação de edição de imagem
     */
    
    public void setVariables(String caminho, float blurlevel, float colorradius, float minsize){
        this.blurlevel          = blurlevel;
        this.colorradius        = colorradius;
        this.minsize            = minsize;
        this.original_image     = UtilImageIO.loadImage(caminho);
        this.labeled_image      = UtilImageIO.loadImage(caminho);
    }
    
    /**
     * Método exclusivo para segmentação da imagem e para setar valores utilizando a 
     * variábel do objeto segmentado. 
     * @param caminho caminho local de onde se encontra a imagem
     * @param blurlevel informação de edição de imagem
     * @param colorradius informação de edição de imagem
     * @param minsize informação de edição de imagem
     */
    
    public void setSeg(String caminho, float blurlevel, float colorradius, float minsize){
        try {
            
            this.seg                 = ImageSegmentation.performSegmentation(caminho, blurlevel, colorradius, minsize);
            this.segmented_regions   = seg.getTotalRegions();
            this.mapped_regions      = seg.getSegmentedImageMap();
            this.segmented_image     = seg.getRegionMarkedImage();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao segmentar imagem" + e);
        }
    }
    
    /**
     * Método para segmentação da imagem com valores estabelecidos na chamada do método
     * @param caminho caminho local de onde se encontra a imagem
     * @param blurlevel informação de edição de imagem
     * @param colorradius informação de edição de imagem
     * @param minsize informação de edição de imagem
     * @return 
     */
    
    public BufferedImage segmentar_imagem(String caminho, float blurlevel, float colorradius, float minsize) {
        
        setSeg(caminho, blurlevel, colorradius, minsize);
        
        setVariables(caminho, blurlevel, colorradius, minsize);
        
        multiple_selected_regions.clear();
        
        return segmented_image;
    }
    
    /**
     * Recebe um array e analisa todos os valores e verifica se eles são iguais, caso
     * sejam, esses mesmos valores são modificados
     * @param array vator a ser recebido
     * @param tam tamanho máximo do vetor
     * @param maxRandom valor máximo randomico para ser sorteado
     * @return 
     */
    
    public int[] differArray(int[] array, int tam, int maxRandom){
        for (int i = 0; i < tam; i++) {
            for (int j = i+1; j < tam; j++) {
                if(array[i] == array[j]){
                    System.out.println("existe um valor igual v"+"["+j+"]");
                    array[j] = random.nextInt(maxRandom);
                }
            }
        } 
        return array;
    }
    
    /**
     * método que retorna um array de inteiros com valores 
     * @param tam tamanho máximo do vetor
     * @return 
     */
    
    public int[] getRandomArray(int tam){
        
        int[] sv      = new int[tam];

        for (int i = 0; i < tam; i++) {
            sv[i] = random.nextInt(255);
        }

        Arrays.sort(sv);
        System.out.println(Arrays.toString(sv));

        sv = differArray(sv, tam, 255);
          
        return sv;
    }
    
    /**
     * Método para fazer a rotulação da imagem de acordo com os valores setados
     * @@param caminho caminho local de onde se encontra a imagem
     * @param blurlevel informação de edição de imagem
     * @param colorradius informação de edição de imagem
     * @param minsize informação de edição de imagem
     * @return 
     */
    
    public BufferedImage rotular_imagem(String caminho, float blurlevel, float colorradius, float minsize){
        
        setVariables(caminho, blurlevel, colorradius, minsize);
        
        setSeg(caminho, blurlevel, colorradius, minsize);
        
        shades_of_gray = getRandomArray(segmented_regions);
        
        int cont = 0;
        
        for (int i = 0; i < labeled_image.getHeight(); i++) {
            for (int j = 0; j < labeled_image.getWidth(); j++) {
                Color c = new Color(shades_of_gray[mapped_regions[cont]],shades_of_gray[mapped_regions[cont]], shades_of_gray[mapped_regions[cont]]);
                labeled_image.setRGB(j, i, c.getRGB());
                cont++;
            }
        }

        return labeled_image;
    }
    
    /**
     * seleciona o velor da região segmentada de acordo com o evento do clique
     * do mouse nas posições x e y
     * @param x cordernada x
     * @param y cordenada y
     * @param lbImgSeg_mdSeg label do local da imagem
     */
    
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
    
    /**
     * pinta o pixel nas posições i e j mais escutor, subtraindo por 2 a cor RGB (vermelhor, verde e azul) 
     * do mesmo
     * @param i cordenada x
     * @param j cordenada y
     * @param bi imagem do pixel a ser pintado
     * @return 
     */
    
    public BufferedImage pintar_escuro(int i, int j, BufferedImage bi){
        
        Color c_origin  = new Color(segmented_image.getRGB(j, i));
        Color c_star    = new Color(c_origin.getRed()/2, c_origin.getGreen()/2, c_origin.getBlue()/2);
        bi.setRGB(j, i, c_star.getRGB());
        return bi;
    }
    
    /**
     * clareia a imagem no labal setado na chamada do votor
     * @param lbImgSeg_mdSeg labal da imagem
     * @return retorna a imagem clareada 
     */
    
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
    
    /**
     * seleciona a região marcada com o clique do mouse no label selecionado
     * @param lbImgSeg_mdSeg 
     */
    
    public void ativa_selecao(JLabel lbImgSeg_mdSeg){
        lbImgSeg_mdSeg.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e){
                    selecionar_rotulo(e.getX(), e.getY(),lbImgSeg_mdSeg);
                    BufferedImage bi = clarear_rotulo(lbImgSeg_mdSeg);
                    lbImgSeg_mdSeg.setIcon( new ImageIcon(bi) );
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

    public BufferedImage getScaled_image() {
        return scaled_image;
    }

    public void setScaled_image(BufferedImage scaled_image) {
        this.scaled_image = scaled_image;
    }

    public String getNome_imagem() {
        return nome_imagem;
    }

    public void setNome_imagem(String nome_imagem) {
        this.nome_imagem = nome_imagem;
    }

    public Functions_UI getFunctions() {
        return functions;
    }

    public void setFunctions(Functions_UI functions) {
        this.functions = functions;
    }
    
}
