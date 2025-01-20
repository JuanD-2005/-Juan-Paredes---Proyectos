
package sopas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Menu extends JFrame {
    public JPanel panel;
    private JLabel J;
    private JButton Jugar, Instucciones, Creditos;
    private Image fondo;
    private Font customFont;
    
    public Menu(){
        this.setSize(1600,700);
        setLocationRelativeTo(null);
        IniciarComponentes();
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(200,200));   
        setTitle("Proyecto Final - Battle Soup");
    }
    
    private void IniciarComponentes(){
        colocarPanel();
        colocarBotones();
        colocarEtiqueta();
        AbrirJugar ();
        AbrirCreditos ();
        AbrirInstucciones ();
    }
    
    private void colocarPanel(){
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        
        this.getContentPane().add(panel);
    }
    
    private void colocarEtiqueta(){
        ImageIcon imagen2 = new ImageIcon("Titulo.png");
        JLabel etiqueta2 = new JLabel();
        etiqueta2.setBounds(370, 40, 800,300);
        etiqueta2.setIcon(new ImageIcon(imagen2.getImage().getScaledInstance(etiqueta2.getWidth(), etiqueta2.getHeight(), Image.SCALE_AREA_AVERAGING)));
        panel.add(etiqueta2);
        
        ImageIcon imagen = new ImageIcon("Fondo.jpg");
        JLabel etiqueta = new JLabel();
        etiqueta.setBounds(0, 0, getWidth(), getHeight());
        etiqueta.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_AREA_AVERAGING)));
        panel.add(etiqueta);
 
    }
    
    private void colocarBotones(){
        try {
            String fontPath = "Pixel.ttf";
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath));
            customFont = customFont.deriveFont(13f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        
        
        
        ImageIcon img = new ImageIcon("Botones1.png");
        img.setDescription("Jugar");
        Jugar = new JButton();
        Jugar.setBounds(150, 400, 200, 100);
        Jugar.setIcon(new ImageIcon(img.getImage().getScaledInstance(Jugar.getWidth(), Jugar.getHeight(), Image.SCALE_AREA_AVERAGING)));
        
        
        panel.add(Jugar);
        
        
        Instucciones = new JButton("Instucciones");
        Instucciones.setBounds(670, 400, 200, 100);
        Instucciones.setFont(customFont);
     
        panel.add(Instucciones);
        
        Creditos = new JButton("Creditos");
        Creditos.setBounds(1250, 400, 200, 100);
        Creditos.setFont(customFont);
        
        panel.add(Creditos);
    }
    
    private void AbrirJugar (){
        ActionListener Inicio = (ActionEvent e) -> {
            Sopas v2 = new Sopas();
            
            v2.setVisible(true);
            
            setVisible(false);
        };
        Jugar.addActionListener(Inicio);
    }
    private void AbrirInstucciones (){
        ActionListener Inicio = (ActionEvent e) -> {
            Instucciones v3 = new Instucciones();
            
            v3.setVisible(true);
        };
        Instucciones.addActionListener(Inicio);
    }
    private void AbrirCreditos (){
        ActionListener Inicio = (ActionEvent e) -> {
            Creditos v4 = new Creditos();
            
            v4.setVisible(true);
        };
        Creditos.addActionListener(Inicio);
    }
    
    /*
    @Override
    public void paint (Graphics g){
        fondo = new ImageIcon(getClass().getResource("Fondo.jpg")).getImage();
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
      //  setOpaque(false);
        super.paint(g);
    }
*/
}
