#include <iostream>
#include <vector>
#include <fstream>
#include <limits>
#include <queue>
#include <unistd.h> // Para usar usleep
#include <sstream>
#include <conio.h>
#include <cstdlib>

using namespace std; // Agregado

const int MAX = 100; // Un número máximo de vértices
const int INF = numeric_limits<int>::max();

class Grafo {
private:
  int numVertices;
  vector<vector<int>> matrizAdyacencia;
 string letras = "ABCDEFGH";
public:
  Grafo(int numVertices) : numVertices(numVertices) {
    matrizAdyacencia.resize(numVertices, vector<int>(numVertices, INF));
    for (int i = 0; i < numVertices; ++i) {
      matrizAdyacencia[i][i] = 0;
    }
  }

//Metodo para agregar Arista
  void agregarArista(char origen, char destino, int peso) {
    int idxOrigen = origen - 'A';
    int idxDestino = destino - 'A';
    matrizAdyacencia[idxOrigen][idxDestino] = peso;
  }


  // Algoritmo de Warshall
  void warshall() {
    vector<vector<int>> copiaMatriz = matrizAdyacencia; // Crear una copia de la matriz original
    for (int k = 0; k < numVertices; ++k) {
      for (int i = 0; i < numVertices; ++i) {
        for (int j = 0; j < numVertices; ++j) {
          if (copiaMatriz[i][k] != INF && copiaMatriz[k][j] != INF) {
            copiaMatriz[i][j] = min(copiaMatriz[i][j], copiaMatriz[i][k] + copiaMatriz[k][j]);
          }
        }
      }
    }
    matrizAdyacencia = copiaMatriz; // Actualizar la matriz original con la copia modificada
  }

  // Método para verificar si es fuertemente conexo
  bool esFuertementeConexo() {
    warshall();
    for (int i = 0; i < numVertices; ++i) {
      for (int j = 0; j < numVertices; ++j) {
        if (i != j && matrizAdyacencia[i][j] == INF) {
          return false;
        }
      }
    }
    return true;
  }

// Método para identificar nodos fuente y pozos
 void identificarNodosFuenteYPozo() {
   // Letras de las aristas
    for (int i = 0; i < numVertices; ++i) {
        bool esFuente = true, esPozo = true;
        for (int j = 0; j < numVertices; ++j) {
            if (i != j && matrizAdyacencia[j][i] != INF) {
                esFuente = false;
            }
            if (i != j && matrizAdyacencia[i][j] != INF) {
                esPozo = false;
            }
        }
        if (esFuente) {
            cout << "\t\tNodo " << letras[i] << " es fuente." << endl;
        }
        if (esPozo) {
            cout << "\t\tNodo " << letras[i] << " es pozo." << endl;
        }
    }
}

// Método para eliminar un nodo
void eliminarNodo(char nodoID) {
    int idx = toupper(nodoID) - 'A';
    matrizAdyacencia.erase(matrizAdyacencia.begin() + idx); // Eliminar fila
    for (auto& fila : matrizAdyacencia) {
        fila.erase(fila.begin() + idx); // Eliminar columna
    }
    --numVertices;

    // Actualizar las letras de las aristas
    string nuevasLetras;
    for (int i = 0; i < letras.size(); ++i) {
        if (i != idx) {
            nuevasLetras += letras[i];
        }
    }
    letras = nuevasLetras;

    cout << "\t\tNodo Eliminado" << endl;
}

// Método para imprimir el grafo
void imprimirGrafo() {
    cout << "\n";
 // Letras de las aristas
    vector<bool> nodoEliminado(matrizAdyacencia.size(), false); // Para rastrear los nodos eliminados

    // Imprimir encabezado con las letras de las aristas
    cout << "\t\t\t\t\t      ";
    for (int i = 0; i < matrizAdyacencia.size(); ++i) {
        if (!nodoEliminado[i]) {
            cout << letras[i] << " ";
        }
    }
    cout << endl;

    // Imprimir matriz de adyacencia
    for (int i = 0; i < matrizAdyacencia.size(); ++i) {
        if (!nodoEliminado[i]) {
            cout << "\t\t\t\t\t    " << letras[i] << " ";
            for (int j = 0; j < matrizAdyacencia[i].size(); ++j) {
                if (!nodoEliminado[j]) {
                    if (matrizAdyacencia[i][j] == INF) {
                        cout << "0 ";
                    } else {
                        cout << matrizAdyacencia[i][j] << " ";
                    }
                }
            }
            cout << endl;
        }
    }

    cout << "\n";
}

// Algoritmo de Dijkstra
 void algoritmoDijkstra(char origen, char destino) {
  int idxOrigen = toupper(origen) - 'A';
  int idxDestino = toupper(destino) - 'A';
  
  vector<int> distancias(numVertices, INF);
  distancias[idxOrigen] = 0;

  priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> cola;
  cola.push({0, idxOrigen});

  while (!cola.empty()) {
    int distanciaActual = cola.top().first;
    int verticeActual = cola.top().second;
    cola.pop();

    if (distanciaActual > distancias[verticeActual]) continue;

    for (int i = 0; i < numVertices; ++i) {
      int peso = matrizAdyacencia[verticeActual][i];
      if (peso != INF && distancias[i] > distanciaActual + peso) {
        distancias[i] = distanciaActual + peso;
        cola.push({distancias[i], i});
      }
    }
  }
  
  
if (distancias[idxDestino] == INF) {
        cout << "\t\tNo hay una ruta válida desde " << origen << " hasta " << destino << endl;
    } else {
        cout << "\t\tLa ruta minima desde " << origen << " hasta " << destino
             << " tiene un peso de " << distancias[idxDestino] << endl;
}

}

};

//Funcion para el manejo del archivo y el llenado de variables.
int leerArchivoYCrearGrafo(Grafo& grafo, const string& nombreArchivo) {
  ifstream archivo(nombreArchivo);
  if (!archivo.is_open()) {
    cerr << "Error al abrir el archivo." << endl;
    return 0;
  }

  int numVertices;
  archivo >> numVertices; // Leer el número de vértices
  cout << "\t\t\t\tNumero de vertices: " << numVertices << endl;

  Grafo nuevoGrafo(numVertices);

  archivo.ignore(numeric_limits<streamsize>::max(), '\n');

  string linea;
  while (getline(archivo, linea)) {
    stringstream ss(linea);

char origen, destino, flecha1, flecha2;
    int peso;


if (!(ss >> origen >> flecha1 >> flecha2 >> destino >> peso)) {
      cerr << "\t\tError al leer la línea: " << linea << endl;
      continue; 
    }

    if (flecha1 != '-' || flecha2 != '>') {
      cerr << "\t\t\t\tFormato de línea incorrecto: " << linea << endl;
      continue; // El formato de la flecha no es correcto
    }

    // Convertir a mayúscula por si acaso
    origen = toupper(origen);
    destino = toupper(destino);

    // Agregar la arista al grafo
    nuevoGrafo.agregarArista(origen, destino, peso);
    cout << "\t\t\t\tArista de " << origen << " a " << destino << " con peso " << peso << endl;
  }

  grafo = nuevoGrafo;
  return 1;
}


int main() {
  char id,origen,destino;
  int opcion;
  Grafo grafo(0); 
  
  
  	cout << endl;
 	std::cout << std::endl;
    std::cout << "\t\t     ,-------------------------,     .d8888b.                    .d888                         " << std::endl; usleep(150000);
    std::cout << "\t\t     |  /--------------------\\  |   d88P  Y88b                  d88P\"                          " << std::endl; usleep(150000);
    std::cout << "\t\t     | |                      | |   888    888                  888                            " << std::endl; usleep(150000);
    std::cout << "\t\t     | |   Juan Paredes       | |   888        888d888  8888b.  888888  .d88b.  .d8888b        " << std::endl; usleep(150000);
    std::cout << "\t\t     | |                      | |   888  88888 888P\"       \"88b 888     d88""88b  88K            " << std::endl; usleep(150000);
    std::cout << "\t\t     | |  Estructura de Datos | |   888    888 888     .d888888 888    888  888 \"Y8888b.       " << std::endl; usleep(150000);
    std::cout << "\t\t     |  \\____________________/  |   Y88b  d88P 888     888  888 888    Y88..88P      X88       " << std::endl; usleep(150000);
    std::cout << "\t\t     |__________________________| \   \"Y8888P88 888     \"Y888888 888     \"Y88P\"   88888P'       " << std::endl; usleep(150000);
    std::cout << "\t\t    ,----\\_____   []   _______/--------," << std::endl; usleep(150000);
    std::cout << "\t\t  /        /______________\\           /|" << std::endl; usleep(150000);
    std::cout << "\t\t/___________________________________ / |   ___" << std::endl; usleep(150000);
    std::cout << "\t\t|                                   |  |      )" << std::endl; usleep(150000);
    std::cout << "\t\t| _ _ _                  [-------]  |  |      (" << std::endl; usleep(150000);
    std::cout << "\t\t| o o o                  [-------]  | /      _)_" << std::endl; usleep(150000);
    std::cout << "\t\t|__________________________________ |/      /   /" << std::endl; usleep(150000);
    std::cout << "\t\t/-------------------------------------/|    ( )/" << std::endl; usleep(150000);
    std::cout << "\t\t/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/ /" << std::endl; usleep(150000);
    std::cout << "\t\t/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/ /" << std::endl; usleep(150000);
    std::cout << "\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" << std::endl; usleep(150000);
    std::cout << std::endl;

	           	 getchar();
system("CLS");
    	cout <<"\t\t========================= DATOS DEL GRAFO =========================\n";
    	cout<< "\n";
    	
	  if(leerArchivoYCrearGrafo(grafo, "arista.txt")!=0){
	  	
   	   do{
   	 
        cout <<"\n";
    	cout <<"\t\t========================= MATRIZ DEL GRAFO =========================\n";
        grafo.imprimirGrafo();
        
        cout <<"\t\t======================== MENU DE OPERACIONES =======================\n";
        cout << "\t\t1) Determinar si el grafo es fuertemente conexo" << endl;
        cout << "\t\t2) Eliminar Nodo" << endl;
        cout << "\t\t3) Hallar ruta minima (Dijkstra)" << endl;
        cout << "\t\t4) Salir" << endl;
        cout << "\t\tR: ";
        cin>>opcion; system("CLS");
        
        switch(opcion){
        	
          case 1:
	       if (grafo.esFuertementeConexo()) {
	       	
	             cout << "\t\tEl grafo es fuertemente conexo." << endl;
	             cin.ignore();
	           	 getchar();
	           
	          } else {
	          	
	              cout << "\t\tEl grafo no es fuertemente conexo." << endl;
	              cout<<"\t\tDesea conocer los nodos Fuentes y pozo? (Si=1)(No=0)\n";
	              cout << "\t\tR: ";
        		  cin>>opcion; 
	              if(opcion==1){
	                 grafo.identificarNodosFuenteYPozo();
	             
				  }
				  
	              
	          cin.ignore();
	           	 getchar();
	        
	            system("CLS");
	          }    
          break;
          
          case 2:
            cout << "\t\tElija nodo a eliminar: ";
            cin>>id;
            grafo.eliminarNodo(id);
          break;
          case 3:
            cout << "\t\tElija vertice origen: ";
            cin>>origen;
            cout << "\t\tElija vertice destino: ";
            cin>>destino;
            grafo.algoritmoDijkstra(origen,destino);
            cin.ignore();
           
              
          getchar();
           	 system("CLS");
          break;
          case 4:
          break;
          default:
        break;
      }
    }while(opcion != 4);
  }
  return 0;
}
