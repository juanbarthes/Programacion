#ifndef SEGMENTO_H
#define SEGMENTO_H
#include "punto.h"


class segmento
{
    public:
        segmento(); // constructor
        segmento(const punto & ext1, const punto & ext2); // constructora basica
        const punto & extremo1() const; // observadora
        const punto & extremo2() const; // observadora
        punto medio();
        float longitud () const; // observadora
        void trasladar(float x, float y);
        bool operator==(const segmento & otrosegmento) const; // observadora
        bool operator!=(const segmento & otrosegmento) const; // observadora
        virtual ~segmento(); // destructor

    private:
        punto ext1;
        punto ext2;
};

#endif // SEGMENTO_H
