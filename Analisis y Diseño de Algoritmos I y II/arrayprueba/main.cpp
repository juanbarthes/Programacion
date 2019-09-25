#include <iostream>

using namespace std;

void imprimir(int array1[],int array2[]){
    cout<<"array1:"<<endl;
    cout<<endl;
    for(int i=0;i<10;i++)
        cout<<array1[i]<<"  ";
    cout<<endl;
    cout<<"array2:"<<endl;
    for(int i=0;i<10;i++)
        cout<<array2[i]<<"  ";
    cout<<endl;
}

void cargar(int array1[],int array2[]){
    for(int i=0;i<10;i++){
        array1[i]=i+2;
        array2[i]=i*2;
    }
}

void intercambiar(int array1[], int array2[]){
    int aux;
    for(int i=0;i<10;i++){
        aux=array1[i];
        array1[i]=array2[i];
        array2[i]=aux;
    }
}

int main()
{
    int array1[10];
    int array2[10];
    cargar(array1,array2);
    imprimir(array1,array2);
    intercambiar(array1,array2);
    imprimir(array1,array2);
    return 0;
}
