const app = Vue.createApp({
    data() {
        return {

            products: [],
            tickets: [],
            ticketsBackUp: [],
            clients: [],
            clientTicket: {},
            productForTicket: [],
            inputSearchVModel: "",
            

        }
    },
    created() {
        this.loadProducts()
        this.loadTickets()
        this.loadClients()
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
        loadClients() {
            axios.get('/api/clients')
            .then(response => {
                console.log(response)
                this.clients = response.data
            })
            .catch(error => console.log(error))
        },
        productTicket(ticket) {
            this.productForTicket = ticket.product
        }, 
        balanceFormateado(numero){
            return new Intl.NumberFormat('de-DE', { style: 'currency', currency: 'ARS' }).format(numero)
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

        // print(producto) {
        //     const doc = new jsPDF({});

        //      //const logo = require('@/icons/png/logo.png')
        //      //var imgLogo = new Image()
        //      //imgLogo.src = logo
        //      //doc.addImage(imgLogo, 'PNG', 200, 100, 24, 8)

        //      doc.setFontSize(20);
        //       doc.text(170, 20,' # Ticket', { align: 'right' });
        //       doc.text(20, 20, 'Nogal');
        //       doc.setFontSize(13);
        //       doc.setFontSize(14);
        //      doc.text(20, 40, " Nueva York E 41 st St")
        //            doc.text(20, 48, "New York City,10001")
        //            doc.text(20, 56, "Phone 212-277-0000")
 
        //      doc.setLineWidth(1.5);
        //       doc.line(10, 95, 200, 95);
 
 
        //       doc.setFontSize(11);
        //       doc.text(20,65,"To:")
        //       doc.text(20,72,`First name:`) 
        //       doc.text(20,78,`Last name:`) 
        //       doc.text(20,84,`Email:`) 
        //       doc.text(20,90,`Phone:`) 
 
        //       doc.text(148,65," Ship To:")
        //       doc.text(150,72,`Street:`) 
        //       doc.text(150,78,`City:`) 
        //       doc.text(150,84,`State:`) 
 
        //           doc.setFontSize(13)
 
        //      doc.text(20,105,"DESCRIPTION")
        //      doc.text(80,105,"QUANTITY")
 
        //      doc.text(120,105,"UNIT PRICE :")
        //      doc.text(170,105,"TOTAL:")
        //      doc.text(150,265,"SHIPPING: FREE")
        //      doc.text(150,275,"TOTAL : $ ________")
 
        //      let numero = 105
 
        //      producto.forEach(ticket=> {
        //          numero = numero + 10
        //          doc.setFontSize(15); 
        //          doc.text(20, numero ,`${ticket.name}`,  { align: 'center' });
        //          doc.text(88,numero,`${ticket.quantity}`,{ align: 'center' }) 
        //            doc.text(125, numero  ,`${this.balanceFormateado(ticket.price).split("A")[0]}`, { align: 'center' });
        //          doc.text(170, numero ,`${this.balanceFormateado(ticket.price * ticket.quantity).split("A")[0]}`, { align: 'center' });
            
 
                 

        //      })
         

 
        //      doc.text(10,265,"Payment Method:__________ ")
        //      doc.text(10,275,"Card/Check Number:____________ ")
             
 
        //    doc.save("Nogal-Purchase.pdf");
 
        // },
        
        print(ticket) {
            console.log(ticket)
            this.clientTicket = this.clients.filter(client => client.id == ticket.client_id)[0]
            console.log(this.clientTicket)

            const doc = new jsPDF({});

            doc.setFontSize(20);
            doc.text(170, 20, `Ticket #${ticket.id}`, { align: 'right' });
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
            doc.text(20, 72, `First name: ${this.clientTicket.firstname}`) //this.clientCurrent.firstName 
            doc.text(20, 78, `Last name: ${this.clientTicket.lastname}`) //this.clientCurrent.lastName
            doc.text(20, 84, `Email: ${this.clientTicket.email}`) //this.clientCurrent.email
            doc.text(20, 90, `Phone: ${this.clientTicket.cellphone}`) //this.clientCurrent.celphone

            doc.text(148, 65, " Ship To:")
            doc.text(150, 72, `Street: ${this.clientTicket.addres}`) //this.clientCurrent.addres
            doc.text(150, 78, `City: ${this.clientTicket.city}`) //this.clientCurrent.city
            doc.text(150, 84, `State: ${this.clientTicket.state}`) //this.clientCurrent.state
                //doc.text(150,90,"Phone:")

            doc.setFontSize(13)

            doc.text(20, 105, "DESCRIPTION")
            doc.text(80, 105, "QUANTITY")

            doc.text(120, 105, "UNIT PRICE :")
            doc.text(170, 105, "TOTAL:")
            doc.text(150, 265, "SHIPPING: FREE")
            doc.text(150, 275, "TOTAL : $ ___________")

            let numero = 105
            let total = 0

            ticket.product.forEach(item => {

                total = total + item.price*item.quantity
                console.log(total)
                numero = numero + 10
                doc.setFontSize(15);
                doc.text(20, numero, item.name, { align: 'center' });
                doc.text(88, numero, `${item.quantity}`, { align: 'center' })
                doc.text(125, numero, ` $${this.balanceFormateado(item.price).split("A")[0]}`, { align: 'center' });
                doc.text(170, numero, `$${this.balanceFormateado(item.price*item.quantity).split("A")[0]}`, { align: 'center' });
            })

            doc.text(175, 275, `${this.balanceFormateado(total).split("A")[0]}`, { align: 'center' });

            doc.save("Nogal-purchase.pdf");

        },
        home(){
            window.location.pathname = '/web/index.html'
    
           },
            // logout() {
            //     axios.post('/api/logout')
            //         .then(() => window.location.pathname = '/web/index.html')
            // }


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