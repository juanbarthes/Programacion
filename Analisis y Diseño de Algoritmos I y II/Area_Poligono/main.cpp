#include <iostream>
#include "punto.h"
#include "segmento.h"
#include <list>
#include "iterator"
using namespace std;

float area_triangulo(punto p1, punto p2){
float crossproduct=(p1.coordx()*p2.coordy())- (p2.coordx()*p1.coordy());
return crossproduct;
}

float areaPoligono( punto poligono[], punto origen, int dim){
    float area=0;
    float product=0;
    int i;
    for(i=0;i<dim-1;i++){
        punto a((poligono[i].coordx()-origen.coordx()),(poligono[i].coordy()-origen.coordy()));
        punto b((poligono[i+1].coordx()-origen.coordx()),(poligono[i+1].coordy()-origen.coordy()));
        product=area_triangulo(a,b);
        area=area+product;
    }
        punto a((poligono[i].coordx()-origen.coordx()),(poligono[i].coordy()-origen.coordy()));
        punto b((poligono[0].coordx()-origen.coordx()),(poligono[0].coordy()-origen.coordy()));
        product=area_triangulo(a,b);
        area=area+product;
        area=area/2;
        return area;
    }



int main(){
int dim=5;//este valor debe ser igual a la cantidad de putos ingresados para crear el poligono.
punto p1(1,1);
punto p2(5,1);
punto p3(5,5);
punto p4(3,7);
punto p5(1,5);
punto poligono[dim]={p1,p2,p3,p4,p5};
punto origen(-500,-7000);//cambiar el punto origen a su antojo para comprobar como calcula el area
float Area=areaPoligono(poligono,origen,dim);
cout<<"el area del poligono es: "<<Area<<endl;
return 0;
}
