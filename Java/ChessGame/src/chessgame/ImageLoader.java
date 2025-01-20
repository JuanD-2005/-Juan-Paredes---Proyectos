
package chessgame;

import java.net.URL;
import javax.swing.ImageIcon;


public class ImageLoader extends Variables {
  
    private static ImageLoader instance=null;
    private static final int MAX_IMAGES=4;
    private final ImageIcon[] images = null;

    public ImageLoader(ImageIcon[] images, String nom1, String nom2, String Win) {
        super(nom1, nom2, Win);
       
    }

    

    


  
    
    private void rules (){
    
        System.out.println("REGLAS DE JUEGO\n");
        
        System.out.println("Los peones caminan verticalmente hacia adelante (se mueven 1 casilla).\n" +
"• Cuando el primer peón llega a la última fila contraria, el peón se convierte en la pieza reina. Los sucesivos\n" +
"peones que llegan, ya no se pueden mover.\n" +
"• Cada jugador puede sólo 1 vez convertir un peón en reina.\n" +
"• Los peones comen en diagonal (se mueven 2 casillas).\n" +
"• La reina camina verticalmente y horizontalmente 3 pasos (derecha, izquierda, arriba o abajo).\n" +
"• La reina come la primera pieza que se consigue en su camino y ocuparía esa posición.");
        
    }
    
    
    
     
   
    
}
