#ifndef SETS_H
#define SETS_H


class Sets {
private:
    int *p;
    int count; // almacena la cantidad de conjuntos

public:

    Sets (int n);
    ~ Sets ();
    int Count ( );
    bool Connected (int i, int j);
    void Union (int i, int j);
    int Find (int j);

};

#endif // SETS_H
