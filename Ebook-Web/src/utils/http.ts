// axios基础的封装
import { useUserStore } from '@/stores/userStores'
import axios, { AxiosError, type AxiosResponse } from 'axios'
import router from '../router'
import createDialog from '@/components/InfoDialog'
import useCommandComponent from '@/hooks/useCommandComponent'
import InfoDialogVue from '@/components/InfoDialog.vue'

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
    const dialog = useCommandComponent(InfoDialogVue)
    if (e.request.status === 401 || e.request.status === 403) {
      // createDialog("ni",'nn');
      dialog({
        title: '😢',
        content: '账户token过期，请重新登录...',
        btnContent: '👌',
        onClose: () => {
          console.log('a')
          router.replace('/account/login')
        }
      })
      // ElMessageBox.alert('账户token过期，请重新登录...', '提示', {
      //     confirmButtonText: '确认',
      // }).then(() => {
      //     router.replace({path: '/login'}).then();
      // })
    } else {
      dialog({ title: '😢', content: '网络不太好，请重新尝试...', btnContent: '👌' })
    }

    return Promise.reject(e)
  }
)

export default httpInstance
