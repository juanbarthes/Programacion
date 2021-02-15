//FUNCIONES
let message = 'hola mundo';
function printMessageClassic(message) {
    console.log(message);
}

const printMessage = message => (message + ' desde el return automatico');//Declarar la arrow fuction encerrando la unica linea que tiene entre parentesis hace un return automatico

//console.log(printMessage(message));

//OBJETOS

let pet = {
    name: 'rex',
    age: '7',
    race: 'golden retriever'
}

//DESTRUCTURING OBJECTS
//Haciendo esto puedo tomar solo las caracteristicas que me importan de un objeto especifico y guardarlas en otro lado
//const {name, race} = pet;
//console.log(name, race);
//En el siguiente ejemplo hago algo mas complejo
let web = {
    name: 'myWeb',
    socialMedia: {
        facebook: {
            name: 'Juan FB',
            page: 'facebook/juan'
        },
        youtube: {
            name: 'Juan YT',
            page: 'youtube/juan'
        }
    }
}

/*const {name, page} = web.socialMedia.youtube;
console.log(name);
console.log(page);*/

//No puedo darle un nombre a la constante general pero si puedo obtener las propiedades del objeto original y darles un nuevo nombre dentro de mi constante
const {name: youtubeName , page: youtubePage} = web.socialMedia.youtube;//Aca renombre las propiedades para tener nombres mas descriptivos
console.log(youtubeName);
console.log(youtubePage);