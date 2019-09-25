#include <iostream>
#include <Punto.h>
#include <stack>
using namespace std;


bool izquierda(Punto next, Punto top, Punto pi){//me dice para que lado giro v2
    bool result=false;
    int product;
    Punto V1(pi.getX()-next.getX(),pi.getY()-next.getY());
    Punto V2(top.getX()-next.getX(),top.getY()-next.getY());
    product=((V1.getX()*V2.getY())-(V2.getX()*V1.getY()));
    if(product<0)
        result=true;
    return result;
}

void intercalar(Punto arr[],Punto p0, unsigned int inicio, unsigned int medio, unsigned int fin)
{
    //cout <<"arranca intercxalar"<<endl;
    Punto aux [fin];
    unsigned int i,j,k;
    for (i = medio+1;i>inicio;i--){
        aux[i-1].setX(arr[i-1].getX());
        aux[i-1].setY(arr[i-1].getY());
    }
    for (j=medio;j<fin;j++){
        aux[fin+medio-j].setX(arr[j+1].getX());
        aux[fin+medio-j].setY(arr[j+1].getY());
    }
    cout<<"Arreglo Auxiliar"<<endl;
    for(int x=0;x<fin;x++){
    cout<<aux[x];
    }
    cout <<endl;
    for (k=inicio;k<=fin;k++){
        if(izquierda(p0,aux[i],aux[j]))
            {
                arr[k].setX(aux[i++].getX());
                arr[k].setY(aux[i++].getY());
            }
         else
            {
                arr[k].setX(aux[j--].getX());
                arr[k].setY(aux[j--].getY());
            }

    }
}

void merge_sort(Punto arr[],Punto p0, int inicio, int fin)
{
    //cout <<"arranca merge"<<endl;
    if (inicio<fin)
    {
        unsigned int mitad= ((inicio+fin)/2);
        merge_sort(arr,p0,inicio,mitad);
        merge_sort(arr,p0,mitad+1,fin);
        intercalar(arr,p0,inicio,mitad,fin);
    }
}

void Seleccion(Punto arr[], int dim){
    int aux=0;
    for(int i=1;i<dim;i++){
        if(arr[i].getY()<arr[aux].getY())
            aux=i;
        else
            if((arr[i].getY()==arr[aux].getY())&&(arr[i].getX()<arr[aux].getX()))
                aux=i;
    }
    if(aux!=0){
        Punto p(arr[aux].getX(),arr[aux].getY());
        arr[aux].setX(arr[0].getX());
        arr[aux].setY(arr[0].getY());
        arr[0].setX(p.getX());
        arr[0].setY(p.getY());
    }
}

Graham_Scan(Punto arr[], int dim, stack <Punto> & pila){
    Seleccion(arr,dim);
    Punto p0(arr[0].getX(),arr[0].getY());
    cout<<"P0 "<<p0<<endl;
    merge_sort(arr,p0,1,dim-1);
    cout<<"arreglo puntos ordenado: "<<endl;
    for(int x=0;x<dim;x++)
        cout<<arr[x];
    cout<<endl;
    cout<<endl;
    pila.push(arr[0]);
    pila.push(arr[1]);
    Punto next(arr[1].getX(),arr[1].getY());
    pila.push(arr[2]);
    for(int i=3;i<dim;i++){
        while(!izquierda(next,pila.top(),arr[i])){
            pila.pop();
            Punto top(pila.top().getX(),pila.top().getY());
            pila.pop();
            next.setX(pila.top().getX());
            next.setY(pila.top().getY());
            pila.push(top);
        }
        pila.push(arr[i]);
        }

    }


int main()
{
    stack <Punto> pila;
    int dim=10;
    Punto p1(10,2);
    Punto p2(2,3);
    Punto p3(9,6);
    Punto p4(2,1);
    Punto p5(1,3);
    Punto p6(4,3);
    Punto p7(4,7);
    Punto p8(11,6);
    Punto p9(6,7);
    Punto p10(8,8);

    Punto arr [dim]={p1,p2,p3,p4,p5,p6,p7,p8,p9,p10};
    Graham_Scan(arr, dim, pila);
    while(!pila.empty()){
        cout<<pila.top()<<endl;
        pila.pop();
    }
    return 0;
}
