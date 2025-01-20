/*
 * Autores: Juan Paredes y Jose Bravo
 * Fecha: 8 de Junio del 2024
 * Descripci�n: Este programa lee un archivo de texto con datos de �rboles binarios de b�squeda (ABB),
 * crea un ABB para cada l�nea del archivo, e imprime informaci�n sobre cada �rbol.
 */

#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include <string>
#include <cstdlib>

using namespace std;

// Estructura para los nodos del �rbol
struct Node{
  string data; // Dato del nodo
  Node *hi; // Hijo izquierdo
  Node *hd; // Hijo derecho

  // Constructor de la clase Node
  Node(string d, Node* hI = NULL, Node* hD = NULL) : data(d), hi(hI), hd(hD) {}
};


/*
 * Clase Arbol: Esta clase representa un �rbol binario de b�squeda (ABB).
 * Cada instancia de Arbol tiene una ra�z que es un puntero a un Node.
 */
class Arbol{
	
  private:
    Node *rot; // Ra�z del �rbol. Es un puntero a la ra�z del ABB.
    
  public:
  	
  	
    /*
     * Constructor de la clase Arbol: Inicializa la ra�z del �rbol como NULL.
     */
    Arbol() : rot(NULL) {} // Inicializaci�n de rot en el constructor de la clase Arbol
    
    /*
     * Funci�n insertar: Inserta un nuevo nodo en el ABB.
     * data: El dato a insertar en el ABB.
     */
    void insertar(string data){
      if (rot == NULL) {
        rot = new Node(data);
      } else {
        insertar(data, rot);
      }
    }
    
    /*
     * Funci�n insertar: Inserta un nuevo nodo en el ABB.
     * data: El dato a insertar en el ABB.
     * node: El nodo actual en el ABB.
     */
    void insertar(string data, Node *node){
      if(data <= node->data){
        if(node->hi == NULL){
          node->hi = new Node(data);
        } else{
          insertar(data, node->hi);
        }
      } else{
        if(node->hd == NULL){
          node->hd = new Node(data);
        } else{
          insertar(data, node->hd);
        }
      }
    }
    
    /*
     * Funci�n imprimirPostOrden: Imprime los nodos del ABB en postorden.
     */
    void imprimirPostOrden(){
      imprimirPostOrden(rot);
      cout << "\n";
    }
    
    /*
     * Funci�n imprimirPostOrden: Imprime los nodos del ABB en postorden.
     * node: El nodo actual en el ABB.
     */
    void imprimirPostOrden(Node *node){
      if(node == NULL){
        return;
      }
      
      imprimirPostOrden(node->hi);
      imprimirPostOrden(node->hd);
      cout<< node->data << " ";
    }
    
    /*
     * Funci�n altura: Devuelve la altura del ABB.
     */
    int altura() {
        return altura(rot);
    }
    
    /*
     * Funci�n altura: Devuelve la altura del ABB.
     * node: El nodo actual en el ABB.
     */
    int altura(Node* node) {
        if (node == NULL) {
            return 0;
        } else {
            return 1 + max(altura(node->hi), altura(node->hd));
        }
    }
    
    /*
     * Funci�n max: Devuelve el m�ximo de dos n�meros.
     * a, b: Los dos n�meros a comparar.
     */
    int max(int a, int b) {
        return (a > b) ? a : b;
    }
    
    /*
     * Funci�n contarHojas: Devuelve la cantidad de nodos hoja en el ABB.
     */
    int contarHojas(){
      return contarHojas(rot);
    }
    
    /*
     * Funci�n contarHojas: Devuelve la cantidad de nodos hoja en el ABB.
     * node: El nodo actual en el ABB.
     */
    int contarHojas(Node* node) {
        if (node == NULL) {
            return 0;
        } else if (node->hi == NULL && node->hd == NULL) {
            return 1;
        } else {
            return contarHojas(node->hi) + contarHojas(node->hd);
        }
    }
    
    /*
     * Funci�n estaEquilibrado: Devuelve true si el ABB est� equilibrado, false en caso contrario.
     */
    bool estaEquilibrado() {
        return estaEquilibrado(rot);
    }
    
    /*
     * Funci�n estaEquilibrado: Devuelve true si el ABB est� equilibrado, false en caso contrario.
     * node: El nodo actual en el ABB.
     */
    bool estaEquilibrado(Node* node) {
        if (node == NULL) {
            return true;
        } else {
            int alturaIzquierda = altura(node->hi);
            int alturaDerecha = altura(node->hd);
            return abs(alturaIzquierda - alturaDerecha) <= 1 && estaEquilibrado(node->hi) && estaEquilibrado(node->hd);
        }
    }
    
    /*
     * Funci�n imprimirEnOrdenInverso: Imprime los nodos del ABB en orden inverso.
     */
    void imprimirEnOrdenInverso(){
      imprimirEnOrdenInverso(rot, 0);
      cout << "\n";
    }

    /*
     * Funci�n imprimirEnOrdenInverso: Imprime los nodos del ABB.
     * node: El nodo actual en el ABB.
     * profundidad: La profundidad actual en el ABB.
     */
    void imprimirEnOrdenInverso(Node *node, int profundidad){
      if(node == NULL){
        return;
      }
  
      imprimirEnOrdenInverso(node->hd, profundidad + 1);
      for(int i = 0; i < profundidad; i++){
        cout << "  ";
      }
      cout << node->data << "\n";
      imprimirEnOrdenInverso(node->hi, profundidad + 1);
    }
    
    //Para lograr la forma del ejemplo del word
};

int main(){
	
	    //Datos por Linea
  		string linea;
  		//Contador de Arbol
 		int numArbol = 1;
 		
        //Vector para mover la informacion del .txt
  		vector<string> datas;
  
		cout << "    _       _         _          _    _               _        " << endl;
		cout << "   /_\\  _ _| |__  ___| |___ ___ | |__(_)_ _  __ _ _ _(_)___ ___" << endl;
		cout << "  / _ \\| '_| '_ \\/ _ \\ / -_|_-< | '_ \\ | ' \\/ _` | '_| / _ (_-<" << endl;
		cout << " /_/ \\_\\_| |_.__/\\___/_\\___/__/ |_.__/_|_||_\\__,_|_| |_\\___/__/" << endl;
		cout <<endl;

        //Procesos de Archivo
		ifstream arc("arbol.txt");
		  
		while(getline(arc, linea)){
			
		  	cout << "--------------------------------------------------------\n";
		    Arbol ar;
		    istringstream ss(linea);
		    
		    string dat;
		    
		    while (ss >> dat) {datas.push_back(dat);}
		      
		    for(int i = 1; i < datas.size(); i++){
		    	
		        ar.insertar(datas[i]);
		    }
		    
		    cout <<"\t\t\tArbol #" << numArbol << "\n";
		    cout << "--------------------------------------------------------\n";
		    cout <<"- Datos: ";
		    cout << linea <<'\n';
		    
		    //Imprimir Post Orden
		    cout << "- PostOrden: ";
		    ar.imprimirPostOrden();
		    
		    //Altura de Arbol
		    cout << "- Altura del arbol: " << ar.altura() << endl;
		    
		    //Hojas del arbool
		    cout << "- Cantidad de nodos hoja: " << ar.contarHojas() << endl;
		    cout <<endl;
		    
		    
		    //Equilibrio del Arbol
		    if(ar.estaEquilibrado()) {
		        cout << "El arbol esta equilibrado." << endl;
		    } else {
		        cout << "El arbol no esta equilibrado." << endl;
		    }
		    
		    //Imprimir Arbol de forma visual como ejemplo del Word
		    cout<<endl;
		    cout << "Visualizacion del arbol:\n";
		    cout<<endl;
		    ar.imprimirEnOrdenInverso();
		    
		    cout << "--------------------------------------------------------\n";
		    numArbol++;
		        
		     cout<<endl;
		     cout<<"pulse enter para continuar";
		     
			  
		    
		    datas.clear(); 
		    cin.get(); // Presiona cualquier tecla para continuar con el siguiente �rbol
		    cout<<endl;
	
		  }
		  
		  return 0;
		}
