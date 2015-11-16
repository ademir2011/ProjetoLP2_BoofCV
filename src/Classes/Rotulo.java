/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Ademir
 */
public class Rotulo {
    
    private int index;
    private float blurlevel;
    private float colorradius;
    private float minsize;
    private String nome;
    private ArrayList<Integer> multiple_selected_regions;
    private BufferedImage origin_image;
    private BufferedImage image_clareada;

    public Rotulo(int index, float blurlevel, float colorradius, float minsize, String nome, ArrayList<Integer> multiple_selected_regions, BufferedImage origin_image, BufferedImage imagem_clareada) {
        this.index                      = index;
        this.blurlevel                  = blurlevel;
        this.colorradius                = colorradius;
        this.minsize                    = minsize;
        this.nome                       = nome;
        this.multiple_selected_regions  = new ArrayList<Integer>(multiple_selected_regions);
        this.image_clareada             = imagem_clareada;
        System.out.println("index "+index+" nome: "+nome+" blurlevel:"+blurlevel+" colorradius:"+colorradius+" minsize:"+minsize+"multiple_selected_regions: "+multiple_selected_regions);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Integer> getMultiple_selected_regions() {
        return multiple_selected_regions;
    }
/*
    public void setMultiple_selected_regions(ArrayList<Integer> multiple_selected_regions) {
        this.multiple_selected_regions = multiple_selected_regions;
    }*/

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public BufferedImage getImagme_clareada() {
        return image_clareada;
    }

    public void setImagme_clareada(BufferedImage imagme_clareada) {
        this.image_clareada = imagme_clareada;
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

    public BufferedImage getOrigin_image() {
        return origin_image;
    }

    public void setOrigin_image(BufferedImage origin_image) {
        this.origin_image = origin_image;
    }

    public BufferedImage getImage_clareada() {
        return image_clareada;
    }

    public void setImage_clareada(BufferedImage image_clareada) {
        this.image_clareada = image_clareada;
    }
    
    
}
