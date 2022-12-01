const app = Vue.createApp({
    data() {
        return {
            emailVModel: "",
            nameVModel: "",
            reasonVModel: "",
            messageVModel: "",
            
            clientCurrent: null,
            clientPage: "./client/profileClient.html",
            mailCurrent: true,

        }
    },
    created() {
        this.loadClientCurrent()
    },
    mounted() {
    },
    methods: {
        loadClientCurrent(){
            axios.get('/api/clientcurrent')
            .then(response => {
                this.clientCurrent = response.data
                if(this.clientCurrent.email.includes("@admin")){
                    this.mailCurrent = false
                    this.clientPage = "./admin/admin2.html"
                }
            })
            .catch(error => console.log(error))
        },
        sendEmail() {
            if (this.nameVModel == "" || this.emailVModel == "" || this.reasonVModel == "" || this.messageVModel == "") {
                Swal.fire({
                    text: `Please fill all the form`,
                    confirmButtonColor: 'lightgray',
                })
            } else {
                if (!this.emailVModel.includes("@")) {
                    Swal.fire({
                        text: `The email must contain @`,
                        confirmButtonColor: 'lightgray',
                    })
                } else {
                    // let timerInterval
                    // Swal.fire({
                    //     title: 'Sending',
                    //     html: 'I will close very soon :)',
                    //     timer: 10000,
                    //     timerProgressBar: true,
                    //     didOpen: () => {
                    //         Swal.showLoading()
                    //         //const b = Swal.getHtmlContainer().querySelector('b')
                    //         timerInterval = setInterval(() => {
                    //             // b.textContent = Swal.getTimerLeft()
                    //         }, 100)
                    //     },
                    //     willClose: () => {
                    //         clearInterval(timerInterval)
                    //     }
                    // })



                    Swal.fire({
                        text: `Thank you very much for contacting us!`,
                        confirmButtonColor: 'lightgray',
                        
                    })


                    axios.post('/api/sendemailcontact', `name=${this.nameVModel}&contactFrom=${this.emailVModel}&subject=${this.reasonVModel}&description=${this.messageVModel}`)
                        .then(response => {
                            console.log(response)
                                window.location.assign("./contact.html")
                            

                        })
                        .catch(error => {
                            console.log(error)
                            Swal.fire({
                                text: `Something went wrong! :(`,
                                confirmButtonColor: 'lightgray',
                            })
                        })
                }
            }
        }
    },
    computed: {
    }
}).mount('#app')