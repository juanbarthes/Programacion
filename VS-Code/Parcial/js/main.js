"use strict"

document.addEventListener('DOMContentLoaded', iniciarMain);

function iniciarMain() {
    let quejas = [];
    let boton = document.querySelector("#enviar");
    boton.addEventListener('click', registrarQueja);

    let botonQuejas = document.querySelector("#botonQuejas");
    botonQuejas.addEventListener('click', contarQuejas);
    let contador = document.querySelector(".contador");

    function registrarQueja() { //registra una queja.
        let cerveza = document.querySelector("#cerveza").value;
        let queja = document.querySelector("#queja").value;
        if ((cerveza != "") && (queja != "")) {
            let reclamo = {
                "cerveza": cerveza,
                "queja": queja
            }
            quejas.push(reclamo);
        }
    }


    function contarQuejas() { //cuenta las quejas de la cerveza cargada por input. 
        //No pude decifrar mi error pero solo cuenta las quejas de la primera cerveza que ingrese, se va actualizando pero no puedo preguntar por otra, y tiene que estar escrita en el input al mismo tiempo que en el input de creacion de queja.
        let solicitada = document.querySelector("#solicitada").value;
        let nroQuejas = 0;
        for (let index = 0; index < quejas.length; index++) {
            if (quejas[index].cerveza === solicitada) {
                nroQuejas++;
            }
        }
        contador.innerHTML = nroQuejas;
    }

}