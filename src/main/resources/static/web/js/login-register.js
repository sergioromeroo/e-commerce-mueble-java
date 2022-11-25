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
            .catch(error => console.log(error))
        },
        clientRegister() {
            axios.post('/api/clients', `firstName=${this.firstNameRegisterVModel}&lastName=${this.lastNameRegisterVModel}&email=${this.emailVModel}&password=${this.passwordVModel}&cellphone=${this.celPhoneRegisterVModel}`)
            .then(response => {
                console.log(response)
                this.access()
            })
            .catch(error => console.log(error))
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