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
    private String nome_rotulo;
    private String nome_imagem;
    private ArrayList<Integer> multiple_selected_regions;
    private BufferedImage origin_image;
    private BufferedImage image_clareada;

    public Rotulo(int index, float blurlevel, float colorradius, float minsize, String nome_rotulo, ArrayList<Integer> multiple_selected_regions, BufferedImage origin_image, BufferedImage imagem_clareada, String nome_imagem) {
        this.index                      = index;
        this.blurlevel                  = blurlevel;
        this.colorradius                = colorradius;
        this.minsize                    = minsize;
        this.nome_rotulo                = nome_rotulo;
        this.multiple_selected_regions  = new ArrayList<Integer>(multiple_selected_regions);
        this.image_clareada             = imagem_clareada;
        this.nome_imagem                = nome_imagem;
        System.out.println("index "+index+" nome rotulo: "+nome_rotulo+" blurlevel:"+blurlevel+" colorradius:"+colorradius+" minsize:"+minsize+"multiple_selected_regions: "+multiple_selected_regions+" nome imagem:"+nome_imagem);
    }

    public String getNome_rotulo() {
        return nome_rotulo;
    }

    public void setNome_rotulo(String nome_rotulo) {
        this.nome_rotulo = nome_rotulo;
    }

    public ArrayList<Integer> getMultiple_selected_regions() {
        return multiple_selected_regions;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public BufferedImage getImagme_clareada() {
        return image_clareada;
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

    public String getNome_imagem() {
        return nome_imagem;
    }

    public void setNome_imagem(String nome_imagem) {
        this.nome_imagem = nome_imagem;
    }
    
}
