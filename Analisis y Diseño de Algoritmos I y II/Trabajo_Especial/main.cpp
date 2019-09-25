#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <fstream>
#include "Lista.h"
#include "Arbol.h"

using namespace std;


void cargar_coleccion_arbol(string path, Arbol<string> & A);
void buscar_palabra_arbol(string pathBusqueda, string pathResultado, Arbol<string> & A);
void cargar_coleccion_lista(string path, Lista<string> & L);
void buscar_palabra_lista(string pathBusqueda, string pathResultado, Lista<string> & L);

int main()
{
    ////////////// BUSQUEDA DE PALABRAS CON LA CLASE DE ARBOL /////////////

    Arbol<string> A;
    Lista<string> L;

    cargar_coleccion_arbol("Datasets\\Coleccion_1.txt", A);
    buscar_palabra_arbol("Datasets\\Busqueda_1.txt", "Resultados\\resultado_1_Arbol.txt", A);
    A.eliminar();

    cargar_coleccion_arbol("Datasets\\Coleccion_2.txt", A);
    buscar_palabra_arbol("Datasets\\Busqueda_2.txt", "Resultados\\resultado_2_Arbol.txt", A);
    A.eliminar();

    cargar_coleccion_arbol("Datasets\\Coleccion_3.txt", A);
    buscar_palabra_arbol("Datasets\\Busqueda_3.txt", "Resultados\\resultado_3_Arbol.txt", A);
    A.eliminar();

    ////////////// BUSQUEDA DE PALABRAS CON LA CLASE DE LISTA /////////////

    cargar_coleccion_lista("Datasets\\Coleccion_2.txt", L);
    L.reiniciar();
    buscar_palabra_lista("Datasets\\Busqueda_2.txt", "Resultados\\resultado_2_Lista.txt", L);
    L.borrar_lista();

    cargar_coleccion_lista("Datasets\\Coleccion_3.txt", L);
    L.reiniciar();
    buscar_palabra_lista("Datasets\\Busqueda_3.txt", "Resultados\\resultado_3_Lista.txt", L);
    L.borrar_lista();

    cargar_coleccion_lista("Datasets\\Coleccion_1.txt", L);
    L.reiniciar();
    buscar_palabra_lista("Datasets\\Busqueda_1.txt", "Resultados\\resultado_1_Lista.txt", L);
    L.borrar_lista();

    return 0;
}

/////////////////////////////// ACA EMPIEZA LOS PROCEDIMIENTO CON EL ARBOL ////////////////////////////////////////////////

void cargar_coleccion_arbol(string path, Arbol<string> & A)
//este es el procedimiento que carga todas las palabras del archivo en un arbol.
{
    ifstream origen(path.c_str());
    if (!origen.is_open())
        cout << "No se pudo abrir el archivo: " << path << endl;
    else {
        while (!origen.eof())
        {
            string palabra;
            origen >> palabra;
            A.agregar(palabra);
        }
    }
}

void buscar_palabra_arbol(string pathBusqueda, string pathResultado, Arbol<string> & A)
// este es el procedimiento que busca la palabra en el arbol y te devuelve en un archivo donde dice si existe o no dicha palabra y su costo.
{
    ifstream busqueda(pathBusqueda.c_str());
    if (!busqueda.is_open())
        cout << "No se pudo abrir el archivo: " << pathBusqueda << endl;
    else {
        ofstream resultado(pathResultado.c_str(), ios::trunc);
        while (!busqueda.eof())
        {
            string palabra;
            A.nuevo_costo();
            busqueda >> palabra;
            resultado << palabra << ", " << ((A.pertenece(palabra))? "existe, " : "no existe, ");
            resultado << "costo: " << A.ver_costo() << endl; //Escribo el costo en el archivo en otra linea porque sino no funciona.

        }
    }
}

/////////////////////////////// ACA EMPIEZA LOS PROCEDIMIENTO CON LA LISTA ////////////////////////////////////////////////

void cargar_coleccion_lista(string path, Lista<string> & L)
//este es el procedimiento que carga todas las palabras del archivo en una lista.
 {
    ifstream origen(path.c_str());
    if (!origen.is_open())
        cout << "No se pudo abrir el archivo: " << path << endl;
    else
    {
        while (!origen.eof())
        {
            string palabra;
            origen >> palabra;
            L.agregar_fin(palabra);
        }
    }
}

void buscar_palabra_lista(string pathBusqueda, string pathResultado, Lista<string> & L)
// este es el procedimiento que busca la palabra en la lista y te devuelve en un archivo donde dice si existe o no dicha palabra y su costo.
{
    ifstream busqueda(pathBusqueda.c_str());
    if (!busqueda.is_open())
        cout << "No se pudo abrir el archivo: " << pathBusqueda << endl;
    else {
        ofstream resultado(pathResultado.c_str(), ios::trunc);
        while (!busqueda.eof())
        {
            string palabra;
            L.nuevo_costo();
            busqueda >> palabra;
            resultado << palabra << ", " << ((L.pertenencia(palabra))? "existe, " : "no existe, ");
            resultado << "costo: " << L.ver_costo() << endl; //Escribo el costo en el archivo en otra linea porque sino no funciona.

        }
    }
}
