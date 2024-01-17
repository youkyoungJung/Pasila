import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import {
  faMagnifyingGlass,
  faCircleChevronLeft,
  faCircleChevronRight
} from '@fortawesome/free-solid-svg-icons'
import { faCalendarCheck, faUser } from '@fortawesome/free-regular-svg-icons'

import App from './App.vue'
import router from './router'

library.add(faMagnifyingGlass, faCircleChevronLeft, faCircleChevronRight)
library.add(faCalendarCheck, faUser)

const app = createApp(App)

app.component('font-awesome-icon', FontAwesomeIcon)

app.use(createPinia())
app.use(router)

app.mount('#app')
