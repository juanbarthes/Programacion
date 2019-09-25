#include <iostream>
#include <cstdio>
#include <string>
using namespace std;

void rabinKarp(string str, string pat) {
    if (str.length() && pat.length()) {// comprueba que el Texto y el Patron esten cargados.
        int n = str.length();
        int m = pat.length();
        int i, j;
        int t0 = 0; //Valor numerico para T.
        int p = 0;  //Valor numerico para P.
        const int pm = 256; // Numero de caracteres del alfabeto.
        const int q = 101;  // Uso un numero primo grande para asegurar que el valor numerico nunca va a exeder un byte.
        int h = 1;
        bool encontrado = false;

        // El valor de h debe ser igual a pow(pm,m-1).
        for (i = 0; i < m-1; i++)
            h = (h * pm) % q;

        //Calcula el valor numerico de T y P.
        for (i = 0; i < m; i++) {
            t0 = (pm * t0 + str[i]) % q;
            p = (pm * p + pat[i]) % q;
        }

        for (i = 0; i <= n-m; i++) {
            if (t0 == p) {// si t0==p se comparan los caracteres de T y P uno por uno
                for (j = 0; j < m; j++)
                    if (str[i+j] != pat[j])
                        break;
                if (j == m) {
                    cout<<"Se encontro el Patron a partir de la posicion: "<<i+1<<endl;
                    encontrado = true;
                }
            }
            //Calcula el valor numerico del siguiente desplazamiento
            t0 = (pm * (t0 - h * str[i]) + str[i+m]) % q;
            if (t0 < 0)
                t0= t0 + q;
        }
        if (!encontrado)
            cout<<"El Patron no fue encontrado"<<endl;
        return;
    }
    cout<<"El Patron debe estar cargado"<<endl;
    return;
}

int main() {
    string T, P;//T es el texto de entrada y P es el patron de buqueda.
    cout<<"Ingrese la cadena de Texto (T)"<<endl;
    cin>>T;
    cout<<"Ingrese el Patron que desea buscar (P)"<<endl;
    cin>>P;
    rabinKarp(T, P);
    return 0;
}
