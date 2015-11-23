/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcoes;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author Ademir
 */
public class View {
    
    Color dark_primary_color    = new Color(97,97,97);
    Color primary_color         = new Color(158,158,158);
    Color accent_color          = new Color(70, 77, 87);
    Color text_color            = new Color(33,33,33);
    Color primary_text          = new Color(33,33,33);
    Color light_prmary_color    = new Color(245,245,245);

    public View() {
    }
    
    /**
     * Método para mudança visual de botões
     * @param bt botão a ser editado
     * @return retorna o botão já configurado
     */
    
    public JButton change_button(JButton bt){
        bt.setBackground(primary_color);
        bt.setForeground(light_prmary_color);
        
        bt.setContentAreaFilled(false);
        bt.setOpaque(true);
        return bt;
    }
    
    /**
     * polimorfismo do método acima, a diferença é que podemos escolher uma cor
     * para o background
     * @param bt
     * @param c
     * @return 
     */
    
    public JButton change_button(JButton bt, Color c){
        bt.setBackground(c);
        bt.setForeground(light_prmary_color);
        
        bt.setContentAreaFilled(false);
        bt.setOpaque(true);
        return bt;
    }
    
    /**
     * polimorfismo do método acima, a diferença é que podemos escolher uma cor
     * para o background e foreground (cor de texto interno)
     * @param bt botão a ser editado
     * @param c cor de background
     * @return retorna o botão já configurado
     */
    
    public JButton change_button(JButton bt, Color c, Color c1){
        bt.setBackground(c);
        bt.setForeground(c1);
        
        bt.setContentAreaFilled(false);
        bt.setOpaque(true);
        return bt;
    }
    
    public Color getDark_primary_color() {
        return dark_primary_color;
    }

    public void setDark_primary_color(Color dark_primary_color) {
        this.dark_primary_color = dark_primary_color;
    }

    public Color getPrimary_color() {
        return primary_color;
    }

    public void setPrimary_color(Color primary_color) {
        this.primary_color = primary_color;
    }

    public Color getAccent_color() {
        return accent_color;
    }

    public void setAccent_color(Color accent_color) {
        this.accent_color = accent_color;
    }

    public Color getText_color() {
        return text_color;
    }

    public void setText_color(Color text_color) {
        this.text_color = text_color;
    }

    public Color getPrimary_text() {
        return primary_text;
    }

    public void setPrimary_text(Color primary_text) {
        this.primary_text = primary_text;
    }

    public Color getLight_prmary_color() {
        return light_prmary_color;
    }

    public void setLight_prmary_color(Color light_prmary_color) {
        this.light_prmary_color = light_prmary_color;
    }
    
}
