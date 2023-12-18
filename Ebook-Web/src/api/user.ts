import type { ResType } from '@/types'
import httpInstance from '../utils/http'
import type { UserRegisterType, UserUpdateType } from '@/types/user'
import type { MessageType } from '@/types/message'

/**
 * 向服务器发送一个GET请求，根据user_id获取用户信息
 * @param {number} userId - 用户的id
 * @returns {Promise} 一个promise，如果成功则返回响应数据，如果失败则返回错误
 */
export const getUserInfoApi = (id: string, userId: number): Promise<any> =>
  httpInstance.get(`/api/v1/users/info/${userId},${id}`)

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

export const getMessageApi = (data: {
  id: any
  page: any
  pageSize: any
}): Promise<ResType<MessageType[]>> =>
  httpInstance.get('/api/v1/user/notify/', {
    params: {
      id: data.id,
      page: data.page,
      pageSize: data.pageSize
    }
  })

export const updateUserInfoApi = (data: UserUpdateType): Promise<ResType<any>> =>
  httpInstance.put('/api/v1/user', data)

export const followUserApi = (data: {
  careUserId: string
  caredUserId: string
}): Promise<ResType<any>> => httpInstance.post('/api/v1/user/care', data)

export const cancelUserFollowApi = (data: {
  caredUserId: string
  careUserId: string
  [property: string]: any
}): Promise<ResType<any>> =>
  httpInstance.delete('/api/v1/user/care', {
    data: data
  })

export const getFollowersApi = (userId: string): Promise<ResType<any>> =>
  httpInstance.get('/api/v1/user/care', {
    params: {
      id: userId
    }
  })

export const getFansApi = (userId: string): Promise<ResType<any>> =>
  httpInstance.get('/api/v1/user/fans', {
    params: {
      id: userId
    }
  })

export const newUserBlackApi = (data: {
  balckuserId: string
  userId: string
}): Promise<ResType<any>> => httpInstance.post('/api/v1/users/blackList', data)


export const cancelBlackApi = (data: {
  userId: string
  balckuserId: string
}): Promise<ResType<any>> =>
  httpInstance.delete('/api/v1/users/disBlackList', {
    data: data
  })
