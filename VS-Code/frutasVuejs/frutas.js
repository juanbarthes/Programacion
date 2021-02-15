const app = new Vue({
    el: '#app',
    data: {
        fruits : [{
                name: 'Manzana',
                quantity: 20
            },
            {
                name: 'Naranja',
                quantity: 50
            },
            {
                name: 'Mandarina',
                quantity: 30
            },
        ],
        newFruit: ''
    },
    methods: {
        addFruit(){
            console.log('doste click');
            const name = document.querySelector('#fruitName').value;
            const quantity = document.querySelector('#fruitQuantity').value;
            console.log(name);
            console.log(quantity);
            this.newFruit = {name: name, quantity: quantity}
            this.fruits.push(this.newFruit);
        }
    }
})