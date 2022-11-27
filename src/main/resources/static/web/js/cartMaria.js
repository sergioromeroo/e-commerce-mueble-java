const app = Vue.createApp({
    data() {
        return {
            shoppingCart: [],
            shoppingCartBackUp: [],
            totalAmount: 0,
            paymentMethodVModel: "",

            idProducts: [],
            quantityProducts: [],

            numberCardVModel: null,
            cvvCardVModel: null,

            products: [],
            tickets: [],
            productForTicket: [],

        }
    },
    created() {
        this.loadProducts()
        this.loadClients()
    },
    mounted() {

        let currentCart = JSON.parse(localStorage.getItem('cart'))
        if (!currentCart) {
            this.shoppingCart = []
        } else {
            this.shoppingCart = currentCart
        }

        let currentTotalAmount = JSON.parse(localStorage.getItem('totalAmount'))
        if (currentTotalAmount != 0) {
            this.totalAmount = currentTotalAmount
        }
    },
    methods: {

        loadProducts() {
            axios.get('/api/products')
                .then(response => {
                    this.products = response.data
                    console.log(this.products)
  
                })
                .catch(error => console.log(error))
        },
        loadClients(){
            axios.get('/api/tickets')
                .then(response => {
                    this.tickets = response.data
                    console.log(this.tickets)
  
                })
                .catch(error => console.log(error))
        },
        productTicket(ticket) {
            //this.productForTicket = ticket.product
            console.log(this.productForTicket)
            this.productForTicket = []

            ticket.product.forEach(product => {
                this.products.forEach(prod => {
                   if (prod.id == product.idProduct){
                    this.productForTicket.push(prod)
                   }
                })
            })

            console.log(this.productForTicket)
        },







        finalAmount() {
            this.totalAmount = 0
            this.shoppingCart.map(product => {
                let addition = product.quantity * product.price
                this.totalAmount += addition
            })
            localStorage.setItem('totalAmount', JSON.stringify(this.totalAmount))
        },
        cartStorage() {
            localStorage.setItem('cart', JSON.stringify(this.shoppingCart))
            this.finalAmount()
        },
        addProductToShoppingCart(selectProduct) {

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
        deleteOneItem(selectProduct) {
            if (selectProduct.quantity > 0) {
                this.shoppingCart.filter(product => {
                    if (product.id == selectProduct.id) {
                        product.quantity--
                        product.stock++
                    }
                })
            }
            this.cartStorage()
        },
        deleteProduct(selectProduct) {
            this.shoppingCart = this.shoppingCart.filter(product => product != selectProduct)
            this.cartStorage()
        },
        emptyCart() {
            localStorage.clear()
            window.location.reload()
        },

        createArrays(){
            console.log(this.shoppingCart)
            
            this.shoppingCartBackUp = this.shoppingCart

            this.shoppingCartBackUp.sort((a, b) => { if (a.id > b.id) { return 1 } if (a.id < b.id) { return -1 } }).forEach(product => {
                this.idProducts.push(product.id)
                this.quantityProducts.push(product.quantity)
            })

            console.log(this.shoppingCart)

            console.log(this.idProducts)
            console.log(this.quantityProducts)
        },

        createTicket() {
            // this.shoppingCart.sort((a, b) => { if (a.id > b.id) { return 1 } if (a.id < b.id) { return -1 } }).forEach(product => {
            //     this.idProduct = product.id
            // })

            axios.post('https://homebanking-production-8635.up.railway.app/api/payments', { number: `${this.numberCardVModel}`, cvv: `${this.cvvCardVModel}`, amount: `${this.totalAmount}`, description: `FF purchase` })
                .then(response => {
                    console.log(response)
                    axios.post('/api/tickets', `amount=${this.totalAmount}&paymentMethod=${response.data}&idProduct=${this.idProducts}&quantity=${this.quantityProducts}`)
                    .then(resp => {
                        console.log(resp)
                        Swal.fire({
                            text:`${response.data}`,
                            confirmButtonColor: 'lightgreen',})
                    })
            }).catch(error => {
                console.log(error)
                Swal.fire({
                    text:`${error.response.data}`,
                    confirmButtonColor: 'lightgreen',})
            })
            .catch(error => {
                console.log(error)
                Swal.fire({
                    text:`${error.response.data}`,
                    confirmButtonColor: 'lightgreen',})
            })
        },

    },
    computed: {
    }
}).mount('#app')