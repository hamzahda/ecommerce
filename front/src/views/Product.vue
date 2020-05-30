<template>
  <div class="product">
    <img src="" />
    <div class="name"></div>
    <div class="price"></div>
    <input type="number"  value="0">
    <button class="butn">ADD TO CART</button>
  </div>
</template>

<script>
export default {
  name: "",
  props:{
      src: String,
      name: String,

      price: String,
      
  },
  methods: {},
  computed: {},
  data : ()=>{
      return{
          counts : 0,
      }
  }
};
</script>
<style scoped>

.product{
    text-align:center;
}

</style>