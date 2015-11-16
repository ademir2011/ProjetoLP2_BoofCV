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

/**
 *
 * @author Ademir
 */
public class DAO {

    ArrayList<Rotulo> rotulo_list = new ArrayList<Rotulo>();
    ArrayList<String> postfixed_list;
    
    public DAO() {
    }
    
    public void adicionar(Rotulo rotulo){
        rotulo_list.add(rotulo);
        System.out.println("Rotulo +"   + " ["+rotulo.getIndex()+"]"
                                        + " ["+rotulo.getNome()+"]"
                                        + " ["+rotulo.getMultiple_selected_regions()+"]"
                                        + " adicionado ao DAO");
    }
    
    public void remover(){
        
    }
    
    public ArrayList<Integer> getRmapbyName(String name){
        for(Rotulo key : rotulo_list){
            if(key.getNome().equals(name)){
                System.out.println("Region map: "+key.getMultiple_selected_regions()+" encontrada a partir do nome: "+name);
                return key.getMultiple_selected_regions();
            }
        }
        
        System.out.println("Region_map_value nao encontrada!");
        return null;
    }
    
    public String getNameofRmap(ArrayList<Integer> multiple_selected_regions){
        for(Rotulo key : rotulo_list){
            if(key.getMultiple_selected_regions().equals(multiple_selected_regions)){
                return key.getNome();
            }
        }
        
        System.out.println("Nome nao encontrado!");
        return "";
    }
    
    public BufferedImage getBufImagebyName(String name){
        for(Rotulo key : rotulo_list){
            if(key.getNome().equals(name)){
                System.out.println("Imagem clareada encontrada !");
                return key.getImagme_clareada();
            }
        }
        
        System.out.println("Region_map_value nao encontrada!");
        return null;
    }
    
    public ArrayList searchByPrefix(String prefix){
        postfixed_list = new ArrayList<String>();
        for(Rotulo key : rotulo_list){
            String name = key.getNome();
            if(name.length() >= prefix.length()){
                String cut_name = name.substring(0, prefix.length());
                if(cut_name.equals(prefix)){
                    postfixed_list.add( name );
                    //name.substring( prefix.length(), name.length() )
                }
            }
        }
        if(postfixed_list.isEmpty())
            return null;
        else
            return postfixed_list;
    }

    public ArrayList<Rotulo> getRotulo_list() {
        return rotulo_list;
    }

    public void setRotulo_list(ArrayList<Rotulo> rotulo_list) {
        this.rotulo_list = rotulo_list;
    }
    
    
    
}
