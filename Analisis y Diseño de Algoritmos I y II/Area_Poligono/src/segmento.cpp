#include "segmento.h"

segmento::segmento()
{

}

segmento::segmento(const punto & ext1, const punto & ext2)
{
    this -> ext1 = ext1;
    this -> ext2 = ext2;
}

const punto & segmento::extremo1() const
{
    return ext1;
}

const punto & segmento::extremo2() const
{
    return ext2;
}

punto segmento::medio()
{
    punto media;
    media.centro(ext1, ext2);
    return media;
}

float segmento::longitud() const
{
    return ext1.distancia(ext2);
}

void segmento::trasladar(float x, float y)
{
    ext1.trasladar(x,y);
    ext2.trasladar(x,y);
}

bool segmento::operator==(const segmento & otrosegmento) const
{
    return (ext1 == otrosegmento.ext1) && (ext2 == otrosegmento.ext2);
}

bool segmento::operator!=(const segmento & otrosegmento) const
{
    return (ext1 == otrosegmento.ext1) || (ext2 == otrosegmento.ext2);
}

segmento::~segmento()
{

}
