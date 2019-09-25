#include <iostream>
#include "GrafoND.h"
#include "Sets.h"

using namespace std;

const int inf=1000;

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

int extraermin(int costos[],Sets S, int dim, int vert){
    int c1, c2;
    int m=inf;
    int p;
    c1=S.Find(vert);
    for(int i=1; i<=dim; i++){
        c2=S.Find(i);
        if((c1!=c2)&&(costos[i]<m)){
            p=i;
            m=costos[i];
        }
    }
    c2=S.Find(p);
    S.Union(c1,c2);
    return p;
}

template <typename c>
void dijkstra(GrafoND<c> & g, int vert){

    //DECLARACION:
    int dim=g.cantidadVertices();
    int padres[dim];
    int costos[dim];
    int mat[dim][dim];
    Sets S(dim);

    //INICIALIZACION
    for (int i=1; i<=dim; i++) //matriz
        for (int j=1; j<=dim; j++){
            if (g.existeArco(i,j))
                mat[i][j]=g.costoArco(i,j);
            else
                mat[i][j]=inf;
    }

    //IMPRIMIR MATRIZ
    /*for(int i=1;i<=dim;i++){
        for (int j=1;j<=dim;j++)
            cout << mat[i][j]<< " ";
        cout<<endl;
        }*/

    for(int i=1; i<=dim; i++){//arreglos
        if(i==vert){
            padres[i]=inf;
            costos[i]=inf;
        }
        else
            {
                padres[i]=vert;
                costos[i]=mat[vert][i];
            }
    }

    //IMPRIMIR ARREGLOS:
    /*cout<<"PADRES: "<<endl;
for(int i=1; i<=dim; i++){
    cout<< padres[i]<< " ";
}
cout<<endl;
cout<<"COSTOS: "<<endl;
for(int i=1; i<=dim; i++){
    cout<< costos[i]<< " ";
}
cout<<endl;*/

   //ARRANCA DIJKSTRA:
   int w;
   while (S.Count()>1){
    w=extraermin(costos,S,dim,vert);
    cout<<w;
    for(int j=1; j<=dim; j++){
        if(mat[w][j]+costos[w] < costos[j]){
            padres[j]=w;
            costos[j]=mat[w][j]+costos[w];
        }
    }
   }
    cout<< "ARREGLO DE PADRES: "<<endl;
    for(int i=1; i<=dim; i++)
        cout<<"  "<< padres[i];
    cout<<endl;
    cout<< "ARREGLO DE COSTOS: "<<endl;
    for(int i=1; i<=dim; i++){
        cout<<"  "<< costos[i];
    }

}

int main()
{
    GrafoND<int> g;
    g.agregarVertice(1);
	g.agregarVertice(2);
	g.agregarVertice(3);
	g.agregarVertice(4);
	g.agregarVertice(5);

    // Luego los arcos
	g.agregarArco(1,2,10);
	g.agregarArco(1,5,100);
    g.agregarArco(1,4,30);
	g.agregarArco(2,3,50);
	g.agregarArco(3,5,10);
	g.agregarArco(4,3,20);
	g.agregarArco(4,5,60);

	dijkstra(g, 1);

    return 0;
}
