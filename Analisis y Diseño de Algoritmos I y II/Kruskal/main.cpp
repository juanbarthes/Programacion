#include <iostream>
#include "GrafoND.h"
#include "Sets.h"

using namespace std;

template<typename C>
ostream & operator << (ostream & salida, const GrafoND<C> & Grafo)
    {
	// Recorremos todos los vertices
	list<int> vertices;
	Grafo.devolverVertices(vertices); // aca puso en una lista todos los vertices del grafo
	list<int>::iterator v = vertices.begin();
	while (v != vertices.end()) {       // recorre toda la lista hasta llegar al ultimo nodo
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


struct aristas{ //este struct se usa para la lista de kruskal

    int origen;
    int destino;
    int costo;
    aristas * sig;
};

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

void imprimir(aristas *lista)
{
    while (lista != NULL)
    {
        cout<<" {"<<lista->origen<<","<<lista->destino<< "}"<<"  "<<lista->costo<<endl;
        lista = lista->sig;
    }
}

//KRUSKAL
template <typename c>
void kruskal(GrafoND<c> & g,GrafoND<c> & X)
    {
       //DECLARACION E INICIALIZACION DE ESTRUCTURAS
       int n = g.cantidadVertices();
       //GrafoND<int> X;
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
      Sets componentes(n);
      int orig, dest;//contienen la etiqueta del vertice origen y el destino respectivamente
      int c1, c2;//se utilizan para localizar las componentes
      while (componentes.Count()>1){
            orig=lista->origen;
            dest=lista->destino;
            c1=componentes.Find(orig);
            c2=componentes.Find(dest);
            if(c1!=c2)
            {
                X.agregarVertice(orig);
                X.agregarVertice(dest);
                X.agregarArco(orig, dest, lista->costo);
                componentes.Union(c1,c2);
            }
            lista = lista->sig;
      }
      // Recorremos todos los vertices
}


int main()
{
    GrafoND<int> g;
    GrafoND<int> X;
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

    kruskal(g,X);

    cout<<X;

    return 0;
}

