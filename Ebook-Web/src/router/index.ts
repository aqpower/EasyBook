import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/account/login'
    },
    {
      path: '/account',
      component: () => import('@/views/LoginView.vue'),
      children: [
        {
          path: 'login',
          component: () => import('@/components/LoginCard.vue')
        },
        {
          path: 'resetPassword',
          component: () => import('@/components/ResetPasswordForm.vue')
        }
      ]
    },
    {
      path: '/home',
      component: () => import('@/views/HomeView.vue')
    }
  ]
})

export default router
