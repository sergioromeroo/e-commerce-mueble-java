const app = Vue.createApp({
    data() {
        return {

            products: [],
            tickets: [],
            ticketsBackUp: [],
            productForTicket: [],
            inputSearchVModel: "",

        }
    },
    created() {
        this.loadProducts()
        this.loadTickets()
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
        loadTickets() {
            axios.get('/api/tickets')
                .then(response => {
                    this.tickets = response.data
                    this.ticketsBackUp = this.tickets
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
        searchFilter(){
            if(this.inputSearchVModel != ""){
            this.tickets = this.ticketsBackUp.filter(ticket => ticket.id == this.inputSearchVModel)
            } else {
                this.tickets= this.ticketsBackUp
            }
        }
    }
}).mount('#app')