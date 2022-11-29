const app = Vue.createApp({
    data() {
        return {
            products: [],
            urlApi: "/api/products",
            ticket: [],
            name: "",
            id: (new URLSearchParams(location.search).get("id")),
            details: [],
            relacionados: [],


        }
    },
    created() { /* created es para  cuando el obejto, la aplicacion ya se creo se ejecuta estos metodos*/
        this.loadData(this.urlApi)
    },
    mounted() { /* es cuando se creo la parte visual cuando este renderizado */
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
    methods: { /* funciones q utilizamos normalmente */
        loadData(url) { //hacemos una peticion a la pagina web consumir los datos en tiempo real
            axios.get(url) //con axios estoy consumiendo una api 
                .then((response) => {
                    this.products = response.data.filter(data => data.enable)
                    //this.ticket = this.products.tickets
                    this.details = this.products.find(value => value.id == this.id)
                    this.products.forEach(product => {
                        if (product.type == this.details.type && this.relacionados.length < 4) {
                            this.relacionados.push(product)
                        }
                    })
                    console.log(this.products)


                })
        },
        logout() {
            axios.post('/api/logout')
                .then(() => window.location.pathname = '/web/index.html')
        },

        /* ADD PRODUCT TO CART */

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
                Swal.fire({
                    text: `Product added to cart`,
                    confirmButtonColor: 'lightgray',
                })
            }
            else {
                // CASO EN EL QUE NO ESTA
                this.products.filter(product => {
                    if (product.id == selectProduct.id) {
                        selectProduct.quantity++
                        product.stock--
                    }
                })
                this.shoppingCart.push(selectProduct)
                this.cartStorage()
                Swal.fire({
                    text: `Product added to cart`,
                    confirmButtonColor: 'lightgray',
                })
            }

            //localStorage.setItem('totalAmount', JSON.stringify(this.totalAmount))
        },

        /* ADD PRODUCT TO FAVORITES */

        addToFavorites(){
            axios.post('/api/clientproductfav', `id=${this.details.id}&name=${this.details.name}&url=${this.details.urlImg}&price=${this.details.price}`)
            .then(response => {
                console.log(response)
            })
            .catch(error => {
                console.log(error)
            })



            // @RequestParam long id,
            // @RequestParam String name,
            // @RequestParam String url,
            // @RequestParam double price,
        }


    },
    computed: {
        /* filtroBuscador() {
            this.productos = this.productos.filter(producto => producto.name.toLowerCase().includes(this.busqueda.toLowerCase()))
        } */

    },
}).mount('#app')