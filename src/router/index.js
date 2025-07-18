import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '@/views/LoginView.vue'
import IndexView from '@/views/IndexView.vue'
import AppointmentView from '@/views/AppointmentView.vue'
import HospitalView from '@/views/HospitalView.vue'
import SetmealView from '@/views/SetmealView.vue'
import SelectDateView from '@/views/SelectDateView.vue'
import ConfirmOrderView from '@/views/ConfirmOrderView.vue'
import Personal from '@/views/Personal.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: LoginView,
    },
    {
      path: '/index',
      name: 'index',
      component: IndexView,
    },
    {
      path: '/appointment',
      name: 'appointment',
      component: AppointmentView,
    },{
      path: '/hospital',
      name: 'hospital',
      component: HospitalView,
    },{
      path: '/setmeal',
      name: 'setmeal',
      component: SetmealView,
    },{
      path:'/selectDateView',
      name:'selectDateView',
      component:SelectDateView
    },{
      path:'/confirmOrderView',
      name:'confirmOrderView',
      component:ConfirmOrderView
    },{
      path:'/personal',
      name:'personal',
      component:Personal
    }
  ],
})

export default router
