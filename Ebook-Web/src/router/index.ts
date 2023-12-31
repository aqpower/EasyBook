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
        },
        {
          path: 'register',
          component: () => import('@/components/RegisterCard.vue')
        },
        {
          path: 'init-profile/:email',
          component: () => import('@/components/InitProfile.vue')
        },
        {
          path: 'adminLogin',
          component: () => import('@/components/AdminLogin.vue')
        }
      ]
    },
    {
      path: '/home',
      component: () => import('@/views/HomeView.vue'),
      children: [
        {
          path: 'explore',
          component: () => import('@/components/CardList.vue'),
          children: [
            {
              path: ':postId',
              component: () => import('@/components/CardDetail.vue')
            }
          ]
        },
        {
          path: 'search/:keyword',
          component: () => import('@/components/CardList.vue'),
          children: [
            {
              path: ':postId',
              component: () => import('@/components/CardDetail.vue')
            }
          ]
        },
        {
          path: 'profile/:userId',
          component: () => import('@/components/UserFile.vue'),
          children: [
            {
              path: 'posts/:postId',
              component: () => import('@/components/CardDetail.vue')
            }
          ]
        },
        {
          path: 'message',
          component: () => import('@/components/MessageList.vue'),
          children: [
            {
              path: 'posts/:postId',
              component: () => import('@/components/CardDetail.vue')
            }
          ]
        }
      ]
    },
    {
      path: '/admin',
      component: () => import('@/views/AdminView.vue'),
      children: [
        {
          path: 'home',
          component: () => import('@/layouts/SysStat.vue')
        },
        {
          path: 'review',
          component: () => import('@/components/VioList.vue'),
          children: [
            {
              path: 'posts/:postId',
              component: () => import('@/components/CardDetail.vue')
            }
          ]
        },
        {
          path: 'createAdmin',
          component: () => import('@/components/CreateAdmin.vue')
        }
      ]
    }
  ]
})

export default router
