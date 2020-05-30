<template>
  <section id="welcome">
    <vueper-slides>
      <vueper-slide
        v-for="(slide, i) in slides"
        :key="i"
        :title="slide.title"
        :content="slide.content"
        :image="slide.image"
      />
    </vueper-slides>
    <div class="center">
      <button class="button">SHOP NOW</button>
      <div class="div">  <img src=""  >  </div>
    </div>
  </section>
</template>
<script>
import { VueperSlides, VueperSlide } from "vueperslides";
import "vueperslides/dist/vueperslides.css";
export default {
  name: "Welcome",
  components: { VueperSlides, VueperSlide },
  methods: {},
  data: ()=>{
    return{
      slides: [
    {
      title: '',
      content: '',
      image: require('../assets/slide.jpg')
    },
    {
      title: '',
      content: '',
      image: require('../assets/slide.jpg')
    }
  ],
    }
  }
};
</script>


<style scoped>

.center{
  display: flex;
  justify-content: center;
}
#welcome{

  background-color: whitesmoke;
  height: 100vh;
}
.button{
  padding: 0.5rem;
  margin: 1rem;

  color: snow;
  background-color:coral ;

  border-style: none;
}

</style>