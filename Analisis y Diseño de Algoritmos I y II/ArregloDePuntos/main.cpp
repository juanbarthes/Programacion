#include <iostream>
#include "Punto.h"

using namespace std;

int main()
{
    Punto A(1,2);
    Punto B(2,4);
    Punto C(0,0);
    C.setX(A.getX());
    C.setY(A.getY());
    A.setX(B.getX());
    A.setY(B.getY());
    B.setX(C.getX());
    B.setY(C.getY());
    cout<<"Punto A:"<<A<<endl;
    cout<<"Punto B:"<<B<<endl;
    cout<<"Punto C:"<<C<<endl;

    return 0;
}
