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
        <b-nav-text> <a href="/cart">cart( {{cart}} ) </a></b-nav-text>
        <b-button class="my-2 my-sm-0 login" right>LogIn</b-button>
      </b-navbar-nav>
    </b-navbar>
  </div>
</template>

<script>
export default {
  name: "header",
  methods: {},
  computed: {}
};
</script>
<style scoped>

.login{
  margin-left: 1rem;
}
</style>
