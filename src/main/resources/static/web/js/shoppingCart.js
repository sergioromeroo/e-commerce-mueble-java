const app = Vue.createApp({
    data() {
        return {
            clientCurrent: {},
            shoppingCart: [],
            totalAmount: 0,
            paymentMethodVModel: "",
            cart_alert: false,
            idProducts: [],
            quantityProducts: [],
            numberCardVModel: null,
            cvvCardVModel: null,
            heading: "Sample PDF Generator",
            moreText: [
                "This is another few sentences of text to look at it.",
                "Just testing the paragraphs to see how they format.",
                "jsPDF likes arrays for sentences.",
                "Do paragraphs wrap properly?",
                "Yes, they do!",
                "What does it look like?",
                "Not bad at all."
            ],
            items: [
                { title: "Item 1", body: "I am item 1 body text" },
                { title: "Item 2", body: "I am item 2 body text" },
                { title: "Item 3", body: "I am item 3 body text" },
                { title: "Item 4", body: "I am item 4 body text" }
            ],
            cardPdf: "XXXX-XXXX-XXXX-",
            paymentMethodPdf: "",
            ticketId: null,

        }
    },
    created() {
        this.loadClientCurrent()
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
        loadClientCurrent() {
            axios.get('/api/clientcurrent')
                .then(response => {
                    console.log(response)
                    this.clientCurrent = response.data
                })
                .catch(error => {
                    console.log(error)
                })
        },
        balanceFormateado(numero){
            return new Intl.NumberFormat('de-DE', { style: 'currency', currency: 'ARS' }).format(numero)
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
            console.log(this.shoppingCart)
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
            } else {
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
            Swal.fire({
                text: `Product removed from cart`,
                confirmButtonColor: 'lightgray',
            })
        },
        emptyCart() {
            localStorage.clear()
            Swal.fire({
                text: `Empty cart`,
                confirmButtonColor: 'lightgray',
                willClose: () => {
                    window.location.assign("./products.html")
                }
            })
        },

        createTicket() {
            Swal.fire({
                    title: "Confirm purchase?",
                    text: `Total amount: $${this.balanceFormateado(this.totalAmount).split("A")[0]}`,
                    icon: "warning",
                    showCancelButton: true,
                    confirmButtonColor: '#808080',
                    cancelButtonColor: '#ff0000',
                    confirmButtonText: 'Confirm purchase?'
                })
                .then((result) => {
                    if (result.isConfirmed) {
                        axios.post('https://homebanking-production-8635.up.railway.app/api/payments', { number: `${this.numberCardVModel}`, cvv: `${this.cvvCardVModel}`, amount: `${this.totalAmount}`, description: `Nogal purchase` })
                            .then(response => {
                                console.log(response)
                                this.ordenateArray()
                                this.cardPdf = this.cardPdf + this.numberCardVModel.toString().slice(12,17)
                                this.paymentMethodPdf = response.data.slice(8,15)
                                axios.post('/api/tickets', `amount=${this.totalAmount}&paymentMethod=${response.data}&idProduct=${this.idProducts}&quantity=${this.quantityProducts}`)
                                    .then((res) => {
                                        this.ticketId = res.data
                                        this.print();
                                    })
                                    .then(resp => {
                                        console.log(resp)
                                        Swal.fire({
                                                title: 'Thank you',
                                                text: "You will be redirected to your customer panel!",
                                                icon: "success",
                                                confirmButtonColor: 'lightgray',
                                                timer: 2500
                                            })
                                            .then(response => {
                                                localStorage.clear()
                                                window.location.href = "../web/client/shopping.html"
                                            })
                                    }).catch(error => {
                                        console.log(error)
                                        Swal.fire({
                                            text: `${error}`,
                                            confirmButtonColor: 'lightgray',
                                        })
                                    })
                            })
                            .catch(error => {
                                console.log(error)
                                if (error.response.status == 500) {
                                    Swal.fire({
                                        text: `Missing payment data`,
                                        confirmButtonColor: 'lightgray',
                                    })
                                } else {
                                    Swal.fire({
                                        text: `${error.response.data}`,
                                        confirmButtonColor: 'lightgray',
                                    })
                                }
                            })
                    }
                })

        },

        ordenateArray() {
            console.log(this.shoppingCart)

            this.shoppingCartBackUp = this.shoppingCart

            this.shoppingCartBackUp.sort((a, b) => { if (a.id > b.id) { return 1 } if (a.id < b.id) { return -1 } }).forEach(product => {
                this.idProducts.push(product.id)
                this.quantityProducts.push(product.quantity)
            })

        },
        print() {
            const doc = new jsPDF({});
            //const logo = require('@/icons/png/logo.png')
            //var imgLogo = new Image()
            //imgLogo.src = logo
            //doc.addImage(imgLogo, 'PNG', 200, 100, 24, 8)

            doc.setFontSize(20);
            doc.text(170, 20, `Ticket #${this.ticketId}`, { align: 'right' });
            doc.text(20, 20, 'Nogal');
            doc.setFontSize(13);
            doc.setFontSize(14);
            doc.text(20, 40, "Nueva York E 41 st St")
            doc.text(20, 48, "New York City,10001")
            doc.text(20, 56, "Phone 212-277-0000")

            doc.setLineWidth(1.5);
            doc.line(10, 95, 200, 95);


            doc.setFontSize(11);
            doc.text(20, 65, "To:")
            doc.text(20, 72, `First name: ${this.clientCurrent.firstname}`) //this.clientCurrent.firstName 
            doc.text(20, 78, `Last name: ${this.clientCurrent.lastname}`) //this.clientCurrent.lastName
            doc.text(20, 84, `Email: ${this.clientCurrent.email}`) //this.clientCurrent.email
            doc.text(20, 90, `Phone: ${this.clientCurrent.cellphone}`) //this.clientCurrent.celphone

            doc.text(148, 65, " Ship To:")
            doc.text(150, 72, `Street: ${this.clientCurrent.addres}`) //this.clientCurrent.addres
            doc.text(150, 78, `City: ${this.clientCurrent.city}`) //this.clientCurrent.city
            doc.text(150, 84, `State: ${this.clientCurrent.state}`) //this.clientCurrent.state
                //doc.text(150,90,"Phone:")

            doc.setFontSize(13)

            doc.text(20, 105, "DESCRIPTION")
            doc.text(80, 105, "QUANTITY")

            doc.text(120, 105, "UNIT PRICE :")
            doc.text(170, 105, "TOTAL:")
            doc.text(150, 265, "SHIPPING: FREE")
            doc.text(150, 275, "TOTAL : $ ________")

            let numero = 105

            this.shoppingCart.forEach(item => {
                numero = numero + 10
                doc.setFontSize(15);
                doc.text(20, numero, item.name, { align: 'center' });
                doc.text(88, numero, `${item.quantity}`, { align: 'center' })
                doc.text(125, numero, ` $${this.balanceFormateado(item.price).split("A")[0]}`, { align: 'center' });
                doc.text(170, numero, `$${this.balanceFormateado(item.price*item.quantity).split("A")[0]}`, { align: 'center' });
                doc.text(175, 275, `${this.balanceFormateado(this.totalAmount).split("A")[0]}`, { align: 'center' });




            })


            doc.text(10, 265, `Payment method: ${this.paymentMethodPdf}`)
            doc.text(10, 275, `Card Number: ${this.cardPdf}`)





            /*             doc.setFontSize(17)
                        doc.text(10,45,"Item Id")
                        doc.text(60,45,"Description")
                        doc.text(120,45,"Quantity")
 
                        doc.text(170,45,"Unit Price")
                        doc.text(150,175,"Total : $")
 
                        let numero = 45
                        this.shoppingCart.forEach(item => {
                            console.log(this.shoppingCart)
                            numero = numero + 10
                            doc.setFontSize(15);
                            doc.text(15, numero ,`${item.id}`, { align: 'center' });
                            doc.text(60, numero , item.name  , { align: 'center' });
                            doc.text(130,numero,`${item.quantity}`,{ align: 'center' })
                            doc.text(170, numero , `${item.price}` , { align: 'center' });
                            doc.text(175,175,`${this.totalAmount}`,{ align: 'center' });
 
                            
 
 
                        }) */



            doc.save("Nogal-purchase.pdf");

        },

    },
    computed: {}
}).mount('#app')