<template>
  <div id="header">
    <b-navbar toggleable="sm" type="light" variant="light">
      <b-navbar-toggle target="nav-text-collapse"></b-navbar-toggle>
      <b-navbar-brand>Shop</b-navbar-brand>
      <b-nav-form>
        <b-form-input class="mr-sm-2" placeholder="Search"></b-form-input>
        <b-button variant="outline-success" class="my-2 my-sm-0" type="submit">Search</b-button>
      </b-nav-form>
      <b-navbar-nav class="ml-auto">
        <b-button id="show-btn" @click="$bvModal.show('cart')">Cart({{count}})</b-button>
        <b-icon-minecart-loaded />
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav class="ml-auto">
          <b-nav-item-dropdown right>
            <template v-if="isAuth" v-slot:button-content>
              <em>Konto</em>
            </template>
            <b-dropdown-item href="#">Profile</b-dropdown-item>
            <b-dropdown-item @click="logout">Sign Out</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
        <b-button class="my-2 my-sm-0 login" right v-if="!isAuth">LogIn</b-button>
        
      </b-navbar-nav>
    </b-navbar>
    <b-modal id="cart" hide-footer>
      <template v-slot:modal-title>
        <h2>Cart</h2>
      </template>
      <div class="d-block text-center">
        <h3>Hello From This Modal!</h3>
        <div v-for="product in data" :product="product" :key="product.name">Name : {{product.name}} Price : {{product.price}}$</div>
        <b-icon icon="plus"/><b-icon icon="minus"/>
      </div>
      <b-button class="mt-3" block @click="$bvModal.hide('cart')">Chekout</b-button>
    </b-modal>

  </div>
</template>

<script>
import { mapGetters, mapActions, mapMutations } from "vuex";
import {cart} from "../assets/cart";
export default {
  name: "header",
  components: {},
  methods: {
    ...mapMutations(["addToCart"], "cart"),
    ...mapActions(["test"], "cart")
  },
  computed: {
    ...mapGetters(["count"], "cart")
  },
  data:()=>{
    return{
      isAuth: true,
      data : cart
    }
  }
};
</script>
<style scoped>
.login {
  margin-left: 1rem;
}
</style>
