#include <iostream>
#include <fstream>
#include <iomanip>
#include <cstdlib>
#include <cstring>
#include <vector>
#include <conio.h>
#include <string>
#include <algorithm>
#include <chrono>
#include <sstream>

using namespace std;
using namespace std::chrono;

//Clase para el manejo de Errores
class OpcionNoValidaException : public exception {
public:
    const char* what() const throw() {
        return "OPCION NO VALIDA.";
    }
};

//Clase de Tablas:
class Producto {
	public:
		vector<int> Id;
		vector<int> Id_proveedor;
		vector<int> stock;
		vector<float> precio;
		vector<string> descripcion;
		vector<int> stock_min;
	public:	
		// Imprimir la información de todos los productos
		void imprimir() {
			// Lectura del archivo (modificar para binario si es necesario)
			ifstream arc("produc.dat", ios::binary);
			if (arc.is_open()) {
				int id, idp, st, stm;
				float prec;
				string descp;
				while (arc >> id >> idp >> st >> prec >> descp >> stm) {
					replace(descp.begin(), descp.end(), '_', ' ');
					
					Id.push_back(id);
					Id_proveedor.push_back(idp);
					stock.push_back(st);
					precio.push_back(prec);
					descripcion.push_back(descp);
					stock_min.push_back(stm);
				}
				arc.close();
			}
		
			// Mostrar datos
			cout << " ===============================| LISTA DE PRODUCTOS |===============================\n";
	    	cout << " ID:      ID DE PROVEEDOR:  STOCK:  PRECIO:  DESCRIPCION:              STOCK MINIMO: \n";
	    	cout << " ======== ================= ======= ======== ========================= ==============\n";
			for (int i = 0; i < Id.size(); ++i) {
				cout << setiosflags(ios::left) << " " << setw(8) << Id[i] << " " << setw(17) << Id_proveedor[i] << " " << setw(7) << stock[i] << " " 
				<< setw(8) << precio[i] << " " << setw(25) << descripcion[i] << " " << setw(14) << stock_min[i] << endl;
				cout << " ======== ================= ======= ======== ========================= ==============\n";
			}
		}
		
		void impresion(){
			
					// Mostrar datos
			cout << " ===============================| LISTA DE PRODUCTOS |===============================\n";
	    	cout << " ID:      ID DE PROVEEDOR:  STOCK:  PRECIO:  DESCRIPCION:              STOCK MINIMO: \n";
	    	cout << " ======== ================= ======= ======== ========================= ==============\n";
			for (int i = 0; i < Id.size(); ++i) {
				cout << setiosflags(ios::left) << " " << setw(8) << Id[i] << " " << setw(17) << Id_proveedor[i] << " " << setw(7) << stock[i] << " " 
				<< setw(8) << precio[i] << " " << setw(25) << descripcion[i] << " " << setw(14) << stock_min[i] << endl;
				cout << " ======== ================= ======= ======== ========================= ==============\n";
			}
			
		}
		
		
		void leer(){
			
				ifstream arc("produc.dat", ios::binary);
			if (arc.is_open()) {
				int id, idp, st, stm;
				float prec;
				string descp;
				while (arc >> id >> idp >> st >> prec >> descp >> stm) {
					replace(descp.begin(), descp.end(), '_', ' ');
					
					Id.push_back(id);
					Id_proveedor.push_back(idp);
					stock.push_back(st);
					precio.push_back(prec);
					descripcion.push_back(descp);
					stock_min.push_back(stm);
				}
				arc.close();
			}
			
		}
	
		
		
			
		// Restaurar la clase a su estado inicial
		void restaurar() {
			Id.clear();
			Id_proveedor.clear();
			stock.clear();
			precio.clear();
			descripcion.clear();
			stock_min.clear();
		}
		
		
};

//Clase de Tablas:
class Cliente {
	public:
		vector<int> Id;
		vector<string> nombre;
		vector<string> direccion;
		vector<string> telefono;
	public:
		// Agregar un nuevo cliente
		void AgregarDato5(int id, char nom[45], char dir[70], char telf[18]) {
			Id.push_back(id);
			nombre.push_back(nom);
			direccion.push_back(dir);
			telefono.push_back(telf);
			
			for (int i = 0; i < Id.size(); ++i) {
				replace(nombre[i].begin(), nombre[i].end(), ' ', '_');
				replace(direccion[i].begin(), direccion[i].end(), ' ', '_');
			}
			// Escritura en archivo (modificar para binario si es necesario)
			ofstream arc("clien.dat", ios::binary);
			if (arc.is_open()) {
				for (int i = 0; i < Id.size(); ++i) {
					arc << Id[i] << ' ' << nombre[i] << ' ' << direccion[i] << ' ' << telefono[i] << '\n';
				}
				arc.close();
			}
		}
	
		// Imprimir la información de todos los clientes
		void imprimir() {
			// Lectura del archivo (modificar para binario si es necesario)
			ifstream arc("clien.dat", ios::binary);
			if (arc.is_open()) {
				int id;
				string dir, nom, telf;
				while (arc >> id >> nom >> dir >> telf) {
					replace(dir.begin(), dir.end(), '_', ' ');
					replace(nom.begin(), nom.end(), '_', ' ');
					
					Id.push_back(id);
					direccion.push_back(dir);
					nombre.push_back(nom);
					telefono.push_back(telf);
				}
				arc.close();
			}
			
			// Mostrar datos
			cout << " ==============================================| LISTA DE CLIENTES |===============================================\n";
	    	cout << " ID:       NOMBRE:                          DIRECCION:                                    TELEFONO:                \n";
	    	cout << " ======== ================================ ============================================= =========================\n";
			for (int i = 0; i < Id.size(); ++i) {
				cout << setiosflags(ios::left) << " " << setw(9) << Id[i] << " " << setw(32) << nombre[i] << " " 
				<< setw(45) << direccion[i] << " " << setw(25) << telefono[i] << endl;
				cout << " ======== ================================ ============================================= =========================\n";
			}
		}
		
		void leer(){
			// Lectura del archivo (modificar para binario si es necesario)
	    	ifstream arc("clien.dat", ios::binary);
			if (arc.is_open()) {
				int id;
				string dir, nom, telf;
				while (arc >> id >> dir >> nom >> telf) {
					replace(dir.begin(), dir.end(), '_', ' ');
					replace(nom.begin(), nom.end(), '_', ' ');
					
					Id.push_back(id);
					direccion.push_back(dir);
					nombre.push_back(nom);
					telefono.push_back(telf);
				}
				arc.close();
			}
		}
		
		void Cambiar(int opc){
			string renpS;
			int Cid, vpos, renp;
			
			cout<<"|Escriba la ID del cliente"<<endl;
			cout<<"\nR: ";
			cin>>Cid;
			
			ifstream arc("clien.dat", ios::binary);
		    if (arc.is_open()) {
		        int id;
				string dir, nom, telf;
				while (arc >> id >> nom >> dir >> telf) {
					replace(dir.begin(), dir.end(), '_', ' ');
					replace(nom.begin(), nom.end(), '_', ' ');
					
	        		Id.push_back(id);
	        		direccion.push_back(dir);
	        		nombre.push_back(nom);
	        		telefono.push_back(telf);
				}
		        arc.close();
		    }
		    
		    borrarArchivo();
		    
		    //buscar posicion
		    bool sip = false ;
		    for (int i = 0; i < Id.size(); ++i){
				if(Id[i] == Cid){
		        	vpos = i;
		        	sip = true;
				}
			}
			if (sip == false){
				cout<<"\n|La Id no existe."<<endl;
				cin.ignore();
				getchar();
				opc = -1;
			}
			
			if(opc == 1){
				cout<<"\n|Escriba el nuevo Id:                         |Id actual: "<<Id[vpos]<<endl;
				cout<<"\nR: ";
				cin>>renp;
				Id[vpos] = renp;
			}else if(opc == 2){
				cout<<"\n|Escriba el nuevo nombre:                     |Nombre actual: "<<nombre[vpos]<<endl;
				cout<<"\nR: ";
				cin.ignore();
				getline(cin,renpS);
				nombre[vpos] = renpS;
			}else if(opc == 3){
				cout<<"\n|Escriba la nueva direccion:                   |Direccion actual: "<<direccion[vpos]<<endl;
				cout<<"\nR: ";
				cin.ignore();
				getline(cin,renpS);
				direccion[vpos] = renpS;
			}else if(opc == 4){
				cout<<"\n|Escriba el nuevo telefono:                   |Telefono actual: "<<telefono[vpos]<<endl;
				cout<<"\nR: ";
				cin.ignore();
				getline(cin,renpS);
				renpS.erase(remove(renpS.begin(), renpS.end(), ' '), renpS.end());
				telefono[vpos] = renpS;
			}
			
			for (int i = 0; i < Id.size(); ++i) {
				replace(nombre[i].begin(), nombre[i].end(), ' ', '_');
				replace(direccion[i].begin(), direccion[i].end(), ' ', '_');
			}
			
		    ofstream ac("clien.dat" , ios::binary);
		    if (ac.is_open()) {
		        for (int i = 0; i < Id.size(); ++i) {
		            ac << Id[i] << ' ' << nombre[i] << ' ' << direccion[i] << ' ' << telefono[i] << '\n';
		        }
		        ac.close();
		    }
		}
		
		// Restaurar la clase a su estado inicial
		void restaurar() {
			Id.clear();
			nombre.clear();
			direccion.clear();
			telefono.clear();
		}
	
		// **Métodos adicionales:**
	
		// Eliminar un espacio determinado de todos los vectores
		void eliminarEspacio() {
			int Cid, vpos;
				
			cout<<"|Escriba la ID del Cliente"<<endl;
			cout<<"\nR: ";
			cin>>Cid;
			
			ifstream arc("clien.dat", ios::binary);
			if (arc.is_open()) {
				int id;
				string dir, nom, telf;
				while (arc >> id >> dir >> nom >> telf) {
					Id.push_back(id);
					direccion.push_back(dir);
	        		nombre.push_back(nom);
	        		telefono.push_back(telf);
				}
				arc.close();
			}
	      	
			for (int i = 0; i < Id.size(); ++i){
				if(Id[i] == Cid){
			        vpos = i;
				}
			}
	  		
	    	if (vpos >= 0 && vpos < Id.size()) {
				Id.erase(Id.begin() + vpos);
				nombre.erase(nombre.begin() + vpos);
				direccion.erase(direccion.begin() + vpos);
				telefono.erase(telefono.begin() + vpos);
			}
	    
			ofstream eli("clien.dat" , ios::binary);
			if (eli.is_open()) {
				for (int i = 0; i < Id.size(); ++i) {
					eli << Id[i] << ' ' << direccion[i] << ' ' << nombre[i] << ' ' << telefono[i] << '\n';
				}
				arc.close();
			}
		}
		
		// Borrar el archivo y el contenido de todos los vectores
		void borrarArchivo() {
			ofstream arc("clien.dat", ios::binary);
			arc.close();
		}
	
		// Remplazar el espacio dado por un valor de un vector string
		void cambiarValor(vector<string>& vector, int indice, string valor) {
			if (indice >= 0 && indice < vector.size()) {
				vector[indice] = valor;
			} else {
				cout << "Índice fuera de rango." << endl;
			}
		}
	
		void cambiarValor(vector<int>& vector, int indice, int valor) {
			if (indice >= 0 && indice < vector.size()) {
				vector[indice] = valor;
			} else {
				cout << "Índice fuera de rango." << endl;
			}
		}
};

//Clase para el Quick sort
class Orden_Quicksorts{
	
	public:	
	
	// Función para intercambiar los elementos de los vectores
			void swaps(vector<string>& names, vector<int>& v1, vector<int>& v2, vector<int>& v3, vector<int>& v4, vector<float>& v5, int i, int j) {
    			swap(names[i], names[j]);
    			swap(v1[i], v1[j]);
			    swap(v2[i], v2[j]);
			    swap(v3[i], v3[j]);
			    swap(v4[i], v4[j]);
			    swap(v5[i], v5[j]);
			}

	// Función de partición del QuickSort
			int partition(vector<string>& names, vector<int>& v1, vector<int>& v2, vector<int>& v3, vector<int>& v4, vector<float>& v5, int low, int high) {
			    string pivot = names[high];
			    int i = (low - 1);
			
			    for (int j = low; j <= high - 1; j++) {
			        if (names[j] < pivot) {
			            i++;
			            swaps(names, v1, v2, v3, v4, v5, i, j);
			        }
			    }
			    swaps(names, v1, v2, v3, v4, v5, i + 1, high);
			    return (i + 1);
			}
			
	// Función de QuickSort
			void quickSort(vector<string>& names, vector<int>& v1, vector<int>& v2, vector<int>& v3, vector<int>& v4, vector<float>& v5, int low, int high) {
			    if (low < high) {
			        int pi = partition(names, v1, v2, v3, v4, v5, low, high);
			
			        quickSort(names, v1, v2, v3, v4, v5, low, pi - 1);
			        quickSort(names, v1, v2, v3, v4, v5, pi + 1, high);
			    }
			}
			
	// Función para ordenar los vectores
			void sortVectors(vector<string>& names, vector<int>& v1, vector<int>& v2, vector<int>& v3, vector<int>& v4, vector<float>& v5) {
			    quickSort(names, v1, v2, v3, v4, v5, 0, names.size() - 1);
			}
				
	
};

//Clase para La mezcla Directa
class RegistroDirecto {
public:
    int codigo;
    string nombre;

    // Método para leer un registro del archivo
    void leerRegistro(ifstream& archivo) {
        archivo >> codigo;
        archivo.ignore();
        getline(archivo, nombre);
    }

    // Método para escribir un registro en el archivo
    void escribirRegistro(ofstream& archivo) const {
        archivo << codigo << " " << nombre << endl;
    }
};

//Clase para la mezcla Natural
class Registro {
public:
    int ID;
    std::string Nombres_Apellidos;
    std::string Direccion;
    std::string NumeroTelefonico;

    Registro(int id, std::string nombres_apellidos, std::string direccion, std::string numeroTelefonico)
        : ID(id), Nombres_Apellidos(nombres_apellidos), Direccion(direccion), NumeroTelefonico(numeroTelefonico) {}
};

//Clase Principla para la mezcla Natural
class MezclaNatural {
	
public:


    void desplegar(string nombreArchivo) {
        ifstream dis(nombreArchivo, ios::binary);
        if (!dis.is_open()) {
            cout << "Error de Apertura-Lectura archivo: " << nombreArchivo << endl;
            return;
        }

        string nombre;
        int index = 0;
        
        
        
        while (getline(dis, nombre)) {
           cout << ++index << ") " << nombre << endl;
        }
    }

    void verificarOrdenamiento(string nombreArchivo) {
        ifstream dis(nombreArchivo, ios::binary);
        if (!dis.is_open()) {
            cout << "Error de Apertura-Lectura archivo: " << nombreArchivo << endl;
            return;
        }

        string actual, anterior;
        bool estaOrdenado = true;

        while (getline(dis, actual)) {
            if (anterior.empty()) {
                anterior = actual;
            }

         //   cout << actual << endl;

            anterior = actual;
        }

        if (estaOrdenado) {
            cout << "EL ARCHIVO ESTA ORDENADO" << endl;
        }
    }

    bool particion(string nombreArchivo, string archivo1, string archivo2) {
    ifstream dis(nombreArchivo, ios::binary);
    if (!dis.is_open()) {
        cout << "Error de Apertura-Lectura archivo: " << nombreArchivo << endl;
        return false;
    }

    ofstream* dos1 = new ofstream(archivo1, ios::binary);
    ofstream* dos2 = new ofstream(archivo2, ios::binary);
    if (!dos1->is_open() || !dos2->is_open()) {
        cout << "Error en la creacion o apertura del archivo 1" << endl;
        return false;
    }

    string actual, anterior;
    int indexOutputStream = 0;
    bool hayCambioDeSecuencia = false;

    while (getline(dis, actual)) {
        // Si la línea es vacía, continúa con la siguiente iteración
        if (actual.empty()) {
            continue;
        }

        if (!anterior.empty() && anterior >= actual) {  // Cambia aquí
            indexOutputStream = indexOutputStream == 0 ? 1 : 0;
            hayCambioDeSecuencia = true;
        }

        if (indexOutputStream == 0) {
            *dos1 << actual << endl;
        } else {
            *dos2 << actual << endl;
        }
        anterior = actual;
    }

    delete dos1;
    delete dos2;

    return hayCambioDeSecuencia;
}

    void fusion(string nombreArchivo, string archivo1, string archivo2) {
    ifstream* dis1 = new ifstream(archivo1, ios::binary);
    ifstream* dis2 = new ifstream(archivo2, ios::binary);
    if (!dis1->is_open() || !dis2->is_open()) {
        cout << "Error de Apertura-Lectura archivo: " << nombreArchivo << endl;
        return;
    }

    ofstream dos(nombreArchivo, ios::binary);
    if (!dos.is_open()) {
        cout << "Error de Apertura-Escritura archivo: " << nombreArchivo << endl;
        return;
    }

    string actual1, actual2, anterior1, anterior2;
    bool finArchivo1 = false, finArchivo2 = false;
    int indexArchivo = 0;

    getline(*dis1, actual1);
    getline(*dis2, actual2);

    while (dis1->good() && dis2->good()) {
        anterior1 = actual1;
        anterior2 = actual2;

        while (anterior1 <= actual1 && anterior2 <= actual2) {
            indexArchivo = (actual1 <= actual2) ? 0 : 1;
            if (indexArchivo == 0) {
                dos << actual1 << endl;
                anterior1 = actual1;
                if (dis1->good()) {
                    getline(*dis1, actual1);
                } else {
                    finArchivo1 = true;
                    break;
                }
            } else {
                dos << actual2 << endl;
                anterior2 = actual2;
                if (dis2->good()) {
                    getline(*dis2, actual2);
                } else {
                    finArchivo2 = true;
                    break;
                }
            }
        }

        indexArchivo = indexArchivo == 0 ? 1 : 0;


if (indexArchivo == 0) {
    while (anterior1 <= actual1) {
        if (!actual1.empty()) {
            dos << actual1 << endl;
        }
        anterior1 = actual1;
        if (dis1->good()) {
            getline(*dis1, actual1);
        } else {
            finArchivo1 = true;
            break;
        }
    }
} else {
    while (anterior2 <= actual2) {
        if (!actual2.empty()) {
            dos << actual2 << endl;
        }
        anterior2 = actual2;
        if (dis2->good()) {
            getline(*dis2, actual2);
        } else {
            finArchivo2 = true;
            break;
        }
    }
}



        
        
        
        
    }

    if (!finArchivo1) {
        dos << actual1 << endl;
        while (getline(*dis1, actual1)) {
            dos << actual1 << endl;
        }
    }

    if (!finArchivo2) {
        dos << actual2 << endl;
        while (getline(*dis2, actual2)) {
            dos << actual2 << endl;
        }
    }

    delete dis1;
    delete dis2;
}

    void ordenar(string nombreArchivo) {
    	
    auto start = high_resolution_clock::now();
    
    int index = 0;
    while (particion(nombreArchivo, "archivo1.txt", "archivo2.txt")) {
        //  cout << "Fusion de tu mama " << ++index << endl;
        fusion(nombreArchivo, "archivo1.txt", "archivo2.txt");
    }
    
    auto stop = high_resolution_clock::now();
    auto duration = duration_cast<milliseconds>(stop - start);
 	cout<<endl;
    cout << "Tiempo de duracion: " << duration.count() << " milisegundos" << endl;
}
       
};

//Funcion #1 para la Mezcla Directa
void merge(const string& NomArch, int l, int mit, int r) {
	int datL = mit - l + 1;
    int datR = r - mit;
    
    // Apertura de archivos
    ifstream archivoEntrada(NomArch.c_str());
    if (!archivoEntrada.is_open()) {
        cerr << "Error al abrir el archivo de entrada: " << NomArch << endl;
        return;
    }
	
	ofstream Aux("Aux.dat");
    if (!Aux.is_open()) {
        cerr << "Error al abrir el archivo auxiliar: Aux.dat" << endl;
        Aux.close();
        return;
    }
	
    ofstream AuxL("AuxL.dat");
    if (!AuxL.is_open()) {
        cerr << "Error al abrir el archivo auxiliar: AuxL.dat" << endl;
        AuxL.close();
        return;
    }
    
    ofstream AuxR("AuxR.dat");
    if (!AuxR.is_open()) {
        cerr << "Error al abrir el archivo auxiliar: AuxR.dat" << endl;
        AuxR.close();
        return;
    }
	
	// Separacion de datos a los archivos AuxL y AuxR
	RegistroDirecto registroM;
	int i = 0, j = 0, k = 0;
    while (archivoEntrada.good()) {
    	registroM.leerRegistro(archivoEntrada);
		if (archivoEntrada.eof()) break;
		
        if (k == l + i && i < datL) {
        	registroM.escribirRegistro(AuxL);
        	i++;
		}
		if (k == mit + 1 + j && j < datR){
			registroM.escribirRegistro(AuxR);
			j++;
		}
		k++;
    }
    
    AuxL.close();
    AuxR.close();
    archivoEntrada.close();
    
    // Reapertura de archivos 1
    ifstream AuxLi("AuxL.dat");
    if (!AuxLi.is_open()) {
        cerr << "Error al abrir el archivo auxiliar: AuxL.dat" << endl;
        AuxLi.close();
        return;
    }
    
    ifstream AuxRi("AuxR.dat");
    if (!AuxRi.is_open()) {
        cerr << "Error al abrir el archivo auxiliar: AuxR.dat" << endl;
        AuxRi.close();
        return;
    }
    
    ofstream AuxLR("AuxLR.dat");
    if (!AuxLR.is_open()) {
        cerr << "Error al abrir el archivo auxiliar: AuxLR.dat" << endl;
        AuxLR.close();
        return;
    }
    
    // Fase de ordenamiento
    RegistroDirecto registroA, registroB, registroP;
    if (AuxLi.good()) {
        registroA.leerRegistro(AuxLi);
    }
    if (AuxRi.good()) {
        registroB.leerRegistro(AuxRi);
    }
    
    while (AuxLi.good() && AuxRi.good()) {
    	if (registroA.nombre <= registroB.nombre) {
    		if (AuxLi.eof()) break;
    		registroA.escribirRegistro(AuxLR);
        	registroA.leerRegistro(AuxLi);
		} else{
			if (AuxRi.eof()) break;
			registroB.escribirRegistro(AuxLR);
        	registroB.leerRegistro(AuxRi);
		}
    }
    while (!AuxLi.eof()) {
    	if (AuxLi.eof()) break;
        registroA.escribirRegistro(AuxLR);
        registroA.leerRegistro(AuxLi);
    }
    while (!AuxRi.eof()) {
    	if (AuxRi.eof()) break;
        registroB.escribirRegistro(AuxLR);
        registroB.leerRegistro(AuxRi);
    }
	
    AuxLi.close();
    AuxRi.close();
    AuxLR.close();
    
    // Reapertura de archivos 2
    ifstream AuxLRi("AuxLR.dat");
    if (!AuxLRi.is_open()) {
        cerr << "Error al abrir el archivo auxiliar: AuxLR.dat" << endl;
        AuxLRi.close();
        return;
    }
    
    archivoEntrada.open(NomArch.c_str());
    
    // Fase de fusión auxiliar
    RegistroDirecto registroLR, registroEnt;
    int A = 0, f = 0;
    while (archivoEntrada.good() && AuxLRi.good()) {
    	registroEnt.leerRegistro(archivoEntrada);
    	if (A == l + f && l + f <= r){
			registroLR.leerRegistro(AuxLRi);
		}
		if (archivoEntrada.eof()) break;
		if (A == l + f && l + f <= r){
			registroLR.escribirRegistro(Aux);
			f++;
		}else{
			registroEnt.escribirRegistro(Aux);
		}
		A++;
    }
    
    Aux.close();
    AuxLRi.close();
	archivoEntrada.close();
	
    // Fase de fusión final
    ofstream archivoEntradaOut(NomArch.c_str(), ios::trunc);
    if (!archivoEntradaOut.is_open()) {
        cerr << "Error al abrir el archivo de entrada: " << NomArch << endl;
        return;
    }

    ifstream AuxIn("Aux.dat");
    if (!AuxIn.is_open()) {
        cerr << "Error al abrir el archivo auxiliar: Aux.dat" << endl;
        return;
    }

    RegistroDirecto bloqueAuxRegis;
    while (AuxIn.good()) {
        bloqueAuxRegis.leerRegistro(AuxIn);
        if (AuxIn.eof()) break;
		bloqueAuxRegis.escribirRegistro(archivoEntradaOut);
    }

    archivoEntradaOut.close();
    AuxIn.close();
	
	// Eliminar archivo auxiliar
    remove("AuxL.dat"); 
    remove("AuxR.dat");
    remove("AuxLR.dat");
    remove("Aux.dat");
}

//Funcion #2 para la Mezcla Directa
void mergeSort(const string& NomArch, int l, int r){
	//Ciclo de ordenamiento
	if(l < r){
		int mit = l + (r - l)/2;
		
		mergeSort(NomArch, l, mit);
		mergeSort(NomArch, mit + 1, r);
		
		merge(NomArch, l, mit, r);
	}
	
	
	
}

//Funcion Para la busqueda Binario
bool compararNombres(const string& nombre1, const string& nombre2) {
  return nombre1 < nombre2;
}


int main() {

 Producto v; Cliente Cli; Orden_Quicksorts Quick;
 int opcion;
 int subopcion;
 //getchar();

  do {
  	
  	   	try {
  	   		         system("cls");
   	cout << "                     Juan Paredes                    \n";
        cout << "                       _______                       \n";
        cout << "                      /      /\\                     \n";
        cout << "    ------------     /______/  \\     ------------  \n";
        cout << "             ---     \\      \\  /     ---           \n";
        cout << "                      \\______\\/                    \n";
        cout << "                                                     \n";
        cout << "                       DATABASE                      \n";
        cout << "              ____________________________           \n";
        cout << "                                                     \n";
        cout << "                                                     \n";
    	
    	cout << " ==================| ORDENAMIENTO |==================\n";
        cout << "1. Enlistas            \n";
        cout << "2. Ordenado Productos  \n";
        cout << "3. Ordenado Clientes   \n";
        cout << "4. Busqueda Producto   \n";
        cout << "5. Salir            \n";
        cout << " ======================================================\n";
        cout << "Por favor, elige una opcion: ";
        cin >> opcion;
        cout << " ======================================================\n";
 


    switch (opcion) {
      case 1:
      	     	do{
      	      system("cls");
      	cout << " ==================|   ENLISTAR   |==================\n";
        cout << "1. Producto        \n";
        cout << "2. Cliente         \n";
        cout << "3. Salir           \n";
        cout << " ======================================================\n";
        cout << "Por favor, elige una opcion: ";
        cin >> subopcion;
        cout << " ======================================================\n";
        
        				switch (subopcion) {
		   
						    case 1:
					    	    system("cls");
						    	v.restaurar();
						    	v.imprimir();
						        cin.ignore();
						    	getchar();
						   	break;
				   	
						   	case 2:
						   	   system("cls");
						   	   Cli.restaurar();
						   	   Cli.imprimir();
						   	   cin.ignore();
					           getchar();
						   	break; 
				   		
						   	case 3:
						   	break;
				   			
						   	default:
						   		
						   		 throw OpcionNoValidaException();
					                 cout << " ==================|Regresando...|==================\n";
						             getchar();
						             system("cls");
						   		
						   break;
				 }}while (subopcion !=3);
        
        break;
        
        
        case 2:{
        	
					        v.restaurar();
					        v.leer();
					        
					        auto start = std::chrono::high_resolution_clock::now();
					        
					        Quick.sortVectors(v.descripcion,v.Id,v.Id_proveedor,v.stock,v.stock_min,v.precio);
					        
					        auto stop = chrono::high_resolution_clock::now();
					 
					  
					        auto duration = chrono::duration_cast<std::chrono::nanoseconds>(stop - start);
					       // std::chrono::duration_cast<std::chrono::nanoseconds>(stop - start);
					        system("cls");
					        
					        v.impresion();
					        cout<<endl;
					        
					       
						    duration = duration_cast<milliseconds>(stop - start);
						
						
						    cout << "Tiempo de duracion: " << duration.count() << " milisegundos" << endl;
					        
					        cin.ignore();
					    	getchar();
        	
        	
			break;
		}
 
        
   
        
        
       case 3:
      	
		      	do{
		      	
		   		system("cls");
		      	cout << " ==================|  Orden por:  |==================\n";
		        cout << "1. Mezcla Directa     \n";
		        cout << "2. Mezcla Natural     \n";
		        cout << "3. Salir           \n";
		      
		        cout << " ======================================================\n";
		        cout << "Por favor, elige una opcion: ";
		        cin >> subopcion;
		        cout << " ======================================================\n";
				         int numRegis;
						 string regis;
						 string NomArch = "clien.dat";
						 auto start = high_resolution_clock::now();
						 auto stop = high_resolution_clock::now();
					  	 auto duration = duration_cast<milliseconds>(stop - start);
					  	 string nombreArchivo;
						 MezclaNatural mezcla1;
						 	ifstream NumArch("clien.dat");
						
						    
				           switch (subopcion) {
						   
						    case 1:
						    	
							// Leer la cantidad de registros 
						
							
							if (!NumArch) {
						        cerr << "Error al abrir el archivo clien.dat." << endl;
						        return 1;
						    }
						    
						   
						    
						    while (getline(NumArch, regis)) {
						        numRegis++;
						    }
						    
							NumArch.close();
							
						    // Ordenar los registros en el archivo binario
						    
						    start = high_resolution_clock::now();
						    mergeSort(NomArch, 0, numRegis - 1);
						    stop = high_resolution_clock::now();
						    duration = duration_cast<milliseconds>(stop - start);
						
						
						    cout << "Tiempo de duracion: " << duration.count() << " milisegundos" << endl;
                           cin.ignore();
					           getchar();
						    	
						    	
						    	
						   	break;
						   	
						   	case 2:{
						   		
						   	cout<<endl;
									
									    ifstream archivoOriginal("clien.dat");
										ofstream archivoCopia("copia.dat");
									
										string linea;
										while (getline(archivoOriginal, linea)) {
									  	istringstream iss(linea);
									 	 int id;
									 	 string nombres_apellidos, direccion, numeroTelefonico;
									 	 iss >> id >> nombres_apellidos >> direccion >> numeroTelefonico;
									
									 	 Registro registro(id, nombres_apellidos, direccion, numeroTelefonico);
									
											  // Guardar en la copia sin el campo ID
											  archivoCopia << registro.Nombres_Apellidos << " " << registro.Direccion << " " << registro.NumeroTelefonico << "\n";
											}
									
									archivoOriginal.close();
									archivoCopia.close();
									    
									    
									    nombreArchivo = "copia.dat";
									
									    //Despliega el contenido del archivo sin ordenar
									    mezcla1.desplegar(nombreArchivo);
									
									    //Ordena el contenido del archivo
									    mezcla1.ordenar(nombreArchivo);
									
									    //Verifica que el archivo este ordenado correctamente
									    mezcla1.verificarOrdenamiento(nombreArchivo);
									
									ifstream archivoOrdenado("copia.dat");
									ofstream archivoFinal("final.dat");
									
									while (getline(archivoOrdenado, linea)) {
									  istringstream iss(linea);
									  string nombres_apellidos, direccion, numeroTelefonico;
									  iss >> nombres_apellidos >> direccion >> numeroTelefonico;
									
									  Registro registroOrdenado(0, nombres_apellidos, direccion, numeroTelefonico);
									
									  // Buscar la ID en el archivo original
									  ifstream archivoOriginal("clien.dat");
									  string lineaOriginal;
									  while (getline(archivoOriginal, lineaOriginal)) {
									    istringstream issOriginal(lineaOriginal);
									    int id;
									    issOriginal >> id >> nombres_apellidos >> direccion >> numeroTelefonico;
									
									    Registro registroOriginal(id, nombres_apellidos, direccion, numeroTelefonico);
									
									    if (registroOrdenado.Nombres_Apellidos == registroOriginal.Nombres_Apellidos &&
									        registroOrdenado.Direccion == registroOriginal.Direccion &&
									        registroOrdenado.NumeroTelefonico == registroOriginal.NumeroTelefonico) {
									      // Encontramos la ID correspondiente
									      archivoFinal << registroOriginal.ID << " " << linea << "\n";
									      break;
									    }
									  }
									  archivoOriginal.close();
									}
									archivoOrdenado.close();
									archivoFinal.close();
									
									ifstream archivoFinals("final.dat");
									string linead;
									
									archivoFinal.close();
									
									
									
									// Cierra el archivoFinals antes de abrir archivoOriginal
									archivoFinals.close();
									std::ofstream archivoOriginals("clien.dat");
									
									// Ahora puedes abrir archivoFinals de nuevo
									std::ifstream archivoFinalz("final.dat");
									
									while (getline(archivoFinalz, linead)) {
									    archivoOriginals << linead << "\n";
									}
									
									archivoFinalz.close();
									archivoOriginals.close();
									
									// Elimina los archivos auxiliares
									remove("copia.dat");
									remove("final.dat");
									
									 	cout<<endl;
									
									// Imprime el contenido de clien.dat
									ifstream archivoClien("clien.dat");
									while (getline(archivoClien, linead)) {
									    cout << linead << "\n";
									}
									archivoClien.close();
									
	                          cin.ignore();
					           getchar();
						   		
							
							   }
							   
						   	break;	
					
						   		
						   	case 3:
						   	break;
						   			
						   	default:
						   		
					   	   	  throw OpcionNoValidaException();
					          cout << " ==================|Regresando...|==================\n";
					             cin.ignore();
					           getchar();
					          system("cls");
						   		
						   break;
				   }
				   
				   }while (subopcion !=3);
        break;
        
        
      case 4:{
      	
      	string nombreBuscado; char respuesta;
      	
      	do{
      		
						    system("cls");
							cout << "================|  Busqueda BINARIA  |================\n";
							cout<<endl;
							
							  vector<int> Id = {1, 2, 3, 4, 5};
							  vector<int> Id_proveedor = {10, 20, 30, 40, 50};
							  vector<int> stock = {50, 100, 150, 200, 250};
							  vector<float> precio = {10.50, 12.75, 15.20, 18.90, 21.00};
							  vector<string> descripcion = {"Producto A", "Producto B", "Producto C", "Producto D", "Producto E"};
							  vector<int> stock_min = {20, 30, 40, 50, 60};
						  	 
						  	 
							Id = v.Id;
							Id_proveedor = v.Id_proveedor;
							stock = v.stock;
							precio = v.precio;
							descripcion = v.descripcion;
							stock_min = v.stock_min;
							
							
							cout << "Escribe la descripcion del producto a buscar: ";
							cin>>nombreBuscado; 
							cout << " ======================================================\n";
							
						
						
						   
						
						
						
						
							  auto indiceInsercion = lower_bound(descripcion.begin(), descripcion.end(), nombreBuscado, compararNombres);
						
								if (indiceInsercion == descripcion.end() || *indiceInsercion != nombreBuscado) {
								cout << "El nombre " << nombreBuscado << " no se encuentra en el vector." << endl;
								} else {
								int index = distance(descripcion.begin(), indiceInsercion);
								int id = Id[index];
								int idProveedor = Id_proveedor[index];
								int cantidadStock = stock[index];
								float precioUnidad = precio[index];
								int stockMinimo = stock_min[index];
						
						
						
								cout << "Nombre: " << nombreBuscado << endl;
								cout << "ID: " << id << endl;
								cout << "ID Proveedor: " << idProveedor << endl;
								cout << "Stock: " << cantidadStock << endl;
								cout << "Precio: " << precioUnidad << endl;
								cout << "Stock Minimo: " << stockMinimo << endl;
								}
								
								    cin.ignore();
									getchar();
										cout << " ======================================================\n";
							  cout << "Desea repetir el proceso? (s/n): ";
							    cin >> respuesta;
							  } while (respuesta == 's' || respuesta == 'S');
      		
		  }
              
        
        
        
        
        break;
        
        
      case 5:
        cout << "Saliendo del programa...\n";
        break;
        
        
        
      default:
        	 throw OpcionNoValidaException();
             cout << " ==================|Regresando...|==================\n";
             getchar();
             system("cls");
		   		
        break; 
    }
    
    
    }catch (OpcionNoValidaException& e) {
			 cout << e.what() << endl;
   			 cout << " ==================| Saliendo... |==================\n";
   			 opcion = 5;
 			 }
    
  } while (opcion != 5);




return 0; 
}



