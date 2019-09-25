#include <iostream>
#ifndef LISTA_H
#define LISTA_H

using namespace std;

template <typename T>
class Lista
{
    public:

        Lista(); //constructor de la clase.
        ~Lista(); // destructor de la clase.

        bool lista_vacia() const; //retorna verdadero si la lista no tiene elementos.
        int longlista() const; //retorna la cantidad de nodos de la lista.
        bool pertenencia(const T &); //comprueba si algun nodo de la lista contiene el elemento solicitado.

        int ver_costo() const ; // te devuelve un numero entero que es la cantidad de veces que fue buscando si la palabra estaba o no.
        void nuevo_costo(); // retorna un 0 para que despues en la siguiente palabra pueda buscar su costo.

        bool cursor_vacio() const; // verifica si el cursor esta apuntando a NULL o no.
        const T &recuperar_var() const; //accede al valor alojado en el nodo.
        void siguiente();//accede al puntero siguiente alojado dentro del nodo.
        void reiniciar();//Vuelve a apuntar el cursor al primer nodo de la lista.
        void agregar_principio(const T &); // agrega un elemento solo al principio de la lista.
        void agregar_fin(const T &); // agrega un elemento solo al final de la lista.
        void agregar_posicion(const T &, int); //inserta un elemento en la posicion solicitada.

        void eliminar_nodo(const T &); //elimina el nodo donde se encuentra el elemento a eliminar.
        void borrar_lista(); // borra toda la lista.

    private:

        struct tlista // estructura del nodo de la lista
        {
            T var;
            tlista *sig;
        };

        tlista * ini;
        tlista * fin;
        tlista * cursor;

        int cont;
        int costo;

};

#endif // LISTA_H
