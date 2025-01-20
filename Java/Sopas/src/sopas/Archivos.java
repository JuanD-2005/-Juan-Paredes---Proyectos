
package sopas;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Archivos {
       
    
    
public static void crearArchivo(String NombreArchivo) {   
        File archivo = new File(NombreArchivo);
        
        try{
           PrintWriter salida = new PrintWriter (archivo);
           salida.close();
        
        }catch (FileNotFoundException ex){
            ex.printStackTrace(System.out);
        }
    }
    
    public static void escribirArchivo(String NombreArchivo, String contenido) {
        File archivo = new File(NombreArchivo);
        
        try{
           PrintWriter salida = new PrintWriter (new FileWriter(archivo, true));
           salida.println(contenido);
           salida.close();
          
        }catch (FileNotFoundException ex){
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void LeerArchivo(String NombreArchivo) {
        File archivo = new File(NombreArchivo);
        
        try{
           BufferedReader entrada = new BufferedReader(new FileReader(archivo));
           String lectura = entrada.readLine();
           while (lectura != null){
               System.out.println(lectura);
               lectura = entrada.readLine();
           }
           entrada.close();
           
        }catch (FileNotFoundException ex){
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void EliminarArchivo(String NombreArchivo) {
        File archivo = new File(NombreArchivo);
        archivo.delete();
  
    }
}
