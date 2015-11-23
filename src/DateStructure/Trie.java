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
public class Trie {
    
    public No root;
    
    /**
     * Intancia a raiz setaendo como vazio
     */
    
    public Trie() {
        root = new No(' ');
    }

    public void insert(String word) {
        if (search(word) == true)
            return;
      
        No current = root;
        for (char ch : word.toCharArray()) {
            No child = current.subNode(ch);
            if (child != null) {
                current = child;
            } else {
                current.childList.add(new No(ch));
                current = current.subNode(ch);
            }
            current.addCount(1);
        }

        current.setEnd(true);
    }

    /**
     * percorre a arvore até encontrar todos os nós que compoem a string
     * @param word palavra setada
     * @return retorna true se deu certo e false caos contrário
     */
    
    public boolean search(String word) {

        No current = root;
        for (char ch : word.toCharArray()) {

            if (current.subNode(ch) == null)
                return false;
            else
                current = current.subNode(ch);
      
        }
        
        if (current.getEnd())
            return true;

        return false;
    }

    public void print(No node, String path) {
        if (this.root.childList.isEmpty()) {
            System.out.println("Árvore vazia!");
            return;
        }
        if (node.getData() != ' ') {
            path += node.getData();
        }
        if (node.getEnd() == true) {
            System.out.println(path);
        }
        for (No childList : node.childList) {
            print(childList, path);
            path = path.substring(0, path.length());
        }
    }

    public void remove(String word) {
        if (search(word) == false) {
            System.out.println(word + " Não está na árvore\n");
            return;
        }

        No current = root;

        for (char ch : word.toCharArray()) {
            No child = current.subNode(ch);
            if (child.getCount() == 1) {
                current.childList.remove(child);
                return;
            } else {
                child.addCount(-1);
                current = child;
            }
        }

        current.setEnd(false);
    }

    public ArrayList<String> getWordsFromPrefix(String prefix) {
        ArrayList<String> lista = new ArrayList();
        No current = root;
        
        if (prefix.isEmpty())
            addPrefix(current, "", lista);
        else{
            for (char ch : prefix.toCharArray()) {
                if (current.subNode(ch) == null)
                    return null;
                else
                    current = current.subNode(ch);
            }
       
            prefix = prefix.substring(0, prefix.length() - 1);
            
            addPrefix(current, prefix, lista);
        }
        return lista;
    }

    void addPrefix(No node, String path, ArrayList lista) {

        if (this.root.childList.isEmpty()) {
            System.out.println("Árvore vazia!");
            return;
        }
        
        if (node.getData() != ' ')
            path += node.getData();
        
        if (node.getEnd() == true)
            lista.add(path);
        
        for (No childList : node.childList) {
            addPrefix(childList, path, lista);
            path = path.substring(0, path.length());
        }
    }
}
