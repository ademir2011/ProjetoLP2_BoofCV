/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Classes.Rotulo;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ademir
 */
public class RotuloDAO {

    ArrayList<Rotulo> rotulo_list = new ArrayList<Rotulo>();
    
    public RotuloDAO() {
    }
    
    public void adicionar(Rotulo rotulo){
        rotulo_list.add(rotulo);
        System.out.println("Rotulo +"   + " ["+rotulo.getIndex()+"]"
                                        + " ["+rotulo.getNome()+"]"
                                        + " ["+rotulo.getRegion_map_value()+"]"
                                        + " adicionado ao DAO");
    }
    
    public void remover(){
        
    }
    
    public ArrayList<Integer> getRmapofName(String name){
        for(Rotulo key : rotulo_list){
            if(key.getNome().equals(name)){
                System.out.println("Region map: "+key.getRegion_map_value()+" encontrada a partir do nome: "+name);
                return key.getRegion_map_value();
            }
        }
        
        System.out.println("Region_map_value nao encontrada!");
        return null;
    }
    
    public String getNameofRmap(ArrayList<Integer> regionmap_value){
        for(Rotulo key : rotulo_list){
            if(key.getRegion_map_value() == regionmap_value){
                return key.getNome();
            }
        }
        
        System.out.println("Nome nao encontrado!");
        return "";
    }
    
    public BufferedImage getBufImageofName(String name){
        for(Rotulo key : rotulo_list){
            if(key.getNome().equals(name)){
                System.out.println("Imagem clareada encontrada !");
                return key.getImagme_clareada();
            }
        }
        
        System.out.println("Region_map_value nao encontrada!");
        return null;
    }

    public ArrayList<Rotulo> getRotulo_list() {
        return rotulo_list;
    }

    public void setRotulo_list(ArrayList<Rotulo> rotulo_list) {
        this.rotulo_list = rotulo_list;
    }
    
    
    
}
