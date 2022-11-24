const app = Vue.createApp({
    data() {
        return {
            shoppingCart: [],
            totalAmount: 0,
            paymentMethodVModel: "",
            idProduct: 0,
        }
    },
    created() {  
    },
    mounted() {
  
        let currentCart = JSON.parse(localStorage.getItem('cart'))
        if(!currentCart) {
            this.shoppingCart = []
        } else {
            this.shoppingCart = currentCart
        }
  
        let currentTotalAmount = JSON.parse(localStorage.getItem('totalAmount'))
        if(currentTotalAmount != 0){
            this.totalAmount = currentTotalAmount
        }
    },
    methods: {
        finalAmount() {
            this.totalAmount = 0
            this.shoppingCart.map(product => {
                let addition = product.quantity * product.price
                this.totalAmount += addition
            })
            localStorage.setItem('totalAmount', JSON.stringify(this.totalAmount))
        },
        cartStorage(){
            localStorage.setItem('cart', JSON.stringify(this.shoppingCart))
            this.finalAmount()
        },
        addProductToShoppingCart(selectProduct){
  
                let repeatedProduct = this.shoppingCart.filter(product => product.id == selectProduct.id)
  
                if (repeatedProduct.length > 0) {
                    // CASO EN EL QUE ESTA EL ELEMENTO YA EN EL CARRITO
                    this.shoppingCart.filter(item => {
                        if (item.id == selectProduct.id) {
                            if (item.stock > 0) {
                                item.quantity++
                                item.stock--
                            }
                        }
                    })
                    this.cartStorage()
                }
                else {
                    // CASO EN EL QUE NO ESTA
                    this.productsBackUp.filter(product => {
                        if (product.id == selectProduct.id) {
                                product.stock--
                        }
                    })
                    this.shoppingCart.push(selectProduct)
                    this.cartStorage()
                }
  
                //localStorage.setItem('totalAmount', JSON.stringify(this.totalAmount))
        },
        deleteOneItem(selectProduct){
            if(selectProduct.quantity > 0){
                this.shoppingCart.filter(product => {
                    if(product.id == selectProduct.id){
                        product.quantity--
                        product.stock++
                    }
                })
            }
            this.cartStorage()
        },
        deleteProduct(selectProduct){
            this.shoppingCart = this.shoppingCart.filter(product => product != selectProduct)
            this.cartStorage()
        },
        emptyCart() {
            localStorage.clear()
            window.location.reload()
        },

        crateTicket(){
            this.shoppingCart.forEach(product => this.idProduct = product.id)
            axios.post('/api/tickets', `amount=${this.totalAmount}&paymentMethod=${this.paymentMethodVModel}&idProduct=${this.idProduct}`)
            .then(response => console.log(response))
            .catch(error => error)


            // axios.post('/api/login', `email=${this.email}&password=${this.contraseña}`).then(response => window.location.assign("./accounts.html"))
            //     .catch(function (error) {
            //         console.log(error.response)
            //         Swal.fire({
            //             text:'Email o contraseña incorrecta',
            //             confirmButtonColor: 'lightgreen',})
            //     })
        }
  
    },
    computed: {
    }
  }).mount('#app')