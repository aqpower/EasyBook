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
// axios请求拦截器
httpInstance.interceptors.request.use(
  (config) => {
    const userStore = useUserStore()
    const token = userStore.user == null ? '' : userStore.user.token
    if (token) {
      config.headers.Authorization = `${token}`
    }
    return config
  },
  (error) => Promise.reject(error)
)

// axios响应式拦截器
httpInstance.interceptors.response.use(
  (res: AxiosResponse) => res.data,
  (e: AxiosError) => {
    console.log(e)
    if (e.request.status === 401 || e.request.status === 403) {
      // ElMessageBox.alert('账户token过期，请重新登录...', '提示', {
      //     confirmButtonText: '确认',
      // }).then(() => {
      //     router.replace({path: '/login'}).then();
      // })
    } else {
      // 统一错误提示
      //   ElMessage.error('网络错误，请稍后重试')
    }

    return Promise.reject(e)
  }
)

export default httpInstance
