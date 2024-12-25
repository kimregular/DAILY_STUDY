import { createApp } from 'vue'
import App from './App.vue'
import mitt from "mitt";

let emitter = mitt();
let app = createApp(App);
app.config.globalProperties.emitter = emitter;

app.mount('#app')
