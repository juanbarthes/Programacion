#include <iostream>
#ifndef ARBOL_H
#define ARBOL_H
#include "Lista.h"


using namespace std;

template <typename T>

class Arbol
{
    private:
        struct tarb // estructura del nodo del arbol.
        {
            T dato;
            tarb *left;
            tarb *right;
        };

        tarb *raiz; // apunta siempre al primer elemento del arbol.
        int cont; // posee todo momento la cantidad de elemento del arbol.
        int costo; // sirve para obtener la cantidad de operaciones de la busqueda de un elemento.

        void crear_nodo(tarb * & , const T &); // crea un nodo nuevo donde se agrega el elemento.
        bool busqueda(tarb *, const T &); // busca el elemento en el arbol y devuelve true si existe y sino un false.
        void insertar(tarb * &, tarb *); // el nuevo nodo creado con el elemento lo agregan en el arbol.
        void armar_lista(tarb *, Lista<T> & ); // va colocando los elemento del arbol en una lista.


    public:
        Arbol(); // es el constructor de la clase.
        ~Arbol(); // es el destructor de la clase.
        void eliminar(); // elimina todo el arbol.
        bool es_vacio() const; // verifica si el arbol esta vacio o no.
        int tamanio() const; // devuelve el tamanio del arbol.
        bool pertenece(const T &); // verifica si un elemento de existe o no dentro del arbol.
        void agregar(const T &); // agrega un nuevo elemento en el arbol.
        void listar(Lista <T> & ); // todos los elemento del arbol, los agrega en una lista.
        void borrar_arbol(tarb *); // borra todo el arbol.
        int ver_costo() const; // te devuelve un numero entero que es la cantidad de veces que fue buscando si la palabra estaba.
        void nuevo_costo(); // retorna un 0 para que despues en la siguiente palabra pueda buscar su costo.
};

#endif // ARBOL_H
