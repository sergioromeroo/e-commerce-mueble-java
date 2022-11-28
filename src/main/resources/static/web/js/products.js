// 'use strict';



// /**
//  * add event on element
//  */

// const addEventOnElem = function (elem, type, callback) {
//   if (elem.length > 1) {
//     for (let i = 0; i < elem.length; i++) {
//       elem[i].addEventListener(type, callback);
//     }
//   } else {
//     elem.addEventListener(type, callback);
//   }
// }



// /**
//  * navbar toggle
//  */

// const navbar = document.querySelector("[data-navbar]");
// const navbarLinks = document.querySelectorAll("[data-nav-link]");
// const navTogglers = document.querySelectorAll("[data-nav-toggler]");
// const overlay = document.querySelector("[data-overlay]");

// const toggleNavbar = function () {
//   navbar.classList.toggle("active");
//   overlay.classList.toggle("active");
//   document.body.classList.toggle("active");
// }

// addEventOnElem(navTogglers, "click", toggleNavbar);

// const closeNavbar = function () {
//   navbar.classList.remove("active");
//   overlay.classList.remove("active");
//   document.body.classList.remove("active");
// }

// addEventOnElem(navbarLinks, "click", closeNavbar);



// /**
//  * header & back top btn active when window scroll down to 100px
//  */

// const header = document.querySelector("[data-header]");
// const backTopBtn = document.querySelector("[data-back-top-btn]");

// const showElemOnScroll = function () {
//   if (window.scrollY > 100) {
//     header.classList.add("active");
//     backTopBtn.classList.add("active");
//   } else {
//     header.classList.remove("active");
//     backTopBtn.classList.remove("active");
//   }
// }

// addEventOnElem(window, "scroll", showElemOnScroll);



// /**
//  * product filter
//  */

// const filterBtns = document.querySelectorAll("[data-filter-btn]");
// const filterBox = document.querySelector("[data-filter]");

// let lastClickedFilterBtn = filterBtns[0];

// const filter = function () {
//   lastClickedFilterBtn.classList.remove("active");
//   this.classList.add("active");
//   lastClickedFilterBtn = this;

//   filterBox.setAttribute("data-filter", this.dataset.filterBtn)
// }

// addEventOnElem(filterBtns, "click", filter);



const app = Vue.createApp({
  data() {
      return {
          products: [],
          productsBackUp: [],
          typeCategory: [],
          materialTypeCategory: [],
          typeVModel: [],
          materialTypeVModel: [],
          inputSearchVModel: "",
          priceVModel: "",
          firstFilter: [],
          shoppingCart: [],
          totalAmount: 0,




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
  created() {
      this.loadProducts()

  },
  mounted() {

      let currentCart = JSON.parse(localStorage.getItem('cart'))
      if(!currentCart) {
          this.shoppingCart = []
      } else {
          this.shoppingCart = currentCart
      }

      let currentTotalAmount = JSON.parse(localStorage.getItem('totalAmount'))
      if(currentTotalAmount != 0){
          this.totalAmount = currentTotalAmount
      }
  },
  methods: {
      loadProducts() {
          axios.get('/api/products')
              .then(response => {
                  this.products = response.data.filter(data => data.enable)
                  this.productsBackUp = this.products
                  this.products.forEach(product => !this.typeCategory.includes(product.type) ? this.typeCategory.push(product.type) : "")
                  this.products.forEach(product => !this.materialTypeCategory.includes(product.materialType) ? this.materialTypeCategory.push(product.materialType) : "")
                  console.log(this.products)

              })
              .catch(error => console.log(error))
      },
      finalAmount() {
          this.totalAmount = 0
          this.shoppingCart.map(product => {
              let addition = product.quantity * product.price
              this.totalAmount += addition
          })
          localStorage.setItem('totalAmount', JSON.stringify(this.totalAmount))
      },
      cartStorage(){
          localStorage.setItem('cart', JSON.stringify(this.shoppingCart))
          this.finalAmount()
      },
      addProductToShoppingCart(selectProduct){

              let repeatedProduct = this.shoppingCart.filter(product => product.id == selectProduct.id)

              if (repeatedProduct.length > 0) {
                  // CASO EN EL QUE ESTA EL ELEMENTO YA EN EL CARRITO
                  this.shoppingCart.filter(item => {
                      if (item.id == selectProduct.id) {
                          if (item.stock > 0) {
                              item.quantity++
                              item.stock--
                          }
                      }
                  })
                  this.cartStorage()
              }
              else {
                  // CASO EN EL QUE NO ESTA
                  this.productsBackUp.filter(product => {
                      if (product.id == selectProduct.id) {
                            selectProduct.quantity++
                              product.stock--
                      }
                  })
                  this.shoppingCart.push(selectProduct)
                  this.cartStorage()
              }

              //localStorage.setItem('totalAmount', JSON.stringify(this.totalAmount))
      },
      deleteOneItem(selectProduct){
          if(selectProduct.quantity > 0){
              this.shoppingCart.filter(product => {
                  if(product.id == selectProduct.id){
                      product.quantity--
                      product.stock++
                  }
              })
          }
          this.cartStorage()
      },
      deleteProduct(selectProduct){
          this.shoppingCart = this.shoppingCart.filter(product => product != selectProduct)
          this.cartStorage()
      },
      emptyCart() {
          localStorage.clear()
          window.location.reload()
      },
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
  computed: {
      filter() {
          let firstFilter = this.productsBackUp.filter(product => product.name.toLowerCase().includes(this.inputSearchVModel.toLowerCase()))
          if (this.typeVModel.length && !this.materialTypeVModel.length) {
              this.products = firstFilter.filter(product => this.typeVModel.includes(product.type))
          }
          else if (this.materialTypeVModel.length && !this.typeVModel.length) {
              this.products = firstFilter.filter(product => this.materialTypeVModel.includes(product.materialType))
          }
          else if(this.typeVModel.length && this.materialTypeVModel.length){
              this.products = firstFilter.filter(product => this.materialTypeVModel.includes(product.materialType)).filter(product => this.typeVModel.includes(product.type))
          }
          else {
              this.products = firstFilter
          }

      },
      priceFilter (){
          if(this.priceVModel == "higher"){
          this.products = this.productsBackUp.sort((a,b) => { if(a.price > b.price) {return -1} if(a.price < b.price) {return 1}})
          }
          else if(this.priceVModel == "lower"){
              this.products = this.productsBackUp.sort((a,b) => { if(a.price > b.price) {return 1} if(a.price < b.price) {return -1}})
          }
      }
  }
}).mount('#app')