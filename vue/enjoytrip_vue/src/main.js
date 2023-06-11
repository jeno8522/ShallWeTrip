import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

import "@/api/fontAwesomeIcon.js"; // fontAwesomeIcon.js 불러옴
import "@/api/vueBootstrap.js";

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
