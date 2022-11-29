const app = Vue.createApp({
    data() {
        return {
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
            ]

        }
    },
    created() {
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
            axios.post('https://homebanking-production-8635.up.railway.app/api/payments', { number: `${this.numberCardVModel}`, cvv: `${this.cvvCardVModel}`, amount: `${this.totalAmount}`, description: `FF purchase` })
                .then(response => {
                    console.log(response)
                    this.ordenateArray()
                    axios.post('/api/tickets', `amount=${this.totalAmount}&paymentMethod=${response.data}&idProduct=${this.idProducts}&quantity=${this.quantityProducts}`)
                        .then(resp => {
                            console.log(resp)
                            Swal.fire({
                                text: `${response.data}`,
                                confirmButtonColor: 'lightgray',
                                willClose: () => {
                                    localStorage.clear()
                                    window.location.assign("./index.html")
                                }
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
                    Swal.fire({
                        text: `${error.response.data}`,
                        confirmButtonColor: 'lightgray',
                    })
                })
        },

        ordenateArray() {
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
        Imprimir() {
            const doc = new jsPDF({});


            doc.setFontSize(20);
            doc.text(90, 20, 'Ticket', { align: 'center' });
            doc.text(20, 20, 'Future Furtniture');
            doc.setLineWidth(1.5);
            doc.line(10, 25, 200, 25);





            doc.setFontSize(17)
            doc.text(10, 35, "name")
            doc.text(60, 35, "Description")
            doc.text(120, 35, "Quantity")

            doc.text(170, 35, "Unit Price")
            doc.text(150, 280, "Total : $")
            doc.setLineWidth(1.0);
            doc.line(150, 285, 195, 285);
            let numero = 40
            this.shoppingCart.forEach(item => {
                numero = numero + 10
                doc.setFontSize(15);
                doc.text(15, numero, `${item.name}`, { align: 'center' });
                doc.text(60, numero, item.name, { align: 'center' });
                doc.text(130, numero, `${item.quantity}`, { align: 'center' })
                doc.text(170, numero, `${item.price * item.quantity}`, { align: 'center' });
                doc.text(175, 280, `${this.totalAmount}`, { align: 'center' });




            })
            


             doc.save("two-by-four.pdf");

        },



    },
    computed: {
    }
}).mount('#app')
