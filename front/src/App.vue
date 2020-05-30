<template>
  <div id="app">
    <Header/>
    <router-view></router-view>
  </div>
</template>

<script>
import Header from "./components/Header"
export default {
  
  name: "App",
  components: {
    Header,
  }
};
</script>
<style>
@import url("https://fonts.googleapis.com/css?family=Sen&display=swap");

#app::-webkit-scrollbar { width: 0 !important }
.selector-for-some-widget {
  box-sizing: content-box;
}

</style>