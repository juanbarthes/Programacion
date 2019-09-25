#include "punto.h"
#include "cmath"

punto::punto()
{

}

punto::punto(float x , float y)
{
    this -> x = x;
    this -> y = y;
}

float punto::coordx() const
{
    return x;
}

float punto::coordy() const
{
    return y;
}

float punto::distancia(const punto & otropunto) const
{
    return sqrt(pow(x-otropunto.x, 2.0)+ pow(y-otropunto.y, 2.0));
}

void punto::centro(punto p1, punto p2)
{
    // Calculo la media distancia entre ambos puntos
    this -> x = ((p2.coordx() - p1.coordy())/2);
    this -> y = ((p2.coordy() - p2.coordy())/2);
}

void punto::modx(float x){
    this->x=x;
}

void punto::mody(float y){
    this->y=y;
}

void punto::trasladar(float x, float y)
{
    this -> x +=x;
    this -> y +=y;
}

bool punto::operator==(const punto & otropunto) const
{
    return (x==otropunto.x) && (y == otropunto.y);
}

bool punto::operator!=(const punto & otropunto) const
{
    return (x == otropunto.x) || (y == otropunto.y);
}

punto::~punto()
{

}
