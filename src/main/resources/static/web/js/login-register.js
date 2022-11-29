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

        }

    },
    created() {},

    mounted() {},

    methods: {

        access() {

            axios.post('/api/login', `email=${this.emailVModel}&password=${this.passwordVModel}`)
                .then(response => {
                    console.log(response)
                    window.location.reload()
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
                        input: 'text',
                        showCancelButton: true,
                        inputValidator: (value) => {
                            return new Promise((resolve) => {
                                if (value === 'chair') {
                                    Swal.fire(
                                            'Successful registration!',
                                            'Welcome!',
                                            'success'
                                        )
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