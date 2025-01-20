
package chessgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;


public class Interfaz extends JFrame  {
    
    //Variables Globales #1
    public int Tablero[][]=new int [9][9];
    public int Pieza=0;
    public  int i;
    public int j;
    public int f;
    public int g;
    public int p;
    public int t;
    public int contB;
    public int contW;
    public int opcion;
    private int xp;
    private int yp;
    private int a;
    private int b;
    private int columna;
    private int fila;
    private int columnaP;
    private int filaP;
    private String nombre;
    private String nombre2;
    public int WINS;
    public int Turns;
    Timer Times;
    
    
    

    public Interfaz(String nombre, String nombre2) {
        this.nombre = nombre;
        this.nombre2 = nombre2;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombre2() {
        return nombre2;
    }
    

//Comienzo del Programa
    //Variables Globales #2
    public JPanel panel;
    private JLabel etiqueta1;
    private JLabel etiqueta2;
    private JLabel etiqueta3;
    private JLabel etiqueta4;
    private JLabel etiqueta5;
    private JLabel etiqueta6;
    private JLabel etiqueta7;
    private JLabel etiqueta8;
    private JLabel etiqueta9;
    private JLabel etiqueta10;
    private JLabel etiqueta11;
    private JLabel etiqueta12;
    private JLabel etiqueta13;
    private JLabel etiqueta14;
    private JLabel etiqueta15;
    private JLabel etiqueta16;
    private JLabel etiqueta17;
    private JLabel etiqueta18;
    private JLabel etiqueta19;
    private JLabel etiqueta20;
    private JLabel etiqueta21;
    private JLabel etiqueta22;
    private JLabel etiqueta23;
    private JLabel etiqueta24;
    private JLabel etiqueta25;
    private JLabel etiqueta26;
    private JLabel etiqueta27;
    private JLabel etiqueta28;
    private JTextField cajaTexto1;
    private JTextField cajaTexto2;
    private JTextField cajaTexto3;
    private JTextField cajaTexto4;
    private  JButton boton1;
    
    
//Diseno de Ventana
    
//JFrame     
    public Interfaz(){ 
        
       
        /*
    nombre = JOptionPane.showInputDialog("Nombre Jugador 1:");
        JOptionPane.showMessageDialog(null, "Jugador 1: " + nombre + ", \n   Bienvenido.");
        
    nombre2 = JOptionPane.showInputDialog("Nombre Jugador 2:");
        JOptionPane.showMessageDialog(null, "Jugador 1: " + nombre2 + ", \n   Bienvenido.");
        
         
       
        
        
    */
        
         opcion =Integer.parseInt( JOptionPane.showInputDialog("Desea Animaciones? 0=no || 1=si")); 
        
   this.setSize(900,500);
   //setLocation(100,200);
   // setBounds(0,0,500,500); //AHORRO DE DATOS
   setLocationRelativeTo(null); //Ventana EN EL CENTRO
   setDefaultCloseOperation(EXIT_ON_CLOSE);
   
  setResizable(false);
   setMinimumSize(new Dimension(200,200));
  // this.getContentPane().setBackground(Color.orange);
   
   setTitle("Juego de Ajedrez de Juan Paredes");
   IniciarComponentes();
    }

//Funcion Recopilatoria 
    private void IniciarComponentes(){
    
    Reinicio();
    colocarPanel();
    colocarLabel(); 
    colocarCajaTexto();
    colocarBoton();
   
    
     do{
    
    casillas();
   
    Winner();
    
    }while(WINS<0);
    }
   
//Panel 
    private void colocarPanel(){
      
   panel = new JPanel();
   panel.setLayout(null);
   this.getContentPane().add(panel);
   
      
      }

//Botón    
    private void colocarBoton(){
    
     boton1 = new JButton();
     boton1.setText("Mover");
     boton1.setBounds(750, 275, 100, 45);
     boton1.setFont(new Font("Times New Roman",Font.BOLD,18));
     boton1.setBackground(Color.GRAY);
     panel.add(boton1);
    }
     
//Etiquetas    
    private void colocarLabel(){
     
 //Piezas Blancas
 
    //Peones
    ImageIcon imagen2 = new ImageIcon("peon1.gif");
    etiqueta2 = new JLabel();
    etiqueta2.setBounds(73, 73, 25, 35);
    etiqueta2.setIcon(new ImageIcon(imagen2.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
    panel.add(etiqueta2);
    
 
    ImageIcon imagen3 = new ImageIcon("peon1.gif");
    etiqueta3 = new JLabel();
    etiqueta3.setBounds(155, 73, 25, 35);
    etiqueta3.setIcon(new ImageIcon(imagen3.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
    panel.add(etiqueta3);
    
    
    ImageIcon imagen4 = new ImageIcon("peon1.gif");
    etiqueta4 = new JLabel();
    etiqueta4.setBounds(237, 73, 25, 35);
    etiqueta4.setIcon(new ImageIcon(imagen4.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
    panel.add(etiqueta4);
    
    ImageIcon imagen5 = new ImageIcon("peon1.gif");
    etiqueta5 = new JLabel();
    etiqueta5.setBounds(319, 73, 25, 35);
    etiqueta5.setIcon(new ImageIcon(imagen5.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
    panel.add(etiqueta5);
    
    //Reinas 
     ImageIcon imagen10 = new ImageIcon("reina1.gif");
    etiqueta19 = new JLabel();
    etiqueta19.setBounds(600, -100, 25, 35);
    etiqueta19.setIcon(new ImageIcon(imagen10.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
    panel.add(etiqueta19);
    
   
    
 //Piezas Negras
 
   //Peones
   ImageIcon imagen6 = new ImageIcon("peon2.gif");
    etiqueta6 = new JLabel();
    etiqueta6.setBounds(115, 365, 25, 35);
    etiqueta6.setIcon(new ImageIcon(imagen6.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
    panel.add(etiqueta6);   
 
    ImageIcon imagen7 = new ImageIcon("peon2.gif");
    etiqueta7 = new JLabel();
    etiqueta7.setBounds(197, 365, 25, 35);
    etiqueta7.setIcon(new ImageIcon(imagen7.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
    panel.add(etiqueta7);   
 
    ImageIcon imagen8 = new ImageIcon("peon2.gif");
    etiqueta8 = new JLabel();
    etiqueta8.setBounds(279, 365, 25, 35);
    etiqueta8.setIcon(new ImageIcon(imagen8.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
    panel.add(etiqueta8);   
 
    ImageIcon imagen9 = new ImageIcon("peon2.gif");
    etiqueta9 = new JLabel();
    etiqueta9.setBounds(361, 365, 25, 35);
    etiqueta9.setIcon(new ImageIcon(imagen9.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
    panel.add(etiqueta9); 
    
    //Reinas
    
     ImageIcon imagen14 = new ImageIcon("reina2.gif");
    etiqueta23 = new JLabel();
    etiqueta23.setBounds(600, 900, 25, 35);
    etiqueta23.setIcon(new ImageIcon(imagen14.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
    panel.add(etiqueta23);
    
   
    
    
    
 //Tablero   
 
    ImageIcon imagen1 = new ImageIcon("tablero.jpg");
    etiqueta1 = new JLabel();
    etiqueta1.setBounds(30, 35, 400, 400);
    etiqueta1.setIcon(new ImageIcon(imagen1.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH)));
    panel.add(etiqueta1);
    
//Texto

    etiqueta10 = new JLabel();
    etiqueta10.setBounds(470, 35, 300, 30);
    etiqueta10.setText("Jugador 1: "+getNombre());
    etiqueta10.setFont(new Font("Times New Roman",Font.BOLD,20));
    etiqueta10.setForeground(Color.BLUE);
    panel.add(etiqueta10);
    
    etiqueta11 = new JLabel();
    etiqueta11.setBounds(470, 395, 300, 30);
    etiqueta11.setText("Jugador 2: "+getNombre2());
    etiqueta11.setFont(new Font("Times New Roman",Font.BOLD,20));
    etiqueta11.setForeground(Color.BLUE);
    panel.add(etiqueta11);
    
    etiqueta13 = new JLabel();
    etiqueta13.setBounds(470, 105, 300, 30);
    etiqueta13.setText("Pieza a Jugar:");
    etiqueta13.setFont(new Font("Times New Roman",Font.BOLD,20));
    panel.add(etiqueta13);
    
    etiqueta14 = new JLabel();
    etiqueta14.setBounds(470, 225, 300, 30);
    etiqueta14.setText("Adonde quiere mover:");
    etiqueta14.setFont(new Font("Times New Roman",Font.BOLD,20));
    panel.add(etiqueta14);
    
    etiqueta15 = new JLabel();
    etiqueta15.setBounds(470, 165, 300, 30);
    etiqueta15.setText("Fila: ");
    etiqueta15.setFont(new Font("Times New Roman",Font.BOLD,20));
    panel.add(etiqueta15);
    
    etiqueta16 = new JLabel();
    etiqueta16.setBounds(580, 165, 300, 30);
    etiqueta16.setText("Columna: ");
    etiqueta16.setFont(new Font("Times New Roman",Font.BOLD,20));
    panel.add(etiqueta16);
    
    etiqueta17 = new JLabel();
    etiqueta17.setBounds(470, 280, 300, 30);
    etiqueta17.setText("Fila: ");
    etiqueta17.setFont(new Font("Times New Roman",Font.BOLD,20));
    panel.add(etiqueta17);
    
    etiqueta18 = new JLabel();
    etiqueta18.setBounds(580, 280, 300, 30);
    etiqueta18.setText("Columna: ");
    etiqueta18.setFont(new Font("Times New Roman",Font.BOLD,20));
    panel.add(etiqueta18);
    
    
 //Fondo Blanco
    etiqueta12 = new JLabel();
    etiqueta12.setOpaque(true);
    etiqueta12.setBounds(460, 100, 280, 260);
    etiqueta12.setBackground(Color.white);
    panel.add(etiqueta12);
    
    
    }
     
//Cajas de Texto  
    private void colocarCajaTexto(){
    
     cajaTexto1 = new JTextField();
   cajaTexto1.setBounds(515, 165, 50, 30);
    panel.add(cajaTexto1);
    
    cajaTexto2 = new JTextField();
   cajaTexto2.setBounds(515, 280, 50, 30);
    panel.add(cajaTexto2);
    
      cajaTexto3 = new JTextField();
   cajaTexto3.setBounds(665, 280, 50, 30);
    panel.add(cajaTexto3);
            
      cajaTexto4 = new JTextField();
   cajaTexto4.setBounds(665, 165, 50, 30);
    panel.add(cajaTexto4);
   
 
    }
  

//Funciones del Funcionamiento del Ajedrez 
     
    //Posicion Inicial Piezas
    private void Reinicio(){
       
 
        for(int p = 0; p<9; p++){
         for (int t = 0; t<9; t++){
         
             Tablero[p][t]=0;
        }
}
        
        Tablero[1][2]=1;
        Tablero[1][4]=2;
        Tablero[1][6]=3;
        Tablero[1][8]=4;
        Tablero[8][1]=5;
        Tablero[8][3]=6;
        Tablero[8][5]=7;
        Tablero[8][7]=8;
     /*
             for(int p = 1; p<=8; p++){
         for (int t = 1; t<=8; t++){
      
             System.out.print(Tablero[p][t]);
   if (t==8){
       System.out.print( "\n ");
   }
}  
}     */ 
    }
    
    //Obtener Ubicacion con Caja de Texto
    private void casillas(){
    
    ActionListener detectar = new ActionListener (){
  
    
    @Override
    public void actionPerformed (ActionEvent e){
    i = Integer.parseInt(cajaTexto1.getText());
    j = Integer.parseInt(cajaTexto4.getText());
    f = Integer.parseInt(cajaTexto2.getText());
    g = Integer.parseInt(cajaTexto3.getText());
    
    
    
    //Revision de Legalidad de Movimiento
    
   
    
    Turnos();
    instruccion();
     Meta();
    
       Times = new Timer (50,new Times());
    Times.start();
     
     if (opcion==0){
     
    
            Spawn();
     
     }
     
  

    }
    
    };
    boton1.addActionListener(detectar);
        
    
    }
    
    //Validación de Movimientos
    private void instruccion(){
        
        //Revision de clase de pieza Tomada
         Pieza = Tablero [i][j];
        
       
        //Revision de Peon
        if (Pieza<=8 && Pieza > 0){
        
            
            //Revision de Peon NEGRO
            if(Pieza<=4)
            {
                if(Turns==1){
   
             if(f==i+1 && g==j){           
                 if (Tablero[f][g]==0){
                 Tablero[f][g]=Pieza;
                 Tablero[i][j]=0;   
                 }
             }
         
             //Revision de COMER Peon NEGRO
             if ((f==i+1 && g==j-1)||(f==i+1 && g==j+1)){  
             if((Tablero[f][g]>4 && Tablero[f][g]<9)||(Tablero[f][g]>14 && Tablero[f][g]<19))
             {
             Tablero[i][j]=0;
             Tablero[f][g]=Pieza;  
             }
             }  
            }
            }
            
            }
            
            //Revision de Peon BANDO BLANCO            
            if (Pieza>=5 && Pieza <=8)
            {
                if(Turns==0){
                     
             if(f==i-1 && g==j){
                 
                 if (Tablero[f][g]==0){
                 Tablero[f][g]=Pieza;
                 Tablero[i][j]=0;
                 
                 }
             }
         
             //Revision de COMER Peon BLANCO
             if ((f==i-1 && g==j-1)||(f==i-1 && g==j+1)){
                 
             if((Tablero[f][g]>4 && Tablero[f][g]<9)||(Tablero[f][g]>14 && Tablero[f][g]<19)){
             Tablero[i][j]=0;
             Tablero[f][g]=Pieza;  
             
             }
             } 
            }
            }
        
        
//Revision de Pieza REINAS
        
//Reinas BANDO NEGRO
          if (Pieza>=11 && Pieza <=14){
              
              System.out.println("OBJETO  REINA");
              
              if(Turns==1){
          
//Movimiento +1

              //Movimiento Vertical ARRIBA
              if (f==i+1 && g==j){
                  
                  if((Tablero[f][g] == 0) || (Tablero[f][g] < 5) || (Tablero[f][g] > 9 && Tablero[f][g] < 15)){
              Tablero[f][g]=Pieza;
              Tablero[i][g]=0;
                  }
              }
              
               //Movimiento Vertical ABAJO
              if (f==i-1 && g==j){
               if((Tablero[f][g] == 0) || (Tablero[f][g] < 5) || (Tablero[f][g] > 9 && Tablero[f][g] < 15)){
              Tablero[f][g]=Pieza;
              Tablero[i][g]=0;
                  }
              }
              
              //Movimiento Horizontal DERECHA
              if (f==i && g==j+1){
                    if((Tablero[f][g] == 0) || (Tablero[f][g] < 5) || (Tablero[f][g] > 9 && Tablero[f][g] < 15)){
              Tablero[f][g]=Pieza;
              Tablero[i][g]=0;
                  }
              }
              
              //Movimiento Horizontal Izquierda
              if (f==i+1 && g==j-1){
                     if((Tablero[f][g] == 0) || (Tablero[f][g] < 5) || (Tablero[f][g] > 9 && Tablero[f][g] < 15)){
              Tablero[f][g]=Pieza;
              Tablero[i][g]=0;
                  }
              }
              
//Movimiento +2

          
              //Movimiento Vertical ARRIBA
              if (f==i+2 && g==j){
              
                  if ((Tablero[i+1][j]>0 && Tablero[i+1][j]<5) || (Tablero[i+1][j]>10 && Tablero[i+1][j]<15)){
                  Tablero[i+1][j]=Pieza;
                  Tablero[i][j]=0;
                  }else {
                   Tablero[f][g] = Pieza;
                   Tablero [i][j]=0;
                  
                  }
              
              }
              
               //Movimiento Vertical ABAJO
                  if (f==i-2 && g==j){
              
                  if ((Tablero[i-1][j]>0 && Tablero[i-1][j]<5) || (Tablero[i-1][j]>10 && Tablero[i-1][j]<15)){
                  Tablero[i-1][j]=Pieza;
                  Tablero[i][j]=0;
                  }else {
                   Tablero[f][g] = Pieza;
                   Tablero[i][j]=0;    
                  }
              }
                  
                  //Movimiento Horizontal DERECHA
                            if (f==i && g==j+2){
              
                  if ((Tablero[i][j+1]>0 && Tablero[i][j+1]<5) || (Tablero[i][j+1]>10 && Tablero[i][j+1]<15)){
                  Tablero[i][j+1]=Pieza;
                  Tablero[i][j]=0;
                  }else {
                   Tablero[f][g] = Pieza;
                   Tablero[i][j]=0;
                  
                  }
              
              }
                
               //Movimiento Horizontal IZQUIERDA 
             if (f==i && g==j-2){
                  if ((Tablero[i][j-1]>0 && Tablero[i][j-1]<5) || (Tablero[i][j-1]>10 && Tablero[i][j-1]<15)){
                  Tablero[i][j-1]=Pieza;
                  Tablero[i][j]=0;
                  }else {
                   Tablero[f][g] = Pieza;
                   Tablero[i][j]=0;
                  }    
              }   
              
           
//Movimiento +3

                //Movimiento Vertical ARRIBA
                  if(f == i+3 && g == j){
                if((Tablero[i+1][j] > 0 && Tablero[i+1][j] < 5) ||  (Tablero[i+1][j] > 10 && Tablero[i+1][j] < 15)){
                    Tablero[i+1][j] = Pieza;
                    Tablero[i][j] = 0;
                } else if((Tablero[i+2][j] > 0 && Tablero[i+2][j] < 5) || (Tablero[i+2][j] > 10 && Tablero[i+2][j] < 15)){
                    Tablero[i+2][j] = Pieza;
                    Tablero[i][j] = 0;
                }else{
                    if((Tablero[f][g] == 0) ||  (Tablero[f][g] > 0 && Tablero[f][g] < 5) ||  (Tablero[f][g] > 10 && Tablero[f][g] < 15)){
                    Tablero[f][g] = Pieza;
                    Tablero[i][j] = 0;
                    }
                }
            }
                  
                   //Movimiento Vertical ABAJO             
                  if(f == i-3 && g == j){
                if((Tablero[i-1][j] > 0 && Tablero[i-1][j] < 5) ||  (Tablero[i-1][j] > 10 && Tablero[i-1][j] < 15)){
                    Tablero[i-1][j] = Pieza;
                    Tablero[i][j] = 0;
                } else if((Tablero[i-2][j] > 0 && Tablero[i-2][j] < 5) || (Tablero[i-2][j] > 10 && Tablero[i-2][j] < 15)){
                    Tablero[i-2][j] = Pieza;
                    Tablero[i][j] = 0;
                }else{
                    if((Tablero[f][g] == 0) ||  (Tablero[f][g] > 0 && Tablero[f][g] < 5) ||  (Tablero[f][g] > 10 && Tablero[f][g] < 15)){
                    Tablero[f][g] = Pieza;
                    Tablero[i][j] = 0;
                    }
                }
            }
                  
                       //Movimiento Horizontal DERECHA
                      if(f == i && g == j+3){
                if((Tablero[i][j+1] > 0 && Tablero[i][j+1] < 5) ||  (Tablero[i][j+1] > 10 && Tablero[i][j+1] < 15)){
                    Tablero[i][j+1] = Pieza;
                    Tablero[i][j] = 0;
                } else if((Tablero[i][j+2] > 0 && Tablero[i][j+2] < 5) || (Tablero[i][j+2] > 10 && Tablero[i][j+2] < 15)){
                    Tablero[i][j+2] = Pieza;
                    Tablero[i][j] = 0;
                }else{
                    if((Tablero[f][g] == 0) ||  (Tablero[f][g] > 0 && Tablero[f][g] < 5) ||  (Tablero[f][g] > 10 && Tablero[f][g] < 15)){
                    Tablero[f][g] = Pieza;
                    Tablero[i][j] = 0;
                    }
                }
            }
                      
                   //Movimiento Horizontal IZQUIERDA
                          if(f == i+3 && g == j){
                if((Tablero[i+1][j] > 0 && Tablero[i+1][j] < 5) ||  (Tablero[i+1][j] > 10 && Tablero[i+1][j] < 15)){
                    Tablero[i+1][j] = Pieza;
                    Tablero[i][j] = 0;
                } else if((Tablero[i+2][j] > 0 && Tablero[i+2][j] < 5) || (Tablero[i+2][j] > 10 && Tablero[i+2][j] < 15)){
                    Tablero[i+2][j] = Pieza;
                    Tablero[i][j] = 0;
                }else{
                    if((Tablero[f][g] == 0) ||  (Tablero[f][g] > 0 && Tablero[f][g] < 5) ||  (Tablero[f][g] > 10 && Tablero[f][g] < 15)){
                    Tablero[f][g] = Pieza;
                    Tablero[i][j] = 0;
                    }
                }
            }
                               
                               
          }                 
          }
          
//Reinas BANDO NEGRO   

               if (Pieza>=15 && Pieza <=18){
              
                   System.out.println("OBJETO  REINA");
                   
                   if (Turns==0){
                   
//Movimiento +1

              //Movimiento Vertical ARRIBA         
               if (f==i+1 && g==j){
                  
                     if((Tablero[f][g] == 0) || (Tablero[f][g] > 4 && Tablero[f][g] < 9) || (Tablero[f][g] > 14 && Tablero[f][g] < 19)){
              Tablero[f][g]=Pieza;
              Tablero[i][g]=0;
                  }
              }
               
              //Movimiento Vertical ABAJO   
              if (f==i-1 && g==j){
                    if((Tablero[f][g] == 0) || (Tablero[f][g] > 4 && Tablero[f][g] < 9) || (Tablero[f][g] > 14 && Tablero[f][g] < 19)){
              Tablero[f][g]=Pieza;
              Tablero[i][g]=0;
                  }
              }
              
               //Movimiento Horizontal DERECHA
              if (f==i && g==j+1){
                   if((Tablero[f][g] == 0) || (Tablero[f][g] > 4 && Tablero[f][g] < 9) || (Tablero[f][g] > 14 && Tablero[f][g] < 19)){
              Tablero[f][g]=Pieza;
              Tablero[i][g]=0;
                  }
              }
              
            //Movimiento Horizontal IZQUIERDA
              if (f==i+1 && g==j-1){
                     if((Tablero[f][g] == 0) || (Tablero[f][g] > 4 && Tablero[f][g] < 9) || (Tablero[f][g] > 14 && Tablero[f][g] < 19)){
              Tablero[f][g]=Pieza;
              Tablero[i][g]=0;
                  }
              }
              
 
 //Movimiento +2
 
 
             //Movimiento Vertical ARRIBA  
              if (f==i+2 && g==j){
              
                  if ((Tablero[i+1][j]>4 && Tablero[i+1][j]<9) || (Tablero[i+1][j]>14 && Tablero[i+1][j]<19)){
                  Tablero[i+1][j]=Pieza;
                  Tablero[i][j]=0;
                  }else {
                   Tablero[f][g] = Pieza;
                   Tablero [i][j]=0;
                  
                  }
              
              }
              
                 //Movimiento Vertical ABAJO   
                  if (f==i-2 && g==j){
              
                  if ((Tablero[i-1][j]>4 && Tablero[i-1][j]<9) || (Tablero[i-1][j]>14 && Tablero[i-1][j]<19)){
                  Tablero[i-1][j]=Pieza;
                  Tablero[i][j]=0;
                  }else {
                   Tablero[f][g] = Pieza;
                   Tablero[i][j]=0;
                  
                  }
              
              }
                  
                 //Movimiento Horizontal DERECHA 
                            if (f==i && g==j+2){
              
                  if ((Tablero[i][j+1]>4 && Tablero[i][j+1]<9) || (Tablero[i][j+1]>14 && Tablero[i][j+1]<19)){
                  Tablero[i][j+1]=Pieza;
                  Tablero[i][j]=0;
                  }else {
                   Tablero[f][g] = Pieza;
                   Tablero[i][j]=0;
                  
                  }
              
              }
             
               //Movimiento Horizontal IZQUIERDA
                 if (f==i && g==j-2){
              
                  if ((Tablero[i][j-1]>4 && Tablero[i][j-1]<9) || (Tablero[i][j-1]>14 && Tablero[i][j-1]<19)){
                  Tablero[i][j-1]=Pieza;
                  Tablero[i][j]=0;
                  }else {
                   Tablero[f][g] = Pieza;
                   Tablero[i][j]=0;
                  
                  }
              
              }          
                           
           
//Movimiento +3

                //Movimiento Vertical ARRIBA 
                  if(f == i+3 && g == j){
                if((Tablero[i+1][j] > 4 && Tablero[i+1][j] < 9) ||  (Tablero[i+1][j] > 14 && Tablero[i+1][j] < 19)){
                    Tablero[i+1][j] = Pieza;
                    Tablero[i][j] = 0;
                } else if((Tablero[i+2][j] > 4 && Tablero[i+2][j] < 9) || (Tablero[i+2][j] > 14 && Tablero[i+2][j] < 19)){
                    Tablero[i+2][j] = Pieza;
                    Tablero[i][j] = 0;
                }else{
                    if((Tablero[f][g] == 0) ||  (Tablero[f][g] > 4 && Tablero[f][g] < 9) ||  (Tablero[f][g] > 14 && Tablero[f][g] < 19)){
                    Tablero[f][g] = Pieza;
                    Tablero[i][j] = 0;
                    }
                }
            }
               
               //Movimiento Vertical ABAJO              
                  if(f == i-3 && g == j){
                if((Tablero[i-1][j] > 4 && Tablero[i-1][j] < 9) ||  (Tablero[i-1][j] > 14 && Tablero[i-1][j] < 19)){
                    Tablero[i-1][j] = Pieza;
                    Tablero[i][j] = 0;
                } else if((Tablero[i-2][j] > 4 && Tablero[i-2][j] < 9) || (Tablero[i-2][j] > 14 && Tablero[i-2][j] < 19)){
                    Tablero[i-2][j] = Pieza;
                    Tablero[i][j] = 0;
                }else{
                    if((Tablero[f][g] == 0) ||  (Tablero[f][g] > 4 && Tablero[f][g] < 9) ||  (Tablero[f][g] > 14 && Tablero[f][g] < 19)){
                    Tablero[f][g] = Pieza;
                    Tablero[i][j] = 0;
                    }
                }
            }
                  
                    //Movimiento Horizontal DERECHA 
                      if(f == i && g == j+3){
                if((Tablero[i][j+1] > 4 && Tablero[i][j+1] < 9) ||  (Tablero[i][j+1] > 14 && Tablero[i][j+1] < 19)){
                    Tablero[i][j+1] = Pieza;
                    Tablero[i][j] = 0;
                } else if((Tablero[i][j+2] > 4 && Tablero[i][j+2] < 9) || (Tablero[i][j+2] > 14 && Tablero[i][j+2] < 19)){
                    Tablero[i][j+2] = Pieza;
                    Tablero[i][j] = 0;
                }else{
                    if((Tablero[f][g] == 0) ||  (Tablero[f][g] > 4 && Tablero[f][g] < 9) ||  (Tablero[f][g] > 14 && Tablero[f][g] < 19)){
                    Tablero[f][g] = Pieza;
                    Tablero[i][j] = 0;
                    }
                }
            }
                   
              //Movimiento Horizontal IZQUIERDA
                          if(f == i+3 && g == j){
                if((Tablero[i+1][j] > 4 && Tablero[i+1][j] < 9) ||  (Tablero[i+1][j] > 14 && Tablero[i+1][j] < 19)){
                    Tablero[i+1][j] = Pieza;
                    Tablero[i][j] = 0;
                } else if((Tablero[i+2][j] > 4 && Tablero[i+2][j] < 9) || (Tablero[i+2][j] > 14 && Tablero[i+2][j] < 19)){
                    Tablero[i+2][j] = Pieza;
                    Tablero[i][j] = 0;
                }else{
                    if((Tablero[f][g] == 0) ||  (Tablero[f][g] > 4 && Tablero[f][g] < 9) ||  (Tablero[f][g] > 14 && Tablero[f][g] < 19)){
                    Tablero[f][g] = Pieza;
                    Tablero[i][j] = 0;
                    }
                }
            }
               }
     }                           
          }
    
    //Validacion de Trasformación a Reina 
    private void Meta(){
       
               for(int p = 1; p<=8; p++){
         for (int t = 1; t<=8; t++){
      
             System.out.print(" "+Tablero[p][t]);
   if (t==8){
       System.out.print( "\n");
   }
}  
}
    
        
        if(contB<1){
        
            for(int n =1; n<9;n++){
            
            if (Tablero[8][n]==4){
            Tablero[8][n]=Tablero[8][n]+10;
            contB++;
            }
            
             if (Tablero[8][n]==3){
            Tablero[8][n]=Tablero[8][n]+10;
            contB++;
            }
            
             if (Tablero[8][n]==2){
            Tablero[8][n]=Tablero[8][n]+10;
            contB++;
            }
               
             if (Tablero[8][n]==1){
            Tablero[8][n]=Tablero[8][n]+10;
            contB++;
            }
           
            }
        
        
        }
        
        
         if(contW<1){
        
            for(int n =1; n<9;n++){
            
            if (Tablero[1][n]==5){
            Tablero[1][n]=Tablero[1][n]+10;
            contW++;
            }
            
             if (Tablero[1][n]==6){
            Tablero[1][n]=Tablero[1][n]+10;
            contW++;
            }
            
             if (Tablero[1][n]==7){
             Tablero[1][n]=Tablero[1][n]+10;
            contW++;
            }
               
             if (Tablero[1][n]==8){
           Tablero[1][n]=Tablero[1][n]+10;
            contW++;
            }
           
            }
        
        
        }
        
      
         
    }
    
    //Validacion de Piezas Restantes para Ganador
    private void Winner(){
    
        int ContadorBlanco = 0;
        int ContadorNegro = 0;
       
        
    for(int p = 0; p<9; p++){
         for (int t = 0; t<9; t++){
        
     if (Tablero[p][t]>0 && Tablero [p][t]<5){
     ContadorNegro++;
     }
     
         if (Tablero[p][t]>10 && Tablero [p][t]<15){
     ContadorNegro++;
     }
     
      if (Tablero[p][t]>4 && Tablero [p][t]<9){
     ContadorBlanco++;
     }
      
          if (Tablero[p][t]>14 && Tablero [p][t]<19){
     ContadorBlanco++;
     }
     
   
        }
    
    
}
    
    if (ContadorBlanco==0){
    WINS=1;
    }
    
     if (ContadorNegro==0){
    WINS=2;
    }
    
     if (WINS==2){
          JOptionPane.showMessageDialog(null, "Jugador 1: " + nombre + ", \n  HAS GANADO");
     }
     
     if (WINS==1){
          JOptionPane.showMessageDialog(null, "Jugador 2: " + nombre2 + ", \n  HAS GANADO");
     }
     
      System.out.println("QUEDAN FICHAS NEGRAS: "+ContadorNegro);
      
      System.out.println("\n");
    
    }
        
    //Animaciones
    class Times implements ActionListener{
 
        
        @Override
        public void actionPerformed(ActionEvent e) {
       
            Rectangle obj1 = new Rectangle(etiqueta2.getBounds());
            Rectangle obj3 = new Rectangle(etiqueta4.getBounds());
            Rectangle obj2 = new Rectangle(etiqueta3.getBounds());
            Rectangle obj4 = new Rectangle(etiqueta5.getBounds());
            Rectangle obj5 = new Rectangle(etiqueta6.getBounds());
         
            
            
            
        Coordinates();
           
//Peon 1 Negro 
   
    if (Turns==1){

           if(Pieza==1){
        
                    
               
            Point p = etiqueta6.getLocation();
            
            if(j<g){
                  
                if(p.x!=a && p.y!=b){
                p.x+=1;
                 p.y-=1;
             
                   etiqueta6.setLocation(p);
                } 
            }
            
            
            if(j==g){
                if(p.y!=b){
                p.y-=1;
                   
                   etiqueta6.setLocation(p);
                } 
            }
            
            if(j>g){
                if(p.x!=a && p.y!=b){
                p.x-=1;
                 p.y-=1;
                   etiqueta6.setLocation(p);
                } 
            }
            
        }
           
           /*
           
           //Sistema de intercepciones con objetos
             if(obj1.intersects(obj5)){
                                imgPB1.setLocation(100, -50);
                            }
           
               if(obj1.intersects(obj5)){
                                imgPB1.setLocation(100, -50);
                            }
                            if(obj1.intersects(obj6)){
                                imgPB2.setLocation(100, -50);
                            }
                            if(obj1.intersects(obj7)){
                                imgPB3.setLocation(100, -50);
                            }
                            if(obj1.intersects(obj8)){
                                imgPB4.setLocation(100, -50);
                            }
                            if(obj1.intersects(obj12)){
                                imgRB.setLocation(100, -50);
                            }
           */
        
//Peon 2 Negro

           if(Pieza==2){
        
            
               
            Point p = etiqueta7.getLocation();
            
               if(j<g){
                  
                if(p.x!=a && p.y!=b){
                p.x+=1;
                 p.y-=1;
             
                   etiqueta7.setLocation(p);
                } 
            }
            
            if(j==g){
                if(p.y!=b){
                p.y-=1;
                
                   etiqueta7.setLocation(p);
                } 
            }
            
            if(j>g){
                if(p.x!=a && p.y!=b){
                p.x-=1;
                 p.y-=1;
                   etiqueta7.setLocation(p);
                } 
            }
            
        }
        
//Peon 3 Negro         
                 if(Pieza==3){
        
            Point p = etiqueta8.getLocation();
            
               if(j<g){
                  
                if(p.x!=a && p.y!=b){
                p.x+=1;
                 p.y-=1;
             
                   etiqueta8.setLocation(p);
                } 
            }
            
            if(j==g){
                if(p.y!=b){
                p.y-=1;
                
                   etiqueta8.setLocation(p);
                } 
            }
            
            if(j>g){
                if(p.x!=a && p.y!=b){
                p.x-=1;
                 p.y-=1;
                   etiqueta8.setLocation(p);
                } 
            }
            
        }
             
//Peon 4 Negro                 
                        if(Pieza==4){
        
            Point p = etiqueta9.getLocation();
            
                if(j<g){
                  
                if(p.x!=a && p.y!=b){
                p.x+=1;
                 p.y-=1;
             
                   etiqueta9.setLocation(p);
                } 
            }
            
            if(j==g){
                if(p.y!=b){
                p.y-=1;
                   
                   etiqueta9.setLocation(p);
                } 
            }
            
            if(j>g){
                if(p.x!=a && p.y!=b){
                p.x-=1;
                 p.y-=1;
                   etiqueta9.setLocation(p);
                } 
            }
            
        }
    }
//Peon Blanco 1
        
if (Turns==0){

        if(Pieza==5){
        
            Point p = etiqueta2.getLocation();
            
             if(j<g){
                  
                if(p.x!=a && p.y!=b){
                p.x+=1;
                 p.y+=1;
             
                   etiqueta2.setLocation(p);
                } 
            }
            
            if(j==g){
                if(p.y!=b){
                p.y+=1;
                   System.out.println("Es"+p);
                   etiqueta2.setLocation(p);
                } 
            }
            
            if(j>g){
                if(p.x!=a && p.y!=b){
                p.x-=1;
                 p.y+=1;
                   etiqueta2.setLocation(p);
                } 
            }
            
        }
        
//Peon Blanco 2        
        
          if(Pieza==6){
        
            Point p = etiqueta3.getLocation();
            
                   if(j<g){
                  
                if(p.x!=a && p.y!=b){
                p.x+=1;
                 p.y+=1;
             
                   etiqueta3.setLocation(p);
                } 
            }
            
            if(j==g){
                if(p.y!=b){
                p.y+=1;
                   System.out.println("Es"+p);
                   etiqueta3.setLocation(p);
                } 
            }
            
            if(j>g){
                if(p.x!=a && p.y!=b){
                p.x-=1;
                 p.y+=1;
                   etiqueta3.setLocation(p);
                } 
            }
        }
         
//Peon Blanco 3          
          
            if(Pieza==7){
        
            Point p = etiqueta4.getLocation();
            
                    if(j<g){
                  
                if(p.x!=a && p.y!=b){
                p.x+=1;
                 p.y+=1;
             
                   etiqueta4.setLocation(p);
                } 
            }
            
            if(j==g){
                if(p.y!=b){
                p.y+=1;
                   System.out.println("Es"+p);
                   etiqueta4.setLocation(p);
                } 
            }
            
            if(j>g){
                if(p.x!=a && p.y!=b){
                p.x-=1;
                 p.y+=1;
                   etiqueta4.setLocation(p);
                } 
            }
            
        }
            
//Peon Blanco 4            
          if(Pieza==8){
        
            Point p = etiqueta5.getLocation();
            
                if(j<g){
                  
                if(p.x!=a && p.y!=b){
                p.x+=1;
                 p.y+=1;
             
                   etiqueta5.setLocation(p);
                } 
            }
            
            if(j==g){
                if(p.y!=b){
                p.y+=1;
                   System.out.println("Es"+p);
                   etiqueta5.setLocation(p);
                } 
            }
            
            if(j>g){
                if(p.x!=a && p.y!=b){
                p.x-=1;
                 p.y+=1;
                   etiqueta5.setLocation(p);
                } 
            }
        }
}
          
//Reina Negra 1     

if (Turns==1){

       if (Pieza>10 && Pieza < 15){
       
           Point p = etiqueta19.getLocation();
           
                if(j<g){
                if(p.x!=a ){
                p.x=+1;
              
                   etiqueta19.setLocation(p);
                } 
            }
          
            if(j>g){
                if(p.x!=a ){
                p.x=-1;
              
                   etiqueta19.setLocation(p);
                } 
            }
           
               if(i>f){
                if(p.y!=b ){
                p.y=-1;
              
                   etiqueta19.setLocation(p);
                } 
            }
            
           if(i<f){
                if(p.y!=b ){
                p.y=+1;
              
                   etiqueta19.setLocation(p);
                } 
            }
       }

}
//Reina Blanca 1

if (Turns==0){

   if (Pieza>10 && Pieza < 15){
       
           Point p = etiqueta23.getLocation();
           
                if(j<g){
                if(p.x!=a ){
                p.x=+1;
              
                   etiqueta23.setLocation(p);
                } 
            }
          
            if(j>g){
                if(p.x!=a ){
                p.x=-1;
              
                   etiqueta23.setLocation(p);
                } 
            }
           
               if(i>f){
                if(p.y!=b ){
                p.y=-1;
              
                   etiqueta23.setLocation(p);
                } 
            }
            
           if(i<f){
                if(p.y!=b ){
                p.y=+1;
              
                   etiqueta23.setLocation(p);
                } 
            }
       }
   


        }
        }
    }
    
    //Manejo de Desaparición con Animación
    
    
    //Manejo de Variable Turnos
    private void Turnos (){
        
       
        Turns++;
        
        if (Turns==2){
         Turns=0;
        }
        
    }
    
    //Coordenadas
    private void Coordinates (){
     
//Coordenadas Horizontales Iniciales
        if (p==1){
            System.out.println("H");
            xp=73;
        }
        
        if (p==2){
            xp=115;
        }
          
        if (p==3){
            xp=155;
        }
        if (p==4){
            xp=197;
        }
                  
        if (p==5){
            xp=237;
        }
         
        if (p==6){
            xp=279;
        }
         
        if (p==7){
            xp=319;
        }
          
        if (p==8){
            xp=361;
        }
         
        
//Coordenadas Verticales Iniciales
        if (t==1){
            yp=365;
        }
        
         if (t==2){
            yp=319;
        }
         
          if (t==3){
            yp=279;
        }
          
           if (t==4){
            yp=237;
        }
           
            if (t==5){
            yp=197;
        }
            
             if (t==6){
            yp=155;
        }
             
              if (t==7){
            yp=115;
        }
        
         if (t==8){
            yp=73;
        }
        
//Coordenadas Horizontales Finales
          if (g==1){
            a=73;
        }
        
        if (g==2){
            a=115;
        }
          
        if (g==3){
            a=155;
        }
        if (g==4){
            a=197;
        }
                  
        if (g==5){
            a=237;
        }
         
        if (g==6){
            a=279;
        }
         
        if (g==7){
            a=319;
        }
          
        if (g==8){
            a=361;
        }
         
        
//Coordenadas Verticales Finales
        if (f==1){
            b=365;
        }
        
         if (f==2){
            b=319;
        }
         
          if (f==3){
            b=279;
        }
          
           if(f==4){
            b=237;
        }
           
            if (f==5){
            b=197;
        }
            
             if (f==6){
            b=155;
        }
             
              if (f==7){
            b=115;
        }
        
         if (f==8){
            b=73;
        }
         
         
         
         
         
    }
    
    //spawn de Piezas
    private void Spawn(){
    
        
        //Manejo de Jlabels Sin animaciones con Desaparicion
       int Desa1=0, Desa2=0, Desa3=0, Desa4=0, Desa5=0, Desa6=0, Desa7=0, Desa8=0, Desa9=0, Desa10=0;
        
             for(int p = 1; p<=8; p++){
         for (int t = 1; t<=8; t++){
      
            if (t==1){
      
            xp=73;
        }
        
        if (t==2){
            xp=115;
        }
          
        if (t==3){
            xp=155;
        }
        if (t==4){
            xp=197;
        }
                  
        if (t==5){
            xp=237;
        }
         
        if (t==6){
            xp=279;
        }
         
        if (t==7){
            xp=319;
        }
          
        if (t==8){
            xp=361;
        }
        
           if (p==1){
            yp=365;
        }
        
         if (p==2){
            yp=319;
        }
         
          if (p==3){
            yp=279;
        }
          
           if (p==4){
            yp=237;
        }
           
            if (p==5){
            yp=197;
        }
            
             if (p==6){
            yp=155;
        }
             
              if (p==7){
            yp=115;
        }
        
         if (p==8){
            yp=73;
        }
            
             
             if (Tablero[p][t]==1){
                
                 etiqueta6.setLocation(xp, yp);
                 Desa1++;
             }
             
                 if (Tablero[p][t]==2){
                     
                 etiqueta7.setLocation(xp, yp);
             Desa2++;
             }
                 
                     if (Tablero[p][t]==3){
                      
                 etiqueta8.setLocation(xp, yp);
               Desa3++;
             }
                     
                         if (Tablero[p][t]==4){
                            
                 etiqueta9.setLocation(xp, yp);
               Desa4++;
             }
                         
                          if (Tablero[p][t]==5){
                            
                 etiqueta2.setLocation(xp, yp);
               Desa5++;
             }
                          
                       if (Tablero[p][t]==6){
                            
                 etiqueta3.setLocation(xp, yp);
               Desa6++;
             }
                                   
                        if (Tablero[p][t]==7){
                            
                 etiqueta4.setLocation(xp, yp);
               Desa7++;
             }
                if (Tablero[p][t]==8){
                            
                 etiqueta5.setLocation(xp, yp);
              Desa8++;
             }
                         
                 if (Tablero[p][t]>10 && Tablero[p][t]<15){
                            
                 etiqueta23.setLocation(xp, yp);
               Desa9++;
             }
                               
             
              if (Tablero[p][t]>14 && Tablero[p][t]<19){
                            
                 etiqueta19.setLocation(xp, yp);
             Desa10++;
             }
                  
}  
}
             
             
              if (Desa1!=1){
               etiqueta6.setLocation(1000,1000);
              }
              
               if (Desa2!=1){
               etiqueta7.setLocation(1000,1000);
              }
                if (Desa3!=1){
               etiqueta8.setLocation(1000,1000);
              }
                 if (Desa4!=1){
               etiqueta9.setLocation(1000,1000);
              }
                  if (Desa5!=1){
               etiqueta2.setLocation(1000,1000);
              }
                   if (Desa6!=1){
               etiqueta3.setLocation(1000,1000);
              }
                    if (Desa7!=1){
               etiqueta4.setLocation(1000,1000);
              }
                     if (Desa8!=1){
               etiqueta5.setLocation(1000,1000);
              }
                      if (Desa9!=1){
               etiqueta19.setLocation(1000,1000);
              }
                       if (Desa10!=1){
               etiqueta23.setLocation(1000,1000);
              }
              
              
            
             
    }
    
    
    
    }


    
