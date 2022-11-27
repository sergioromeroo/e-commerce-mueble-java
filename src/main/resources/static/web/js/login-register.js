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
            
            login:false,
            register:false,

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
            .catch(function (error){
                Swal.fire({
                    title: 'Wrong Password or UserMail Check Again',
                    confirmButtonColor: 'lightgray',
                    confirmButtonText:'Try again',
                    timer: 2500
                  
                  })
            })
        },
        clientRegister() {
            if (!this.emailVModel.includes("@")) {
                Swal.fire({
                    text: `The email Must Contain @ to be valid`,
                    confirmButtonColor: 'lightgrey',
                    timer: 5500
                })
            }
            
            axios.post('/api/clients', `firstName=${this.firstNameRegisterVModel}&lastName=${this.lastNameRegisterVModel}&email=${this.emailVModel}&password=${this.passwordVModel}&cellphone=${this.celPhoneRegisterVModel}`)
            .then(response => {
                console.log(response)
                this.access()
            })
            
            .catch( function(error) {
                Swal.fire({
                    icon: 'error',
                    title: error.response.data,
                    confirmButtonColor: 'lightgray',
                    confirmButtonText:'Ok',

                })
            })
            
        },

        theLoginAndRegister(){
            if(this.register == false ){
                this.login  = false
                this.register =  true;
            }
            else{
                this.login = true;
                this.register = false;
            }
        },
        accounts(){
            if (this.register == false) {
                this.login = !this.login;
                this.register = false;
            } else {
                this.login == false;
                this.register = false;
            }
        },
        closeTools(){
            this.login = false;
            this.register = false;
        }

    },


}).mount('#app')