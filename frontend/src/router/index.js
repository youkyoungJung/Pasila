import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import LoginView from '@/views/LoginView.vue'
import JoinView from '@/views/JoinView.vue'
import FindPwView from '@/views/FindPwView.vue'
import FindPwSendView from '@/views/FindPwSendView.vue'
import LiveView from '@/views/LiveView.vue'
import ScheduleView from '@/views/ScheduleView.vue'
import ChannelView from '@/views/ChannelView.vue'
import EditView from '@/views/EditView.vue'
import ChannelOrders from '@/views/ChannelOrders.vue'
import ReadyRegistView from '@/views/ReadyRegistView.vue'
import ReadyScriptView from '@/views/ReadyScriptView.vue'
import ReadyChatbotView from '@/views/ReadyChatbotView.vue'
import ReadyScheduleView from '@/views/ReadyScheduleView.vue'
import SetPwView from '@/views/SetPwView.vue'
import MyPageView from '@/views/MyPageView.vue'

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
      component: JoinView
    },
    {
      path: '/findpw',
      name: 'findpw',
      component: FindPwView
    },
    {
      path: '/findpw/send',
      name: 'findpwsend',
      component: FindPwSendView
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
    },
    {
      path: '/channel/:id',
      name: 'channel',
      component: ChannelView
    },
    {
      path: '/edit/:id',
      name: 'edit',
      component: EditView
    },
    {
      path: '/channel/:id/orders',
      name: 'orders',
      component: ChannelOrders
    },
    {
      path: '/ready/regist',
      name: 'readyregist',
      component: ReadyRegistView
    },
    {
      path: '/ready/script',
      name: 'readyscript',
      component: ReadyScriptView
    },
    {
      path: '/ready/chatbot',
      name: 'readychatbot',
      component: ReadyChatbotView
    },
    {
      path: '/ready/schedule',
      name: 'readyschedule',
      component: ReadyScheduleView
    },
    {
      path: '/setpw/:id',
      name: 'setpw',
      component: SetPwView
    },
    {
      path: '/my/:id',
      name: 'mypage',
      component: MyPageView
    }
  ]
})

export default router
