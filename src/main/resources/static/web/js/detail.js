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
            clientCurrent: null,
            clientPage: "./client/profileClient.html",
            mailCurrent: true,


            // login y register
            emailVModel: "",
            passwordVModel: "",

            firstNameRegisterVModel: "",
            lastNameRegisterVModel: "",
            celPhoneRegisterVModel: null,
            addresVModel: "",
            cityVModel: "",
            stateVModel: "",

            login: false,
            register: false,

            clientCurrent: null,
            clientPage: "./client/profileClient.html",
            mailCurrent: true,
        }
    },
    created() { /* created es para  cuando el obejto, la aplicacion ya se creo se ejecuta estos metodos*/
        this.loadData(this.urlApi)
        this.loadClientCurrent()
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
        loadClientCurrent() {
            axios.get('/api/clientcurrent')
                .then(response => {
                    this.clientCurrent = response.data
                    console.log(response)
                    if(this.clientCurrent.email.includes("@admin")){
                        this.mailCurrent = false
                        this.clientPage = "./admin/admin2.html"
                    }
                })
                .then(error => console.log(error))
        },

        /* LOGIN AND REGISTER */
        access() {
            axios.post('/api/login', `email=${this.emailVModel}&password=${this.passwordVModel}`)
                .then(response => {
                    console.log(response)
                    console.log(this.emailVModel)
                    if(this.emailVModel.includes("@admin")){
                        window.location.assign("./admin/admin2.html")
                    } else {
                        Swal.fire({
                            title: `¡Welcome!`,
                            confirmButtonColor: 'lightgray',
                            timer: 1500
                        })
                        .then(() => window.location.reload())
                    }
                })
                .catch(function(error) {
                    Swal.fire({
                        title: 'Wrong Password or UserMail Check Again',
                        confirmButtonColor: 'lightgray',
                        confirmButtonText: 'Try again',
                        timer: 2500

                    })
                })
        },
        clientRegister() {
            if (!this.emailVModel.includes("@")) {
                Swal.fire({
                    text: `The email Must Contain @ to be valid`,
                    confirmButtonColor: 'lightgray',
                    timer: 5500
                })
            } else {

            axios.post('/api/clients', `firstName=${this.firstNameRegisterVModel}&lastName=${this.lastNameRegisterVModel}&email=${this.emailVModel}&password=${this.passwordVModel}&cellphone=${this.celPhoneRegisterVModel}&city=${this.cityVModel}&addres=${this.addresVModel}&state=${this.stateVModel}`)
                .then(response => {


                    axios.post('/api/sendemailvalidation', "contactTo=" + this.emailVModel);


                    Swal.fire({
                        title: 'Write the secret word sended to your email',
                        input: 'text',
                        showCancelButton: true,
                        inputValidator: (value) => {
                            return new Promise((resolve) => {
                                if (value === 'chair') {
                                    Swal.fire(
                                            'Successful registration!',
                                            'Welcome!',
                                            'success'
                                        )
                                        .then(() => {

                                            this.access()

                                        })
                                } else {
                                    /* resolve(); */
                                    this.deleteClient(this.emailVModel);
                                }
                            })
                        }
                    })


                })

            .catch(function(error) {
                console.log(error)
                Swal.fire({
                    icon: 'error',
                    title: "Missing information",
                    confirmButtonColor: 'lightgray',
                    confirmButtonText: 'Ok',

                })
            })
        }
        },

        logout() {
            axios.post('/api/logout')
                .then(() => window.location.pathname = '/web/index.html')
        },

        theLoginAndRegister() {
            if (this.register == false) {
                this.login = false
                this.register = true;
            } else {
                this.login = true;
                this.register = false;
            }
        },
        accounts() {
            if (this.register == false) {
                this.login = !this.login;
                this.register = false;
            } else {
                this.login == false;
                this.register = false;
            }
        },
        closeTools() {
            this.login = false;
            this.register = false;
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
            console.log(selectProduct)
            if (this.clientCurrent == null) {
                Swal.fire({
                    text: `please login first`,
                    confirmButtonColor: 'lightgray',
                })
            } else {

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
            }
            //localStorage.setItem('totalAmount', JSON.stringify(this.totalAmount))
        },

        /* ADD PRODUCT TO FAVORITES */

        addToFavorites() {
            if (this.clientCurrent == null) {
                Swal.fire({
                    text: `please login first`,
                    confirmButtonColor: 'lightgray',
                })
            } else {
            axios.post('/api/clientproductfav', `id=${this.details.id}&name=${this.details.name}&url=${this.details.urlImg}&price=${this.details.price}`)
                .then(response => {
                    console.log(response)
                    Swal.fire({
                        text: `Product added to favorites`,
                        confirmButtonColor: 'lightgray',
                    })
                })
                .catch(error => {
                    console.log(error)
                    Swal.fire({
                        text: `${error.response.data}`,
                        confirmButtonColor: 'lightgray',
                    })
                })
            }
        },
        balanceFormateado(numero) {
            return new Intl.NumberFormat('de-DE', { style: 'currency', currency: 'ARS' }).format(numero)
        },



    },
    computed: {
        /* filtroBuscador() {
            this.productos = this.productos.filter(producto => producto.name.toLowerCase().includes(this.busqueda.toLowerCase()))
        } */

    },
}).mount('#app')