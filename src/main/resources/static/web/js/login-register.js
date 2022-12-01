const { createApp } = Vue
createApp({
    data() {
        return {
            hola: "hola",

            emailVModel: "",
            passwordVModel: "",

            firstNameRegisterVModel: "",
            lastNameRegisterVModel: "",
            celPhoneRegisterVModel: null,
            addresVModel: "",
            cityVModel: "",
            stateVModel: "",

            login: false,
            register: false,

            clientCurrent: null,
            clientPage: "./client/profileClient.html",
            mailCurrent: true,

        }

    },
    created() {
        this.loadClientCurrent()
        console.log(this.clientCurrent)
        console.log(this.mailCurrent)
    },

    mounted() {},

    methods: {

        loadClientCurrent(){
            axios.get('/api/clientcurrent')
            .then(response => {
                console.log(response)
                this.clientCurrent = response.data
                if(this.clientCurrent.email.includes("@admin")){
                    this.mailCurrent = false
                    this.clientPage = "./admin/admin2.html"
                }
                console.log(this.mailCurrent)
            })
            .catch(error => console.log(error))
        },

        access() {
            axios.post('/api/login', `email=${this.emailVModel}&password=${this.passwordVModel}`)
                .then(response => {
                    console.log(response)
                    console.log(this.emailVModel)
                    if(this.emailVModel.includes("@admin")){
                        window.location.assign("./admin/admin2.html")
                    } else {
                        Swal.fire({
                            title: `¡Welcome!`,
                            confirmButtonColor: 'lightgray',
                            timer: 1500
                        })
                        .then(() => window.location.reload())
                    }
                })
                .catch(function(error) {
                    Swal.fire({
                        title: 'Wrong Password or UserMail Check Again',
                        confirmButtonColor: 'lightgray',
                        confirmButtonText: 'Try again',
                        timer: 2500

                    })
                })
        },
        clientRegister() {
            if (!this.emailVModel.includes("@")) {
                Swal.fire({
                    text: `The email Must Contain @ to be valid`,
                    confirmButtonColor: 'lightgray',
                    timer: 5500
                })
            } else {

            axios.post('/api/clients', `firstName=${this.firstNameRegisterVModel}&lastName=${this.lastNameRegisterVModel}&email=${this.emailVModel}&password=${this.passwordVModel}&cellphone=${this.celPhoneRegisterVModel}&city=${this.cityVModel}&addres=${this.addresVModel}&state=${this.stateVModel}`)
                .then(response => {


                    axios.post('/api/sendemailvalidation', "contactTo=" + this.emailVModel);


                    Swal.fire({
                        title: 'Write the secret word sended to your email',
                        input: 'password',
                        showCancelButton: true,
                        inputValidator: (value) => {
                            return new Promise((resolve) => {
                                if (value === 'chair') {
                                
                                        Swal.fire({
                                            title: 'Successful registration!',
                                            text: "Welcome!",
                                            icon: "success",
                                            confirmButtonColor: 'lightgray',
                                            timer: 4500
        
                                        })
                                        .then(() => {

                                            this.access()

                                        })
                                } else {
                                    /* resolve(); */
                                    this.deleteClient(this.emailVModel);
                                }
                            })
                        }
                    })


                })

            .catch(function(error) {
                console.log(error)
                Swal.fire({
                    icon: 'error',
                    title: "Missing information",
                    confirmButtonColor: 'lightgray',
                    confirmButtonText: 'Ok',

                })
            })
        }
        },
        deleteClient(email) {
            axios.put('/api/clients/delete', "email=" + email)
                .then(() => {
                    Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: 'You write bad the secret code, please register again',
                            confirmButtonColor: 'lightgray',
                            confirmButtonText: 'Ok',

                        })
                        .then(() => {
                            window.location.href = '/web/index.html'
                        })
                })

        },

        theLoginAndRegister() {
            if (this.register == false) {
                this.login = false
                this.register = true;
            } else {
                this.login = true;
                this.register = false;
            }
        },
        accounts() {
            if (this.register == false) {
                this.login = !this.login;
                this.register = false;
            } else {
                this.login == false;
                this.register = false;
            }
        },
        closeTools() {
            this.login = false;
            this.register = false;
        }

    },


}).mount('#app')