import type { ResType } from '@/types'
import httpInstance from '../utils/http'
import type { UserRegisterType } from '@/types/user'

/**
 * 向服务器发送一个GET请求，根据user_id获取用户信息
 * @param {number} userId - 用户的id
 * @returns {Promise} 一个promise，如果成功则返回响应数据，如果失败则返回错误
 */
export const getUserInfoApi = (userId: number): Promise<any> =>
  httpInstance.get(`/api/v1/users/${userId}`)

/**
 * 向服务器发送一个POST请求，进行用户登录请求
 * @param {{password: string; email?: string; id?: string}} data
 * @returns {Promise} 一个Promise
 */
export const UserLoginApi = (data: {
  email?: string
  id?: string
  password: string
}): Promise<any> => httpInstance.post('/api/v1/login/users', data)

export const sendEmailApi = (email: string): Promise<ResType<any>> =>
  httpInstance.get('/api/v1/login/email-verification/', {
    params: {
      email: email
    }
  })

export const newUserApi = (data: any) => httpInstance.post('/api/v1/users', data)

export const emailVerifyApi = (code: string): Promise<ResType<any>> =>
  httpInstance.post(`/api/v1/login/email-verification/?verfityCode=${code}`)

export const userRegisterApi = (data: UserRegisterType): Promise<ResType<any>> =>
  httpInstance.post('/api/v1/users', data)
