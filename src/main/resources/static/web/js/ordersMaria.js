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
   

                })
                .catch(error => console.log(error))
        },
        loadTickets() {
            axios.get('/api/tickets')
                .then(response => {
                

                    this.tickets = response.data

                    this.ticketsBackUp = this.tickets
         

                })
                .catch(error => console.log(error))
        },
        productTicket(ticket) {
            //this.productForTicket = ticket.product
            // console.log(this.productForTicket)
            // this.productForTicket = []

            // ticket.product.forEach(product => {
            //     this.products.forEach(prod => {
            //         if (prod.id == product.idProduct) {
            //             this.productForTicket.push(prod)
            //         }
            //     })
            // })

            // console.log(this.productForTicket)
            
            this.productForTicket = ticket.product
        }, Imprimir(producto) {
            const doc = new jsPDF({});

             //const logo = require('@/icons/png/logo.png')
             //var imgLogo = new Image()
             //imgLogo.src = logo
             //doc.addImage(imgLogo, 'PNG', 200, 100, 24, 8)

             doc.setFontSize(20);
              doc.text(170, 20,' # Ticket', { align: 'right' });
              doc.text(20, 20, 'Nogal');
              doc.setFontSize(13);
              doc.text(20,28,"Future Furniture");
              doc.setFontSize(14);
             doc.text(20, 40, " Nueva York E 41 st St")
                   doc.text(20, 48, "New York City,10001")
                   doc.text(20, 56, "Phone 212-277-0000")
 
             doc.setLineWidth(1.5);
              doc.line(10, 95, 200, 95);
 
 
              doc.setFontSize(11);
              doc.text(20,65,"To:")
              doc.text(20,72,`First name:`) 
              doc.text(20,78,`Last name:`) 
              doc.text(20,84,`Email:`) 
              doc.text(20,90,`Phone:`) 
 
              doc.text(148,65," Ship To:")
              doc.text(150,72,`Street:`) 
              doc.text(150,78,`City:`) 
              doc.text(150,84,`State:`) 
 
                  doc.setFontSize(13)
 
             doc.text(20,105,"DESCRIPTION")
             doc.text(80,105,"QUANTITY")
 
             doc.text(120,105,"UNIT PRICE :")
             doc.text(170,105,"TOTAL:")
             doc.text(150,255,"SUBTOTAL : $")
             doc.text(150,265,"SHIPPING: $")
             doc.text(150,275,"TOTAL : $ ________")
 
             let numero = 105
 
             producto.forEach(ticket=> {
                 numero = numero + 10
                 doc.setFontSize(15); 
                 doc.text(20, numero ,`${ticket.name}`,  { align: 'center' });
                 doc.text(88,numero,`${ticket.quantity}`,{ align: 'center' }) 
                   doc.text(125, numero  ,`${ticket.price}`, { align: 'center' });
                 doc.text(170, numero ,`${ticket.price * ticket.quantity}`, { align: 'center' });
            
 
                 

             })
         

 
             doc.text(10,265,"Payment Method:__________ ")
             doc.text(10,275,"Card/Check Number:____________ ")
             
 
 
 
 

 
             
 
           doc.save("two-by-four.pdf");
 
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