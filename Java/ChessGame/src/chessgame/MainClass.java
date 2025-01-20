
package chessgame;

import javax.swing.ImageIcon;


public class MainClass extends ImageLoader {

   
    public static void main(String[] args) {

     

        
        
        Interfaz v1 = new Interfaz();
        
        v1.setVisible(true);
        
    }

    public MainClass(ImageIcon[] images, String nom1, String nom2, String Win) {
        super(images, nom1, nom2, Win);
    }
    
}
