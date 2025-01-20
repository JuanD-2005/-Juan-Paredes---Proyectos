
package sopas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Creditos extends JFrame{
    private JPanel panel;
    private JLabel [] Escri = new JLabel[3];
    private Font customFont;
    private Font customFont1;
    public Creditos(){
        this.setSize(1000,500);
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.red);
        
        IniciarComponentes();
        
        setMinimumSize(new Dimension(200,200));   
        setTitle("Proyecto Final - Battle Soup");
    }
    
    private void IniciarComponentes(){
        colocarPanel();
        colocarEtiqueta();
    }
    
    private void colocarPanel(){
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        
        this.getContentPane().add(panel);
    }
    
    private void colocarEtiqueta(){
        try {
            String fontPath = "Pixel.ttf";
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath));
            customFont = customFont.deriveFont(13f);
            
            customFont1 = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath));
            customFont1 = customFont.deriveFont(23f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        
        Escri[0] = new JLabel();
        Escri[0].setText("| Jose Andres Bravo Diaz | C.I: 31.122.695");
        Escri[0].setBounds(10, 32, 950, 32);
        Escri[0].setOpaque(true);
        Escri[0].setBackground(Color.WHITE);
        Escri[0].setFont(customFont1);
        
        panel.add(Escri[0]);
        
        Escri[1] = new JLabel();
        Escri[1].setText("| Juan Diego Paredez Gamez | C.I: 31.357.791");
        Escri[1].setBounds(10, 64, 950, 32);
        Escri[1].setOpaque(true);
        Escri[1].setBackground(Color.WHITE);
        Escri[1].setFont(customFont1);
        
        panel.add(Escri[1]);
        
        Escri[2] = new JLabel();
        Escri[2].setText("All pictures are copyrighted © Bravo Andres-Paredes Juan, Programs.2023.Semester");
        Escri[2].setBounds(10, 96, 950, 32);
        Escri[2].setOpaque(true);
        Escri[2].setBackground(Color.WHITE);
        Escri[2].setFont(customFont);
        
        panel.add(Escri[2]);
        
        ImageIcon imagen = new ImageIcon("Fondo.jpg");
        JLabel etiqueta2 = new JLabel();
        etiqueta2.setBounds(0, 0, getWidth(), getHeight());
        etiqueta2.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_AREA_AVERAGING)));
        panel.add(etiqueta2);
 
    }
}
