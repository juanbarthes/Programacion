document.addEventListener("DOMContentLoaded", function (e) {

            let baseUrl = "http://web-unicen.herokuapp.com/api/groups/";
            let grupo = 'prueba09';
            let coleccion = 'termos';

            // let ej = baseUrl + grupo + "/" + coleccion;
            // console.log(ej);

            function enviar(m, mo, c, ca) {
                let termo = {
                    "thing": {
                        "marca": m,
                        "modelo": mo,
                        "color": c,
                        "capacidad": ca
                    }
                };


                fetch(baseUrl + grupo + "/" + coleccion, {
                        "method": "POST",
                        "mode": "cors",
                        "headers": {
                            "Content-Type": "application/json"
                        },
                        "body": JSON.stringify(termo)
                    }).then(function (response) {
                        if (!response.ok) {
                            console.log('error');
                        }
                        return response.json();
                    }).then(function (rtaJson) {
                        console.log(rtaJson);
                    }).catch(function (e) {}}