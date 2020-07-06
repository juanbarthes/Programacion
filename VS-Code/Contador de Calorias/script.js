document.addEventListener("DOMContentLoaded", iniciarPagina)

function iniciarPagina() {
    "use strict";
    let calorias = 0;

    let btnSumar = document.getElementById("suma");
    btnSumar.addEventListener("click", function () {
        sumarCantidad(+1)
    });

    let btnRestar = document.getElementById("resta");
    btnRestar.addEventListener("click", function () {
        sumarCantidad(-1)
    });

    let btnInput = document.getElementById("sumarInput");
    btnInput.addEventListener("click", sumarInput);

    sumarCantidad(0); /*fuerzo actualizacion iniciar*/

    function sumarInput() {
        let entrada = document.getElementById("entrada");
        let valor = parseInt(entrada.value);
        sumarCantidad(valor);
    }

    function sumarCantidad(nuevas) {
        let total = document.getElementById("txtTotal");
        calorias += nuevas;
        total.innerHTML = calorias;
    }
}