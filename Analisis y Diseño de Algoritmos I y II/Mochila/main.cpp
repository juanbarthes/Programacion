#include <iostream>
#include <list>
#include <iterator>
struct objeto{
    int peso;
    int beneficio;
};
using namespace std;



int main()
{
    int capacidad=20;
    objeto objetos[10];
    //inicializo arreglo
    int i=0;
    objetos[i].peso=2;
    objetos[i].beneficio=8;
    i++;
    objetos[i].peso=1;
    objetos[i].beneficio=6;
    i++;
    objetos[i].peso=5;
    objetos[i].beneficio=1;
    i++;
    objetos[i].peso=2;
    objetos[i].beneficio=1;
    i++;
    objetos[i].peso=4;
    objetos[i].beneficio=3;
    i++;
    objetos[i].peso=4;
    objetos[i].beneficio=2;
    i++;
    objetos[i].peso=1;
    objetos[i].beneficio=9;
    i++;
    objetos[i].peso=3;
    objetos[i].beneficio=1;
    i++;
    objetos[i].peso=7;
    objetos[i].beneficio=2;
    i++;
    objetos[i].peso=11;
    objetos[i].beneficio=10;



    list <objeto> mochila;
    list <objeto> :: iterator it;









    return 0;
}
