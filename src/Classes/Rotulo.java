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
    private String nome;
    private ArrayList<Integer> region_map_value;
    private BufferedImage imagme_clareada;

    public Rotulo(int index, String nome, ArrayList<Integer> region_map_value, BufferedImage imagem_clareada) {
        this.index              = index;
        this.nome               = nome;
        this.region_map_value   = new ArrayList<Integer>(region_map_value);
        this.imagme_clareada    = imagem_clareada;
        System.out.println("index "+index+" nome: "+nome+" region_map_value: "+region_map_value);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Integer> getRegion_map_value() {
        return region_map_value;
    }

    public void setRegion_map_value(ArrayList<Integer> region_map_value) {
        this.region_map_value = region_map_value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public BufferedImage getImagme_clareada() {
        return imagme_clareada;
    }

    public void setImagme_clareada(BufferedImage imagme_clareada) {
        this.imagme_clareada = imagme_clareada;
    }
    
    
}
