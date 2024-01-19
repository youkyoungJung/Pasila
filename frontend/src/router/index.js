import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LiveView from '../views/LiveView.vue'
import ScheduleView from '../views/ScheduleView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/live/:id',
      name: 'live',
      component: LiveView
    },
    {
      path: '/schedule',
      name: 'schedule',
      component: ScheduleView
    }
  ]
})

export default router
