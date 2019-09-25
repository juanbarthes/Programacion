#ifndef PUNTO_H
#define PUNTO_H



using namespace std;

class Punto {
public:

    Punto(){};
    Punto(int x, int y) { this->x = x; this->y = y; }
    int getX() const { return x; }
    int getY() const { return y; }
    void setX(int x) { this->x = x; }
    void setY(int y) { this->y = y; }
    bool operator < (const Punto & p) const {
        if (x < p.x) { return true; }
            else if (x > p.x) { return false; }
                    else { return y < p.y; } }

    private:
        int x, y;
};

ostream & operator << (ostream & stream, const Punto & punto) {
    return stream << "(" << punto.getX() << "," << punto.getY() <<
    ")";
}

#endif // PUNTO_H
