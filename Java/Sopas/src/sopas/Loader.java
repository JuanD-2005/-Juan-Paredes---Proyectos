
package sopas;

import java.io.File;
import javax.sound.sampled.*;
public class Loader {
    
    public static void main(String[] args) {
  
 try {
            // Cargar el archivo de música
            String filePath = "Archivo\\Cancion.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));

            // Crear un <link>Clip</link> y abrir el flujo de audio
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // Reproducir la música
            clip.start();

          

            // Cerrar el clip y liberar los recursos
           

        } catch (Exception e) {
            e.printStackTrace();
        }
    

    
        Menu v1 = new Menu();
        
        v1.setVisible(true);  
    }
 
    
    
}
