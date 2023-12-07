// axios基础的封装
import { useUserStore } from '@/stores/userStores'

import axios, { AxiosError, type AxiosResponse } from 'axios'
import router from '../router'

const baseUrl: string = import.meta.env.VITE_BASE_URL // 添加类型注解
const httpInstance = axios.create({
  baseURL: baseUrl,
  timeout: 5000
})

// 拦截器
const userStore = useUserStore()
// axios请求拦截器
httpInstance.interceptors.request.use(
  (config) => {
    const token = userStore.user == null ? '' : userStore.user.token
    config.headers.Authorization = `Bearer ${token}`
    return config
  },
  (error) => Promise.reject(error)
)

// axios响应式拦截器
httpInstance.interceptors.response.use(
  (res: AxiosResponse) => res.data,
  (e: AxiosError) => {
    if (e.request.status === 401 || e.request.status === 403) {
      // ElMessageBox.alert('账户token过期，请重新登录...', '提示', {
      //     confirmButtonText: '确认',
      // }).then(() => {
      //     router.replace({path: '/login'}).then();
      // })
      const alertElement = document.createElement('div')
      alertElement.textContent = 'This is an alert message'
      alertElement.classList.add(
        'bg-red-100',
        'border',
        'border-red-400',
        'text-red-700',
        'px-4',
        'py-3',
        'rounded',
        'relative',
        'mb-4',
        'shadow-md'
      )

      const closeButton = document.createElement('span')
      closeButton.textContent = '×'
      closeButton.classList.add('absolute', 'top-0', 'right-0', 'px-2', 'py-1', 'cursor-pointer')

      closeButton.addEventListener('click', () => {
        alertElement.remove()
      })

      alertElement.appendChild(closeButton)
      document.body.appendChild(alertElement)
    } else {
      // 统一错误提示
    //   ElMessage.error('网络错误，请稍后重试')
    }
    return Promise.reject(e)
  }
)

export default httpInstance
