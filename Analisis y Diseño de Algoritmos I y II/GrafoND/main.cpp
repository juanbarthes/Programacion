#include <iostream>
#include "GrafoND.h"
#include "sets.h"

using namespace std;

//declaro variables globales

int time=0;
int v;
int dim;
int * des;
int * fin;
char * marcas;
int * padres;

struct aristas{ //este struct se usa para la lista de kruskal

    int origen;
    int destino;
    int costo;
    aristas * sig;
};

template<typename C>
ostream & operator << (ostream & salida, const GrafoND<C> & Grafo)
    {
	// Recorremos todos los vertices
	list<int> vertices;
	Grafo.devolverVertices(vertices);
	list<int>::iterator v = vertices.begin();

	while (v != vertices.end()) {
		salida << "    " << *v << "\n";
		// Recorremos todos los adyacentes de cada vertice
		list< typename GrafoND<C>::Arco> adyacentes;
		Grafo.devolverAdyacentes(*v, adyacentes);
		typename list< typename GrafoND<C>::Arco>::iterator ady = adyacentes.begin();
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
void DFS (GrafoND<C>& g, int origen){
    time++;
    des[origen] = time;
    marcas[origen]= 'g';
    list< typename GrafoND<C>::Arco> adyacentes;
    g.devolverAdyacentes(origen, adyacentes);
    typename list< typename GrafoND<C>::Arco>::iterator ady = adyacentes.begin();
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
void DFS_destino (GrafoND<C>& g, int origen, int destino, int costo){
    marcas[origen]= 'g';//MARCO EN GRIS DONDE ESTOY PARADO
    if (origen!=destino){//SI NO LLEGUE A DESTINO ENTRO AL IF
        list< typename GrafoND<C>::Arco> adyacentes;//DECLARO UNA LISTA PARA CONTENER LOS ADYACENTES DEL VERTICE EN EL QUE ESTOY PARADO
        g.devolverAdyacentes(origen, adyacentes);//CARGO LA LISTA
        typename list< typename GrafoND<C>::Arco>::iterator ady = adyacentes.begin();//DECLARO UN ITERADOR
        while (ady != adyacentes.end()) {//POR CADA ADYACENTE CON MARCA BLANCA EJECUTO DFS

            v=ady->devolverAdyacente();
            if (marcas[v]=='b'){
                padres[v]=origen;
                DFS_destino(g, v, destino, costo+g.costoArco(origen,v));
            }
            ady++;
        }
    }
    else //SI LLEGO A ORIGEN ENTRO AL ELSE
        {
            cout << "EL COSTO TOTAL DE ESTE CAMINO ES: " << costo << endl;
            cout << "EL CAMINO ES: " << endl;
    int i=origen;
    cout << origen;
    while (padres[i]!=-1){ //IMPRIMO EL ARREGLO DE PADRES
        cout << "<--" << padres[i];
        i=padres[i];
    }
    cout << endl;

}
    marcas[origen]='b'; //VUELVO A MARCAR EN BLANCO LOS VERTICES
}
template<typename C>
void DFS_Forest (GrafoND<C>&g){
// escriba aquí su código
}

//para la lista de kruskal

void insertar_ordenado(aristas * &lista, aristas * nodo){
    if (lista==NULL)
        lista=nodo;
    else
    if (nodo->costo < lista->costo){
        nodo->sig=lista;
        lista=nodo;
    }
    else
        insertar_ordenado(lista->sig, nodo);
}

//KRUSKAL
    template <typename c>
    void kruskal(GrafoND<c> & g, GrafoND<c> & X)
    {
       //DECLARACION E INICIALIZACION DE ESTRUCTURAS
       aristas *lista=NULL;//la lista ordena las aristas de menor a mayor segun el costo
       list<int> vertices;
       g.devolverVertices(vertices);
       list<int>::iterator v;
       for (v=vertices.begin(); v!=vertices.end(); v++){//por cada vertice creo su propia lista de adyacentes
            list <typename GrafoND<c>::Arco> adyacentes;
            g.devolverAdyacentes(*v, adyacentes);
            typename list <typename GrafoND<c>::Arco>::iterator ady;
            for (ady=adyacentes.begin(); ady != (adyacentes.end()); ady++){//por cada adyacente del vertice inserto un nodo ordenado en la lista de costos
                aristas *aux= new aristas();
                aux->origen= *v;
                aux->destino=ady->devolverAdyacente();
                aux->costo=ady->devolverCosto();
                aux->sig=NULL;
                insertar_ordenado(lista, aux);
            }


       }

      //CON LA LISTA DE KRUSKAL CARGADA EMPIEZA A TRABAJAR EL ALGORITMO
      int dim=g.cantidadVertices();
      Sets componentes(dim);
      aristas * cursor=lista;
      int orig, dest;//contienen la etiqueta del vertice origen y el destino respectivamente
      int c1, c2;//se utilizan para localizar las componentes
      while (componentes.Count()>1){
            orig=lista->origen;
            dest=lista->destino;
            c1=componentes.Find(orig);
            c2=componentes.Find(dest);
            if(c1=!c2){
                X.agregarVertice(orig);
                X.agregarVertice(dest);
                X.agregarArco(orig, dest, lista->costo);
                componentes.Union(c1, c2);
            }
            cursor=cursor->sig;
      }
      // Recorremos todos los vertices
	list<int> verticesX;
	X.devolverVertices(verticesX);
	list<int>::iterator it = verticesX.begin();

	while (it != verticesX.end()) {
		cout << "    " << *it << "\n";
		// Recorremos todos los adyacentes de cada vertice
		list< typename GrafoND<c>::Arco> adyacentes;
		X.devolverAdyacentes(*v, adyacentes);
		typename list< typename GrafoND<c>::Arco>::iterator ady = adyacentes.begin();
		while (ady != adyacentes.end()) {
			cout << "   " <<  *v << "->"  << ady->devolverAdyacente() << " (" << ady->devolverCosto () << ")\n";
			ady++;
		}
		v++;


    }
    }

//-----------------   M A I N  ------------------

int main(int argc, char **argv)
{

	GrafoND<int> g;
    GrafoND<int> X;//este grafo sirve para contener el nuevo arbol de recubrimiento minimo

	// Cargamos un GrafoNo Dirigido dirigido
	// Primero los vértices
	g.agregarVertice(1);
	g.agregarVertice(2);
	g.agregarVertice(3);
	g.agregarVertice(4);
	g.agregarVertice(5);
	g.agregarVertice(6);

    // Luego los arcos
	g.agregarArco(1,2,6);
	g.agregarArco(1,3,1);
	g.agregarArco(1,4,5);
	g.agregarArco(2,3,5);
	g.agregarArco(2,5,3);
	g.agregarArco(5,3,6);
	g.agregarArco(5,6,6);
	g.agregarArco(6,3,4);
    g.agregarArco(6,4,2);
    g.agregarArco(4,3,5);


	//inicializacion de los arreglos
	/*dim = g.cantidadVertices();
	cout<<"LA CANTIDAD DE VERTICES ES: ";
	cout << dim << endl;
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
    //DFS_destino(g, origen, dest, costo);*/


    cout << "Estructura del GrafoNDirigido: " << endl;
    cout << g;
    kruskal(g, X);
    cout<< "ESTRUCTURA DEL GRAFO MINIMO: " <<endl;
    cout << X;
    return 0;


}

