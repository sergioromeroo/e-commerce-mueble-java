const app = Vue.createApp({
    data() {
        return {
            clients: [],
            urlApi: "/api/clients",
            clientsBackUp: [],
            inputSearchVModel: "",
        
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
                    console.log(response)
                    this.clients= response.data;
                    this.clientsBackUp = this.clients

                    

                })
        },
        createProduct() {
            axios.post('/api/post/product', { type: this.type, name: this.name, price: this.price, urlImg: this.urlImg, materialType: this.materialType, description: this.description })
                .then(() => {

                    /*  aca iria un sweet alert    */
                    alert("Producto creado");
                })
        },

        deleteProduct(productChecket) {
            console.log(productChecket.id);
            axios.patch('/api/products/delete', "id=" + productChecket.id)
                .then(() => {

                    /*  aca iria un sweet alert    */
                    alert("producto eliminado");

                })
                .then(() => {
                    window.location.pathname = '/web/admin/admin2.html';
                })
        },
       home(){
        window.location.pathname = '/web/index.html'

       },
        logout() {
            axios.post('/api/logout')
                  .then(() => {
                        Swal.fire({
                              title: 'Successful logout',
                              text: 'You will be redirected',
                              confirmButtonColor: 'lightgray',
                              timer: 1500
                        })
                              .then(() => window.location.pathname = '/web/index.html')
                  })
                  .catch(error => console.log(error))
      },
    },
    computed: {

        searchFilter(){
            if(this.inputSearchVModel != ""){
            this.clients = this.clientsBackUp.filter(client => client.email.toLowerCase().includes(this.inputSearchVModel.toLowerCase()))
            } else {
                this.clients = this.clientsBackUp
            }
        }
        /* filtroBuscador() {
            this.productos = this.productos.filter(producto => producto.name.toLowerCase().includes(this.busqueda.toLowerCase()))
        } */

    },
}).mount('#app')