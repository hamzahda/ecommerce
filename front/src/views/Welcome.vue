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
    <button>SHOP NOW</button>
    <div class="div"></div>
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
</style>