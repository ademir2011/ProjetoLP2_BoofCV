/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Classes.Rotulo;
import Funcoes.Functions_UI;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * Class para criação de uma estrutura de armazenamento
 * @author Ademir e Leonardo
 */
public class DAO {

    ArrayList<Rotulo> rotulo_list = new ArrayList<Rotulo>();
    ArrayList<String> postfixed_list;
    ArrayList<String> temp_name_list = new ArrayList<String>(); 
    ArrayList<Rotulo> rotulo_temp_list = new ArrayList<Rotulo>();
    FileWriter arq_write;
    FileReader arq_read;
    PrintWriter gravarArq;
    Functions_UI function;
    Random r = new Random();
    
    public final String dir_name_root = "DaoTrabalhoLP2";
    
    /**
     * Criar diretorio principal onde vai conter as imagens
     * @throws IOException 
     */
    
    public DAO() throws IOException {
            
        create_diretorio("C:\\"+dir_name_root);
            
    }
    
    /**
     * Cria um diretório dado um caminho
     * @param dir_path caminho onde será criado o diretório
     */
    
    public void create_diretorio(String dir_path){
        try {
            (new File(dir_path)).mkdir();
        } catch (Exception ex) {   
             JOptionPane.showMessageDialog(null,"Err","Erro ao criar o diretório" + ex.toString(),JOptionPane.ERROR_MESSAGE);   
        }  
    }
    
    /**
     * verifica a esxistencia de uma pasta dado seu caminho
     * @param dir_path caminho da possível pasta
     * @return retorna true a pasta existir e false caso contrário
     */
    
    public boolean directoryExist(String dir_path){
        if ( new File(dir_path).exists() ) { 
            return true;
        }  else {
            return false;
        } 
    }
    
    /**
     * Adiciona o objeto rotulo no banco, na pasta principal. 
     * Primeiro se faz uma verificação se já existe o diretório, se sim uma mensagem é exibida
     * caso contrário será criada uma imagem com o nome do rutulo.
     * Após a criação do diretório é adicionado a imagem segmentada assim como o arquivo
     * txt com as informações básicas da imagem.
     * @param rotulo 
     */
    
    public void adicionar(Rotulo rotulo) {
        
        rotulo_list.add(rotulo);
          
        System.out.println("Rotulo +"   + " ["+rotulo.getIndex()+"]"
                                        + " ["+rotulo.getNome_rotulo()+"]"
                                        + " ["+rotulo.getMultiple_selected_regions()+"]"
                                        + " adicionado ao DAO");
        
        if (!directoryExist("C:\\"+rotulo.getNome_imagem()))   
            create_diretorio("C:\\"+dir_name_root+"\\"+rotulo.getNome_rotulo());
        else 
            System.out.println("Diretório já existe !");
        
        try {
            
            arq_write = new FileWriter("C:\\"+dir_name_root+"\\"+rotulo.getNome_rotulo()+"\\"+rotulo.getNome_imagem()+".txt");
            gravarArq = new PrintWriter(arq_write);
            gravarArq.printf(rotulo.getNome_rotulo()+"%n");
            gravarArq.printf(String.valueOf( rotulo.getBlurlevel()+"%n" ));
            gravarArq.printf(String.valueOf( rotulo.getColorradius()+"%n" ));
            gravarArq.printf(String.valueOf( rotulo.getMinsize()+"%n" ));
            ImageIO.write(rotulo.getImage_clareada(),"jpg", new File("C:\\"+dir_name_root+"\\"+rotulo.getNome_rotulo()+"\\"+rotulo.getNome_imagem()+".jpg") );

            arq_write.close();
        } catch (IOException ex) {
            
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    /**
     * adiciona rotulo a lista de rotulos temporarios
     * @param rotulo objeto rotulo
     */
    
    public void adicionar_temp(Rotulo rotulo){
        rotulo_temp_list.add(rotulo);
    }
    
    /**
     * Remove todos os rótulos temporários
     */
    
    public void remove_temp(){
        rotulo_temp_list.clear();
    }
    
    /**
     * retorna todas as regioes marcadas a partir do nome
     * @param name nome setado pelo usuário
     * @return já informado acima
     */
    
    public ArrayList<Integer> getRmapbyName(String name){
        for(Rotulo key : rotulo_list){
            if(key.getNome_rotulo().equals(name)){
                System.out.println("Region map: "+key.getMultiple_selected_regions()+" encontrada a partir do nome: "+name);
                return key.getMultiple_selected_regions();
            }
        }
        
        System.out.println("Region_map_value nao encontrada!");
        return null;
    }
    
    /**
     * método para procurar o nome a partir de multiplos valores selecionados
     * @param multiple_selected_regions arraylist de valores
     * @return retorna uma srting com o nome achado
     */
    
    public String getNameofRmap(ArrayList<Integer> multiple_selected_regions){
        for(Rotulo key : rotulo_list){
            if(key.getMultiple_selected_regions().equals(multiple_selected_regions)){
                return key.getNome_rotulo();
            }
        }
        
        System.out.println("Nome nao encontrado!");
        return "";
    }
    
    /**
     * procura uma imagem a partir do nome dam esma
     * @param rotulo_name nome do rotulo
     * @param name nome da imagem
     * @return retorna a imagem achada
     * @throws IOException 
     */
    
    public BufferedImage getBufImagebyName(String rotulo_name, String image_name) throws IOException{
        
        File file = new File("C:\\"+dir_name_root+"\\"+rotulo_name+"\\"+image_name+".jpg");
        
        BufferedImage bi = ImageIO.read(file);
        
        return bi;
    }
    
    /**
     * procura por todos os rotulos que comecem com o prefixo
     * setado. O método abaixo varre toda a pasta onde são armazenadas as subpastas
     * com informação e imagem de cada rótulo e analisa o nome se é igual ao prefixo setado. Caso encontre
     * armazena num arraylist chamado postfixed_list. Além disse é feitas certas verificações caso 
     * o prefixo seja vazio
     * @param prefix prefixo setado
     * @return retorna a lista de nomes cujo seja igual ao prefixo
     */
    
    public ArrayList searchByPrefix(String prefix){
        postfixed_list = new ArrayList<String>();
            
        File file = new File("C:\\"+dir_name_root);
        String[] directories = file.list(new FilenameFilter() {
          @Override
          public boolean accept(File current, String name) {
            return new File(current, name).isDirectory();
          }
        });
        
        System.out.println(Arrays.toString(directories));
        
        for(int i = 0; i < directories.length; i++){
            if(directories[i].length() >= prefix.length() ){
                String cut_name = directories[i].substring(0, prefix.length());
                if(cut_name.equals(prefix)){
                    postfixed_list.add( directories[i] );
                }
            }
        }
        
        if(postfixed_list.isEmpty())
            return null;
        else
            return postfixed_list;
    }
    
    /**
     * cria um bufferedimage a partir de uma imagem localizado no caminho setado
     * na variável path
     * @param path local onde a imagem está armazenada
     * @return retorna um bufferedimagem da imagem
     * @throws IOException 
     */
    
    public BufferedImage createImageByPath(String path) throws IOException{
        File img = new File(path);
        BufferedImage bufferedImage = ImageIO.read(img);
        return bufferedImage;
    }

    public ArrayList<Rotulo> getRotulo_list() {
        return rotulo_list;
    }

    public void setRotulo_list(ArrayList<Rotulo> rotulo_list) {
        this.rotulo_list = rotulo_list;
    }

    public ArrayList<String> getPostfixed_list() {
        return postfixed_list;
    }

    public void setPostfixed_list(ArrayList<String> postfixed_list) {
        this.postfixed_list = postfixed_list;
    }

    public ArrayList<String> getTemp_name_list() {
        return temp_name_list;
    }

    public void setTemp_name_list(ArrayList<String> temp_name_list) {
        this.temp_name_list = temp_name_list;
    }

    public FileWriter getArq_write() {
        return arq_write;
    }

    public void setArq_write(FileWriter arq_write) {
        this.arq_write = arq_write;
    }

    public FileReader getArq_read() {
        return arq_read;
    }

    public void setArq_read(FileReader arq_read) {
        this.arq_read = arq_read;
    }

    public PrintWriter getGravarArq() {
        return gravarArq;
    }

    public void setGravarArq(PrintWriter gravarArq) {
        this.gravarArq = gravarArq;
    }

    public Functions_UI getFunction() {
        return function;
    }

    public void setFunction(Functions_UI function) {
        this.function = function;
    }

    public String getDir_name_root() {
        return dir_name_root;
    }

    public ArrayList<Rotulo> getRotulo_temp_list() {
        return rotulo_temp_list;
    }

    public void setRotulo_temp_list(ArrayList<Rotulo> rotulo_temp_list) {
        this.rotulo_temp_list = rotulo_temp_list;
    }

    public Random getR() {
        return r;
    }

    public void setR(Random r) {
        this.r = r;
    }
    
    
    
}
