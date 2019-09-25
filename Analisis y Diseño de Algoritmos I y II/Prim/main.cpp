#include <iostream>
#include "GrafoND.h"
using namespace std;

const int inf=99999999;//es mi discernible.

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

int elegir_minimo(int solucion[], int menorcosto[], int dim){//devuelve la posicion del vertice con menor costo
    int m=inf;
    int p;
    for(int i=1; i<=dim; i++){
        if ((solucion[i]==0)&&(menorcosto[i]<m)){
            p=i;
            m=menorcosto[i];
        }
    }
    return p;
}

template <typename c>
void prim(GrafoND<c> & g, GrafoND<c> & X){

    //DECLARACION DE VARIABLES:
    int dim=g.cantidadVertices();//es la dimension de la matriz y los arreglos.
    int mat[dim][dim];//matriz de costos.
    int solucion[dim];//conjunto solucion: cuando todos los vertices esten en el conjunto el algoritmo termina.
    int padres[dim];//para cada vertice indica su padre.
    int menorcosto[dim];//costo minimo para cada vertice.
    int w;//sirve para seleccionar el vertice de minimo costo.

    //INICIALIZACION DE ESTRUCTURAS:
    for (int i=1; i<=dim; i++) //matriz
    for (int j=1; j<=dim; j++){
        if (g.existeArco(i,j))
            mat[i][j]=g.costoArco(i,j);
        else
            mat[i][j]=inf;
    }

    for(int i=1; i<=dim; i++){//arreglos
        solucion[i]=0;
        padres[i]=1;
        menorcosto[i]=mat[1][i];
    }
    solucion[1]=1;

    //ARRANCA EL ALGORITMO:
    for(int i=2; i<=dim; i++){
        w=elegir_minimo(solucion, menorcosto, dim);
        solucion[w]=1;
        X.agregarVertice(w);
        X.agregarVertice(padres[w]);
        X.agregarArco(padres[w],w,menorcosto[w]);
        for(int j=2; j<=dim; j++){
            if((solucion[j]==0)&&(mat[w][j]<menorcosto[j])){
                padres[j]=w;
                menorcosto[j]=mat[w][j];
            }
        }


    }


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

    prim(g,X);
    cout<<X;

    return 0;
}

