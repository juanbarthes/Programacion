#include "Lista.h"
#include <iostream>

using namespace std;

template <typename T>
Lista<T>::Lista()
{
    cursor = ini;
    ini = NULL;
    fin = NULL;
    cont=0;
    costo=0;
}

template <typename T>
void Lista<T>::borrar_lista()
{
    while (cursor!=NULL)
    {
        cursor=cursor->sig;
        delete(ini);
        ini=cursor;
    }
    fin=NULL;
}

template <typename T>
 Lista<T>::~Lista()
{
    borrar_lista();
}

template <typename T>
int Lista<T>::ver_costo() const
{
    return(costo);
}

template <typename T>
void Lista<T>::nuevo_costo()
{
    costo=0;
}

template <typename T>
bool Lista<T>::cursor_vacio() const
{
    return (cursor == NULL);
}

template <typename T>
void Lista<T>::eliminar_nodo(const T & dato)
{
    tlista *aux = cursor;
    tlista *eliminar = aux->sig;
    if ((aux != NULL)&&(aux->var == dato))
    {
        cursor = eliminar;
        delete (aux);
        cont--;
    }
    while((eliminar != NULL) && (eliminar->var != dato))
    {
        aux = aux->sig;
        eliminar = eliminar->sig;
    }
    if (eliminar != NULL)
    {
        aux = eliminar->sig;
        delete (eliminar);
    }
}

template <typename T>
bool Lista<T>::lista_vacia() const
{
    bool resultado = false;
    if (ini == NULL)
        resultado=true;
    return resultado;
}

template <typename T>
int Lista<T>::longlista()const
{
    return cont;
}

template <typename T>
bool Lista<T>::pertenencia(const T & dato)
{
    reiniciar();
    bool resultado=false;
    while ((cursor!=NULL) && (resultado==false))
        {
            costo++;
            if (cursor->var != dato)
            {
                siguiente();
            }
            else
            {
                resultado = true;
            }
        }
    return resultado;
}


template <typename T>
void Lista<T>::reiniciar()
{
    cursor = ini;
}

template <typename T>
void Lista<T>::siguiente()
{
    cursor=cursor->sig;
}

template <typename T>
 const T &Lista<T>::recuperar_var() const
{
    return cursor->var;
}

template <typename T>
void Lista<T>::agregar_principio(const T & dato)
 {
    tlista * newnodo = new tlista;
    newnodo->var=dato;
    newnodo->sig=ini;
    if (lista_vacia())
        fin=newnodo;
    ini=newnodo;
    cont++;
 }

template <typename T>
void Lista<T>::agregar_fin(const T & dato)
 {
    tlista * newnodo = new tlista;
    newnodo->var=dato;
    newnodo->sig=NULL;
    if (lista_vacia())
        {
            ini=newnodo;
            fin=newnodo;
        }
    else
        {
        fin->sig=newnodo;
        fin=newnodo;
        }
    cont++;
 }

 template <typename T>
 void Lista<T>::agregar_posicion(const T & dato, int pos)
 {
    reiniciar();
    if ((pos==0) or (cursor==NULL))
        agregar_principio(dato);
    else
        if (pos==cont-1)
            agregar_fin(dato);
        else
        {
            pos--;
            tlista * newnodo = new tlista;
            newnodo->var=dato;
            newnodo->sig=NULL;
            reiniciar();
            while (pos>0)
                {
                    siguiente();
                    pos--;
                }
            newnodo->sig=cursor->sig;
            cursor->sig=newnodo;
            cont++;
        }
 }

template class Lista <int>;
template class Lista <float>;
template class Lista <string>;
template class Lista <char>;
