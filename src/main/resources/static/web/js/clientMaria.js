const app = Vue.createApp({
    data() {
        return {
            clientCurrent: {}

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
                    console.log(response)
                    this.clientCurrent = response.data
                    console.log(this.clientCurrent)

                })
                .catch(error => console.log(error))
        },


    },
    computed: {

    }
}).mount('#app')