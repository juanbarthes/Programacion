#include <iostream>
#include "GrafoD.h"


using namespace std;

//declaro variables globales

int time=0;
int v;
int dim;
int * des;
int * fin;
char * marcas;
int * padres;

template<typename C>
ostream & operator << (ostream & salida, const GrafoD<C> & Grafo)
    {
	// Recorremos todos los vertices
	list<int> vertices;
	Grafo.devolverVertices(vertices);
	list<int>::iterator v = vertices.begin();

	while (v != vertices.end()) {
		salida << "    " << *v << "\n";
		// Recorremos todos los adyacentes de cada vertice
		list< typename GrafoD<C>::Arco> adyacentes;
		Grafo.devolverAdyacentes(*v, adyacentes);
		typename list< typename GrafoD<C>::Arco>::iterator ady = adyacentes.begin();
		while (ady != adyacentes.end()) {
			salida << "   " <<  *v << "->"  << ady->devolverAdyacente() << " (" << ady->devolverCosto () << ")\n";
			ady++;
		}
		v++;
	}
	return salida;
}


//-----------   R E C O R R I D O S ----------------


template<typename C>
void DFS (GrafoD<C>& g, int origen){
    time++;
    des[origen] = time;
    marcas[origen]= 'g';
    list< typename GrafoD<C>::Arco> adyacentes;
    g.devolverAdyacentes(origen, adyacentes);
    typename list< typename GrafoD<C>::Arco>::iterator ady = adyacentes.begin();
    cout << g<< endl;
    while (ady != adyacentes.end()) {
        v=ady->devolverAdyacente();
        if (marcas[v]=='b'){
            padres[v]=origen;
            DFS(g, v);
        ady++;
		}
    time++;
    marcas[origen]='n';
}
}

template<typename C>
void DFS_destino (GrafoD<C>& g, int origen, int destino, int costo){
    time++;
    des[origen] = time;
    marcas[origen]= 'g';//MARCO EN GRIS DONDE ESTOY PARADO
    if (origen!=destino){//SI NO LLEGUE A DESTINO ENTRO AL IF
        list< typename GrafoD<C>::Arco> adyacentes;//DECLARO UNA LISTA PARA CONTENER LOS ADYACENTES DEL VERTICE EN EL QUE ESTOY PARADO
        g.devolverAdyacentes(origen, adyacentes);//CARGO LA LISTA
        typename list< typename GrafoD<C>::Arco>::iterator ady = adyacentes.begin();//DECLARO UN ITERADOR
        int cost = 0;
        while (ady != adyacentes.end()) {//POR CADA ADYACENTE CON MARCA BLANCA EJECUTO DFS

            v=ady->devolverAdyacente();
            if (marcas[v]=='b'){
                cost = costo+g.costoArco(origen,v);
                padres[v]=origen;
                DFS_destino(g, v, destino, cost);
            }
            ady++;


        }
    }
    else //SI LLEGO A ORIGEN ENTRO AL ELSE
        {
            cout << "EL COSTO TOTAL DE ESTE CAMINO ES: " << costo << endl;
            cout << "EL CAMINO ES: " << endl;
    time++;
    int i=origen;
    cout << origenm;
    while (padres[i]!=-1){ //IMPRIMO EL ARREGLO DE PADRES
        cout << "<--" << padres[i];
        i=padres[i];
    }
    cout << endl;

}
    marcas[origen]='b'; //VUELVO A MARCAR EN BLANCO LOS VERTICES
}
template<typename C>
void DFS_Forest (GrafoD<C>&g){
// escriba aquí su código
}

//-----------------   M A I N  ------------------

int main(int argc, char **argv)
{

	GrafoD<int> g;


	// Cargamos un GrafoDirigido dirigido
	// Primero los vértices
	g.agregarVertice(1);
	g.agregarVertice(2);
	g.agregarVertice(3);
	g.agregarVertice(4);
	g.agregarVertice(5);
	g.agregarVertice(6);
	g.agregarVertice(7);
	g.agregarVertice(8);
	g.agregarVertice(9);
	g.agregarVertice(10);
	g.agregarVertice(11);
	g.agregarVertice(12);

    // Luego los arcos
	g.agregarArco(1, 2, 1);
	g.agregarArco(1, 7, 5);
	g.agregarArco(2, 3, 3);
	g.agregarArco(4, 3, 4);
	g.agregarArco(5, 3, 7);
	g.agregarArco(5, 7, 2);
	g.agregarArco(6, 5, 1);
    g.agregarArco(6, 8, 15);
	g.agregarArco(7, 11, 3);
	g.agregarArco(8, 4, 10);
	g.agregarArco(8, 5, 2);
	g.agregarArco(8, 12, 1);
	g.agregarArco(9, 8, 2);
    g.agregarArco(10, 6, 4);
	g.agregarArco(10, 7, 9);
	g.agregarArco(10, 9, 20);
    g.agregarArco(11, 10, 2);
    g.agregarArco(11, 12, 3);



	//inicializacion de los arreglos
	dim = g.cantidadVertices();
    marcas= new char[dim];
    padres= new int[dim];
    des= new int[dim];
    fin= new int[dim];
	for (int i=0; i<=dim; i++){
        marcas[i] = 'b';
        padres[i]=-1;
        des[i]=0;
        fin[i]=0;
	}

    int origen=11;
    int dest=3;
    int costo=0;
    DFS_destino(g, origen, dest, costo);


    /*cout << "Estructura del GrafoDirigido: " << endl;
    cout << g;

    GrafoD<int> g_reverso;
    g.grafoReverso(g_reverso);
    cout << "Estructura del Grafo Dirigido Reverso: " << endl;
    cout << g_reverso;*/

    return 0;
}

