const app = Vue.createApp({
    data() {
        return {

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
        loadClients() {
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
                    if (prod.id == product.idProduct) {
                        this.productForTicket.push(prod)
                    }
                })
            })

            console.log(this.productForTicket)
        },


    },
    computed: {
    }
}).mount('#app')