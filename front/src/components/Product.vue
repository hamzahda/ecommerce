<template>
  <div class="product">
    <div>
      <img :src="product.link" />
    </div>
    <div class="details">
      <div class="name">{{product.name}}</div>
      <div class="price">{{product.price}}</div>
      <input ref="nrInput" type="number" value="1" />
      <button class="butn" @click="addToCart(product, this.$refs.nrInput.value)">ADD TO CART</button>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  props: ["product"],
  methods: {
    ...mapActions(["addToCart", "remove"], "cart")
  },
  computed: {},
  data: () => {
    return {
      counts: 0
    };
  }
};
</script>
<style scoped>
.product {
  text-align: center;
  background-color: rosybrown;

  width: 11rem;
  height: 11rem;
}
input {
  width: 4rem;
  border-style: none;


}
.deatils{
  display: flex;
  justify-content: center;
}
</style>