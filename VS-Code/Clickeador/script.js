document.addEventListener("DOMContentLoaded", iniciarPagina);
function iniciarPagina(event){
    "use strict";
    let btn = document.getElementById("btn-click");
    btn.addEventListener("click", clickear);

    let contador = 0;

    function clickear(){
        contador++;
        let valor = document.getElementById("span contador");
        valor.innerHTML = contador;
    }
}