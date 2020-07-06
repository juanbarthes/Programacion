"use strict";

let boton = document.querySelector("#crear");
boton.addEventListener("click", crearBotones);


function crearBotones(params) {
    let n = parseInt(document.querySelector("#numero").value);
    for (let index = 0; index < n; index++) {
        let tiempo = Math.random() * 5000;
        setTimeout(() => {
            let botonera = document.querySelector(".botonera")
            let botonNuevo = document.createElement("button");
            botonNuevo.innerHTML = "boton"+(index + 1);
            boton.classList.toggle("js-async");
            botonera.appendChild(botonNuevo);
            botonNuevo.addEventListener("click", function () {
                alert("soy el boton "+(index+1));
            })
        }, tiempo);


    }
}