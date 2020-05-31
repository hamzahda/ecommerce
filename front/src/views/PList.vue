<template>
  <div class="products">
    <Product class="product" v-for="product in data" :product="product" :key="product.id" />
  </div>
</template>

<script>
import { products } from "../assets/data";
import Product from "../components/Product";
export default {
  name: "",
  components: {
    Product
  },
  computed: {},
  data: () => {
    return {
      data: products
    };
  }
};
</script>
<style scoped>
.products {
  display: flex;
  justify-content: center;

  flex-direction: row;
}

</style>