#include <iostream>
#include <ctime>
#include <cstdlib>
#include <conio.h>
#include <iomanip>
using namespace std;

// Estructura para un nodo en la lista doblemente enlazada
struct Node {
  int data; // Valor almacenado en el nodo
  Node *next; // Puntero al siguiente nodo en la lista
  Node *prev; // Puntero al nodo anterior en la lista
};

// Clase para manejar la lista doblemente enlazada
class ListaDobleEnlazada {
	private:
		Node *head; // Puntero al primer nodo de la lista
		Node *tail; // Puntero al último nodo de la lista
	public:
		// Constructor para inicializar una lista vacía
		ListaDobleEnlazada() {
			head = NULL;
			tail = NULL;
		}
	
		// Método para insertar un nuevo nodo al final de la lista
		void insertar(int data) {
			Node *newNode = new Node;
			newNode->data = data;
			newNode->next = NULL;
			newNode->prev = NULL;
			
			if (head == NULL) {
			  head = newNode;
			  tail = newNode;
			} else {
			  tail->next = newNode;
			  newNode->prev = tail;
			  tail = newNode;
			}
		}
	
		// Método para eliminar el nodo de la lista
		void borrar(Node *pos) {
			Node *current = pos->prev;
			
			if (pos->prev != NULL && pos->next != NULL) {
				current->next = pos->next;
			} else if (pos->prev == NULL && pos->next == NULL){
				head = NULL;
    			tail = NULL;
			} else if (pos->next == NULL){
				current->next = NULL;
				tail = current;
			} else if (pos->prev == NULL){
				head = head->next;
				head->prev = NULL;
			}
			delete pos;
		}
	
		// Método para imprimir la lista en orden (de principio a fin)
		void printForward() {
			Node *current = head;
			while (current != NULL) {
			  cout << current->data << " ";
			  current = current->next;
			}
			cout << endl;
		}
	
		// Método para buscar la posicion de un nodo y luego borrarlo
		void BusquedaBorradora(int posic) {
			Node *current = head;
			if(posic == 0){
				borrar(current);
			}else{
				for(int i = 0; i < posic; i++){
			        current = current->next;
			    }
			    borrar(current);
			}
		}
		
		int SumaTotal(){
			Node *current = head;
			int suma = 0;
			
			while(current != NULL){
		        suma = suma + current->data;
		        current = current->next;
		    }
		    
		    return suma;
		}
		
		void BorrarLista(){
		    Node *lista = head->next;
		    head = NULL;
			
		    while(lista != tail){
		        Node *borrar = lista;
		        lista = lista->next;
		        delete(borrar);
		    }
		    tail = NULL;
		    delete(lista);
		}
};

int main() {
	
    string opcion;
	int Diffi = 18;
	int Difficulty;
    
    do{
    	
    //Menu Principal	         
	system("cls");   
    cout << "            ______________________________________                  \n";
    cout << "           /   _______________________________    \\                \n";
    cout << "           |   |            __ __              |   |                \n";
    cout << "           | _ |    /\\     ) _) /''''',        |   |               \n";
    cout << "           |(.)|   <  >    \\ ) // '  `,        |   |               \n";
    cout << "           | ` |    \\/       \\/// ~ |~:    +   |   |              \n";
    cout << "           |   |             ///*\\  ' :    |   |   |               \n";
    cout << "           |   |            ///***\\_~.'    |   |   |               \n";
    cout << "           |   |  .'.    __///````,,..\\_   |   |   |               \n";
    cout << "           |   |   ` \\ _/* +_\\#\\\\~\\ooo/ \\  |   |   |          \n";
    cout << "           |   |     |/:\\ + *_\\_\\#\\~\\so/!!\\|   |   |          \n";
    cout << "           |   |    _\\_::\\ * + \\-\\#\\\\o/!!!\\|   |   |        _   __      _       __   _                 \n";
    cout << "           |   |   / <_=::\\ + */_____@_!!!_|   |   |       | | / /___  (_)___  / /_ (_)__ __ ___  ___ \n";
    cout << "           |   |  <__/_____\\ */( /\\______ _|   |   |  ===  | |/ // -_)/ // _ \\/ __// // // // _ \\/ _ \\ ===\n";
    cout << "           |   |   |_   _ __\\/_)/* \\   ._/  >  |   |       |___/ \\__//_//_//_/\\__//_/ \\_,_//_//_/\\___/\n";
    cout << "           |   |   | !!! @     /* + \\::=_>_/   |   |      \n";
    cout << "           |   |   |\\!!!/o\\\\#\\_\\ + * \\::_\\     |   |         || 1. Jugar  \n";
    cout << "           |   |   | \\!!/os\\~\\#_\\_* + \\:/|     |   |         ||  \n";
    cout << "           |   |   |  \\_/ooo\\~\\\\#_\\+_*/- \\     |   |         || 2. Dificultad          \n";
    cout << "           |   |   |    '\\''``,,,,///      .`. |   |         ||      \n";
    cout << "           |   |   |     ,.- ***///        '   |   |         || 3. Instrucciones  \n";
    cout << "           |   |   |    : ~  \\*///             |   |         ||       \n";
    cout << "           |   |   +    : |   \\//\\             |   |         || 4. Salir      \n";
    cout << "           |   |        ,~  ~ //_( \\     /\\    | ; |           \n";
    cout << "           |   |        ,'  ` /_(__(    <  >   |(_)|           \n";
    cout << "           |   |         `````           \\/    |   |                \n";
    cout << "           |   |_______________________________|   |                \n";
    cout << "           \\______________________________________/          | R: ";
    cin >> opcion;

    switch (std::stoi(opcion)) {
    	
    	
        case 1:{
			
        		int rount = 0, WinPJ = 0, WinCPU = 0;
        		
        		
        			do{				
							// Jugador
							ListaDobleEnlazada ManoPJ; 
							int opPJ, opCard, randPJ, posic = 0, sumaPJ = 0, posC1, posC2, posC3, posC4, posC5, numC1, numC2, numC3, numC4, numC5;
							bool card1 = false, card2 = false, card3 = false, card4 = false, card5 = false, comodin = true, CC1 = false, CC2 = false, CC3 = false, CC4 = false, CC5 = false;
							
							// Computadora
							ListaDobleEnlazada ManoCPU;
							int sumaCPU = 0, randCPU;
							
							//Contador Auxiliar
							int count = 0;
						do{
		
							do {
								
								        //Interfaz PRINCIPAL
										system("cls");	  
									    cout << "\t\t\t\t  _   __      _       __   _                \n";
									    cout << "\t\t\t\t | | / /___  (_)___  / /_ (_)__ __ ___  ___ \n";
									    cout << "\t\t\t   ===== | |/ // -_)/ // _ \\/ __// // // // _ \\/ _ \\ =====\n";
									    cout << "\t\t\t\t |___/ \\__//_//_//_/\\__//_/ \\_,_//_//_/\\___/\n";
									    cout <<endl;
									    cout <<endl;  
									    cout << "\t\t\t\t_ _ _________ \t==ESTADISTICAS==   _________  \n";
									    cout << "\t\t\t\t||?|?        |\t\t\t  |X   _    |   \n";
									    cout << "\t\t\t\t|| |         |\tJUGADOR: "<<WinPJ<<"\t  |   / \\   |        \n";
									    cout << "\t\t\t\t|| |         |\tCPU: "<<WinCPU<<"   \t  |  / ^ \\  |         \n";
									    cout << "\t\t\t\t|| |   :)    |\t\t\t  | ( "<<setw(2)<<sumaCPU<<"  ) |  \n";
									    cout << "\t\t\t\t|| |         |\t\t\t  |  \\ v /  |    \n";
									    cout << "\t\t\t\t|| |         |\t\t\t  |   \\_/   |     \n";
									    cout << "\t\t\t\t|| |        ?|\t\t\t  |        X|    \n";
									    cout << "\t\t\t\t~ ~~ ~~~~~~~~ \t\t\t   ~~~~~~~~~   \n";
									    cout << "\t\t\t\t    BARAJA \t\t\t   CARTAS CPU\n";   
										cout <<endl;
										cout <<endl;
										cout <<endl;
									    cout << "\t\t\t      ________   ________   ________   ________   ________\n";
									    cout << "\t\t\t     |        | |        | |        | |        | |        |\n";
									    cout << "\t\t\t     |        | |        | |        | |        | |        |      === SUMATORIA ===\n";
									    
									    
									    if(card1==false){cout<<"\t\t\t     |   ?    | ";}else{cout<<"\t\t\t     |   "<<setw(2)<< numC1 <<"   | ";}
									    if(card2==false){cout<<"|   ?    | ";}else{cout<<"|   "<<setw(2)<<numC2<<"   | ";}
									    if(card3==false){cout<<"|   ?    | ";}else{cout<<"|   "<<setw(2)<<numC3<<"   | ";}
									    if(card4==false){cout<<"|   ?    | ";}else{cout<<"|   "<<setw(2)<<numC4<<"   | ";}
									    if(card5==false){cout<<"|   ?    | ";}else{cout<<"|   "<<setw(2)<<numC5<<"   | ";}
									    
									    
									    
										cout<<"     ===   "<<setw(2)<<sumaPJ<<" pts  ===\n";
									    cout << "\t\t\t     |        | |        | |        | |        | |        |\n";
									    cout << "\t\t\t     |        | |        | |        | |        | |        |\n";
									    cout << "\t\t\t      --------   --------   --------   --------   -------- \n";
									    cout << "\t\t\t                           CARTAS J1 \n";
									    cout <<endl;
									    cout <<endl;
									    cout <<endl;
									    cout << "\t\t\t                            COMODIN            \n"; 
									
									    
									    if (comodin != true){
									    cout << "\t\t\t                 ------------------------------            \n";   
										cout << "\t\t\t                |------------VACIO-------------|    \n";          
									    cout << "\t\t\t                 ------------------------------            \n"; 
										}else{	
										cout << "\t\t\t                 ------------------------------            \n";   
										cout << "\t\t\t                |----------DISPONIBLE----------|    \n";          
									    cout << "\t\t\t                 ------------------------------            \n"; 
											
										}
    
										cout << "\n\n\t\t\t\t\t     ====== OPCIONES ====== " << endl;
										cout << "\t\t\t\t\t     1| Revelar carta" << endl;
										cout << "\t\t\t\t\t     2| Uso de Comodin" << endl;
										cout << "\t\t\t\t\t     3| Terminar Ronda" << endl;
										cout << "\t\t\t\t\t     R: ";
										
										//Pedida de Datos con contador Auxiliar
										if(count == 0){cin >> opPJ;}else{opPJ=3;}	
			
										switch(opPJ){
										
										//Manejo de Cartas	
									    	case 1:
									    		cout << "\n\n\t\t\t\t\t     Selecciona una carta: ";
									    		cin >> opCard;
									    		
									    		srand(time(0));
									    		
									    		switch(opCard){
									    			
														case 1:
														if(CC1 == false){
												    	if(card1 == false){
													    	randPJ = rand() % 12 + 1;
												    		ManoPJ.insertar(randPJ);
												    		card1 = true;
												    		numC1 = randPJ;
												    		posC1 = posic;
												    		posic++;	
													    }else{cout << "\n\t\t\t\t\t     Esta carta ya esta revelada" << endl;}
														}else{cout << "\n\t\t\t\t\t     Esta carta ya no puede ser revelada" << endl;}
											    		break;
											    		
												        case 2:
												    	if(CC2 == false){
												    	if(card2 == false){
												    		randPJ = rand() % 12 + 1;
												    		ManoPJ.insertar(randPJ);
												    		card2 = true;
												    		numC2 = randPJ;
												    		posC2 = posic;
												    		posic++;
													    }else{cout << "\n\t\t\t\t\t     Esta carta ya esta revelada" << endl;}
														}else{cout << "\n\t\t\t\t\t     Esta carta ya no puede ser revelada" << endl;}
												    break;
												    
												    case 3:
												    	if(CC3 == false){
												    	if(card3 == false){
												    		randPJ = rand() % 12 + 1;
												    		ManoPJ.insertar(randPJ);
												    		card3 = true;
												    		numC3 = randPJ;
												    		posC3 = posic;
												    		posic++;
													    }else{cout << "\n\t\t\t\t\t     Esta carta ya esta revelada" << endl;}
														}else{cout << "\n\t\t\t\t\t     Esta carta ya no puede ser revelada" << endl;}
												    break;
												    
												    case 4:
												    	if(CC4 == false){
												    	if(card4 == false){
												    		randPJ = rand() % 12 + 1;
												    		ManoPJ.insertar(randPJ);
												    		card4 = true;
												    		numC4 = randPJ;
												    		posC4 = posic;
												    		posic++;
													    }else{cout << "\n\t\t\t\t\t     Esta carta ya esta revelada" << endl;}
														}else{cout << "\n\t\t\t\t\t     Esta carta ya no puede ser revelada" << endl;}
												    break;
												    
												    case 5:
												    	if(CC5 == false){
												    	if(card5 == false){
												    		randPJ = rand() % 12 + 1;
												    		ManoPJ.insertar(randPJ);
												    		card5 = true;
												    		numC5 = randPJ;
												    		posC5 = posic;
												    		posic++;
													    }else{cout << "\n\t\t\t\t\t     Esta carta ya esta revelada" << endl;}
														}else{cout << "\n\t\t\t\t\t     Esta carta ya no puede ser revelada" << endl;}
												    break;
												    
											    	default:
											    	break;
											    	
												}	
								    	break;
								    	
								    	//Manejo de COMODIN
									    case 2:
										    	if(comodin == true){
											    	cout << "\n\t\t\t\t\t     Carta a aplicar el comodin: ";
										    		cin >> opCard;
										    	
										    	
										    		switch(opCard){
										    			
												    	case 1:
												    		if(card1 == true){
												    			ManoPJ.BusquedaBorradora(posC1);
												    			card1 = false;
												    			comodin = false;
												    			CC1 = true;
															}else{cout << "\n\t\t\t\t\t     Esta carta no a sido revelada" << endl;cin.ignore();getchar();}
												    	break;
												    	
													    case 2:
													    	if(card2 == true){
												    			ManoPJ.BusquedaBorradora(posC2);
												    			card2 = false;
												    			comodin = false;
												    			CC2 = true;
															}else{cout << "\n\t\t\t\t\t     Esta carta no a sido revelada" << endl;cin.ignore();getchar();}
													    break;
													    
													    case 3:
													    	if(card3 == true){
												    			ManoPJ.BusquedaBorradora(posC3);
												    			card3 = false;
												    			comodin = false;
												    			CC3 = true;
															}else{cout << "\n\t\t\t\t\t     Esta carta no a sido revelada" << endl;}
													    break;
													    
													    case 4:
													    	if(card4 == true){
												    			ManoPJ.BusquedaBorradora(posC4);
												    			card4 = false;
												    			comodin = false;
												    			CC4 = true;
															}else{cout << "\n\t\t\t\t\t     Esta carta no a sido revelada" << endl;}
													    break;
													    
													     case 5:
													    	if(card5 == true){
												    			ManoPJ.BusquedaBorradora(posC5);
												    			card5 = false;
												    			comodin = false;
												    			CC5 = true;
															}else{cout << "\n\t\t\t\t\t     Esta carta no a sido revelada" << endl;}
													    break;
													    
												    	default:
												    	break;
														}
														
												}else{cout << "\n\t\t\t\t\t     Ya se uso el comodin en esta ronda" << endl;}
												
										    break;
										    
										    //FIN DE RONDA
										    case 3:
										    break;
										    
										    //DEFAULT
									    	default:
									    	break;
										}
			
										sumaPJ = ManoPJ.SumaTotal();
										
										}while(opPJ != 3);
		
											if(count == 0){
												
											
											do{   //CPU
											srand(time(0));
											randCPU = rand() % 12 + 1;
											ManoCPU.insertar(randCPU);
											sumaCPU = ManoCPU.SumaTotal();
											}while(sumaCPU < Diffi);}
											
											count++;
											
										}while(count != 2);
			
								rount++;
								
								if(sumaPJ > sumaCPU && sumaPJ <= 21){WinPJ++; cout << "\n\t\t\t\t\t     El Jugador gano la ronda" << endl;}
								
								else if(sumaCPU > sumaPJ && sumaCPU <= 21){WinCPU++;cout << "\n\t\t\t\t\t     El Computador gano la ronda" << endl;}
								
								else if(sumaCPU == sumaPJ){cout << "\n\t\t\t\t\t     Ninguno gano la ronda" << endl; rount--;}
								
								else if(sumaCPU <= 21){WinCPU++; cout << "\n\t\t\t\t\t     El Computador gano la ronda" << endl;}
								
								else if(sumaPJ <= 21){WinPJ++;cout << "\n\t\t\t\t\t     El Jugador gano la ronda" << endl;}
								
								else{cout << "\n\t\t\t\t\t     Ninguno gano la ronda" << endl;rount--;}
								
								cin.ignore();
								getchar();
								
								ManoPJ.BorrarLista();
								ManoCPU.BorrarLista();
								
						}while(rount != 3 && WinPJ != 2 && WinCPU != 2);
	
	
     	
	
	
	
						if(WinPJ == 2){
						system("cls");
					    cout << "\t\t\t\t  _   __      _       __   _                \n";
					    cout << "\t\t\t\t | | / /___  (_)___  / /_ (_)__ __ ___  ___ \n";
					    cout << "\t\t\t   ===== | |/ // -_)/ // _ \\/ __// // // // _ \\/ _ \\ =====\n";
					    cout << "\t\t\t\t |___/ \\__//_//_//_/\\__//_/ \\_,_//_//_/\\___/\n";
					    cout<<endl;
					    cout<<endl;
					    cout << "\t\t\t\t     _\n";
					    cout << "\t\t\t\t   _| |\n";
					    cout << "\t\t\t\t _| | |\n";
					    cout << "\t\t\t\t| | | |\n";
					    cout << "\t\t\t\t| | | | __\n";                
					    cout << "\t\t\t\t| | | |/  \\\t   _   ___     __           _     \n";
					    cout << "\t\t\t\t|       /\\ \\\t  | | / (_)___/ /____  ____(_)__ _\n";
					    cout << "\t\t\t\t|      /  \\/\t  | |/ / / __/ __/ _ \\/ __/ / _ `/ \n";
					    cout << "\t\t\t\t|      \\  /\\\t  |___/_/\\__/\\__/\\___/_/ /_/\\_,_/ \n";
					    cout << "\t\t\t\t|       \\/ /\n";
					    cout << "\t\t\t\t \\        /\n";
					    cout << "\t\t\t\t  |     /\n";
					    cout << "\t\t\t\t  |    |\n\n";
					    cout<<endl;
					    cout<< "\t\t\t   -------------------------------------------------------\n";
					    cout<< "\t\t\t   |                VOLVER A INTENTARLO?                 |\n";
					    cout<< "\t\t\t   -------------------------------------------------------\n";
    
						}
						
						if(WinCPU==2){
							    system("cls");
					    cout << "\t\t\t\t  _   __      _       __   _                \n";
					    cout << "\t\t\t\t | | / /___  (_)___  / /_ (_)__ __ ___  ___ \n";
					    cout << "\t\t\t   ===== | |/ // -_)/ // _ \\/ __// // // // _ \\/ _ \\ =====\n";
					    cout << "\t\t\t\t |___/ \\__//_//_//_/\\__//_/ \\_,_//_//_/\\___/\n";
					    cout <<endl;
					    cout <<endl;
					    cout <<endl;
					    cout << "\t\t\t          _.-/`) \t\t\n";
					    cout << "\t\t\t         // / / )\t\t\n";
					    cout << "\t\t\t      .=// / / / )\t\t\n";
					    cout << "\t\t\t     //`/ / / / /\t   ___                   __      \n";
					    cout << "\t\t\t    // /     ` /\t  / _ \\___ ___________  / /____ _\n";
					    cout << "\t\t\t   ||         /\t\t / // / -_) __/ __/ _ \\/ __/ _ `/ \n";
					    cout << "\t\t\t    \\\\       /\t\t/____/\\__/_/ /_/  \\___/\\__/\\_,_/\n";
					    cout << "\t\t\t     ))    .'\t\t\n";
					    cout << "\t\t\t    //    /\t\t\n";
					    cout << "\t\t\t         /\t\t\n";
					    cout <<endl;
					    cout << "\t\t\t   -------------------------------------------------------\n";
					    cout << "\t\t\t   |                VOLVER A INTENTARLO?                 |\n";
					    cout << "\t\t\t   -------------------------------------------------------\n";
						}
						getchar();   		
        		
   				 break;
		}
        	
			        case 2:
			    	system("cls");     	
			    cout << "\t\t\t\t   ___  _ ____          ____          __" << endl;
			    cout << "\t\t\t\t  / _ \\(_) _(_)_____ __/ / /____ ____/ /" << endl;
			    cout << "\t\t\t\t / // / / _/ / __/ // / / __/ _ `/ _  / " << endl;
			    cout << "\t\t\t\t/____/_/_//_/\\__/\\_,_/_/\\__/\\_,_/\\_,_/" << endl;
			    cout<< endl;
				cout<< endl;
			    cout<< endl;     
				cout<<"\t || DIFICIL(3): Alta posibilidad de no pasar de 21 y mantener una puntuacion Normal (CPU) \n\n";
				cout<<"\t || NORMAL(2): Posibilidad normal de mantener puntuacion media (CPU)\n\n";
				cout<<"\t || FACIL(1): Posibilidad alta de pasar de 21 (CPU) \n\n";
				cout<<"\n\t ||Introduce el numero: ";
				cin>> Difficulty;
			
					if(Difficulty == 1){
					Diffi = 20;
				}else if (Difficulty == 2){
					Diffi = 18;
				}else if (Difficulty == 3){
					Diffi = 16;
				}
				
				
				cout<<"\n\t ||Dificultad ESTABLECIDA ";	
			        	cin.ignore();
						getchar();	
			            break;
			        case 3:
			        		system("cls"); 
			    cout << "\t\t\t\t   ____         __                   _                 " << endl;
			    cout << "\t\t\t\t  /  _/__  ___ / /_______ __________(_)__  ___  ___ ___" << endl;
			    cout << "\t\t\t\t _/ // _ \\(_-</ __/ __/ // / __/ __/ / _ \\/ _ \\/ -_|_-<" << endl;
			    cout << "\t\t\t\t/___/_//_/___/\\__/_/  \\_,_/\\__/\\__/_/\\___/_//_/\\__/___/" << endl;
			    cout<< endl;
				cout<< endl;
			    cout<< endl; 
				cout<<"\t 1. Se le proporcionaran 5 Cartas VOLTEADAS, podra decidir si voltearla o finalizar la RONDA. \n\n";
				cout<<"\t 1. Basara su decision en la SUMA de las cartas volteadas, intentara NO PASAR de 21 pts.\n\n";
				cout<<"\t 1. Aquel que se acerque mas pero sin pasarse recibira el punto. \n\n";
				cout<<"\t 1. Tiene derecho de un COMODIN, que sera desvoltear la carta de su preferencia.\n\n";  	\
				cout<<"\t 1. Solo se podra usar una vez por PARTIDA.\n\n";  	
				 	cin.ignore();
						getchar();
			            break;
			            
			            case 4:
			            	break;
			            
			        default:
			            cout << "Opcion no reconocida.\n";
			            break;
			    }
			    	
				}while(opcion!="4");
				
  return 0;
}
