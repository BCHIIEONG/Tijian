import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '@/views/LoginView.vue'
import IndexView from '@/views/IndexView.vue'
import AppointmentView from '@/views/AppointmentView.vue'
import HospitalView from '@/views/HospitalView.vue'
import SetmealView from '@/views/SetmealView.vue'
import SelectDateView from '@/views/SelectDateView.vue'
import ConfirmOrderView from '@/views/ConfirmOrderView.vue'
import Personal from '@/views/Personal.vue'
import AppointmentSuccessView from '@/views/AppointmentSuccessView.vue'
import RegisterView from '@/views/RegisterView.vue'
import AppointmentListView from '@/views/AppointmentListView.vue'
import ReportListView from '@/views/ReportListView.vue'
import ReportView from '@/views/ReportView.vue'
import AppointmentCancelView from '@/views/AppointmentCancelView.vue'
import DoctorDetailView from '@/views/DoctorDetailView.vue'
import CommonDiseasesView from '@/views/CommonDiseasesView.vue'
import DiseasesView from '@/views/DiseasesView.vue'
import FoundView from '@/views/FoundView.vue'
import FoundContentView from '@/views/FoundContentView.vue'
import PhysicalExaminationEncyclopediaView from '@/views/PhysicalExaminationEncyclopediaView.vue'
import ExamineView from '@/views/ExamineView.vue'
import ExamineDetailView from '@/views/ExamineDetailView.vue'


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
    },{
      path:'/appointmentsuccess',
      name:'appointmentsuccess',
      component:AppointmentSuccessView
    },{
      path:'/register',
      name:'register',
      component:RegisterView
    },{
      path:'/appointmentlist',
      name:'appointmentlist',
      component:AppointmentListView
    },{
      path:'/reportlist.html',
      name:'reportlist',
      component:ReportListView
    },{
      path:'/report',
      name:'report',
      component:ReportView
    },{
      path:'/appointment-cancel',
      name:'appointment-cancel',
      component:AppointmentCancelView
    },{
      path:'/doctorDetailView',
      name:'doctorDetailView',
      component:DoctorDetailView
    },{
      path: '/commonDiseasesView',
      name: 'commonDiseasesView',
      component: CommonDiseasesView,
    },{
      path: '/diseasesView',
      name: 'diseasesView',
      component: DiseasesView,
    },{
      path: '/foundView',
      name: 'foundView',
      component: FoundView,
    },{
      path: '/foundContentView',
      name: 'foundContentView',
      component: FoundContentView,
    },{
      path: '/physicalExaminationEncyclopediaView',
      name: 'physicalExaminationEncyclopediaView',
      component: PhysicalExaminationEncyclopediaView,
    },{
      path: '/examineView',
      name: 'examineView',
      component: ExamineView,
    },{
      path: '/examineDetailView',
      name: 'examineDetailView',
      component: ExamineDetailView,
    },
  ],
})

export default router
