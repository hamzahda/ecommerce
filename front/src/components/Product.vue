<template>
		<div class="card">
			<div class="img">
				<img src="@/assets/index.jpg">
			</div>
			<div class="contentbox">
				
        Price : {{product.price}}$ <br/>
        <input type="number" value="1">
        <button>ADD TO CART</button>
			</div>
		</div>
</template>
<script>
export default {
  props: ["product"],
  methods: {
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
.card{
  width: 11rem;
  


  margin: 1rem;
  padding: 1rem;
}


img{
  width: 8rem;
  height: fit-content;
}
.img{
  text-align: center;
}
.contentbox{
  text-align: center;
}
input{
  width: 4rem;
  margin: 1rem;  
}
button{
  border-style: none;
  padding: 1rem;

  
}
</style>