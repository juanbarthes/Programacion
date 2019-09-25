#ifndef PUNTO_H
#define PUNTO_H


class punto
{
    public:
        punto(); // constructor
        punto(float x,float y); // constructora basica
        float coordx () const; // observadora
        float coordy () const; // observadora
        float distancia(const punto & otropunto) const; // observadora
        void centro(punto p1, punto p2); // modificadora
        void modx(float x); //modificadora
        void mody(float y); //modificadora
        void trasladar(float x, float y); //modificadora
        bool operator == (const punto & otropunto) const;// observadora
        bool operator != (const punto & otropunto) const;
        virtual ~punto(); // destructor

    private:
        float x;
        float y;
};

#endif // PUNTO_H
