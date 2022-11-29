const app = Vue.createApp({
    data() {
        return {
            clientCurrent: {},
            favorites: [],
            tickets: [],

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
                    console.log(this.clientCurrent)
                    this.favorites = this.clientCurrent.clientProductFav
                    console.log(this.favorites)
                    this.tickets = this.clientCurrent.tickets
                    console.log(this.tickets)

                })
                .catch(error => console.log(error))
        },
    },
    computed: {
    }
}).mount('#app')






/* <div class="card text-center movilcard" style="width: 50vw;">
                                    <div class="card-header">
                                          <ul class="nav nav-tabs card-header-tabs">
                                                <li class="nav-item">
                                                      <a class="nav-link active" aria-current="true"
                                                            href="#">Tarjetas</a>
                                                </li>
                                          </ul>
                                    </div>
                                    <div class="card-body">
                                          <div class="d-flex flex-column justify-content-center">
                                                <div class="accordion accordion-flush" id="accordionFlushExample">
                                                      <div class="accordion-item" v-for="tarjeta in tarjetas">
                                                            <h2 class="accordion-header"
                                                                  :id="'flush-heading'+tarjeta.id">
                                                                  <button class="accordion-button collapsed"
                                                                        type="button" data-bs-toggle="collapse"
                                                                        :data-bs-target="'#flush-collapse'+tarjeta.id"
                                                                        aria-expanded="false"
                                                                        :aria-controls="'flush-collapse'+tarjeta.id">
                                                                        {{tarjeta.type}}O {{tarjeta.color}}
                                                                  </button>
                                                            </h2>
                                                            <div :id="'flush-collapse'+tarjeta.id"
                                                                  class="accordion-collapse collapse"
                                                                  :aria-labelledby="'flush-heading'+tarjeta.id"
                                                                  data-bs-parent="#accordionFlushExample">
                                                                  <div class="accordion-body">
                                                                        <p>{{tarjeta.number.replaceAll("-", " ")}}</p>
                                                                        <p>{{tarjeta.cardHolder}}</p>
                                                                        <div
                                                                              class="d-flex gap-3 justify-content-center">
                                                                              <p>Valido: {{tarjeta.thruDate}}</p>
                                                                              <p>CVV: {{tarjeta.cvv}}</p>
                                                                        </div>
                                                                  </div>
                                                            </div>
                                                      </div>
                                                </div>
                                                <div class="d-flex flex-column justify-content-center"
                                                      v-if="tarjetas.length == 0">
                                                      <p class="card-text">Aún no tenés tarjetas.</p>
                                                </div>
                                          </div>
                                    </div>

                              </div>*/