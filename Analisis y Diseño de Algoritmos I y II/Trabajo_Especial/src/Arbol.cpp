#include <iostream>
#include "Arbol.h"
#include "Lista.h"

using namespace std;

template <typename T>
Arbol<T>::Arbol()
{
    cont=0;
    raiz= NULL;
}

template <typename T>
void Arbol<T>::borrar_arbol(tarb *punt)
{
    if (punt!=NULL)
    {
        borrar_arbol(punt->left);
        borrar_arbol(punt->right);
        delete(punt);
    }

}

template <typename T>
Arbol<T>::~Arbol()
{
    tarb *punt=raiz;
    borrar_arbol(punt);
    raiz=NULL;
}

template <typename T>
void Arbol<T>::eliminar()
{
    tarb *punt=raiz;
    borrar_arbol(punt);
    raiz=NULL;
}

template <typename T>
int Arbol<T>::ver_costo() const
{
    return(costo);
}

template <typename T>
void Arbol<T>::nuevo_costo()
{
    costo=0;
}

template <typename T>
bool Arbol<T>::es_vacio() const
{
    return (cont==0);
}

template <typename T>
int Arbol<T>::tamanio() const
{
    return cont;
}

template <typename T>
bool Arbol<T>::busqueda(tarb *punt, const T &dato)
// busqueda es llamado por pertence para verificar si elemento hiciste y ademas
// cuenta la cantidad operaciones que tuvo que hacer para encontrar la palabra.
{
    costo++;
    if (punt==NULL)
        return false;
    else
        {
            if (punt->dato==dato)
                return true;
            else
                {
                    if (punt->dato > dato)
                        return busqueda(punt->left, dato);
                    else
                        return busqueda(punt->right, dato);
                }
        }
}

template <typename T>
bool Arbol<T>::pertenece(const T & dato)
// verifica si el elemento existe en el arbol.
{
    tarb *punt=raiz;
    return busqueda(punt,dato);
}

template <typename T>
void Arbol<T>::crear_nodo(tarb * & nodo, const T & dato)
// es llamado por agregar para que crea un nuevo nodo con un elemento.
{
    nodo->dato=dato;
    nodo->left=NULL;
    nodo->right=NULL;
}

template <typename T>
void Arbol<T>::insertar(tarb * & punt, tarb * nodo)
// es llamado por agregar y se encarga de meter el nuevo nodo en el arbol
{
    if (punt==NULL)
        punt=nodo;
    else
        if (nodo->dato < punt->dato)
            insertar(punt->left, nodo);
        else
            insertar(punt->right, nodo);
}

template <typename T>
void Arbol<T>::agregar(const T & dato)
// agrega un nuevo eleemnto emn el arbol y ademas actualiza el contador.
{
 if (!pertenece(dato))
    {
    tarb *nodo= new tarb;
    tarb *punt=raiz;
    crear_nodo(nodo, dato);
    if (raiz == NULL)
        raiz = nodo;
    else
    insertar(punt, nodo);
    cont++;
    }
}

template <typename T>
void Arbol<T>::armar_lista(tarb *punt, Lista<T> & lista)
// es llamado por listar para poder agregar el elemento en una lista.
{
    if (punt!=NULL)
    {
        armar_lista(punt->left, lista);
        lista.agregar_fin(punt->dato);
        armar_lista(punt->right, lista);
    }
}

template <typename T>
void Arbol<T>::listar(Lista<T> & lista)
{
    tarb *punt=raiz;
    armar_lista(punt, lista);
}


template class Arbol <int>;
template class Arbol <float>;
template class Arbol <string>;
template class Arbol <char>;

