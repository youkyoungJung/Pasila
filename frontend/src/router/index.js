import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import JoinView from '../views/JoinView.vue';
import FindPwView from '../views/FindPwView.vue';
import FindPwSendView from '@/views/FindPwSendView.vue';


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/join',
      name: 'join',
      component: JoinView,
    },
    {
      path: '/findpw',
      name: 'findpw',
      component: FindPwView,
    },
    {
      path: '/findpw/send',
      name: 'findpwsend',
      component: FindPwSendView,
    }
  ]
})

export default router
