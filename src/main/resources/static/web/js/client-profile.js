const app = Vue.createApp({
    data() {
          return {
                clientCurrent: {},
                favorites: [],
                tickets: [],
                products: [],
                initials: ""

          }
    },
    created() {
          this.loadClientCurrent()
    },
    mounted() {

    },
    methods: {
          loadClientCurrent() {
                axios.get('/api/clientcurrent')
                      .then(response => {
                            this.clientCurrent = response.data

                            this.favorites = this.clientCurrent.clientProductFav.sort((a, b) => { if (a.id > b.id) { return 1 } if (a.id < b.id) { return -1 } })

                            this.tickets = this.clientCurrent.tickets.sort((a, b) => { if (a.id < b.id) { return 1 } if (a.id > b.id) { return -1 } })

                            this.initials = this.clientCurrent.firstname.slice(0, 1) + this.clientCurrent.lastname.slice(0, 1)

                            console.log(this.clientCurrent)
                      })
                      .catch(error => console.log(error))
          },

          deleteFavorite(){
            console.log("borrado")
            Swal.fire({
                  text: 'Delete',
                  confirmButtonColor: 'lightgray',
                  willClose: () => {
                      window.location.reload()
                  }
              })
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

          productsInTicket(ticket) {
                this.products = ticket.product.sort((a, b) => { if (a.id > b.id) { return 1 } if (a.id < b.id) { return -1 } })
                console.log(this.products)
          },
          balanceFormateado(numero) {
                return new Intl.NumberFormat('de-DE', { style: 'currency', currency: 'ARS' }).format(numero)
          },
          print(ticket) {
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
                doc.text(150, 275, "TOTAL : $ ______________")

                let numero = 105

                ticket.product.forEach(item => {

                      numero = numero + 10
                      doc.setFontSize(15);
                      doc.text(20, numero, item.name, { align: 'center' });
                      doc.text(88, numero, `${item.quantity}`, { align: 'center' })
                      doc.text(125, numero, ` $${this.balanceFormateado(item.price).split("A")[0]}`, { align: 'center' });
                      doc.text(170, numero, `$${this.balanceFormateado(item.price * item.quantity).split("A")[0]}`, { align: 'center' });
                })

                doc.text(175, 275, `$${this.balanceFormateado(ticket.amount).split("A")[0]}`, { align: 'center' });

                doc.save("Nogal-purchase.pdf");

          }
    },
    computed: {
    }
}).mount('#app')
