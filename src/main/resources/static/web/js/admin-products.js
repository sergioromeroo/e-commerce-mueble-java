const app = Vue.createApp({
            data() {
                return {
                    products: [],
                    productsBackUp: [],
                    inputSearchVModel: "",
                    urlApi: "/api/products",
                    ticket: [],
                    search: "",
                    type: "",
                    name: "",
                    price: 0,
                    urlImg: "",
                    stock: 0,
                    materialType: "",
                    description: "",
                    productsEnableTrue: [],
                    stock2: "",
                    id: ""
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
                            this.productsEnableTrue = this.products.filter(product => product.enable == true)
                            this.productsBackUp = this.productsEnableTrue
                            console.log(this.productsEnableTrue);

                        })
                },
                createProduct() {
                    axios.post('/api/post/product', { type: this.type, name: this.name, price: this.price, urlImg: this.urlImg, stock: this.stock, materialType: this.materialType, description: this.description })
                        .then(() => {
                            Swal.fire({
                                text: `Add product ok`,
                                confirmButtonColor: 'lightgray',
                            })
                        }).catch(error => {
                            console.log(error)
                            Swal.fire({
                                text: `${error.response.data}`,
                                confirmButtonColor: 'lightgray',
                            })
                        })
                },
                /*         editProduct() {
                            axios.patch('/api/products/update', `stock=${this.stock2}&id=${this.id}`)
                                .then(() => {

                                    Swal.fire({
                                        text: `Edit product ok`,
                                        confirmButtonColor: 'lightgray',
                                        willClose: () => {
                                            window.location.assign("./admin2.html")
                                        }
                                    })
                                    /*  aca iria un sweet alert    */
                //alert("Producto editado");
            })
        .catch(error => {
            console.log(error)
            Swal.fire({
                text: `${error.response.data}`,
                confirmButtonColor: 'lightgray',

            })
        })
    },
    * /
editProduct() {


        Swal.fire({
            title: "Are you sure you want to edit this product?",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: '#808080',
            cancelButtonColor: '#ff0000',
            confirmButtonText: 'Confirm Edit'
        })

        .then((result) => {
            if (result.isConfirmed) {

                axios.patch('/api/products/update', `stock=${this.stock2}&id=${this.id}`)
                    .then(() => {
                        Swal.fire({
                                title: 'The product Has been edited Succsefully!',
                                text: "The Page would Reload on a second!",
                                icon: "success",
                                confirmButtonColor: 'lightgray',
                                timer: 2500

                            })
                            .then(response => window.location.href = "./admin2.html")

                    })

                .catch(function(error) {
                    Swal.fire({
                        icon: 'error',
                        text: error.response.data,

                    })
                })


            }


        });
    },


    deleteProduct(productChecket) {
        console.log(productChecket.id);
        axios.patch('/api/products/delete', "id=" + productChecket.id)
            .then(() => {

                Swal.fire({
                        text: `Removed product`,
                        confirmButtonColor: 'lightgray',
                        willClose: () => {
                            window.location.assign("./admin2.html")
                        }
                    })
                    /*  aca iria un sweet alert    */
                    //alert("producto eliminado");

            })
            .catch(error => {
                console.log(error)
                Swal.fire({
                    text: `${error}`,
                    confirmButtonColor: 'lightgray',

                })
            })

    },

    logout() {
        axios.post('/api/logout')
            .then(() => window.location.pathname = '/web/index.html')
    }
},
computed: {

searchFilter() {
    if (this.inputSearchVModel != "") {
        this.productsEnableTrue = this.productsBackUp.filter(product => product.name.toLowerCase().includes(this.inputSearchVModel.toLowerCase()))
    } else {
        this.productsEnableTrue = this.productsBackUp
    }
}
/* filtroBuscador() {
    this.productos = this.productos.filter(producto => producto.name.toLowerCase().includes(this.busqueda.toLowerCase()))
} */

},
}).mount('#app')