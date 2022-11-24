const app = Vue.createApp({
    data() {
        return {
            productos: [],
            urlApi: "/api/products",
            ticket:[]
        }
    },
    created() {/* created es para  cuando el obejto, la aplicacion ya se creo se ejecuta estos metodos*/
        this.loadData(this.urlApi)
    },
    mounted() {/* es cuando se creo la parte visual cuando este renderizado */

    },
    methods: {/* funciones q utilizamos normalmente */
        loadData(url) {//hacemos una peticion a la pagina web consumir los datos en tiempo real
            axios.get(url)//con axios estoy consumiendo una api 
                .then((response) => {
                    this.productos = response.data;
                    this.ticket=this.productos.tickets
                    console.log(productos);
                    
                })
        },  

        logout(){
            axios.post('/api/logout')
            .then(() => window.location.pathname='/web/index.html')
        } 
    },
    computed: {

    },
}).mount('#app')

/* El método GET
El método GET se utiliza para recuperar datos del servidor. Este es un método de solo lectura, por lo que 
no tiene riesgo de mutar o corromper los datos. Por ejemplo, si llamamos al método get en nuestra API, 
obtendremos una lista de todas las tareas pendientes. */

/* put  */
