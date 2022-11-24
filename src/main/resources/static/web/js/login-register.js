const { createApp } = Vue
createApp({
    data() {
        return {
            hola: "hola",
            
            emailVModel: "",
            passwordVModel: "",
            
            firstNameRegisterVModel: "",
            lastNameRegisterVModel: "",
            emailRegisterVModel: "",
            passwordRegisterVModel: "",
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
            .then(response => console.log(response))
            .catch(error => console.log(error))
        },
        clientRegister() {
            axios.post('/api/clients', `firstName=${this.firstNameRegisterVModel}&lastName=${this.lastNameRegisterVModel}&email=${this.emailRegisterVModel}&password=${this.passwordRegisterVModel}&cellphone=${this.celPhoneRegisterVModel}`)
            .then(response => console.log(response))
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