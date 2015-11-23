/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcoes;

import Classes.Rotulo;
import DAO.DAO;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ademir
 */
public class TratamentoDeErros {
    
    public ArrayList<String> file_list = new ArrayList<String>();

    public TratamentoDeErros() {
    }
    
    /**
     * tratamento de erro ao carregar alguma imagem
     * é verificado se se algum arquivo selecionado já existe ou atingiu o numero máximo 
     * de arquivos permitidos
     * @param model model onde ficará armazenado a exibição dos arquivos selecionados
     * @param selectedFile arquivos escolhidos
     * @return retorna true se ocorreu tudo certo false caso contrário
     */
    
    public boolean tratamento_de_erro_carregamento(DefaultTableModel model, File[] selectedFile){
        if(model.getRowCount() > 25) {
            JOptionPane.showMessageDialog(null, "Voce atingiu o numero maximo de imagens");
            return false;
        }
        
        for (String key : file_list) {
            for(int i = 0; i < selectedFile.length; i++){
                if(selectedFile[i].getAbsolutePath().equals(key)){
                    JOptionPane.showMessageDialog(null, "O arquivo: "+selectedFile[i]+" ja existente, escolha outro !");
                    return false;
                }
            }
        }
        return true;
    }
    
    
    /**
     * tratamento de erro ao selecionar imagens, nas ações de: não selecionar imagens ou 
     * não selecionar arquivo, caso contrário 
     * @param tbCaminho_mdX table para análise
     * @return retorna true se deu tudo certo e false caso contrário
     */
    
    public boolean tratamento_de_erro_table(JTable tbCaminho_mdX){
        if(tbCaminho_mdX.getSelectedRow()==-1){
            if(tbCaminho_mdX.getRowCount() == 0){
                JOptionPane.showMessageDialog(null, "Nenhuma imagem selecionada");
                return false;
            } else {
                JOptionPane.showMessageDialog(null, "Voce nao selecionou algum arquivo");
                return false;
            }
        } else {
            System.out.println("Nenhum erro encontrado");
            return true;
        }
    }
    
    /**
     * Tratamento de erro na lista de rótulos selecionados. É verificado se nenhuma imagem
     * foi segmentada, se nenhma texto para adicionar rótulo foi inserido ou se 
     * tal região já foi selecionada.
     * @param jTfNotes texto para ser analisados
     * @param ativar_anotacao instrução booleanada para ativar determinadas verificação
     * @param multiple_selected_regions array de regionas selecionadas
     * @param dao objeto do banco de dados
     * @return retorna true se deu tudo certo, sem erros, e false caso contrário
     */
    
    public boolean tratamento_de_erro_list(JTextField jTfNotes, boolean ativar_anotacao, ArrayList<Integer> multiple_selected_regions, DAO dao){
        
        if(ativar_anotacao == false) {
            JOptionPane.showMessageDialog(null, "Nenhuma imagem segmentada");
            return false;
        } else if(jTfNotes.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Nenhum texto digitado");
            return false;
        }
        
        for (Rotulo key : dao.getRotulo_list()) {
            System.out.println("TESTE: "+multiple_selected_regions);
            if(key.getMultiple_selected_regions().equals(multiple_selected_regions)){
                JOptionPane.showMessageDialog(null, "Regioes já selecionadas com o nome: "+key.getNome_rotulo());
                return false;
            }
        }
        
        return true;
    }
}
