const { createApp } = Vue
createApp({
    data() {
        return {
            hola: "hola",
            password: "",
            emial: "",
            emialRegister:"",
            passwordRegister:"",
            nameRegister:"",
            login:false,
            register:false,

        }

    },
    created() {},

    mounted() {},

    methods: {

        acceder: function () {
            axios.post('/api/login', "email=" + this.emial + "&contrase単a=" + this.contrase単a).then(() => window.location.href = "./cuentas.html")
        },
        register: function () {
            axios.post('/api/login', "email=" + this.emial + "&contrase単a=" + this.contrase単a).then(() => window.location.href = "./cuentas.html")
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