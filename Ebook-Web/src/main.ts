import './assets/tailwind.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { createPersistedState } from 'pinia-plugin-persistedstate'

import App from './App.vue'
import router from './router'
import Toast, { type PluginOptions } from "vue-toastification";
const app = createApp(App)

const pinia = createPinia()
pinia.use(createPersistedState())
app.use(pinia)
app.use(router)

const options: PluginOptions = {
    // You can set your default options here
};
app.use(Toast, options);
app.mount('#app')
