import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import LoginView from '@/views/LoginView.vue'
import JoinView from '@/views/JoinView.vue'
import FindPwView from '@/views/FindPwView.vue'
import FindPwSendView from '@/views/FindPwSendView.vue'
import OpenviduView from '@/views/OpenviduView.vue'
import OrderView from '@/views/OrderView.vue'
import OrderSuccessView from '@/views/OrderSuccessView.vue'
import LiveEndView from '@/views/LiveEndView.vue'
import ReplayView from '@/views/ReplayView.vue'
import ShortpingView from '@/views/ShortpingView.vue'
import ScheduleView from '@/views/ScheduleView.vue'
import ChannelView from '@/views/ChannelView.vue'
import EditView from '@/views/EditView.vue'
import ChannelOrdersView from '@/views/ChannelOrdersView.vue'
import ReadyRegistView from '@/views/ReadyRegistView.vue'
import ReadyScriptView from '@/views/ReadyScriptView.vue'
import ReadyChatbotView from '@/views/ReadyChatbotView.vue'
import ReadyScheduleView from '@/views/ReadyScheduleView.vue'
import SetPwView from '@/views/SetPwView.vue'
import CheckPasswordView from '@/views/CheckPasswordView.vue'
import MyPageView from '@/views/MyPageView.vue'
import MyOrdersView from '@/views/MyOrdersView.vue'

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
      path: '/live/:liveId',
      name: 'openvidu',
      component: OpenviduView,
      props: true
    },
    {
      path: '/live/:id/order',
      name: 'order',
      component: OrderView,
      props: true
    },
    {
      path: '/order/success',
      name: 'order-success',
      component: OrderSuccessView
    },
    {
      path: '/live/:id/end',
      name: 'liveend',
      component: LiveEndView,
      props: true
    },
    {
      path: '/replay/:liveId',
      name: 'replay',
      component: ReplayView,
      props: true
    },
    {
      path: '/shortping/:shortpingId',
      name: 'shortping',
      component: ShortpingView,
      props: true
    },
    {
      path: '/schedule',
      name: 'schedule',
      component: ScheduleView
    },
    {
      path: '/channel/:channelId',
      name: 'channel',
      component: ChannelView,
      props: true
    },
    {
      path: '/edit/:liveId',
      name: 'edit',
      component: EditView,
      props: true
    },
    {
      path: '/channel/:channelId/orders',
      name: 'orders',
      component: ChannelOrdersView,
      props: true
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
      path: '/setpw',
      name: 'setpw',
      component: SetPwView
    },
    {
      path: '/checkpw',
      name: 'checkpw',
      component: CheckPasswordView
    },
    {
      path: '/my',
      name: 'mypage',
      component: MyPageView
    },
    {
      path: '/my/orders',
      name: 'myorders',
      component: MyOrdersView
    }
  ]
})

export default router
