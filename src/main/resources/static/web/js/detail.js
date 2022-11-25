const app = Vue.createApp({
    data() {
        return {
            products: [],
            urlApi: "/api/products",
            ticket: [],
            name: "",
            id:(new URLSearchParams(location.search).get("id")),
            details:[]

        }
    },
    created() { /* created es para  cuando el obejto, la aplicacion ya se creo se ejecuta estos metodos*/
        this.loadData(this.urlApi)
    },
    mounted() { /* es cuando se creo la parte visual cuando este renderizado */

    },
    methods: { /* funciones q utilizamos normalmente */
        loadData(url) { //hacemos una peticion a la pagina web consumir los datos en tiempo real
            axios.get(url) //con axios estoy consumiendo una api 
                .then((response) => {
                    this.products = response.data;
                    this.ticket = this.products.tickets
                    this.details= this.products.find(value => value.id == this.id)
                    console.log(this.products);

                })
        },



        logout() {
            axios.post('/api/logout')
                .then(() => window.location.pathname = '/web/index.html')
        }
    },
    computed: {
        /* filtroBuscador() {
            this.productos = this.productos.filter(producto => producto.name.toLowerCase().includes(this.busqueda.toLowerCase()))
        } */

    },
}).mount('#app')