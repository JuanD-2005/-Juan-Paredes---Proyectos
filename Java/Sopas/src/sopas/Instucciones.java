
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

public class Instucciones extends JFrame{
    private JPanel panel;
    private JLabel [] Escri = new JLabel[7];
    private Font customFont;
    public Instucciones(){
        this.setSize(1000,500);
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.red);
        
        IniciarComponentes();
        
        setMinimumSize(new Dimension(200,200));   
        setTitle("Instrucciones");
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
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        
        Escri[0] = new JLabel();
        Escri[0].setText("En su turno cada jugador debe marcar una secuencia de letras en horizontal, en vertical o ");
        Escri[0].setBounds(10, 32, 950, 32);
        Escri[0].setOpaque(true);
        Escri[0].setBackground(Color.WHITE);
        Escri[0].setFont(customFont);
        
        panel.add(Escri[0]);
        
        Escri[1] = new JLabel();
        Escri[1].setText("en diagonal, tanto leidas de atrás hacia delante como de delante hacia atrás. Si la ");
        Escri[1].setBounds(10, 64, 950, 32);
        Escri[1].setOpaque(true);
        Escri[1].setBackground(Color.WHITE);
        Escri[1].setFont(customFont);
        
        panel.add(Escri[1]);
        
        Escri[2] = new JLabel();
        Escri[2].setText("secuencia de letras constituye efectivamente una de las palabras escondidas en la sopa de");
        Escri[2].setBounds(10, 96, 950, 32);
        Escri[2].setOpaque(true);
        Escri[2].setBackground(Color.WHITE);
        Escri[2].setFont(customFont);
        
        panel.add(Escri[2]);
        
        Escri[3] = new JLabel();
        Escri[3].setText("letras, computara un acierto. Debera clikear y luego arrastrar para Finalmente clikear una");
        Escri[3].setBounds(10, 128, 950, 32);
        Escri[3].setOpaque(true);
        Escri[3].setBackground(Color.WHITE);
        Escri[3].setFont(customFont);
        
        panel.add(Escri[3]);
        
        Escri[4] = new JLabel();
        Escri[4].setText("ultima vez si quiere establecer que palabras selecciono. En caso de empate aquel que haya");
        Escri[4].setBounds(10, 160, 950, 32);
        Escri[4].setOpaque(true);
        Escri[4].setBackground(Color.WHITE);
        Escri[4].setFont(customFont);
        
        panel.add(Escri[4]);
        
        Escri[5] = new JLabel();
        Escri[5].setText("conseguido un total de letras minimo al otro jugador ganara. Puede cargar una partida");
        Escri[5].setBounds(10, 192, 950, 32);
        Escri[5].setOpaque(true);
        Escri[5].setBackground(Color.WHITE);
        Escri[5].setFont(customFont);
        
        panel.add(Escri[5]);
        
        Escri[6] = new JLabel();
        Escri[6].setText("anterior que haya Sido puesta como Guardar y Salir con Cargar.");
        Escri[6].setBounds(10, 224, 950, 32);
        Escri[6].setOpaque(true);
        Escri[6].setBackground(Color.WHITE);
        Escri[6].setFont(customFont);
        
        panel.add(Escri[6]);
        
        ImageIcon imagen = new ImageIcon("Fondo.jpg");
        JLabel etiqueta2 = new JLabel();
        etiqueta2.setBounds(0, 0, getWidth(), getHeight());
        etiqueta2.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_AREA_AVERAGING)));
        panel.add(etiqueta2);
 
    }
}
