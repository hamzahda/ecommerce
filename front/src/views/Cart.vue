<template>
  <div class="cart">
    <div class="right">
      <div class>
      </div>
    </div>
    <div class="left">
      <div class="amount">Price : {{price}}</div>
      <select name="method">
        <option value="cash"></option>
        <option value></option>
      </select>
      <select name="delivery" id>
        <option value="nr"></option>
        <option value="sp"></option>
      </select>
      <button>CHECKOUT</button>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  props: ["product"],
  methods: {},
  computed: {
    ...mapGetters(["cart", "price"], "cart")
  },
  data: () => {
    return {
      counts: 0
    };
  }
};
</script>
<style scoped>
.cart {
  display: flex;
}
.right {
  display: flex;
  height: 100vh;

  justify-content: flex-start;
  flex-direction: column;
  
  flex: 1;

  }
.left {
  background-color: thistle;
}
</style>