
package sopas;
import java.util.Random;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sopas.Archivos.crearArchivo;
import static sopas.Archivos.escribirArchivo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Sopas extends JFrame{
    
    //Variables Globales
    public JPanel panel;
    private JLabel [][] espacio; 
    public int [][] cuenco= new int [21][21];
    public int [][] sopita= new int [21][21];
    public int [][] preso= new int [21][21];
    public int Ganar = 0, Seg = 0;;
    private JLabel [] palabras;
    public String [] Tema1 = new String [16];
    public String [] Tema2 = new String [16];
    public String [] Tema3 = new String [16];
    public String [] TemaX = new String [16];
    public String [] Matricula = new String [10];
    public int cuchara;
    private JLabel GS,Carg,Pts1,Pts2,Nom1,Nom2,img1,img2,img3,img4,img5;
    private ImageIcon Img1,Img2,Img3,Img4,ImgR1,ImgR2,ImgR3,ImgR4,ImgR5,ImgR6,ImgR7,ImgR8;
    private JButton GyS, Cargar;
    private boolean movimiento = false;
    private int tachar = 0, alm = 0,Choque = 0, Puntos1 = 0, Puntos2 = 0,Nletras = 0,Hoal = 0;
    private JLabel [] Tacha;
    private Timer tiempo;
    private int turnos = 1;
    private Font customFont,customFont1,customFont2;
    private String Nombre1,Nombre2;
    private int[] abecedario1 = new int[16];
    private int word;
    private int filler1;
    private int[] abecedario2 = new int[16];
    private int filler2;
    private JLabel NomG;
    
    
    public Sopas(){
        this.setSize(1800,900);
        setLocationRelativeTo(null);
   
        Nombre1 = JOptionPane.showInputDialog("Nombre Jugador 1:");
        JOptionPane.showMessageDialog(null, "Jugador 1: " + Nombre1 + ", \n   Bienvenido.");
        
        Nombre2 = JOptionPane.showInputDialog("Nombre Jugador 2:");
        JOptionPane.showMessageDialog(null, "Jugador 1: " + Nombre2 + ", \n   Bienvenido.");
        
        crearArchivo("Archivo\\Inicio.txt");
       
         
        
        IniciarComponentes();
        
        
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(200,200));   
        setTitle("Proyecto Final - Battle Soup");
    }

    //Metodo Global 
    private void IniciarComponentes(){
      
        
         for (int z = 1; z <  21; z++){
          for (int k = 1; k <  21; k++){
          
              preso[z][k]=0;
          
          }}
        
        for(int i = 1; i<16; i++){
         abecedario1[i]=100;
          abecedario2[i]=100;
         }
        
        colocarPanel();
        Temas();
        colocarEtiqueta();
        backend();
        colocarTema();
        colocarBotones();
        colocarFondo();
        
        tiempo = new Timer(1000,Timer());
        tiempo.start();
        eventosDelRatonMotion();
        GuardarYsalir();
          cargar();
        
    }
   
    //Declaracion de Paneles
    private void colocarPanel(){
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        
        this.getContentPane().add(panel);
    }
    
    //Declaracion de Jlabels
    private void colocarEtiqueta(){
        try {
            String fontPath = "Pixel.ttf";
            
            
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath));
            customFont1 = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath));
            customFont2 = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath));
            
            customFont = customFont.deriveFont(13f);
            customFont1 = customFont.deriveFont(11f);
            customFont2 = customFont.deriveFont(50f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        espacio  = new JLabel [20][20];
  
        int fila = 100;
        
        for (int i = 0 ; i < 20 ; i++){
            fila = fila + 38;
            
            int columna = 25;
            for (int j = 0 ; j < 20 ; j++){
                columna=columna+38;
        
                espacio [j][i] = new JLabel("",SwingConstants.CENTER);
                espacio [j][i].setBackground(Color.white);
                espacio [j][i].setFont(customFont);
                espacio [j][i].setOpaque(true);
                espacio [j][i].setBounds(fila, columna, 30, 32);
                
                panel.add(espacio [j][i]);
            }
        }
        
       
        Tacha = new JLabel[16];
       
        
        for (int i=0; i < 16; i++){
           
            
            Tacha [i] = new JLabel();
            Tacha [i].setBounds(2000, 2000, 132, 3);
            Tacha [i].setOpaque(true);
            Tacha [i].setBackground(Color.BLACK);
            panel.add(Tacha[i]);
        }
        
        
        
        Nom1 = new JLabel(""+Nombre1,SwingConstants.CENTER);
        Nom2 = new JLabel(""+Nombre2,SwingConstants.CENTER);
        
        Nom1.setBounds(1150, 800, 132, 47);
        Nom1.setFont(customFont);
        Nom2.setBounds(1550, 800, 132, 47);
        Nom2.setFont(customFont);
        
        panel.add(Nom1);
        panel.add(Nom2);
        
        ImageIcon Marc2 = new ImageIcon("Marco2.png");
        JLabel Mar5 = new JLabel();
        Mar5.setBounds(1150, 800, Nom1.getWidth(), Nom1.getHeight());
        Mar5.setIcon(new ImageIcon(Marc2.getImage().getScaledInstance(Nom1.getWidth(), Nom1.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(Mar5);
        
        JLabel Mar6 = new JLabel();
        Mar6.setBounds(1550, 800, Nom2.getWidth(), Nom2.getHeight());
        Mar6.setIcon(new ImageIcon(Marc2.getImage().getScaledInstance(Nom2.getWidth(), Nom2.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(Mar6);
        
        Pts1 = new JLabel("Puntos de "+Nombre1+": "+Puntos1,SwingConstants.CENTER);
        Pts1.setBounds(978, 350, 230, 57);
        Pts1.setFont(customFont);
        panel.add(Pts1);
        
        Pts2 = new JLabel("Puntos de "+Nombre2+": "+Puntos2,SwingConstants.CENTER);
        Pts2.setBounds(978, 450, 230, 57);
        Pts2.setFont(customFont);
        panel.add(Pts2);
        
        ImageIcon Marc1 = new ImageIcon("Marco1.png");
        JLabel Mar3 = new JLabel();
        Mar3.setBounds(978, 350, Pts1.getWidth(), Pts1.getHeight());
        Mar3.setIcon(new ImageIcon(Marc1.getImage().getScaledInstance(Pts1.getWidth(), Pts1.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(Mar3);
        
        JLabel Mar4 = new JLabel();
        Mar4.setBounds(978, 450, Pts2.getWidth(), Pts2.getHeight());
        Mar4.setIcon(new ImageIcon(Marc1.getImage().getScaledInstance(Pts2.getWidth(), Pts2.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(Mar4);
        
        GS = new JLabel("Guardar y Salir",SwingConstants.CENTER);
        
        GS.setBounds(40, 0, 135, 40);
        GS.setFont(customFont1);
        
        panel.add(GS);
        
        JLabel Mar1 = new JLabel();
        Mar1.setBounds(40, 0, GS.getWidth(), GS.getHeight());
        Mar1.setIcon(new ImageIcon(Marc1.getImage().getScaledInstance(GS.getWidth(), GS.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(Mar1);
        
        Carg = new JLabel("Cargar Partida",SwingConstants.CENTER);
        
        Carg.setBounds(215, 0, 135, 40);
        Carg.setFont(customFont1);
        
        panel.add(Carg);
        
        JLabel Mar2 = new JLabel();
        Mar2.setBounds(215, 0, Carg.getWidth(), Carg.getHeight());
        Mar2.setIcon(new ImageIcon(Marc1.getImage().getScaledInstance(Carg.getWidth(), Carg.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(Mar2);
        
        Img1 = new ImageIcon("Jugador1.png");
        img1 = new JLabel();
        img1.setBounds(1152, 640, 132, 172);
        img1.setIcon(new ImageIcon(Img1.getImage().getScaledInstance(132, 172, Image.SCALE_SMOOTH)));
        panel.add(img1);
        
        Img2 = new ImageIcon("Jugador2.png");
        img2 = new JLabel();
        img2.setBounds(1552, 640, 132, 172);
        img2.setIcon(new ImageIcon(Img2.getImage().getScaledInstance(132, 172, Image.SCALE_SMOOTH)));
        panel.add(img2);
        
        Img3 = new ImageIcon("Flecha1.png");
        img3 = new JLabel();
        img3.setBounds(1335, 650, 172, 142);
        img3.setIcon(new ImageIcon(Img3.getImage().getScaledInstance(172, 142, Image.SCALE_SMOOTH)));
        panel.add(img3);
        
        
        
        ImgR1 = new ImageIcon("reloj1.png");
        ImgR2 = new ImageIcon("reloj2.png");
        ImgR3 = new ImageIcon("reloj3.png");
        ImgR4 = new ImageIcon("reloj4.png");
        ImgR5 = new ImageIcon("reloj5.png");
        ImgR6 = new ImageIcon("reloj6.png");
        ImgR7 = new ImageIcon("reloj7.png");
        ImgR8 = new ImageIcon("reloj8.png");
        img5 = new JLabel();
        img5.setBounds(968, 50, 200, 200);
        img5.setIcon(new ImageIcon(ImgR1.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        panel.add(img5);
    }
    
    //Declaracion Jlabel de Lista
    private void colocarTema(){
    
        palabras = new JLabel [16];
        int list = 45;
         
        for (int i=0; i < 16; i++){
        palabras [i] = new JLabel(TemaX[i],SwingConstants.CENTER);
        palabras [i].setBounds(1350, list, 132, 32);
        palabras[i].setFont(customFont);

        panel.add(palabras[i]);
        list = list+33;
        }
            
        Img4 = new ImageIcon("Libreta.png");
        img4 = new JLabel();
        img4.setBounds(1220, 0, 374, 654);
        img4.setIcon(new ImageIcon(Img4.getImage().getScaledInstance(374, 654, Image.SCALE_SMOOTH)));
        panel.add(img4);      
            
    }
    
    //Declaracion de Botones de salida, entrada etc...
    private void colocarBotones(){
        ImageIcon BGyS = new ImageIcon("GyS.png");
        GyS = new JButton();
        GyS.setBounds(0, 0, 40, 40);
        GyS.setIcon(new ImageIcon(BGyS.getImage().getScaledInstance(GyS.getWidth(),GyS.getHeight(),Image.SCALE_SMOOTH)));
        
        panel.add(GyS);
        
        ImageIcon BCarg = new ImageIcon("Cargar.png");
        Cargar = new JButton();
        Cargar.setBounds(175, 0, 40, 40);
        Cargar.setOpaque(true);
        Cargar.setIcon(new ImageIcon(BCarg.getImage().getScaledInstance(Cargar.getWidth(),Cargar.getHeight(),Image.SCALE_SMOOTH)));
        
        panel.add(Cargar);
    }
    
    //Declaracion de Background 
    private void  colocarFondo(){

    
        //Etiqueta Tipo Imagen
        ImageIcon imagen = new ImageIcon("Fondo.jpg");
        JLabel etiqueta2 = new JLabel();
        etiqueta2.setBounds(0, 0, getWidth(), getHeight());
        etiqueta2.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiqueta2);
 
    }

    //Lectura y Escritura de Variables a los .text asignados
    private void leerData(){

    
    crearArchivo("Archivo\\Nombres.txt");
    
    escribirArchivo("Archivo\\Nombres.txt",""+Nombre1);
      escribirArchivo("Archivo\\Nombres.txt",""+Nombre2);
    
    crearArchivo("Archivo\\Turno.txt");
    
     escribirArchivo("Archivo\\Turno.txt",""+turnos);
    
    crearArchivo("Archivo\\Puntaje.txt");
    
      escribirArchivo("Archivo\\Puntaje.txt",""+Puntos1);
      
      escribirArchivo("Archivo\\Puntaje.txt",""+Puntos2);
      
      crearArchivo("Archivo\\Generacion.txt");
      
      for (int i = 1; i <  21; i++){
          for (int k = 1; k <  21; k++){
          escribirArchivo("Archivo\\Generacion.txt",""+sopita[i][k]);   
          }
      }
      
            
      
    crearArchivo("Archivo\\Presionado.txt");
      
    
        for (int i = 1; i <  16; i++){
         System.out.println("era: "+abecedario1[i]);
        
        }
    
     
       
    
        for (int i = 1; i <  16; i++){
         
              if (abecedario1[i]!=100){
              
                  System.out.println("Encontre: "+abecedario1[i]);
                  int baul = abecedario1[i]+1;
                  
          for (int z = 1; z <  21; z++){
          for (int k = 1; k <  21; k++){
                 

              if (sopita[z][k]==baul){
                   System.out.println("Encontre: "+baul);
                  preso[z][k]=1;
                        
              }
                  
                  
              }
                  
          
              }
              
              
             
    if (cuchara==1){
    if (baul==9){
         
    preso[8][10]=1; 

    }
    
    if (baul==10){
    preso[8][10]=1;
    }
           
    if (baul==11){
     preso [14][12]=1;
    }
    
     if (baul==13){
     preso [14][12]=1;
    }   
      }
    
    if (cuchara==2){ 
        
        if(baul==14){
          preso[11][10]=1;
        }
        
        if(baul==8){
          preso[11][10]=1;
        }
        
        if (baul==13){
         preso[1][3]=1;
        }
        
         if (baul==6){
         preso[1][3]=1;
        } 
      }
    
    if (cuchara==3){ 
        
        if (baul==14){
         preso[7][1]=1;
        }
        
        if (baul==9){
         preso[7][1]=1;
        }
        
        if (baul==5){
         preso[15][12]=1; 
        }
        
         if (baul==10){
        preso[15][12]=1; 
        }   
      }
    
     if (cuchara==4){ 
         
             if (baul==1){
       preso[8][10]=1;
        }
               if (baul==14){
        preso[8][10]=1;
        }
           
          if (baul==13){
        preso[17][5]=1;
        }
          
           if (baul==9){
        preso[17][5]=1;
        }  
      }
     
     if (cuchara==5){ 
            if (baul==13){
         preso[10][7]=1;
        }  
            
            if (baul==12){
         preso[10][7]=1;
        }  
         
             if (baul==11){
          preso[4][4]=1;
        }  
             
             if (baul==8){
          preso[4][4]=1;
        }  
      }
     
        if (cuchara==6){ 
             if (baul==10){
             preso[9][11]=1;
        }  
              if (baul==11){
             preso[9][11]=1;
        }  
              
               if (baul==1){
              preso[9][1]=1; 
        }  
               
                if (baul==1){
             preso[9][1]=1; 
        }
          
      }
    
          
      }
              
              
              }
        
        
         for (int i = 1; i <  16; i++){
         
              
              
      if (abecedario2[i]!=100){
              
          int baul = abecedario2[i]+1;
                  
                 for (int z = 1; z <  21; z++){
                 for (int k = 1; k <  21; k++){
                 
                          if (cuenco[z][k]==baul){
                          preso[z][k]=2;
                          }
                                      }

                          }
                  
   if (cuchara==1){
                  
            if (baul==9){
            preso[8][10]=2; 
            }
                  
            if (baul==10){
            preso[8][10]=2;
            }
                  
            if (baul==11){
            preso [14][12]=2;
            }
    
            if (baul==13){
             preso [14][12]=2;
            }   
                      }
    
    if (cuchara==2){ 
        
        if(baul==14){
          preso[11][10]=2;
        }
        
        if(baul==8){
          preso[11][10]=2;
        }
        
        if (baul==13){
         preso[1][3]=2;
        }
        
         if (baul==6){
         preso[1][3]=2;
        } 
      }
    
    if (cuchara==3){ 
        
        if (baul==14){
         preso[7][1]=2;
        }
        
        if (baul==9){
         preso[7][1]=2;
        }
        
        if (baul==5){
         preso[15][12]=2; 
        }
        
         if (baul==10){
        preso[15][12]=2; 
        }   
      }
    
     if (cuchara==4){ 
         
             if (baul==1){
       preso[8][10]=2;
        }
               if (baul==14){
        preso[8][10]=2;
        }
           
          if (baul==13){
        preso[17][5]=2;
        }
          
           if (baul==9){
        preso[17][5]=2;
        }  
      }
     
     if (cuchara==5){ 
            if (baul==13){
         preso[10][7]=2;
        }  
            
            if (baul==12){
         preso[10][7]=2;
        }  
         
             if (baul==11){
          preso[4][4]=2;
        }  
             
             if (baul==8){
          preso[4][4]=2;
        }  
      }
     
        if (cuchara==6){ 
             if (baul==10){
             preso[9][11]=2;
        }  
              if (baul==11){
             preso[9][11]=2;
        }  
              
               if (baul==1){
              preso[9][1]=2; 
        }  
               
                if (baul==1){
             preso[9][1]=2; 
        }
          
      }
  }
                     
}
          
 for (int z = 1; z <  21; z++){
          for (int k = 1; k <  21; k++){
          
             escribirArchivo("Archivo\\Presionado.txt",""+ preso[z][k]);
          
          }}
         
         
              
         

        
        
        
        

    
    crearArchivo("Archivo\\Tema.txt");
    
     escribirArchivo("Archivo\\Tema.txt",""+cuchara);   
    
        System.out.println("La CUCHARA ERA:"+cuchara);
    
     escribirArchivo("Archivo\\Inicio.txt","0");
        System.out.println("Se puso 0");
    

}
    
    //Carga de Datos desde los .text a las Variables
    private void CargaData(){
        String str = null;
        int u=0,f=0,co=0;
//Cargar Nombres        
         try (BufferedReader reader = new BufferedReader(new FileReader("Archivo\\Nombres.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file here
                
                  u++;
               
                if(u==1)
                Nombre1=line;
                
               if(u==2)
                Nombre2=line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
         
          Nom1.setText("");
        Nom2.setText("");
        Nom1.setText(Nombre1);
        Nom2.setText(Nombre2);
        
//Cargar Puntos

u=0;

  try (BufferedReader reader = new BufferedReader(new FileReader("Archivo\\Puntaje.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file here
                
                  u++;
                
                if(u==1)
                    str = line;
            Puntos1= Integer.parseInt(str);
                
               if(u==2)
                   str = line;
                Puntos2 = Integer.parseInt(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
  
   Pts1.setText("Puntos de Jugador 1: "+Puntos1);
   Pts2.setText("Puntos de Jugador 1: "+Puntos2);
    

//Cargar Tema 

 try (BufferedReader reader = new BufferedReader(new FileReader("Archivo\\Tema.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file here
                str = line;
                cuchara = Integer.parseInt(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

 //Cargar Matriz y Generacion 
 
 if (cuchara == 1|| cuchara == 2){
        //Igualacion de Temas
            for (int i =0; i<16; i++){
                        
                TemaX[i]=Tema1[i];
                                }
 }
 
 if (cuchara == 3|| cuchara == 4){
        //Igualacion de Temas
            for (int i =0; i<16; i++){
                        
                TemaX[i]=Tema2[i];
                                }
 }
 
  if (cuchara == 5|| cuchara == 6){
        //Igualacion de Temas
            for (int i =0; i<16; i++){
                        
                TemaX[i]=Tema3[i];
                                }
 }
  
  for (int i=0; i < 16; i++){
   
              palabras[i].setText("");
             
 
   
   }
  
  
   for (int i=0; i < 16; i++){
   
              palabras[i].setText(TemaX[i]);
             
 
   
   }
  
 
 f=1;
 co=1;
 
  try (BufferedReader reader = new BufferedReader(new FileReader("Archivo\\Generacion.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file here
                str = line;
                sopita [f][co] = Integer.parseInt(str);
                
                co++;
                        
                        if(co==21){
                        co=1;
                        f++;
                        }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
  
  
   for(int i = 0; i<20; i++){
                for(int k = 0; k<20; k++){
                   espacio[i][k].setText("");
               
                }
            }
            
  
  int X=0;
  int direccion=0, precio, Posicion=1,Pase=0, Zona = 0, p = 0, palabreo = 0;
  
   X=0;
   
//Re - Generacion del tablero con matriz clon    
    do{
        
    if(cuchara==1){
        
         if(X==8){
               TemaX[X]="COBLTO";
                 }
         
         if(X==9){
              TemaX[X]="RGON";
                 }
            
         if(X==10) {
                TemaX[X]="NEN";
                 }
         
         if(X==12) {
                TemaX[X]="BRO";
                 }
     }
                     
     if(cuchara==2){
                     
         if(X==13) {
                 TemaX[X]="HERRO";
                 }
                     
         if(X==7) {
                 TemaX[X]="SILICO";
                 
                 }
         
         if(X==5){
                   TemaX[X]="CORE";
                 }
         
         if(X==12){
                 TemaX[X]="ORO";
                 }
         
     }
     
     if(cuchara==3){
                 
          if(X==13){
                   TemaX[X]="POWERPINT";
                 }
                     
          if(X==8){
                    TemaX[X]="BS";
                  }
                     
          if(X==9){
                   TemaX[X]="NTBEAMS";
                  }
                     
          if(X==4){
                    TemaX[X]="OPRA";
                  }
                  
     }   
      if(cuchara==4){
                 
           if(X==0){
                   TemaX[X]="ADBE";
                   }
                   
           if(X==13){
                     TemaX[X]="POWERPINT";
                     }
           
          if(X==12){
                    TemaX[X]="ZOM";
                  }
                     
          if(X==8){
                     TemaX[X]="BS";
                 }
     }
      
     if(cuchara==5){
                
          if(X==12){
                      TemaX[X]="MSA";
                     }  
                  
          if(X==11){
                      TemaX[X]="XAMEN";
                  }
          
          if(X==10){
                       TemaX[X]="SILA";
                  }
          
          if(X==7){
                      TemaX[X]="IBRO";
                 }
          
         }
     
     if(cuchara==6){
                 //-1
            if(X==9){
                    TemaX[X]="FCHA";
                 }
                     
            if(X==10){
                    TemaX[X]="SLLA";
                 }  
            
            if(X==0){
                    TemaX[X]="LPIZ";
                  } 
            
                  //-1   
            if(X==13){
                    TemaX[X]="NOT";
                 }
                 
                 
                 
                 }
        
        
     
    
      
// Convertir la cadena en un arreglo de caracteres
char[] arregloCaracteres = TemaX[X].toCharArray();
precio=X+1;
Zona=1;
// Imprimir el arreglo de caracteres

  direccion = (int)(Math.random()*2 + 1); 

for (char c : arregloCaracteres) {
   
    Posicion=1;
    Pase=0;
    String cadena = String.valueOf(c);
                 
  
    
      
       if(cuchara==1){
                 //-1
                     if(X==8)
                     {
                     direccion=2;
                     }
                     
                 
                     
                     if(X==9)  {
                     direccion=1;
                     }
                     
                     if(X==10) {
                     direccion=1;

                     }
                     
                     if(X==12) {
                    direccion=1;

                     }
        
                 }
                 if(cuchara==2){
                 
                     if(X==13) {
                   direccion=1;

                     }
                     
                     if(X==7) {
                   direccion=1;

                     }
                     
                     if(X==5){
                     direccion=1;

                     }
                     
                     if(X==9){
                     direccion=1;

                     }
                 
                 }
                 if(cuchara==3){
                 
                  if(X==13){
                 direccion=1;

                     }
                     
                  if(X==8){
                direccion=1;

                     }
                   //-1  
                  if(X==9){
                   direccion=2;

                     }
                     
                  if(X==4){
                 direccion=1;

                     }
                 
                 }   
                 if(cuchara==4){
                 
                   if(X==0){
                   direccion=1;

                     }
                     //-1
                  if(X==13){
                   direccion=2;

                     }
                  if(X==12){
                   direccion=1;

                     }
                     //-1
                  if(X==8){
                   direccion=2;

               
                     }  
                 
                 
                 }
                 if(cuchara==5){
                 //-1
                  if(X==12){
                  direccion=2;

                     }  
                     
                  if(X==11){
                 direccion=1;

                     }
                     
                  if(X==10){
                   direccion=1;

                     }
                     
                  if(X==7){
                   direccion=1;

                     } 
                     
                 
                 }
                 if(cuchara==6){
                 //-1
                    if(X==9){
                    direccion=2;

                     } 
                     
                  if(X==10){
                   direccion=1;

                     } 
                     
                  if(X==0){
                    direccion=1;

                     } 
                  //-1   
                  if(X==13){
                     direccion=2;

                     } 
                 }
                 
                 
                 
    if(direccion==1){
                 
        for(int i = 1; i<21; i++){
        for(int k = 1; k<21; k++){
       
    
         if (Pase!=1){
                    if(sopita[i][k]==precio){
                            if(Posicion==Zona){
                                System.out.println("ESOOO");
                            espacio[k-1][i-1].setText(cadena);
                            p++;   
                            i=100;
                            k=100;
                            Zona++;
                        }else{Posicion++;
                }
              }
            }
          }
        }
      }  
      
       if(direccion==2){
    
                for(int i = 20; i>0; i--){
                for(int k = 20; k>0; k--){
                      
                if (Pase!=1){
                         
                        if(sopita[i][k]==precio){
                             
                                    if(Posicion==Zona){
                              
                                        System.out.println("ESOOO");
                                        espacio[k-1][i-1].setText(cadena);
                                          p++;   
                                           Zona++;
                                            i=-100;
                                            k=-100;
                                                }else{
                                              Posicion++;
                                            }
                                        }
                                }else{
                                }
                           } 
                        }
                    }
       
     
       
              }     
      System.out.println("ESOOO"+precio);
X++;                
palabreo++;                  
     
    }while(palabreo!=16); 
    
    
     if(cuchara==1){
        
               TemaX[8]="COBALTO";
              TemaX[9]="ARGON";
                TemaX[10]="NEON";
                TemaX[12]="BORO";
                 
     }
                     
     if(cuchara==2){
                     
                 TemaX[13]="HIERRO";
                 TemaX[7]="SILICIO";
                   TemaX[5]="COBRE";
                 TemaX[12]="BORO";
                 
         
     }
     
     if(cuchara==3){
                   TemaX[13]="POWERPOINT";
              
                    TemaX[8]="OBS";
                 
                   TemaX[9]="NETBEAMS";
             
                    TemaX[4]="OPERA";
                  
                  
     }   
      if(cuchara==4){
                   TemaX[0]="ADOBE";
                
                     TemaX[13]="POWERPOINT";
                 
                    TemaX[12]="ZOOM";
                     TemaX[8]="OBS";
     }
      
     if(cuchara==5){
                
                      TemaX[5]="MESA";
                    
                      TemaX[11]="EXAMEN";
                
                       TemaX[10]="SILLA";
                 
                      TemaX[7]="LIBRO";
                 
          
         }
     
     if(cuchara==6){
                    TemaX[9]="FICHA";
               
                    TemaX[10]="SILLA";
                 
                    TemaX[0]="LAPIZ";
                  
                    TemaX[13]="NOTA";
                  
                 }
    
    
    
    
    
    
                for(int i = 1; i<21; i++){
                for(int k = 1; k<21; k++){
                
                if(sopita[i][k]==0){
                    
                      String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                      Random random = new Random();
                      char randomLetter = alphabet.charAt(random.nextInt(alphabet.length()));
                      String VALOR = String.valueOf(randomLetter);
                      espacio[k-1][i-1].setText(VALOR);
                }
            }
            }
                
                 for(int i = 1; i<21; i++){
                for(int k = 1; k<21; k++){
                
                cuenco[i][k]=sopita[i][k];
            }
            }
                
                
                
     if(cuchara==1){
        
         espacio[9][7].setText("A");
         espacio[11][13].setText("O");
               
     }
        
     if(cuchara==2){
          espacio[9][10].setText("I");   
          espacio[2][0].setText("B"); 
             
     }
                 
     if(cuchara==3){
            espacio[0][6].setText("O");      
            espacio[11][14].setText("E"); 
                 
     }   
     if(cuchara==4){
              espacio[9][7].setText("O");    
              espacio[4][16].setText("O"); 
                 
                 
     }
     if(cuchara==5){
              espacio[6][9].setText("E");    
              espacio[3][3].setText("L"); 
             
                     
                 
     }
     if(cuchara==6){
              espacio[10][8].setText("I"); 
              espacio[0][8].setText("A"); 
   
    
    }
     
//Carga de la Matriz de zonas Presionadas anteriormente     
f=1;
co=1;
   
 try (BufferedReader reader = new BufferedReader(new FileReader("Archivo\\Presionado.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file here
                str = line;
                preso [f][co] = Integer.parseInt(str);
                
                co++;
                        
                        if(co==21){
                        co=1;
                        f++;
                        }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
 
 //For de Presionar

   for(int i = 1; i<21; i++){
             for(int k = 1; k<21; k++){
                 
                   if(preso[i][k]==1){
                         
                            espacio[k-1][i-1].setBackground(Color.BLUE);}
                   
                      if(preso[i][k]==2){
                         
                            espacio[k-1][i-1].setBackground(Color.RED);}
                 
                 
             }
             }
   
   
    //Tachar
    
    
     for(int i = 0; i<16; i++){
     
     if (abecedario1[0]==0)
     Tacha[0].setLocation(1350, 60);
     
     if (abecedario1[1]==1)
     Tacha[1].setLocation(1350, 93);
     
     if (abecedario1[2]==2)
     Tacha[2].setLocation(1350, 126);
     
     if (abecedario1[3]==3)
     Tacha[3].setLocation(1350, 159);
     
     if (abecedario1[4]==4)
     Tacha[4].setLocation(1350, 192);
     
     if (abecedario1[5]==5)
     Tacha[5].setLocation(1350, 225);
     
     if (abecedario1[6]==6)
     Tacha[6].setLocation(1350, 258);
     
     if (abecedario1[7]==7)               
     Tacha[7].setLocation(1350, 291);
     
     if (abecedario1[8]==8)
     Tacha[8].setLocation(1350, 324);
          
     if (abecedario1[9]==9)
     Tacha[9].setLocation(1350, 357);
        
     if (abecedario1[10]==10)
     Tacha[10].setLocation(1350, 390);
        
     if (abecedario1[11]==11)
     Tacha[11].setLocation(1350, 423);
     
     if (abecedario1[12]==12)
     Tacha[12].setLocation(1350, 456);
                        
     if (abecedario1[13]==13)
     Tacha[13].setLocation(1350, 489);
                           
     if (abecedario1[14] ==14)
     Tacha[14].setLocation(1350, 522);
     
     if (abecedario1[15]==15)
     Tacha[15].setLocation(1350, 555);
     
     
     
      if (abecedario2[0]==0)
     Tacha[0].setLocation(1350, 60);
     
     if (abecedario2[1]==1)
     Tacha[1].setLocation(1350, 93);
     
     if (abecedario2[2]==2)
     Tacha[2].setLocation(1350, 126);
     
     if (abecedario2[3]==3)
     Tacha[3].setLocation(1350, 159);
     
     if (abecedario2[4]==4)
     Tacha[4].setLocation(1350, 192);
     
     if (abecedario2[5]==5)
     Tacha[5].setLocation(1350, 225);
     
     if (abecedario2[6]==6)
     Tacha[6].setLocation(1350, 258);
     
     if (abecedario2[7]==7)               
     Tacha[7].setLocation(1350, 291);
     
     if (abecedario2[8]==8)
     Tacha[8].setLocation(1350, 324);
          
     if (abecedario2[9]==9)
     Tacha[9].setLocation(1350, 357);
        
     if (abecedario2[10]==10)
     Tacha[10].setLocation(1350, 390);
        
     if (abecedario2[11]==11)
     Tacha[11].setLocation(1350, 423);
     
     if (abecedario2[12]==12)
     Tacha[12].setLocation(1350, 456);
                        
     if (abecedario2[13]==13)
     Tacha[13].setLocation(1350, 489);
                           
     if (abecedario2[14] ==14)
     Tacha[14].setLocation(1350, 522);
     
     if (abecedario2[15]==15)
     Tacha[15].setLocation(1350, 555);
     
     
     }
     
     //Reiniciar Tiempo
     Seg=0;
    
    
   
   }
    
    //Listado de Palabras por tema
    private void Temas(){
        //Elementos
        Tema1 [0] = "HELIO"; //5u - 5 letras
        Tema1 [1] = "CLORO"; //10u - 5 letras 
        Tema1 [2] = "SODIO"; //15u - 5 letras 
        Tema1 [3] = "ORO";  //12u - 3 letras 
        Tema1 [4] = "PLATA"; //25u - 5 letras 
        Tema1 [5] = "COBRE"; //30u - 5 letras 
        Tema1 [6] = "PLOMO"; //35u - 5 letras 
        Tema1 [7] = "SILICIO"; //56u - 7 letras  
        Tema1 [8] = "COBALTO"; //63u - 7 letras 
        Tema1 [9] = "ARGON"; //50u - 5 letras 
        Tema1 [10] = "NEON"; //44u - 4 letras 
        Tema1 [11] = "LITIO"; //60u - 5 letras
        Tema1 [12] = "BORO"; //52u - 4 letras 
        Tema1 [13] = "HIERRO"; //84u 6 letras 
        Tema1 [14] = "CROMO"; // 5x15 = 75
        Tema1 [15] = "ZINC";// 4x16 = 64
        
        // 680 pts totales
        
        //Programas de PC
        Tema2 [0] = "ADOBE"; // 5u - 5 letras 
        Tema2 [1] = "WORD"; // 8u - 4 letras 
        Tema2 [2] = "EXCEL"; // 15u - 5 letras 
        Tema2 [3] = "CANVA"; // 20u - 5 letras 
        Tema2 [4] = "OPERA"; // 25u - 5 letras 
        Tema2 [5] = "GOOGLE"; // 36u - 6 letras 
        Tema2 [6] = "PAINT"; // 35u- 5 letras 
        Tema2 [7] = "ACCESS"; // 48u - 6 letras  
        Tema2 [8] = "OBS"; // 27u - 3 letras 
        Tema2 [9] = "NETBEANS"; // 80u - 8 letras 
        Tema2 [10] = "VISUAL"; // 66u- 6 letras 
        Tema2 [11] = "DISCORD"; // 84u - 7 letras 
        Tema2 [12] = "ZOOM"; // 52u - 4 letras
        Tema2 [13] = "POWERPOINT"; // 140u - 10 letras 
        Tema2 [14] = "CALCULADORA"; //11x15 = 165
        Tema2 [15] = "PUBLISHER"; // 9x16 = 144
        
        //Sumatoria Total 950pts
        
        //Regreso a clases
        Tema3 [0] = "LAPIZ"; // 5 letras - 5u
        Tema3 [1] = "LAPICERO"; // 8 letras - 16u 
        Tema3 [2] = "BORRADOR"; // 8 letras - 24u
        Tema3 [3] = "BOLSO"; // 5 letras - 20u
        Tema3 [4] = "COMPAS"; // 6 letras - 30u
        Tema3 [5] = "CUADERNO"; // 8 letras - 48u
        Tema3 [6] = "REGLA"; // 5 letras - 35u
        Tema3 [7] = "LIBRO"; // 5 letras - 40u
        Tema3 [8] = "HOJA"; // 4 letras - 36u
        Tema3 [9] = "FICHA"; // 5 letras - 50u
        Tema3 [10] = "SILLA"; // 5 letras - 55u
        Tema3 [11] = "EXAMEN"; // 6 letras - 72u
        Tema3 [12] = "MESA"; // 4 letras - 52u
        Tema3 [13] = "NOTA"; // 4 letras - 56u
        Tema3 [14] = "COLORES"; // 7x15 = 105
        Tema3 [15] = "PEGAMENTO"; // 9x16 = 144
        
        //Sumatoria Total  788pts
        
    }

    //Generacion Pseudo - Aleatoria de la Matriz Numerica
    private void backend(){

        int selection, selectionX; 
        int Generacion = 0, ayuda=0;
        int x,y,z,w,X=0,p=0;
        int direccion;
        int Ghost=0;
        int serie=0;
        int precio=0;
        int key=0;
        int orden=0;
        int Acumulation=0, Llave=0;
        int tamano = 0, tamaño=0;
        int proceso = 0, Posicion=0, Zona=0;
        int palabreo=0, Pase=0, AVER=0;
        
        
         
         
        
        

        //Random Tema
    selection = (int)(Math.random()*3 + 1); 
    selectionX = (int)(Math.random()*2 + 1); 
    
   
    
    
//TEMA #1

    do{

            if (selection == 1){
        //Igualacion de Temas
            for (int i =0; i<16; i++){
                        
                TemaX[i]=Tema1[i];
                                }

                //Eleccion de Tablero A-B
           
       

    //Tablero A
                if (selectionX==1){
                //Choques #1 
                //COBALTO-ARGON (cobalto -1) 

                cuenco[5][10]=9; 
                cuenco[6][10]=9;
                cuenco[7][10]=9; 
                cuenco[8][10]=19; 
                cuenco[9][10]=9; 
                cuenco[10][10]=9; 
                cuenco[11][10]=9; 

                cuenco[8][11]=10;
                cuenco[8][12]=10; 
                cuenco[8][13]=10;
                cuenco[8][14]=10;

                //NEON - BORO
       
                cuenco [14][10]=11 ;
                cuenco [14][11]=11;
                cuenco [14][12]=24;
                cuenco [14][13]=11 ;

                cuenco [16][14]=13; 
                cuenco [13][11]=13;
                cuenco[15][13]=13;
                
                cuchara=1;
                                }
                
    //Tablero B
                if (selectionX==2){
                //Choques #2
                //SILICIO-HIERRO 

                cuenco[10][10]=14;
                cuenco[11][10]=22;
                cuenco[12][10]=14;
                cuenco[13][10]=14;
                cuenco[14][10]=14;
                cuenco[15][10]=14; 
          
                cuenco[11][11]=8;
                cuenco[11][9]=8;
                cuenco[11][8]=8;
                cuenco[11][7]=8;
                cuenco[11][6]=8;
                cuenco[11][5]=8;

                //COBRE-BORO ESTA MAL
                cuenco[1][1]=6;
                cuenco[1][2]=6;
                cuenco[1][3]=19;
                cuenco[1][4]=6;
                cuenco[1][5]=6;

                cuenco[2][3]=13;
                cuenco[3][3]=13;
                cuenco[4][3]=13;
                
                cuchara=2;
                
                                }    
                
                                        }
                

//TEMA #2
            if (selection == 2){
            //Igualacion de Temas

            for (int i =0; i<16; i++){
            
                TemaX[i]=Tema2[i];
                                    }

          //Eleccion de Tablero A-B
 
  
    //Tablero A
                if (selectionX==1){
                
                //POWERPOINT-OBS 
                cuenco[1][1]=14; 
                cuenco[2][1]=14; 
                cuenco[3][1]=14;
                cuenco[4][1]=14;
                cuenco[5][1]=14;
                cuenco[6][1]=14;
                cuenco[7][1]=23;
                cuenco[8][1]=14;
                cuenco[9][1]=14;
                cuenco[10][1]=14; 

                cuenco[7][2]=9;
                cuenco[7][3]=9;


                //OPERA - NETBEANS(-1) 
                cuenco[9][12]=10; 
                cuenco[10][12]=10; 
                cuenco[11][12]=10; 
                cuenco[12][12]=10; 
                cuenco[13][12]=10; 
                cuenco[14][12]=10; 
                cuenco[15][12]=15; 
                cuenco[16][12]=10; 

                cuenco[15][10]=5; 
                cuenco[15][11]=5; 
                cuenco[15][13]=5; 
                cuenco[15][14]=5;
                
                cuchara=3;
                
            }
            
        //Tablero B         
                if (selectionX==2){
                //Choques #4
                //POWERPOINT(-1)-ADOBE     
                cuenco[6][10]=1; 
                cuenco[7][10]=1; 
                cuenco[8][10]=17; 
                cuenco[9][10]=1; 
                cuenco[10][10]=1; 

                cuenco[8][9]=14;
                cuenco[8][8]=14; 
                cuenco[8][7]=14;
                cuenco[8][11]=14;
                cuenco[8][12]=14;
                cuenco[8][13]=14; 
                cuenco[8][14]=14; 
                cuenco[8][15]=14; 
                cuenco[8][16]=14;


                //ZOOM-OBS(-1) 
                cuenco[16][5]=13; 
                cuenco[17][5]=22;
                cuenco[18][5]=13;
                cuenco[19][5]=13;
                
                cuenco[17][4]=9;
                cuenco[17][3]=9;
                
                cuchara=4;
                    }
                                    }
                
                
        
//TEMA #3        
        if (selection == 3){
            for (int i =0; i<16; i++){
          
                    TemaX[i]=Tema3[i];
                                }

      


    //Tablero A            
                if (selectionX==1){
                //Choques #5
                //MESA(-1)-EXAMEN 
                cuenco[10][5]=13;
                cuenco[10][6]=13;
                cuenco[10][7]=25;
                cuenco[10][8]=13;

                cuenco[11][7]=12;
                cuenco[12][7]=12;
                cuenco[13][7]=12;
                cuenco[14][7]=12;
                cuenco[15][7]=12;
               

                //SILLA-LIBRO 
                cuenco[1][1]=11; 
                cuenco[2][2]=11; 
                cuenco[3][3]=11; 
                cuenco[4][4]=19; 
                cuenco[5][5]=11; 

                cuenco[4][5]=8; 
                cuenco[4][6]=8; 
                cuenco[4][7]=8; 
                cuenco[4][8]=8; 
                
                
                cuchara=5;
                                }
    //Tablero B  
    
            if (selectionX==2){  
              //Choques #6
              //FICHA(-1)-SILLA 
                cuenco[6][8]=10; 
                cuenco[7][9]=10; 
                cuenco[8][10]=10;
                cuenco[9][11]=21; 
                cuenco[10][12]=10; 

                cuenco[8][11]=11; 
                cuenco[10][11]=11; 
                cuenco[11][11]=11; 
                cuenco[12][11]=11; 

                //LAPIZ-NOTA(-1)
                cuenco[8][1]=1; 
                cuenco[9][1]=15; 
                cuenco[10][1]=1; 
                cuenco[11][1]=1; 
                cuenco[12][1]=1; 

                cuenco[9][2]=14; 
                cuenco[9][3]=14;
                cuenco[9][4]=14;
              
                cuchara=6;
                        }
                                }
//Generacion de Coordenadas 

        do{
           
            
//Manejo de Excepciones para los Choques                 
                 
         

                 if(cuchara==1){
                 
                     if(tamano==8)
                     tamano++;
                     
                     if(tamano==9)
                     tamano++;
                     
                     if(tamano==10)
                     tamano++;
                     
                     if(tamano==12)
                     tamano++;
        
                 }
                 if(cuchara==2){
                 
                     if(tamano==13)
                     tamano++;
                     
                     if(tamano==7)
                     tamano++;
                     
                     if(tamano==5)
                     tamano++;
                     
                     if(tamano==12)
                     tamano++;
                 
                 }
                 if(cuchara==3){
                 
                  if(tamano==13)
                     tamano++;
                     
                  if(tamano==8)
                     tamano++;
                     
                  if(tamano==9)
                     tamano++;
                     
                  if(tamano==4)
                     tamano++;
                 
                 }   
                 if(cuchara==4){
                 
                       if(tamano==0)
                     tamano++;
                     
                  if(tamano==13)
                     tamano++;
                     
                  if(tamano==12)
                     tamano++;
                     
                  if(tamano==8)
                     tamano++;  
                 
                 
                 }
                 if(cuchara==5){
                 
                  if(tamano==12)
                     tamano++;
                     
                  if(tamano==11)
                     tamano++;
                     
                  if(tamano==10)
                     tamano++;
                     
                  if(tamano==7)
                     tamano++; 
                     
                 
                 }
                 if(cuchara==6){
                 
                    if(tamano==9)
                     tamano++;
                     
                  if(tamano==10)
                     tamano++;
                     
                  if(tamano==0)
                     tamano++;
                     
                  if(tamano==13)
                     tamano++;         
                 }
                 
//revision de zona libre                 
        
             x = (int)(Math.random()*20 + 1); 
             y = (int)(Math.random()*20 + 1); 
        
             z = x;
             w = y;      
             
             key=0;
             
        serie = TemaX[tamano].length();
     
        precio = tamano+1;
        
         if (cuenco[x][y]==0){
             
            direccion = (int)(Math.random()*8 + 1); 
               
                     for (int i=0; i<serie; i++){
             
                           if(direccion==1){
                   
                               if(x>0&&x<21){
                   
                                   if(cuenco[x][y]==0){
                                   cuenco[x][y]=precio;
                                   key++; 
                                   x++;
                                   proceso++;
                               }
                               }
                               }

               
                           if(direccion==2){
                   
                               if(x>0&&x<21){

                                   if(cuenco[x][y]==0){

                                       cuenco[x][y]=precio;
                                       key++;
                                       x--;
                                        proceso++;
                     
                               }
                               }
                               }
             
                           if(direccion==3){
                   
                               if(y>0&&y<21){
                                   
                                    if(cuenco[x][y]==0){
                  
                                        cuenco[x][y]=precio;
                                        key++;
                                        y++;
                                         proceso++;
                               }
                               }
                               }
               
                           if(direccion==4){
                   
                               if(y>0&&y<21){
                                   
                                   if(cuenco[x][y]==0){
                                       
                                      cuenco[x][y]=precio;
                                      key++;
                                      y--;
                                       proceso++;
                               }
                               }
                               }
                           
               
                           if(direccion==5){
                   
                               if(x>0&&x<21&&y>0&&y<21){
                   
                                    if(cuenco[x][y]==0){
                                        
                                       cuenco[x][y]=precio;
                                       key++;
                                       x++;
                                       y++;
                                        proceso++;
                           }
                           }
                           }
               
                          if(direccion==6){
                      
                              if(x>0&&x<21&&y>0&&y<21){
                    
                                  if(cuenco[x][y]==0){
                                      
                                   cuenco[x][y]=precio;
                                      key++;
                                      x++;
                                      y--;
                                       proceso++;
                           }
                           }
                           } 
                  
                         if(direccion==7){
                       
                                 if(x>0&&x<21&&y>0&&y<21){
                                     
                                     if(cuenco[x][y]==0){
                                         
                                       cuenco[x][y]=precio;
                                        key++;
                                        x--;
                                        y++;
                                         proceso++;
                         }
                         }
                         }
                     
                        if(direccion==8){
                            
                             if(x>0&&x<21&&y>0&&y<21){
                                 
                                 if(cuenco[x][y]==0){
                                     
                                       cuenco[x][y]=precio;
                                        key++;
                                        x--;
                                        y--;
                                         proceso++;
                         }
                         }
                         }
              
             if(proceso==0){
                 i=100;
                 }
               
             proceso=0;
                 
             }
                   
             if(key==serie)
                 {
                     Generacion++;
                        tamano++;
                             
                 }
             
                 if (key!=serie){
             
                     for (int i=0; i<key; i++){ 
                         
                       if(direccion==1){
                        cuenco[z][w]=0;
                        z++;
                       }
               
                       if(direccion==2){
                       cuenco[z][w]=0;
                         z--;
                       }
                   
                       if(direccion==3){
                       cuenco[z][w]=0;
                        w++;
                       }
                
                       if(direccion==4){
                       cuenco[z][w]=0;
                       w--;
                       }
                   
                       if(direccion==5){
                       cuenco[z][w]=0;
                       z++;
                       w++;
                       }
       
                      if(direccion==6){
                      cuenco[z][w]=0;
                      w--;
                      z++;
                       }
                  
                      if(direccion==7){
                        cuenco[z][w]=0; 
                        z--;
                        w++;
                       }
               
                      if(direccion==8){
                       cuenco[z][w]=0;   
                       z--;
                       w--;
                       
                     
                       }
                       
                      
                 }
             }
         }
         
         ayuda++;
         if(ayuda==500){
         Generacion=12;
         ayuda=0;
         }
            
            
        }while(Generacion!=12);
            
          
                orden++;
                
                    if(Generacion==12){
            
                     for (int i = 1; i < 21; i++){
                     for (int k = 1; k < 21; k++){
             
                    Acumulation= cuenco[i][k]+Acumulation;
            
                                                }
                                                }
                                        }
                    
            
             
                     if(cuchara==1 ||cuchara==2){
           
                             if(Acumulation==680){
           
                                 Ghost=10;
                                  
                                
                             }else{
                                 
                                 
                                     
                     for (int i = 1; i < 21; i++){
                     for (int k = 1; k < 21; k++){
                         
                                 cuenco[i][k]=0;
             
                    }
                    }
             }
         }
        
                     if(cuchara==3 ||cuchara==4){
                                                                    
                             if(Acumulation==950){
                               Ghost=10;
 
                                 }else if(Acumulation==952){
                                       Ghost=10;
                                 }else{
                     
                                for (int i = 1; i < 21; i++){
                                for (int k = 1; k < 21; k++){
             
                                  cuenco[i][k]=0;
                           
                                }
                            }
                         }
                     }
                              
                     if(cuchara==5 ||cuchara==6){
             
                         if(Acumulation==788){
                              Ghost=10;
                           
                                 }else{
                                 
                        for (int i = 1; i < 21; i++){
                        for (int k = 1; k < 21; k++){
                            
                             cuenco[i][k]=0;
                        }
                        }
                     }
                     }
               
                    
             Generacion=0;
             tamano=0;
             Acumulation=0;
             
}while(Ghost !=10);
   
    X=0;
   
///Puesta de Palabras al Tablero Jlabel    
    
do{
        
    //Conjunto de Excepciones por los choques 
        if(cuchara==1){
        
         if(X==8){
           TemaX[X]="COBLTO";
                 }
         
         if(X==9){
           TemaX[X]="RGON";
                 }
            
         if(X==10) {
            TemaX[X]="NEN";
                 }
         
         if(X==12) {
            TemaX[X]="BRO";
                 }
     }
                     
         if(cuchara==2){
                     
         if(X==13) {
         TemaX[X]="HERRO";
                 }
                     
         if(X==7) {
         TemaX[X]="SILICO";
             }
                 
         
         if(X==5){
          TemaX[X]="CORE";
                 }
         
         if(X==12){
         TemaX[X]="ORO";
                 }
         
     }
     
         if(cuchara==3){
             
         if(X==13){
         TemaX[X]="POWERPINT";
             }
                     
          if(X==8){
         TemaX[X]="BS";
                  }
                     
          if(X==9){
          TemaX[X]="NTBEAMS";
                  }
                     
          if(X==4){
          TemaX[X]="OPRA";
                  }            
     }   
         
          if(cuchara==4){
                 
           if(X==0){
            TemaX[X]="ADBE";
           }
                   
           if(X==13){
           TemaX[X]="POWERPINT";
                     }
           
          if(X==12){
            TemaX[X]="ZOM";
                  }
                     
          if(X==8){
         TemaX[X]="BS";
                 }
     }
      
         if(cuchara==5){
                
          if(X==12){
          TemaX[X]="MSA";
         }  
                  
          if(X==11){
          TemaX[X]="XAMEN";
          }
          
          if(X==10){
         TemaX[X]="SILA";
          }
          
          if(X==7){
          TemaX[X]="IBRO";
         }
         }
          
     
         if(cuchara==6){
      
            if(X==9){
            TemaX[X]="FCHA";
             }
                     
            if(X==10){
            TemaX[X]="SLLA";
             }  
            
            if(X==0){
            TemaX[X]="LPIZ";
                  } 
            
            if(X==13){
            TemaX[X]="NOT";
                 }
             }
                
    AVER=0;  
    p=0;    
    Zona=1;
    
// Convertir la cadena en un arreglo de caracteres
char[] arregloCaracteres = TemaX[X].toCharArray();
precio=X+1;

// Imprimir el arreglo de caracteres

  direccion = (int)(Math.random()*2 + 1); 

for (char c : arregloCaracteres) {
   
    Posicion=1;
    Pase=0;
    String cadena = String.valueOf(c);
            
       if(cuchara==1){
                
             if(X==8){
             direccion=2;
                     }
                     
             if(X==9)  {
             direccion=1;
                     }
                 
             if(X==10) {
             direccion=1;
                 }
                     
             if(X==12) {
            direccion=1;
                 }
         }
                     
       if(cuchara==2){
           
         if(X==13) {
         direccion=1;
         }
         
         if(X==7) {
         direccion=1;
         }

         if(X==5){
         direccion=1;
         }
                     
         if(X==9){
         direccion=1;
         }
     }

         if(cuchara==3){
                     
          if(X==13){
          direccion=1;
             }

          if(X==8){
          direccion=1;
         }
          
          if(X==9){
           direccion=2;
             }

          if(X==4){
         direccion=1;
             }
                     
         }   

         if(cuchara==4){
                 
           if(X==0){
           direccion=1;
             }

          if(X==13){
           direccion=2;
             }
                 
          if(X==12){
           direccion=1;
             }

          if(X==8){
           direccion=2;
                     }  
         }
         
         if(cuchara==5){
         
          if(X==12){
          direccion=2;
         }  

          if(X==11){
         direccion=1;
             }
          
          if(X==10){
           direccion=1;
             }
                     
          if(X==7){
           direccion=1;
             } 
         }

         if(cuchara==6){
             
            if(X==9){
            direccion=2;
             } 

             if(X==10){
             direccion=1;
             } 
                     
             if(X==0){
             direccion=1;
             } 
 
            if(X==13){
             direccion=2;
         } 

                 }
      
    
    if(direccion==1){
        for(int i = 1; i<21; i++){
        for(int k = 1; k<21; k++){
       
    
                 
        if(cuenco[i][k]==precio){
             
                        if(Posicion==Zona){
                            espacio[k-1][i-1].setText(cadena);
                              p++;   
                            i=100;
                            k=100;
                             Zona++;
                             
                                  
              
                        }else{
                              Posicion++;
                        }
                        }
                        }
                        }
                      }  
    
    if(direccion==2){
    
        for(int i = 20; i>0; i--){
        for(int k = 20; k>0; k--){
                      
                if(cuenco[i][k]==precio){
                   
                         
                            if(Posicion==Zona){
                             
                                espacio[k-1][i-1].setText(cadena);
                                p++;   
                                Zona++;
                                i=-100;
                                k=-100;
                            }else{
                          Posicion++;
                    }
                    } 
                   } 
                }
            }
AVER++;        
      }                
      
X++;                
palabreo++;                  
     
    }while(palabreo!=16); 
    
    
     if(cuchara==1){
        
               TemaX[8]="COBALTO";
              TemaX[9]="ARGON";
                TemaX[10]="NEON";
                TemaX[12]="BORO";
                 
     }
                     
     if(cuchara==2){
                     
                 TemaX[13]="HIERRO";
                 TemaX[7]="SILICIO";
                   TemaX[5]="COBRE";
                 TemaX[12]="BORO";
                 
         
     }
     
     if(cuchara==3){
                   TemaX[13]="POWERPOINT";
              
                    TemaX[8]="OBS";
                 
                   TemaX[9]="NETBEAMS";
             
                    TemaX[4]="OPERA";
                  
                  
     }   
      if(cuchara==4){
                   TemaX[0]="ADOBE";
                
                     TemaX[13]="POWERPOINT";
                 
                    TemaX[12]="ZOOM";
                     TemaX[8]="OBS";
     }
      
     if(cuchara==5){
                
                      TemaX[5]="MESA";
                    
                      TemaX[11]="EXAMEN";
                
                       TemaX[10]="SILLA";
                 
                      TemaX[7]="LIBRO";
                 
          
         }
     
     if(cuchara==6){
                    TemaX[9]="FICHA";
               
                    TemaX[10]="SILLA";
                 
                    TemaX[0]="LAPIZ";
                  
                    TemaX[13]="NOTA";
                  
                 }
    
    
      for(int i = 1; i<21; i++){
        for(int k = 1; k<21; k++){
        
        sopita[i][k]=cuenco[i][k];
        
        }}
    
    
    /*
    for(int i = 1; i<21; i++){
        for(int k = 1; k<21; k++){
                      
            if(cuenco[i][k]==0){
               
              
                String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                Random random = new Random();
                char randomLetter = alphabet.charAt(random.nextInt(alphabet.length()));
                String VALOR = String.valueOf(randomLetter);
                espacio[k-1][i-1].setText(VALOR);
                cuenco[i][k] = 1000;  
            }
        }
    }
    */
    
    
     if(cuchara==1){
        
         espacio[9][7].setText("A");
         espacio[11][13].setText("O");
               
     }
        
     if(cuchara==2){
          espacio[9][10].setText("I");   
          espacio[2][0].setText("B"); 
             
     }
                 
     if(cuchara==3){
            espacio[0][6].setText("O");      
            espacio[11][14].setText("E"); 
                 
     }   
     if(cuchara==4){
              espacio[9][7].setText("O");    
              espacio[4][16].setText("O"); 
                 
                 
     }
     if(cuchara==5){
              espacio[6][9].setText("E");    
              espacio[3][3].setText("L"); 
             
                     
                 
     }
     if(cuchara==6){
              espacio[10][8].setText("I"); 
              espacio[0][8].setText("A"); 
   
    
    }

   
     }
    
    //Funcionamiento de Raton
    private void eventosDelRatonMotion(){
        
        MouseAdapter MovRat = new MouseAdapter(){
            int N = 0,C = 0;
            @Override
            public void mousePressed(MouseEvent e) {
                JLabel label = (JLabel)e.getSource();
                if(!movimiento){
                    if (label.getBackground() == Color.white) {
                        if(turnos == 1){
                            label.setBackground(Color.CYAN);
                        }else{
                            label.setBackground(Color.RED);
                        }
                        
                        for(int i = 0; i < 20; i++){
                            for(int j = 0; j < 20; j++){
                                if(label == espacio[j][i]){
                                    alm = alm + cuenco[i+1][j+1];
                                    
                                    if(cuenco[i+1][j+1] == 15){
                                        C = cuenco[i+1][j+1];
                                    }
                                    if(cuenco[i+1][j+1] == 21){
                                        C = cuenco[i+1][j+1];
                                    }
                                }
                            }
                        }
                        N++;
                        movimiento = true;
                    } else if(turnos == 1){
                        if (label.getBackground() == Color.CYAN) {
                            label.setBackground(Color.WHITE);
                        }
                    }else{
                        if (label.getBackground() == Color.RED) {
                            label.setBackground(Color.WHITE);
                        }
                    }
                        
                }else{
                    movimiento = false;
                    tachar = alm;
                    Nletras = N;
                    Choque = C;
                    Search();
                    alm = 0;
                    N = 0;
                    C = 0;
                    for(int i = 0; i < 20; i++){
                        for(int j = 0; j < 20; j++){
                            if(label == espacio[j][i]){
                                if(cuenco[i+1][j+1] == Hoal+1){
                                    cuenco[i+1][j+1] = 1000;
                                    Hoal = 0;
                                }
                            }
                        }
                    }
                }
            }
            
            @Override
            public void mouseMoved(MouseEvent e) {
                JLabel label = (JLabel)e.getSource();
                if(movimiento){
                    if (label.getBackground() == Color.white) {
                        if(turnos == 1){
                            label.setBackground(Color.CYAN);
                        }else{
                            label.setBackground(Color.RED);
                        }
                        
                        for(int i = 0; i < 20; i++){
                            for(int j = 0; j < 20; j++){
                                if(label == espacio[j][i]){
                                    alm = alm + cuenco[i+1][j+1];
                                    if(cuenco[i+1][j+1] == 15){
                                        C = cuenco[i+1][j+1];
                                    }
                                    if(cuenco[i+1][j+1] == 21){
                                        C = cuenco[i+1][j+1];
                                    }
                                }
                            }
                        }
                        N++;
                    }
                }
            }
        };
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                espacio [j][i].addMouseListener(MovRat);
                espacio [j][i].addMouseMotionListener(MovRat);
            }
        }
    }
    
    //Busqueda de Palabra a Tachar
    private void Search(){
        
       
        //Tema 1
        if (cuchara == 1){
            if(tachar == 5){
                Tacha[0].setLocation(1350, 60);
                Hoal = 0;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[0]=0;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[0]=0;
                }
            }
            if(tachar == 10){
                Tacha[1].setLocation(1350, 93);
                Hoal = 1;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[1]=1;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[1]=1;
                }
            }
            if(tachar == 15){
                Tacha[2].setLocation(1350, 126);
                Hoal = 2;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[2]=2;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[2]=2;
                }
            }
            if(tachar == 12){
                Tacha[3].setLocation(1350, 159);
                Hoal = 3;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[3]=3;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[3]=3;
                }
            }
            if(tachar == 25){
                Tacha[4].setLocation(1350, 192);
                Hoal = 4;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[4]=4;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[4]=4;
                }
            }
            if(tachar == 30){
                Tacha[5].setLocation(1350, 225);
                Hoal = 5;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[5]=5;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[5]=5;
                }
            }
            if(tachar == 35){
                Tacha[6].setLocation(1350, 258);
                Hoal = 6;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[6]=6;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[6]=6;
                }
            }
            if(tachar == 56){
                Tacha[7].setLocation(1350, 291);
                Hoal = 7;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[7]=7;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[7]=7;
                }
            }
            if(tachar == 60){
                Tacha[11].setLocation(1350, 423);
                Hoal = 11;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[11]=11;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[11]=11;
                }
            }
            if(tachar == 84){
                Tacha[13].setLocation(1350, 489);
                Hoal = 13;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[13]=13;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[13]=13;
                }
            }
            if(tachar == 75){
                Tacha[14].setLocation(1350, 522);
                Hoal = 14;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[14]=14;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[14]=14;
                }
            }
            if(tachar == 64){
                Tacha[15].setLocation(1350, 555);
                Hoal = 15;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[15]=15;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[15]=15;
                }
            }
            //Choques
            if(tachar == 73 || tachar == 54){
                Tacha[8].setLocation(1350, 324);
                Hoal = 8;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[8]=8;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[8]=8;
                }
            }
            if(tachar == 59 || tachar == 40){
                Tacha[9].setLocation(1350, 357);
                Hoal = 9;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[9]=9;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[9]=9;
                }
            }
            if(tachar == 57 || tachar == 33){
                Tacha[10].setLocation(1350, 390);
                Hoal = 10;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[10]=10;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[10]=10;
                }
            }
            if(tachar == 63 || tachar == 39){
                Tacha[12].setLocation(1350, 456);
                Hoal = 12;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[12]=12;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[12]=12;
                }
            }
        }
        if (cuchara == 2){
            if(tachar == 5){
                Tacha[0].setLocation(1350, 60);
                Hoal = 0;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[0]=0;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[0]=0;
                }
            }
            if(tachar == 10){
                Tacha[1].setLocation(1350, 93);
                Hoal = 1;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[1]=1;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[1]=1;
                }
            }
            if(tachar == 15){
                Tacha[2].setLocation(1350, 126);
                Hoal = 2;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[2]=2;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[2]=2;
                }
            }
            if(tachar == 12){
                Tacha[3].setLocation(1350, 159);
                Hoal = 3;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[3]=3;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[3]=3;
                }
            }
            if(tachar == 25){
                Tacha[4].setLocation(1350, 192);
                Hoal = 4;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[4]=4;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[4]=4;
                }
            }
            if(tachar == 35){
                Tacha[6].setLocation(1350, 258);
                Hoal = 6;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[6]=6;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[6]=6;
                }
            }
            if(tachar == 63){
                Tacha[8].setLocation(1350, 324);
                Hoal = 8;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[8]=8;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[8]=8;
                }
            }
            if(tachar == 50){
                Tacha[9].setLocation(1350, 357);
                Hoal = 9;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[9]=9;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[9]=9;
                }
            }
            if(tachar == 44){
                Tacha[10].setLocation(1350, 390);
                Hoal = 10;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[10]=10;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[10]=10;
                }
            }
            if(tachar == 60){
                Tacha[11].setLocation(1350, 423);
                Hoal = 11;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[11]=11;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[11]=11;
                }
            }
            if(tachar == 75){
                Tacha[14].setLocation(1350, 522);
                Hoal = 14;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[14]=14;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[14]=14;
                }
            }
            if(tachar == 64){
                Tacha[15].setLocation(1350, 555);
                Hoal = 15;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[15]=15;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[15]=15;
                }
            }
            //Choques
            if(Nletras == 6){
                if(tachar == 92 || tachar == 70){//REVISAR 6
                    Tacha[13].setLocation(1350, 489);
                    Hoal = 13;
                    if(turnos == 1){
                        Puntos1 = Puntos1 + 10;
                        abecedario1[13]=13;
                    }else{
                        Puntos2 = Puntos2 + 10;
                        abecedario2[13]=13;
                    }
                }
            }
            if(Nletras == 7){
                if(tachar == 70 || tachar == 48){//REVISAR 7
                    Tacha[7].setLocation(1350, 291);
                    Hoal = 7;
                    if(turnos == 1){
                        Puntos1 = Puntos1 + 10;
                        abecedario1[7]=7;
                    }else{
                        Puntos2 = Puntos2 + 10;
                        abecedario2[7]=7;
                    }
                }
            }
            if(tachar == 43 || tachar == 24){
                Tacha[5].setLocation(1350, 225);
                Hoal = 5;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[5]=5;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[5]=5;
                }
            }
            if(tachar == 58 || tachar == 39){
                Tacha[12].setLocation(1350, 456);
                Hoal = 12;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[12]=12;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[12]=12;
                }
            }
        }
        //Tema 2
        if (cuchara == 3){
            if(tachar == 5){
                Tacha[0].setLocation(1350, 60);
                Hoal = 0;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[0]=0;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[0]=0;
                }
            }
            if(tachar == 8){
                Tacha[1].setLocation(1350, 93);
                Hoal = 1;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[1]=1;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[1]=1;
                }
            }
            if(tachar == 15){
                Tacha[2].setLocation(1350, 126);
                Hoal = 2;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[2]=2;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[2]=2;
                }
            }
            if(tachar == 20){
                Tacha[3].setLocation(1350, 159);
                Hoal = 3;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[3]=3;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[3]=3;
                }
            }
            if(tachar == 36){
                Tacha[5].setLocation(1350, 225);
                Hoal = 5;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[5]=5;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[5]=5;
                }
            }
            if(Choque == 0){
                if(tachar == 35){//REVISAR 5
                    Tacha[6].setLocation(1350, 258);
                    Hoal = 6;
                    if(turnos == 1){
                        Puntos1 = Puntos1 + 10;
                        abecedario1[6]=6;
                    }else{
                        Puntos2 = Puntos2 + 10;
                        abecedario2[6]=6;
                    }
                }
            }
            if(tachar == 48){
                Tacha[7].setLocation(1350, 291);
                Hoal = 7;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[7]=7;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[7]=7;
                }
            }
            if(tachar == 66){
                Tacha[10].setLocation(1350, 390);
                Hoal = 10;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[10]=10;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[10]=10;
                }
            }
            if(tachar == 84){
                Tacha[11].setLocation(1350, 423);
                Hoal = 11;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[11]=11;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[11]=11;
                }
            }
            if(tachar == 52){
                Tacha[12].setLocation(1350, 456);
                Hoal = 12;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[12]=12;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[12]=12;
                }
            }
            if(tachar == 165){
                Tacha[14].setLocation(1350, 522);
                Hoal = 14;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[14]=14;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[14]=14;
                }
            }
            if(tachar == 144){
                Tacha[15].setLocation(1350, 555);
                Hoal = 15;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[15]=15;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[15]=15;
                }
            }
            //Choques
            if(tachar == 149 || tachar == 126){
                Tacha[13].setLocation(1350, 489);
                Hoal = 13;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[13]=13;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[13]=13;
                }
            }
            if(tachar == 41 || tachar == 18){
                Tacha[8].setLocation(1350, 324);
                Hoal = 8;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[8]=8;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[8]=8;
                }
            }
            if(tachar == 85 || tachar == 70){
                Tacha[9].setLocation(1350, 357);
                Hoal = 9;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[9]=9;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[9]=9;
                }
            }
            if(Choque == 15){
                if(tachar == 35 || tachar == 20){//REVISAR 5 - 15
                    Tacha[4].setLocation(1350, 192);
                    Hoal = 4;
                    if(turnos == 1){
                        Puntos1 = Puntos1 + 10;
                        abecedario1[4]=4;
                    }else{
                        Puntos2 = Puntos2 + 10;
                        abecedario2[4]=4;
                    }
                }
            }
        }
        if (cuchara == 4){
            if(tachar == 8){
                Tacha[1].setLocation(1350, 93);
                Hoal = 1;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[1]=1;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[1]=1;
                }
            }
            if(tachar == 15){
                Tacha[2].setLocation(1350, 126);
                Hoal = 2;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[2]=2;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[2]=2;
                }
            }
            if(tachar == 20){
                Tacha[3].setLocation(1350, 159);
                Hoal = 3;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[3]=3;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[3]=3;
                }
            }
            if(tachar == 25){
                Tacha[4].setLocation(1350, 192);
                Hoal = 4;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[4]=4;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[4]=4;
                }
            }
            if(tachar == 36){
                Tacha[5].setLocation(1350, 225);
                Hoal = 5;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[5]=5;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[5]=5;
                }
            }
            if(tachar == 35){
                Tacha[6].setLocation(1350, 258);
                Hoal = 6;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[6]=6;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[6]=6;
                }
            }
            if(tachar == 48){
                Tacha[7].setLocation(1350, 291);
                Hoal = 7;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[7]=7;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[7]=7;
                }
            }
            if(tachar == 80){
                Tacha[9].setLocation(1350, 357);
                Hoal = 9;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[9]=9;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[9]=9;
                }
            }
            if(tachar == 66){
                Tacha[10].setLocation(1350, 390);
                Hoal = 10;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[10]=10;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[10]=10;
                }
            }
            if(tachar == 84){
                Tacha[11].setLocation(1350, 423);
                Hoal = 11;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[11]=11;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[11]=11;
                }
            }
            if(tachar == 165){
                Tacha[14].setLocation(1350, 522);
                Hoal = 14;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[14]=14;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[14]=14;
                }
            }
            if(tachar == 144){
                Tacha[15].setLocation(1350, 555);
                Hoal = 15;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[15]=15;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[15]=15;
                }
            }
            //Choques
            if(tachar == 21 || tachar == 4){
                Tacha[0].setLocation(1350, 60);
                Hoal = 0;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[0]=0;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[0]=0;
                }
            }
            if(tachar == 157 || tachar == 140){
                Tacha[13].setLocation(1350, 489);
                Hoal = 13;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[13]=13;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[13]=13;
                }
            }
            if(tachar == 61 || tachar == 39){
                Tacha[12].setLocation(1350, 456);
                Hoal = 12;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[12]=12;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[12]=12;
                }
            }
            if(tachar == 40 || tachar == 18){
                Tacha[8].setLocation(1350, 324);
                Hoal = 8;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[8]=8;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[8]=8;
                }
            }
        }
        //Tema 3
        if (cuchara == 5){
            if(tachar == 5){
                Tacha[0].setLocation(1350, 60);
                Hoal = 0;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[0]=0;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[0]=0;
                }
            }
            if(tachar == 16){
                Tacha[1].setLocation(1350, 93);
                Hoal = 1;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[1]=1;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[1]=1;
                }
            }
            if(tachar == 24){
                Tacha[2].setLocation(1350, 126);
                Hoal = 2;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[2]=2;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[2]=2;
                }
            }
            if(tachar == 20){
                Tacha[3].setLocation(1350, 159);
                Hoal = 3;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[3]=3;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[3]=3;
                }
            }
            if(tachar == 30){
                Tacha[4].setLocation(1350, 192);
                Hoal = 4;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[4]=4;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[4]=4;
                }
            }
            if(tachar == 48){
                Tacha[5].setLocation(1350, 225);
                Hoal = 5;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[5]=5;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[5]=5;
                }
            }
            if(tachar == 35){
                Tacha[6].setLocation(1350, 258);
                Hoal = 6;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[6]=6;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[6]=6;
                }
            }
            if(tachar == 36){
                Tacha[8].setLocation(1350, 324);
                Hoal = 8;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[8]=8;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[8]=8;
                }
            }
            if(tachar == 50){
                Tacha[9].setLocation(1350, 357);
                Hoal = 9;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[9]=9;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[9]=9;
                }
            }
            if(tachar == 56){
                Tacha[13].setLocation(1350, 489);
                Hoal = 13;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[13]=13;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[13]=13;
                }
            }
            if(tachar == 105){
                Tacha[14].setLocation(1350, 522);
                Hoal = 14;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[14]=14;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[14]=14;
                }
            }
            if(tachar == 144){
                Tacha[15].setLocation(1350, 555);
                Hoal = 15;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[15]=15;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[15]=15;
                }
            }
            //Choques
            if(tachar == 64 || tachar == 39){
                Tacha[12].setLocation(1350, 456);
                Hoal = 12;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[12]=12;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[12]=12;
                }
            }
            if(tachar == 85 || tachar == 60){
                Tacha[11].setLocation(1350, 423);
                Hoal = 11;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[11]=11;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[11]=11;
                }
            }
            if(tachar == 63 || tachar == 44){
                Tacha[10].setLocation(1350, 390);
                Hoal = 10;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[10]=10;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[10]=10;
                }
            }
            if(tachar == 51 || tachar == 32){
                Tacha[7].setLocation(1350, 291);
                Hoal = 7;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[7]=7;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[7]=7;
                }
            }
        }
        if (cuchara == 6){
            if(tachar == 16){
                Tacha[1].setLocation(1350, 93);
                Hoal = 1;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[1]=1;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[1]=1;
                }
            }
            if(tachar == 24){
                Tacha[2].setLocation(1350, 126);
                Hoal = 2;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[2]=2;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[2]=2;
                }
            }
            if(tachar == 20){
                Tacha[3].setLocation(1350, 159);
                Hoal = 3;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[3]=3;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[3]=3;
                }
            }
            if(tachar == 30){
                Tacha[4].setLocation(1350, 192);
                Hoal = 4;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[4]=4;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[4]=4;
                }
            }
            if(tachar == 48){
                Tacha[5].setLocation(1350, 225);
                Hoal = 5;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[5]=5;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[5]=5;
                }
            }
            if(tachar == 35){
                Tacha[6].setLocation(1350, 258);
                Hoal = 6;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[6]=6;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[6]=6;
                }
            }
            if(Choque == 0){
                if(tachar == 40){//REVISAR 5
                    Tacha[7].setLocation(1350, 291);
                    Hoal = 7;
                    if(turnos == 1){
                        Puntos1 = Puntos1 + 10;
                        abecedario1[7]=7;
                    }else{
                        Puntos2 = Puntos2 + 10;
                        abecedario2[7]=7;
                    }
                }
            }
            if(tachar == 36){
                Tacha[8].setLocation(1350, 324);
                Hoal = 8;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[8]=8;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[8]=8;
                }
            }
            if(tachar == 72){
                Tacha[11].setLocation(1350, 423);
                Hoal = 11;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[11]=11;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[11]=11;
                }
            }
            if(tachar == 52){
                Tacha[12].setLocation(1350, 456);
                Hoal = 12;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[12]=12;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[12]=12;
                }
            }
            if(tachar == 105){
                Tacha[14].setLocation(1350, 522);
                Hoal = 14;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[14]=14;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[14]=14;
                }
            }
            if(tachar == 144){
                Tacha[15].setLocation(1350, 555);
                Hoal = 15;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[15]=15;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[15]=15;
                }
            }
            //Choques
            if(Choque == 21){
                if(tachar == 61 || tachar == 40){//REVISAR 5 - 21
                    Tacha[9].setLocation(1350, 357);
                    Hoal = 9;
                    if(turnos == 1){
                        Puntos1 = Puntos1 + 10;
                        abecedario1[9]=9;
                    }else{
                        Puntos2 = Puntos2 + 10;
                        abecedario2[9]=9;
                    }
                }
            }
            if(tachar == 65 || tachar == 44){
                Tacha[10].setLocation(1350, 390);
                Hoal = 10;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[10]=10;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[10]=10;
                }
            }
            if(tachar == 19 || tachar == 4){
                Tacha[0].setLocation(1350, 60);
                Hoal = 0;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[0]=0;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[0]=0;
                }
            }
            if(tachar == 57 || tachar == 42){
                Tacha[13].setLocation(1350, 489);
                Hoal = 13;
                if(turnos == 1){
                    Puntos1 = Puntos1 + 10;
                    abecedario1[13]=13;
                }else{
                    Puntos2 = Puntos2 + 10;
                    abecedario2[13]=13;
                }
            }
        }
        Ganar = Puntos1 + Puntos2;
        Pts1.setText("Puntos de "+Nombre1+": "+Puntos1);
        Pts2.setText("Puntos de "+Nombre2+": "+Puntos2);
        
        if(Ganar == 160){
            System.out.println(""+Ganar);
             escribirArchivo("Archivo\\Inicio.txt","1"); 
            tiempo.stop();
            VentanaGanador();
            if(Puntos1>Puntos2){
                NomG.setText(""+Nombre1);
            }
            
            if(Puntos1<Puntos2){
                NomG.setText(""+Nombre2);
            }
                 
            if(Puntos1==Puntos2){

                for(int i = 0; i<17; i++){



                    int word1=abecedario1[i]+1;

                    if(word1<100){

                        int aletrado1 = TemaX[word].length();

                        filler1 = filler1 + aletrado1;
                    }

                }

                for(int i = 0; i<17; i++){



                    int word2=abecedario2[i]+1;

                    if(word2<100){

                        int aletrado2 = TemaX[word].length();

                        filler2 = filler2 + aletrado2;
                    }

                }

                if(filler1>filler2){

                    NomG.setText(""+Nombre1);
                }else{

                   NomG.setText(""+Nombre2);
                }
            }
            
            
        }
        
        
        
        
    }
    
    //Ventana Emergente de Victoria
    private void VentanaGanador(){
        JFrame VG = new JFrame();
        VG.setSize(1000,500);
        VG.setLocationRelativeTo(null);
        VG.setTitle("Proyecto Final - Battle Soup");
        VG.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        VG.setVisible(true);
        
        JPanel panelG = new JPanel();
        
        panelG = new JPanel();
        panelG.setLayout(null);
        panelG.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        
        VG.getContentPane().add(panelG);
        
        NomG = new JLabel("",SwingConstants.CENTER);
        NomG.setBounds(260, 300, 450, 150);
        NomG.setFont(customFont2);
        panelG.add(NomG);
        
        ImageIcon Marc3 = new ImageIcon("Marco3.png");
        JLabel Mar = new JLabel();
        Mar.setBounds(260, 300, NomG.getWidth(), NomG.getHeight());
        Mar.setIcon(new ImageIcon(Marc3.getImage().getScaledInstance(NomG.getWidth(), NomG.getHeight(), Image.SCALE_SMOOTH)));
        panelG.add(Mar);
        
        ImageIcon imagen2 = new ImageIcon("Win.png");
        JLabel etiqueta2 = new JLabel();
        etiqueta2.setBounds(60, 30, 850, 250);
        etiqueta2.setIcon(new ImageIcon(imagen2.getImage().getScaledInstance(etiqueta2.getWidth(), etiqueta2.getHeight(), Image.SCALE_SMOOTH)));
        panelG.add(etiqueta2);
        
        ImageIcon imagen = new ImageIcon("Fondo.jpg");
        JLabel etiqueta = new JLabel();
        etiqueta.setBounds(0, 0, VG.getWidth(), VG.getHeight());
        etiqueta.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(VG.getWidth(), VG.getHeight(), Image.SCALE_SMOOTH)));
        panelG.add(etiqueta);
    }
    
    //Metodo Timer
    public ActionListener Timer(){
        return new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Seg++;
                if(Seg == 3){
                    img5.setIcon(new ImageIcon(ImgR2.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
                }
                if(Seg == 9){
                    img5.setIcon(new ImageIcon(ImgR3.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
                }
                if(Seg == 12){
                    img5.setIcon(new ImageIcon(ImgR4.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
                }
                if(Seg == 15){
                    img5.setIcon(new ImageIcon(ImgR5.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
                }
                if(Seg == 18){
                    img5.setIcon(new ImageIcon(ImgR6.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
                }
                if(Seg == 21){
                    img5.setIcon(new ImageIcon(ImgR7.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
                }
                if(Seg == 24){
                    img5.setIcon(new ImageIcon(ImgR8.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
                }
                if(Seg == 30){
                    System.out.println("ring ring");
                    img5.setIcon(new ImageIcon(ImgR1.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
                    tiempo.stop();
                    Seg = 0;
                    movimiento = false;
                    alm = 0;
                    if(turnos == 1){
                        turnos = 2;
                        img1.setLocation(1552, 640);
                        Nom1.setLocation(1550, 800);
                        img2.setLocation(1152, 640);
                        Nom2.setLocation(1150, 800);
                    }else{
                        turnos = 1;
                        img1.setLocation(1152, 640);
                        Nom1.setLocation(1150, 800);
                        img2.setLocation(1552, 640);
                        Nom2.setLocation(1550, 800);
                    }
                    tiempo.start();
                }
            }
            
        };
    }
    
    private void GuardarYsalir(){
        ActionListener GUySA = (ActionEvent e) -> {
            leerData();
            System.exit(0);
        };
        GyS.addActionListener(GUySA);
    }
    
    private void cargar(){
        ActionListener Carga = (ActionEvent v) -> {
            
            int pointer = 0;
            String str;
             try (BufferedReader reader = new BufferedReader(new FileReader("Archivo\\Inicio.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file here
                str = line;
                pointer = Integer.parseInt(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
            
            
            
            if(pointer==0){CargaData();}else{ System.out.println("EPALE"+pointer);}
            
            
        };
        Cargar.addActionListener(Carga);
    }
}
