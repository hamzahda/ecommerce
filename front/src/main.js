import "@babel/polyfill";
import "mutationobserver-shim";
import Vue from "vue";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import VueRouter from "vue-router";
import App from "./App.vue";
import store from './store'
import Vuex from 'vuex'

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

Vue.use(Vuex);
Vue.use(VueRouter);
Vue.config.productionTip = false;
Vue.use(BootstrapVue);
Vue.use(IconsPlugin);

const router = new VueRouter({
  routes: [
    {
      path: "/",
      name: "Welcome",
      component: () => import("./views/Welcome.vue"),
    },
    {
      path: "/plist",
      name: "plist",
      component: () => import("./views/PList.vue"),
    },
    {
      path: "/cart",
      name: "cart",
      component: () => import("./views/Cart.vue"),
    },
  ],
});
new Vue({store,
  router,
  render: (h) => h(App),
}).$mount("#app");
