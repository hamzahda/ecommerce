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
        <b-navbar-text>
          <a href="/cart">  cart( {{count}} )  </a>
        </b-navbar-text>
        <b-icon-minecart-loaded />
        <b-button class="my-2 my-sm-0 login" right>LogIn</b-button>
      </b-navbar-nav>
    </b-navbar>
  </div>
</template>

<script>
import { mapGetters, mapActions, mapMutations } from "vuex";
export default {
  name: "header",
  components: {},
  methods: {
    ...mapMutations(["addToCart"], "cart"),
    ...mapActions(["test"], "cart")
  },
  computed: {
    ...mapGetters(["count"], "cart")
  }
};
</script>
<style scoped>
.login {
  margin-left: 1rem;
}
</style>
