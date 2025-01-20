#include <iostream>
#include <fstream>
#include <iomanip>
#include <cstdlib>
#include <cstring>
#include <vector>
#include <conio.h>
#include <string>
#include <algorithm>

using namespace std;

//Clase para el manejo de Errores
class OpcionNoValidaException : public exception {
public:
    const char* what() const throw() {
        return "Opcion no valida.";
    }
};

//Clase de Tablas:
class Proveedor {
	public:
		vector<int> Id;
		vector<string> nombre;
		vector<string> telefono;
	public:
		// Agregar un nuevo proveedor
		void AgregarDato1(int id, char nom[45], char telf[30]) {
			Id.push_back(id);
			nombre.push_back(nom);
			telefono.push_back(telf);
			
			for (int i = 0; i < Id.size(); ++i) {
				replace(nombre[i].begin(), nombre[i].end(), ' ', '_');
			}
			// Escritura en archivo (modificar para binario si es necesario)
			ofstream arc("prove.dat", ios::binary);
			if (arc.is_open()) {
				for (int i = 0; i < Id.size(); ++i) {
					arc << Id[i] << ' ' << nombre[i] << ' ' << telefono[i] << '\n';
				}
				arc.close();
	    	}
		}
	
	  // Imprimir la información de todos los proveedores
		void imprimir() {
	    // Lectura del archivo (modificar para binario si es necesario)
		ifstream arc("prove.dat", ios::binary);
		if (arc.is_open()) {
			int id;
			string nom, telf;
			while (arc >> id >> nom >> telf) {
				replace(nom.begin(), nom.end(), '_', ' ');
	      	
				Id.push_back(id);
				nombre.push_back(nom);
				telefono.push_back(telf);
			}
			arc.close();
	    }
	
	    // Mostrar datos
	    cout << " ==================| LISTA DE PROVEEDORES |==================\n";
	    cout << " ID:      NOMBRE:                   TELEFONO:                \n";
	    cout << " ======== ========================= =========================\n";
	    for (int i = 0; i < Id.size(); ++i) {
	      cout << setiosflags(ios::left) << " " << setw(8) << Id[i] << " " << setw(25) << nombre[i] << " " << setw(25) << telefono[i] << endl;
	      cout << " ======== ========================= =========================\n";
	    }
	  }
	  
	  void leer(){
	  	
	  	ifstream arc("prove.dat", ios::binary);
		if (arc.is_open()) {
			int id;
			string nom, telf;
			while (arc >> id >> nom >> telf) {
				replace(nom.begin(), nom.end(), '_', ' ');
	      	
				Id.push_back(id);
				nombre.push_back(nom);
				telefono.push_back(telf);
			}
			arc.close();
	    }
	  	
	  }
		
		void Cambiar(int opc){
			string renpS;
			int Cid, vpos, renp;
			
			cout<<"|Escriba la ID del proveedor"<<endl;
			cout<<"\nR: ";
			cin>>Cid;
			
			ifstream arc("prove.dat", ios::binary);
		    if (arc.is_open()) {
		        int id;
		        string nom,telf;
		        while (arc >> id >> nom >> telf) {
		        	replace(nom.begin(), nom.end(), '_', ' ');
		        	
		            Id.push_back(id);
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
				cout<<"\n|Escriba el nuevo telefono:                   |Telefono actual: "<<telefono[vpos]<<endl;
				cout<<"\nR: ";
				cin.ignore();
				getline(cin,renpS);
				renpS.erase(remove(renpS.begin(), renpS.end(), ' '), renpS.end());
				telefono[vpos] = renpS;
			}
			
			for (int i = 0; i < Id.size(); ++i) {
				replace(nombre[i].begin(), nombre[i].end(), ' ', '_');
			}
			
		    ofstream ac("prove.dat" , ios::binary);
		    if (ac.is_open()) {
		        for (int i = 0; i < Id.size(); ++i) {
		            ac << Id[i] << ' ' << nombre[i] << ' ' << telefono[i] << '\n';
		        }
		        ac.close();
		    }
		}
		
		// Restaurar la clase a su estado inicial
		void restaurar() {
			Id.clear();
			nombre.clear();
			telefono.clear();
		}
	
		// **Métodos adicionales:**
	
		// Eliminar un espacio determinado de todos los vectores
		void eliminarEspacio() {
			int Cid, vpos;
				
			cout<<"|Escriba la ID del proveedor"<<endl;
			cout<<"\nR: ";
			cin>>Cid;
			
	    	ifstream arc("prove.dat", ios::binary);
			if (arc.is_open()) {
	    		int id;
	    		string nom,telf;
	    		while (arc >> id >> nom >> telf) {
	                Id.push_back(id);
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
	        	telefono.erase(telefono.begin() + vpos);
			}
	      
			ofstream eli("prove.dat" , ios::binary);
			if (eli.is_open()) {
				for (int i = 0; i < Id.size(); ++i) {
					eli << Id[i] << ' ' << nombre[i] << ' ' << telefono[i] << '\n';
				}
				arc.close();
			}
		}
	
		// Borrar el archivo y el contenido de todos los vectores
		void borrarArchivo() {
			ofstream arc("prove.dat", ios::binary);
			arc.close();
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
		// Agregar un nuevo producto
		void AgregarDato2(int id, int idp, int st, float prec, char descp[45], int stm) {
			Id.push_back(id);
			Id_proveedor.push_back(idp);
			stock.push_back(st);
			precio.push_back(prec);
			descripcion.push_back(descp);
			stock_min.push_back(stm);
			
			for (int i = 0; i < Id.size(); ++i) {
				replace(descripcion[i].begin(), descripcion[i].end(), ' ', '_');
			}
			// Escritura en archivo (modificar para binario si es necesario)
			ofstream arc("produc.dat", ios::binary);
			if (arc.is_open()) {
				for (int i = 0; i < Id.size(); ++i) {
					arc << Id[i] << ' ' << Id_proveedor[i] << ' ' << stock[i] << ' ' << precio[i] << ' ' << descripcion[i] << ' ' << stock_min[i] << '\n';
				}
				arc.close();
			}
		}
		
		
		void escribir(){
			
				for (int i = 0; i < Id.size(); ++i) {
				replace(descripcion[i].begin(), descripcion[i].end(), ' ', '_');
			}
			
				// Escritura en archivo (modificar para binario si es necesario)
			ofstream arc("produc.dat", ios::binary);
			if (arc.is_open()) {
				for (int i = 0; i < Id.size(); ++i) {
					arc << Id[i] << ' ' << Id_proveedor[i] << ' ' << stock[i] << ' ' << precio[i] << ' ' << descripcion[i] << ' ' << stock_min[i] << '\n';
				}
				arc.close();
			}
			
		}
		
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
		
		void actualizar(int opc){
			string nArc;
			int Cid, vpos, rep;
			float camb;
			cout<<"|Escriba el nombre del archivo"<<endl;
			cout<<"\nR: ";
			cin.ignore();
			getline(cin,nArc);
			nArc += ".txt";
			
			cout<<"\n|Escriba la ID del producto"<<endl;
			cout<<"\nR: ";
			cin>>Cid;
			
			ifstream arc("produc.dat", ios::binary);
            if (arc.is_open()) {
                int id,idp,st,stm;
                float prec;
                string descp;
                while (arc >> id >> idp >> st >> prec >> descp >> stm) {
                    Id.push_back(id);
		    		Id_proveedor.push_back(idp);
		    		stock.push_back(st);
		    		precio.push_back(prec);
		    		descripcion.push_back(descp);
		    		stock_min.push_back(stm);
                }
                arc.close();
            }
            ofstream borr("produc.dat" , ios::binary);
            arc.close();
            
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
			
			if(opc == 3){
				cout<<"\n|Cuanto stock desea reponer?"<<endl;
				cout<<"\nR: ";
				cin>>rep;
				stock[vpos] += rep;
			}else if(opc == 4){
				cout<<"\n|Escriba el nuevo precio:                     |Precio actual: "<<precio[vpos]<<endl;
				cout<<"\nR: ";
				cin>>camb;
				precio[vpos] = camb;
			}
			
			ofstream act(nArc.c_str());
			for (int i = 0; i < Id.size(); ++i) {
				act << Id[i] << ' ' << Id_proveedor[i] << ' ' << stock[i] << ' ' << precio[i] << ' ' << descripcion[i] << ' ' << stock_min[i] << '\n';
			}
			act.close();
			
			restaurar();
			
			ifstream actArc(nArc.c_str());
			int id,idp,st,stm;
            float prec;
            string descp;
			while (actArc >> id >> idp >> st >> prec >> descp >> stm) {
                Id.push_back(id);
	    		Id_proveedor.push_back(idp);
	    		stock.push_back(st);
	    		precio.push_back(prec);
	    		descripcion.push_back(descp);
	    		stock_min.push_back(stm);
            }
            actArc.close();
            
            ofstream arcAct("produc.dat" , ios::binary);
            if (arcAct.is_open()) {
                for (int i = 0; i < Id.size(); ++i) {
                    arcAct << Id[i] << ' ' << Id_proveedor[i] << ' ' << stock[i] << ' ' << precio[i] << ' ' << descripcion[i] << ' ' << stock_min[i] << '\n';
                }
                arcAct.close();
            }
		}
		
		void Cambiar(int opc){
			string renpS;
			int Cid, vpos, renp;
			float renpF;
			
			cout<<"|Escriba la ID del producto"<<endl;
			cout<<"\nR: ";
			cin>>Cid;
			
			ifstream arc("produc.dat", ios::binary);
		    if (arc.is_open()) {
		        int id,idp,st,stm;
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
				cout<<"\n|Escriba el nuevo Id:                                  |Id actual: "<<Id[vpos]<<endl;
				cout<<"\nR: ";
				cin>>renp;
				Id[vpos] = renp;
			}else if(opc == 2){
				cout<<"\n|Escriba el nuevo Id de proveedor:                     |Id de proveedor actual: "<<Id_proveedor[vpos]<<endl;
				cout<<"\nR: ";
				cin>>renp;
				Id_proveedor[vpos] = renp;
			}else if(opc == 3){
				cout<<"\n|Escriba el nuevo stock:                            	|Stock actual: "<<stock[vpos]<<endl;
				cout<<"\nR: ";
				cin>>renp;
				stock[vpos] = renp;
			}else if(opc == 4){
				cout<<"\n|Escriba el nuevo precio:                            	|Precio actual: "<<precio[vpos]<<endl;
				cout<<"\nR: ";
				cin>>renpF;
				precio[vpos] = renp;
			}else if(opc == 5){
				cout<<"\n|Escriba el nuevo descripcion:                         |Descripcion actual: "<<descripcion[vpos]<<endl;
				cout<<"\nR: ";
				cin.ignore();
				getline(cin,renpS);
				descripcion[vpos] = renpS;
			}else if(opc == 6){
				cout<<"\n|Escriba el nuevo stock minimo:                        |Stock minimo actual: "<<stock_min[vpos]<<endl;
				cout<<"\nR: ";
				cin>>renp;
				stock_min[vpos] = renp;
			}
			
			for (int i = 0; i < Id.size(); ++i) {
				replace(descripcion[i].begin(), descripcion[i].end(), ' ', '_');
			}
			
		    ofstream ac("produc.dat" , ios::binary);
		    if (ac.is_open()) {
		        for (int i = 0; i < Id.size(); ++i) {
                    ac << Id[i] << ' ' << Id_proveedor[i] << ' ' << stock[i] << ' ' << precio[i] << ' ' << descripcion[i] << ' ' << stock_min[i] << '\n';
                }
		        ac.close();
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
		
		// **Métodos adicionales:**
		
		// Eliminar un espacio determinado de todos los vectores
		void eliminarEspacio() {
			int Cid, vpos;
			
			cout<<"|Escriba la ID del producto"<<endl;
			cout<<"\nR: ";
			cin>>Cid;
			
	      	ifstream arc("produc.dat", ios::binary);
            if (arc.is_open()) {
            	int id,idp,st,stm;
        		float prec;
        		string descp;
				while (arc >> id >> idp >> st >> prec >> descp >> stm) {
	            	Id.push_back(id);
	    			Id_proveedor.push_back(idp);
	    			stock.push_back(st);
	    			precio.push_back(prec);
	    			descripcion.push_back(descp);
	    			stock_min.push_back(stm);
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
				Id_proveedor.erase(Id_proveedor.begin() + vpos);
				stock.erase(stock.begin() + vpos);
				precio.erase(precio.begin() + vpos);
				descripcion.erase(descripcion.begin() + vpos);
				stock_min.erase(stock_min.begin() + vpos);
			}
			
			ofstream eli("produc.dat" , ios::binary);
            if (eli.is_open()) {
                for (int i = 0; i < Id.size(); ++i) {
                    eli << Id[i] << ' ' << Id_proveedor[i] << ' ' << stock[i] << ' ' << precio[i] << ' ' << descripcion[i] << ' ' << stock_min[i] << '\n';
                }
                arc.close();
            }
		}
		
		// Borrar el archivo y el contenido de todos los vectores
		void borrarArchivo() {
			ofstream arc("produc.dat", ios::binary);
			arc.close();
		}
		
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
			}else {
				cout << "Índice fuera de rango." << endl;
			}
		}
		
		
		void cambiarValor(vector<float>& vector, int indice, float valor) {
			if (indice >= 0 && indice < vector.size()) {
				vector[indice] = valor;
			} else {
				cout << "Índice fuera de rango." << endl;
			}
		}
};

//Clase de Tablas:
class Compra {
	public:
	  vector<int> Id;
	  vector<int> Id_producto;
	  vector<int> Id_Factura;
	  vector<int> Cantidad;
	public:
		// Agregar una nueva compra
		void AgregarDato3(int id, int idp, int fact, int Can) {
			Id.push_back(id);
			Id_producto.push_back(idp);
			Id_Factura.push_back(fact);
			Cantidad.push_back(Can);
			
			// Escritura en archivo (modificar para binario si es necesario)
			ofstream arc("comp.dat", ios::binary);
			if (arc.is_open()) {
				for (int i = 0; i < Id.size(); ++i) {
					
					arc << Id[i] << ' ' << Id_producto[i] << ' ' << Id_Factura[i] << ' ' << Cantidad[i] << '\n';
				}
				arc.close();
			}
		}
	
		// Imprimir la información de todas las compras
		void imprimir() {
			// Lectura del archivo (modificar para binario si es necesario)
			ifstream arc("comp.dat", ios::binary);
			if (arc.is_open()) {
				int id, idp, fact, Can;
				while (arc >> id >> idp >> fact >> Can) {
					Id.push_back(id);
					Id_producto.push_back(idp);
					Id_Factura.push_back(fact);
					Cantidad.push_back(Can);
				}
				arc.close();
			}
			
	    	// Mostrar datos
	    	cout << " ================| LISTA DE COMPRAS |================\n";
	    	cout << " ID:      ID DE PRODUCTO:  ID DE FACTURA:  CANTIDAD: \n";
	    	cout << " ======== ================ =============== ==========\n";
	    	for (int i = 0; i < Id.size(); ++i) {
				cout << setiosflags(ios::left) << " " << setw(8) << Id[i] << " " << setw(16) << Id_producto[i] << " " 
				<< setw(15) << Id_Factura[i] << " " << setw(10) << Cantidad[i] << endl;
				cout << " ======== ================ =============== ==========\n";
			}
		}
		
		void leer(){
			
				ifstream arc("comp.dat", ios::binary);
			if (arc.is_open()) {
				int id, idp, fact, Can;
				while (arc >> id >> idp >> fact >> Can) {
					Id.push_back(id);
					Id_producto.push_back(idp);
					Id_Factura.push_back(fact);
					Cantidad.push_back(Can);
				}
				arc.close();
			}
			
		}
		
		void Cambiar(int opc){
			int Cid, vpos, renp;
			
			cout<<"|Escriba la ID de la compra"<<endl;
			cout<<"\nR: ";
			cin>>Cid;
			
			ifstream arc("comp.dat", ios::binary);
		    if (arc.is_open()) {
		        int id, idp, fact, Can;
		        while (arc >> id >> idp >> fact >> Can) {
			        Id.push_back(id);
			        Id_producto.push_back(idp);
			        Id_Factura.push_back(fact);
			        Cantidad.push_back(Can);
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
				cout<<"\n|Escriba el nuevo Id:                                |Id actual: "<<Id[vpos]<<endl;
				cout<<"\nR: ";
				cin>>renp;
				Id[vpos] = renp;
			}else if(opc == 2){
				cout<<"\n|Escriba el nuevo Id de producto:                    |Id de producto actual: "<<Id_producto[vpos]<<endl;
				cout<<"\nR: ";
				cin>>renp;
				Id_producto[vpos] = renp;
			}else if(opc == 3){
				cout<<"\n|Escriba el nuevo Id de factura:                     |Id de factura actual: "<<Id_Factura[vpos]<<endl;
				cout<<"\nR: ";
				cin>>renp;
				Id_Factura[vpos] = renp;
			}else if(opc == 4){
				cout<<"\n|Escriba la nueva cantidad:                          |Cantidad actual: "<<Cantidad[vpos]<<endl;
				cout<<"\nR: ";
				cin>>renp;
				Cantidad[vpos] = renp;
			}
			
		    ofstream ac("comp.dat" , ios::binary);
		    if (ac.is_open()) {
		        for (int i = 0; i < Id.size(); ++i) {
		            ac << Id[i] << ' ' << Id_producto[i] << ' ' << Id_Factura[i] << ' ' << Cantidad[i] << '\n';
		        }
		        ac.close();
		    }
		}
		
		void escribir(){
			
				ofstream arc("comp.dat", ios::binary);
			if (arc.is_open()) {
				for (int i = 0; i < Id.size(); ++i) {
					
					arc << Id[i] << ' ' << Id_producto[i] << ' ' << Id_Factura[i] << ' ' << Cantidad[i] << '\n';
				}
				arc.close();
			}
			
		}
		
		void eliminacion(int i){
			
		
		    Id.erase(Id.begin() + i-1);
			Id_producto.erase(	Id_producto.begin() + i-1);
			Id_Factura.erase(Id_Factura.begin() + i-1);
			Cantidad.erase(	Cantidad.begin() + i-1);
			
			
		}
		
		// Restaurar la clase a su estado inicial
		void restaurar() {
			Id.clear();
			Id_producto.clear();
			Id_Factura.clear();
			Cantidad.clear();
		}
	
		// **Métodos adicionales:**
	
		// Eliminar un espacio determinado de todos los vectores
		void eliminarEspacio() {
			int Cid, vpos;
				
			cout<<"|Escriba la ID del proveedor"<<endl;
			cout<<"\nR: ";
			cin>>Cid;
			
			ifstream arc("comp.dat", ios::binary);
			if (arc.is_open()) {
				int id, idp, fact, Can;
				while (arc >> id >> idp >> fact >> Can) {
					Id.push_back(id);
					Id_producto.push_back(idp);
					Id_Factura.push_back(fact);
					Cantidad.push_back(Can);
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
				Id_producto.erase(Id_producto.begin() + vpos);
				Id_Factura.erase(Id_Factura.begin() + vpos);
				Cantidad.erase(Cantidad.begin() + vpos);
			}
	    
			ofstream eli("comp.dat" , ios::binary);
			if (eli.is_open()) {
				for (int i = 0; i < Id.size(); ++i) {
					eli << Id[i] << ' ' << Id_producto[i] << ' ' << Id_Factura[i] << ' ' << Cantidad[i] << '\n';
				}
				arc.close();
			}
		}
	
		// Borrar el archivo y el contenido de todos los vectores
		void borrarArchivo() {
			ofstream arc("comp.dat", ios::binary);
			arc.close();
		}
		
		void cambiarValor(vector<int>& vector, int indice, int valor) {
			if (indice >= 0 && indice < vector.size()) {
				vector[indice] = valor;
			} else {
				cout << "Índice fuera de rango." << endl;
			}
		}
};

//Clase de Tablas:
class Factura {
	public:
	  vector<int> Id;
	  vector<int> Id_cliente;
	  vector<string> fecha;
	public:
		// Agregar una nueva factura
		void AgregarDato4(int id, int idc, char fech[12]) {
			Id.push_back(id);
			Id_cliente.push_back(idc);
			fecha.push_back(fech);
			
			// Escritura en archivo (modificar para binario si es necesario)
			ofstream arc("factu.dat", ios::binary);
			if (arc.is_open()) {
				for (int i = 0; i < Id.size(); ++i) {
					arc << Id[i] << ' ' << Id_cliente[i] << ' ' << fecha[i] << '\n';
				}
				arc.close();
			}
		}
	
		// Imprimir la información de todas las facturas
		void imprimir() {
			// Lectura del archivo (modificar para binario si es necesario)
			ifstream arc("factu.dat", ios::binary);
			if (arc.is_open()) {
				int id, idc;
				string fech;
				while (arc >> id >> idc >> fech) {
					Id.push_back(id);
					Id_cliente.push_back(idc);
					fecha.push_back(fech);
				}
				arc.close();
			}
			
			// Mostrar datos
			cout << " ========| LISTA DE FACTURAS |========\n";
	    	cout << " ID:      ID DE CLIENTE:  FECHA:      \n";
	    	cout << " ======== =============== ============\n";
			for (int i = 0; i < Id.size(); ++i) {
				cout << setiosflags(ios::left) << " " << setw(8) << Id[i] << " " << setw(15) << Id_cliente[i] << " " << setw(12) << fecha[i] << endl;
				cout << " ======== =============== ============\n";
			}
		}
		
		void leer(){
			
				ifstream arc("factu.dat", ios::binary);
			if (arc.is_open()) {
				int id, idc;
				string fech;
				while (arc >> id >> idc >> fech) {
					Id.push_back(id);
					Id_cliente.push_back(idc);
					fecha.push_back(fech);
				}
				arc.close();
			}
			
			
		}
	
		void Cambiar(int opc){
			string renpS;
			int Cid, vpos, renp;
			
			cout<<"|Escriba la ID de la factura"<<endl;
			cout<<"\nR: ";
			cin>>Cid;
			
			ifstream arc("factu.dat", ios::binary);
		    if (arc.is_open()) {
		        int id, idc;
	      		string fech;
		        while (arc >> id >> idc >> fech) {
	        		Id.push_back(id);
	        		Id_cliente.push_back(idc);
	        		fecha.push_back(fech);
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
				cout<<"\n|Escriba el nuevo Id:                                |Id actual: "<<Id[vpos]<<endl;
				cout<<"\nR: ";
				cin>>renp;
				Id[vpos] = renp;
			}else if(opc == 2){
				cout<<"\n|Escriba el nuevo Id de cliente:                     |Id de cliente actual: "<<Id_cliente[vpos]<<endl;
				cout<<"\nR: ";
				cin>>renp;
				Id_cliente[vpos] = renp;
			}else if(opc == 3){
				cout<<"\n|Escriba la nueva fecha:                             |fecha actual: "<<fecha[vpos]<<endl;
				cout<<"\nR: ";
				cin.ignore();
				getline(cin,renpS);
				renpS.erase(remove(renpS.begin(), renpS.end(), ' '), renpS.end());
				fecha[vpos] = renpS;
			}
			
		    ofstream ac("factu.dat" , ios::binary);
		    if (ac.is_open()) {
		        for (int i = 0; i < Id.size(); ++i) {
		            ac << Id[i] << ' ' << Id_cliente[i] << ' ' << fecha[i] << '\n';
		        }
		        ac.close();
		    }
		}
	
		// Restaurar la clase a su estado inicial
		void restaurar() {
			Id.clear();
			Id_cliente.clear();
			fecha.clear();
		}
	
		// **Métodos adicionales:**
	
		// Eliminar un espacio determinado de todos los vectores
		void eliminarEspacio() {
			int Cid, vpos;
				
			cout<<"|Escriba la ID del proveedor"<<endl;
			cout<<"\nR: ";
			cin>>Cid;
			
			ifstream arc("factu.dat", ios::binary);
			if (arc.is_open()) {
				int id, idc;
				string fech;
				while (arc >> id >> idc >> fech) {
					Id.push_back(id);
	        		Id_cliente.push_back(idc);
	        		fecha.push_back(fech);
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
				Id_cliente.erase(Id_cliente.begin() + vpos);
				fecha.erase(fecha.begin() + vpos);
			}
	    
			ofstream eli("factu.dat" , ios::binary);
			if (eli.is_open()) {
				for (int i = 0; i < Id.size(); ++i) {
						eli << Id[i] << ' ' << Id_cliente[i] << ' ' << fecha[i] << '\n';
				}
				arc.close();
			}
		}
	
		// Borrar el archivo y el contenido de todos los vectores
		void borrarArchivo() {
			ofstream arc("factu.dat", ios::binary);
			arc.close();
		}
	
		// Remplazar el espacio dado por un valor de un vector string
		void reemplazarValor(int indice, string valor) {
			if (indice >= 0 && indice < fecha.size()) {
				fecha[indice] = valor;
			}
		}
	
		// Remplazar el espacio dado por un valor de un vector float (no aplicable en este caso)
	
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
			cout << " =================================| LISTA DE CLIENTES |=================================\n";
	    	cout << " ID:      NOMBRE:                   DIRECCION:                 TELEFONO:                \n";
	    	cout << " ======== ========================= ========================== =========================\n";
			for (int i = 0; i < Id.size(); ++i) {
				cout << setiosflags(ios::left) << " " << setw(8) << Id[i] << " " << setw(25) << nombre[i] << " " 
				<< setw(26) << direccion[i] << " " << setw(25) << telefono[i] << endl;
				cout << " ======== ========================= ========================== =========================\n";
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

//Clase para el manejo de Usuarios
class Datos {
    public:
        vector<string> usuario1;
        vector<string> contrasena1;
        vector<string> usuario2;
        vector<string> contrasena2;
        vector<string> usuario3;
        vector<string> contrasena3;

    public:
        void leerArchivo(string nombreArchivo, vector<string>& usuario, vector<string>& contrasena) {
            ifstream archivo(nombreArchivo.c_str(), ios::binary);
            if (archivo.is_open()) {
                string u, c;
                while (archivo >> u >> c) {
                    usuario.push_back(u);
                    contrasena.push_back(c);
                }
                archivo.close();
            }
        }

        void escribirArchivo(string nombreArchivo, vector<string>& usuario, vector<string>& contrasena) {
            ofstream archivo(nombreArchivo.c_str(), ios::binary);
            if (archivo.is_open()) {
                for (int i = 0; i < usuario.size(); ++i) {
                    archivo << usuario[i] << ' ' << contrasena[i] << '\n';
                }
                archivo.close();
            }
        }

        void agregarDatos(string u, string c, vector<string>& usuario, vector<string>& contrasena) {
            usuario.push_back(u);
            contrasena.push_back(c);
        }

//Elimina un espacio determinado de todos los vectores
        void eliminarEspacio(int indice, vector<string>& usuario, vector<string>& contrasena) {
            if (indice >= 0 && indice < usuario.size()) {
                usuario.erase(usuario.begin() + indice);
                contrasena.erase(contrasena.begin() + indice);
            }
        }
        
//Borra el archivo y el contenido de todos los vectores
        void borrarArchivo(string nombreArchivo, vector<string>& usuario, vector<string>& contrasena) {
            ofstream archivo(nombreArchivo.c_str(), ios::binary);
            archivo.close();
            usuario.clear();
            contrasena.clear();
        }
//Borra el contenido de los vectores  
        void borrarArchivo(vector<string>& usuario, vector<string>& contrasena) {
        
            usuario.clear();
            contrasena.clear();
        }
        
        void mostrarDatos(vector<string>& usuario, vector<string>& contrasena) {
            for (int i = 0; i < usuario.size(); ++i) {
                cout << "Usuario: " << usuario[i] << ", Contrasena: " << contrasena[i] << '\n';
            }
        }
        
//Remplaza el espacio dado por un valor de un vector string        
        void reemplazarValor(int indice, string valor, vector<string>& vector) {
  if (indice >= 0 && indice < vector.size()) {
    vector[indice] = valor;
  }
}

//Remplaza el espacio dado por un valor de un vector float
        void reemplazarValorFloat(int indice, float valor, vector<float>& vector) {
  if (indice >= 0 && indice < vector.size()) {
    vector[indice] = valor;
  }
}

//Remplaza el espacio dado por un valor de un vector int
        void reemplazarValorInt(int indice, int valor, vector<int>& vector) {
  if (indice >= 0 && indice < vector.size()) {
    vector[indice] = valor;
  }
}


};

int main() {
	
		//Variables y objetos a declarar	
		int opcion = 0, tipoUsuario = 0, key=0, o, id,idp,st,stm,fact,Can,idc,drid;
		char fech[12], dir[70], telf[30], nom[45], descp[45], ch;
		string usuario, contrasena, vacio, contrasenaAdmin;
		string n,t,d,f,dr;
		Producto v; Compra Com; Factura Fact; Cliente Cli;
		float prec;
		bool esAdmin = false,volv;;
		fstream arc;
		Datos datos;
		int koi = 0; // Solo en el Bloque 1
		Proveedor pv;  
    
//Comienzo del Menu              
    do {
    	
    	try {
    	key=0;
    	
    	cout << "               Juan Paredes - Jose Bravo             \n";
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
    	
    	cout << " ==================| INICIAR SESION |==================\n";
        cout << "1. Usuario existente\n";
        cout << "2. Registrar nuevo  \n";
        cout << "3. Salir            \n";
        cout << " ======================================================\n";
        cout << "Por favor, elige una opcion: ";
        cin >> opcion;
        cout << " ======================================================\n";

        switch (opcion) {
        	
        	
//INICIO DE SESION - MENUS INTERNOS
	
            	case 1:
            	  system("cls");
            	  
            	  
            	    cout << " ==================|  INICIAR SESION  |==================\n";
            	    cout << "\nIngrese el nombre de usuario: ";
                    cin >> usuario;
                    cout << "\nIngrese la contrasena: ";
                    cin >> contrasena;
            	  
                   // Leer los archivos
             	   datos.leerArchivo("datos1.dat", datos.usuario1, datos.contrasena1);
             	   datos.leerArchivo("datos2.dat", datos.usuario2, datos.contrasena2);
             	   datos.leerArchivo("datos3.dat", datos.usuario3, datos.contrasena3);
 
//------------------MENU ADMINISTRADOR 


  		for (int i = 0; i < datos.usuario1.size(); ++i) {
              	    	
                  	 	 if (datos.usuario1[i] == usuario && datos.contrasena1[i] == contrasena) {
                     
				       	 cout << "\nUsuario y contrasena correctos.\n";
				       	 cout << " ======================================================\n";
					   	 ch = getch();
                       
         
            									
          											  do{
  															system("CLS");
 															 cout << " ==================| ADMINISTRADOR |==================\n";
 															 cout<<" 1| Proveedor"<<endl;
 															 cout<<" 2| Producto"<<endl;
  														//	 cout<<" 3| Compras"<<endl;
  														//	 cout<<" 4| Facturas"<<endl;
  														   	 cout<<" 3| Clientes"<<endl;
 															 cout<<" 4| Salir"<<endl;
 															 cout << " ======================================================\n";
 															 cout<<"\nR: ";
 															 cin>>o;
 															 cout << " ======================================================\n";
  															
 																 switch (o){
  																	 case 1:
    																	system("CLS");
    																
    																	cout << " ==================|   PROVEEDOR   |==================\n";
    																	cout<<" 1| Enlistar proveedor"<<endl;
																	    cout<<" 2| Modificar proveedor"<<endl;
																	    cout<<" 3| Agregar proveedor"<<endl;
																	    cout<<" 4| Eliminar proveedor"<<endl;
																	    cout<<"\nR: ";
																	    cin>>o;
																	    cout << " ======================================================\n";
    																	
   																	 switch (o){
    	
																			     case 1: 
																					      system("CLS");
																					      pv.restaurar(); 
																					      pv.imprimir();
																					      cin.ignore();
																					      getchar();
																			     break;
																			     
																			     case 2:
																			    	do{
																			    		system("CLS");
																						cout << " ==================|   PROVEEDOR   |==================\n";
				    																	cout<<" 1| Modificar Id de proveedor"<<endl;
																					    cout<<" 2| Modificar nombre de proveedor"<<endl;
																					    cout<<" 3| Modificar telefono de proveedor"<<endl;
																					    cout<<" 4| Terminar modificacion"<<endl;
																					    cout<<"\nR: ";
																					    cin>>o;
																					    cout << " ======================================================\n";
																						switch (o){
																							case 1:
																								system("CLS");
																								pv.restaurar();
																								pv.Cambiar(o);
																							break;
																							case 2:
																								system("CLS");
																								pv.restaurar();
																								pv.Cambiar(o);
																							break;
																							case 3:
																								system("CLS");
																								pv.restaurar();
																								pv.Cambiar(o);
																							break;
																							case 4:
																							break;
																							default:
																								
																								 
																			            
																				             ch = getch();
																				             system("cls");
																								
																								
																							break;
																			            	}
																						}while(o != 4);
																			     break;
																			     
																			     case 3: 
																							pv.restaurar();
																							pv.leer();
																							do{
																								system("CLS");
																								cout<<"\n Id del proveedor: ";
																								cin>>id;
																								volv = false;
																								for (int i = 0; i < pv.Id.size(); ++i){
																									if(pv.Id[i] == id){
																							        	cout<<"\n Id ya existente ingrese otra Id"<<endl;
																							        	volv = true;
																							        	cin.ignore();
																										getchar();
																									}
																								}
																							}while( volv == true);
																							cin.ignore();
																							cout<<"\n Nombre del proveedor: ";
																							getline(cin,n);
																							cout<<"\n Telefono del proveedor: ";
																							getline(cin,t);
																							t.erase(remove(t.begin(), t.end(), ' '), t.end());
																							pv.AgregarDato1(id,strcpy(nom,n.c_str()),strcpy(telf,t.c_str()));
																				 break;
																				 case 4:
																				 	pv.restaurar();
																				 	pv.eliminarEspacio();
																				 	o = 0;
																				 break;
																			     default:
																			    
										                					    	throw OpcionNoValidaException();
																		           ch = getch();
																		           system("cls");
																			     break;
																			    }
																   break;
																   
																   
																   case 2:
																    system("CLS");
														
																    cout << " ==================|   PRODUCTOS   |==================\n";
																    cout<<" 1| Enlistar producto"<<endl;
																    cout<<" 2| Modificar producto"<<endl;
																    cout<<" 3| Agregar producto"<<endl;
																    cout<<" 4| Eliminar producto"<<endl;
																    cout<<"\nR: ";
																    cin>>o;
																    cout << " ======================================================\n";
																    
    																	switch (o){
    																		
																			     case 1: 
																				      system("CLS");
																				      v.restaurar();
																				      v.imprimir();
																				      cin.ignore();
																				      getchar();
																			     break;
																			    
																			     case 2:
																			     	do{
																			    		system("CLS");
																						cout << " ==================|   PRODUCTOS   |==================\n";
				    																	cout<<" 1| Modificar Id de producto"<<endl;
				    																	cout<<" 2| Modificar Id de proveedor"<<endl;
																					    cout<<" 3| Modificar stock"<<endl;
																					    cout<<" 4| Modificar precio"<<endl;
																					    cout<<" 5| Modificar descripcion"<<endl;
																					    cout<<" 6| Modificar stock minimo"<<endl;
																					    cout<<" 7| Terminar modificacion"<<endl;
																					    cout<<"\nR: ";
																					    cin>>o;
																					    cout << " ======================================================\n";
																						switch (o){
																							case 1:
																								system("CLS");
																								v.restaurar();
																								v.Cambiar(o);
																							break;
																							case 2:
																								system("CLS");
																								v.restaurar();
																								v.Cambiar(o);
																							break;
																							case 3:
																								system("CLS");
																								v.restaurar();
																								v.Cambiar(o);
																							break;
																							case 4:
																								system("CLS");
																								v.restaurar();
																								v.Cambiar(o);
																							break;
																							case 5:
																								system("CLS");
																								v.restaurar();
																								v.Cambiar(o);
																							break;
																							case 6:
																								system("CLS");
																								v.restaurar();
																								v.Cambiar(o);
																							break;
																							case 7:
																							break;
																							default:
																							break;
																			            	}
																						}while(o != 7);
																			     break;
																			     
																			     case 3: 
																				    	v.restaurar();
																						v.leer();
																						do{
																							system("CLS");
																				    		cout<<"\n Id del producto: ";
																							cin>>id;
																							volv = false;
																							for (int i = 0; i < v.Id.size(); ++i){
																								if(v.Id[i] == id){
																						        	cout<<"\n Id ya existente ingrese otra Id"<<endl;
																						        	volv = true;
																						        	cin.ignore();
																									getchar();
																								}
																							}
																						}while( volv == true);
																				      cout<<"\n Id del proveedor: ";
																				      cin>>idp;
																				      cout<<"\n Numero del stock: ";
																				      cin>>st;
																				      cout<<"\n Precio del producto: ";
																				      cin>>prec;
																				      cin.ignore();
																				      cout<<"\n Descripcion del producto: ";
																				      getline(cin,d);
																				      cout<<"\n Numero del stock minimo: ";
																				      cin>>stm;
																				      v.AgregarDato2(id, idp, st, prec, strcpy(descp,d.c_str()), stm);
																			     break;
																			     
																			     case 4:
																			     	v.restaurar();
																				 	v.eliminarEspacio();
																				 	o = 0;
																			     break;
																			     default:
																			     	
										                					    	throw OpcionNoValidaException();
																		           ch = getch();
																		           system("cls");
																			     break;
																			    }
  																    break;	
																	  	
//Código para la modificacion de los archivos de Factura y compra 																		  
																		  	
																   /* case 3:
																    
																		
																    system("CLS");
															
																    cout << " ==================|    COMPRAS    |==================\n";
																    cout<<" 1| Enlistar compra"<<endl;
																    cout<<" 2| Modificar compra"<<endl;
																    cout<<" 3| Agregar compra"<<endl;
																    cout<<" 4| Eliminar compra"<<endl;
																    cout<<"\nR: ";
																    cin>>o;
																    cout << " ======================================================\n";
																		    switch (o){
																		    	
																		     case 1:
																			      system("CLS");
																			      Com.restaurar();
																			      Com.imprimir();
																			      cin.ignore();
																			      getchar();
																		     break;
																		     
																		     case 2:
																		     	do{
																			    		system("CLS");
																						cout << " ==================|   COMPRAS   |==================\n";
				    																	cout<<" 1| Modificar Id de compra"<<endl;
																					    cout<<" 2| Modificar Id de producto"<<endl;
																					    cout<<" 3| Modificar Id de factura"<<endl;
																					    cout<<" 4| Modificar cantidad"<<endl;
																					    cout<<" 5| Terminar modificacion"<<endl;
																					    cout<<"\nR: ";
																					    cin>>o;
																					    cout << " ======================================================\n";
																						switch (o){
																							case 1:
																								system("CLS");
																								Com.restaurar();
																								Com.Cambiar(o);
																							break;
																							case 2:
																								system("CLS");
																								Com.restaurar();
																								Com.Cambiar(o);
																							break;
																							case 3:
																								system("CLS");
																								Com.restaurar();
																								Com.Cambiar(o);
																							break;
																							case 4:
																								system("CLS");
																								Com.restaurar();
																								Com.Cambiar(o);
																							break;
																							case 5:
																							break;
																							default:
																							break;
																			            	}
																						}while(o != 5);
																		    break;
																		     
																		     case 3: 
																					Com.restaurar();
																					Com.leer();
																					do{
																						system("CLS");
																			      		cout<<"\n Id de la compra: ";
																						cin>>id;
																						volv = false;
																						for (int i = 0; i < Com.Id.size(); ++i){
																							if(Com.Id[i] == id){
																					        	cout<<"\n Id ya existente ingrese otra Id"<<endl;
																					        	volv = true;
																					        	cin.ignore();
																								getchar();
																							}
																						}
																					}while( volv == true);
																			      cout<<"\n Id del producto: ";
																			      cin>>idp;
																			      cout<<"\n Id de la factura: ";
																			      cin>>fact;
																			      cout<<"\n Cantidad de la compra: ";
																			      cin>>Can;
																			      Com.AgregarDato3(id,idp,fact,Can);
																		     break;
																		     
																		     case 4:
																		     	Com.restaurar();
																				Com.eliminarEspacio();
																			 break;
																		     
																		     default:
																	
                					    										throw OpcionNoValidaException();
																	            ch = getch();
																	            system("cls");
																		     break;
																		    }
  																	 break;
  																	 
																	   case 4:
																		    system("CLS");
														
																		    cout << " ==================|    FACTURA    |==================\n";
																		    cout<<" 1| Enlistar factura"<<endl;
																		    cout<<" 2| Modificar factura"<<endl;
																		    cout<<" 3| Agregar factura"<<endl;
																		    cout<<" 4| Eliminar factura"<<endl;
																		    cout<<"\nR: ";
																		    cin>>o;
																		    cout << " ======================================================\n";
																		    	switch (o){
																		    		
																				     case 1: 
																					      system("CLS");
																					      Fact.restaurar();
																					      Fact.imprimir();
																					      cin.ignore();
																					      getchar();
																				     break;
																				     
																				    case 2:
																				    	do{
																			    		system("CLS");
																						cout << " ==================|   FACTURA   |==================\n";
				    																	cout<<" 1| Modificar Id de factura"<<endl;
																					    cout<<" 2| Modificar Id de cliente"<<endl;
																					    cout<<" 3| Modificar fecha"<<endl;
																					    cout<<" 4| Terminar modificacion"<<endl;
																					    cout<<"\nR: ";
																					    cin>>o;
																					    cout << " ======================================================\n";
																						switch (o){
																							case 1:
																								system("CLS");
																								Fact.restaurar();
																								Fact.Cambiar(o);
																							break;
																							case 2:
																								system("CLS");
																								Fact.restaurar();
																								Fact.Cambiar(o);
																							break;
																							case 3:
																								system("CLS");
																								Fact.restaurar();
																								Fact.Cambiar(o);
																							break;
																							case 4:
																							break;
																							default:
																							break;
																			            	}
																						}while(o != 4);
																		     		break;
																				     
																				     case 3: 
																				    		Fact.restaurar();
																							Fact.leer();
																							do{
																								system("CLS");
																								cout<<"\n Id de la factura: ";
																								cin>>id;
																								volv = false;
																								for (int i = 0; i < Fact.Id.size(); ++i){
																									if(Fact.Id[i] == id){
																							        	cout<<"\n Id ya existente ingrese otra Id"<<endl;
																							        	volv = true;
																							        	cin.ignore();
																										getchar();
																									}
																								}
																							}while( volv == true);
																					      cout<<"\n Id del cliente: ";
																					      cin>>idc;
																					      cin.ignore();
																					      cout<<"\n Fecha: ";
																					      getline(cin,f);
																					      f.erase(remove(f.begin(), f.end(), ' '), f.end());
																					      Fact.AgregarDato4(id,idc,strcpy(fech,f.c_str()));
																				     break;
																				     
																				     case 4:
																				     	Fact.restaurar();
																						Fact.eliminarEspacio();
																					 break;
																				     
																				     default:
																				     	
											                					    	throw OpcionNoValidaException();
																			           ch = getch();
																			           system("cls");
																				     break;
																				     
																				    }
																	   break;
																	   */
																	   
																	   case 3:
																	    system("CLS");
																	    cout << " ==================|    CLIENTE    |==================\n";
																	    
																	    cout<<" 1| Enlistar cliente"<<endl;
																	    cout<<" 2| Modificar cliente"<<endl;
																	    cout<<" 3| Agregar cliente"<<endl;
																	    cout<<" 4| Eliminar cliente"<<endl;
																	    cout<<"\nR: ";
																	    cin>>o;
																	    cout << " ======================================================\n";
																		    switch (o){
																		    	
																			     case 1:
																				      system("CLS");
																				      Cli.restaurar();
																				      Cli.imprimir();
																				      cin.ignore();
																				      getchar();
																			     break;
																			     
																			    case 2:
																			    	do{
																			    		system("CLS");
																						cout << " ==================|   CLIENTE   |==================\n";
				    																	cout<<" 1| Modificar Id de cliente"<<endl;
																					    cout<<" 2| Modificar nombre"<<endl;
																					    cout<<" 3| Modificar direccion"<<endl;
																					    cout<<" 4| Modificar telefono"<<endl;
																					    cout<<" 5| Terminar modificacion"<<endl;
																					    cout<<"\nR: ";
																					    cin>>o;
																					    cout << " ======================================================\n";
																						switch (o){
																							case 1:
																								system("CLS");
																								Cli.restaurar();
																								Cli.Cambiar(o);
																							break;
																							case 2:
																								system("CLS");
																								Cli.restaurar();
																								Cli.Cambiar(o);
																							break;
																							case 3:
																								system("CLS");
																								Cli.restaurar();
																								Cli.Cambiar(o);
																							break;
																							case 4:
																								system("CLS");
																								Cli.restaurar();
																								Cli.Cambiar(o);
																							break;
																							case 5:
																							break;
																							default:
																							break;
																			            	}
																						}while(o != 5);
																		     	break;
																			     
																			     case 3: 
																						Cli.restaurar();
																						Cli.leer();
																				    	do{
																							system("CLS");
																				    		cout<<"\n Id del cliente: ";
																							cin>>id;
																							volv = false;
																							for (int i = 0; i < Cli.Id.size(); ++i){
																								if(Cli.Id[i] == id){
																						        	cout<<"\n Id ya existente ingrese otra Id"<<endl;
																						        	volv = true;
																						        	cin.ignore();
																									getchar();
																								}
																							}
																						}while( volv == true);
																				      cin.ignore();
																				      cout<<"\n Nombre del cliente: ";
																				      getline(cin,n);
																				      cout<<"\n Direccion del cliente: ";
																				      getline(cin,dr);
																				      cout<<"\n Telefono del cliente: ";
																				      getline(cin,t);
																				      t.erase(remove(t.begin(), t.end(), ' '), t.end());
																				      Cli.AgregarDato5(id,strcpy(nom,n.c_str()),strcpy(dir,dr.c_str()),strcpy(telf,t.c_str()));
																			     break;
																			     
																			     case 4:
																				     	Cli.restaurar();
																						Cli.eliminarEspacio();
																						o = 0;
																				 break;
																			     default:
																			     
										                					    	throw OpcionNoValidaException();
																		           ch = getch();
																		           system("cls");
																			     break;
																		    }
																		    
																	   break;
																	   
																	   case 4:
																	   usuario="";
																	   contrasena=""; 
																	   break;
																	   
																	   default:
																	   	 	
										                					    	throw OpcionNoValidaException();
																		           ch = getch();
																		           system("cls");
																	   	   usuario="";
																	   contrasena=""; 
																	   break;
																	   
																	  }
																
 															}while(o != 4);
            	
            
                 		   }
               			 }		
               			 
               




//------------------MENU PERSONAL DE ALMACEN
               			 
            	          		for (int i = 0; i < datos.usuario2.size(); ++i) {
                		if (datos.usuario2[i] == usuario && datos.contrasena2[i] == contrasena) {
                    		cout << "Usuario y contrasena correctos.\n";
                    		cout << " ======================================================\n";
							ch = getch();
							
							do{
					    		system("CLS");
					    		cout<< " =====================| ALMACEN |=====================\n";
						    	cout<<"\n  1| Mostrar lista de productos"<<endl;
								cout<<"\n  2| Agregar un producto nuevo"<<endl;
								cout<<"\n  3| Reponer stock de un producto"<<endl;
								cout<<"\n  4| Cambiar precio de un producto"<<endl;
								cout<<"\n  5| Salir"<<endl;
								cout<< " =====================================================\n";
						    	cout << "Por favor, elige una opcion: ";
						    	cin>>o;
						    	cout<< " =====================================================\n";
						    	
						    	switch (o){
						    		case 1:
						    			system("CLS");
								    	
								    	v.restaurar();
										v.imprimir();
									    
									    cin.ignore();
									    getchar();
						    		break;
						    		case 2:
						    			v.restaurar();
										v.leer();
						    			do{
											system("CLS");
											
								    		cout<<"\n Id del producto: ";
											cin>>id;
											volv = false;
											for (int i = 0; i < v.Id.size(); ++i){
												if(v.Id[i] == id){
										        	cout<<"\n Id ya existente ingrese otra Id"<<endl;
										        	volv = true;
										        	cin.ignore();
													getchar();
												}
											}
										}while( volv == true);
										
										cout<<"\n|Id del proveedor: ";
										cin>>idp;
										
										cout<<"\n|Numero del stock: ";
										cin>>st;
										
										cout<<"\n|Precio del producto: ";
										cin>>prec;
										
										cin.ignore();
										cout<<"\n|Descripcion del producto: ";
										getline(cin,d);
										
										cout<<"\n|Numero del stock_min: ";
										cin>>stm;
										
										v.AgregarDato2(id, idp, st, prec, strcpy(descp,d.c_str()), stm);
						    		break;
						    		case 3:
						    			system("CLS");
						    			v.restaurar();
						    			v.actualizar(o);
						    			
						    			cout << "\nStock repuesto.\n";
			                    		cout << " ======================================================\n";
										ch = getch();
						    		break;
						    		case 4:
						    			system("CLS");
						    			v.restaurar();
						    			v.actualizar(o);
						    			
						    			cout << "\nPrecio cambiado.\n";
			                    		cout << " ======================================================\n";
										ch = getch();
						    		break;
						    		case 5:
						    			usuario="";
										contrasena=""; 
						    		break;
						    		default:
						    			
						    			throw OpcionNoValidaException();
								           ch = getch();
								           system("cls");
						    			
						    		break;
								}
							}while(o != 5);
                		}
            		}
             		
     
             		
//------------------MENU PERSONAL DE CAJA 
             		  
		                for (int i = 0; i < datos.usuario3.size(); ++i) {
		                    if (datos.usuario3[i] == usuario && datos.contrasena3[i] == contrasena) {
		                    	
		                    		cout << "Usuario y contrasena correctos.\n";
                    		cout << " ======================================================\n";
							ch = getch();
		                    	int idon, Iddos, Identif;
		                    	float compra; 
		                    	
		                    	 char fecha[12];
		                    	 
		                    	 	system("cls");
		                    	 
		                    	 	cout << " ==================| Personal de Caja |==================\n";
		                    	 cout<<"Introduzca la fecha de hoy: "; 
		                    	 
							   cin >> fecha;
		                        
		                        cout << "\n ======================================================\n";
		                         cout << "\t  Presiona para continuar...\n";
							    ch = getch();
							    
                        
                        
                        		 do {
                        		 	system("cls");
    							cout << " ==================| Personal de Caja |==================\n";
			                    cout << "1.  Registrar Id de Cliente\n";
			                    cout << "2.  Ingresar Id de Cliente \n";
			                    cout << "3.  Terminar dia\n";
			                    cout << " ======================================================\n";
			                    cout << "Por favor, elige una opcion: ";
			                    cin >> opcion;
			                    cout << " ======================================================\n";
  							

									    // Validar la entrada
									  
									
											  switch (opcion) {
											      case 1:
											        	 	system("cls");
    							cout << " ==================| Personal de Caja |==================\n";
			                    cout << "    Introduzca la ID del nuevo cliente: ";
			                    cin >> idon;
			                   
			                   
			                    Cli.leer();
			                    
			                      for (int i = 0; i < Cli.Id.size(); ++i) {
		                    if (Cli.Id[i]==idon) {
		                    	koi++;
		                    }}
		                    
		                    if (koi==0){
		                    	  cout << "    ID DISPONIBLE, rellene sus datos... \n";
		                    	  cout << " ======================================================\n";
		                    
		                    	  system("cls");
								  cout << " ==================| Personal de Caja |==================\n";
				             
							      cin.ignore();
							      cout<<"\n Nombre del cliente: ";
							      getline(cin,n);
							      cout<<"\n Direccion del cliente: ";
							      getline(cin,dr);
							      cout<<"\n Telefono del cliente: ";
							      getline(cin,t);
							      Cli.AgregarDato5(idon,strcpy(nom,n.c_str()),strcpy(dir,dr.c_str()),strcpy(telf,t.c_str()));
			                  	  cout << " ======================================================\n";
		                    
		                    	
							}else{
								
								cout << "    ID NO DISPONIBLE, regresando... ";
		                    	 cout << " ======================================================\n";
								
							}
		                    
							
							
											    
											        
											        
											        break;
											        
											      case 2:
											        // Submenú
											        int subopcion, iden;
											        
											        Cli.leer();
											        
											        	system("cls");
					    							cout << " ==================| Personal de Caja |==================\n";
								                    cout << "Por favor, ingrese su ID: ";
								                    cin >> iden;
								                    cout << " ======================================================\n";
  													int identif;
  													
  													
														for (int i = 0; i < Cli.Id.size(); ++i) {
                											if (Cli.Id[i] == iden ) {
						
																 Fact.leer();
																	  
																 if (Fact.Id.empty()){identif=1;}else{ identif = Fact.Id.size();identif++;}
															
															     Fact.AgregarDato4(identif, iden, fecha );
						
						
						
													      
																		      
																			  // i = indice  
																		        do {
																		        		system("cls");
																		          cout << " ==================|      COMPRA      |==================\n";
																		          cout << "1| Anadir producto al carrito   " << endl;
																		      //    cout << "2| Eliminar producto del carrito" << endl;
																		          cout << "2| Pasar Factura                " << endl;
																		          cout << "Ingrese una opcion: ";
																		          cin >> subopcion;
								                  								  cout << " ======================================================\n";
																					
																			      int poe=0, posicion, cantidad, resta, ide,eliminar,exist, stock, indice, arreglo;
																			      float Total, monto;
																			         	
																					          switch (subopcion) {
																					            case 1:
																					            	
																					            	system("cls");
																					               v.restaurar();
																								   v.leer();
																								   Com.restaurar();
																								   Com.leer();
																								   cout << " ==================|      COMPRA      |==================\n";
																					               cout << "Introduzca el Id del producto: " ;
																					               cin>>Iddos;
																					              
																					              poe=0;
																					              
																					              for (int i = 0; i < v.Id.size(); ++i) {
							         													       		if (v.Id[i] == Iddos ) {
							         													       		
							         													       		posicion = i;
							         													       		
							         													       		
							         													       		cout<<"\n Articulo: "<<v.descripcion[posicion]<<endl;
							         													       		
							         													       		
							         													       		cout << "Introduzca la cantidad: ";
																										cin>>cantidad;
																										
																									
																								  
																							
																								  float value = v.precio [posicion];
																								  
																								  compra = value*cantidad;
																								  
																								  resta = v.stock[posicion]-cantidad;
																								  
																								  if(resta>=0){
																								  
																								  v.cambiarValor(v.stock,posicion,resta); 
																								  v.escribir();
																						          
																								   if (Com.Id.empty()){ide=1;}else{ide= Com.Id.size();
																								  
																								  ide++;}
																						         Com.AgregarDato3(ide,Iddos,identif,cantidad);
																										
																										
																								  
																								  }else{
																								  	
																								  	compra=0; 
																								  	cout<<"No existen suficiente Stock para realizar la compra";
																								  	ch = getch();
																		           system("cls");
																								  }
																								  
																								  poe++;	
																								  
							         													       		
																										
																										}}
							         													       		
							         													       		
							         													       		if (poe==0){
							         													       			
							         													       			
							         													       			 cout<<" Id NO ENCONTRADA";
							         													       			 ch = getch();
																		           system("cls");
																										}
																					              
																					              
																					              Total = Total+compra;
																					             
																								  
																								  cout << " ======================================================\n";
																								  
																					              break;
																					              /*
																					            case 2:
																					            	{
																									int count=0;
																					            	int contador=0;
																					            	float preciaje;
																					           	Com.restaurar();
																					            	Com.leer();
																					            	v.restaurar();
																					            	v.leer();
																					            	 system("cls");
																					            	  cout << " ==================|      COMPRA      |==================\n";
																					               	 cout<<"\n";
																					            	 
																					            	 
																					            	 exist=0;
																					            	 
																					            	   for (int i = 0; i < Com.Id.size(); ++i) {
							         													       		if (Com.Id_Factura[i] == identif ) {
							         													       			exist=1;
							         													       			contador++;
							         													       			
							         													       			cout<<contador<<"- ";
							         													       			
							         													       				int producto = Com.Id_producto [i];
							         													       			
							         													       			
							         													       			    for (int k = 0; k < v.Id.size(); k++) {
							         													       		if (v.Id[k] == producto ) {
							         													       			
							         													       			cout<<" "<<v.descripcion[k]<<"-"<<v.precio[k]<<" X ";
							         													       		}}
							         													       			
							         													       		cout<<Com.Cantidad[i]<<endl;	
							         													       			
							         													       		}
							         													       	}
							         													       	
							         													       	if (exist==1){
							         													       		
							         													       		
							         													       		
							         													       		
							         													       		cout << "Escoja la compra a sacar del carrito: ";
							         													       	cin>>eliminar;
							         													       	
							         													       	if(eliminar<=contador&&eliminar>0){
							         													       		
							         													       		
							         												
							         													       			   for (int i = 0; i < Com.Id.size(); ++i) {
							         													       		if (Com.Id_Factura[i] == identif ) {
							         													       			
							         													       			if(count==eliminar-1){
							         													       				
							         													       			
							         													       			
							         													       				int producto = Com.Id_producto [i];
							         													       			
							         													       			
							         													       			    for (int k = 0; k < v.Id.size(); k++) {
							         													       		if (v.Id[k] == producto ) {
							         													       			
							         													       			cout<<" "<<v.descripcion[k]<<"-"<<v.precio[k]<<" X ";
							         													       			stock = v.stock[k];
							         													       			preciaje = v.precio[k];
							         													       			indice = k;
							         													       		}}
							         													       			
							         													       		cout<<Com.Cantidad[i]<<endl;
							         													       		cantidad=Com.Cantidad[i];		
							         													       				
							         													       				indice = i;
							         													       				
																											}
							         													       			
							         													       			count++;
							         													       		
							         													       		}
							         													       	}
							         													       		
							         													       		cout<<" "<<stock<<" "<<cantidad<<" "<<indice<<endl;
							         													       		 arreglo = stock+cantidad;
							         													       		 float minus = preciaje * cantidad;
							         													       		 
							         													       		 Total = Total - minus;
							         													       		 
							         													       		v.cambiarValor(v.stock,indice,arreglo);
							         													       		Com.eliminacion(indice);
							         													       		v.escribir();
							         													       		Com.escribir();
							         													       	
							         													       			cout<<"Eliminacion completada\n";
							         													       				 ch = getch();
																		           system("cls");
							         													       		
							         													       		
																									}else{
																										
																										cout<<"No existe tal elemento!\n";
																												 ch = getch();
																		           system("cls");
																										
																										
																										
																									}
							         													       	
							         													       
							         													       		
																									}
																									
																									
																									if (exist ==0){
																										
																										
																										cout<<"No hay articulos para eliminar\n";
																										
																										 ch = getch();
																		           system("cls");
																										
																									}
							         													       	
							         													       	
																					          
																					         }
																					              break;
																					              */
																					              case 2:
																					              	{
																									  
																					            	Com.restaurar();
																					            	Com.leer();
																					            	int indiced;
																					            
																						
																					             
																					              cout << " ==================|      FACTURA     |==================\n";
																					              cout << " ********************************************************" << endl;
																								  cout << " **              Supermercado El Baratta               **" << endl;
																								  cout << " ****************************************************" << endl;
																								  cout << " ** Fecha: " <<setw(12) << fecha <<"**" << endl;
																								  cout << " ** ID: " <<setw(12) << identif <<"**" << endl;
																								  cout << "----------------------------------------------------" << endl;
																					              
																					              cout << " Las compras fueron: \n";
																					              
																					               for (int i = 0; i < Com.Id.size(); ++i) {
							         													       		if (Com.Id_Factura[i] == identif ) {
							         													       			
							         													       			
							         													       		
							         													       			int producto = Com.Id_producto [i];
							         													       			
							         													       			
							         													       			    for (int k = 0; k < v.Id.size(); k++) {
							         													       		if (v.Id[k] == producto ) {
							         													       			
							         													       			
							         													       		
							         													       			
							         													       			monto=v.precio[k];
							         													       			indiced = k;
							         													       		}}
							         													       		
							         													       		int carrito;
							         													       		
							         													       		carrito = Com.Cantidad[i]*monto;
																					              
																					              cout<<setw(2)<<Com.Cantidad[i]<<"x "; 
																					              cout<<setw(4)<<monto<<" = ";
																					              cout<<v.descripcion[indiced];
																					              cout<<" Bs "<<carrito<<" Aprox"<<endl;
																					              
																					          }}
																					          
																					           cout << "\n----------------------------------------------------" << endl;
																					           cout << "TOTAL                                   Bs "<<Total<< endl;
																					           cout << "****************************************************" << endl;
																							   cout << "** ¡Gracias por su compra! Vuelva pronto. **" << endl;
																							   cout << "****************************************************" << endl;
																					           ch = getch();
																					              }
																					              	break;
																					              
																					              
																					              default:
																					              	{
																									  
																					              	throw OpcionNoValidaException();
																		           ch = getch();
																		           system("cls");
																					              }
																					              	break;
																					              
																			          }
																				        } while (subopcion != 2);
																				        
																				    }else{cout<<"ID no encontrada"<< endl;}
																				        
																						}
																				        break;
																				        
																				        case 3:
																				        	
																				        	int identifi=0, signal=0;
																				        	
																				        	fstream text("Listado.txt", ios::out);
																				        	text.seekp(ios::end);
							
																						  // Si el archivo se abrió correctamente
																						  if (text.is_open()) {
																						    // Escribe información en el archivo usando el operador de inserción
																						    pv.restaurar();
																						    pv.leer();
																						    v.restaurar();
																						    v.leer();
																							 
																							 text << "Listado del: "<<fecha<< endl;
																						    
																						     for (int i = 0; i < pv.Id.size(); ++i) {
																							 
																							 text << pv.Id[i]<<" "<<pv.nombre[i]<<" "<< pv.telefono[i]<< endl;
																							 
																							 identifi = pv.Id[i];
																							 
																							   for (int k = 0; k < v.Id.size(); ++k) {
																							   	
																							   	
																							   	
																							 
																							 if(v.Id_proveedor[k]==identifi){
																							 	
																							 	
																							 	
																							 	if(v.stock[k]<=v.stock_min[k]){
																							 	
																							 		text << "Se deben reponer: "<<v.descripcion[k]<<" de código: "<< v.Id[k]<< endl;
																							 		signal++;
																							 		
																							 		
																							 		
																								 }
																							 }
																							 	
																							 }
																							 
																						
																							 
																							 }
																							  
																								 if(signal==0){
																							 	
																							 	text << "no hay artículos a reponer"<< endl;
																							 
																							 }
																							 
																							 }
															    
													
															
																								    // Cierra el archivo
																								    text.close();
																						        	
																						        	break;
																						        	
																						        
																						        
																						    }
																						    
																		  } while (opcion != 3);
																		
									
									 
									
									 				
		                    	
		                    	
		                    	
					                    		}
					                		}
                
                   system("cls");
                   cout << " ==================|Regresando...|==================\n";
           		   ch = getch();
                   usuario = "";
                   contrasena="";
                   system("cls");
          	       break;

//REGISTRO DE NUEVO USUARIO - CREACION DE NUEVO PERFIL
                
          		  case 2:
            	  	system("cls");
               
             	 cout << " ==================|    REGISTRARSE   |==================\n";
              	 cout << " Ingrese la contrasena de administrador: ";
               	 cin >> contrasenaAdmin;
              	datos.leerArchivo("datos1.dat", datos.usuario1, datos.contrasena1);
                
              		  for (int i = 0; i < datos.contrasena1.size(); ++i) {
                 		   if (datos.contrasena1[i] == contrasenaAdmin) {
                      		  esAdmin = true; break;
                   		 }
               		 }
               		 
                    //Caso .DAT no creado
               		 if (datos.usuario1.empty()){esAdmin = true;}
		
               		 if (esAdmin) {
                   	 // Preguntar qué tipo de usuario se va a crear
                   	 int tipoUsuario;
                    
                    
                    cout << " ==================| Clase de Usuario |==================\n";
                    cout << "1. Admin              \n";
                    cout << "2. Personal de almacen\n";
                    cout << "3. Cajero             \n";
                    cout << " ==========================================================\n";
                    cout << "R: ";
                    cin >> tipoUsuario;
                    
                    // Agregar nuevos datos
                   		 if(tipoUsuario > 3 || tipoUsuario < 1) {
                   	 	
                     		   cout << "Tipo de usuario no valido. Por favor, intenta de nuevo.\n";
                        	   throw OpcionNoValidaException();
                      		   break;
                   		 }
                  
                    cout << "\nIngrese el nombre de usuario: ";
                    cin >> usuario;
                    cout << "\nIngrese la contrasena: ";
                    cin >> contrasena;
                    
                    // Agregar los datos a los vectores y escribirlos en los archivos
                    
                            	      for (int i = 0; i < datos.usuario1.size(); ++i) {
                            	      	
                   						 if (datos.usuario1[i] == usuario) {
                      						  cout << "\n\n Usuario ya en Uso...\n\n";
                       						  key++; 
                  							  } 
              							  }
                
						                for (int i = 0; i < datos.usuario2.size(); ++i) {
						                    if (datos.usuario2[i] == usuario) {
						                           cout << "\n\n Usuario ya en Uso...\n\n";
						                       	   key++;
						                    }
										}
							
						                for (int i = 0; i < datos.usuario3.size(); ++i) {
						                    if (datos.usuario3[i] == usuario) {
						                            cout << "\n\n Usuario ya en Uso...\n\n";
						                            key++;
						                    }
						                }
                    
                		if(key==0){
					
                    
                   			 if (tipoUsuario == 1) {
                        			datos.agregarDatos(usuario, contrasena, datos.usuario1, datos.contrasena1);
                        			datos.escribirArchivo("datos1.dat", datos.usuario1, datos.contrasena1);
                  			  }
                  			  
					 		 if (tipoUsuario == 2) {
                      			  datos.agregarDatos(usuario, contrasena, datos.usuario2, datos.contrasena2);
                      			  datos.escribirArchivo("datos2.dat", datos.usuario2, datos.contrasena2);
                    		 } 
							
							 if (tipoUsuario == 3) {
			                      datos.agregarDatos(usuario, contrasena, datos.usuario3, datos.contrasena3);
			                      datos.escribirArchivo("datos3.dat", datos.usuario3, datos.contrasena3);
                    		 }
					  }			
                    
                  
                } else { cout << "\n\n Contrasena de administrador INCORRECTA...\n\n";}
                
	               cout << " ==================|Regresando...|==================\n";
			       ch = getch();
			       system("cls");
	          	   usuario = "";
	               contrasena="";
                
			       datos.borrarArchivo(datos.usuario1, datos.contrasena1);
			       datos.borrarArchivo(datos.usuario2, datos.contrasena2);
			       datos.borrarArchivo(datos.usuario3, datos.contrasena3);
                break;
                
            case 3:
	                cout << "Has seleccionado salir. ¡Hasta luego!\n";
	        break;
                
            default:
	            	 throw OpcionNoValidaException();
	                 cout << " ==================|Regresando...|==================\n";
		             ch = getch();
		             system("cls");
            break;
        }
        
        
}catch (OpcionNoValidaException& e) {
			 cout << e.what() << endl;
   			 cout << " ==================|Regresando...|==================\n";
   			 opcion = 3;
 			 }
			  	    
    } while (opcion != 3);

    return 0;
}

