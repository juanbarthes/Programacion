"use strict";

let objeto = {
    "cursos": [
        {
            "sede": "tandil",
            "año": 2020
        },
        {
            "sede": "rauch",
            "año": 2020
        }
    ]
}


function imprimirObjeto(objeto) {
    for (let index = 0; index < objeto.cursos.length; index++) {
        console.log(objeto.cursos[index].sede);
        console.log(objeto.cursos[index].año);
    }
}

imprimirObjeto(objeto);

