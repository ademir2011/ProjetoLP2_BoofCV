/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DateStructure;

import java.util.ArrayList;

/**
 *
 * @author Ademir
 */
public class No {
    private char data; 
    private boolean isend; 
    private int count;  
    public ArrayList<No> childList; 
    //public ArrayList<Imagem> imagensComEssaAnotacao;

    public No(char c){
        childList = new ArrayList<No>();
        isend = false;
        data = c;
        count = 0;
    }  


    /**
     * procura com os nós já existente aquele cujo char do nó seja o char passado
     * @param c char setado
     * @return retorna o nó filho do char encontrado
     */

    public No subNode(char c){
        if (childList != null)
            for (No eachChild : childList)
                if (eachChild.data == c)
                    return eachChild;
        return null;
    }
    
    public char getData(){
        return this.data;
    }
    
    public void setEnd(boolean b){
        this.isend = b;
    }
    
    public boolean getEnd(){
        return this.isend;
    }
    
    /**
     * adiciona contador
     * @param n 
     */

    public void addCount(int n){
        this.count += n;
    }
    
    public int getCount(){
        return this.count;
    }
}
